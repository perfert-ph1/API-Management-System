package com.apiSystem.service.api;

import com.apiSystem.entity.vo.RoleVO;
import com.apiSystem.entity.vo.UserAndRoleVO;

import java.util.List;

public interface RoleService {
    /**
     * 获取所有角色
     * @param pid
     * @return
     */
    List<RoleVO> getAllRole(Integer pid);

    /**
     * 添加角色
     * @param pid
     * @param roleName
     * @return
     */
    Integer addRole(Integer pid, String roleName);

    /**
     * 获取项目下所有成员
     * @param pid
     * @return
     */
    List<UserAndRoleVO> getMembers(Integer pid);

    /**
     * 获取项目下所属角色下的用户
     * @param pid
     * @param rid
     * @return
     */
    List<UserAndRoleVO> getMembers(Integer pid, Integer rid);

    /**
     * 获取所有非管理者的项目成员
     * @param pid
     * @return
     */
    List<UserAndRoleVO> getMemberNotManager(Integer pid);

    /**
     * 添加用户
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    Integer addMember(Integer rid, Integer uid, Integer pid);

    /**
     * 修改角色
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    Integer editMemberRole(Integer rid, Integer uid, Integer pid);

    /**
     * 判断是否有删除权限
     * @param token
     * @param pid
     * @return
     */
    Boolean checkDeletePower(String token, Integer pid);

    /**
     * 删除角色
     * @param uid
     * @param pid
     * @return
     */
    Integer deleteMember(Integer uid, Integer pid);

    /**
     * 批量删除角色
     * @param uids
     * @param pid
     * @return
     */
    Integer batchDeleteMember(List<Integer> uids, Integer pid);


    /**
     * 通过pid和用户名
     * @param pid
     * @param username
     * @return
     */
    List<UserAndRoleVO> getMemberByUsername(Integer pid, String username);
}
