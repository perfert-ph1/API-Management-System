package com.apiSystem.mapper;

import com.apiSystem.entity.po.StatusGrp;
import com.apiSystem.entity.po.StatusGrpExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatusGrpMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StatusGrp record);

    int insertSelective(StatusGrp record);

    List<StatusGrp> selectByExample(StatusGrpExample example);

    StatusGrp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StatusGrp record, @Param("example") StatusGrpExample example);

    int updateByExample(@Param("record") StatusGrp record, @Param("example") StatusGrpExample example);

    int updateByPrimaryKeySelective(StatusGrp record);

    int updateByPrimaryKey(StatusGrp record);
}