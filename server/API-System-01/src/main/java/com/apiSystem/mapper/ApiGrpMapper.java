package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.po.api.ApiGrpExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiGrpMapper {
    int countByExample(ApiGrpExample example);

    int deleteByExample(ApiGrpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiGrp record);

    int insertSelective(ApiGrp record);

    List<ApiGrp> selectByExample(ApiGrpExample example);

    ApiGrp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiGrp record, @Param("example") ApiGrpExample example);

    int updateByExample(@Param("record") ApiGrp record, @Param("example") ApiGrpExample example);

    int updateByPrimaryKeySelective(ApiGrp record);

    int updateByPrimaryKey(ApiGrp record);
}