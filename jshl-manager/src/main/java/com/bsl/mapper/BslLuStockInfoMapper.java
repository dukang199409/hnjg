package com.bsl.mapper;

import com.bsl.pojo.BslLuStockInfo;
import com.bsl.pojo.BslLuStockInfoExample;
import com.bsl.pojo.BslLuStockInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslLuStockInfoMapper {
    int countByExample(BslLuStockInfoExample example);

    int deleteByExample(BslLuStockInfoExample example);

    int deleteByPrimaryKey(BslLuStockInfoKey key);

    int insert(BslLuStockInfo record);

    int insertSelective(BslLuStockInfo record);

    List<BslLuStockInfo> selectByExample(BslLuStockInfoExample example);
    
    List<BslLuStockInfo> selectOneDayInfo();

    BslLuStockInfo selectByPrimaryKey(BslLuStockInfoKey key);

    int updateByExampleSelective(@Param("record") BslLuStockInfo record, @Param("example") BslLuStockInfoExample example);

    int updateByExample(@Param("record") BslLuStockInfo record, @Param("example") BslLuStockInfoExample example);

    int updateByPrimaryKeySelective(BslLuStockInfo record);

    int updateByPrimaryKey(BslLuStockInfo record);
}