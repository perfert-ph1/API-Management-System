package com.apiSystem.mapper;

import com.apiSystem.entity.po.UserInnerProjectPO;
import com.apiSystem.entity.po.UserInnerProjectPOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInnerProjectPOMapper {
    int countByExample(UserInnerProjectPOExample example);

    int deleteByExample(UserInnerProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInnerProjectPO record);

    int insertSelective(UserInnerProjectPO record);

    List<UserInnerProjectPO> selectByExample(UserInnerProjectPOExample example);

    UserInnerProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInnerProjectPO record, @Param("example") UserInnerProjectPOExample example);

    int updateByExample(@Param("record") UserInnerProjectPO record, @Param("example") UserInnerProjectPOExample example);

    int updateByPrimaryKeySelective(UserInnerProjectPO record);

    int updateByPrimaryKey(UserInnerProjectPO record);
}