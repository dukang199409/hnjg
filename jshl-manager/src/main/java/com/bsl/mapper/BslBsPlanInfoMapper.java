package com.bsl.mapper;

import com.bsl.pojo.BslBsPlanInfo;
import com.bsl.pojo.BslBsPlanInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslBsPlanInfoMapper {
    int countByExample(BslBsPlanInfoExample example);

    int deleteByExample(BslBsPlanInfoExample example);

    int deleteByPrimaryKey(String bsId);

    int insert(BslBsPlanInfo record);

    int insertSelective(BslBsPlanInfo record);

    List<BslBsPlanInfo> selectByExample(BslBsPlanInfoExample example);

    BslBsPlanInfo selectByPrimaryKey(String bsId);

    int updateByExampleSelective(@Param("record") BslBsPlanInfo record, @Param("example") BslBsPlanInfoExample example);

    int updateByExample(@Param("record") BslBsPlanInfo record, @Param("example") BslBsPlanInfoExample example);

    int updateByPrimaryKeySelective(BslBsPlanInfo record);

    int updateByPrimaryKey(BslBsPlanInfo record);
}