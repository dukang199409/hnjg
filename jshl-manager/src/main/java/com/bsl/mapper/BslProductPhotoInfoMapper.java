package com.bsl.mapper;

import com.bsl.pojo.BslProductPhotoInfo;
import com.bsl.pojo.BslProductPhotoInfoExample;
import com.bsl.reportbean.BslStockPhotoReport;
import com.bsl.select.QueryCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslProductPhotoInfoMapper {
    int countByExample(BslProductPhotoInfoExample example);

    int deleteByExample(BslProductPhotoInfoExample example);

    int insert(BslProductPhotoInfo record);

    int insertSelective(BslProductPhotoInfo record);

    List<BslProductPhotoInfo> selectByExample(BslProductPhotoInfoExample example);
    
    List<BslStockPhotoReport> selectByExampleNew(QueryCriteria queryCriteria);

    int updateByExampleSelective(@Param("record") BslProductPhotoInfo record, @Param("example") BslProductPhotoInfoExample example);

    int updateByExample(@Param("record") BslProductPhotoInfo record, @Param("example") BslProductPhotoInfoExample example);
    
    int deleteProductPhotoInfoOnyears();
}