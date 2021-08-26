package com.bsl.service.status.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslChangeStatusRecordMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.pojo.BslProductInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.service.sale.SaleDetailService;
import com.bsl.service.sale.SalePlanService;
import com.bsl.service.status.ChangeStatusService;

/**
 * 实物状态维护
 * duk-20190319
 */
@Service
public class ChangeStatusServiceImpl implements ChangeStatusService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	
	@Autowired	 
	BslChangeStatusRecordMapper bslChangeStatusRecordMapper;
	
	@Autowired	 
	SalePlanService salePlanService;
	
	@Autowired	 
	SaleDetailService saleDetailService;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_NEXT_CHANGE_STATUS_ID}")
	private String REDIS_NEXT_CHANGE_STATUS_ID;
	
	/**
	 * 状态修改流水自动生成编号
	 * CS+日期+4位序号
	 * @return
	 */
	public String createStockStatusChangeId() {
		long incr = jedisClient.incr(REDIS_NEXT_CHANGE_STATUS_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("CS%s%04d", sdf.format(new Date()), incr);
		return rawId;
	}
	
	/**
	 * 实物状态维护
	 */
	@Override
	public BSLResult changeProdStatus(BslChangeStatusRecord bslChangeStatusRecord) {
		//获取原产品信息
		String prodId = bslChangeStatusRecord.getChangeProdId();
		BslProductInfo bslProductInfo = bslProductInfoMapper.selectByPrimaryKey(prodId);
		if(bslProductInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查到该产品信息");
		}
		String beforeStatus = bslProductInfo.getProdStatus();
		String afterStatus = bslChangeStatusRecord.getAfterStatus();
		String prodType = bslProductInfo.getProdType();
		//二次校验产品状态是不是与页面查询的一致
		if(!beforeStatus.equals(bslChangeStatusRecord.getBeforeStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"该产品状态已经发生变动,请重新查询维护");
		}
		//出库待发货的产品不支持维护
		if(DictItemOperation.产品状态_出库待发货.equals(beforeStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"出库待发货的产品无需维护，请做退回或者发货");
		}
		//校验产品状态不是已出库
		if(DictItemOperation.产品状态_已出库.equals(beforeStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已出库的产品无需维护，请做退回或者完成");
		}
		//校验产品状态不是已出库
		if(DictItemOperation.产品状态_已入库.equals(beforeStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已入库的产品无需维护，请做出库或者删除");
		}
		//校验状态是否改变
		if(beforeStatus.equals(afterStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"维护前后产品状态一致,无需维护");
		}
		//产品状态不能为3-已分剪
		if(DictItemOperation.产品类型_成品.equals(prodType)){
			if(DictItemOperation.产品状态_已完成.equals(afterStatus)){
				throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"维护后产品状态不能为已分剪");
			}
		}
		//已发货只能到出库待发货
		if(DictItemOperation.产品状态_已发货.equals(beforeStatus)
				&& !DictItemOperation.产品状态_出库待发货.equals(afterStatus)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已发货的只能维护为出库待发货");
		}
		
		//校验完成开始修改
		//1-修改产品状态
		BslProductInfo prodInfoChangeStatus = bslProductInfo;
		prodInfoChangeStatus.setProdStatus(afterStatus);
		//校验产品状态是不是已分剪
		if(DictItemOperation.产品状态_已完成.equals(beforeStatus) && DictItemOperation.产品状态_已入库.equals(afterStatus)){
			prodInfoChangeStatus.setProdOutPlan(null);
		}
		if(DictItemOperation.产品状态_已发货.equals(beforeStatus)&& DictItemOperation.产品状态_出库待发货.equals(afterStatus)){
			//prodInfoChangeStatus.setProdOutPlan(null);
			//prodInfoChangeStatus.setProdSaleSerno(null);
			prodInfoChangeStatus.setProdOutCarno(null);
			//prodInfoChangeStatus.setProdOutDate(null);
		}
		prodInfoChangeStatus.setUpdDate(new Date());
		prodInfoChangeStatus.setRemark("状态强制维护:"+beforeStatus+"到"+afterStatus);
		int resultUpdate = bslProductInfoMapper.updateByPrimaryKey(prodInfoChangeStatus);
		if(resultUpdate<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultUpdate==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"状态强制维护失败");
		}
		
		//2-插入状态维护表
		//修改成功之后记录状态变更流水
		bslChangeStatusRecord.setChangeSerno(createStockStatusChangeId());//修改流水
		bslChangeStatusRecord.setChangeType(DictItemOperation.状态强制维护类别_产品状态维护);
		bslChangeStatusRecord.setCrtDate(new Date());
		int resultStock = bslChangeStatusRecordMapper.insert(bslChangeStatusRecord);
		if(resultStock<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultStock==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增状态变动表失败");
		}
		
		//3-已发货状态的维护需要更新通知单状态
		if(DictItemOperation.产品状态_已发货.equals(beforeStatus)){
			salePlanService.updateRefreshSalePlanStatus(bslProductInfo.getProdOutPlan());
			saleDetailService.updateSaleDetailStatus(bslProductInfo.getProdSaleSerno());
		}
		
		return BSLResult.ok(prodId);
	}

	/**
	 * 根据id查询产品信息
	 */
	@Override
	public BSLResult getProdInfoByPk(String prodId) {
		 BslProductInfo selectByPrimaryKey = bslProductInfoMapper.selectByPrimaryKey(prodId);
		 if(selectByPrimaryKey == null){
			 throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"未查到该产品信息");
		 }
		 return BSLResult.ok(selectByPrimaryKey);
	}

}
