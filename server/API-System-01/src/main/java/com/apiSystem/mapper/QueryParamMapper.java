package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.QueryParam;
import com.apiSystem.entity.po.api.QueryParamExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QueryParamMapper {
    int countByExample(QueryParamExample example);

    int deleteByExample(QueryParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QueryParam record);

    int insertSelective(QueryParam record);

    List<QueryParam> selectByExample(QueryParamExample example);

    QueryParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QueryParam record, @Param("example") QueryParamExample example);

    int updateByExample(@Param("record") QueryParam record, @Param("example") QueryParamExample example);

    int updateByPrimaryKeySelective(QueryParam record);

    int updateByPrimaryKey(QueryParam record);
}