package com.apiSystem.mapper;

import com.apiSystem.entity.po.UserInnerRolePO;
import com.apiSystem.entity.po.UserInnerRolePOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInnerRolePOMapper {
    int countByExample(UserInnerRolePOExample example);

    int deleteByExample(UserInnerRolePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInnerRolePO record);

    int insertSelective(UserInnerRolePO record);

    List<UserInnerRolePO> selectByExample(UserInnerRolePOExample example);

    UserInnerRolePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInnerRolePO record, @Param("example") UserInnerRolePOExample example);

    int updateByExample(@Param("record") UserInnerRolePO record, @Param("example") UserInnerRolePOExample example);

    int updateByPrimaryKeySelective(UserInnerRolePO record);

    int updateByPrimaryKey(UserInnerRolePO record);
}