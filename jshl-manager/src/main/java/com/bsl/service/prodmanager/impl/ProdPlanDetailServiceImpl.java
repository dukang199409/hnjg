package com.bsl.service.prodmanager.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslMakePlanInfoDetailMapper;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoDetailExample;
import com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria;
import com.bsl.pojo.BslMakePlanInfoDetailJoinInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.ProdPlanDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 产品生产指令调度详细信息管理
 * duk-20190319
 */
@Service
public class ProdPlanDetailServiceImpl implements ProdPlanDetailService {

	@Autowired	 
	BslMakePlanInfoDetailMapper bslMakePlanInfoDetailMapper;
	@Autowired	 
	BslMakePlanInfoMapper bslMakePlanInfoMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_PLAN_DETAIL_ID}")
	private String REDIS_NEXT_PLAN_DETAIL_ID;
	
	/**
	 *查询所有指令详细调度信息
	 */
	@Override
	public EasyUIDataGridResult getProdPlanDetailService(Integer page, Integer rows) {
		// 查询调度详细信息列表
		BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
		Criteria criteria = bslMakePlanInfoDetailExample.createCriteria();
		criteria.andPlanInfoDetailIdLike("X%");
		bslMakePlanInfoDetailExample.setOrderByClause("`plan_info_detail_id` desc");
		// 分页处理
		PageHelper.startPage(page, rows);
		List<BslMakePlanInfoDetail> list = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<BslMakePlanInfoDetail> pageInfo = new PageInfo<BslMakePlanInfoDetail>(list);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("prodPlanDetailServiceImpl");
		result.setMethodName("selectMakePlanDetailInfoJoinPlan");
		return result;
	}

	/**
	 *根据条件查询所有指令详细调度信息
	 */
	@Override
	public BSLResult getProdPlanDetailService(QueryCriteria queryCriteria) {
		BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
		Criteria criteria = bslMakePlanInfoDetailExample.createCriteria();
		criteria.andPlanInfoDetailIdLike("X%");
		//成品生产批号
		if (!StringUtils.isBlank(queryCriteria.getPlanId())) {
			criteria.andPlanIdLike(StringUtil.likeStr(queryCriteria.getPlanId()));
		}
		//成品生产批号详细调度编号
		if (!StringUtils.isBlank(queryCriteria.getPlanInfoDetailId())) {
			criteria.andPlanInfoDetailIdLike(StringUtil.likeStr(queryCriteria.getPlanInfoDetailId()));
		}
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
			bslMakePlanInfoDetailExample.setOrderByClause("`plan_info_detail_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslMakePlanInfoDetailExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
		
			}
		}
		List<BslMakePlanInfoDetail> list = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		PageInfo<BslMakePlanInfoDetail> pageInfo = new PageInfo<BslMakePlanInfoDetail>(list);
		return BSLResult.ok(list,"prodPlanDetailServiceImpl","getProdPlanDetailService",pageInfo.getTotal(),list);
	}
	
	/**
	 *根据条件查询所有指令详细调度信息
	 */
	@Override
	public BSLResult selectMakePlanDetailInfoJoinPlan(QueryCriteria queryCriteria) {
		
		if(StringUtils.isBlank(queryCriteria.getPlanId())){
			queryCriteria.setPlanId(null);
		}
		if(StringUtils.isBlank(queryCriteria.getPlanInfoDetailId())){
			queryCriteria.setPlanInfoDetailId(null);
		}
		Date startDate = new Date();
		if(!StringUtils.isBlank(queryCriteria.getStartDate())){
			try {
				startDate = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				startDate = DictItemOperation.日期转换实例.parse("2018-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Date endDate = new Date();
		if(!StringUtils.isBlank(queryCriteria.getEndDate())){
			try {
				endDate = DictItemOperation.日期转换实例.parse(queryCriteria.getEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		queryCriteria.setDateStart(startDate);
		queryCriteria.setDateEnd(endDate);
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			queryCriteria.setOrderByClause("`plan_info_detail_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
		
			}
		}
		List<BslMakePlanInfoDetailJoinInfo> list = bslMakePlanInfoDetailMapper.selectMakePlanDetailInfoJoinPlan(queryCriteria);
		PageInfo<BslMakePlanInfoDetailJoinInfo> pageInfo = new PageInfo<BslMakePlanInfoDetailJoinInfo>(list);
		return BSLResult.ok(list,"prodPlanDetailServiceImpl","selectMakePlanDetailInfoJoinPlan",pageInfo.getTotal(),list);
		
	}


	/**
	 *根据实体类查询所有指令详细调度信息
	 */
	@Override
	public BSLResult getProdPlanByMakePlanInfoDetailService(BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample) {
		 List<BslMakePlanInfoDetail> selectByExample = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 *新增指令详细调度信息
	 */
	@Override
	public BSLResult addProdPlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail) {
		//根据生产指令号查询原指令信息
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
		if(bslMakePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令号查询记录为空！");
		}
		if(DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) || DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已完成或者已终止的指令不能再增加详细调度信息！");
		}
		bslMakePlanInfoDetail.setCrtDate(new Date());
		bslMakePlanInfoDetail.setProdNorm(bslMakePlanInfo.getMakeProdNorm());
		bslMakePlanInfoDetail.setPlanInfoDetailId(createRawId(bslMakePlanInfoDetail.getPlanId()));
		
		int result = bslMakePlanInfoDetailMapper.insert(bslMakePlanInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslMakePlanInfoDetail.getPlanInfoDetailId());
	}

	/**
	 *修改指令详细调度信息
	 */
	@Override
	public BSLResult updateProdPlanInfoDetail(BslMakePlanInfoDetail bslMakePlanInfoDetail) {
		//根据生产指令号查询原指令信息
		BslMakePlanInfo bslMakePlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
		if(bslMakePlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令号查询记录为空！");
		}
		/*if(!DictItemOperation.指令状态_创建.equals(bslMakePlanInfoOld.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有正在创建中的产品生产指令才允许修改详细调度信息！");
		}*/
		
		//根据生产指令详细调度号查询原调度信息
		BslMakePlanInfoDetail bslMakePlanInfoDetailOld = bslMakePlanInfoDetailMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanInfoDetailId());
		if(bslMakePlanInfoDetailOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据生产指令详细调度号查询记录为空！");
		}
		//如果对应修改了指令号
		if(!bslMakePlanInfoDetailOld.getPlanId().equals(bslMakePlanInfoDetail.getPlanId())){
			//判断修改后指令号对应的状态
			BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
			if(bslMakePlanInfo == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令号查询记录为空！");
			}
			if(DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) || DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已完成或者已终止的指令不能再增加详细调度信息！");
			}
			bslMakePlanInfoDetail.setProdNorm(bslMakePlanInfo.getMakeProdNorm());
		}
		int result = bslMakePlanInfoDetailMapper.updateByPrimaryKeySelective(bslMakePlanInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		return BSLResult.ok(bslMakePlanInfoDetail.getPlanInfoDetailId());
	}

	/**
	 *删除指令详细调度信息
	 */
	@Override
	public BSLResult deleteProdPlanInfoDetail(String planInfoDetailId) {
		//根据生产指令号查询原指令信息
		BslMakePlanInfoDetail bslMakePlanInfoDetailSelect = bslMakePlanInfoDetailMapper.selectByPrimaryKey(planInfoDetailId);
		if(bslMakePlanInfoDetailSelect == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令详细调度编号查询记录为空！");
		}
		//根据生产指令号查询原指令信息
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetailSelect.getPlanId());
		if(bslMakePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令号查询记录为空！");
		}
		if(!DictItemOperation.指令状态_创建.equals(bslMakePlanInfo.getPlanStatus()) &&
				!DictItemOperation.指令状态_进行中.equals(bslMakePlanInfo.getPlanStatus()) &&
				!DictItemOperation.指令状态_暂停.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "非创建/进行中/暂停产品生产指令不能删除详细调度信息！");
		}
		//删除操作
		int result = bslMakePlanInfoDetailMapper.deleteByPrimaryKey(planInfoDetailId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(planInfoDetailId);
	}
	
	/**
	 * 产品调度指令详细自动生成编号
	 * 指令号+_PD+2位序号
	 * @return
	 */
	public String createRawId(String planId) {
		long incr = jedisClient.incr(REDIS_NEXT_PLAN_DETAIL_ID);
		String rawId = String.format(planId+"%s%02d", "_PD", incr);
		return rawId;
	}


}
