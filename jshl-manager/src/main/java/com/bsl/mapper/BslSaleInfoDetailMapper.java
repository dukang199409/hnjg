package com.bsl.mapper;

import com.bsl.pojo.BslSaleInfoDetail;
import com.bsl.pojo.BslSaleInfoDetailExample;
import com.bsl.reportbean.BslWasteSaleDetailInfo;
import com.bsl.select.QueryCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslSaleInfoDetailMapper {
    int countByExample(BslSaleInfoDetailExample example);

    int deleteByExample(BslSaleInfoDetailExample example);

    int deleteByPrimaryKey(String saleSerno);

    int insert(BslSaleInfoDetail record);

    int insertSelective(BslSaleInfoDetail record);

    List<BslSaleInfoDetail> selectByExample(BslSaleInfoDetailExample example);

    BslSaleInfoDetail selectByPrimaryKey(String saleSerno);

    int updateByExampleSelective(@Param("record") BslSaleInfoDetail record, @Param("example") BslSaleInfoDetailExample example);

    int updateByExample(@Param("record") BslSaleInfoDetail record, @Param("example") BslSaleInfoDetailExample example);

    int updateByPrimaryKeySelective(BslSaleInfoDetail record);

    int updateByPrimaryKey(BslSaleInfoDetail record);
    
    List<BslSaleInfoDetail> getAllJsWastes(QueryCriteria queryCriteria);
    
    List<BslWasteSaleDetailInfo> getAllWasteSaleInfo(QueryCriteria queryCriteria);
}