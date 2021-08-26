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
import com.bsl.mapper.BslWxWasteWeightInfoMapper;
import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.pojo.BslWxWasteWeightInfoExample;
import com.bsl.pojo.BslWxWasteWeightInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.waste.WasteWxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 外协厂废品管理
 * duk-20190319
 */
@Service
public class WasteWxServiceImpl implements WasteWxService {

	@Autowired	 
	BslWxWasteWeightInfoMapper bslWxWasteWeightInfoMapper;
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
		BslWxWasteWeightInfoExample bslProductInfoExample = new BslWxWasteWeightInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		if(!StringUtils.isBlank(queryCriteria.getWasteType())){
			criteria.andWasteTypeEqualTo(queryCriteria.getWasteType());
		}
		//分页处理
		PageHelper.startPage(1,2000);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`waste_type` asc");
		List<BslWxWasteWeightInfo> bslProductInfos = bslWxWasteWeightInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslWxWasteWeightInfo> pageInfo = new PageInfo<BslWxWasteWeightInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wasteWxServiceImpl","getWasteService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 入库
	 */
	@Override
	public BSLResult updateWasteIn(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser) {
		//先根据类型查询原重量
		BslWxWasteWeightInfo oriWasteInfo = bslWxWasteWeightInfoMapper.selectByPrimaryKey(bslWxWasteWeightInfo.getWasteType());
		if(oriWasteInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "没有该种类的废品。");
		}
		//设置入参
		Float oriWeight = oriWasteInfo.getWasteWeight();
		Float inWeight = bslWxWasteWeightInfo.getWasteWeight();
		bslWxWasteWeightInfo.setWasteWeight(oriWeight+inWeight);
		if(bslWxWasteWeightInfo.getWasteInWeight() == null){
			bslWxWasteWeightInfo.setWasteInWeight(inWeight);
		}else{
			bslWxWasteWeightInfo.setWasteInWeight(bslWxWasteWeightInfo.getWasteInWeight()+inWeight);
		}
		bslWxWasteWeightInfo.setCrtDate(new Date());
		//开始修改
		int result = bslWxWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWxWasteWeightInfo);
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
		bslStockChangeDetail.setRubbishType(bslWxWasteWeightInfo.getWasteType());//废品类型
		bslStockChangeDetail.setInputuser(inputUser);//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark(bslWxWasteWeightInfo.getRemark());
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslWxWasteWeightInfo.getWasteWeight());
	}

	/**
	 * 质量修改
	 */
	@Override
	public BSLResult updateWasteCheck(BslWxWasteWeightInfo bslWxWasteWeightInfo,String inputUser) {
		//先根据类型查询原废品重量信息
		BslWxWasteWeightInfo oriWasteInfo = bslWxWasteWeightInfoMapper.selectByPrimaryKey(bslWxWasteWeightInfo.getWasteType());
		if(oriWasteInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空, "没有该种类的废品。");
		}
		//设置入参
		Float oriWeight = oriWasteInfo.getWasteWeight();
		Float updWeight = bslWxWasteWeightInfo.getWasteWeight();
		//开始修改
		bslWxWasteWeightInfo.setCrtDate(new Date());
		//开始修改
		int result = bslWxWasteWeightInfoMapper.updateByPrimaryKeySelective(bslWxWasteWeightInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
		}
		//修改成功之后记录状态变更流水
		BslChangeStatusRecord bslChangeStatusRecord = new BslChangeStatusRecord();
		bslChangeStatusRecord.setChangeSerno(createStockStatusChangeId());//修改流水
		bslChangeStatusRecord.setChangeType(DictItemOperation.状态强制维护类别_废品重量维护);
		bslChangeStatusRecord.setChangeProdId(bslWxWasteWeightInfo.getWasteType());//废品类型
		bslChangeStatusRecord.setBeforeStatus(oriWeight.toString());//原重量
		bslChangeStatusRecord.setAfterStatus(updWeight.toString());//修改质量
		bslChangeStatusRecord.setInputuser(inputUser);
		bslChangeStatusRecord.setCrtDate(new Date());
		bslChangeStatusRecord.setRemark(bslWxWasteWeightInfo.getRemark());
		int resultStock = bslChangeStatusRecordMapper.insert(bslChangeStatusRecord);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增状态变动表失败");
		}
		return BSLResult.ok(bslWxWasteWeightInfo.getWasteWeight());
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
