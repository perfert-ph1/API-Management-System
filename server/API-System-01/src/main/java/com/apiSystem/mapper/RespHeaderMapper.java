package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.RespHeader;
import com.apiSystem.entity.po.api.RespHeaderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RespHeaderMapper {
    int countByExample(RespHeaderExample example);

    int deleteByExample(RespHeaderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RespHeader record);

    int insertSelective(RespHeader record);

    List<RespHeader> selectByExample(RespHeaderExample example);

    RespHeader selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RespHeader record, @Param("example") RespHeaderExample example);

    int updateByExample(@Param("record") RespHeader record, @Param("example") RespHeaderExample example);

    int updateByPrimaryKeySelective(RespHeader record);

    int updateByPrimaryKey(RespHeader record);
}