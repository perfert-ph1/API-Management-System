package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.Api;
import com.apiSystem.entity.po.api.ApiExample;
import com.apiSystem.entity.po.api.ApiWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiMapper {
    int countByExample(ApiExample example);

    int deleteByExample(ApiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiWithBLOBs record);

    int insertSelective(ApiWithBLOBs record);

    List<ApiWithBLOBs> selectByExampleWithBLOBs(ApiExample example);

    List<Api> selectByExample(ApiExample example);

    ApiWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiWithBLOBs record, @Param("example") ApiExample example);

    int updateByExampleWithBLOBs(@Param("record") ApiWithBLOBs record, @Param("example") ApiExample example);

    int updateByExample(@Param("record") Api record, @Param("example") ApiExample example);

    int updateByPrimaryKeySelective(ApiWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ApiWithBLOBs record);

    int updateByPrimaryKey(Api record);
}