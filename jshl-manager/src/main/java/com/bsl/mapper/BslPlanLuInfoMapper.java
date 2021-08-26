package com.bsl.mapper;

import com.bsl.pojo.BslPlanLuInfo;
import com.bsl.pojo.BslPlanLuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslPlanLuInfoMapper {
    int countByExample(BslPlanLuInfoExample example);

    int deleteByExample(BslPlanLuInfoExample example);

    int deleteByPrimaryKey(Integer no);

    int insert(BslPlanLuInfo record);

    int insertSelective(BslPlanLuInfo record);

    List<BslPlanLuInfo> selectByExample(BslPlanLuInfoExample example);

    BslPlanLuInfo selectByPrimaryKey(Integer no);

    int updateByExampleSelective(@Param("record") BslPlanLuInfo record, @Param("example") BslPlanLuInfoExample example);

    int updateByExample(@Param("record") BslPlanLuInfo record, @Param("example") BslPlanLuInfoExample example);

    int updateByPrimaryKeySelective(BslPlanLuInfo record);

    int updateByPrimaryKey(BslPlanLuInfo record);
}