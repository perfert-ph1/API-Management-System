package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.Request;
import com.apiSystem.entity.po.api.RequestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequestMapper {
    int countByExample(RequestExample example);

    int deleteByExample(RequestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Request record);

    int insertSelective(Request record);

    List<Request> selectByExample(RequestExample example);

    Request selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByExample(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKey(Request record);
}