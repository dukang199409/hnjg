package com.bsl.service.rawmaterialsmanager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.RawService;
import com.bsl.service.rawmaterialsmanager.ReceiptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 卷板台账实现类
 * duk-20190319
 */
@Service
public class RawServiceImpl implements RawService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired	 
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired	 
	ReceiptService receiptService;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_RAW_ID}")
	private String REDIS_NEXT_RAW_ID;
	
	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	

	/**
	 * 初始化查询所有0-卷板预入库信息 
	 */
	@Override
	public EasyUIDataGridResult getRawService(Integer page, Integer rows) {
		//查询条件状态 类型是0-卷板
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslProductInfoExample.setOrderByClause("`prod_id` desc");
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslProductInfos);
		//取记录总条数
		PageInfo<BslProductInfo> pageInfo = new PageInfo<>(bslProductInfos);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("rawServiceImpl");
		result.setMethodName("getRawService");
		return result;
	}

	/**
	 *根据条件查询0-卷板预入库信息 
	 */
	@Override
	public BSLResult getRawService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		if(!StringUtils.isBlank(queryCriteria.getProdStatus())){
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLuno())){
			criteria.andProdLunoLike("%"+queryCriteria.getProdLuno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			criteria.andProdOutPlanLike("%"+queryCriteria.getProdOutPlan()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdPlanNo())){
			criteria.andProdPlanNoLike("%"+ queryCriteria.getProdPlanNo()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+ queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdOrirawid())){
			criteria.andProdOrirawidLike("%"+ queryCriteria.getProdOrirawid()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdSource())){
			criteria.andProdSourceEqualTo(queryCriteria.getProdSource());
		}
		//开始日期结束日期
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
			bslProductInfoExample.setOrderByClause("`prod_id` desc,`prod_plan_no` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"rawServiceImpl","getRawService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 根据实体类获取产品信息
	 */
	@Override
	public BSLResult getRawServiceByProductInfo(BslProductInfoExample bslProductInfoExample) {
		 List<BslProductInfo> selectByExample = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 * 入库
	 */
	@Override
	public BSLResult addCfmRawInfo(BslProductInfo bslProductInfo) throws Exception {
		//查询厂家卷板信息（防止重复扫码）
		if(!StringUtils.isBlank(bslProductInfo.getProdOrirawid())){
			BslProductInfoExample bslProductInfoExampleOri = new BslProductInfoExample(); 
			com.bsl.pojo.BslProductInfoExample.Criteria criteriaOri = bslProductInfoExampleOri.createCriteria();
			criteriaOri.andProdOrirawidEqualTo(bslProductInfo.getProdOrirawid());
			int countOriRaw = bslProductInfoMapper.countByExample(bslProductInfoExampleOri);
			if(countOriRaw>0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"厂家卷号已经被录入过，请防止重复入库");
			}
		}
		
		//根据原料入库通知单号获取通知单信息(判断是0-自购卷板还是1-外接卷板)
		bslProductInfo.setProdSource("1");
		bslProductInfo.setProdPlanNo("hnjg0001");
		//设置预入库信息
		if(bslProductInfo.getCrtDate() == null){
			bslProductInfo.setCrtDate(new Date());//创建日期当天
		}else{
	        Calendar cal1 = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
	        cal1.setTime(bslProductInfo.getCrtDate());
	        cal1.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
	        cal1.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
	        cal1.set(Calendar.SECOND, now.get(Calendar.SECOND));
	        bslProductInfo.setCrtDate(cal1.getTime());
		}
		bslProductInfo.setProdType(DictItemOperation.产品类型_卷板);
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
		bslProductInfo.setProdNum(1);//件数默认为1
		bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
		bslProductInfo.setProdId(createRawId());
		if(StringUtils.isBlank(bslProductInfo.getProdLuno())){
			bslProductInfo.setProdLuno("bsl"+bslProductInfo.getProdOriId());
		}
		int result = bslProductInfoMapper.insert(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		
		//插入成功之后记录插入流水
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
		bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_入库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_卷板);//产品类型
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(bslProductInfo.getProdCheckuser());//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
//			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
//			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslProductInfo.getProdId());
	}

	/**
	 * 已入库入库单(卷板)信息修改
	 */
	@Override
	public BSLResult updateRawInfo(BslProductInfo bslProductInfo) {
		//获取原入库单信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据原料物料编码查询记录为空");
		}
		//校验状态，只有是1-已入库的状态才能修改（管理员可修改）
		/*if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只有在库的卷板才能在此修改");
		}*/
		//校验剩余重新入库打印重量与实际重量一致性
		if(DictItemOperation.产品来源_剩余入库.equals(oldBslProductInfo.getProdSource())){
			//剩余入库原钢卷号不允许修改
			if(!oldBslProductInfo.getProdOriId().equals(bslProductInfo.getProdOriId())){
				throw new BSLException(ErrorCodeInfo.错误类型_修改前参数校验, "剩余入库原钢卷号不允许修改");
			}
			//剩余入库修改质量需要校验
			if(!oldBslProductInfo.getProdRelWeight().equals(bslProductInfo.getProdRelWeight())){
				//获取原卷板信息进行校验
				BslProductInfo oriRaw = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOriId());
				if(oriRaw == null){
					throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据原卷号查询记录为空");
				}
				if(oriRaw.getProdRelWeight().equals(bslProductInfo.getProdRelWeight())){
					throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重量未改变,无需剩余材料重新入库,请直接对原产品未用退回");
				}
				if(oriRaw.getProdRelWeight() < bslProductInfo.getProdRelWeight()){
					throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重新入库重量不能大于原产品重量");
				}
			}
			
			if(!bslProductInfo.getProdPrintWeight().equals(bslProductInfo.getProdRelWeight())){
				throw new BSLException(ErrorCodeInfo.错误类型_修改前参数校验, "剩余入库实际重量应等于打印重量");
			}
			//剩余入库钢种不允许修改
			if(!oldBslProductInfo.getProdMaterial().equals(bslProductInfo.getProdMaterial())){
				throw new BSLException(ErrorCodeInfo.错误类型_修改前参数校验, "剩余入库钢种不允许修改");
			}
			//剩余入库发货仓库不允许修改
			if(!oldBslProductInfo.getProdSourceCompany().equals(bslProductInfo.getProdSourceCompany())){
				throw new BSLException(ErrorCodeInfo.错误类型_修改前参数校验, "剩余入库发货仓库不允许修改");
			}
		}
		//如果修改了炉号，更新纵剪带，成品炉号
		if(!oldBslProductInfo.getProdLuno().equals(bslProductInfo.getProdLuno())){
			//获取子产品信息
			BslProductInfoExample exampleHalf = new BslProductInfoExample();
			Criteria criteriaHalf = exampleHalf.createCriteria();
			criteriaHalf.andProdParentNoEqualTo(bslProductInfo.getProdId());
			List<BslProductInfo> halfList = bslProductInfoMapper.selectByExample(exampleHalf);
			if(halfList != null && halfList.size()>0){
				for (BslProductInfo bslProductInfoHalf : halfList) {
					//修改纵剪带炉号
					bslProductInfoHalf.setProdLuno(bslProductInfo.getProdLuno());
					bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoHalf);
					//修改产品炉号
					BslProductInfoExample exampleProd = new BslProductInfoExample();
					Criteria criteriaProd = exampleProd.createCriteria();
					criteriaProd.andProdParentNoEqualTo(bslProductInfoHalf.getProdId());
					List<BslProductInfo> prodList = bslProductInfoMapper.selectByExample(exampleProd);
					if(prodList != null && prodList.size()>0){
						for (BslProductInfo bslProductInfoProd : prodList) {
							//修改纵剪带炉号
							bslProductInfoProd.setProdLuno(bslProductInfo.getProdLuno());
							bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoProd);
						}
					}
				}
			}
		}
		
		//参数赋值
		bslProductInfo.setUpdDate(new Date());
		
		int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}
		return BSLResult.ok(bslProductInfo.getProdId());
	}

	/**
	 * 剩余卷板重新入库
	 */
	@Override
	public BSLResult addReRawInfo(BslProductInfo bslProductInfo) {
		//获取原入卷板信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(bslProductInfo.getProdOriId());
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据原原料物料编码查询记录为空");
		}
		if(!DictItemOperation.产品状态_已出库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原卷板状态必须是已出库");
		}
		if(oldBslProductInfo.getProdRelWeight().equals(bslProductInfo.getProdRelWeight())){
			throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重量未改变,无需剩余材料重新入库,请直接对原卷板未用退回");
		}
		if(oldBslProductInfo.getProdRelWeight() < bslProductInfo.getProdRelWeight()){
			throw new BSLException(ErrorCodeInfo.错误类型_新增前参数校验, "重新入库卷板重量不能大于原卷板");
		}
		
		//参数赋值
		bslProductInfo.setProdId(createRawId());//生成卷板id
		bslProductInfo.setCrtDate(new Date());//日期当天
		bslProductInfo.setProdMaterial(oldBslProductInfo.getProdMaterial());//钢种同原卷板
		bslProductInfo.setProdNum(1);
		bslProductInfo.setProdOrirawid(oldBslProductInfo.getProdOrirawid());
		//bslProductInfo.setProdOriId(oldBslProductInfo.getProdId());
		bslProductInfo.setProdLuno(oldBslProductInfo.getProdLuno());
		bslProductInfo.setProdRecordWeight(Float.valueOf(0));
		bslProductInfo.setProdPrintWeight(bslProductInfo.getProdRelWeight());//打印重量为实际重量
		bslProductInfo.setProdType(DictItemOperation.产品类型_卷板);
		bslProductInfo.setProdSourceCompany(oldBslProductInfo.getProdSourceCompany());//发货仓库同原卷板
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
		bslProductInfo.setProdSource(DictItemOperation.产品来源_剩余入库);
		bslProductInfo.setProdDclFlag(DictItemOperation.产品外协厂标志_本厂);
		bslProductInfo.setProdPlanNo(oldBslProductInfo.getProdPlanNo());//对应原料通知单号同原卷板
		bslProductInfo.setProdCompany(oldBslProductInfo.getProdCompany());//厂家同原来一致
		bslProductInfo.setProdCustomer(oldBslProductInfo.getProdCustomer());//客户同原来一致
		bslProductInfo.setProdParentNo(oldBslProductInfo.getProdParentNo());//对应来源同原卷板
		//开始插入
		int result = bslProductInfoMapper.insert(bslProductInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"重新入库失败");
		}
		
		//成功之后将原卷板信息更新为3-已完成
		BslProductInfo updateBslProductInfo = new BslProductInfo();
		updateBslProductInfo.setProdId(oldBslProductInfo.getProdId());
		updateBslProductInfo.setProdStatus(DictItemOperation.产品状态_已完成);
		updateBslProductInfo.setUpdDate(new Date());
		int resultUpdate = bslProductInfoMapper.updateByPrimaryKeySelective(updateBslProductInfo);
		if(resultUpdate<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultUpdate==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改原卷板状态失败");
		}
		
		//新增完成之后记录库存变动信息
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
		bslStockChangeDetail.setPlanSerno(bslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_剩余重新入库);//交易码
		bslStockChangeDetail.setProdType(DictItemOperation.产品类型_卷板);//产品类型
		bslStockChangeDetail.setProdOriId(bslProductInfo.getProdOriId());//原卷号
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetail.setCrtDate(new Date());
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		//在记录原卷板的完成信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(oldBslProductInfo.getProdId());//产品编号
		bslStockChangeDetailRaw.setPlanSerno(oldBslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_卷板);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(bslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok(bslProductInfo.getProdId());
	}
	
	/**
	 * 卷板自动生成编号
	 * YL+日期+2位序号
	 * @return
	 */
	public String createRawId() {
		long incr = jedisClient.incr(REDIS_NEXT_RAW_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("YL%s%02d", sdf.format(new Date()), incr);
		return rawId;
	}
	
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
	
	public BslProductInfo queryByPrimaryKey(String prodId) {
		return bslProductInfoMapper.selectByPrimaryKey(prodId);
	}

	/**
	 * 扫码新增数据处理
	 */
	@Override
	public BSLResult rawAddSMDeal(String rawInfo) {
		int length = rawInfo.length();
		if(length<=0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有要处理的数据");
		}
		BslProductInfo bslProductInfo = new BslProductInfo();
		String[] infos = rawInfo.split("\\|");
		bslProductInfo.setProdOrirawid(infos[1].substring(4).trim());
		bslProductInfo.setProdName(infos[2].substring(3).trim());
		bslProductInfo.setProdMaterial(DictItemOperation.getNumByKegMetarial(infos[3].substring(5).trim()));
		Float weight = Float.valueOf(infos[4].substring(3).replaceAll("Kg", "").trim());
		 //公斤要转化为吨
		weight = weight/1000;
        //保留三位小数
		weight = ((float)Math.round(weight*1000))/1000;
		bslProductInfo.setProdRecordWeight(weight);
		String norm1 = infos[6].substring(2).trim();
		String norm2 = infos[7].substring(2).trim();
		bslProductInfo.setProdNorm(norm1+"*"+norm2);
		Float prodLength = Float.valueOf(infos[8].substring(2).trim());
		 //mm要转化为米
		prodLength = prodLength/1000;
        //保留2位小数
		prodLength = ((float)Math.round(prodLength*100))/100;
		bslProductInfo.setProdLength(prodLength);
		return BSLResult.ok(bslProductInfo);
	}

	/**
	 * 删除
	 */
	@Override
	public BSLResult deleteRaw(String prodId) {
		//获取原入卷板信息进行校验
		BslProductInfo oldBslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(oldBslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据原原料物料编码查询记录为空");
		}
		if(!DictItemOperation.产品状态_已入库.equals(oldBslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原卷板状态必须是在库才能删除");
		}
		int resultStock = bslProductInfoMapper.deleteByPrimaryKey(prodId);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"删除失败");
		}
		//删除之后修改原通知单状态
		receiptService.updateBsInfoStatus(oldBslProductInfo.getProdPlanNo());
		//记录删除信息
		BslStockChangeDetail bslStockChangeDetailRaw = new BslStockChangeDetail();
		bslStockChangeDetailRaw.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetailRaw.setProdId(oldBslProductInfo.getProdId());//产品编号
		bslStockChangeDetailRaw.setPlanSerno(oldBslProductInfo.getProdPlanNo());//对应的原料入库单号
		bslStockChangeDetailRaw.setTransCode(DictItemOperation.库存变动交易码_删除);//交易码
		bslStockChangeDetailRaw.setProdType(DictItemOperation.产品类型_卷板);//产品类型
		bslStockChangeDetailRaw.setRubbishWeight(oldBslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetailRaw.setInputuser(oldBslProductInfo.getProdInputuser());//录入人
		bslStockChangeDetailRaw.setCrtDate(new Date());
		int resultStockRaw = bslStockChangeDetailMapper.insert(bslStockChangeDetailRaw);
		if(resultStockRaw<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStockRaw==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		
		return BSLResult.ok();
	}

}
