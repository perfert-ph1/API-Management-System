package com.apiSystem.mapper;

import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.po.LoginTokenPOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LoginTokenPOMapper {
    int countByExample(LoginTokenPOExample example);

    int deleteByExample(LoginTokenPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginTokenPO record);

    int insertSelective(LoginTokenPO record);

    List<LoginTokenPO> selectByExample(LoginTokenPOExample example);

    LoginTokenPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginTokenPO record, @Param("example") LoginTokenPOExample example);

    int updateByExample(@Param("record") LoginTokenPO record, @Param("example") LoginTokenPOExample example);

    int updateByPrimaryKeySelective(LoginTokenPO record);

    int updateByPrimaryKey(LoginTokenPO record);
}