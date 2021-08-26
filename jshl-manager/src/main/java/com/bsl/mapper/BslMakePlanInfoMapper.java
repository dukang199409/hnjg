package com.bsl.mapper;

import com.bsl.pojo.BslMakePlanInfo;
import com.bsl.pojo.BslMakePlanInfoExample;
import com.bsl.reportbean.BslMakePlanInfoPro;
import com.bsl.select.QueryCriteria;
import com.bsl.select.QueryExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslMakePlanInfoMapper {
    int countByExample(BslMakePlanInfoExample example);

    int deleteByExample(BslMakePlanInfoExample example);

    int deleteByPrimaryKey(String planId);

    int insert(BslMakePlanInfo record);

    int insertSelective(BslMakePlanInfo record);

    List<BslMakePlanInfo> selectByExample(BslMakePlanInfoExample example);

    BslMakePlanInfo selectByPrimaryKey(String planId);

    int updateByExampleSelective(@Param("record") BslMakePlanInfo record, @Param("example") BslMakePlanInfoExample example);

    int updateByExample(@Param("record") BslMakePlanInfo record, @Param("example") BslMakePlanInfoExample example);

    int updateByPrimaryKeySelective(BslMakePlanInfo record);

    int updateByPrimaryKey(BslMakePlanInfo record);
    
    List<BslMakePlanInfoPro> selectByExampleCCrate(QueryCriteria example);
    List<BslMakePlanInfoPro> selectByExampleCCrate2(QueryExample example);
}