package com.bsl.service.plan.impl;

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
import com.bsl.mapper.BslMakePlanInfoDetailMapper;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoDetailExample;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.reportbean.BslMakePlanInfoPro;
import com.bsl.pojo.BslMakePlanInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 纵剪带指令管理
 */
@Service
public class MakePlanServiceImpl implements MakePlanService {

	@Autowired
	private BslMakePlanInfoMapper bslMakePlanInfoMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_PLAN_ID_KEY}")
	private String REDIS_NEXT_PLAN_ID_KEY;
	@Autowired
	private BslMakePlanInfoDetailMapper bslMakePlanInfoDetailMapper;
	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 生成半成品生产批号
	 * Z+日期+2位序号
	 * @return
	 */
	public String createPlanId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long incr = jedisClient.incr(REDIS_NEXT_PLAN_ID_KEY);
		
		String planId = String.format("Z%s%02d", sdf.format(new Date()), incr);
		return planId;
	}
	
	/**
	 * 初始化查询所有纵剪带生产指令
	 */
	@Override
	public EasyUIDataGridResult getMakePlanInfoList(int page, int rows) {
		// 查询商品列表
		BslMakePlanInfoExample example = new BslMakePlanInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_半成品生产指令);
		// 分页处理
		PageHelper.startPage(page, rows);
		example.setOrderByClause("`plan_id` desc");
		List<BslMakePlanInfo> list = bslMakePlanInfoMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<BslMakePlanInfo> pageInfo = new PageInfo<BslMakePlanInfo>(list);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("makePlanServiceImpl");//类名
		result.setMethodName("queryMakePlanInfoList");
		return result;
	}

	/**
	 * 根据条件查询纵剪带生产指令
	 */
	@Override
	public BSLResult queryMakePlanInfoList(QueryExample queryCriteria) {
		if (queryCriteria != null) {
			queryCriteria.setPlanFlag(DictItemOperation.指令类别_半成品生产指令);
			if (!StringUtils.isBlank(queryCriteria.getPlanId())) {
				queryCriteria.setPlanId(StringUtil.likeStr(queryCriteria.getPlanId()));
			}else{
				queryCriteria.setPlanId(null);
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
			if(!StringUtils.isBlank(queryCriteria.getPage()) && !StringUtils.isBlank(queryCriteria.getRows())) {
				PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
			}
			//排序
			if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
				queryCriteria.setOrderByClause("`plan_status` asc,`plan_id` desc");
			}else{
				String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
				if(!StringUtils.isBlank(sortSql)){
					queryCriteria.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
				}
			}
		}
		List<BslMakePlanInfoPro> list = bslMakePlanInfoMapper.selectByExampleCCrate2(queryCriteria);
		PageInfo<BslMakePlanInfoPro> pageInfo = new PageInfo<BslMakePlanInfoPro>(list);
		return BSLResult.ok(list, "makePlanServiceImpl", "queryMakePlanInfoList",pageInfo.getTotal(),list);
	}
	
	/**
	 * 新增纵剪带生产指令
	 */
	@Override
	public BSLResult add(BslMakePlanInfo bslMakePlanInfo) {
		String planId = createPlanId();
		bslMakePlanInfo.setPlanId(planId);
		bslMakePlanInfo.setPlanFlag(DictItemOperation.指令类别_半成品生产指令);
		bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_创建);
		bslMakePlanInfo.setCrtDate(new Date());
		
		int result = bslMakePlanInfoMapper.insert(bslMakePlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(planId);
	}
	
	/**
	 * 修改纵剪带生产指令
	 */
	@Override
	public BSLResult update(BslMakePlanInfo bslMakePlanInfo) {
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
//			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改用料炉号！";
//		}
		if(!bslBsPlanInfoOld.getProdMaterial().equals(bslMakePlanInfo.getProdMaterial())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改用料钢种！";
		}
		if(!bslBsPlanInfoOld.getProdNorm().equals(bslMakePlanInfo.getProdNorm())){
			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改用料规格！";
		}
		if(!bslBsPlanInfoOld.getMakeName().equals(bslMakePlanInfo.getMakeName())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改制造产品名称！";
		}
		if(!bslBsPlanInfoOld.getMakeType().equals(bslMakePlanInfo.getMakeType())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改制造纵剪带用途！";
		}
		if(!bslBsPlanInfoOld.getPlanJz().equals(bslMakePlanInfo.getPlanJz())){
			flag = true;
			errorMsg = "非创建中/进行中/暂停的纵剪带生产指令不允许修改纵剪带制造机组！";
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
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
			criteria.andProdPlanNoEqualTo(bslMakePlanInfo.getPlanId());
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()>0){
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					bslProductInfo.setUpdDate(new Date());
					bslProductInfo.setProdMaterial(bslMakePlanInfo.getProdMaterial());
					//bslProductInfo.setProdLuno(bslMakePlanInfo.getPlanLuno());
					bslProductInfo.setProdName(bslMakePlanInfo.getMakeName());
					bslProductInfo.setProdUserType(bslMakePlanInfo.getMakeType());
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
	 * 删除纵剪带生产指令
	 */
	@Override
	public BSLResult delete(String planId) {
		//根据指令号获取原指令信息
		BslMakePlanInfo bslBsPlanInfoOld = bslMakePlanInfoMapper.selectByPrimaryKey(planId);
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据指令号查询记录为空");
		}
		//校验状态
		if(!DictItemOperation.指令状态_创建.equals(bslBsPlanInfoOld.getPlanStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "非创建中的纵剪带生产指令不允许删除！");
		}
		//校验该指令下不存在调度计划
		BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
		com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteria = bslMakePlanInfoDetailExample.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		List<BslMakePlanInfoDetail> bslMakePlanInfoDetais = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
		if(bslMakePlanInfoDetais.size()>0){
			throw new BSLException(ErrorCodeInfo.错误类型_删除前子数据检查,"该指令下有详细调度信息，若要删除请先删除调度信息");
		}
		//删除操作
		int result = bslMakePlanInfoMapper.deleteByPrimaryKey(planId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}
		return BSLResult.ok(planId);
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
			
			//创建纵剪带指令查询实例
			BslMakePlanInfoExample bslMakePlanInfoExample = new BslMakePlanInfoExample();
			Criteria criteria = bslMakePlanInfoExample.createCriteria();
			//查询条件0-纵剪带生产指令 状态1-进行中
			criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_半成品生产指令);
			criteria.andPlanStatusEqualTo(DictItemOperation.指令状态_进行中);	
			criteria.andPlanJzEqualTo(planJz);
			List<BslMakePlanInfo> executePlans = bslMakePlanInfoMapper.selectByExample(bslMakePlanInfoExample);
			if(executePlans!= null && executePlans.size()>0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"同一时间同一机组只能有一条正在执行的纵剪带生产指令！");
			}
			//创建产品指令详细查询实列
			BslMakePlanInfoDetailExample bslMakePlanInfoDetailExample = new BslMakePlanInfoDetailExample();
			com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteriaDetail = bslMakePlanInfoDetailExample.createCriteria();
			criteriaDetail.andPlanIdEqualTo(planId);
			List<BslMakePlanInfoDetail> executePlanDetails = bslMakePlanInfoDetailMapper.selectByExample(bslMakePlanInfoDetailExample);
			if(executePlanDetails == null || executePlanDetails.size()<=0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该纵剪带生产指令下没有详细调度信息，无法执行！");
			}
			//校验完成，开始执行
			bslMakePlanInfo.setPlanStatus(DictItemOperation.指令状态_进行中);
		}else if(statusInt == 2){
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
			//查询因为这个指令出库的卷板信息,判断是不是都是已完成
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			//判断是不是都是已完成
			boolean isAchieve = true;
			if(bslProductInfos != null && bslProductInfos.size()>0){
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					if(!DictItemOperation.产品状态_已完成.equals(bslProductInfo.getProdStatus())) {
						isAchieve = false;
						break;
					}
				}
				if(!isAchieve) {
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"存在未完成的卷板，不能完成!");
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
			//查询因为这个指令出库的卷板信息,判断是不是都是已完成
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			//判断是不是都是已完成
			boolean isAchieve = true;
			if(bslProductInfos != null && bslProductInfos.size()>0){
				for (BslProductInfo bslProductInfo : bslProductInfos) {
					if(!DictItemOperation.产品状态_已完成.equals(bslProductInfo.getProdStatus())) {
						isAchieve = false;
						break;
					}
				}
				if(!isAchieve) {
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"存在未完成的卷板，不能完成!");
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
			//查询因为这个指令出库的卷板信息,没有才能撤销执行
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdOutPlanEqualTo(planId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos != null && bslProductInfos.size()> 0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"没有因为该指令出库的卷板才能撤销执行!");
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
	 * 根据实体类查询纵剪带生产指令
	 */
	@Override
	public List<BslMakePlanInfo> query(BslMakePlanInfoExample example) {
		return bslMakePlanInfoMapper.selectByExample(example);
	}

	/**
	 * 获取指定机组正在执行中的纵剪带指令信息
	 */
	@Override
	public BslMakePlanInfo getMakePlanInfoExe(String jz) {
		BslMakePlanInfoExample prodPlanInfoExample = new BslMakePlanInfoExample();
		com.bsl.pojo.BslMakePlanInfoExample.Criteria criteria = prodPlanInfoExample.createCriteria();
		criteria.andPlanFlagEqualTo(DictItemOperation.指令类别_半成品生产指令);
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
	 * 根据纵剪带指令id获取详细调度信息
	 */
	@Override
	public List<BslMakePlanInfoDetail> getMakePlanInfoDetail(String planId) {
		BslMakePlanInfoDetailExample bslPlanDetailSelectExample = new BslMakePlanInfoDetailExample();
		com.bsl.pojo.BslMakePlanInfoDetailExample.Criteria criteria = bslPlanDetailSelectExample.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		bslPlanDetailSelectExample.setOrderByClause("`plan_info_detail_id` desc");
		List<BslMakePlanInfoDetail> bslMakePlanInfoDetails = bslMakePlanInfoDetailMapper.selectByExample(bslPlanDetailSelectExample);
		return bslMakePlanInfoDetails;
	}

	/**
	 * 根据实体类获取详细调度信息
	 */
	@Override
	public BSLResult getDetailByMakePlanInfoService(BslMakePlanInfoExample example) {
		 List<BslMakePlanInfo> selectByExample = bslMakePlanInfoMapper.selectByExample(example);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 * 根据指令号查询该指令信息
	 */
	@Override
	public BslMakePlanInfo getMakePlanInfoById(String planId) {
		return bslMakePlanInfoMapper.selectByPrimaryKey(planId);
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
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
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
	
}
