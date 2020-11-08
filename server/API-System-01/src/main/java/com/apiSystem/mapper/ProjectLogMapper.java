package com.apiSystem.mapper;

import com.apiSystem.entity.po.ProjectLog;
import com.apiSystem.entity.po.ProjectLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectLog record);

    int insertSelective(ProjectLog record);

    List<ProjectLog> selectByExample(ProjectLogExample example);

    ProjectLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectLog record, @Param("example") ProjectLogExample example);

    int updateByExample(@Param("record") ProjectLog record, @Param("example") ProjectLogExample example);

    int updateByPrimaryKeySelective(ProjectLog record);

    int updateByPrimaryKey(ProjectLog record);
}