package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.Response;
import com.apiSystem.entity.po.api.ResponseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResponseMapper {
    int countByExample(ResponseExample example);

    int deleteByExample(ResponseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Response record);

    int insertSelective(Response record);

    List<Response> selectByExample(ResponseExample example);

    Response selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByExample(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByPrimaryKeySelective(Response record);

    int updateByPrimaryKey(Response record);
}