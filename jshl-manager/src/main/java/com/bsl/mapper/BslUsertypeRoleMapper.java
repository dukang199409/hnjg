package com.bsl.mapper;

import com.bsl.pojo.BslUsertypeRole;
import com.bsl.pojo.BslUsertypeRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslUsertypeRoleMapper {
    int countByExample(BslUsertypeRoleExample example);

    int deleteByExample(BslUsertypeRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(BslUsertypeRole record);

    int insertSelective(BslUsertypeRole record);

    List<BslUsertypeRole> selectByExample(BslUsertypeRoleExample example);

    BslUsertypeRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") BslUsertypeRole record, @Param("example") BslUsertypeRoleExample example);

    int updateByExample(@Param("record") BslUsertypeRole record, @Param("example") BslUsertypeRoleExample example);

    int updateByPrimaryKeySelective(BslUsertypeRole record);

    int updateByPrimaryKey(BslUsertypeRole record);
}