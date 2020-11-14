package com.apiSystem.service.api;

import com.apiSystem.entity.vo.RoleVO;
import com.apiSystem.entity.vo.UserAndRoleVO;

import java.util.List;

public interface RoleService {

    List<RoleVO> getAllRole(Integer pid);

    Integer addRole(Integer pid, String roleName);

    List<UserAndRoleVO> getUserAndRole(Integer pid);

    List<UserAndRoleVO> getUserAndRole(Integer pid, Integer rid);

    Integer addUserAndRole(Integer rid, Integer uid, Integer pid);

    Integer editRole(Integer rid, Integer uid, Integer pid);

    Boolean checkDeletePower(String token, Integer pid);

    Integer deleteUserAndRole(Integer uid, Integer pid);
}
