package com.bsl.service.sale.impl;

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
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.mapper.BslStockChangeDetailMapper;
import com.bsl.mapper.BslWasteWeightInfoMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SalePlanService;
import com.bsl.service.sale.SaleProdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 销售出库产品管理实现类 duk-20190319
 */
@Service
public class SaleProdServiceImpl implements SaleProdService {

	@Autowired
	BslBsPlanInfoMapper bslBsPlanInfoMapper;
	@Autowired
	BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	@Autowired
	BslWasteWeightInfoMapper bslWasteWeightInfoMapper;
	@Autowired
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired	 
	SalePlanService salePlanService;
	@Autowired	 
	SaleDetailService saleDetailService;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_NEXT_STOCKCHANGE_ID}")
	private String REDIS_NEXT_STOCKCHANGE_ID;

	/**
	 * 库存变动流水自动生成编号 CH+日期+4位序号
	 * 
	 * @return
	 */
	public String createStockChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_STOCKCHANGE_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CH%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}

	/**
	 * 根据条件查询可以出库的产品信息
	 */
	@Override
	public BSLResult getCanOutProdsService(QueryCriteria queryCriteria) {
		List<BslProductInfo> prodsSelect = new ArrayList<BslProductInfo>();
		long total = 0;
		List<String> prodIds = new ArrayList<String>();
		//根据三个条件分别查询，然后取交集
		if(!StringUtils.isBlank(queryCriteria.getSalePlanId())){
			List<BslProductInfo> prodsBySalePlanId = getProdsBySalePlanId(queryCriteria.getSalePlanId());
			if(prodsBySalePlanId != null && prodsBySalePlanId.size()>0){
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsBySalePlanId) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prodIds.size()>0){
					prodIds.retainAll(prodIds1);
				}else{
					prodIds.addAll(prodIds1);
				}
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleSerno())){
			List<BslProductInfo> prodsBySaleSerno = getProdsBySaleSerno(queryCriteria.getSaleSerno());
			if(prodsBySaleSerno != null && prodsBySaleSerno.size()>0){
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsBySaleSerno) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prodIds.size()>0){
					prodIds.retainAll(prodIds1);
				}else{
					prodIds.addAll(prodIds1);
				}
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			List<BslProductInfo> prodsByProdId = getProdsByProdId(queryCriteria.getProdId());
			if(prodsByProdId != null && prodsByProdId.size()>0){	
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsByProdId) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prodIds.size()>0){
					prodIds.retainAll(prodIds1);
				}else{
					prodIds.addAll(prodIds1);
				}
			}
		}
		//为了分页查询，重新查下
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		if(prodIds.size()>0){
			criteria.andProdIdIn(prodIds);
			if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
				selectExample.setOrderByClause("`prod_id` desc");
			}else{
				String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
				if(!StringUtils.isBlank(sortSql)){	
					selectExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
				}
			}
			// 分页处理
			PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
			// 调用sql查询
			prodsSelect = bslProductInfoMapper.selectByExample(selectExample);
			PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(prodsSelect);
			total = pageInfo.getTotal();
		}
		return BSLResult.ok(total,prodsSelect);
	}

	/**
	 * 根据通知单号查询可以出库的产品信息
	 */
	public List<BslProductInfo> getProdsBySalePlanId(String salePlanId){
		//记录返回可出库产品信息
		List<BslProductInfo> prodsBySalePlanId = new ArrayList<BslProductInfo>();
		//查询实体类
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		//已入库的才能出库
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		/*//获取通知单对应类别
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(salePlanId);
		if(DictItemOperation.收发类别_待处理品处理发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_待处理品);
			prodsBySalePlanId = bslProductInfoMapper.selectByExample(selectExample);
		}else{*/
		//查询该通知单下所有计划
		BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
		com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteriaDetail = bslSaleInfoDetailExample.createCriteria();
		criteriaDetail.andSalePlanIdEqualTo(salePlanId);
		List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
		//循环查询可以出库的信息（先去重再增加）
		for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
			List<BslProductInfo> prods = getProdsBySaleSerno(bslSaleInfoDetail.getSaleSerno());
			if(prods != null && prods.size()>0){
				prodsBySalePlanId.removeAll(prods);
				prodsBySalePlanId.addAll(prods);
			}
		}
		//}
		return prodsBySalePlanId;
	}
	
	/**
	 * 根据流水计划查询可以出库的产品信息
	 */
	public List<BslProductInfo> getProdsBySaleSerno(String saleSerno){
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		//已入库的才能出库
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		//获取流水信息
		BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
		if(bslSaleInfoDetail == null){
			return null;
		}
		if(DictItemOperation.销售单出库标志_已发货.equals(bslSaleInfoDetail.getSaleStatus())){
			return null;
		}
		//获取通知单对应类别
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslSaleInfoDetail.getSalePlanId());
		if(DictItemOperation.收发类别_半成品发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
			criteria.andProdUserTypeNotEqualTo(DictItemOperation.纵剪带用途_自用);
		}else if(DictItemOperation.收发类别_卷板退货.equals(bslBsPlanInfo.getBsFlag()) || DictItemOperation.收发类别_卷板销售发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		}else if(DictItemOperation.收发类别_成品发货.equals(bslBsPlanInfo.getBsFlag()) || DictItemOperation.收发类别_成品转场发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
			criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		}else if(DictItemOperation.收发类别_委外仓成品发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_成品);
			criteria.andProdDclFlagNotEqualTo(DictItemOperation.产品外协厂标志_本厂);
		}else if(DictItemOperation.收发类别_待处理品处理发货.equals(bslBsPlanInfo.getBsFlag())){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_待处理品);
			criteria.andProdDclFlagEqualTo(DictItemOperation.产品外协厂标志_本厂);
		}else{
			return null;
		}
		
		//如果该流水按产品编号销售出库，增加产品编号
		if(DictItemOperation.销售单出库标准_按产品编号销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			criteria.andProdIdEqualTo(bslSaleInfoDetail.getProdId());
		}else{
			//如果是按重量/数量出库，增加规格钢种
			criteria.andProdMaterialEqualTo(bslSaleInfoDetail.getProdMaterial());
			criteria.andProdNormEqualTo(bslSaleInfoDetail.getProdNorm());
			//成品发货还要增加定尺
			if(DictItemOperation.收发类别_成品发货.equals(bslBsPlanInfo.getBsFlag()) || 
					DictItemOperation.收发类别_委外仓成品发货.equals(bslBsPlanInfo.getBsFlag())
						 || DictItemOperation.收发类别_成品转场发货.equals(bslBsPlanInfo.getBsFlag())){
				criteria.andProdLengthEqualTo(bslSaleInfoDetail.getProdLength());
			}
		}
		List<BslProductInfo> prodsBySaleSerno = bslProductInfoMapper.selectByExample(selectExample);
		return prodsBySaleSerno;
	}

	/**
	 * 根据编号查询可以出库的产品信息
	 */
	public List<BslProductInfo> getProdsByProdId(String prodId){
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		//已入库的才能出库
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		criteria.andProdIdEqualTo(prodId);
		List<BslProductInfo> prodsByProdId = bslProductInfoMapper.selectByExample(selectExample);
		return prodsByProdId;
	}
	
	/**
	 * 根据条件查询已经出库的产品信息
	 */
	@Override
	public BSLResult getOutProdsService(QueryCriteria queryCriteria) {
		List<BslProductInfo> prodsSelect = new ArrayList<BslProductInfo>();
		long total = 0;
		List<String> prods = new ArrayList<String>();
		//根据三个条件分别查询，然后取交集
		if(!StringUtils.isBlank(queryCriteria.getSalePlanId())){
			List<BslProductInfo> prodsBySalePlanId = getOutProdsBySalePlanId(queryCriteria.getSalePlanId());
			if(prodsBySalePlanId != null && prodsBySalePlanId.size()>0){
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsBySalePlanId) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prods.size()>0){
					prods.retainAll(prodIds1);
				}else{
					prods.addAll(prodIds1);
				}
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getSaleSerno())){
			List<BslProductInfo> prodsBySaleSerno = getOutProdsBySaleSerno(queryCriteria.getSaleSerno());
			if(prodsBySaleSerno != null && prodsBySaleSerno.size()>0){
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsBySaleSerno) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prods.size()>0){
					prods.retainAll(prodIds1);
				}else{
					prods.addAll(prodIds1);
				}
			}
		}
		if(!StringUtils.isBlank(queryCriteria.getProdId())){
			List<BslProductInfo> prodsByProdId = getOutProdsByProdId(queryCriteria.getProdId());
			if(prodsByProdId != null && prodsByProdId.size()>0){
				List<String> prodIds1 = new ArrayList<String>();
				for (BslProductInfo bslProductInfo : prodsByProdId) {
					prodIds1.add(bslProductInfo.getProdId());
				}
				if(prods.size()>0){
					prods.retainAll(prodIds1);
				}else{
					prods.addAll(prodIds1);
				}
			}
		}
		//为了分页查询，重新查下
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		if(prods.size()>0){
			criteria.andProdIdIn(prods);
			if(!StringUtils.isBlank(queryCriteria.getProdStatus())){
				criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
			}
			if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
				selectExample.setOrderByClause("`prod_status` desc,`prod_id` desc");
			}else{
				String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
				if(!StringUtils.isBlank(sortSql)){	
					selectExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
				}
			}
			// 分页处理
			PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
			// 调用sql查询
			prodsSelect = bslProductInfoMapper.selectByExample(selectExample);
			PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(prodsSelect);
			total = pageInfo.getTotal();
		}
		return BSLResult.ok(total,prodsSelect);
	}
	
	/**
	 * 根据通知单号查询已经出库/已发货的产品信息
	 */
	public List<BslProductInfo> getOutProdsBySalePlanId(String salePlanId){
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		//已入库的才能出库
		List<String> lists = new ArrayList<String>();
		lists.add(DictItemOperation.产品状态_出库待发货);
		lists.add(DictItemOperation.产品状态_已发货);
		criteria.andProdStatusIn(lists);
		//产品出库单号等于对应单号
		criteria.andProdOutPlanEqualTo(salePlanId);
		List<BslProductInfo> prodsBySalePlanId = bslProductInfoMapper.selectByExample(selectExample);
		return prodsBySalePlanId;
	}
	
	/**
	 * 根据流水计划查询已经出库/已发货的产品信息
	 */
	public List<BslProductInfo> getOutProdsBySaleSerno(String saleSerno){
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		List<String> lists = new ArrayList<String>();
		lists.add(DictItemOperation.产品状态_出库待发货);
		lists.add(DictItemOperation.产品状态_已发货);
		criteria.andProdStatusIn(lists);
		criteria.andProdSaleSernoEqualTo(saleSerno);
		List<BslProductInfo> prodsBySaleSerno = bslProductInfoMapper.selectByExample(selectExample);
		return prodsBySaleSerno;
	}
	
	/**
	 * 根据编号查询已经出库/已发货的产品信息
	 */
	public List<BslProductInfo> getOutProdsByProdId(String prodId){
		BslProductInfoExample selectExample = new BslProductInfoExample();
		Criteria criteria = selectExample.createCriteria();
		List<String> lists = new ArrayList<String>();
		lists.add(DictItemOperation.产品状态_出库待发货);
		lists.add(DictItemOperation.产品状态_已发货);
		criteria.andProdStatusIn(lists);
		criteria.andProdIdEqualTo(prodId);
		List<BslProductInfo> prodsByProdId = bslProductInfoMapper.selectByExample(selectExample);
		return prodsByProdId;
	}

	/**
	 * 发货出库
	 */
	@Override
	public BSLResult updateSaleProdOutPut(String prodId,String salePlanId,String saleSerno,String prodCheckuser,Float prodOutWeight){
		//获取该产品信息
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未查询到该产品信息");
		}
		if(DictItemOperation.产品类型_半成品.equals(bslProductInfo.getProdType()) && DictItemOperation.纵剪带用途_自用.equals(bslProductInfo.getProdUserType())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "自用类型的纵剪带不允许外销！");
		}
		if(!DictItemOperation.产品状态_已入库.equals(bslProductInfo.getProdStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "非入库状态的产品无法销售出库！");
		}
		//如果详细计划不是空，判断是否符合规则
		if(!StringUtils.isBlank(saleSerno)){
			BslSaleInfoDetail bslSaleInfoDetail = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
			if(bslSaleInfoDetail == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未查询到该详细计划信息");
			}
			if(!StringUtils.isBlank(salePlanId) && !salePlanId.equals(bslSaleInfoDetail.getSalePlanId())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "所输入详细计划信息不是所输入的销售单下计划，无法出库！");
			}
			Map<String,String> mapResult = isOutSuccess(bslProductInfo,bslSaleInfoDetail,prodCheckuser,prodOutWeight);
			if("0000".equals(mapResult.get("code"))){
				return BSLResult.ok(prodId);
			}else{
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,mapResult.get("msg"));
			}
		}else{
			//saleSerno为空则salePlanId不为空
			//获取该通知单状态
			BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(salePlanId);
			if(bslBsPlanInfo == null){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未查询到该销售通知单信息");
			}
			/*if(DictItemOperation.通知单状态_已发货.equals(bslBsPlanInfo.getBsStatus())){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该销售通知单已经发货，无法出库");
			}*/
			//判断销售出库单类型，若是待处理品，则直接发货
			/*if(DictItemOperation.收发类别_待处理品处理发货.equals(bslBsPlanInfo.getBsFlag())){
				if(!DictItemOperation.产品类型_待处理品.equals(bslProductInfo.getProdType())){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该通知单只允许待处理品发货！");
				}
				//判断完成开始出库
				Map<String,String> mapResult = isOutDclSuccess(bslProductInfo,bslBsPlanInfo,prodCheckuser,prodOutWeight);
				if(!"0000".equals(mapResult.get("code"))){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该待处理品出库失败，编号："+prodId+"，原因："+mapResult.get("msg"));
				}
			}else{*/
				//获取该通知单下所有详细信息
				BslSaleInfoDetailExample bslSaleInfoDetailExample = new BslSaleInfoDetailExample();
				com.bsl.pojo.BslSaleInfoDetailExample.Criteria criteriaDetail = bslSaleInfoDetailExample.createCriteria();
				criteriaDetail.andSalePlanIdEqualTo(salePlanId);
				List<BslSaleInfoDetail> bslSaleInfoDetails = bslSaleInfoDetailMapper.selectByExample(bslSaleInfoDetailExample);
				Boolean isOut = false;
				for (BslSaleInfoDetail bslSaleInfoDetail : bslSaleInfoDetails) {
					//循环校验是否满足出库条件
					Map<String,String> mapResult = isOutSuccess(bslProductInfo,bslSaleInfoDetail,prodCheckuser,prodOutWeight);
					if("0000".equals(mapResult.get("code"))){
						isOut = true;
						break;
					}
				}
				//说明不满足出库条件
				if(!isOut){
					throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该产品不满足销售通知单指定信息，无法出库，产品编号："+prodId);
				}
			}
		//}
		
		return BSLResult.ok(prodId);
	}
	
	/**
	 * 根据产品编号与详细计划判断是否满足，以及满足之后开始出库
	 */
	public Map<String,String> isOutSuccess(BslProductInfo bslProductInfo,BslSaleInfoDetail bslSaleInfoDetail,String prodCheckuser,Float prodOutWeight){
		Map<String,String> map = new HashMap<String,String>();
		String salePlanId = bslSaleInfoDetail.getSalePlanId();
		String prodId = bslProductInfo.getProdId();
		if(DictItemOperation.销售单出库标准_按产品编号销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			if(!prodId.equals(bslSaleInfoDetail.getProdId())){
				map.put("code", "1111");
				map.put("msg", "所输入产品信息与详细计划指定产品不符，无法出库！");
				return map;
			}
			if(bslSaleInfoDetail.getProdSumnum() >= 1){
				map.put("code", "1111");
				map.put("msg", "该详细计划指定产品已经出库，无法再次出库！");
				return map;
			}
			//满足条件开始出库
			//1-产品信息改为出库待发货
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_出库待发货);//修改状态
			bslProductInfo.setProdOutPlan(salePlanId);//出库指令
			bslProductInfo.setProdSaleSerno(bslSaleInfoDetail.getSaleSerno());
			bslProductInfo.setProdOutWeight(prodOutWeight);//出库质量
			bslProductInfo.setUpdDate(new Date());
			int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
			if(resultProd<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultProd==0){
				map.put("code", "1111");
				map.put("msg", "修改产品状态失败！");
				return map;
			}
			//2-修改该笔详细计划
			bslSaleInfoDetail.setProdSumnum(1);//已出库数量改为1
			bslSaleInfoDetail.setProdSumweight(bslProductInfo.getProdRelWeight());//出库质量为产品实际质量
			bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
			int resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
			if(resultDetail<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultDetail==0){
				map.put("code", "1111");
				map.put("msg", "修改详细计划状态失败");
				return map;
			}
			//3-刷新通知单状态
			salePlanService.updateRefreshSalePlanStatus(salePlanId);
			//4-记录库存变动信息
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(salePlanId);//对应的生产指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_出售出库);//交易码
			bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(prodCheckuser);//录入人
			bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
			bslStockChangeDetail.setCrtDate(new Date());
			bslStockChangeDetail.setRemark("出售出库");
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultStock==0){
				map.put("code", "1111");
				map.put("msg", "新增库存变动表失败");
				return map;
			}
			
		}else if(DictItemOperation.销售单出库标准_按数量销售出库.equals(bslSaleInfoDetail.getSaleFlag())){
			//如果是按数量出库
			//校验已经出库数量是否是最大值
			if(bslSaleInfoDetail.getProdSumnum() >= bslSaleInfoDetail.getSaleNum()){
				map.put("code", "1111");
				map.put("msg", "该详细计划指定出库数量已达最大值，无法出库！");
				return map;
			}
			//检验规格钢种
			if(!bslProductInfo.getProdNorm().equals(bslSaleInfoDetail.getProdNorm())){
				map.put("code", "1111");
				map.put("msg", "该产品规格与该详细计划指定出库规格不符，无法出库！");
				return map;
			}
			if(!bslProductInfo.getProdMaterial().equals(bslSaleInfoDetail.getProdMaterial())){
				map.put("code", "1111");
				map.put("msg", "该产品钢种与该详细计划指定出库钢种不符，无法出库！");
				return map;
			}
			//定尺不为空（成品出库）
			if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0){
				if(!bslProductInfo.getProdLength().equals(bslSaleInfoDetail.getProdLength())){
					map.put("code", "1111");
					map.put("msg", "该产品定尺与该详细计划指定出库定尺不符，无法出库！");
					return map;
				}
			}
			//校验完成，开始出库
			//1-产品信息改为出库待发货
			bslProductInfo.setProdSaleSerno(bslSaleInfoDetail.getSaleSerno());
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_出库待发货);//修改状态
			bslProductInfo.setProdOutPlan(salePlanId);//出库指令
			bslProductInfo.setProdOutWeight(prodOutWeight);//出库质量
			bslProductInfo.setUpdDate(new Date());
			int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
			if(resultProd<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultProd==0){
				map.put("code", "1111");
				map.put("msg", "修改产品状态失败！");
				return map;
			}
			//2-修改该笔详细计划
			bslSaleInfoDetail.setProdSumnum(bslSaleInfoDetail.getProdSumnum()+1);//已出库数量+1
			bslSaleInfoDetail.setProdSumweight(bslSaleInfoDetail.getProdSumweight() + bslProductInfo.getProdRelWeight());//出库质量+产品实际质量
			if(bslSaleInfoDetail.getProdSumnum() == bslSaleInfoDetail.getSaleNum()){
				bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
			}
			int resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
			if(resultDetail<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultDetail==0){
				map.put("code", "1111");
				map.put("msg", "修改详细计划状态失败");
				return map;
			}
			//3-刷新通知单状态
			salePlanService.updateRefreshSalePlanStatus(salePlanId);
			//4-记录库存变动
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(salePlanId);//对应的生产指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_出售出库);//交易码
			bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(prodCheckuser);//录入人
			bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
			bslStockChangeDetail.setCrtDate(new Date());
			bslStockChangeDetail.setRemark("出售出库");
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultStock==0){
				map.put("code", "1111");
				map.put("msg", "新增库存变动表失败");
				return map;
			}
		}else{
			//如果是按重量出库
			//检验规格钢种
			if(!bslProductInfo.getProdNorm().equals(bslSaleInfoDetail.getProdNorm())){
				map.put("code", "1111");
				map.put("msg", "该产品规格与该详细计划指定出库规格不符，无法出库！");
				return map;
			}
			if(!bslProductInfo.getProdMaterial().equals(bslSaleInfoDetail.getProdMaterial())){
				map.put("code", "1111");
				map.put("msg", "该产品钢种与该详细计划指定出库钢种不符，无法出库！");
				return map;
			}
			//定尺不为空（成品出库）
			if(bslSaleInfoDetail.getProdLength() != null && bslSaleInfoDetail.getProdLength() > 0){
				if(!bslProductInfo.getProdLength().equals(bslSaleInfoDetail.getProdLength())){
					map.put("code", "1111");
					map.put("msg", "该产品定尺与该详细计划指定出库定尺不符，无法出库！");
					return map;
				}
			}
			Float saleWeightH = bslSaleInfoDetail.getSaleWeight()*1.05f;
			Float saleWeightL = bslSaleInfoDetail.getSaleWeight()*0.95f;
			//增加后重量
			Float sumWeight = bslSaleInfoDetail.getProdSumweight() + bslProductInfo.getProdRelWeight();
			if(sumWeight > saleWeightH){
				map.put("code", "1111");
				map.put("msg", "出库之后该计划累计出库重量大于限定重量，无法出库！");
				return map;
			}
			
			//校验完成，开始出库
			//1-产品信息改为出库待发货
			bslProductInfo.setProdStatus(DictItemOperation.产品状态_出库待发货);//修改状态
			bslProductInfo.setProdOutPlan(salePlanId);//出库指令
			bslProductInfo.setProdSaleSerno(bslSaleInfoDetail.getSaleSerno());
			bslProductInfo.setProdOutWeight(prodOutWeight);//出库质量
			bslProductInfo.setUpdDate(new Date());
			int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
			if(resultProd<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultProd==0){
				map.put("code", "1111");
				map.put("msg", "修改产品状态失败！");
				return map;
			}
			//2-修改该笔详细计划
			bslSaleInfoDetail.setProdSumnum(bslSaleInfoDetail.getProdSumnum()+1);//已出库数量+1
			bslSaleInfoDetail.setProdSumweight(sumWeight);//出库质量+产品入库质量
			if(sumWeight>=saleWeightL && sumWeight<=saleWeightH){
				bslSaleInfoDetail.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
			}
			int resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetail);
			if(resultDetail<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultDetail==0){
				map.put("code", "1111");
				map.put("msg", "修改详细计划状态失败");
				return map;
			}
			//3-记录库存变动信息
			salePlanService.updateRefreshSalePlanStatus(salePlanId);
			//4-记录库存变动
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(salePlanId);//对应的生产指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_出售出库);//交易码
			bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(prodCheckuser);//录入人
			bslStockChangeDetail.setPrice(bslSaleInfoDetail.getSalePrice());//价格
			bslStockChangeDetail.setCrtDate(new Date());
			bslStockChangeDetail.setRemark("出售出库");
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				map.put("code", "1111");
				map.put("msg", "sql执行异常！");
				return map;
			}else if(resultStock==0){
				map.put("code", "1111");
				map.put("msg", "新增库存变动表失败");
				return map;
			}
		}
		//能走到这里说明交易成功
		map.put("code", "0000");
		map.put("msg", "出库成功");
		return map;
	}
	
	/**
	 * 根据产品编号与详细计划判断是否满足，以及满足之后开始出库
	 */
	public Map<String,String> isOutDclSuccess(BslProductInfo bslProductInfo,BslBsPlanInfo bslBsPlanInfo,String prodCheckuser,Float prodOutWeight){
		Map<String,String> map = new HashMap<String,String>();
		//满足条件开始出库
		//1-产品信息改为出库待发货
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_出库待发货);//修改状态
		bslProductInfo.setProdOutPlan(bslBsPlanInfo.getBsId());//出库指令
		bslProductInfo.setProdSaleSerno("");
		bslProductInfo.setProdOutWeight(prodOutWeight);//出库质量
		bslProductInfo.setUpdDate(new Date());
		int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(resultProd<0){
			map.put("code", "1111");
			map.put("msg", "sql执行异常！");
			return map;
		}else if(resultProd==0){
			map.put("code", "1111");
			map.put("msg", "修改产品状态失败！");
			return map;
		}
		//2-修改该笔详细计划
		//待处理品不涉及
		//3-刷新通知单状态
		salePlanService.updateRefreshSalePlanStatus(bslBsPlanInfo.getBsId());
		//4-记录库存变动信息
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(bslProductInfo.getProdId());//产品编号
		bslStockChangeDetail.setPlanSerno(bslBsPlanInfo.getBsId());//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_出售出库);//交易码
		bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
		bslStockChangeDetail.setRubbishWeight(bslProductInfo.getProdRelWeight());//重量
		bslStockChangeDetail.setInputuser(prodCheckuser);//录入人
		bslStockChangeDetail.setPrice(0f);//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("待处理品出库");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			map.put("code", "1111");
			map.put("msg", "sql执行异常！");
			return map;
		}else if(resultStock==0){
			map.put("code", "1111");
			map.put("msg", "新增库存变动表失败");
			return map;
		}
		
		//能走到这里说明交易成功
		map.put("code", "0000");
		map.put("msg", "出库成功");
		return map;
	}


	/**
	 * 误发退回
	 */
	@Override
	public BSLResult updateReBackSaleProd(String saleSerno,String prodId,String checkuser){
		//获取原产品信息
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if (bslProductInfo == null) {
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据产品编号未查询到产品信息");
		}
		String salePlanId = bslProductInfo.getProdOutPlan();
		// 出库待发货的才能退回
		if (DictItemOperation.销售单出库标志_已发货.equals(bslProductInfo.getProdStatus())) {
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "已发货状态的产品不允许退回！");
		}
		//出库增加重量
		Float saleWeight = bslProductInfo.getProdRelWeight();
		
		//开始退回
		//满足条件进行出库，1-产品状态变更 2-插入库存变动表
		bslProductInfo.setProdStatus(DictItemOperation.产品状态_已入库);
		bslProductInfo.setProdOutPlan("");
		bslProductInfo.setProdSaleSerno("");
		bslProductInfo.setProdOutCarno("");
		bslProductInfo.setProdOutWeight(0f);
		bslProductInfo.setUpdDate(new Date());
		int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(resultProd<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultProd==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改产品状态失败");
		}
		
		//判断产品类型
		Float price = 0f;
		if(!DictItemOperation.产品类型_待处理品.equals(bslProductInfo.getProdType())){
			// 获取原详细计划信息
			BslSaleInfoDetail bslSaleInfoDetailOld = bslSaleInfoDetailMapper.selectByPrimaryKey(saleSerno);
			if (bslSaleInfoDetailOld == null) {
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据流水号未查询到销售出库详细信息");
			}
			price = bslSaleInfoDetailOld.getSalePrice();
			//出库完成之后修改销售出库详细状态
			//判断出库标志
			int resultDetail;
			if(DictItemOperation.销售单出库标准_按产品编号销售出库.equals(bslSaleInfoDetailOld.getSaleFlag())){
				//如果是按编号出库
				bslSaleInfoDetailOld.setProdSumnum(0);//已出库数量改为0
				bslSaleInfoDetailOld.setProdSumweight(0f);//出库质量0
				bslSaleInfoDetailOld.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
				resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetailOld);

			}else if(DictItemOperation.销售单出库标准_按数量销售出库.equals(bslSaleInfoDetailOld.getSaleFlag())){
				//如果是按质量出库
				bslSaleInfoDetailOld.setProdSumnum(bslSaleInfoDetailOld.getProdSumnum()-1);//已出库数量-1
				bslSaleInfoDetailOld.setProdSumweight(bslSaleInfoDetailOld.getProdSumweight() - bslProductInfo.getProdRelWeight());//出库质量-产品实际质量
				bslSaleInfoDetailOld.setSaleStatus(DictItemOperation.销售单出库标志_未达标准);
				resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetailOld);
				
			}else{
				//如果是按重量出库
				Float saleWeightH = bslSaleInfoDetailOld.getSaleWeight()*1.05f;
				Float saleWeightL = bslSaleInfoDetailOld.getSaleWeight()*0.95f;
				//减少后重量
				Float sumWeight = bslSaleInfoDetailOld.getProdSumweight() - bslProductInfo.getProdRelWeight();
				//2-修改该笔详细计划
				bslSaleInfoDetailOld.setProdSumnum(bslSaleInfoDetailOld.getProdSumnum()-1);//已出库数量+1
				bslSaleInfoDetailOld.setProdSumweight(sumWeight);//出库质量+产品入库质量
				if(sumWeight>=saleWeightL && sumWeight<=saleWeightH){
					bslSaleInfoDetailOld.setSaleStatus(DictItemOperation.销售单出库标志_已达标准);
				}
				resultDetail = bslSaleInfoDetailMapper.updateByPrimaryKeySelective(bslSaleInfoDetailOld);
			}
			if(resultDetail<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultDetail==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改详细计划信息失败");
			}
		}
		//退回完成之后插入库存变动流水表
		BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
		bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
		bslStockChangeDetail.setProdId(prodId);//产品编号
		bslStockChangeDetail.setPlanSerno(salePlanId);//对应的生产指令单号
		bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_未用退回);//交易码
		bslStockChangeDetail.setProdType(bslProductInfo.getProdType());//产品类型
		bslStockChangeDetail.setRubbishWeight(saleWeight);//重量
		bslStockChangeDetail.setInputuser(checkuser);//录入人
		bslStockChangeDetail.setPrice(price);//价格
		bslStockChangeDetail.setCrtDate(new Date());
		bslStockChangeDetail.setRemark("出售出库退回");
		int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
		}
		//刷新销售出库通知单状态
		salePlanService.updateRefreshSalePlanStatus(salePlanId);
		return BSLResult.ok(prodId);
	}

	/**
	 * 产品复磅
	 */
	@Override
	public BSLResult saleProdFb(String prodId, Float prodOutWeight) {
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "未查询到该产品信息");
		}
		//1-产品信息改为出库待发货
		bslProductInfo.setProdOutWeight(prodOutWeight);//出库质量
		bslProductInfo.setUpdDate(new Date());
		int resultProd = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
		if(resultProd<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultProd==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"记录复磅信息失败");
		}
		return BSLResult.ok(prodId);
	}

	/**
	 * 根据编号数组查询产品信息
	 */
	@Override
	public BSLResult getByProdIds(String[] arrays) {
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		List<String> lists = new ArrayList<String>();
		for (String string : arrays) {
			lists.add(string);
		}
		if(lists.size()>0){
			criteria.andProdIdIn(lists);
			bslProductInfoExample.setOrderByClause("`prod_id` desc");
			List<BslProductInfo> selectByExample = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(selectByExample == null || selectByExample.size()<=0){
				return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录,"根据编号未找到产品信息");
			}
			return BSLResult.ok(selectByExample);
		}else{
			return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录,"根据编号未找到产品信息");
		}
	}

	/**
	 * 批量发货出库
	 */
	@Override
	public BSLResult prodOutPl(String salePlanId, String saleSerno, String prodCheckuser, String[] arrays) {
		if(arrays.length<=0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"没有需要出库的产品信息！");
		}
		String prodId;
		for (String prodIdTmp : arrays) {
			prodId = prodIdTmp;
			//循环调用出库方法
			BSLResult updateSaleProdOutPut = updateSaleProdOutPut(prodId,salePlanId,saleSerno,prodCheckuser,0f);
			if(updateSaleProdOutPut.getStatus()!= 200){
				return updateSaleProdOutPut;
			}
		}
		return BSLResult.ok();
	}


}
