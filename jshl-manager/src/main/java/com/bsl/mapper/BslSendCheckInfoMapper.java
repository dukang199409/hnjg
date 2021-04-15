package com.bsl.mapper;

import com.bsl.pojo.BslSendCheckInfo;
import com.bsl.pojo.BslSendCheckInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslSendCheckInfoMapper {
    int countByExample(BslSendCheckInfoExample example);

    int deleteByExample(BslSendCheckInfoExample example);

    int deleteByPrimaryKey(String luId);

    int insert(BslSendCheckInfo record);

    int insertSelective(BslSendCheckInfo record);

    List<BslSendCheckInfo> selectByExample(BslSendCheckInfoExample example);

    BslSendCheckInfo selectByPrimaryKey(String luId);

    int updateByExampleSelective(@Param("record") BslSendCheckInfo record, @Param("example") BslSendCheckInfoExample example);

    int updateByExample(@Param("record") BslSendCheckInfo record, @Param("example") BslSendCheckInfoExample example);

    int updateByPrimaryKeySelective(BslSendCheckInfo record);

    int updateByPrimaryKey(BslSendCheckInfo record);
}