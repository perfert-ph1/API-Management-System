package com.apiSystem.mapper;

import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.po.StatusExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatusMapper {
    int insertBatch(List<Status> statuses);

    int deleteByPrimaryKey(Integer id);

    int deleteBatch(List<Integer> ids);

    int insert(Status record);

    int insertSelective(Status record);

    List<Status> selectByExample(StatusExample example);

    Status selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Status record, @Param("example") StatusExample example);

    int updateByExample(@Param("record") Status record, @Param("example") StatusExample example);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);
}