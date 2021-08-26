package com.bsl.mapper;

import com.bsl.pojo.BslWxWasteWeightInfo;
import com.bsl.pojo.BslWxWasteWeightInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslWxWasteWeightInfoMapper {
    int countByExample(BslWxWasteWeightInfoExample example);

    int deleteByExample(BslWxWasteWeightInfoExample example);

    int deleteByPrimaryKey(String wasteType);

    int insert(BslWxWasteWeightInfo record);

    int insertSelective(BslWxWasteWeightInfo record);

    List<BslWxWasteWeightInfo> selectByExample(BslWxWasteWeightInfoExample example);

    BslWxWasteWeightInfo selectByPrimaryKey(String wasteType);

    int updateByExampleSelective(@Param("record") BslWxWasteWeightInfo record, @Param("example") BslWxWasteWeightInfoExample example);

    int updateByExample(@Param("record") BslWxWasteWeightInfo record, @Param("example") BslWxWasteWeightInfoExample example);

    int updateByPrimaryKeySelective(BslWxWasteWeightInfo record);

    int updateByPrimaryKey(BslWxWasteWeightInfo record);
}