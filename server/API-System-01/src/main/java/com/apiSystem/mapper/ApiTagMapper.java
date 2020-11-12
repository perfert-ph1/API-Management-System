package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.ApiTag;
import com.apiSystem.entity.po.api.ApiTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiTagMapper {
    int countByExample(ApiTagExample example);

    int deleteByExample(ApiTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiTag record);

    int insertSelective(ApiTag record);

    List<ApiTag> selectByExample(ApiTagExample example);

    ApiTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiTag record, @Param("example") ApiTagExample example);

    int updateByExample(@Param("record") ApiTag record, @Param("example") ApiTagExample example);

    int updateByPrimaryKeySelective(ApiTag record);

    int updateByPrimaryKey(ApiTag record);
}