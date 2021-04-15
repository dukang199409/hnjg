package com.bsl.mapper;

import com.bsl.pojo.BslMenuInfo;
import com.bsl.pojo.BslMenuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslMenuInfoMapper {
    int countByExample(BslMenuInfoExample example);

    int deleteByExample(BslMenuInfoExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(BslMenuInfo record);

    int insertSelective(BslMenuInfo record);

    List<BslMenuInfo> selectByExample(BslMenuInfoExample example);

    BslMenuInfo selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") BslMenuInfo record, @Param("example") BslMenuInfoExample example);

    int updateByExample(@Param("record") BslMenuInfo record, @Param("example") BslMenuInfoExample example);

    int updateByPrimaryKeySelective(BslMenuInfo record);

    int updateByPrimaryKey(BslMenuInfo record);
}