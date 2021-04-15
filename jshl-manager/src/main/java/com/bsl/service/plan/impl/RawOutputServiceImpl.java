package com.bsl.service.plan.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryExample;
import com.bsl.service.plan.MakePlanService;
import com.bsl.service.plan.RawOutputService;
import com.bsl.service.serach.PlanLunoInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 卷板出库
 */
@Service
public class RawOutputServiceImpl implements RawOutputService {
	
	@Autowired
	private BslProductInfoMapper bslProductInfoMapper;
	
	@Autowired
	private MakePlanService makePlanService;
	
	@Autowired
	private PlanLunoInfoService planLunoInfoService;
	
	//记录流水
	@Autowired	 
	BslStockChangeDetailMapper bslStockChangeDetailMapper;
	@Autowired
	private JedisClient jedisClient;
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
	 * 根据条件查询已入库已出库已完成的卷板信息
	 */
	@Override
	public BSLResult queryBslProductInfoList(QueryExample queryCriteria) {
		
		BslMakePlanInfo prodPlanInfoExe = makePlanService.getMakePlanInfoExe(queryCriteria.getPlanJz());
		if(prodPlanInfoExe == null){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "没有查询到正在执行的纵剪带指令，无法出库！");
		}
		BslProductInfoExample example = new BslProductInfoExample();
		if (queryCriteria != null) {
			Criteria criteria = example.createCriteria();
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
			//卷号
			if (!StringUtils.isBlank(queryCriteria.getProdId())) {
				criteria.andProdIdLike(StringUtil.likeStr(queryCriteria.getProdId()));
			}
			//入库单号
			if (!StringUtils.isBlank(queryCriteria.getProdPlanNo())) {
				criteria.andProdPlanNoLike(StringUtil.likeStr(queryCriteria.getProdPlanNo()));
			}
			//产品状态
			if (!StringUtils.isBlank(queryCriteria.getProdStatus())) {
				criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
			} else {
				List<String> values = new ArrayList<>();
				values.add(DictItemOperation.产品状态_已入库);
				values.add(DictItemOperation.产品状态_已出库);
				criteria.andProdStatusIn(values);
			}
			criteria.andProdMaterialEqualTo(prodPlanInfoExe.getProdMaterial());//钢种是正在执行的指令指定钢种
			criteria.andProdNormEqualTo(prodPlanInfoExe.getProdNorm());//规格是正在执行的指令指定规格
			//criteria.andProdLunoEqualTo(prodPlanInfoExe.getPlanLuno());//炉号是正在执行的指令指定炉号
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
				example.setOrderByClause("`prod_id` asc,`prod_plan_no` desc");
			}
		}
		List<BslProductInfo> list = bslProductInfoMapper.selectByExample(example);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(list);
		return BSLResult.ok(pageInfo.getTotal(),list);
	}

	/**
	 * 根据id查询卷板信息
	 */
	@Override
	public BslProductInfo getProdPlanByPK(String prodId) {
		return bslProductInfoMapper.selectByPrimaryKey(prodId);
	}

	/**
	 * 卷板出库/未用退回/完成
	 */
	@Override
	public BSLResult updateRawStatusService(String prodId,String planJz,String inputUser,Integer intStatus) {
		//获取卷板信息
		BslProductInfo bslProductInfoOld = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录, "根据对应原料物料编码查询记录为空！");
		}
		String statusOld = bslProductInfoOld.getProdStatus();//原状态
		String luno = bslProductInfoOld.getProdLuno();//炉号
		String norm= bslProductInfoOld.getProdNorm();//规格
		String meterial = bslProductInfoOld.getProdMaterial();//钢种
		Float relWeight = bslProductInfoOld.getProdRelWeight();//重量
		
		//判断操作方式
		if(intStatus == 1){
			//获取正在执行的纵剪带指令，检验所需重量，钢种，规格，炉号
			BslMakePlanInfo prodPlanInfoExe = makePlanService.getMakePlanInfoExe(planJz);
			if(prodPlanInfoExe == null){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "没有查询到正在执行的纵剪带指令，无法出库！");
			}
			String exePlanId = prodPlanInfoExe.getPlanId();
			
			/**
			 * 出库
			 */
			//库存状态为已入库
			if(!DictItemOperation.产品状态_已入库.equals(statusOld)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "必须是在库的卷板才能出库！");
			}
			//判断当前没有进行中的卷板
			if(getRawExe(exePlanId) != null){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "还有已出库未制造完成的卷板，请先完成之后才能出库！");
			}
			
			if(!prodPlanInfoExe.getProdMaterial().equals(meterial)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原料钢种与正在执行的纵剪带指令规定钢种不符，无法出库！");
			}
			if(!prodPlanInfoExe.getProdNorm().equals(norm)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原料规格与正在执行的纵剪带指令规定规格不符，无法出库！");
			}
//			if(!prodPlanInfoExe.getPlanLuno().equals(luno)){
//				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "原料炉号与正在执行的纵剪带指令规定炉号不符，无法出库！");
//			}
			//校验数量
			if(prodPlanInfoExe.getProdNum() != null && prodPlanInfoExe.getProdNum()!=0){
				BslProductInfoExample exampleSelectNum = new BslProductInfoExample();
				Criteria criteriaNum = exampleSelectNum.createCriteria();
				criteriaNum.andProdOutPlanEqualTo(exePlanId);
				List<BslProductInfo> bslProductInfoOutNums = bslProductInfoMapper.selectByExample(exampleSelectNum);
				if(bslProductInfoOutNums!=null && bslProductInfoOutNums.size()>0){
					if(bslProductInfoOutNums.size()>=prodPlanInfoExe.getProdNum()){
						throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "因该指令出库的卷板数量已达限定值，不允许出库！");
					}
				}
			}
			if(prodPlanInfoExe.getProdWeight() != null && prodPlanInfoExe.getProdWeight()!=0){
				//校验重量（获取因为这条指令出库的产品重量）
				BslProductInfoExample exampleSelect = new BslProductInfoExample();
				Criteria criteria = exampleSelect.createCriteria();
				criteria.andProdOutPlanEqualTo(exePlanId);
				List<BslProductInfo> bslProductInfoOuts = bslProductInfoMapper.selectByExample(exampleSelect);
				if(bslProductInfoOuts!=null && bslProductInfoOuts.size()>0){
					//因为该指令出库的总重量
					Float sumWeight = 0f;
					for (BslProductInfo bslProductInfo : bslProductInfoOuts) {
						sumWeight += bslProductInfo.getProdRelWeight();
					}
					Float chaoNum = (relWeight + sumWeight - prodPlanInfoExe.getProdWeight())/prodPlanInfoExe.getProdWeight();
					if(chaoNum > 0.1){
						throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "该指令已出库用料重量加上该卷板重量超出限定的10%，不允许出库！");
					}
				}
			}
			
			//校验完成开始出库
			BslProductInfo prodInfoOut = new BslProductInfo();
			prodInfoOut.setProdId(prodId);
			prodInfoOut.setProdStatus(DictItemOperation.产品状态_已出库);
			prodInfoOut.setProdOutDate(new Date());
			prodInfoOut.setProdOutPlan(exePlanId);
			int resultUpdate = bslProductInfoMapper.updateByPrimaryKeySelective(prodInfoOut);
			if(resultUpdate<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultUpdate==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"出库失败");
			}
			
			//登记库存变动明细表
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(exePlanId);//对应的纵剪带指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_制造出库);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_卷板);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfoOld.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(inputUser);//录入人
			bslStockChangeDetail.setCrtDate(new Date());
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultStock==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
			}
			
			//出库完成增加炉号
			//先判断有没有此炉号
			int luNum = planLunoInfoService.getPlanLuno(exePlanId, luno);
			if(luNum <= 0){
				//说明还没有这种炉号，开始新增
				planLunoInfoService.insertPlanLuno(exePlanId, luno);
			}
			
			
		}else if(intStatus == 2){
			/**
			 * 未用退回
			 */
			//库存状态为已出库
			if(!DictItemOperation.产品状态_已出库.equals(statusOld)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "必须是已出库的卷板才能退回！");
			}
			//开始退回
			BslProductInfo bslProductInfoUpdate = new BslProductInfo();
			bslProductInfoUpdate.setProdId(prodId);
			bslProductInfoUpdate.setProdStatus(DictItemOperation.产品状态_已入库);
			bslProductInfoUpdate.setProdOutPlan("");//出库指令号设置为空
			bslProductInfoUpdate.setProdOutDate(null);
			//bslProductInfoUpdate.setRemark("未用退回");
			bslProductInfoUpdate.setUpdDate(new Date());
			int resultUpdate = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoUpdate);
			if(resultUpdate<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultUpdate==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未用退回失败");
			}
			
			/**
			 * 新增未用退回记录
			 */
			//退回成功之后记录插入流水
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfoOld.getProdOutPlan());//当时的纵剪带指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_未用退回);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_卷板);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfoOld.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(inputUser);//录入人
			bslStockChangeDetail.setCrtDate(new Date());
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultStock==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
			}
			
			//退回完成删除炉号
			//先判断有没有大于1的类似产品,
			int luNum = planLunoInfoService.getPlanLuno(bslProductInfoOld.getProdOutPlan(), luno);
			if(luNum <= 1){
				//不大于1说明只有这一个，可以删除
				planLunoInfoService.deletePlanLuno(bslProductInfoOld.getProdOutPlan(), luno);
			}
		}else{
			/**
			 * 完成
			 */
			//库存状态为已出库
			if(!DictItemOperation.产品状态_已出库.equals(statusOld)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "必须是已出库的卷板才能完成！");
			}
			//校验是否有因为该卷版制造的半成品
			BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
			Criteria criteria = bslProductInfoExample.createCriteria();
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
			criteria.andProdParentNoEqualTo(prodId);
			List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
			if(bslProductInfos == null || bslProductInfos.size()<=0){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"该卷板下没有已入库的纵剪带，无法完成！");
			}
			
			//开始完成
			BslProductInfo bslProductInfoUpdate = new BslProductInfo();
			bslProductInfoUpdate.setProdId(prodId);
			bslProductInfoUpdate.setProdStatus(DictItemOperation.产品状态_已完成);
			//bslProductInfoUpdate.setRemark("完成");
			bslProductInfoUpdate.setUpdDate(new Date());
			int resultUpdate = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfoUpdate);
			if(resultUpdate<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultUpdate==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未用退回失败");
			}
			
			/**
			 * 新增完成记录
			 */
			//完成成功之后记录插入流水
			BslStockChangeDetail bslStockChangeDetail = new BslStockChangeDetail();
			bslStockChangeDetail.setTransSerno(createStockChangeId());//流水
			bslStockChangeDetail.setProdId(prodId);//产品编号
			bslStockChangeDetail.setPlanSerno(bslProductInfoOld.getProdOutPlan());//当时的纵剪带指令单号
			bslStockChangeDetail.setTransCode(DictItemOperation.库存变动交易码_完成);//交易码
			bslStockChangeDetail.setProdType(DictItemOperation.产品类型_卷板);//产品类型
			bslStockChangeDetail.setRubbishWeight(bslProductInfoOld.getProdRelWeight());//重量
			bslStockChangeDetail.setInputuser(inputUser);//录入人
			bslStockChangeDetail.setCrtDate(new Date());
			int resultStock = bslStockChangeDetailMapper.insert(bslStockChangeDetail);
			if(resultStock<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultStock==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增库存变动表失败");
			}
			
		}
		return BSLResult.ok(prodId);
	}

	/**
	 * 获取某指令正在进行中的卷板信息
	 */
	@Override
	public BslProductInfo getRawExe(String exePlanId) {
		BslProductInfoExample example = new BslProductInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已出库);
		criteria.andProdOutPlanEqualTo(exePlanId);
		List<BslProductInfo> list = bslProductInfoMapper.selectByExample(example);
		if(list == null || list.size()<=0){
			return null;
		}else{
			return list.get(0);
		}
	}


}
