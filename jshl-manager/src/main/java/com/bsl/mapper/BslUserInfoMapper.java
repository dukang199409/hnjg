package com.bsl.mapper;

import com.bsl.pojo.BslUserInfo;
import com.bsl.pojo.BslUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslUserInfoMapper {
    int countByExample(BslUserInfoExample example);

    int deleteByExample(BslUserInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(BslUserInfo record);

    int insertSelective(BslUserInfo record);

    List<BslUserInfo> selectByExample(BslUserInfoExample example);

    BslUserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") BslUserInfo record, @Param("example") BslUserInfoExample example);

    int updateByExample(@Param("record") BslUserInfo record, @Param("example") BslUserInfoExample example);

    int updateByPrimaryKeySelective(BslUserInfo record);

    int updateByPrimaryKey(BslUserInfo record);
}