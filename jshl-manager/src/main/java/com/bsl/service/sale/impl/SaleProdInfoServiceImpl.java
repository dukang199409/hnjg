package com.bsl.service.sale.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.reportbean.BslOutProductDetailInfo;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.SaleProdInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;

/**
 * 销售出库详细产品信息实现类 duk-20190319
 */
@Service
public class SaleProdInfoServiceImpl implements SaleProdInfoService {

	@Autowired
	BslProductInfoMapper bslProductInfoMapper;

	/**
	 * 根据条件查询通知单下属出库产品信息
	 */
	@Override
	public BSLResult getOutProdsService(QueryCriteria queryCriteria) {
		//创建查询的实例，并赋值
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		
		if(!StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			criteria.andProdOutPlanEqualTo(queryCriteria.getProdOutPlan());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdSaleSerno())){
			criteria.andProdSaleSernoEqualTo(queryCriteria.getProdSaleSerno());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdMaterial())){
			criteria.andProdMaterialEqualTo(queryCriteria.getProdMaterial());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLength())){
			criteria.andProdLengthEqualTo(Float.valueOf(queryCriteria.getProdLength()));
		}
		if(!StringUtils.isBlank(queryCriteria.getProdStatus())){
			criteria.andProdStatusEqualTo(queryCriteria.getProdStatus());
		}
		if(!StringUtils.isBlank(queryCriteria.getProdNorm())){
			String norm = queryCriteria.getProdNorm();
			String[] strings = norm.split(" ");
			List<String> list = new ArrayList<String>();
			if(strings!= null && strings.length>0){
				for (String stringTmp : strings) {
					if(!StringUtils.isBlank(stringTmp)){
						list.add(stringTmp);
					}
				}
			}
			if(list!= null && list.size()>0){
				criteria.andProdNormIn(list);
			}
			//criteria.andProdNormLike("%"+queryCriteria.getProdNorm()+"%");
		}
		if(!StringUtils.isBlank(queryCriteria.getProdLuno())){
			criteria.andProdLunoLike("%"+queryCriteria.getProdLuno()+"%");
		}
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		if(StringUtils.isBlank(queryCriteria.getSort()) || StringUtils.isBlank(queryCriteria.getOrder())){
			bslProductInfoExample.setOrderByClause("`prod_status` desc,`upd_date` desc,`prod_id` desc");
		}else{
			String sortSql = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, queryCriteria.getSort());
			if(!StringUtils.isBlank(sortSql)){
				bslProductInfoExample.setOrderByClause("`"+sortSql+"` "+ queryCriteria.getOrder());
			}
		}
		List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		PageInfo<BslProductInfo> pageInfo = new PageInfo<BslProductInfo>(bslProductInfos);
		return BSLResult.ok(bslProductInfos,"saleProdInfoServiceImpl","getOutProdsService",pageInfo.getTotal(),bslProductInfos);
	}

	/**
	 * 根据条件查询已经出库的产品汇总信息
	 */
	@Override
	public BSLResult getOutProdsGroupService(QueryCriteria queryCriteria) {
		//开始结束日期不能超过一年
		Date startDate = new Date();
		if(StringUtils.isBlank(queryCriteria.getStartDate())){
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			startDate = calendar.getTime();
		}else{
			try {
				startDate = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.YEAR, -1);
		Date endDateBeforeYear = calendar.getTime();
		if(endDateBeforeYear.after(startDate)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只允许查询一年之间的数据");
		}
		queryCriteria.setDateStart(startDate);
		queryCriteria.setDateEnd(endDate);
		if(StringUtils.isBlank(queryCriteria.getBsCustomer())){
			queryCriteria.setBsCustomer(null);
		}
		if(StringUtils.isBlank(queryCriteria.getBsOrderNo())){
			queryCriteria.setBsOrderNo(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			queryCriteria.setProdOutPlan(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdSaleSerno())){
			queryCriteria.setProdSaleSerno(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdMaterial())){
			queryCriteria.setProdMaterial(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdDclFlag())){
			queryCriteria.setProdDclFlag(null);
		}
		if(StringUtils.isBlank(queryCriteria.getBsGettype())){
			queryCriteria.setBsGettype(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdNorm())){
			queryCriteria.setProdNorm(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdLength())){
			queryCriteria.setProdLength(null);
		}else{
			queryCriteria.setProdLengthF(Float.valueOf(queryCriteria.getProdLength()));
		}
		queryCriteria.setWxFlag("0");
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslOutProductDetailInfo> lists = bslProductInfoMapper.querySaleOutGroup(queryCriteria);
		if(StringUtils.isBlank(queryCriteria.getUserType()) || 
				(!(queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_管理员)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_总经理)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_副总经理)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_财务人员)>=0 
					|| queryCriteria.getUserType().indexOf(DictItemOperation.人员角色_营销人员)>=0))){
			for (BslOutProductDetailInfo bslOutProductDetailInfo : lists) {
				bslOutProductDetailInfo.setSalePrice(null);
				bslOutProductDetailInfo.setSumAmt(null);
			}
		}
		PageInfo<BslOutProductDetailInfo> pageInfo = new PageInfo<BslOutProductDetailInfo>(lists);
		return BSLResult.ok(lists,"saleProdInfoServiceImpl","getOutProdsGroupService",pageInfo.getTotal(),lists);
	}
	
	/**
	 * 根据条件查询外协厂已经出库的产品汇总信息
	 */
	@Override
	public BSLResult getWxOutProdsGroupService(QueryCriteria queryCriteria) {
		//开始结束日期不能超过一年
		Date startDate = new Date();
		if(StringUtils.isBlank(queryCriteria.getStartDate())){
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			startDate = calendar.getTime();
		}else{
			try {
				startDate = DictItemOperation.日期转换实例.parse(queryCriteria.getStartDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.YEAR, -1);
		Date endDateBeforeYear = calendar.getTime();
		if(endDateBeforeYear.after(startDate)){
			throw new BSLException(ErrorCodeInfo.错误类型_状态校验错误, "只允许查询一年之间的数据");
		}
		queryCriteria.setDateStart(startDate);
		queryCriteria.setDateEnd(endDate);
		if(StringUtils.isBlank(queryCriteria.getBsCustomer())){
			queryCriteria.setBsCustomer(null);
		}
		if(StringUtils.isBlank(queryCriteria.getBsOrderNo())){
			queryCriteria.setBsOrderNo(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdOutPlan())){
			queryCriteria.setProdOutPlan(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdSaleSerno())){
			queryCriteria.setProdSaleSerno(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdMaterial())){
			queryCriteria.setProdMaterial(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdNorm())){
			queryCriteria.setProdNorm(null);
		}
		if(StringUtils.isBlank(queryCriteria.getBsGettype())){
			queryCriteria.setBsGettype(null);
		}
		if(StringUtils.isBlank(queryCriteria.getProdLength())){
			queryCriteria.setProdLength(null);
		}else{
			queryCriteria.setProdLengthF(Float.valueOf(queryCriteria.getProdLength()));
		}
		queryCriteria.setWxFlag("1");
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslOutProductDetailInfo> lists = bslProductInfoMapper.querySaleOutGroup(queryCriteria);
		PageInfo<BslOutProductDetailInfo> pageInfo = new PageInfo<BslOutProductDetailInfo>(lists);
		return BSLResult.ok(lists,"saleProdInfoServiceImpl","getWxOutProdsGroupService",pageInfo.getTotal(),lists);
	}
	
	/**
	 * 初始化查询已经出库的产品汇总信息
	 */
	@Override
	public EasyUIDataGridResult getOutProdsGroupService(Integer page, Integer rows) {
		//日期默认查询一年的数据
		QueryCriteria queryCriteria = new QueryCriteria();
		Date endDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.YEAR, -1);
		Date startDate = calendar.getTime();
		queryCriteria.setDateStart(startDate);
		queryCriteria.setDateEnd(endDate);
		
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		List<BslOutProductDetailInfo> lists = bslProductInfoMapper.querySaleOutGroup(queryCriteria);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(lists);
		//取记录总条数
		PageInfo<BslOutProductDetailInfo> pageInfo = new PageInfo<>(lists);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("saleProdInfoServiceImpl");
		result.setMethodName("getOutProdsGroupService");
		return result;
	}

}
