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
import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.dclprod.DclProdService;
import com.bsl.service.prodmanager.HalfProdOutPutService;
import com.bsl.service.prodmanager.ProdPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 待处理品入库实现类
 * duk-20190319
 */
@Service
public class DclProdServiceImpl implements DclProdService {

	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private ProdPlanService prodPlanService;
	@Autowired
	private BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired
	private HalfProdOutPutService halfProdOutPutService;
	
	@Value("REDIS_NEXT_DCLPROD_ID")
	private String REDIS_NEXT_DCLPROD_ID;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	

	/**
	 *根据条件查询待处理品信息 
	 */
	@Override
	public BSLResult getDclProdServiceByCriteria(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslDclProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslDclProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_待处理品);
		criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
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
			bslDclProductInfoExample.setOrderByClause("`crt_date` desc,`prod_id` desc,`prod_plan_no`");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslDclProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslDclProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"dclProdServiceImpl","getDclProdServiceByCriteria",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 待处理品入库
	 */
	@Override
	public BSLResult addDclProdinfo(BslProductInfo bslDclProductInfo,int sumNum) {
		//二次校验
		//获取正在执行的待处理品生产指令
		BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(bslDclProductInfo.getProdMakeJz());
		if(makePlanInfoExe == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到正在执行的生产指令信息，无法入库");
		}
		//校验指令号
		if(!makePlanInfoExe.getPlanId().equals(bslDclProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "生产指令号必须是执行中的指令");
		}
		//校验钢种
		if(!makePlanInfoExe.getProdMaterial().equals(bslDclProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品钢种必须是生产指令指定钢种");
		}
		//校验规格
		if(!makePlanInfoExe.getMakeProdNorm().equals(bslDclProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品规格必须是生产指令指定规格");
		}
		//校验父级盘号
		BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslDclProductInfo.getProdParentNo());
		if(parentProd == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级纵剪带信息");
		}
		if(!parentProd.getProdOutPlan().equals(makePlanInfoExe.getPlanId())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品父级纵剪带必须是因该指令出库的纵剪带");
		}
		
		//校验总重量
		//记录本次入库重量
		Float inWeight = bslDclProductInfo.getProdRelWeight();
		int checkInt = halfProdOutPutService.updateHalfProdStatus(bslDclProductInfo.getProdParentNo(),inWeight);
		if(checkInt == 1){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该纵剪带已生产重量加上本次需入库重量累计超出纵剪带本身重量，无法入库！");
		}
		
		//判断入库待处理待处理品数数,平分重量
		Float relWeight = bslDclProductInfo.getProdRelWeight()/sumNum;
		relWeight = ((float)Math.round(relWeight*1000))/1000;
		bslDclProductInfo.setProdRelWeight(relWeight);
		bslDclProductInfo.setProdPrintWeight(relWeight);
		
		//记录入库流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		String returnProdId = "";
		String prodId;
		
		for (int i = 0; i < sumNum; i++) {
			//校验完成，开始入库
			prodId = createProdId();
			bslDclProductInfo.setProdId(prodId);//生成编号
			bslDclProductInfo.setProdLuno(parentProd.getProdLuno());//炉号为父级炉号
			bslDclProductInfo.setProdOrirawid(parentProd.getProdOrirawid());
			bslDclProductInfo.setCrtDate(new Date());//创建日期当天
			bslDclProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslDclProductInfo.setProdType(DictItemOperation.产品类型_待处理品);
			bslDclProductInfo.setProdCompany(parentProd.getProdCompany());//厂家同原来一致
			bslDclProductInfo.setProdCustomer(parentProd.getProdCustomer());
			bslDclProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			int result = bslProductInfoMapper.insert(bslDclProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslDclProductInfo.getProdId());//待处理品编号
			bslStockChangeDetail.setPlanSerno(bslDclProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_待处理品);//待处理品类型
			bslStockChangeDetail.setRubbishWeight(bslDclProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslDclProductInfo.getProdCheckuser());//录入人
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
		return BSLResult.ok(returnProdId);
	}
	
	/**
	 * 待处理品补录入库
	 */
	@Override
	public BSLResult addDclProdinfoB(BslProductInfo bslDclProductInfo,int sumNum) {
		//二次校验
		//获取生产指令
		BslMakePlanInfo makePlanInfo = prodPlanService.getProdPlanInfo(bslDclProductInfo.getProdPlanNo());
		if(makePlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到生产指令信息，无法入库");
		}
		//校验钢种
		if(!makePlanInfo.getProdMaterial().equals(bslDclProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品钢种必须是生产指令指定钢种");
		}
		//校验规格
		if(!makePlanInfo.getMakeProdNorm().equals(bslDclProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品规格必须是生产指令指定规格");
		}
		//校验父级盘号
		BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslDclProductInfo.getProdParentNo());
		if(parentProd == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级纵剪带信息");
		}
		if(!parentProd.getProdOutPlan().equals(makePlanInfo.getPlanId())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品父级纵剪带必须是因该指令出库的纵剪带");
		}
		//校验总重量
		//记录本次入库重量
		Float inWeight = bslDclProductInfo.getProdRelWeight();
		int checkInt = halfProdOutPutService.updateHalfProdStatus(bslDclProductInfo.getProdParentNo(),inWeight);
		if(checkInt == 1){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该纵剪带已生产重量加上本次需入库重量累计超出纵剪带本身重量，无法入库！");
		}
		
		//判断入库待处理待处理品数数,平分重量
		Float relWeight = bslDclProductInfo.getProdRelWeight()/sumNum;
		relWeight = ((float)Math.round(relWeight*1000))/1000;
		bslDclProductInfo.setProdRelWeight(relWeight);
		bslDclProductInfo.setProdPrintWeight(relWeight);
		
		//记录入库流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		String returnProdId = "";
		String prodId;
		
		for (int i = 0; i < sumNum; i++) {
			//校验完成，开始入库
			prodId = createProdId();
			bslDclProductInfo.setProdId(prodId);//生成编号
			bslDclProductInfo.setProdLuno(parentProd.getProdLuno());//炉号为父级炉号
			bslDclProductInfo.setProdOrirawid(parentProd.getProdOrirawid());
			bslDclProductInfo.setCrtDate(new Date());//创建日期当天
			bslDclProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
			bslDclProductInfo.setProdType(DictItemOperation.产品类型_待处理品);
			bslDclProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
			bslDclProductInfo.setProdCompany(parentProd.getProdCompany());//厂家同原来一致
			bslDclProductInfo.setProdCustomer(parentProd.getProdCustomer());
			int result = bslProductInfoMapper.insert(bslDclProductInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"入库失败");
			}
			
			//插入成功之后记录插入流水
			bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(bslDclProductInfo.getProdId());//待处理品编号
			bslStockChangeDetail.setPlanSerno(bslDclProductInfo.getProdPlanNo());//对应的生产指令号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_待处理品);//待处理品类型
			bslStockChangeDetail.setRubbishWeight(bslDclProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(bslDclProductInfo.getProdCheckuser());//录入人
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
		return BSLResult.ok(returnProdId);
	}
	
	/**
	 * 已入库待处理品信息修改
	 */
	@Override
	public BSLResult updateDclProdPlan(BslProductInfo bslDclProductInfo) {
		//校验管理员
		if(!DictItemOperation.管理员.equals(bslDclProductInfo.getProdInputuser())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许修改待处理品信息");
		}
		//获取原待处理品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslDclProductInfo.getProdId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据待处理品编号查询记录为空");
		}
		//校验不允许修改的内容
		//校验指令号
		if(!oldBslProductInfo.getProdPlanNo().equals(bslDclProductInfo.getProdPlanNo())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品指令号不允许修改");
		}
		//校验钢种
		if(!oldBslProductInfo.getProdMaterial().equals(bslDclProductInfo.getProdMaterial())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品钢种不允许修改");
		}
		//校验规格
		if(!oldBslProductInfo.getProdNorm().equals(bslDclProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品规格不允许修改");
		}
		//校验父级盘号
		if(!oldBslProductInfo.getProdParentNo().equals(bslDclProductInfo.getProdParentNo())){
			BslProductInfo parentProd = bslProductInfoMapper.selectByPrimaryKey(bslDclProductInfo.getProdParentNo());
			if(parentProd == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "没有查询到指定的父级纵剪带信息");
			}
			if(!parentProd.getProdOutPlan().equals(oldBslProductInfo.getProdPlanNo())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "待处理品父级纵剪带必须是因该指令出库的纵剪带");
			}
			bslDclProductInfo.setProdLuno(parentProd.getProdLuno());
			bslDclProductInfo.setProdOrirawid(parentProd.getProdOrirawid());
			
			//校验总重量
			//记录本次入库重量
			Float inWeight = bslDclProductInfo.getProdRelWeight();
			int checkInt = halfProdOutPutService.updateHalfProdStatus(bslDclProductInfo.getProdParentNo(),inWeight);
			if(checkInt == 1){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该纵剪带已生产重量加上本次需入库重量累计超出纵剪带本身重量，无法修改！");
			}
			
			//修改完成之后判断是否需要更新纵剪带状态
			if(checkInt == 2){
				parentProd.setProdStatus(DictItemOperation.产品状态_已完成);
				parentProd.setUpdDate(new Date());
				bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
			}
			BslMakePlanInfo makePlanInfoExe = prodPlanService.getProdPlanInfoExe(bslDclProductInfo.getProdMakeJz());
			if(makePlanInfoExe != null){
				if(makePlanInfoExe.getPlanId().equals(bslDclProductInfo.getProdPlanNo())){
					parentProd.setProdStatus(DictItemOperation.产品状态_已出库);
					parentProd.setUpdDate(new Date());
					bslProductInfoMapper.updateByPrimaryKeySelective(parentProd);
				}
			}
		}
		
		//校验完成开始修改
		bslDclProductInfo.setUpdDate(new Date());
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslDclProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		return BSLResult.ok(bslDclProductInfo.getProdId());
	}
	
	/**
	 * 待处理品自动生成编号
	 * DCLP+日期+3位序号
	 * @return
	 */
	public String createProdId() {
		long incr = jedisClient.incr(REDIS_NEXT_DCLPROD_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prodId = String.format("DCLP%s%03d", sdf.format(new Date()), incr);
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

	/**
	 * 删除
	 */
	@Override
	public BSLResult delete(String prodId,String user) {
		//校验人员
		if(!DictItemOperation.管理员.equals(user)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有管理员才允许删除待处理品");
		}
		//获取原待处理品信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据待处理品编号查询记录为空");
		}
		//校验状态，只有是未处理的状态才能修改
		if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有未处理的待处理品才允许删除");
		}
		//开始删除
		int result = bslProductInfoMapper.deleteByPrimaryKey(prodId);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}
		
		//记录删除信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(oldBslProductInfo.getProdId());//待处理品编号
		bslStockChangeDetailRaw.setPlanSerno(oldBslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_删除);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_待处理品);//待处理品类型
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
		return BSLResult.ok(prodId);
	}

	/**
	 * 根据编号查询产品信息
	 */
	@Override
	public BSLResult getDclProdServiceById(String prodId) {
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		return BSLResult.ok(bslProductInfo);
	}

}
