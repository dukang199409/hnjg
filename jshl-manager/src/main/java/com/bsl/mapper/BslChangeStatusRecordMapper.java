package com.bsl.mapper;

import com.bsl.pojo.BslChangeStatusRecord;
import com.bsl.pojo.BslChangeStatusRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BslChangeStatusRecordMapper {
    int countByExample(BslChangeStatusRecordExample example);

    int deleteByExample(BslChangeStatusRecordExample example);

    int deleteByPrimaryKey(String changeSerno);

    int insert(BslChangeStatusRecord record);

    int insertSelective(BslChangeStatusRecord record);

    List<BslChangeStatusRecord> selectByExample(BslChangeStatusRecordExample example);

    BslChangeStatusRecord selectByPrimaryKey(String changeSerno);

    int updateByExampleSelective(@Param("record") BslChangeStatusRecord record, @Param("example") BslChangeStatusRecordExample example);

    int updateByExample(@Param("record") BslChangeStatusRecord record, @Param("example") BslChangeStatusRecordExample example);

    int updateByPrimaryKeySelective(BslChangeStatusRecord record);

    int updateByPrimaryKey(BslChangeStatusRecord record);
}