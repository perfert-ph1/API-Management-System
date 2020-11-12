package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.ReqHeader;
import com.apiSystem.entity.po.api.ReqHeaderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReqHeaderMapper {
    int countByExample(ReqHeaderExample example);

    int deleteByExample(ReqHeaderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReqHeader record);

    int insertSelective(ReqHeader record);

    List<ReqHeader> selectByExample(ReqHeaderExample example);

    ReqHeader selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReqHeader record, @Param("example") ReqHeaderExample example);

    int updateByExample(@Param("record") ReqHeader record, @Param("example") ReqHeaderExample example);

    int updateByPrimaryKeySelective(ReqHeader record);

    int updateByPrimaryKey(ReqHeader record);
}