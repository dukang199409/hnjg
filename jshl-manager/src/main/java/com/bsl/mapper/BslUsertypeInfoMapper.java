package com.bsl.mapper;

import com.bsl.pojo.BslUsertypeInfo;
import com.bsl.pojo.BslUsertypeInfoExample;
import com.bsl.pojo.BslUsertypeInfoKey;
import com.bsl.pojo.UserInfoAndType;
import com.bsl.select.QueryCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslUsertypeInfoMapper {
    int countByExample(BslUsertypeInfoExample example);

    int deleteByExample(BslUsertypeInfoExample example);

    int deleteByPrimaryKey(BslUsertypeInfoKey key);

    int insert(BslUsertypeInfo record);

    int insertSelective(BslUsertypeInfo record);

    List<UserInfoAndType> selectByExampleNew(QueryCriteria queryCriteria);
    
    List<BslUsertypeInfo> selectByExample(BslUsertypeInfoExample example);

    BslUsertypeInfo selectByPrimaryKey(BslUsertypeInfoKey key);

    int updateByExampleSelective(@Param("record") BslUsertypeInfo record, @Param("example") BslUsertypeInfoExample example);

    int updateByExample(@Param("record") BslUsertypeInfo record, @Param("example") BslUsertypeInfoExample example);

    int updateByPrimaryKeySelective(BslUsertypeInfo record);

    int updateByPrimaryKey(BslUsertypeInfo record);
}