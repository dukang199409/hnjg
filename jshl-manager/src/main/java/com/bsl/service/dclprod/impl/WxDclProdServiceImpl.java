package com.bsl.service.dclprod.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.WxDclProdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 外协厂待处理品管理实现类
 * duk-20190319
 */
@Service
public class WxDclProdServiceImpl implements WxDclProdService {

	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private BslStockChangeDetailMapper bslStockChangeDetailMapper;
	
	@Value("REDIS_NEXT_DCLPROD_ID")
	private String REDIS_NEXT_DCLPROD_ID;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	
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
	 * 获取能够处理的待处理品信息
	 */
	@Override
	public List<BslProductInfo> getDclInfoDeal() {
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_待处理品);
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_转场);
		bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		return bslProductInfos;
	}

	/**
	 * 获取外协产待处理品信息
	 */
	@Override
	public BSLResult getDclWxProdServiceByCriteria(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslDclProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslDclProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_待处理品);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_转场);
		//待处理品编号
		if (!StringUtils.isBlank(queryCriteria.getProdId())) {
			criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
		}
		//成品生产批号
		if (!StringUtils.isBlank(queryCriteria.getProdPlanNo())) {
			criteria.andProdPlanNoLike(StringUtil.likeStr(queryCriteria.getProdPlanNo()));
		}
		//炉号
		if (!StringUtils.isBlank(queryCriteria.getProdLuno())) {
			criteria.andProdLunoLike(StringUtil.likeStr(queryCriteria.getProdLuno()));
		}
		//父级盘号
		if (!StringUtils.isBlank(queryCriteria.getProdParentNo())) {
			criteria.andProdParentNoLike(StringUtil.likeStr(queryCriteria.getProdParentNo()));
		}
		//待处理品规格
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		//钢种
		if (!StringUtils.isBlank(queryCriteria.getProdMaterial())) {
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		//待处理品状态
		if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		
		//开始日期结束日期
		//起始日期 结束日期
		Date dateStart = new Date();
		Date dateEnd = new Date();
		if(!StringUtils.isBlank(queryCriteria.getStartDate())){
			try {
				dateStart = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dateStart = DictItemOperation.日期转换实例.parse("2018-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getEndDate())){
			try {
				dateEnd = DictItemOperation.日期转换实例.parse(queryCriteria.getEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			dateEnd = new Date();
		}
		criteria.andCrtDateBetween(dateStart,dateEnd);
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslDclProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslDclProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslDclProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"wxDclProdServiceImpl","getDclWxProdServiceByCriteria",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 待处理品单个完成
	 */
	@Override
	public BSLResult updateFinishStatus(String prodId, String user) {
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据待处理品编号查询记录为空");
		}
		//校验状态，只有是未处理的状态才能修改
		if(!DictItemOperation.产品状态_已入库.equals(bslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的待处理品才允许完成");
		}
		//开始完成
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_处理完成);
		bslProductInfo.setUpdDate(new Date());
		bslProductInfo.setProdCheckuser(user);
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"完成失败");
		}
		
		//记录完成信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(bslProductInfo.getProdId());//待处理品编号
		bslStockChangeDetailRaw.setPlanSerno(bslProductInfo.getProdPlanNo());
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_待处理品);//待处理品类型
		bslStockChangeDetailRaw.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(user);//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(prodId);
	}

	/**
	 * 待处理品单个批量
	 */
	@Override
	public BSLResult updateFinishAllStatus(String[] arrays, String user) {
		BSLResult bslResultTmp;
		for (String prodId : arrays) {
			bslResultTmp = updateFinishStatus(prodId,user);
			if(bslResultTmp.getStatus() != 200){
				throw new BSLException(ErrorCodeInfo.错误类型_交易异常,bslResultTmp.getMsg());
			}
		}
		return BSLResult.ok();
	}

}
