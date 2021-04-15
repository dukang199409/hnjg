package com.bsl.service.plan.impl;

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
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 纵剪带详细计划管理
 */
@Service
public class MakePlanDetailServiceImpl implements MakePlanDetailService {

	@Autowired
	private BslMakePlanInfoDetailMapper bslMakePlanInfoDetailMapper;
	
	@Autowired
	private BslMakePlanInfoMapper bslMakePlanInfoMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_PLAN_DETAIL_ID}")
	private String REDIS_NEXT_PLAN_DETAIL_ID;
	
	/**
	 * 查询所有调度计划信息
	 */
	@Override
	public EasyUIDataGridResult getMakePlanInfoDetailList(int page, int rows) {
		DictItemOperation.log.info("===========getMakePlanInfoDetailList开始");
		// 查询商品列表
		BslMakePlanInfoDetailExample example = new BslMakePlanInfoDetailExample();
		Criteria criteria = example.createCriteria();
		criteria.andPlanInfoDetailIdLike("Z%");
		example.setOrderByClause("`plan_info_detail_id` desc");
		// 分页处理
		PageHelper.startPage(page, rows);
		List<BslMakePlanInfoDetail> list = bslMakePlanInfoDetailMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<BslMakePlanInfoDetail> pageInfo = new PageInfo<BslMakePlanInfoDetail>(list);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("makePlanDetailServiceImpl");//类名
		result.setMethodName("queryMakePlanInfoDetailList");
		DictItemOperation.log.info("===========查询成功============");
		return result;
	}

	/**
	 * 根据条件查询所有调度计划信息
	 */
	@Override
	public BSLResult queryMakePlanInfoDetailList(QueryExample queryCriteria) {
		DictItemOperation.log.info("===========queryMakePlanInfoDetailList开始");
		DictItemOperation.log.info("===========查询参数："+queryCriteria);
		BslMakePlanInfoDetailExample example = new BslMakePlanInfoDetailExample();
		if (queryCriteria != null) {
			Criteria criteria = example.createCriteria();
			criteria.andPlanInfoDetailIdLike("Z%");
			//半成品生产批号
			if (!StringUtils.isBlank(queryCriteria.getPlanId())) {
				criteria.andPlanIdLike(StringUtil.likeStr(queryCriteria.getPlanId()));
			}
			//成品生产批号详细调度编号
			if (!StringUtils.isBlank(queryCriteria.getPlanInfoDetailId())) {
				criteria.andPlanInfoDetailIdLike(StringUtil.likeStr(queryCriteria.getPlanInfoDetailId()));
			}
			//规格
			if (!StringUtils.isBlank(queryCriteria.getProdNorm())) {
				criteria.andProdNormLike(StringUtil.likeStr(queryCriteria.getProdNorm()));
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
			if(!StringUtils.isBlank(queryCriteria.getPage()) && !StringUtils.isBlank(queryCriteria.getRows())) {
				//分页处理
				PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
			}
			if(!StringUtils.isBlank(queryCriteria.getSort()) && !StringUtils.isBlank(queryCriteria.getOrder())) {
				String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
				if(!StringUtils.isBlank(sortSql)){
					example.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
				}
			}else{
				example.setOrderByClause("`plan_info_detail_id` desc");
			}
		}
		List<BslMakePlanInfoDetail> list = bslMakePlanInfoDetailMapper.selectByExample(example);
		PageInfo<BslMakePlanInfoDetail> pageInfo = new PageInfo<BslMakePlanInfoDetail>(list);
		DictItemOperation.log.info("===========查询成功============");
		return BSLResult.ok(list,"makePlanDetailServiceImpl","queryMakePlanInfoDetailList",pageInfo.getTotal(),list);
	}
	
	/**
	 *新增指令详细调度信息
	 */
	@Override
	public BSLResult add(BslMakePlanInfoDetail bslMakePlanInfoDetail) {
		DictItemOperation.log.info("===========BslMakePlanInfoDetail开始");
		DictItemOperation.log.info("===========入参："+bslMakePlanInfoDetail);
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
		//根据指令号查询原指令信息
		if(bslMakePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应纵剪带指令号查询记录为空！");
		}
		if(DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) || DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已完成或者已终止的指令不能再增加详细调度信息！");
		}
		bslMakePlanInfoDetail.setCrtDate(new Date());
		bslMakePlanInfoDetail.setPlanInfoDetailId(createDetailId(bslMakePlanInfoDetail.getPlanId()));
		int result = bslMakePlanInfoDetailMapper.insert(bslMakePlanInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		DictItemOperation.log.info("===========新增完成，编号："+bslMakePlanInfoDetail.getPlanInfoDetailId());
		return BSLResult.ok(bslMakePlanInfoDetail.getPlanInfoDetailId());
	}

	/**
	 *修改指令详细调度信息
	 */
	@Override
	public BSLResult update(BslMakePlanInfoDetail bslMakePlanInfoDetail) {
		DictItemOperation.log.info("===========update开始");
		DictItemOperation.log.info("===========入参："+bslMakePlanInfoDetail);
		//根据纵剪带指令号查询原指令信息
		BslMakePlanInfo bslMakePlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
		if(bslMakePlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应产品生产指令号查询记录为空！");
		}
		/*if(!DictItemOperation.指令状态_创建.equals(bslMakePlanInfoOld.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有正在创建中的纵剪带生产指令才允许修改详细调度信息！");
		}*/
		//根据纵剪带指令详细调度号查询原调度信息
		BslMakePlanInfoDetail bslMakePlanInfoDetailOld = bslMakePlanInfoDetailMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanInfoDetailId());
		if(bslMakePlanInfoDetailOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令详细调度号查询记录为空！");
		}
		//如果对应修改了指令号
		if(!bslMakePlanInfoDetailOld.getPlanId().equals(bslMakePlanInfoDetail.getPlanId())){
			//判断修改后指令号对应的状态
			BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetail.getPlanId());
			if(bslMakePlanInfo == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应纵剪带生产指令号查询记录为空！");
			}
			if(DictItemOperation.指令状态_已完成.equals(bslMakePlanInfo.getPlanStatus()) || DictItemOperation.指令状态_强制终止.equals(bslMakePlanInfo.getPlanStatus())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已完成或者已终止的指令不能再增加详细调度信息！");
			}
		}
		DictItemOperation.log.info("===========校验完成===========");
		int result = bslMakePlanInfoDetailMapper.updateByPrimaryKeySelective(bslMakePlanInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		DictItemOperation.log.info("===========插入完成===========");
		return BSLResult.ok(bslMakePlanInfoDetail.getPlanInfoDetailId());
	}

	/**
	 *删除指令详细调度信息
	 */
	@Override
	public BSLResult delete(String planInfoDetailId) {
		DictItemOperation.log.info("===========delete开始");
		DictItemOperation.log.info("===========入参："+planInfoDetailId);
		//根据纵剪带指令号查询原指令信息
		BslMakePlanInfoDetail bslMakePlanInfoDetailSelect = bslMakePlanInfoDetailMapper.selectByPrimaryKey(planInfoDetailId);
		if(bslMakePlanInfoDetailSelect == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应纵剪带生产指令详细调度编号查询记录为空！");
		}
		//根据纵剪带指令号查询原指令信息
		BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfoDetailSelect.getPlanId());
		if(bslMakePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应纵剪带生产指令号查询记录为空！");
		}
		if(!DictItemOperation.指令状态_创建.equals(bslMakePlanInfo.getPlanStatus()) &&
			!DictItemOperation.指令状态_进行中.equals(bslMakePlanInfo.getPlanStatus()) &&
			!DictItemOperation.指令状态_暂停.equals(bslMakePlanInfo.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "非创建/进行中/暂停纵剪带生产指令不能删除详细调度信息！");
		}
		DictItemOperation.log.info("===========校验完成===========");
		//删除操作
		int result = bslMakePlanInfoDetailMapper.deleteByPrimaryKey(planInfoDetailId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		DictItemOperation.log.info("===========删除完成===========");
		return BSLResult.ok(planInfoDetailId);
	}

	/**
	 * 产品调度指令详细自动生成编号
	 * 指令号+_PD+2位序号
	 * @return
	 */
	public String createDetailId(String planId) {
		long incr = jedisClient.incr(REDIS_NEXT_PLAN_DETAIL_ID);
		String rawId = String.format(planId+"%s%02d", "_PD", incr);
		return rawId;
	}

}
