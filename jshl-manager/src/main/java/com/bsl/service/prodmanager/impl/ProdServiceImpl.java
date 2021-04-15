package com.bsl.service.prodmanager.impl;

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
import com.bsl.common.utils.StringUtil;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslMakePlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.reportbean.BslProductInfoCollect;
import com.bsl.reportbean.BslRuInFo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.prodmanager.HalfProdOutPutService;
import com.bsl.service.prodmanager.ProdPlanService;
import com.bsl.service.prodmanager.ProdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 产品入库实现类
 * duk-20190319
 */
@Service
public class ProdServiceImpl implements ProdService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired	 
	BslMakePlanInfoMapper bslMakePlanInfoMapper;
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired
	private ProdPlanService prodPlanService;
	@Autowired
	private HalfProdOutPutService halfProdOutPutService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private MakePlanService makePlanService;
	
	@Value("REDIS_NEXT_PROD_ID")
	private String REDIS_NEXT_PROD_ID;
	
	@Value("REDIS_NEXT_PROD_W_ID")
	private String REDIS_NEXT_PROD_W_ID;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	
	@Value("${REDIS_NEXT_CAR_ID}")
	private String REDIS_NEXT_CAR_ID;
	

	/**
	 * 初始化查询所有2-产品信息 
	 */
	@Override
	public EasyUIDataGridResult getProdService(Integer page, Integer rows) {
		//查询条件状态 类型是2-产品
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslProductInfos);
		//取记录总条数
		PageInfo<BslProductInfo> pageInfo = new PageInfo<>(bslProductInfos);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("prodServiceImpl");
		result.setMethodName("getProdService");
		return result;
	}

	/**
	 *根据条件查询2-产品信息 
	 */
	@Override
	public BSLResult getProdService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		//产品编号
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
		//产品状态
		if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		//班次
		if (!StringUtils.isBlank(queryCriteria.getProdBc())) {
			criteria.andProdBcEqualTo(queryCriteria.getProdBc());
		}
		//生产机组
		if (!StringUtils.isBlank(queryCriteria.getProdMakeJz())) {
			criteria.andProdMakeJzEqualTo(queryCriteria.getProdMakeJz());
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
			bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"prodServiceImpl","getProdService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 入库
	 */
	@Override
	public BSLResult addCfmProdInfo(BslProductInfo bslProductInfo,int sumNum) {
		//二次校验
		//获取正在执行的产品生产指令
		BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(bslProductInfo.getProdMakeJz());
		if(makePlanInfoExe == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到正在执行的生产指令信息，无法入库");
		}
		//校验指令号
		if(!makePlanInfoExe.getPlanId().equals(bslProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "生产指令号必须是执行中的指令");
		}
		//校验名称
		if(!makePlanInfoExe.getMakeName().equals(bslProductInfo.getProdName())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品名称必须是生产指令指定名称");
		}
		//校验炉号
//		if(!makePlanInfoExe.getPlanLuno().equals(bslProductInfo.getProdLuno())){
//			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号必须是生产指令对应炉号");
//		}
		//校验钢种
		if(!makePlanInfoExe.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种必须是生产指令指定钢种");
		}
		//校验规格
		if(!makePlanInfoExe.getMakeProdNorm().equals(bslProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是生产指令指定规格");
		}
		//校验定尺必须是指定定尺
		List<BslMakePlanInfoDetail> makePlanInfoDetail = makePlanService.getMakePlanInfoDetail(makePlanInfoExe.getPlanId());
		boolean isAssNorm = false;
		for (BslMakePlanInfoDetail bslMakePlanInfoDetail : makePlanInfoDetail) {
			if(bslProductInfo.getProdLength().equals(bslMakePlanInfoDetail.getProdLength())){
				isAssNorm = true;
				break;
			}
		}
		if(isAssNorm == false){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品定尺必须是该指令下详细计划指定的某种定尺");
		}
		
		//校验父级盘号
		BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdParentNo());
		if(parentProd == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级产品信息");
		}
		if(!parentProd.getProdOutPlan().equals(makePlanInfoExe.getPlanId())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品父级产品必须是因该指令出库的产品");
		}
		//校验总重量
		//记录本次入库重量
		Float inWeight = bslProductInfo.getProdRelWeight();
		int checkInt = halfProdOutPutService.updateHalfProdStatus(bslProductInfo.getProdParentNo(),inWeight);
		if(checkInt == 1){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该产品已生产重量加上本次需入库重量累计超出产品本身重量，无法入库！");
		}
		
		//判断入库产品数,平分重量
		Float relWeight = bslProductInfo.getProdRelWeight()/sumNum;
		relWeight = ((float)Math.round(relWeight*1000))/1000;
		bslProductInfo.setProdRelWeight(relWeight);
		
		//记录入库流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		String returnProdId = "";
		String prodId;
		
		for (int i = 0; i < sumNum; i++) {
			//校验完成，开始入库
			prodId = createProdId();
			bslProductInfo.setProdId(prodId);//生成编号
			bslProductInfo.setProdType(DictItemOperation.产品类型_成品);
			bslProductInfo.setProdLuno(parentProd.getProdLuno());//炉号为父级炉号
			bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
			bslProductInfo.setCrtDate(new Date());//创建日期当天
			bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfo.setProdCompany(parentProd.getProdCompany());//厂家同原来一致
			bslProductInfo.setProdOrirawid(parentProd.getProdOrirawid());
			bslProductInfo.setProdCustomer(parentProd.getProdCustomer());
			int result = bslProductInfoMapper.insert(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_成品);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslProductInfo.getProdCheckuser());//录入人
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
		//入库完成之后判断是否需要更新纵剪带状态
		if(checkInt == 2){
			parentProd.setProdStatus(DictItemOperation.产品状态_已完成);
			parentProd.setUpdDate(new Date());
			bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
		}
		prodPlanService.updateProdRuNumAndSums(makePlanInfoExe.getPlanId());
		return BSLResult.ok(returnProdId);
	}
	
	/**
	 * 补录入库
	 */
	@Override
	public BSLResult addCfmProdInfoBuLu(BslProductInfo bslProductInfo, int sumNum) {
		//二次校验
		//获取正在执行的产品生产指令
		BslMakePlanInfo makePlanInfo = prodPlanService.getProdPlanInfo(bslProductInfo.getProdPlanNo());
		if(makePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到生产指令信息，无法入库");
		}
		//校验名称
		if(!makePlanInfo.getMakeName().equals(bslProductInfo.getProdName())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品名称必须是生产指令指定名称");
		}
		//校验炉号
//				if(!makePlanInfoExe.getPlanLuno().equals(bslProductInfo.getProdLuno())){
//					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号必须是生产指令对应炉号");
//				}
		//校验钢种
		if(!makePlanInfo.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种必须是生产指令指定钢种");
		}
		//校验规格
		if(!makePlanInfo.getMakeProdNorm().equals(bslProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是生产指令指定规格");
		}
		//校验定尺必须是指定定尺
		List<BslMakePlanInfoDetail> makePlanInfoDetail = makePlanService.getMakePlanInfoDetail(makePlanInfo.getPlanId());
		boolean isAssNorm = false;
		for (BslMakePlanInfoDetail bslMakePlanInfoDetail : makePlanInfoDetail) {
			if(bslProductInfo.getProdLength().equals(bslMakePlanInfoDetail.getProdLength())){
				isAssNorm = true;
				break;
			}
		}
		if(isAssNorm == false){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品定尺必须是该指令下详细计划指定的某种定尺");
		}
		
		//校验父级盘号
		BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdParentNo());
		if(parentProd == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级产品信息");
		}
		if(!parentProd.getProdOutPlan().equals(makePlanInfo.getPlanId())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品父级产品必须是因该指令出库的产品");
		}
		//校验总重量
		//记录本次入库重量
		Float inWeight = bslProductInfo.getProdRelWeight();
		int checkInt = halfProdOutPutService.updateHalfProdStatus(bslProductInfo.getProdParentNo(),inWeight);
		if(checkInt == 1){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该产品已生产重量加上本次需入库重量累计超出产品本身重量，无法入库！");
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
			prodId = createProdId();
			bslProductInfo.setProdId(prodId);//生成编号
			bslProductInfo.setProdType(DictItemOperation.产品类型_成品);
			bslProductInfo.setProdLuno(parentProd.getProdLuno());//炉号为父级炉号
			bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
			bslProductInfo.setCrtDate(new Date());//创建日期当天
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			bslProductInfo.setProdCompany(parentProd.getProdCompany());//厂家同原来一致
			bslProductInfo.setProdCustomer(parentProd.getProdCustomer());
			bslProductInfo.setProdOrirawid(parentProd.getProdOrirawid());
			
			int result = bslProductInfoMapper.insert(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_成品);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslProductInfo.getProdCheckuser());//录入人
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
		//入库完成之后判断是否需要更新纵剪带状态
		if(checkInt == 2){
			parentProd.setProdStatus(DictItemOperation.产品状态_已完成);
			parentProd.setUpdDate(new Date());
			bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
		}
		prodPlanService.updateProdRuNumAndSums(makePlanInfo.getPlanId());
		return BSLResult.ok(returnProdId);
	}

	/**
	 * 已入库产品信息修改
	 */
	@Override
	public BSLResult updateProdInfo(BslProductInfo bslProductInfo) {
		//校验管理员
		if(!DictItemOperation.管理员.equals(bslProductInfo.getProdInputuser())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许修改产品");
		}
		//获取原产品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据件号查询记录为空");
		}
		//校验状态，只有是1-入库中的状态才能修改
		/*if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的产品才能修改");
		}*/
		//校验不允许修改的内容
		//校验指令号
		if(!oldBslProductInfo.getProdPlanNo().equals(bslProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品指令号不允许修改");
		}
		//校验名称
		if(!oldBslProductInfo.getProdName().equals(bslProductInfo.getProdName())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品名称不允许修改");
		}
		//校验炉号
//		if(!oldBslProductInfo.getProdLuno().equals(bslProductInfo.getProdLuno())){
//			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号不允许修改");
//		}
		//校验钢种
		if(!oldBslProductInfo.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种不允许修改");
		}
		//校验父级卷板号
		/*if(!oldBslProductInfo.getProdParentNo().equals(bslProductInfo.getProdParentNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品父级盘号不允许修改");
		}*/
		//校验规格
		if(!oldBslProductInfo.getProdNorm().equals(bslProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格不允许修改");
		}
		//校验定尺必须是指定定尺
		List<BslMakePlanInfoDetail> makePlanInfoDetail = makePlanService.getMakePlanInfoDetail(oldBslProductInfo.getProdPlanNo());
		boolean isAssNorm = false;
		for (BslMakePlanInfoDetail bslMakePlanInfoDetail : makePlanInfoDetail) {
			if(bslProductInfo.getProdLength().equals(bslMakePlanInfoDetail.getProdLength())){
				isAssNorm = true;
				break;
			}
		}
		if(isAssNorm == false){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品定尺必须是该指令下详细计划指定的某种定尺");
		}
		//校验父级盘号
		if(!oldBslProductInfo.getProdParentNo().equals(bslProductInfo.getProdParentNo())){
			BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdParentNo());
			if(parentProd == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级产品信息");
			}
			if(!parentProd.getProdOutPlan().equals(oldBslProductInfo.getProdPlanNo())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品父级产品必须是因该指令出库的产品");
			}
			bslProductInfo.setProdLuno(parentProd.getProdLuno());
			bslProductInfo.setProdOrirawid(parentProd.getProdOrirawid());

			//校验总重量
			//记录本次入库重量
			Float inWeight = bslProductInfo.getProdRelWeight();
			int checkInt = halfProdOutPutService.updateHalfProdStatus(bslProductInfo.getProdParentNo(),inWeight);
			if(checkInt == 1){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该产品已生产重量加上本次需入库重量累计超出产品本身重量，无法修改！");
			}
			
			//修改完成之后判断是否需要更新纵剪带状态
			if(checkInt == 2){
				parentProd.setProdStatus(DictItemOperation.产品状态_已完成);
				parentProd.setUpdDate(new Date());
				bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
			}
			BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(bslProductInfo.getProdMakeJz());
			if(makePlanInfoExe != null){
				if(makePlanInfoExe.getPlanId().equals(bslProductInfo.getProdPlanNo())){
					parentProd.setProdStatus(DictItemOperation.产品状态_已出库);
					parentProd.setUpdDate(new Date());
					bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
				}
			}
			
		}
		
		//校验完成开始修改
		bslProductInfo.setUpdDate(new Date());
		bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		prodPlanService.updateProdRuNumAndSums(bslProductInfo.getProdPlanNo());
		return BSLResult.ok(bslProductInfo.getProdId());
	}
	
	/**
	 * 产品自动生成编号
	 * XCP+日期+3位序号
	 * @return
	 */
	public String createProdId() {
		long incr = jedisClient.incr(REDIS_NEXT_PROD_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prodId = String.format("XCP%s%03d", sdf.format(new Date()), incr);
		return prodId;
	}
	
	/**
	 * 外协厂产品自动生成编号
	 * XCPW+日期+3位序号
	 * @return
	 */
	public String createProdIdWx() {
		long incr = jedisClient.incr(REDIS_NEXT_PROD_W_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prodId = String.format("XCPW%s%03d", sdf.format(new Date()), incr);
		return prodId;
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

	@Override
	public List<BslProductInfo> getProdList(BslProductInfoExample productInfoExample) {
		return bslProductInfoMapper.selectByExample(productInfoExample);
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
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_成品);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(oldBslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		//如果删除的产品指令号是正在执行的指令，更新产品的父级纵剪带状态
		//获取正在执行的产品生产指令
		BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(oldBslProductInfo.getProdMakeJz());
		if(makePlanInfoExe != null){
			if(makePlanInfoExe.getPlanId().equals(oldBslProductInfo.getProdPlanNo())){
				BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(oldBslProductInfo.getProdParentNo());
				parentProd.setProdStatus(DictItemOperation.产品状态_已出库);
				parentProd.setUpdDate(new Date());
				bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
			}
		}
		prodPlanService.updateProdRuNumAndSums(oldBslProductInfo.getProdPlanNo());
		return BSLResult.ok(prodId);
	}

	@Override
	public List<BslProductInfoCollect> querySaleOutBill(QueryExample queryExample) {
		return bslProductInfoMapper.querySaleOutBill(queryExample);
	}
	
	@Override
	public List<BslProductInfoCollect> querySaleOutBillWaste(QueryExample queryExample) {
		if(queryExample.getProdIds() != null){
			List<String> prodIds = new ArrayList<String>();
			for (String prodId : queryExample.getProdIds()) {
				prodIds.add(prodId);
			}
			queryExample.setProdIdsList(prodIds);
		}
		return bslProductInfoMapper.querySaleOutBillWaste(queryExample);
	}

	@Override
	public List<BslProductInfoCollect> querySaleOutByProds(QueryExample queryExample) {
		return bslProductInfoMapper.querySaleOutByProds(queryExample);
	}

	/**
	 * 根据盘号获取该盘已经入库的包数
	 */
	@Override
	public BSLResult getProdRuNums(String prodId) {
		//校验父级盘号
		BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(parentProd == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级产品信息");
		}
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		List<String> list = new ArrayList<String>();
		list.add(DictItemOperation.产品类型_成品);
		list.add(DictItemOperation.产品类型_待处理品);
		criteria.andProdTypeIn(list);
		criteria.andProdParentNoEqualTo(prodId);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		List<BslProductInfo> prods = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		BslRuInFo bslRuInFo = new BslRuInFo();
		bslRuInFo.setProdId(prodId);
		bslRuInFo.setProdRelWeight(parentProd.getProdRelWeight());
		if(prods == null){
			bslRuInFo.setProdRuNum(0);
			bslRuInFo.setProdRuWeight(0f);
		}else{
			Float prodRuWeight = 0f;
			for (BslProductInfo bslProductInfo : prods) {
				prodRuWeight += bslProductInfo.getProdRelWeight();
			}
			bslRuInFo.setProdRuNum(prods.size());
			bslRuInFo.setProdRuWeight(prodRuWeight);
		}
		return BSLResult.ok(bslRuInFo);
	}
	
	/**
	 * 发货车号流水自动生成编号
	 * C+日期+车号+NO+序号
	 * @return
	 */
	public String createCarSernoId(String carNo) {
		long incr = jedisClient.incr(REDIS_NEXT_CAR_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		String no = sdf.format(new Date()) + sdf2.format(new Date()) + carNo;
		String rawId = String.format("C%sNO%02d", no, incr);
		return rawId;
	}

	/**
	 * 更新产品的发货车号
	 */
	@Override
	public BSLResult updateProdCarNo(List<String> prods,String prodCarNo) {
		BslProductInfo bslProductInfo = new BslProductInfo();
		bslProductInfo.setProdOutCarno(createCarSernoId(prodCarNo));
		for (String prodId : prods) {
			bslProductInfo.setProdId(prodId);
			int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"更新产品发货车号成功");
			}
		}
		return BSLResult.ok(bslProductInfo.getProdOutCarno());
	}

	/**
	 * 查询外协厂产品信息
	 */
	@Override
	public BSLResult getWxProdService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
		criteria.andProdDclFlagNotEqualTo(DictItemOperation.产品外协厂标志_本厂);
		//产品编号
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
		//父级待处理品编号
		if (!StringUtils.isBlank(queryCriteria.getProdOriId())) {
			criteria.andProdOriIdLike(StringUtil.likeStr(queryCriteria.getProdOriId()));
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
		//产品状态
		if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		//班次
		if (!StringUtils.isBlank(queryCriteria.getProdBc())) {
			criteria.andProdBcEqualTo(queryCriteria.getProdBc());
		}
		//转换单号
		if (!StringUtils.isBlank(queryCriteria.getProdFhck())) {
			criteria.andProdFhckLike(queryCriteria.getProdFhck());
		}
		//外协厂标志
		if (!StringUtils.isBlank(queryCriteria.getProdDclFlag())) {
			criteria.andProdDclFlagEqualTo(queryCriteria.getProdDclFlag());
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
			bslProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"prodServiceImpl","getWxProdService",pageInfo.getTotal(),bslProductInfos);
	}
	
	/**
	 * 产品入库-待处理品处理
	 */
	@Override
	public BSLResult addProdFromDclinfo(BslProductInfo bslProductInfo, int sumNum) {
		//二次校验
		//获取待处理品信息
		String prodCompany = "";
		String prodCustomer = "";
		String prodMakeJz = "";
		String prodOrirawId = "";
		BslProductInfo bslProductInfoDcl = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOriId());
		if(bslProductInfoDcl != null){
			//校验炉号
			if(!bslProductInfo.getProdLuno().equals(bslProductInfoDcl.getProdLuno())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号必须与父级待处理品炉号一致");
			}
			//校验钢种
			if(!bslProductInfo.getProdMaterial().equals(bslProductInfoDcl.getProdMaterial())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种必须与父级待处理品钢种一致");
			}
			//校验规格
			/*if(!bslProductInfo.getProdNorm().equals(bslProductInfoDcl.getProdNorm())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是生产指令指定规格");
			}*/
			prodCompany = bslProductInfoDcl.getProdCompany();
			prodCustomer = bslProductInfoDcl.getProdCustomer();
			prodMakeJz = bslProductInfoDcl.getProdMakeJz();
			prodOrirawId = bslProductInfoDcl.getProdOrirawid();
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
			prodId = createProdId();
			bslProductInfo.setProdId(prodId);//生成编号
			bslProductInfo.setProdType(DictItemOperation.产品类型_成品);
			bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
			bslProductInfo.setCrtDate(new Date());//创建日期当天
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			bslProductInfo.setProdCompany(prodCompany);//厂家同原来一致
			bslProductInfo.setProdCustomer(prodCustomer);
			bslProductInfo.setProdMakeJz(prodMakeJz);
			bslProductInfo.setProdOrirawid(prodOrirawId);
			
			int result = bslProductInfoMapper.insert(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_成品);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslProductInfo.getProdCheckuser());//录入人
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
		return BSLResult.ok(returnProdId);
	}


	/**
	 * 外协产品入库
	 */
	@Override
	public BSLResult addProdWxinfoB(BslProductInfo bslProductInfo, int sumNum) {
		//二次校验
		//获取待处理品信息
		String prodCompany = "";
		String prodCustomer = "";
		String prodOrirawId = "";
		BslProductInfo bslProductInfoDcl = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOriId());
		if(bslProductInfoDcl != null){
			//校验炉号
			if(!bslProductInfo.getProdLuno().equals(bslProductInfoDcl.getProdLuno())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品炉号必须与父级待处理品炉号一致");
			}
			//校验钢种
			if(!bslProductInfo.getProdMaterial().equals(bslProductInfoDcl.getProdMaterial())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种必须与父级待处理品钢种一致");
			}
			//校验规格
			/*if(!bslProductInfo.getProdNorm().equals(bslProductInfoDcl.getProdNorm())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格必须是生产指令指定规格");
			}*/
			prodCompany = bslProductInfoDcl.getProdCompany();
			prodCustomer = bslProductInfoDcl.getProdCustomer();
			prodOrirawId = bslProductInfoDcl.getProdOrirawid();
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
			prodId = createProdIdWx();
			bslProductInfo.setProdId(prodId);//生成编号
			bslProductInfo.setProdType(DictItemOperation.产品类型_成品);
			bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
			bslProductInfo.setCrtDate(new Date());//创建日期当天
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_加工);
			bslProductInfo.setProdCompany(prodCompany);//厂家同原来一致
			bslProductInfo.setProdCustomer(prodCustomer);
			bslProductInfo.setProdOrirawid(prodOrirawId);
			
			int result = bslProductInfoMapper.insert(bslProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_成品);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslProductInfo.getProdCheckuser());//录入人
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
		return BSLResult.ok(returnProdId);
	}

	/**
	 * 外协厂产品修改
	 */
	@Override
	public BSLResult updateProdWxInfo(BslProductInfo bslProductInfo) {
		//校验管理员
		if(!DictItemOperation.管理员.equals(bslProductInfo.getProdInputuser())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许修改产品");
		}
		//获取原产品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据件号查询记录为空");
		}
		//校验状态，只有是1-入库中的状态才能修改
		/*if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的产品才能修改");
		}*/
		//校验不允许修改的内容
		//校验钢种
		/*if(!oldBslProductInfo.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品钢种不允许修改");
		}*/
		//校验规格
		/*if(!oldBslProductInfo.getProdNorm().equals(bslProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "产品规格不允许修改");
		}*/
		
		//校验完成开始修改
		bslProductInfo.setUpdDate(new Date());
		bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		return BSLResult.ok(bslProductInfo.getProdId());
	}

	/**
	 * 外协厂产品删除
	 */
	@Override
	public BSLResult deleteWxProd(String prodId, String user) {
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
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_成品);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(oldBslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(prodId);
	}

}
