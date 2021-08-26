package com.bsl.mapper;

import com.bsl.pojo.BslEnumInfo;
import com.bsl.pojo.BslEnumInfoExample;
import com.bsl.pojo.BslEnumInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslEnumInfoMapper {
    int countByExample(BslEnumInfoExample example);

    int deleteByExample(BslEnumInfoExample example);

    int deleteByPrimaryKey(BslEnumInfoKey key);

    int insert(BslEnumInfo record);

    int insertSelective(BslEnumInfo record);

    List<BslEnumInfo> selectByExample(BslEnumInfoExample example);

    BslEnumInfo selectByPrimaryKey(BslEnumInfoKey key);

    int updateByExampleSelective(@Param("record") BslEnumInfo record, @Param("example") BslEnumInfoExample example);

    int updateByExample(@Param("record") BslEnumInfo record, @Param("example") BslEnumInfoExample example);

    int updateByPrimaryKeySelective(BslEnumInfo record);

    int updateByPrimaryKey(BslEnumInfo record);
    
    //获取去重后所有的枚举英文名和中文名
    List<BslEnumInfo> getEnumChiEngNames();
}