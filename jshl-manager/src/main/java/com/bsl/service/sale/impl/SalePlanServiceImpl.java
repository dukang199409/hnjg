package com.bsl.service.sale.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.pojo.BslBsPlanInfoExample.Criteria;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SalePlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 销售出库通知单管理实现类
 * duk-20190319
 */
@Service
public class SalePlanServiceImpl implements SalePlanService{
	
	 @Autowired	 
	 BslBsPlanInfoMapper bslBsPlanInfoMapper;
	 @Autowired	 
	 BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	 @Autowired	 
	 BslProductInfoMapper bslProductInfoMapper;
	 @Autowired	 
	 SaleDetailService saleDetailService;
	 
	 @Autowired
	 private JedisClient jedisClient;
	
	 @Value("${REDIS_NEXT_SALE_PLAN_ID}")
	 private String REDIS_NEXT_SALE_PLAN_ID;
	 
	/**
	 * 获取符合条件的销售出库通知单
	 */
	@Override
	public BSLResult getSalePlanService(QueryCriteria queryCriteria) {
		 
		//创建查询的实例，并赋值
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询销售出库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_销售出库通知单);
		if(!StringUtils.isBlank(queryCriteria.getBsId())){
			criteria.andBsIdLike("%"+queryCriteria.getBsId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getBsFlag())){
			criteria.andBsFlagEqualTo(queryCriteria.getBsFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsStatus())){
			criteria.andBsStatusEqualTo(queryCriteria.getBsStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsOrderNo())){
			criteria.andBsOrderNoLike("%"+queryCriteria.getBsOrderNo()+"%");
		}
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
			bslBsPlanInfoExample.setOrderByClause("`bs_status` asc,`bs_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslBsPlanInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		if(StringUtils.isBlank(queryCriteria.getUserType()) || 
				(!(queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_管理员)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_总经理)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_副总经理)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_财务人员)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_营销人员)>=0))){
			for (BslBsPlanInfo bslBsPlanInfo : bslBsPlanInfoList) {
				bslBsPlanInfo.setBsPrice(null);
			}
		}
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<BslBsPlanInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"salePlanServiceImpl","getSalePlanService",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 加载界面时查询所有销售出库通知单
	 */
	@Override
	public EasyUIDataGridResult getSalePlanService(Integer page, Integer rows) {
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询销售出库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_销售出库通知单);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslBsPlanInfoExample.setOrderByClause("`bs_status` asc,`bs_id` desc");
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslBsPlanInfoList);
		//取记录总条数
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<>(bslBsPlanInfoList);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("salePlanServiceImpl");
		result.setMethodName("getSalePlanService");
		return result;
	}
	
	/**
	 * 根据条件查询所有非外协厂销售出库通知单信息
	 */
	@Override
	public BSLResult getSalePlanServiceNotWx(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询销售出库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_销售出库通知单);
		List<String> strings = new ArrayList<String>();
		strings.add(DictItemOperation.收发类别_委外仓废品发货);
		strings.add(DictItemOperation.收发类别_委外仓成品发货);
		criteria.andBsFlagNotIn(strings);
		if(!StringUtils.isBlank(queryCriteria.getBsId())){
			criteria.andBsIdLike("%"+queryCriteria.getBsId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getBsFlag())){
			criteria.andBsFlagEqualTo(queryCriteria.getBsFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsStatus())){
			criteria.andBsStatusEqualTo(queryCriteria.getBsStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsOrderNo())){
			criteria.andBsOrderNoLike("%"+queryCriteria.getBsOrderNo()+"%");
		}
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
			bslBsPlanInfoExample.setOrderByClause("`bs_status` asc,`bs_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslBsPlanInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<BslBsPlanInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"salePlanServiceImpl","getSalePlanServiceNotWx",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 新增销售出库通知单
	 */
	@Override
	public BSLResult addSalePlanInfo(BslBsPlanInfo bslBsPlanInfo) {
		bslBsPlanInfo.setBsType(DictItemOperation.收发标志_销售出库通知单);
		bslBsPlanInfo.setBsAmt(0);
		bslBsPlanInfo.setBsRelweight(0f);
		bslBsPlanInfo.setBsJsnum(0);
		bslBsPlanInfo.setBsJsweight(0f);
		bslBsPlanInfo.setBsPrice(0f);
		bslBsPlanInfo.setBsStatus(DictItemOperation.通知单状态_创建);//状态默认为0-创建
		bslBsPlanInfo.setCrtDate(new Date());//创建日期当天
		bslBsPlanInfo.setBsId(createSalePlanId());
		int result = bslBsPlanInfoMapper.insert(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}
	
	/**
	 * 修改销售出库通知单
	 */
	@Override
	public BSLResult updateSalePlanInfo(BslBsPlanInfo bslBsPlanInfo) {
		String bsId = bslBsPlanInfo.getBsId();
		//修改之前先校验状态是不是创建或进行中
		BslBsPlanInfo bslSelectPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslSelectPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到入库通知单信息");
		}
		//已发货的不允许修改
		/*String bsStatus = bslSelectPlanInfo.getBsStatus();
		if(DictItemOperation.通知单状态_已发货.equals(bsStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货的销售出库通知单不允许修改！");
		}*/
		//已经录入详细的不允许修改通知单类别（除了成品发货-成品转场发货互转 废品发货-废品转场发货互转）
		if(!bslSelectPlanInfo.getBsFlag().equals(bslBsPlanInfo.getBsFlag())){
			String bsFlagOld = bslSelectPlanInfo.getBsFlag();
			String bsFlagNew = bslBsPlanInfo.getBsFlag();
			if(!((DictItemOperation.收发类别_废品发货.equals(bsFlagOld) && DictItemOperation.收发类别_废品转场发货.equals(bsFlagNew))
				|| (DictItemOperation.收发类别_废品发货.equals(bsFlagNew) && DictItemOperation.收发类别_废品转场发货.equals(bsFlagOld))
				|| (DictItemOperation.收发类别_成品发货.equals(bsFlagNew) && DictItemOperation.收发类别_成品转场发货.equals(bsFlagOld))
				|| (DictItemOperation.收发类别_成品发货.equals(bsFlagOld) && DictItemOperation.收发类别_成品转场发货.equals(bsFlagNew)))
			)
			{
				List<BslSaleInfoDetail> bslSaleInfoDetails = getSaleDetail(bsId);
				if(bslSaleInfoDetails != null && bslSaleInfoDetails.size()>0){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已经有销售出库详细信息的销售单不允许修改类别！");
				}
			}
		}
		
		//进行修改
		//bslBsPlanInfo.setUpdDate(new Date());//修改日期当天
		int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}		
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}

	/**
	 * 删除销售出库通知单
	 */
	@Override
	public BSLResult deleteSalePlanInfo(BslBsPlanInfo bslBsPlanInfo) {
		String bsId = bslBsPlanInfo.getBsId();
		//先校验状态是不是创建
		BslBsPlanInfo bslBsPlanInfoOld = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售出库通知单信息");
		}
		if(!DictItemOperation.通知单状态_创建.equals(bslBsPlanInfoOld.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"非创建状态的销售出库通知单不允许删除！");
		}
		
		//获取该销售出库通知单下的产品信息
		List<BslSaleInfoDetail> bslSaleInfoDetails = getSaleDetail(bsId);
		if(bslSaleInfoDetails != null && bslSaleInfoDetails.size()>0){
			throw new BSLException(ErrorCodeInfo.错误类型_删除前子数据检查,"该通知单下有录入详细信息，若要删除请先删除详细信息");
		}	
		
		//删除操作
		int result = bslBsPlanInfoMapper.deleteByPrimaryKey(bsId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}

	/**
	 * 根据实体类获取通知单信息
	 */
	@Override
	public BSLResult getSalePlanServiceByBsPlanInfo(BslBsPlanInfoExample bslBsPlanInfoExample) {
		 List<BslBsPlanInfo> selectByExample = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 * 根据单号获取通知单信息
	 */
	@Override
	public BSLResult getSalePlanByBsBsId(String bsId) {
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		return BSLResult.ok(bslBsPlanInfo);
	}

	/**
	 * 产品选择发货
	 */
	@Override
	public BSLResult updateFinishSalePlanInfo(String[] arrays,String prodOutPlan,String checkUser) {
		//根据单号获取该出库单信息
		BslBsPlanInfo bslBsPlanInfoOld = bslBsPlanInfoMapper.selectByPrimaryKey(prodOutPlan);
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售出库通知单信息");
		}
		if(arrays.length <= 0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未选择发货产品信息");
		}
		//开始产品发货
		//非废品发货完成之后将下属的销售出库产品改为4-已发货
		String bsFlag = bslBsPlanInfoOld.getBsFlag();
		if(!DictItemOperation.收发类别_废品发货.equals(bsFlag) && 
				!DictItemOperation.收发类别_委外仓废品发货.equals(bsFlag) &&
					!DictItemOperation.收发类别_废品转场发货.equals(bsFlag)){
			//获取该销售出库通知单下的详细信息
			List<BslSaleInfoDetail> bslSaleInfoDetails = getSaleDetail(prodOutPlan);
			if(bslSaleInfoDetails == null || bslSaleInfoDetails.size()<=0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该发货通知单下无录入详细信息，不能发货");
			}
			BslProductInfo bslProductInfo;
			//对这些产品循环发货
			for (String prodId : arrays) {
				bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
				if(bslProductInfo == null){
					throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查询到该产品信息："+prodId);
				}
				if(bslProductInfo.getProdOutPlan() == null || !bslProductInfo.getProdOutPlan().equals(prodOutPlan)){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该发货通知单下无此产品出库记录，不能发货:"+prodId);
				}
				if(!DictItemOperation.产品状态_出库待发货.equals(bslProductInfo.getProdStatus())){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该产品状态不是出库待发货，不能发货："+prodId);
				}
				bslProductInfo.setProdStatus(DictItemOperation.产品状态_已发货);
				bslProductInfo.setUpdDate(new Date());
				bslProductInfo.setProdOutDate(new Date());
				bslProductInfo.setProdCheckuser(checkUser);
				bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
			}
			//更新销售出库详单状态
			for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
				saleDetailService.updateSaleDetailStatus(bslSaleInfoDetail.getSaleSerno());
			}
		}else{
			//废品发货判断是不是均达到了发货标准
			BslSaleInfoDetail bslSaleInfoDetail;
			for (String saleSerno : arrays) {
				bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
				if(bslSaleInfoDetail == null){
					throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查询到该废品发货详细流水信息："+saleSerno);
				}
				if(bslSaleInfoDetail.getSalePlanId() == null || !bslSaleInfoDetail.getSalePlanId().equals(prodOutPlan)){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该发货通知单下无此发货详细流水，不能发货："+saleSerno);
				}
				if(!DictItemOperation.销售单出库标志_已达标准.equals(bslSaleInfoDetail.getSaleStatus())){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该废品发货详细流水未达发货标准，不能发货："+saleSerno);
				}
				bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已发货);
				bslSaleInfoDetail.setUpdDate(new Date());
				bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
			}
		}
		
		//更新出库单状态与详细流水状态
		updateRefreshSalePlanStatus(prodOutPlan);
		return  BSLResult.ok();
		
	}
	
	/**
	 * 根据单号获取该单号下所有销售详细信息
	 */
	public List<BslSaleInfoDetail> getSaleDetail(String bsId){
		//获取该通知单下属销售单
		//创建销售详细的实体类
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteria = bslSaleInfoDetailExample.createCriteria();
		criteria.andSalePlanIdEqualTo(bsId);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		return bslSaleInfoDetails;
	}
	
	/**
	 * 销售出库流水自动生成编号
	 * S+日期+2位序号
	 * @return
	 */
	public String createSalePlanId() {
		long incr = jedisClient.incr(REDIS_NEXT_SALE_PLAN_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("S%s%02d", sdf.format(new Date()), incr);
		return rawId;
	}

	/**
	 * 根据单号获取该单号下所有销售详细信息，并刷新其状态
	 */
	@Override
	public BSLResult updateRefreshSalePlanStatus(String bsId) {
		//获取原状态
		BslBsPlanInfo bslSaleInfoDetailOld = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslSaleInfoDetailOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售出库通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bsStatus)){
			return BSLResult.ok(bsId);
		}*/
		int saleNum = 0;//记录已发货数
		int outNum = 0;//记录已达销售标准数
		int notOutNum = 0;//记录未达销售标准数
		Float price = 0f;//计算总价
		Float weight = 0f;//计算总重
		int amt = 0;//计算总数
		String status;//记录状态
		if(DictItemOperation.收发类别_待处理品处理发货.equals(bslSaleInfoDetailOld.getBsFlag())){
			//获取因为该指令出库的产品
			BslProductInfoExample selectExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = selectExample.createCriteria();
			//已入库的才能出库
			List<String> lists = new ArrayList<String>();
			lists.add(DictItemOperation.产品状态_出库待发货);
			lists.add(DictItemOperation.产品状态_已发货);
			lists.add(DictItemOperation.产品状态_已转场);
			criteria.andProdStatusIn(lists);
			//产品出库单号等于对应单号
			criteria.andProdOutPlanEqualTo(bsId);
			List<BslProductInfo> prodsBySalePlanId = bslProductInfoMapper.selectByExample(selectExample);
			if(prodsBySalePlanId != null && prodsBySalePlanId.size()>0){
				//已出库数量
				amt = prodsBySalePlanId.size();
				//已出库重量
				for (BslProductInfo bslProductInfo : prodsBySalePlanId) {
					weight += bslProductInfo.getProdRelWeight();
					if(DictItemOperation.产品状态_已发货.equals(bslProductInfo.getProdStatus())){
						saleNum ++;
					}else{
						outNum ++;
					}
				}
				if(outNum == 0){
					status = DictItemOperation.通知单状态_已发货;
				}else{
					if(bslSaleInfoDetailOld.getBsWeight() != null && bslSaleInfoDetailOld.getBsWeight() != 0f){
						Float saleWeightH = bslSaleInfoDetailOld.getBsWeight()*1.05f;
						Float saleWeightL = bslSaleInfoDetailOld.getBsWeight()*0.95f;
						if(weight>=saleWeightL && weight<=saleWeightH){
							status = DictItemOperation.通知单状态_已完成;
						}else{
							status = DictItemOperation.通知单状态_进行中;
						}
					}else{
						status = DictItemOperation.通知单状态_进行中;
					}
				}
			}else{
				status = DictItemOperation.通知单状态_创建;
			}
			bslSaleInfoDetailOld.setBsRelweight(weight);//实际出库重量
			bslSaleInfoDetailOld.setBsAmt(amt);//计划出库总数量
			bslSaleInfoDetailOld.setUpdDate(new Date());			
			bslSaleInfoDetailOld.setBsStatus(status);
			int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslSaleInfoDetailOld);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改出库通知单状态失败");
			}
		}else{
			List<BslSaleInfoDetail> bslSaleInfoDetails = getSaleDetail(bsId);
			for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
				amt += bslSaleInfoDetail.getProdSumnum();
				price += bslSaleInfoDetail.getSalePrice()* bslSaleInfoDetail.getProdSumweight();
				weight += bslSaleInfoDetail.getProdSumweight();
				if(DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())){
					saleNum ++;
				}else if(DictItemOperation.销售单出库标志_已达标准.equals(bslSaleInfoDetail.getSaleStatus())){
					outNum ++;
				}else if(DictItemOperation.销售单出库标志_未达标准.equals(bslSaleInfoDetail.getSaleStatus())){
					notOutNum ++;
				}else{
					
				}
			}
			if(saleNum>0){
				if(outNum > 0){
					if(notOutNum>0){
						status = DictItemOperation.通知单状态_进行中;
					}else{
						status = DictItemOperation.通知单状态_已完成;
					}
				}else{
					if(notOutNum>0){
						status = DictItemOperation.通知单状态_进行中;
					}else{
						status = DictItemOperation.通知单状态_已发货;
					}
				}
			}else{
				if(outNum > 0){
					if(notOutNum>0){
						status = DictItemOperation.通知单状态_进行中;
					}else{
						status = DictItemOperation.通知单状态_已完成;
					}
				}else{
					//获取因为该指令出库的产品
					BslProductInfoExample selectExample = new BslProductInfoExample();
					com.bsl.pojo.BslProductInfoExample.Criteria criteria = selectExample.createCriteria();
					//已入库的才能出库
					List<String> lists = new ArrayList<String>();
					lists.add(DictItemOperation.产品状态_出库待发货);
					lists.add(DictItemOperation.产品状态_已发货);
					criteria.andProdStatusIn(lists);
					//产品出库单号等于对应单号
					criteria.andProdOutPlanEqualTo(bsId);
					List<BslProductInfo> prodsBySalePlanId = bslProductInfoMapper.selectByExample(selectExample);
					if(prodsBySalePlanId != null && prodsBySalePlanId.size()>0){
						status = DictItemOperation.通知单状态_进行中;
					}else{
						status = DictItemOperation.通知单状态_创建;
					}
				}
			}
			
			//去两位小数
			price = ((float)Math.round(price*100))/100;
			bslSaleInfoDetailOld.setBsPrice(price);//实际出库价格
			bslSaleInfoDetailOld.setBsRelweight(weight);//实际出库重量
			bslSaleInfoDetailOld.setBsAmt(amt);//计划出库总数量
			bslSaleInfoDetailOld.setUpdDate(new Date());			
			bslSaleInfoDetailOld.setBsStatus(status);
			int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslSaleInfoDetailOld);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改出库通知单状态失败");
			}
		}
		
		return BSLResult.ok(bsId);
	}

	/**
	 * 根据单号id查询销售出库通知单详细信息
	 */
	@Override
	public BSLResult getSalePlanDetailByBsBsId(String bsId) {
		List<BslSaleInfoDetail> saleDetail = getSaleDetail(bsId);
		return BSLResult.ok(saleDetail);
	}

	/**
	 * 根据单号记录复磅重量
	 */
	@Override
	public BSLResult saleFbAll(String bsId, Float bsFbweight) {
		BslBsPlanInfo bslBsPlanInfo = new BslBsPlanInfo();
		bslBsPlanInfo.setBsId(bsId);
		bslBsPlanInfo.setBsFbweight(bsFbweight);
		int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"记录复磅重量失败");
		}
		return BSLResult.ok(bsId);
	}

	/**
	 * 根据单号查询已经出库重量
	 */
	@Override
	public BSLResult getOutWeight(String bsId) {
		 List<BslSaleInfoDetail> bslSaleInfoDetails =  getSaleDetail(bsId);
		 Float weight = 0f;
		 for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
			 if(bslSaleInfoDetail.getProdSumweight() != null){
				 weight += bslSaleInfoDetail.getProdSumweight();
			 }
		 }
		 return BSLResult.ok(weight);
	}

	/**
	 * 销售出库通知单强制完成
	 */
	@Override
	public BSLResult updateQZFinishSalePlanInfo(String bsId, String checkUser) {
		//根据单号获取该出库单信息，判断状态是不是已发货
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售出库通知单信息");
		}
		if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货的销售出库通知单信息无需维护");
		}
		//校验因为该通知单出库的产品是不是都是已发货
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdOutPlanEqualTo(bsId);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(bslProductInfos == null || bslProductInfos.size()<=0){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"没有因为该通知单出库的产品信息");
		}
		//开始校验
		for (BslProductInfo bslProductInfo : bslProductInfos) {
			if(!DictItemOperation.产品状态_已发货.equals(bslProductInfo.getProdStatus())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该通知单下出库的产品未完全发货，请先发货或者退库");
			}
		}
		//校验完成开始修改
		//1-修改该通知单下详细信息
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteriaSale = bslSaleInfoDetailExample.createCriteria();
		criteriaSale.andSalePlanIdEqualTo(bsId);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		if(bslSaleInfoDetails != null && bslSaleInfoDetails.size()>0){
			//2-循环更新详细的状态
			for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
				if(!DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())){
					bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已发货);
					bslSaleInfoDetail.setSaleNum(bslSaleInfoDetail.getProdSumnum());
					bslSaleInfoDetail.setSaleWeight(bslSaleInfoDetail.getProdSumweight());
					bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
				}
			}
		}
		//3-更新发货通知单信息
		bslBsPlanInfo.setBsStatus(DictItemOperation.通知单状态_已发货);
		bslBsPlanInfo.setUpdDate(new Date());
		int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"强制完成失败");
		}
		
		return BSLResult.ok(bsId);
	}

	/**
	 * 根据产品发货车号获取销售出库单信息
	 */
	@Override
	public BslBsPlanInfo getSalePlanByCarno(String carno) {
		//查询某一产品信息
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdOutCarnoEqualTo(carno);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(bslProductInfos == null || bslProductInfos.size()<=0){
			return null;
		}
		BslProductInfo bslProductInfo = bslProductInfos.get(0);
		//获取出库单号
		String prodOutPlan = bslProductInfo.getProdOutPlan();
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(prodOutPlan);
		return bslBsPlanInfo;
	}

	/**
	 * 刷新出库数量和重量
	 */
	@Override
	public BSLResult updateReloadSalePlanDetail(String bsId) {
		//根据单号获取该出库单信息，判断状态是不是已发货
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售出库通知单信息");
		}
		int sumNumPlan = 0;
		Float sumWeightPlan = 0f;
		//如果是待处理品发货
		if(DictItemOperation.收发类别_待处理品处理发货.equals(bslBsPlanInfo.getBsFlag())){
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(bsId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()>0){
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					sumWeightPlan += bslProductInfo.getProdRelWeight();
				}
				sumNumPlan = bslProductInfos.size();
			}
		}else{
			//校验完成开始修改
			//1-修改该通知单下详细信息
			BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
			com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteriaSale = bslSaleInfoDetailExample.createCriteria();
			criteriaSale.andSalePlanIdEqualTo(bsId);
			List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
			if(bslSaleInfoDetails == null || bslSaleInfoDetails.size()<=0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该通知单下未查询到详细流水");
			}
			//2-循环更新详细的状态
			for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
				BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
				com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
				criteria.andProdSaleSernoEqualTo(bslSaleInfoDetail.getSaleSerno());
				List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
				if(bslProductInfos == null || bslProductInfos.size()<=0){
					bslSaleInfoDetail.setProdSumnum(0);
					bslSaleInfoDetail.setProdSumweight(0f);
				}else{
					Float sumWeight = 0f;
					for (BslProductInfo bslProductInfo : bslProductInfos) {
						sumWeight += bslProductInfo.getProdRelWeight();
					}
					sumNumPlan += bslProductInfos.size();
					sumWeightPlan += sumWeight;
					bslSaleInfoDetail.setProdSumnum(bslProductInfos.size());
					bslSaleInfoDetail.setProdSumweight(sumWeight);
				}
				bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
			}
		}
		bslBsPlanInfo.setBsAmt(sumNumPlan);
		bslBsPlanInfo.setBsRelweight(sumWeightPlan);
		bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		return BSLResult.ok(bsId);
	}

	/**
	 * 根据条件查询所有外协厂销售出库通知单信息
	 */
	@Override
	public BSLResult getSalePlanServiceWx(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询销售出库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_销售出库通知单);
		List<String> strings = new ArrayList<String>();
		strings.add(DictItemOperation.收发类别_委外仓废品发货);
		strings.add(DictItemOperation.收发类别_委外仓成品发货);
		criteria.andBsFlagIn(strings);
		if(!StringUtils.isBlank(queryCriteria.getBsId())){
			criteria.andBsIdLike("%"+queryCriteria.getBsId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getBsFlag())){
			criteria.andBsFlagEqualTo(queryCriteria.getBsFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsStatus())){
			criteria.andBsStatusEqualTo(queryCriteria.getBsStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsOrderNo())){
			criteria.andBsOrderNoLike("%"+queryCriteria.getBsOrderNo()+"%");
		}
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
			bslBsPlanInfoExample.setOrderByClause("`bs_status` asc,`bs_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslBsPlanInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<BslBsPlanInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"salePlanServiceImpl","getSalePlanServiceWx",pageInfo.getTotal(),bslBsPlanInfoList);
	}

}
