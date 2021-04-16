package com.bsl.mapper;

import com.bsl.pojo.BslProductInfoH;
import com.bsl.pojo.BslProductInfoHExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslProductInfoHMapper {
    int countByExample(BslProductInfoHExample example);

    int deleteByExample(BslProductInfoHExample example);

    int insert(BslProductInfoH record);

    int insertSelective(BslProductInfoH record);

    List<BslProductInfoH> selectByExample(BslProductInfoHExample example);

    int updateByExampleSelective(@Param("record") BslProductInfoH record, @Param("example") BslProductInfoHExample example);

    int updateByExample(@Param("record") BslProductInfoH record, @Param("example") BslProductInfoHExample example);
}