package com.bsl.mapper;

import com.bsl.pojo.BslStockChangeDetail;
import com.bsl.pojo.BslStockChangeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslStockChangeDetailMapper {
    int countByExample(BslStockChangeDetailExample example);

    int deleteByExample(BslStockChangeDetailExample example);

    int deleteByPrimaryKey(String transSerno);

    int insert(BslStockChangeDetail record);

    int insertSelective(BslStockChangeDetail record);

    List<BslStockChangeDetail> selectByExample(BslStockChangeDetailExample example);

    BslStockChangeDetail selectByPrimaryKey(String transSerno);

    int updateByExampleSelective(@Param("record") BslStockChangeDetail record, @Param("example") BslStockChangeDetailExample example);

    int updateByExample(@Param("record") BslStockChangeDetail record, @Param("example") BslStockChangeDetailExample example);

    int updateByPrimaryKeySelective(BslStockChangeDetail record);

    int updateByPrimaryKey(BslStockChangeDetail record);
}