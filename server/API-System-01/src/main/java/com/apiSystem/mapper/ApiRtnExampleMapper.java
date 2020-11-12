package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.ApiRtnExample;
import com.apiSystem.entity.po.api.ApiRtnExampleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiRtnExampleMapper {
    int countByExample(ApiRtnExampleExample example);

    int deleteByExample(ApiRtnExampleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiRtnExample record);

    int insertSelective(ApiRtnExample record);

    List<ApiRtnExample> selectByExampleWithBLOBs(ApiRtnExampleExample example);

    List<ApiRtnExample> selectByExample(ApiRtnExampleExample example);

    ApiRtnExample selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiRtnExample record, @Param("example") ApiRtnExampleExample example);

    int updateByExampleWithBLOBs(@Param("record") ApiRtnExample record, @Param("example") ApiRtnExampleExample example);

    int updateByExample(@Param("record") ApiRtnExample record, @Param("example") ApiRtnExampleExample example);

    int updateByPrimaryKeySelective(ApiRtnExample record);

    int updateByPrimaryKeyWithBLOBs(ApiRtnExample record);

    int updateByPrimaryKey(ApiRtnExample record);
}