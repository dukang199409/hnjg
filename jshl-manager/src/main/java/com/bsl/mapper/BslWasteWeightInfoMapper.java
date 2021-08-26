package com.bsl.mapper;

import com.bsl.pojo.BslWasteWeightInfo;
import com.bsl.pojo.BslWasteWeightInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslWasteWeightInfoMapper {
    int countByExample(BslWasteWeightInfoExample example);

    int deleteByExample(BslWasteWeightInfoExample example);

    int deleteByPrimaryKey(String wasteType);

    int insert(BslWasteWeightInfo record);

    int insertSelective(BslWasteWeightInfo record);

    List<BslWasteWeightInfo> selectByExample(BslWasteWeightInfoExample example);

    BslWasteWeightInfo selectByPrimaryKey(String wasteType);

    int updateByExampleSelective(@Param("record") BslWasteWeightInfo record, @Param("example") BslWasteWeightInfoExample example);

    int updateByExample(@Param("record") BslWasteWeightInfo record, @Param("example") BslWasteWeightInfoExample example);

    int updateByPrimaryKeySelective(BslWasteWeightInfo record);

    int updateByPrimaryKey(BslWasteWeightInfo record);
}