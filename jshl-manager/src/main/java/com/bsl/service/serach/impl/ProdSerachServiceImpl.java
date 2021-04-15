package com.bsl.service.serach.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.utils.BSLResult;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductInfoExample.Criteria;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.select.ProdLeftInfo;
import com.bsl.service.serach.ProdSerachService;

/**
 * 满足条件产品剩余信息查询实现类
 * duk-20190319
 */
@Service
public class ProdSerachServiceImpl implements ProdSerachService {

	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;

	/**
	 * 根据条件查询所有满足条件的在库产品信息
	 */
	@Override
	public BSLResult getLeftInfo(BslProductInfo bslProductInfo,String flag) {
		//创建返回实例
		ProdLeftInfo leftInfo = new ProdLeftInfo();
		List<String> prodNorms = new ArrayList<String>();
		List<String> prodMaterials = new ArrayList<String>();
		List<Float> prodLengths = new ArrayList<Float>();
		List<String> prodLunos = new ArrayList<String>();
		int countProds = 0;
		Float sumWeight = 0f;
		//查询实例
		BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
		Criteria criteria = bslProductInfoExample.createCriteria();
		//记录返回查询信息
		List<BslProductInfo> bslProductInfos;
		//校验规格
		if(StringUtils.isBlank(bslProductInfo.getProdNorm())){
			throw new BSLException(ErrorCodeInfo.错误类型_参数为空,"规格不能为空");
		}
		criteria.andProdNormEqualTo(bslProductInfo.getProdNorm());
		if(!StringUtils.isBlank(bslProductInfo.getProdMaterial())){
			criteria.andProdMaterialEqualTo(bslProductInfo.getProdMaterial());
		}
		if(!StringUtils.isBlank(bslProductInfo.getProdLuno())){
			criteria.andProdLunoEqualTo(bslProductInfo.getProdLuno());
		}
		//如果状态是1-卷板相关信息查询
		if("1".equals(flag)){
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_卷板);
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
		}else if("2".equals(flag)){
			//在库的纵剪带查询
			criteria.andProdTypeEqualTo(DictItemOperation.产品类型_半成品);
			criteria.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
			criteria.andProdUserTypeNotEqualTo(DictItemOperation.纵剪带用途_外销);
		}

		bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
		if(bslProductInfos!=null && bslProductInfos.size()>0){
			//处理查回来之后处理数据
			countProds = bslProductInfos.size();
			for (BslProductInfo prod : bslProductInfos) {
				sumWeight += prod.getProdRelWeight();
				if(!prodNorms.contains(prod.getProdNorm())){
					prodNorms.add(prod.getProdNorm());
				}
				if(!prodMaterials.contains(prod.getProdMaterial())){
					prodMaterials.add(prod.getProdMaterial());
				}
				if(!prodLengths.contains(prod.getProdLength())){
					prodLengths.add(prod.getProdLength());
				}
				if(!prodLunos.contains(prod.getProdLuno())){
					prodLunos.add(prod.getProdLuno());
				}
			}
		}
		leftInfo.setProdCount(countProds);
		leftInfo.setProdLengths(prodLengths);
		leftInfo.setProdLunos(prodLunos);
		leftInfo.setProdMaterials(prodMaterials);
		leftInfo.setProdNorms(prodNorms);
		leftInfo.setSumWeight(sumWeight);
		return BSLResult.ok(leftInfo);
	}
	
}