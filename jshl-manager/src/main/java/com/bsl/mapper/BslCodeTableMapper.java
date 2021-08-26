package com.bsl.mapper;

import com.bsl.pojo.BslCodeTableExample;
import com.bsl.pojo.BslCodeTableKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslCodeTableMapper {
    int countByExample(BslCodeTableExample example);

    int deleteByExample(BslCodeTableExample example);

    int deleteByPrimaryKey(BslCodeTableKey key);

    int insert(BslCodeTableKey record);

    int insertSelective(BslCodeTableKey record);

    List<BslCodeTableKey> selectByExample(BslCodeTableExample example);

    int updateByExampleSelective(@Param("record") BslCodeTableKey record, @Param("example") BslCodeTableExample example);

    int updateByExample(@Param("record") BslCodeTableKey record, @Param("example") BslCodeTableExample example);
}