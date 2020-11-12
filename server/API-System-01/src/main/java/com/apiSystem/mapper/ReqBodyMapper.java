package com.apiSystem.mapper;

import com.apiSystem.entity.po.api.ReqBody;
import com.apiSystem.entity.po.api.ReqBodyExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReqBodyMapper {
    int countByExample(ReqBodyExample example);

    int deleteByExample(ReqBodyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReqBody record);

    int insertSelective(ReqBody record);

    List<ReqBody> selectByExampleWithBLOBs(ReqBodyExample example);

    List<ReqBody> selectByExample(ReqBodyExample example);

    ReqBody selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReqBody record, @Param("example") ReqBodyExample example);

    int updateByExampleWithBLOBs(@Param("record") ReqBody record, @Param("example") ReqBodyExample example);

    int updateByExample(@Param("record") ReqBody record, @Param("example") ReqBodyExample example);

    int updateByPrimaryKeySelective(ReqBody record);

    int updateByPrimaryKeyWithBLOBs(ReqBody record);

    int updateByPrimaryKey(ReqBody record);
}