package com.bsl.mapper;

import com.bsl.pojo.BslLunoQuality;
import com.bsl.pojo.BslLunoQualityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslLunoQualityMapper {
    int countByExample(BslLunoQualityExample example);

    int deleteByExample(BslLunoQualityExample example);

    int deleteByPrimaryKey(String luId);

    int insert(BslLunoQuality record);

    int insertSelective(BslLunoQuality record);

    List<BslLunoQuality> selectByExample(BslLunoQualityExample example);

    BslLunoQuality selectByPrimaryKey(String luId);

    int updateByExampleSelective(@Param("record") BslLunoQuality record, @Param("example") BslLunoQualityExample example);

    int updateByExample(@Param("record") BslLunoQuality record, @Param("example") BslLunoQualityExample example);

    int updateByPrimaryKeySelective(BslLunoQuality record);

    int updateByPrimaryKey(BslLunoQuality record);
}