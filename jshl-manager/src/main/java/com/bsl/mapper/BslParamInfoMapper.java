package com.bsl.mapper;

import com.bsl.pojo.BslParamInfo;
import com.bsl.pojo.BslParamInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslParamInfoMapper {
    int countByExample(BslParamInfoExample example);

    int deleteByExample(BslParamInfoExample example);

    int deleteByPrimaryKey(String paramId);

    int insert(BslParamInfo record);

    int insertSelective(BslParamInfo record);

    List<BslParamInfo> selectByExample(BslParamInfoExample example);

    BslParamInfo selectByPrimaryKey(String paramId);

    int updateByExampleSelective(@Param("record") BslParamInfo record, @Param("example") BslParamInfoExample example);

    int updateByExample(@Param("record") BslParamInfo record, @Param("example") BslParamInfoExample example);

    int updateByPrimaryKeySelective(BslParamInfo record);

    int updateByPrimaryKey(BslParamInfo record);
}