package com.bsl.service.rawmaterialsmanager.impl;

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
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import com.bsl.pojo.BslBsPlanInfoExample.Criteria;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.rawmaterialsmanager.RawService;
import com.bsl.service.rawmaterialsmanager.ReceiptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 原料入库通知单管理实现类
 * duk-20190319
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
	
	 @Autowired	 
	 BslBsPlanInfoMapper bslBsPlanInfoMapper;
	 @Autowired	 
	 BslProductInfoMapper bslProductInfoMapper;
	 @Autowired	 
	 RawService rawService;
	 
	 @Autowired
	 private JedisClient jedisClient;
		
	 @Value("${REDIS_NEXT_RECEIPT_ID}")
	 private String REDIS_NEXT_RECEIPT_ID;
	
	/**
	 * 获取符合条件的原料入库通知单
	 */
	@Override
	public BSLResult getReceiptService(QueryCriteria queryCriteria) {
		 
		//创建查询的实例，并赋值
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询原料入库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_原料入库通知单);
		if(!StringUtils.isBlank(queryCriteria.getBsId())){
			criteria.andBsIdLike("%"+queryCriteria.getBsId()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getBsFlag())){
			criteria.andBsFlagEqualTo(queryCriteria.getBsFlag());
		}
		if(!StringUtils.isBlank(queryCriteria.getBsStatus())){
			criteria.andBsStatusEqualTo(queryCriteria.getBsStatus());
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
			bslBsPlanInfoExample.setOrderByClause("`bs_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslBsPlanInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<BslBsPlanInfo>(bslBsPlanInfoList);
		return BSLResult.ok(bslBsPlanInfoList,"receiptServiceImpl","getReceiptService",pageInfo.getTotal(),bslBsPlanInfoList);
	}

	/**
	 * 加载界面时查询所有原料入库通知单
	 */
	@Override
	public EasyUIDataGridResult getReceiptService(Integer page, Integer rows) {
		BslBsPlanInfoExample bslBsPlanInfoExample = new BslBsPlanInfoExample();
		Criteria criteria = bslBsPlanInfoExample.createCriteria();
		//查询原料入库通知单
		criteria.andBsTypeEqualTo(DictItemOperation.收发标志_原料入库通知单);
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		bslBsPlanInfoExample.setOrderByClause("`bs_id` desc");
		List<BslBsPlanInfo> bslBsPlanInfoList = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(bslBsPlanInfoList);
		//取记录总条数
		PageInfo<BslBsPlanInfo> pageInfo = new PageInfo<>(bslBsPlanInfoList);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("receiptServiceImpl");
		result.setMethodName("getReceiptService");
		return result;
	}

	/**
	 * 新增原料入库通知单
	 */
	@Override
	public BSLResult addReceiptInfo(BslBsPlanInfo bslBsPlanInfo) {
		bslBsPlanInfo.setBsType(DictItemOperation.收发标志_原料入库通知单);
		bslBsPlanInfo.setBsStatus(DictItemOperation.通知单状态_创建);//状态默认为0-创建
		bslBsPlanInfo.setCrtDate(new Date());//创建日期当天
		bslBsPlanInfo.setBsId(createReceiptId());
		int result = bslBsPlanInfoMapper.insert(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"新增失败");
		}
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}
	
	/**
	 * 修改原料入库通知单
	 */
	@Override
	public BSLResult updateReceiptInfo(BslBsPlanInfo bslBsPlanInfo) {
		//修改之前先校验状态是不是创建或进行中
		BslBsPlanInfo bslSelectPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bslBsPlanInfo.getBsId());
		if(bslSelectPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到入库通知单信息");
		}
		
		//获取该通知单下属入库单
		//创建产品的实体类
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample(); 
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdPlanNoEqualTo(bslBsPlanInfo.getBsId());
		//获取该原料入库通知单下的产品信息
		List<BslProductInfo> listBslProductInfo = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(listBslProductInfo != null && listBslProductInfo.size()>0){
			//判断是不是对卷数进行修改(若是，修改后的卷数大于等于当前实际下属卷数)
			if(!bslSelectPlanInfo.getBsAmt().equals(bslBsPlanInfo.getBsAmt())){
				int bsAmt = 0;
				for (int i = 0; i < listBslProductInfo.size(); i++) {
					if(!DictItemOperation.产品来源_剩余入库.equals(listBslProductInfo.get(i).getProdSource())){
						bsAmt ++;
					}
				}
				if(bslBsPlanInfo.getBsAmt()<bsAmt){
					throw new BSLException(ErrorCodeInfo.错误类型_修改前参数校验,"修改后的卷数不能小于现有录入卷数");
				}
			}
			//判断是不是对收发类别就行修改（0-自购1-外接）
			if(!bslSelectPlanInfo.getBsFlag().equals(bslBsPlanInfo.getBsFlag())){
				BslProductInfo bslProductInfo = new BslProductInfo();
				for (int i = 0; i < listBslProductInfo.size(); i++) {
					bslProductInfo.setProdId(listBslProductInfo.get(i).getProdId());
					bslProductInfo.setProdSource(bslBsPlanInfo.getBsFlag());
					int result = bslProductInfoMapper.updateByPrimaryKeySelective(bslProductInfo);
					if(result<0){
						throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
					}else if(result==0){
						throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
					}
				}
			}
		}
		//修改前判断状态
		String bsStatus = bslSelectPlanInfo.getBsStatus();
		if(DictItemOperation.通知单状态_已完成.equals(bsStatus) && !DictItemOperation.管理员.equals(bslBsPlanInfo.getBsInputuser())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"已完成的原料入库通知单只允许管理员修改！");
		}
		//进行修改
		bslBsPlanInfo.setUpdDate(new Date());//修改日期当天
		int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
		}		
		updateBsInfoStatus(bslBsPlanInfo.getBsId());
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}

	/**
	 * 删除原料入库通知单
	 */
	@Override
	public BSLResult deleteReceiptInfo(BslBsPlanInfo bslBsPlanInfo) {
		//删除之前先校验状态以及是不是下面已经有预录入的入库单
		//先校验状态是不是创建
		BslBsPlanInfo bslBsPlanInfoOld = bslBsPlanInfoMapper.selectByPrimaryKey(bslBsPlanInfo.getBsId());
		if(bslBsPlanInfoOld == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到入库通知单信息");
		}
		if(!DictItemOperation.通知单状态_创建.equals(bslBsPlanInfoOld.getBsStatus())){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误,"非创建状态的原料入库通知单不允许删除！");
		}
		//创建产品的实体类
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample(); 
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdPlanNoEqualTo(bslBsPlanInfo.getBsId());
		//获取该原料入库通知单下的产品信息
		List<BslProductInfo> listBslProductInfo = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(listBslProductInfo != null && listBslProductInfo.size()>0){
			throw new BSLException(ErrorCodeInfo.错误类型_删除前子数据检查,"该通知单下有录入产品信息，若要删除请先删除产品信息");
		}	
		
		//删除操作
		int result = bslBsPlanInfoMapper.deleteByPrimaryKey(bslBsPlanInfo.getBsId());
		if(result<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(result==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的删除记录");
		}		
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}

	/**
	 * 根据实体类获取通知单信息
	 */
	@Override
	public BSLResult getReceiptServiceByBsPlanInfo(BslBsPlanInfoExample bslBsPlanInfoExample) {
		 List<BslBsPlanInfo> selectByExample = bslBsPlanInfoMapper.selectByExample(bslBsPlanInfoExample);
		 return BSLResult.ok(selectByExample);
	}

	/**
	 * 根据单号获取通知单信息
	 */
	@Override
	public BSLResult getReceiptByBsBsId(String bsId) {
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		return BSLResult.ok(bslBsPlanInfo);
	}
	
	/**
	 * 原料入库通知单自动生成编号
	 * RC+日期+2位序号
	 * @return
	 */
	public String createReceiptId() {
		long incr = jedisClient.incr(REDIS_NEXT_RECEIPT_ID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String rawId = String.format("RC%s%02d", sdf.format(new Date()), incr);
		return rawId;
	}

	/**
	 * 根据单号查询卷板信息
	 */
	@Override
	public BSLResult getMakePlanInfoDetail(String bsId) {
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		criteria.andProdPlanNoEqualTo(bsId);
		return rawService.getRawServiceByProductInfo(bslProductInfoExample);
	}

	/**
	 * 根据单号查询卷板信息刷新状态
	 */
	@Override
	public BSLResult updateBsInfoStatus(String bsId) {
		//获取信息
		BslBsPlanInfo bslBsPlanInfo = bslBsPlanInfoMapper.selectByPrimaryKey(bsId);
		String status = "";
		if(bslBsPlanInfo == null){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据单号未查询到入库通知单信息");
		}
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		com.bsl.pojo.BslProductInfoExample.Criteria criteria = bslProductInfoExample.createCriteria();
		criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
		criteria.andProdSourceNotEqualTo(DictItemOperation.产品来源_剩余入库);
		criteria.andProdPlanNoEqualTo(bsId);
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(bslProductInfos == null || bslProductInfos.size() == 0){
			status = DictItemOperation.通知单状态_创建;
		}else{
			if(bslBsPlanInfo.getBsAmt()<=bslProductInfos.size()){
				status = DictItemOperation.通知单状态_已完成;
				float relWeight = 0;
				for (int i = 0; i < bslProductInfos.size(); i++) {
					relWeight += bslProductInfos.get(i).getProdRelWeight();
				}
				bslBsPlanInfo.setBsRelweight(relWeight);
			}else{
				status = DictItemOperation.通知单状态_进行中;
			}
		}
		if(!status.equals(bslBsPlanInfo.getBsStatus())){
			bslBsPlanInfo.setUpdDate(new Date());//修改日期当天
			bslBsPlanInfo.setBsStatus(status);
			int result = bslBsPlanInfoMapper.updateByPrimaryKeySelective(bslBsPlanInfo);
			if(result<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(result==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"根据条件没有符合的修改记录");
			}		
		}
		return BSLResult.ok(bslBsPlanInfo.getBsId());
	}
	
	

}
