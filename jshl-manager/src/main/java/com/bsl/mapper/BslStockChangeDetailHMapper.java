package com.bsl.mapper;

import com.bsl.pojo.BslStockChangeDetailH;
import com.bsl.pojo.BslStockChangeDetailHExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslStockChangeDetailHMapper {
    int countByExample(BslStockChangeDetailHExample example);

    int deleteByExample(BslStockChangeDetailHExample example);

    int deleteByPrimaryKey(String transSerno);

    int insert(BslStockChangeDetailH record);

    int insertSelective(BslStockChangeDetailH record);

    List<BslStockChangeDetailH> selectByExample(BslStockChangeDetailHExample example);

    BslStockChangeDetailH selectByPrimaryKey(String transSerno);

    int updateByExampleSelective(@Param("record") BslStockChangeDetailH record, @Param("example") BslStockChangeDetailHExample example);

    int updateByExample(@Param("record") BslStockChangeDetailH record, @Param("example") BslStockChangeDetailHExample example);

    int updateByPrimaryKeySelective(BslStockChangeDetailH record);

    int updateByPrimaryKey(BslStockChangeDetailH record);
    
    int insertHistoryChangeInfo(String dateString);
    int deleteHistoryChangeInfo(String dateString);
}