package com.bsl.mapper;

import com.bsl.pojo.BslStockChangePhoto;
import com.bsl.pojo.BslStockChangePhotoExample;
import com.bsl.pojo.BslStockChangePhotoKey;
import com.bsl.reportbean.BslStockChangeSumReport;
import com.bsl.select.QueryCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslStockChangePhotoMapper {
    int countByExample(BslStockChangePhotoExample example);

    int deleteByExample(BslStockChangePhotoExample example);

    int deleteByPrimaryKey(BslStockChangePhotoKey key);

    int insert(BslStockChangePhoto record);

    int insertSelective(BslStockChangePhoto record);

    List<BslStockChangePhoto> selectByExample(BslStockChangePhotoExample example);
    
    List<BslStockChangePhoto> selectStockChangeSumInfo();
    
    List<BslStockChangeSumReport> selectStockChangeSumAllInfo(QueryCriteria criteria);

    BslStockChangePhoto selectByPrimaryKey(BslStockChangePhotoKey key);

    int updateByExampleSelective(@Param("record") BslStockChangePhoto record, @Param("example") BslStockChangePhotoExample example);

    int updateByExample(@Param("record") BslStockChangePhoto record, @Param("example") BslStockChangePhotoExample example);

    int updateByPrimaryKeySelective(BslStockChangePhoto record);

    int updateByPrimaryKey(BslStockChangePhoto record);
}