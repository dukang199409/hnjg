package com.bsl.service.sale.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.bsl.mapper.BslWasteWeightInfoMapper;
import com.bsl.mapper.BslWxWasteWeightInfoMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.pojo.BslSaleInfoDetailExample.Criteria;
import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.pojo.BslWasteWeightInfoExample;
import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.pojo.BslWxWasteWeightInfoExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SalePlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 销售出库详细管理实现类
 * duk-20190319
 */
@Service
public class SaleDetailServiceImpl implements SaleDetailService{
	
	 @Autowired	 
	 BslBsPlanInfoMapper bslBsPlanInfoMapper;
	 @Autowired	 
	 BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	 @Autowired	 
	 BslWasteWeightInfoMapper bslWasteWeightInfoMapper;
	 @Autowired	 
	 BslProductInfoMapper bslProductInfoMapper;
	 @Autowired	 
	 SalePlanService salePlanService;
	 @Autowired	 
	 BslWxWasteWeightInfoMapper bslWxWasteWeightInfoMapper;
	 
	 @Autowired
	 private JedisClient jedisClient;
	
	 @Value("${REDIS_NEXT_SALE_PLAN_ID}")
	 private String REDIS_NEXT_SALE_PLAN_ID;
	
	/**
	 * 销售出库详细流水自动生成编号
	 * SD+日期+2位序号
	 * @return
	 */
	public String createSaleSernoId() {
		long incr = jedisClient.incr(REDIS_NEXT_SALE_PLAN_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("SD%s%02d", sdf.format(new Date()), incr);
		return rawId;
	}

	/**
	 * 加载界面时查询所有销售出库详细信息
	 */
	@Override
	public EasyUIDataGridResult getSaleDetailService(Integer page, Integer rows) {
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslSaleInfoDetailExample.setOrderByClause("`sale_serno` desc");
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslSaleInfoDetails);
		//取记录总条数
		PageInfo<BslSaleInfoDetail> pageInfo = new PageInfo<>(bslSaleInfoDetails);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("saleDetailServiceImpl");
		result.setMethodName("getSaleDetailService");
		return result;
	}

	/**
	 * 根据条件查询销售出库详细信息
	 */
	@Override
	public BSLResult getSaleDetailService(QueryCriteria queryCriteria) {
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		Criteria criteria = bslSaleInfoDetailExample.createCriteria();
		if(!StringUtils.isBlank(queryCriteria.getSalePlanId())){
			criteria.andSalePlanIdLike("%"+queryCriteria.getSalePlanId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleSerno())){
			criteria.andSaleSernoLike("%"+queryCriteria.getSaleSerno()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleFlag())){
			criteria.andSaleFlagEqualTo(queryCriteria.getSaleFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleStatus())){
			criteria.andSaleStatusEqualTo(queryCriteria.getSaleStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			criteria.andProdIdLike("%"+queryCriteria.getProdId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLength())){
			criteria.andProdLengthEqualTo(Float.valueOf(queryCriteria.getProdLength()));
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
			bslSaleInfoDetailExample.setOrderByClause("`sale_serno` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){	
				bslSaleInfoDetailExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()),Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		PageInfo<BslSaleInfoDetail> pageInfo = new PageInfo<BslSaleInfoDetail>(bslSaleInfoDetails);
		return BSLResult.ok(bslSaleInfoDetails,"saleDetailServiceImpl","getSaleDetailService",pageInfo.getTotal(),bslSaleInfoDetails);
	}

	/**
	 *根据销售单号查询类别以及能出库的产品信息（指定产品ID的销售出库用）
	 */
	@Override
	public BSLResult getBsFlagAndProdInfoService(String bsId) {
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货状态的销售通知单不允许再添加详细信息！");
		}*/
		String bsFlag = bslBsPlanInfo.getBsFlag();
		List<BslWasteWeightInfo> listWastes;
		List<BslWxWasteWeightInfo> listWasteWxs;
		List<BslProductInfo> listProds;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bsFlag", bsFlag);
		if(DictItemOperation.收发类别_废品发货.equals(bsFlag) || DictItemOperation.收发类别_废品转场发货.equals(bsFlag)){
			BslWasteWeightInfoExample bslWasteWeightInfoExample = new BslWasteWeightInfoExample();
			listWastes = bslWasteWeightInfoMapper.selectByExample(bslWasteWeightInfoExample);
			map.put("lists", listWastes);
		}else if (DictItemOperation.收发类别_委外仓废品发货.equals(bsFlag)) {
			BslWxWasteWeightInfoExample bslWxWasteWeightInfoExample = new BslWxWasteWeightInfoExample();
			listWasteWxs = bslWxWasteWeightInfoMapper.selectByExample(bslWxWasteWeightInfoExample);
			map.put("lists", listWasteWxs);
		}else{
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
			if(DictItemOperation.收发类别_卷板销售发货.equals(bsFlag) || DictItemOperation.收发类别_卷板退货.equals(bsFlag)){
				criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
			}else if(DictItemOperation.收发类别_半成品发货.equals(bsFlag)){
				criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
				criteria.andProdUserTypeEqualTo(DictItemOperation.纵剪带用途_外销);
			}else if(DictItemOperation.收发类别_成品发货.equals(bsFlag) || DictItemOperation.收发类别_成品转场发货.equals(bsFlag)){
				criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
				criteria.andProdDclFlagEqualTo(DictItemOperation.是否标志_否);
			}else if(DictItemOperation.收发类别_委外仓成品发货.equals(bsFlag)){
				criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
				criteria.andProdDclFlagEqualTo(DictItemOperation.是否标志_是);
			}else{
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"通知单类别不正确！");
			}
			listProds = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			//获取所有已经发货或者预约发货的产品记录,去除这些产品
			List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(new BslSaleInfoDetailExample());
			List<String> prodLists = new ArrayList<String>();
			for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
				prodLists.add(bslSaleInfoDetail.getProdId());
			}
			for (int i = 0; i < listProds.size(); i++) {
				if(prodLists.contains(listProds.get(i).getProdId())){
					listProds.remove(i);
				}
			}
			map.put("lists", listProds);
		}
		return BSLResult.ok(map);
	}

	/**
	 * 根据实体类查询详细信息
	 */
	@Override
	public BSLResult getSaleDetailServiceByBsDetailInfo(BslSaleInfoDetailExample bslSaleInfoDetailExample) {
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		return BSLResult.ok(bslSaleInfoDetails);
	}
	
	/**
	 * 新增指定类型的销售通知详细计划
	 */
	@Override
	public BSLResult addSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail) {
		//先获取原销售通知单信息
		String salePlanId = bslSaleInfoDetail.getSalePlanId();
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(salePlanId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货状态的销售通知单不允许再添加详细信息！");
		}*/
		if(DictItemOperation.收发类别_废品发货.equals(bslBsPlanInfo.getBsFlag()) || DictItemOperation.收发类别_委外仓废品发货.equals(bslBsPlanInfo.getBsFlag())
				|| DictItemOperation.收发类别_废品转场发货.equals(bslBsPlanInfo.getBsFlag())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"废品销售请点击固定产品销售新增详细计划！");
		}
		//获取该通知单下的所有详细，与本次新增的比对规格，钢种，定尺，方式，如已存在报错
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		Criteria criteria = bslSaleInfoDetailExample.createCriteria();
		criteria.andSalePlanIdLike(salePlanId);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		for (BslSaleInfoDetail bslSaleInfoDetailHas : bslSaleInfoDetails) {
			if(bslSaleInfoDetailHas.getSaleFlag().equals(bslSaleInfoDetail.getSaleFlag())
				&& bslSaleInfoDetailHas.getProdMaterial().equals(bslSaleInfoDetail.getProdMaterial())
				&& bslSaleInfoDetailHas.getProdNorm().equals(bslSaleInfoDetail.getProdNorm())){
				//成品发货还要比对定尺
				if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0){
					if(bslSaleInfoDetailHas.getProdLength().equals(bslSaleInfoDetail.getProdLength())){
						throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该销售通知单下已经有此类产品的计划信息，若要增加该类产品出库数量或重量，请直接做修改交易！");
					}
				}else{
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该销售通知单下已经有此类产品的计划信息，若要增加该类产品出库数量或重量，请直接做修改交易！");
				}
			}
		}
		//校验完成开始新增
		bslSaleInfoDetail.setSaleSerno(createSaleSernoId());//单笔销售流水
		bslSaleInfoDetail.setProdSumnum(0);//已出库数量
		bslSaleInfoDetail.setProdSumweight(0f);//已出库质量
		bslSaleInfoDetail.setProdJsnum(0);
		bslSaleInfoDetail.setProdJsweight(0f);
		bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
		bslSaleInfoDetail.setCrtDate(new Date());
		int result = bslSaleInfoDetailMapper.insert(bslSaleInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		//刷新销售通知单状态
		salePlanService.updateRefreshSalePlanStatus(salePlanId);
		return BSLResult.ok(bslSaleInfoDetail.getSaleSerno());
	}
	
	/**
	 * 修改指定类型的销售通知详细计划
	 */
	@Override
	public BSLResult updateSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail) {
		String saleSerno = bslSaleInfoDetail.getSaleSerno();
		//获取原详细计划
		BslSaleInfoDetail bslSaleInfoDetailOld = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetailOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售通知详细信息");
		}
		//获取原通知单信息,已发货的不允许修改
		BslBsPlanInfo bslBsPlanInfoOld = bslBsPlanInfoMapper.selectByPrimaryKey(bslSaleInfoDetailOld.getSalePlanId());
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查询到原销售通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfoOld.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货状态的销售通知单下属详细不允许修改！");
		}*/
		//先获取现在关联销售通知单信息（已发货的不允许修改）
		String salePlanId = bslSaleInfoDetail.getSalePlanId();
		//如果有根据此计划出库的产品，不能修改钢种规格定尺种类和原销售通知单，修改计划出库数量和质量要检验
		if((bslSaleInfoDetailOld.getProdSumnum() != null && bslSaleInfoDetailOld.getProdSumnum() > 0) || 
				( bslSaleInfoDetailOld.getProdSumweight() != null && bslSaleInfoDetailOld.getProdSumweight()>0)){
			if(!bslSaleInfoDetailOld.getSalePlanId().equals(bslSaleInfoDetail.getSalePlanId())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已经存在根据此计划出库的产品，此时不允许修改销售通知单号！");
			}
			if(!bslSaleInfoDetailOld.getProdMaterial().equals(bslSaleInfoDetail.getProdMaterial())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已经存在根据此计划出库的产品，此时不允许修改计划出库钢种！");
			}
			if(!bslSaleInfoDetailOld.getProdNorm().equals(bslSaleInfoDetail.getProdNorm())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已经存在根据此计划出库的产品，此时不允许修改计划出库规格！");
			}
			if (DictItemOperation.收发类别_成品发货.equals(bslBsPlanInfoOld.getBsFlag()) || DictItemOperation.收发类别_委外仓成品发货.equals(bslBsPlanInfoOld.getBsFlag()) 
					|| DictItemOperation.收发类别_成品转场发货.equals(bslBsPlanInfoOld.getBsFlag())) {
				if(!bslSaleInfoDetailOld.getProdLength().equals(bslSaleInfoDetail.getProdLength())){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已经存在根据此计划出库的产品，此时不允许修改计划出库定尺！");
				}
			}
			//判断计划出库数量有没有修改
			if(bslSaleInfoDetail.getSaleNum() != bslSaleInfoDetailOld.getSaleNum()){
				//若修改了，不能改的比当前已出库小
				if(bslSaleInfoDetail.getSaleNum()<bslSaleInfoDetailOld.getProdSumnum()){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"修改计划出库数量不能小于当前已出库数量！");
				}
			}
			//判断计划出库重量有没有修改
			if(bslSaleInfoDetail.getSaleWeight() != bslSaleInfoDetailOld.getSaleWeight()){
				//若修改了，不能改的比当前已出库小
				if(bslSaleInfoDetail.getSaleWeight()<bslSaleInfoDetailOld.getProdSumweight()){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"修改计划出库重量不能小于当前已出库重量！");
				}
			}
		}
		
		//如果修改了规格，钢种，定尺(非空时)，方式，获取该通知单下的所有详细，与本次修改后的比对，规格，钢种，定尺(非空时)，方式，如已存在报错
		if(!(bslSaleInfoDetail.getSaleFlag().equals(bslSaleInfoDetailOld.getSaleFlag()))
				|| !(bslSaleInfoDetail.getProdMaterial().equals(bslSaleInfoDetailOld.getProdMaterial()))
				|| !(bslSaleInfoDetail.getProdNorm().equals(bslSaleInfoDetailOld.getProdNorm()))
				|| ((bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0) 
						&& (!bslSaleInfoDetail.getProdLength().equals(bslSaleInfoDetailOld.getProdLength())))){
			BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
			Criteria criteria = bslSaleInfoDetailExample.createCriteria();
			criteria.andSalePlanIdEqualTo(salePlanId);
			List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
			for (BslSaleInfoDetail bslSaleInfoDetailHas : bslSaleInfoDetails) {
				if(bslSaleInfoDetailHas.getSaleFlag().equals(bslSaleInfoDetail.getSaleFlag())
					&& bslSaleInfoDetailHas.getProdMaterial().equals(bslSaleInfoDetail.getProdMaterial())
					&& bslSaleInfoDetailHas.getProdNorm().equals(bslSaleInfoDetail.getProdNorm())){
					//成品发货还要比对定尺
					if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0){
						if(bslSaleInfoDetailHas.getProdLength().equals(bslSaleInfoDetail.getProdLength())){
							throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该销售通知单下已经有此类产品的计划信息，若要增加该类产品出库数量或重量，请直接做修改交易！");
						}
					}else{
						throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该销售通知单下已经有此类产品的计划信息，若要增加该类产品出库数量或重量，请直接做修改交易！");
					}
				}
			}
		}
		//校验完成开始修改
		bslSaleInfoDetail.setCrtDate(new Date());
		int result = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改失败");
		}
		//刷新销售通知单详细状态
		updateSaleDetailStatus(saleSerno);
		//刷新销售通知单状态
		salePlanService.updateRefreshSalePlanStatus(salePlanId);
		return BSLResult.ok(bslSaleInfoDetail.getSaleSerno());
	}

	/**
	 * 新增指定编号的销售通知详细计划
	 */
	@Override
	public BSLResult addSaleDetailInfoById(String inputUser,String salePlanId, String[] arrays) {
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(salePlanId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货状态的销售通知单不允许再添加详细信息！");
		}*/
		BslSaleInfoDetail bslSaleInfoDetail = new BslSaleInfoDetail();
		bslSaleInfoDetail.setSalePlanId(salePlanId);//销售通知单号
		bslSaleInfoDetail.setSaleFlag(DictItemOperation.销售单出库标准_按产品编号销售出库);
		bslSaleInfoDetail.setSaleNum(1);
		bslSaleInfoDetail.setProdSumnum(0);
		bslSaleInfoDetail.setProdSumweight(0f);
		bslSaleInfoDetail.setProdJsnum(0);
		bslSaleInfoDetail.setProdJsweight(0f);
		bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
		bslSaleInfoDetail.setInputuser(inputUser);//录入人
		bslSaleInfoDetail.setCrtDate(new Date());
		//循环插入
		boolean flag = true;
		String rString = "";
		for (String saleDetailInfoString : arrays) {
			String[] saleDetailInfo = saleDetailInfoString.split(";");
			String prodId = saleDetailInfo[0];
			Float prodWeight = 0f;
			if(!"undefined".equals(saleDetailInfo[1]) && !"".equals(saleDetailInfo[1])){
				prodWeight = Float.parseFloat(saleDetailInfo[1]);
			}
			Float salePrice = 0f;
			if(!"undefined".equals(saleDetailInfo[2]) || !"".equals(saleDetailInfo[2])){
				salePrice = Float.parseFloat(saleDetailInfo[2]);
			}
			String remark = "";
			if(saleDetailInfo.length>=4){
				if(!"undefined".equals(saleDetailInfo[3])){
					 remark = saleDetailInfo[3];
				}
			}
			if(!DictItemOperation.收发类别_废品发货.equals(bslBsPlanInfo.getBsFlag()) && 
					!DictItemOperation.收发类别_委外仓废品发货.equals(bslBsPlanInfo.getBsFlag())
					&& !DictItemOperation.收发类别_废品转场发货.equals(bslBsPlanInfo.getBsFlag())){
				BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
				bslSaleInfoDetail.setProdNorm(bslProductInfo.getProdNorm());
				bslSaleInfoDetail.setProdMaterial(bslProductInfo.getProdMaterial());
				bslSaleInfoDetail.setProdLength(bslProductInfo.getProdLength());
			}
			//开始插入
			bslSaleInfoDetail.setSaleSerno(createSaleSernoId());//单笔销售流水
			bslSaleInfoDetail.setSaleWeight(prodWeight);//产品重量
			bslSaleInfoDetail.setProdId(prodId);//产品编号
			bslSaleInfoDetail.setSalePrice(salePrice);//销售单价
			bslSaleInfoDetail.setRemark(remark);
			int result = bslSaleInfoDetailMapper.insert(bslSaleInfoDetail);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
			}
			if(flag){
				rString = bslSaleInfoDetail.getSalePlanId();
				flag = false;
			}
		}
		//刷新销售通知单状态
		salePlanService.updateRefreshSalePlanStatus(salePlanId);
		return BSLResult.ok(rString);
	}
	
	/**
	 * 修改指定编号的销售通知详细计划
	 */
	@Override
	public BSLResult updateSaleDetailInfoById(BslSaleInfoDetail bslSaleInfoDetail) {
		//获取原产品信息
		String saleSerno = bslSaleInfoDetail.getSaleSerno();
		BslSaleInfoDetail bslSaleInfoDetailOld = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetailOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据流水号未查询到销售出库详细信息");
		}
		//获取原通知单信息,已发货的不允许修改
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslSaleInfoDetailOld.getSalePlanId());
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查询到原销售通知单信息");
		}
		/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货状态的销售通知单下属详细不允许修改！");
		}*/
		//非废品不允许修改重量
		String bsFlag = bslBsPlanInfo.getBsFlag();
		if(!bslSaleInfoDetail.getSaleWeight().equals(bslSaleInfoDetailOld.getSaleWeight())){
			if(DictItemOperation.收发类别_废品发货.equals(bsFlag) || DictItemOperation.收发类别_委外仓废品发货.equals(bsFlag)
					|| DictItemOperation.收发类别_废品转场发货.equals(bsFlag) ){
				//是废品时,不允许改的比已出库的还少
				if(bslSaleInfoDetail.getSaleWeight()<bslSaleInfoDetailOld.getProdSumweight()){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"修改计划出库重量不能小于当前已出库数量！");
				}
				//修改完校验是不是满足出库条件
				Float saleWeightH = bslSaleInfoDetail.getSaleWeight()*1.05f;
				Float saleWeightL = bslSaleInfoDetail.getSaleWeight()*0.95f;
				if(bslSaleInfoDetailOld.getProdSumweight() >= saleWeightL 
						&& bslSaleInfoDetailOld.getProdSumweight() <= saleWeightH ){
					bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
				}else{
					bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
				}
			}else{
				//非废品不允许修改重量
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"根据编号设定的详细销售出库计划,若产品类型不是废品,不允许修改计划出库重量！");
			}
		}
		
		//进行修改
		//bslSaleInfoDetail.setUpdDate(new Date());//修改日期当天
		int result = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}		
		//修改完成之后刷新通知单状态
		salePlanService.updateRefreshSalePlanStatus(bslSaleInfoDetail.getSalePlanId());
		return BSLResult.ok(saleSerno);
	}

	/**
	 * 删除销售出库详细信息
	 */
	@Override
	public BSLResult deleteSaleDetailInfo(BslSaleInfoDetail bslSaleInfoDetail) {
		String saleSerno = bslSaleInfoDetail.getSaleSerno();
		BslSaleInfoDetail bslSaleInfoDetailSelect = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetailSelect == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据流水号未查询到销售出库详细信息");
		}
		if(bslSaleInfoDetailSelect.getProdSumnum() > 0 || bslSaleInfoDetailSelect.getProdSumweight() > 0){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已经有因为该详细流水出库的产品或废品，无法删除");
		}
		//没有因为该详细计划出库的产品才能删除
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdSaleSernoEqualTo(saleSerno);
		List<BslProductInfo> listProds = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(listProds != null && listProds.size()>0){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已经有因为该详细流水出库的产品，无法删除");
		}
		//删除操作
		int result = bslSaleInfoDetailMapper.deleteByPrimaryKey(saleSerno);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}
		//刷新销售通知单状态
		salePlanService.updateRefreshSalePlanStatus(bslSaleInfoDetailSelect.getSalePlanId());
		return BSLResult.ok(saleSerno);
	}

	/**
	 * 刷新该详细计划状态
	 */
	@Override
	public BSLResult updateSaleDetailStatus(String saleSerno) {
		String status;
		//获取该详细计划信息
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetail == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未查询到该详细计划信息");
		}
		//获取因为该详细出库的产品
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdSaleSernoEqualTo(saleSerno);
		List<BslProductInfo> listProds = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		int countNum = listProds.size();
		int saleNum = 0;
		Float outWeight = 0f;
		if(listProds == null || listProds.size()<=0){
			status = DictItemOperation.销售单出库标志_未达标准;
		}else{
			for (BslProductInfo bslProductInfo : listProds) {
				if(DictItemOperation.产品状态_已发货.equals(bslProductInfo.getProdStatus())
						|| DictItemOperation.产品状态_已转场.equals(bslProductInfo.getProdStatus())){
					saleNum ++;
				}
				outWeight += bslProductInfo.getProdRelWeight();
			}
			//判断出库方式
			if(DictItemOperation.销售单出库标准_按产品编号销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
				if(countNum >= 1){
					if(DictItemOperation.产品状态_已发货.equals(listProds.get(0).getProdStatus())
							|| DictItemOperation.产品状态_已转场.equals(listProds.get(0).getProdStatus())){
						status = DictItemOperation.销售单出库标志_已发货;
					}else{
						status = DictItemOperation.销售单出库标志_已达标准;
					}
				}else{
					status = DictItemOperation.销售单出库标志_未达标准;
				}
			}else if(DictItemOperation.销售单出库标准_按数量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
				if(bslSaleInfoDetail.getSaleNum() == countNum){
					if(countNum == saleNum){
						status = DictItemOperation.销售单出库标志_已发货;
					}else{
						status = DictItemOperation.销售单出库标志_已达标准;
					}
				}else{
					status = DictItemOperation.销售单出库标志_未达标准;
				}
			}else{
				Float saleWeightH = bslSaleInfoDetail.getSaleWeight()*1.05f;
				Float saleWeightL = bslSaleInfoDetail.getSaleWeight()*0.95f;
				if(outWeight >= saleWeightL && outWeight <= saleWeightH){
					if(countNum == saleNum){
						status = DictItemOperation.销售单出库标志_已发货;
					}else{
						status = DictItemOperation.销售单出库标志_已达标准;
					}
				}else{
					status = DictItemOperation.销售单出库标志_未达标准;
				}
			}
		}
		
		BslSaleInfoDetail updateDetail = new BslSaleInfoDetail();
		updateDetail.setProdSumnum(countNum);
		updateDetail.setProdSumweight(outWeight);
		updateDetail.setSaleSerno(saleSerno);
		updateDetail.setSaleStatus(status);
		int result = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(updateDetail);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改状态失败");
		}
		return BSLResult.ok(saleSerno);
	}

	@Override
	public BslSaleInfoDetail getBslSaleInfoDetailById(String planDetailId) {
		return bslSaleInfoDetailMapper.selectByPrimaryKey(planDetailId);
	}
	


	/**
	 * 根据单号id查询废品可发货详细信息
	 */
	@Override
	public BSLResult getWaitSaleWassteGroups(String salePlanId) {
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(salePlanId);
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到销售通知单信息");
		}
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		Criteria criteria = bslSaleInfoDetailExample.createCriteria();
		criteria.andSalePlanIdEqualTo(salePlanId);
		criteria.andSaleStatusEqualTo(DictItemOperation.销售单出库标志_已达标准);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		PageInfo<BslSaleInfoDetail> pageInfo = new PageInfo<BslSaleInfoDetail>(bslSaleInfoDetails);
		return BSLResult.ok(bslSaleInfoDetails,"saleDetailServiceImpl","getWaitSaleWassteGroups",pageInfo.getTotal(),bslSaleInfoDetails);
	}

}
