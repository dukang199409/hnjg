package com.bsl.service.prodmanager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslMakePlanInfoDetailMapper;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoDetailExample;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.pojo.BslMakePlanInfoExample.Criteria;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.reportbean.BslMakePlanInfoDetailPro;
import com.bsl.reportbean.BslMakePlanInfoPro;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.prodmanager.ProdPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 产品生产指令管理
 * duk-20190319
 */
@Service
public class ProdPlanServiceImpl implements ProdPlanService {

	@Autowired	 
	BslMakePlanInfoMapper bslMakePlanInfoMapper;
	@Autowired	 
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired	 
	BslMakePlanInfoDetailMapper bslMakePlanInfoDetailMapper;
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_PROD_PLAN_ID_KEY}")
	private String REDIS_NEXT_PROD_PLAN_ID;
	
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
	 * 产品调度指令自动生成编号
	 * X+日期+2位序号
	 * @return
	 */
	public String createRawId() {
		long incr = jedisClient.incr(REDIS_NEXT_PROD_PLAN_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("X%s%02d", sdf.format(new Date()), incr);
		return rawId;
	}
	
	/**
	 * 初始化查询所有生产计划指令信息
	 */
	@Override
	public EasyUIDataGridResult getProdPlanService(Integer page, Integer rows) {
		//查询条件1-产品生产指令
		BslMakePlanInfoExample bslMakePlanInfoExample = new BslMakePlanInfoExample();
		Criteria criteria = bslMakePlanInfoExample.createCriteria();
		criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_成品生产指令);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslMakePlanInfoExample.setOrderByClause("`plan_id` desc");
		List<BslMakePlanInfo> bslMakePlanInfos = bslMakePlanInfoMapper.selectByExample(bslMakePlanInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslMakePlanInfos);
		//取记录总条数
		PageInfo<BslMakePlanInfo> pageInfo = new PageInfo<>(bslMakePlanInfos);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("prodPlanServiceImpl");
		result.setMethodName("getProdPlanService");
		return result;
	}

	/**
	 * 获取符合条件的生产计划指令信息
	 */
	@Override
	public BSLResult getProdPlanService(QueryCriteria queryCriteria) {
		//查询条件1-产品生产指令
		queryCriteria.setPlanFlag(DictItemOperation.指令类别_成品生产指令);
		if (!StringUtils.isBlank(queryCriteria.getPlanId())) {
			queryCriteria.setPlanId(StringUtil.likeStr(queryCriteria.getPlanId()));
		}else{
			queryCriteria.setPlanId(null);
		}
		if (!StringUtils.isBlank(queryCriteria.getProdType())) {
			queryCriteria.setProdType(queryCriteria.getProdType());
		}else{
			queryCriteria.setProdType(null);
		}
		if(!StringUtils.isBlank(queryCriteria.getMakeName())){
			queryCriteria.setMakeName(StringUtil.likeStr(queryCriteria.getMakeName()));
		}else{
			queryCriteria.setMakeName(null);
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			queryCriteria.setProdNorm(StringUtil.likeStr(queryCriteria.getProdNorm()));
		}else{
			queryCriteria.setProdNorm(null);
		}
		if(!StringUtils.isBlank(queryCriteria.getMakeProdNorm())){
			queryCriteria.setMakeProdNorm("%"+queryCriteria.getMakeProdNorm()+"%");
		}else{
			queryCriteria.setMakeProdNorm(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdMaterial())){
			queryCriteria.setProdMaterial(null);
		}
		if (StringUtils.isBlank(queryCriteria.getPlanStatus())) {
			queryCriteria.setPlanStatus(null);
		}
		if (StringUtils.isBlank(queryCriteria.getPlanJz())) {
			queryCriteria.setPlanJz(null);
		}
		//起始日期 结束日期
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
			queryCriteria.setOrderByClause("`plan_status` asc,`plan_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslMakePlanInfoPro> bslMakePlanInfos = bslMakePlanInfoMapper.selectByExampleCCrate(queryCriteria);
		PageInfo<BslMakePlanInfoPro> pageInfo = new PageInfo<BslMakePlanInfoPro>(bslMakePlanInfos);
		return BSLResult.ok(bslMakePlanInfos,"prodPlanServiceImpl","getProdPlanService",pageInfo.getTotal(),bslMakePlanInfos);
		
	}

	/**
	 * 根据实体类查询所有生产计划指令信息
	 */
	@Override
	public BSLResult getProdPlanByMakePlanInfoService(BslMakePlanInfoExample bslMakePlanInfoExample) {
		 List<BslMakePlanInfo> selectByExample = bslMakePlanInfoMapper.selectByExample(bslMakePlanInfoExample);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 * 新增生产计划指令信息
	 */
	@Override
	public BSLResult addProdPlanInfo(BslMakePlanInfo bslMakePlanInfo) {
		//设置插入参数
		bslMakePlanInfo.setCrtDate(new Date());//创建日期当天
		bslMakePlanInfo.setPlanFlag(DictItemOperation.指令类别_成品生产指令);
		bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_创建);
		bslMakePlanInfo.setPlanId(createRawId());//自动生成指令号X+日期+2位序号
		
		int result = bslMakePlanInfoMapper.insert(bslMakePlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslMakePlanInfo.getPlanId());
	}
	
	/**
	 * 修改生产计划指令信息
	 */
	@Override
	public BSLResult updateProdPlanInfo(BslMakePlanInfo bslMakePlanInfo) {
		//根据指令号获取原指令信息
		BslMakePlanInfo bslBsPlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfo.getPlanId());
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令号查询记录为空");
		}
		Boolean flag = false;
		String errorMsg = "";
		//校验状态
//		if(!bslBsPlanInfoOld.getPlanLuno().equals(bslMakePlanInfo.getPlanLuno())){
//			flag = true;
//			errorMsg = "非创建中/进行中/暂停的生产指令不允许修改用料炉号！";
//		}
		if(!bslBsPlanInfoOld.getProdMaterial().equals(bslMakePlanInfo.getProdMaterial())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的生产指令不允许修改用料钢种！";
		}
		if(!bslBsPlanInfoOld.getProdNorm().equals(bslMakePlanInfo.getProdNorm())){
			errorMsg = "非创建中/进行中/暂停的的生产指令不允许修改用料规格！";
		}
		if(!bslBsPlanInfoOld.getMakeName().equals(bslMakePlanInfo.getMakeName())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的生产指令不允许修改制造产品名称！";
		}
		if(!bslBsPlanInfoOld.getMakeName().equals(bslMakePlanInfo.getMakeType())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的生产指令不允许修改制造产品规格！";
		}
		if(!bslBsPlanInfoOld.getPlanJz().equals(bslMakePlanInfo.getPlanJz())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的生产指令不允许修改产品制造机组！";
		}
		if(flag && !DictItemOperation.指令状态_创建.equals(bslBsPlanInfoOld.getPlanStatus()) 
				&& !DictItemOperation.指令状态_进行中.equals(bslBsPlanInfoOld.getPlanStatus())
				&& !DictItemOperation.指令状态_暂停.equals(bslBsPlanInfoOld.getPlanStatus())){

			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, errorMsg);
		}

		int result = bslMakePlanInfoMapper.updateByPrimaryKeySelective(bslMakePlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		if(flag){
			//如果修改了以上限制内容，则会把对应在库的产品属性刷掉
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
			criteria.andProdPlanNoEqualTo(bslMakePlanInfo.getPlanId());
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()>0){
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					bslProductInfo.setUpdDate(new Date());
					bslProductInfo.setProdMaterial(bslMakePlanInfo.getProdMaterial());
					//bslProductInfo.setProdLuno(bslMakePlanInfo.getPlanLuno());
					bslProductInfo.setProdNorm(bslMakePlanInfo.getMakeProdNorm());
					bslProductInfo.setProdName(bslMakePlanInfo.getMakeName());
					int resultUpd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
					if(resultUpd<0){
						throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
					}else if(resultUpd==0){
						throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
					}
				}
			}
		}
		return BSLResult.ok(bslMakePlanInfo.getPlanId());
	}

	/**
	 * 删除生产计划指令信息
	 */
	@Override
	public BSLResult deleteProdPlanInfo(BslMakePlanInfo bslMakePlanInfo) {
		//根据指令号获取原指令信息
		BslMakePlanInfo bslBsPlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(bslMakePlanInfo.getPlanId());
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令号查询记录为空");
		}
		//校验状态非创建的不允许删除
		if(!DictItemOperation.指令状态_创建.equals(bslBsPlanInfoOld.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有创建中的产品生产指令才允许删除");
		}
		//校验该指令下不存在调度计划
		BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
		com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteria = bslMakePlanInfoDetailExample.createCriteria();
		criteria.andPlanIdEqualTo(bslMakePlanInfo.getPlanId());
		List<BslMakePlanInfoDetail> bslMakePlanInfoDetais = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		if(bslMakePlanInfoDetais.size()>0){
			throw new BSLException(ErrorCodeInfo.错误类型_删除前子数据检查,"该指令下有详细调度信息，若要删除请先删除调度信息");
		}
		//删除操作
		int result = bslMakePlanInfoMapper.deleteByPrimaryKey(bslMakePlanInfo.getPlanId());
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(bslMakePlanInfo.getPlanId());
	}

	/**
	 * 状态修改生产计划指令信息
	 */
	@Override
	public BSLResult updateProdPlanInfoStatus(BslMakePlanInfo bslMakePlanInfo, Integer statusInt) {
		String planId = bslMakePlanInfo.getPlanId();
		//根据指令号获取原指令信息
		BslMakePlanInfo bslBsPlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(planId);
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令号查询记录为空");
		}
		String status = bslBsPlanInfoOld.getPlanStatus();
		String planJz = bslBsPlanInfoOld.getPlanJz();
				
		if(statusInt == 1){
			/**
			 * 点击执行按钮
			 */
			//校验原状态
			if(DictItemOperation.产品状态_已完成.equals(status) ||DictItemOperation.指令状态_强制终止.equals(status)){
				if(!"000000".equals(bslMakePlanInfo.getInputuser())){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"只有超级管理员才允许把已完成/终止的指令重新执行!");
				}
			}
			//校验现在是不是有正在执行的指令
			//创建产品指令查询实例
			BslMakePlanInfoExample bslMakePlanInfoExample = new BslMakePlanInfoExample();
			Criteria criteria = bslMakePlanInfoExample.createCriteria();
			criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_成品生产指令);
			//查询条件1-产品生产指令 状态1-进行中 机组-当前机组
			criteria.andPlanStatusEqualTo(DictItemOperation.指令状态_进行中);
			criteria.andPlanJzEqualTo(planJz);
			List<BslMakePlanInfo> executePlans = bslMakePlanInfoMapper.selectByExample(bslMakePlanInfoExample);
			if(executePlans!= null && executePlans.size()>0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"同一时间同一机组只能有一条正在执行的产品调度指令！");
			}
			//创建产品指令详细查询实列
			BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
			com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteriaDetail = bslMakePlanInfoDetailExample.createCriteria();
			criteriaDetail.andPlanIdEqualTo(planId);
			List<BslMakePlanInfoDetail> executePlanDetails = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
			if(executePlanDetails == null || executePlanDetails.size()<=0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该产品调度指令下没有详细调度信息，无法执行！");
			}
			//校验完成，开始执行
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_进行中);
		}else if(statusInt==2){
			/**
			 * 点击暂停按钮
			 */
			//校验原状态
			if(!(DictItemOperation.指令状态_进行中.equals(status))){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"只有执行中的指令才能暂停!");
			}
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_暂停);
		}else if(statusInt==3){
			/**
			 * 点击完成按钮
			 */
			//校验原状态
			if(!(DictItemOperation.指令状态_进行中.equals(status))){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"只有执行中的指令才能完成!");
			}
			//查询因为这个指令出库的半成品信息,把已出库的半成品状态改为已完成
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()>0){
				//创建产品修改实例
				BslProductInfo bslProductInfoUpd = new BslProductInfo();
				bslProductInfoUpd.setProdStatus(DictItemOperation.产品状态_已完成);
				bslProductInfoUpd.setUpdDate(new Date());
				bslProductInfoUpd.setProdOutPlan(planId);
				//创建库存变动实例
				BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
				bslStockChangeDetail.setPlanSerno(planId);//当时的生产指令单号
				bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
				bslStockChangeDetail.setProdType(DictItemOperation.产品类型_半成品);//产品类型
				bslStockChangeDetail.setCrtDate(new Date());
				
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					if(DictItemOperation.产品状态_已出库.equals(bslProductInfo.getProdStatus())){
						bslProductInfoUpd.setProdId(bslProductInfo.getProdId());
						bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoUpd);
						
						//记录库存变动流水（出库->完成）
						bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
						bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
						bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
						bslStockChangeDetail.setInputuser(bslMakePlanInfo.getInputuser());//录入人
						bslStockChangeDetailMapper.insert(bslStockChangeDetail);
					}
				}
			}
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_已完成);
		}else if(statusInt==4){
			/**
			 * 点击强制终止按钮
			 */
			//校验原状态
			if(!(DictItemOperation.指令状态_进行中.equals(status))){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"只有执行中的指令才能终止!");
			}
			//查询因为这个指令出库的半成品信息,把已出库的半成品状态改为已完成
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()>0){
				//创建产品修改实例
				BslProductInfo bslProductInfoUpd = new BslProductInfo();
				bslProductInfoUpd.setProdStatus(DictItemOperation.产品状态_已完成);
				bslProductInfoUpd.setUpdDate(new Date());
				bslProductInfoUpd.setProdOutPlan(planId);
				//创建库存变动实例
				BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
				bslStockChangeDetail.setPlanSerno(planId);//当时的生产指令单号
				bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
				bslStockChangeDetail.setProdType(DictItemOperation.产品类型_半成品);//产品类型
				bslStockChangeDetail.setCrtDate(new Date());
				
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					if(DictItemOperation.产品状态_已出库.equals(bslProductInfo.getProdStatus())){
						bslProductInfoUpd.setProdId(bslProductInfo.getProdId());
						bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoUpd);
						
						//记录库存变动流水（出库->完成）
						bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
						bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
						bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
						bslStockChangeDetail.setInputuser(bslMakePlanInfo.getInputuser());//录入人
						bslStockChangeDetailMapper.insert(bslStockChangeDetail);
					}
				}
			}
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_强制终止);
		}else if(statusInt==5){
			/**
			 * 撤销执行按钮
			 */
			//校验原状态
			if(!(DictItemOperation.指令状态_进行中.equals(status) || DictItemOperation.指令状态_暂停.equals(status))){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"只有执行中/暂停的指令才能撤销执行!");
			}
			//查询因为这个指令出库的纵剪带信息,没有才能撤销执行
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()> 0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"没有因为该指令出库的纵剪带才能撤销执行!");
			}
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_创建);
		}
		//开始修改
		int result = bslMakePlanInfoMapper.updateByPrimaryKeySelective(bslMakePlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		return BSLResult.ok(planId);
	}

	/**
	 * 获取指定机组正在执行中的生产指令信息
	 */
	@Override
	public BslMakePlanInfo getProdPlanInfoExe(String jz) {
		BslMakePlanInfoExample prodPlanInfoExample = new BslMakePlanInfoExample();
		com.bsl.pojo.BslMakePlanInfoExample.Criteria criteria = prodPlanInfoExample.createCriteria();
		criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_成品生产指令);
		criteria.andPlanStatusEqualTo(DictItemOperation.指令状态_进行中);
		criteria.andPlanJzEqualTo(jz);
		List<BslMakePlanInfo> prodPlanInfos = bslMakePlanInfoMapper.selectByExample(prodPlanInfoExample);
		if(prodPlanInfos == null || prodPlanInfos.size()<=0){
			return null;
		}else{
			return prodPlanInfos.get(0);
		}
		
	}
	
	/**
	 * 根据生产指令id获取详细调度信息
	 */
	@Override
	public List<BslMakePlanInfoDetailPro> getProdPlanInfoDetailPro(String planId) {
		QueryCriteria example = new QueryCriteria();
		example.setPlanId(planId);
		List<BslMakePlanInfoDetailPro> bslMakePlanInfoDetails = bslMakePlanInfoDetailMapper.selectMakePlanDetailPro(example);
		return bslMakePlanInfoDetails;
	}

	@Override
	public BslMakePlanInfo getProdPlanInfo(String planId) {
		return bslMakePlanInfoMapper.selectByPrimaryKey(planId);
	}

	//查询指令出库的纵剪带（按照质量降序排列）
	@Override
	public BSLResult getHalfProdsByPlanId(String planId) {
		BslProductInfoExample example = new BslProductInfoExample();
		//只查询出已入库和已出库的记录
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = example.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
		criteria.andProdOutPlanEqualTo(planId);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(example);
		return  BSLResult.ok(bslProductInfos);
	}
	
	/**
	 * 根据盘号获取该盘已经入库的包数和重量
	 */
	@Override
	public BSLResult updateProdRuNumAndSums(String planId) {
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdPlanNoEqualTo(planId);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
		List<BslProductInfo> prods = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(prods != null && prods.size()>=0){
			int num = prods.size();
			Float sumW = 0f;
			for (BslProductInfo bslProductInfo : prods) {
				sumW += bslProductInfo.getProdRelWeight();
			}
			BslMakePlanInfo bslMakePlanInfo = bslMakePlanInfoMapper.selectByPrimaryKey(planId);
			bslMakePlanInfo.setAlreadyNum(num);
			bslMakePlanInfo.setAlreadySum(sumW);
			bslMakePlanInfoMapper.updateByPrimaryKeySelective(bslMakePlanInfo);
		}
		return BSLResult.ok(planId);
	}
	
	/**
	 * 获取详细信息
	 */
	@Override
	public List<BslMakePlanInfoDetail> getProdPlanInfoDetail(String planId) {
		BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
		com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteria = bslMakePlanInfoDetailExample.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		List<BslMakePlanInfoDetail> bslMakePlanInfoDetais = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		return bslMakePlanInfoDetais;
	}

}
