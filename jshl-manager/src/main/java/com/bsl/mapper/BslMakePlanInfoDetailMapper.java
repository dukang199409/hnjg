package com.bsl.mapper;

import com.bsl.pojo.BslMakePlanInfoDetail;
import com.bsl.pojo.BslMakePlanInfoDetailExample;
import com.bsl.pojo.BslMakePlanInfoDetailJoinInfo;
import com.bsl.reportbean.BslMakePlanInfoDetailPro;
import com.bsl.select.QueryCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslMakePlanInfoDetailMapper {
    int countByExample(BslMakePlanInfoDetailExample example);

    int deleteByExample(BslMakePlanInfoDetailExample example);

    int deleteByPrimaryKey(String planInfoDetailId);

    int insert(BslMakePlanInfoDetail record);

    int insertSelective(BslMakePlanInfoDetail record);

    List<BslMakePlanInfoDetail> selectByExample(BslMakePlanInfoDetailExample example);
    
    List<BslMakePlanInfoDetailJoinInfo> selectMakePlanDetailInfoJoinPlan(QueryCriteria example);

    BslMakePlanInfoDetail selectByPrimaryKey(String planInfoDetailId);

    int updateByExampleSelective(@Param("record") BslMakePlanInfoDetail record, @Param("example") BslMakePlanInfoDetailExample example);

    int updateByExample(@Param("record") BslMakePlanInfoDetail record, @Param("example") BslMakePlanInfoDetailExample example);

    int updateByPrimaryKeySelective(BslMakePlanInfoDetail record);

    int updateByPrimaryKey(BslMakePlanInfoDetail record);
    
    List<BslMakePlanInfoDetailPro> selectMakePlanDetailPro(QueryCriteria example);
}