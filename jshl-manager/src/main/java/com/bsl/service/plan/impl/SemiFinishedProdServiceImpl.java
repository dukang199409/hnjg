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
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.reportbean.BslHalfProdMakeInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.plan.RawOutputService;
import com.bsl.service.plan.SemiFinishedProdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 半成品入库
 */
@Service
public class SemiFinishedProdServiceImpl implements SemiFinishedProdService {

	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	//记录流水
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired
	private MakePlanService makePlanService;
	@Autowired
	private RawOutputService rawOutputService;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_SEMI_PROD_ID_KEY}")
	private String REDIS_NEXT_SEMI_PROD_ID;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	

	/**
	 * 库存变动流水自动生成编号
	 * CH+日期+2位序号
	 * @return
	 */
	public String createStockChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_STOCKCHANGE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CH%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}
	
	/**
	 * 半成品入库产品编号
	 * 半成品指令号日期月日三位序号
	 * @return
	 */
	public String createSemiProdId() {
		long incr = jedisClient.incr(REDIS_NEXT_SEMI_PROD_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prodId = String.format("ZJD%s%03d", sdf.format(new Date()), incr);
		return prodId;
	}
	
	/**
	 *根据生产出库单查询出库的半成品信息
	 */
	@Override
	public BSLResult queryMakeInfoList(QueryExample queryCriteria) {
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			queryCriteria.setProdOutPlan(null);
		}else{
			queryCriteria.setProdOutPlan(queryCriteria.getProdOutPlan());
		}
		if(StringUtils.isBlank(queryCriteria.getProdId())){
			queryCriteria.setProdId(null);
		}else{
			queryCriteria.setProdId("%"+queryCriteria.getProdId()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getProdStatus())){
			queryCriteria.setProdStatus(null);
		}
		//分页处理
		if(!StringUtils.isBlank(queryCriteria.getPage()) && !StringUtils.isBlank(queryCriteria.getRows())) {
			PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslHalfProdMakeInfo> lists = bslProductInfoMapper.halfProdMakeInfo(queryCriteria);
		PageInfo<BslHalfProdMakeInfo> pageInfo = new PageInfo<BslHalfProdMakeInfo>(lists);
		return BSLResult.ok(lists,"semiFinishedProdServiceImpl","queryMakeInfoList",pageInfo.getTotal(),lists);
	}
	
	/**
	 * 半成品入库新增
	 * 盘号就是半成品ID prodId
	 * 卷号就是半成品指令编号 planId
	 */
	@Override
	public BSLResult add(BslProductInfo bslProductInfo,int sumNum) {
		//二次校验
		//获取正在执行的纵剪带指令
		BslMakePlanInfo makePlanInfoExe = makePlanService.getMakePlanInfoExe(bslProductInfo.getProdMakeJz());
		if(makePlanInfoExe == null){
			 throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到正在执行的纵剪带指令信息，无法入库");
		 }
		//校验指令号
		if(!makePlanInfoExe.getPlanId().equals(bslProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "纵剪带指令号必须是执行中的指令");
		}
		//校验名称
		if(!makePlanInfoExe.getMakeName().equals(bslProductInfo.getProdName())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品名称必须是纵剪带指令指定名称");
		}
		//校验炉号
//		if(!makePlanInfoExe.getPlanLuno().equals(bslProductInfo.getProdLuno())){
//			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号必须是纵剪带指令对应炉号");
//		}
		//校验钢种
		if(!makePlanInfoExe.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种必须是纵剪带指令指定钢种");
		}
		//获取正在出库中的卷板
		BslProductInfo rawExe = rawOutputService.getRawExe(makePlanInfoExe.getPlanId());
		if(rawExe == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到该指令下制造出库的卷板信息，无法入库");
		}
		if(!rawExe.getProdId().equals(bslProductInfo.getProdParentNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品父级卷板必须是该指令下正在出库制造的卷板");
		}
		//校验该卷板重量是否超出
		Boolean isOutW = isOutWeight(rawExe,bslProductInfo.getProdRelWeight());
		if(isOutW){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该卷板下纵剪带重量总和已超出卷板重量，无法入库，请核对！");
		}
		
		//校验规格必须是指定规格
		List<BslMakePlanInfoDetail> makePlanInfoDetail = makePlanService.getMakePlanInfoDetail(makePlanInfoExe.getPlanId());
		boolean isAssNorm = false;
		for (BslMakePlanInfoDetail bslMakePlanInfoDetail : makePlanInfoDetail) {
			if(bslProductInfo.getProdNorm().equals(bslMakePlanInfoDetail.getProdNorm())){
				isAssNorm = true;
				break;
			}
		}
		if(isAssNorm == false){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是该指令下详细计划指定的某种规格");
		}
		
		//判断入库盘数,平分重量
		Float relWeight = bslProductInfo.getProdRelWeight()/sumNum;
		relWeight = ((float)Math.round(relWeight*1000))/1000;
		bslProductInfo.setProdRelWeight(relWeight);
		
		//记录入库流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		String returnProdId = "";
		String prodId;
		for (int i = 0; i < sumNum; i++) {
			//校验完成，开始入库
			prodId = createSemiProdId();
			bslProductInfo.setProdId(prodId);
			bslProductInfo.setCrtDate(new Date());
			bslProductInfo.setProdType(DictItemOperation.产品类型_半成品);
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfo.setProdLuno(rawExe.getProdLuno());//炉号等于父级产品炉号
			bslProductInfo.setProdOrirawid(rawExe.getProdOrirawid());
			bslProductInfo.setProdNum(1);
			bslProductInfo.setProdCompany(rawExe.getProdCompany());
			bslProductInfo.setProdCustomer(rawExe.getProdCustomer());
			bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
			bslProductInfo.setProdUserType(makePlanInfoExe.getMakeType());//用途为指令指定的用途
			bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			int result = bslProductInfoMapper.insert(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//记录入库流水
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的纵剪带指令
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_半成品);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslProductInfo.getProdInputuser());//录入人
			bslStockChangeDetail.setCrtDate(new Date());
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultStock==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
			}
			
			//记录返回起始编号
			if(i==0){
				returnProdId = prodId;
			}
		}
		makePlanService.updateProdRuNumAndSums(makePlanInfoExe.getPlanId());
		return BSLResult.ok(returnProdId);
	}
	
	/**
	 * 检验纵剪带父级卷板重量信息
	 */
	private boolean isOutWeight(BslProductInfo bslProductInfo,Float weight) {
		//获取该卷板下入库的纵剪带信息
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
		criteria.andProdParentNoEqualTo(bslProductInfo.getProdId());
		List<BslProductInfo> prods = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		Float inWeight = 0f;
		if(prods != null && prods.size() > 0){
			for (BslProductInfo prod : prods) {
				inWeight += prod.getProdRelWeight();
			}
		}
		Float rawWeight = bslProductInfo.getProdRelWeight();
		if( inWeight + weight > rawWeight){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 半成品入库修改
	 */
	@Override
	public BSLResult update(BslProductInfo bslProductInfo) {
		//校验管理员
		if(!DictItemOperation.管理员.equals(bslProductInfo.getProdInputuser())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许修改产品");
		}
		//获取原产品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据盘号查询记录为空");
		}
		//校验状态，只有是1-入库中的状态才能修改
		/*if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的产品才能修改");
		}*/
		//校验不允许修改的内容
		//校验指令号
		if(!oldBslProductInfo.getProdPlanNo().equals(bslProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "纵剪带指令号不允许修改");
		}
		//校验名称
		if(!oldBslProductInfo.getProdName().equals(bslProductInfo.getProdName())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品名称不允许修改");
		}
		//校验炉号
		/*if(!oldBslProductInfo.getProdLuno().equals(bslProductInfo.getProdLuno())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号不允许修改");
		}*/
		//校验钢种
		if(!oldBslProductInfo.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种不允许修改");
		}
		//校验父级卷板号
		if(!oldBslProductInfo.getProdParentNo().equals(bslProductInfo.getProdParentNo())){
			//校验钢卷号必须是因为该指令出库的钢卷号
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdIdEqualTo(bslProductInfo.getProdParentNo());
			criteria.andProdOutPlanEqualTo(bslProductInfo.getProdPlanNo());
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos == null || bslProductInfos.size() == 0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "纵剪带父级钢卷号必须是因为该指令出库的钢卷号");
			}
			bslProductInfo.setProdLuno(bslProductInfos.get(0).getProdLuno());
			bslProductInfo.setProdOrirawid(bslProductInfos.get(0).getProdOrirawid());
		}
		//校验规格必须是指定规格
		List<BslMakePlanInfoDetail> makePlanInfoDetail = makePlanService.getMakePlanInfoDetail(bslProductInfo.getProdPlanNo());
		boolean isAssNorm = false;
		for (BslMakePlanInfoDetail bslMakePlanInfoDetail : makePlanInfoDetail) {
			if(bslProductInfo.getProdNorm().equals(bslMakePlanInfoDetail.getProdNorm())){
				isAssNorm = true;
				break;
			}
		}
		if(isAssNorm == false){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是该指令下详细计划指定的某种规格");
		}
		
		//如果修改了炉号，更新成品炉号
		if(!oldBslProductInfo.getProdLuno().equals(bslProductInfo.getProdLuno())){
			//修改产品炉号
			BslProductInfoExample exampleProd = new BslProductInfoExample();
			Criteria criteriaProd = exampleProd.createCriteria();
			criteriaProd.andProdParentNoEqualTo(bslProductInfo.getProdId());
			List<BslProductInfo> prodList = bslProductInfoMapper.selectByExample(exampleProd);
			if(prodList != null && prodList.size()>0){
				for (BslProductInfo bslProductInfoProd : prodList) {
					//修改纵剪带炉号
					bslProductInfoProd.setProdLuno(bslProductInfo.getProdLuno());
					bslProductInfoProd.setProdOrirawid(bslProductInfo.getProdOrirawid());
					bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoProd);
				}
			}
		}
		
		//检验完成开始修改
		bslProductInfo.setUpdDate(new Date());
		bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		
		makePlanService.updateProdRuNumAndSums(bslProductInfo.getProdPlanNo());
		return BSLResult.ok(bslProductInfo.getProdId());
	}

	/**
	 * 半成品剩余重新入库
	 */
	@Override
	public BSLResult addRe(BslProductInfo bslProductInfo) {
		//获取原半成品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOriId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据原盘号查询记录为空");
		}
		if(!DictItemOperation.产品状态_已出库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原半成品状态必须是已出库");
		}
		if(DictItemOperation.纵剪带用途_外销.equals(oldBslProductInfo.getProdUserType())){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "纵剪带用途为外销，不能重新入库！");
		}
		if(oldBslProductInfo.getProdRelWeight().equals(bslProductInfo.getProdRelWeight())){
			throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重量未改变,无需剩余材料重新入库,请直接对原半成品未用退回");
		}
		if(oldBslProductInfo.getProdRelWeight() < bslProductInfo.getProdRelWeight()){
			throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重新入库重量不能大于原半成品重量");
		}
		//校验完成开始参数赋值
		bslProductInfo.setProdId(createSemiProdId());//生成编号
		bslProductInfo.setProdName(oldBslProductInfo.getProdName());//名称同原半成品
		bslProductInfo.setProdType(DictItemOperation.产品类型_半成品);
		bslProductInfo.setProdNum(1);
		bslProductInfo.setProdLuno(oldBslProductInfo.getProdLuno());
		bslProductInfo.setProdMaterial(oldBslProductInfo.getProdMaterial());//钢种同原半成品
		bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
		bslProductInfo.setProdSourceCompany(oldBslProductInfo.getProdSourceCompany());//发货仓库同原半成品
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
		bslProductInfo.setProdSource(DictItemOperation.产品来源_剩余入库);
		bslProductInfo.setProdLuno(oldBslProductInfo.getProdLuno());//炉号与原来一致
		bslProductInfo.setProdOrirawid(oldBslProductInfo.getProdOrirawid());
		bslProductInfo.setProdPlanNo(oldBslProductInfo.getProdPlanNo());//对应纵剪带生产指令同原半成品
		bslProductInfo.setProdParentNo(oldBslProductInfo.getProdParentNo());//对应来源同原半成品
		bslProductInfo.setProdCompany(oldBslProductInfo.getProdCompany());//厂家同原来一致
		bslProductInfo.setProdCustomer(oldBslProductInfo.getProdCustomer());
		bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
		bslProductInfo.setCrtDate(new Date());//日期当天
		//开始插入
		int result = bslProductInfoMapper.insert(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"重新入库失败");
		}
		
		//把原来那个半成品状态改为已完成
		BslProductInfo productInfoOri = new BslProductInfo();
		productInfoOri.setProdId(oldBslProductInfo.getProdId());
		productInfoOri.setProdStatus(DictItemOperation.产品状态_已完成);
		productInfoOri.setUpdDate(new Date());
		int resultUpdateOri = bslProductInfoMapper.updateByPrimaryKeySelective(productInfoOri);
		if(resultUpdateOri<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultUpdateOri==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原半成品状态失败");
		}
		
		//新增完成之后记录库存变动信息
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
		bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的纵剪带批号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_剩余重新入库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_半成品);//产品类型
		bslStockChangeDetail.setProdOriId(bslProductInfo.getProdOriId());//原半成品
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"剩余入库库存变动表失败");
		}
		
		//在记录原半成品的完成信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(oldBslProductInfo.getProdId());//产品编号
		bslStockChangeDetailRaw.setPlanSerno(oldBslProductInfo.getProdPlanNo());//对应的纵剪带批号
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_半成品);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockHalfOri = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockHalfOri<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockHalfOri==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"状态完成库存变动表失败");
		}
		
		return BSLResult.ok(bslProductInfo.getProdId());
	}
	
	/**
	 * 初始化查询所有半成品信息
	 */
	@Override
	public EasyUIDataGridResult getBslProductInfoList(int page, int rows) {
		BslProductInfoExample example = new BslProductInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
		// 分页处理
		PageHelper.startPage(page, rows);
		example.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		List<BslProductInfo> list = bslProductInfoMapper.selectByExample(example);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(list);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("semiFinishedProdServiceImpl");
		result.setMethodName("queryBslProductInfoList");
		return result;
	}

	/**
	 *根据条件查询半成品信息 
	 */
	@Override
	public BSLResult queryBslProductInfoList(QueryExample queryCriteria) {
		BslProductInfoExample example = new BslProductInfoExample();
		if (queryCriteria != null) {
			Criteria criteria = example.createCriteria();
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
			//盘号
			if (!StringUtils.isBlank(queryCriteria.getProdId())) {
				criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
			}
			//半成品生产批号
			if (!StringUtils.isBlank(queryCriteria.getProdPlanNo())) {
				criteria.andProdPlanNoLike(StringUtil.likeStr(queryCriteria.getProdPlanNo()));
			}
			//炉号
			if (!StringUtils.isBlank(queryCriteria.getProdLuno())) {
				criteria.andProdLunoLike(StringUtil.likeStr(queryCriteria.getProdLuno()));
			}
			//父级钢卷号
			if (!StringUtils.isBlank(queryCriteria.getProdParentNo())) {
				criteria.andProdParentNoLike(StringUtil.likeStr(queryCriteria.getProdParentNo()));
			}
			//出库指令号
			if (!StringUtils.isBlank(queryCriteria.getProdOutPlan())) {
				criteria.andProdOutPlanLike(StringUtil.likeStr(queryCriteria.getProdOutPlan()));
			}
			//产品规格
			if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
				criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
			}
			//钢种
			if (!StringUtils.isBlank(queryCriteria.getProdMaterial())) {
				criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
			}
			//班次
			if (!StringUtils.isBlank(queryCriteria.getProdBc())) {
				criteria.andProdBcEqualTo(queryCriteria.getProdBc());
			}
			//生产机组
			if (!StringUtils.isBlank(queryCriteria.getProdMakeJz())) {
				criteria.andProdMakeJzEqualTo(queryCriteria.getProdMakeJz());
			}
			//用途
			if (!StringUtils.isBlank(queryCriteria.getProdUserType())) {
				criteria.andProdUserTypeEqualTo(queryCriteria.getProdUserType());
			}
			//产品状态
			if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
				criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
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
				example.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
			}
		}
		List<BslProductInfo> list = bslProductInfoMapper.selectByExample(example);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(list);
		return BSLResult.ok(list,"semiFinishedProdServiceImpl","queryBslProductInfoList",pageInfo.getTotal(),list);
	}

	/**
	 *根据实例查询半成品信息 
	 */
	@Override
	public List<BslProductInfo> query(BslProductInfoExample example) {
		return bslProductInfoMapper.selectByExample(example);
	}
	
	/**
	 * 删除
	 */
	@Override
	public BSLResult delete(String prodId,String user) {
		//校验人员
		if(!DictItemOperation.管理员.equals(user)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许删除产品");
		}
		//获取原产品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据产品编号查询记录为空");
		}
		//校验状态，只有是1-入库中的状态才能修改
		if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的产品才允许删除");
		}
		//开始删除
		int result = bslProductInfoMapper.deleteByPrimaryKey(prodId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		//记录删除信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(oldBslProductInfo.getProdId());//产品编号
		bslStockChangeDetailRaw.setPlanSerno(oldBslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_删除);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_半成品);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(oldBslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		makePlanService.updateProdRuNumAndSums(oldBslProductInfo.getProdPlanNo());
		return BSLResult.ok(prodId);
	}

}
