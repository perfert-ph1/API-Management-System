package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.RespBody;
import com.apiSystem.entity.po.api.RespBodyExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RespBodyMapper {
    int countByExample(RespBodyExample example);

    int deleteByExample(RespBodyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RespBody record);

    int insertSelective(RespBody record);

    List<RespBody> selectByExampleWithBLOBs(RespBodyExample example);

    List<RespBody> selectByExample(RespBodyExample example);

    RespBody selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RespBody record, @Param("example") RespBodyExample example);

    int updateByExampleWithBLOBs(@Param("record") RespBody record, @Param("example") RespBodyExample example);

    int updateByExample(@Param("record") RespBody record, @Param("example") RespBodyExample example);

    int updateByPrimaryKeySelective(RespBody record);

    int updateByPrimaryKeyWithBLOBs(RespBody record);

    int updateByPrimaryKey(RespBody record);
}