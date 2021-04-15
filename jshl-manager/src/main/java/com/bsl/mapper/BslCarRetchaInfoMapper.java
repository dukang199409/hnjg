package com.bsl.mapper;

import com.bsl.pojo.BslCarRetchaInfo;
import com.bsl.pojo.BslCarRetchaInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslCarRetchaInfoMapper {
    int countByExample(BslCarRetchaInfoExample example);

    int deleteByExample(BslCarRetchaInfoExample example);

    int deleteByPrimaryKey(String carNo);

    int insert(BslCarRetchaInfo record);

    int insertSelective(BslCarRetchaInfo record);

    List<BslCarRetchaInfo> selectByExample(BslCarRetchaInfoExample example);

    BslCarRetchaInfo selectByPrimaryKey(String carNo);

    int updateByExampleSelective(@Param("record") BslCarRetchaInfo record, @Param("example") BslCarRetchaInfoExample example);

    int updateByExample(@Param("record") BslCarRetchaInfo record, @Param("example") BslCarRetchaInfoExample example);

    int updateByPrimaryKeySelective(BslCarRetchaInfo record);

    int updateByPrimaryKey(BslCarRetchaInfo record);
}