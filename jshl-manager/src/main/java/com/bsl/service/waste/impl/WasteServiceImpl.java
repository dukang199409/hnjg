package com.bsl.service.waste.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslChangeStatusRecordMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.mapper.BslWasteWeightInfoMapper;
import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.pojo.BslWasteWeightInfoExample;
import com.bsl.pojo.BslWasteWeightInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.waste.WasteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 废品管理
 * duk-20190319
 */
@Service
public class WasteServiceImpl implements WasteService {

	@Autowired	 
	BslWasteWeightInfoMapper bslWasteWeightInfoMapper;
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired	 
	BslChangeStatusRecordMapper bslChangeStatusRecordMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	
	@Value("${REDIS_NEXT_CHANGE_STATUS_ID}")
	private String REDIS_NEXT_CHANGE_STATUS_ID;

	/**
	 * 根据条件查询废品库存信息
	 */
	@Override
	public BSLResult getWasteService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslWasteWeightInfoExample bslProductInfoExample = new BslWasteWeightInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		if(!StringUtils.isBlank(queryCriteria.getWasteType())){
			criteria.andWasteTypeEqualTo(queryCriteria.getWasteType());
		}
		//分页处理
		PageHelper.startPage(1,2000);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`waste_type` asc");
		List<BslWasteWeightInfo> bslProductInfos = bslWasteWeightInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslWasteWeightInfo> pageInfo = new PageInfo<BslWasteWeightInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wasteServiceImpl","getWasteService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 入库
	 */
	@Override
	public BSLResult updateWasteIn(BslWasteWeightInfo bslWasteWeightInfo,String inputUser) {
		//先根据类型查询原重量
		BslWasteWeightInfo oriWasteInfo = bslWasteWeightInfoMapper.selectByPrimaryKey(bslWasteWeightInfo.getWasteType());
		if(oriWasteInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "没有该种类的废品。");
		}
		//设置入参
		Float oriWeight = oriWasteInfo.getWasteWeight();
		Float inWeight = bslWasteWeightInfo.getWasteWeight();
		bslWasteWeightInfo.setWasteWeight(oriWeight+inWeight);
		if(bslWasteWeightInfo.getWasteInWeight() == null){
			bslWasteWeightInfo.setWasteInWeight(inWeight);
		}else{
			bslWasteWeightInfo.setWasteInWeight(bslWasteWeightInfo.getWasteInWeight()+inWeight);
		}
		bslWasteWeightInfo.setCrtDate(new Date());
		//开始修改
		int result = bslWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWasteWeightInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
		}
		//入库成功之后插入修改流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_废品);//产品类型
		bslStockChangeDetail.setRubbishWeight(inWeight);//重量
		bslStockChangeDetail.setRubbishType(bslWasteWeightInfo.getWasteType());//废品类型
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark(bslWasteWeightInfo.getRemark());
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslWasteWeightInfo.getWasteWeight());
	}

	/**
	 * 质量修改
	 */
	@Override
	public BSLResult updateWasteCheck(BslWasteWeightInfo bslWasteWeightInfo,String inputUser) {
		//先根据类型查询原废品重量信息
		BslWasteWeightInfo oriWasteInfo = bslWasteWeightInfoMapper.selectByPrimaryKey(bslWasteWeightInfo.getWasteType());
		if(oriWasteInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "没有该种类的废品。");
		}
		//设置入参
		Float oriWeight = oriWasteInfo.getWasteWeight();
		Float updWeight = bslWasteWeightInfo.getWasteWeight();
		//开始修改
		bslWasteWeightInfo.setCrtDate(new Date());
		//开始修改
		int result = bslWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWasteWeightInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
		}
		//修改成功之后记录状态变更流水
		BslChangeStatusRecord bslChangeStatusRecord = new BslChangeStatusRecord();
		bslChangeStatusRecord.setChangeSerno(createStockStatusChangeId());//修改流水
		bslChangeStatusRecord.setChangeType(DictItemOperation.状态强制维护类别_废品重量维护);
		bslChangeStatusRecord.setChangeProdId(bslWasteWeightInfo.getWasteType());//废品类型
		bslChangeStatusRecord.setBeforeStatus(oriWeight.toString());//原重量
		bslChangeStatusRecord.setAfterStatus(updWeight.toString());//修改质量
		bslChangeStatusRecord.setInputuser(inputUser);
		bslChangeStatusRecord.setCrtDate(new Date());
		bslChangeStatusRecord.setRemark(bslWasteWeightInfo.getRemark());
		int resultStock = bslChangeStatusRecordMapper.insert(bslChangeStatusRecord);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增状态变动表失败");
		}
		return BSLResult.ok(bslWasteWeightInfo.getWasteWeight());
	}

	/**
	 * 库存变动流水自动生成编号
	 * CH+日期+4位序号
	 * @return
	 */
	public String createStockChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_STOCKCHANGE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CH%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}
	
	/**
	 * 状态修改流水自动生成编号
	 * CS+日期+4位序号
	 * @return
	 */
	public String createStockStatusChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_CHANGE_STATUS_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CS%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}

}
