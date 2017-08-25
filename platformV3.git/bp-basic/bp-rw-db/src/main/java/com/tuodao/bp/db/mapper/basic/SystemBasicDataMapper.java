package com.tuodao.bp.db.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuodao.bp.db.model.basic.SystemBasicData;
import com.tuodao.bp.db.model.basic.SystemBasicDataExample;

public interface SystemBasicDataMapper {
    int countByExample(SystemBasicDataExample example);

    int deleteByExample(SystemBasicDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemBasicData record);

    int insertSelective(SystemBasicData record);

    List<SystemBasicData> selectByExample(SystemBasicDataExample example);

    SystemBasicData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemBasicData record, @Param("example") SystemBasicDataExample example);

    int updateByExample(@Param("record") SystemBasicData record, @Param("example") SystemBasicDataExample example);

    int updateByPrimaryKeySelective(SystemBasicData record);

    int updateByPrimaryKey(SystemBasicData record);
}