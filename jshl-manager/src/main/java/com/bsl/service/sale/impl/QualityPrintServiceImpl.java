package com.bsl.service.sale.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.EasyUIDataGridResult;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslBsPlanInfoMapper;
import com.bsl.mapper.BslCarRetchaInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslSaleInfoDetailMapper;
import com.bsl.pojo.BslCarRetchaInfo;
import com.bsl.reportbean.BslProductQualityInfo;
import com.bsl.reportbean.BslSaleCarInfo;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.QueryCriteria;
import com.bsl.service.sale.QualityPrintService;
import com.bsl.service.sale.SaleDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 质量书打印管理实现类
 * duk-20190319
 */
@Service
public class QualityPrintServiceImpl implements QualityPrintService{
	
	 @Autowired	 
	 BslBsPlanInfoMapper bslBsPlanInfoMapper;
	 @Autowired	 
	 BslSaleInfoDetailMapper bslSaleInfoDetailMapper;
	 @Autowired	 
	 BslProductInfoMapper bslProductInfoMapper;
	 @Autowired	 
	 SaleDetailService saleDetailService;
	 @Autowired
	 BslCarRetchaInfoMapper bslCarRetchaInfoMapper;
	 
	 /**
	 * 加载界面时查询所有车次流水信息
	 */
	@Override
	public EasyUIDataGridResult getCarSernoService(Integer page, Integer rows) {
		QueryCriteria queryCriteria = new QueryCriteria();
		//分页处理
		PageHelper.startPage(page,rows);
		//调用sql查询
		List<BslSaleCarInfo> lists = bslProductInfoMapper.prodSaleCarInfo(queryCriteria);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(lists);
		//取记录总条数
		PageInfo<BslSaleCarInfo> pageInfo = new PageInfo<>(lists);
		result.setTotal(pageInfo.getTotal());
		result.setClassName("qualityPrintServiceImpl");
		result.setMethodName("getCarSernoServiceByCriteria");
		return result;
	}
	 
	/**
	 * 获取符合条件的车次流水信息
	 */
	@Override
	public BSLResult getCarSernoServiceByCriteria(QueryCriteria queryCriteria) {
		 
		if(StringUtils.isBlank(queryCriteria.getProdOutCarno())){
			queryCriteria.setProdOutCarno(null);
		}else{
			queryCriteria.setProdOutCarno("%"+queryCriteria.getProdOutCarno()+"%");
		}
		if(StringUtils.isBlank(queryCriteria.getPrintNum())){
			queryCriteria.setPrintNum(null);
		}
		if(StringUtils.isBlank(queryCriteria.getWxFlag())){
			queryCriteria.setWxFlag(null);
		}
		String startDate = null;
		String endDate = null;
		if(!StringUtils.isBlank(queryCriteria.getStartDate())){
			startDate = queryCriteria.getStartDate().replace("-", "");
		}
		if(!StringUtils.isBlank(queryCriteria.getEndDate())){
			endDate = queryCriteria.getEndDate().replace("-", "");
		}
		queryCriteria.setStartDate(startDate);
		queryCriteria.setEndDate(endDate);
		
		//分页处理
		PageHelper.startPage(Integer.parseInt(queryCriteria.getPage()), Integer.parseInt(queryCriteria.getRows()));
		//调用sql查询
		List<BslSaleCarInfo> lists = bslProductInfoMapper.prodSaleCarInfo(queryCriteria);
		PageInfo<BslSaleCarInfo> pageInfo = new PageInfo<BslSaleCarInfo>(lists);
		return BSLResult.ok(lists,"qualityPrintServiceImpl","getCarSernoServiceByCriteria",pageInfo.getTotal(),lists);
		
	}

	/**
	 * 获取符合条件的车次流水发货详细信息
	 */
	@Override
	public BSLResult getCarDeatilInfo(String prodOutCarno) {
		QueryCriteria queryCriteria = new QueryCriteria();
		List<String> prodOutCarnosList = new ArrayList<String>();
		prodOutCarnosList.add(prodOutCarno);
		queryCriteria.setProdOutCarnosList(prodOutCarnosList);
		
		//分页处理
		PageHelper.startPage(1,500);
		//调用sql查询
		List<BslProductQualityInfo> lists = bslProductInfoMapper.prodQualityInfo(queryCriteria);
		PageInfo<BslProductQualityInfo> pageInfo = new PageInfo<BslProductQualityInfo>(lists);
		return BSLResult.ok(lists,"qualityPrintServiceImpl","getCarDeatilInfo",pageInfo.getTotal(),lists);
	}

	/**
	 * 根据车号集合获取详细信息
	 */
	@Override
	public List<BslProductQualityInfo> getCarDetailByList(List<String> cars) {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setProdOutCarnosList(cars);
		List<BslProductQualityInfo> lists = bslProductInfoMapper.prodQualityInfo(queryCriteria);
		return lists;
	}

	/**
	 * 记录打印次数
	 */
	@Override
	public BSLResult updatePrintNum(String prodOutCarno) {
		//记录打印次数
		BslCarRetchaInfo bslCarRetchaInfo = bslCarRetchaInfoMapper.selectByPrimaryKey(prodOutCarno);
		if(bslCarRetchaInfo == null){
			bslCarRetchaInfo = new BslCarRetchaInfo();
			bslCarRetchaInfo.setCarNo(prodOutCarno);
			bslCarRetchaInfo.setPrintNum(1);
			int insertResult = bslCarRetchaInfoMapper.insert(bslCarRetchaInfo);
			if(insertResult<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(insertResult==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"记录打印次数信息失败");
			}
		}else{
			if(bslCarRetchaInfo.getPrintNum() == null){
				bslCarRetchaInfo.setPrintNum(1);
			}else{
				bslCarRetchaInfo.setPrintNum(bslCarRetchaInfo.getPrintNum()+1);
			}
			
			int resultResult = bslCarRetchaInfoMapper.updateByPrimaryKey(bslCarRetchaInfo);
			if(resultResult<0){
				throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
			}else if(resultResult==0){
				throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改打印次数信息失败");
			}
		}
		
		return BSLResult.ok();
	}

	/**
	 * 记录车次信息
	 */
	@Override
	public BSLResult insertCCInfo(String prodOutCarno, String wxFlag,String bsGettype) {
		BslCarRetchaInfo bslCarRetchaInfo = new BslCarRetchaInfo();
		bslCarRetchaInfo.setCarNo(prodOutCarno);
		bslCarRetchaInfo.setWxFlag(wxFlag);
		bslCarRetchaInfo.setBsGettype(bsGettype);
		int resultResult =bslCarRetchaInfoMapper.insert(bslCarRetchaInfo);
		if(resultResult<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultResult==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改打印次数信息失败");
		}
		return BSLResult.ok();
	}

	/**
	 * 修改车次信息
	 */
	@Override
	public BSLResult updateCarDeatilInfo(BslCarRetchaInfo bslCarRetchaInfo) {
		int resultResult =bslCarRetchaInfoMapper.updateByPrimaryKeySelective(bslCarRetchaInfo);
		if(resultResult<0){
			throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
		}else if(resultResult==0){
			throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"修改车次信息失败");
		}
		return BSLResult.ok();
	}

}
