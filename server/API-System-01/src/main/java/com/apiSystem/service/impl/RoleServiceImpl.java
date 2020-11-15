package com.apiSystem.service.impl;

import com.apiSystem.entity.po.*;
import com.apiSystem.entity.vo.UserAndRoleVO;
import com.apiSystem.entity.vo.UserVo;
import com.apiSystem.mapper.*;
import com.apiSystem.service.api.RoleService;
import com.apiSystem.entity.vo.RoleVO;
import com.apiSystem.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolePOMapper rolePOMapper;

    @Autowired
    private UserInnerRolePOMapper userInnerRolePOMapper;

    @Autowired
    private UserInnerProjectPOMapper userInnerProjectPOMapper;

    @Autowired
    private UserPOMapper userPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private UserService userService;

    /**
     * 根据pid获取角色对象
     * @param pid
     * @return
     */
    @Override
    public List<RoleVO> getAllRole(Integer pid) {
        if(pid == null){
            return null;
        }
        RolePOExample example = new RolePOExample();
        example.createCriteria().andPidEqualTo(pid);
        List<RolePO> rolePOList = rolePOMapper.selectByExample(example);
        List<RoleVO> roleVOS = rolePO2RoleVo(rolePOList);
        return roleVOS;
    }

    /**
     * 根据pid和角色名称添加角色
     * @param pid
     * @param roleName
     * @return
     */
    @Override
    public Integer addRole(Integer pid, String roleName) {
        //查询是否有重复名字
        RolePOExample example = new RolePOExample();
        example.createCriteria().andRoleNameEqualTo(roleName).andPidEqualTo(pid);
        List<RolePO> rolePOS = rolePOMapper.selectByExample(example);
        if(rolePOS.size() > 0){
            return -1;
        }
        RolePO rolePO = new RolePO();
        rolePO.setPid(pid);
        rolePO.setRoleName(roleName);
        rolePOMapper.insert(rolePO);
        return 1;
    }




    /**
     * 通过pid查询用户以及其对应的角色
     * @param pid
     * @return
     */
    @Override
    public List<UserAndRoleVO> getMembers(Integer pid) {
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andPidEqualTo(pid);
        List<UserInnerRolePO> userInnerRolePOList = userInnerRolePOMapper.selectByExample(userInnerRolePOExample);
        List<Integer> uids = userInnerRolePOS2Uids(userInnerRolePOList);
        List<UserAndRoleVO> userAndRoleVOS = new ArrayList<>();
        for (Integer uid:uids) {
            UserPO userPO = userPOMapper.selectByPrimaryKey(uid);
            UserAndRoleVO userAndRoleVO = new UserAndRoleVO();
            userAndRoleVO.setUid(uid);
            userAndRoleVO.setHeaderUrl(userPO.getHeaderUrl());
            userAndRoleVO.setUsername(userPO.getUsername());
            ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
            if(uid == projectPO.getCreatorId()){
                userAndRoleVO.setIsManager(1);
            }else {
                userAndRoleVO.setIsManager(0);
            }
            UserInnerRolePOExample example = new UserInnerRolePOExample();
            example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
            List<UserInnerRolePO> userInnerRolePOS = userInnerRolePOMapper.selectByExample(example);
            UserInnerRolePO userInnerRolePO = userInnerRolePOS.get(0);
            Integer rid = userInnerRolePO.getRid();
            RolePO rolePO = rolePOMapper.selectByPrimaryKey(rid);
            userAndRoleVO.setRoleName(rolePO.getRoleName());
            userAndRoleVO.setRid(rolePO.getId());
            userAndRoleVOS.add(userAndRoleVO);
        }
        return userAndRoleVOS;
    }

    /**
     * 通过pid和rid返回用户信息以及对应的角色
     * @param pid
     * @param rid
     * @return
     */
    @Override
    public List<UserAndRoleVO> getMembers(Integer pid, Integer rid) {
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andPidEqualTo(pid).andRidEqualTo(rid);
        List<UserInnerRolePO> userInnerRolePOList = userInnerRolePOMapper.selectByExample(userInnerRolePOExample);
        List<Integer> uids = userInnerRolePOS2Uids(userInnerRolePOList);
        List<UserAndRoleVO> userAndRoleVOS = new ArrayList<>();
        for (Integer uid:uids) {
            UserPO userPO = userPOMapper.selectByPrimaryKey(uid);
            UserAndRoleVO userAndRoleVO = new UserAndRoleVO();
            userAndRoleVO.setUid(uid);
            userAndRoleVO.setHeaderUrl(userPO.getHeaderUrl());
            userAndRoleVO.setUsername(userPO.getUsername());
            ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
            if(uid == projectPO.getCreatorId()){
                userAndRoleVO.setIsManager(1);
            }else {
                userAndRoleVO.setIsManager(0);
            }
            RolePO rolePO = rolePOMapper.selectByPrimaryKey(rid);
            userAndRoleVO.setRoleName(rolePO.getRoleName());
            userAndRoleVO.setRid(rolePO.getId());
            userAndRoleVOS.add(userAndRoleVO);
        }
        return userAndRoleVOS;
    }

    /**
     * 通过pid返回非项目管理者的成员
     * @param pid
     * @return
     */
    @Override
    public List<UserAndRoleVO> getMemberNotManager(Integer pid) {
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andPidEqualTo(pid);
        List<UserInnerRolePO> userInnerRolePOList = userInnerRolePOMapper.selectByExample(userInnerRolePOExample);
        List<Integer> uids = userInnerRolePOS2Uids(userInnerRolePOList);
        List<UserAndRoleVO> userAndRoleVOS = new ArrayList<>();
        for (Integer uid:uids) {
            ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
            if(uid == projectPO.getCreatorId()){
                continue;
            }
            UserPO userPO = userPOMapper.selectByPrimaryKey(uid);
            UserAndRoleVO userAndRoleVO = new UserAndRoleVO();
            userAndRoleVO.setUid(uid);
            userAndRoleVO.setHeaderUrl(userPO.getHeaderUrl());
            userAndRoleVO.setUsername(userPO.getUsername());
            userAndRoleVO.setIsManager(0);
            UserInnerRolePOExample example = new UserInnerRolePOExample();
            example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
            List<UserInnerRolePO> userInnerRolePOS = userInnerRolePOMapper.selectByExample(example);
            UserInnerRolePO userInnerRolePO = userInnerRolePOS.get(0);
            Integer rid = userInnerRolePO.getRid();
            RolePO rolePO = rolePOMapper.selectByPrimaryKey(rid);
            userAndRoleVO.setRoleName(rolePO.getRoleName());
            userAndRoleVO.setRid(rolePO.getId());
            userAndRoleVOS.add(userAndRoleVO);
        }
        return userAndRoleVOS;
    }

    /**
     * 添加用户以及其对应的角色
     * 需要检验是否重复
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    @Override
    public Integer addMember(Integer rid, Integer uid, Integer pid) {
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andRidEqualTo(rid).andPidEqualTo(pid).andUidEqualTo(uid);
        List<UserInnerRolePO> userInnerRolePOS = userInnerRolePOMapper.selectByExample(userInnerRolePOExample);
        if(userInnerRolePOS.size()>0){
            return -1;
        }
        //插入inner_role_user表
        UserInnerRolePO userInnerRolePO = new UserInnerRolePO();
        userInnerRolePO.setPid(pid);
        userInnerRolePO.setUid(uid);
        userInnerRolePO.setRid(rid);
        int status = userInnerRolePOMapper.insert(userInnerRolePO);
        //插入user_project表
        UserInnerProjectPO userInnerProjectPO = new UserInnerProjectPO();
        userInnerProjectPO.setUid(uid);
        userInnerProjectPO.setPid(pid);
        int flag = userInnerProjectPOMapper.insert(userInnerProjectPO);
        return status+flag;
    }

    /**
     * 根据rid、uid为用户重新分配角色
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    @Override
    public Integer editMemberRole(Integer rid, Integer uid, Integer pid) {
        //修改inner_role_user表
        UserInnerRolePOExample example = new UserInnerRolePOExample();
        example.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
        List<UserInnerRolePO> userInnerRolePOS = userInnerRolePOMapper.selectByExample(example);
        UserInnerRolePO userInnerRolePO = userInnerRolePOS.get(0);
        userInnerRolePO.setRid(rid);
        int status = userInnerRolePOMapper.updateByPrimaryKeySelective(userInnerRolePO);
        return status;
    }

    /**
     * 查看是否具有删除权限
     * true - 有、false - 无
     * @param token
     * @param pid
     * @return
     */
    @Override
    public Boolean checkDeletePower(String token, Integer pid) {
        UserVo user = userService.findUserByToken(token);
        Integer userId = user.getId();
        ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
        if(userId != projectPO.getCreatorId()){
            return false;
        }
        return true;
    }

    /**
     * 删除项目成员
     * @param uid
     * @param pid
     * @return
     */
    @Override
    public Integer deleteMember(Integer uid, Integer pid) {
        //inner_role_user表
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
        int status1 = userInnerRolePOMapper.deleteByExample(userInnerRolePOExample);
        //user_project表
        UserInnerProjectPOExample userInnerProjectPOExample = new UserInnerProjectPOExample();
        userInnerProjectPOExample.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
        int status2 = userInnerProjectPOMapper.deleteByExample(userInnerProjectPOExample);
        return status1+status2;
    }

    /**
     * 批量删除项目成员
     * @param uids
     * @param pid
     * @return
     */
    @Override
    public Integer batchDeleteMember(List<Integer> uids, Integer pid) {
        for (Integer uid:uids) {
            //inner_role_user表
            UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
            userInnerRolePOExample.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
            int status1 = userInnerRolePOMapper.deleteByExample(userInnerRolePOExample);
            //user_project表
            UserInnerProjectPOExample userInnerProjectPOExample = new UserInnerProjectPOExample();
            userInnerProjectPOExample.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
            int status2 = userInnerProjectPOMapper.deleteByExample(userInnerProjectPOExample);
            if(status1 + status2 <= 1 ){
                return -1;
            }
        }
        return 1;
    }

    @Override
    public List<UserAndRoleVO> getMemberByUsername(Integer pid, String username) {
        UserInnerRolePOExample userInnerRolePOExample = new UserInnerRolePOExample();
        userInnerRolePOExample.createCriteria().andPidEqualTo(pid);
        List<UserInnerRolePO> userInnerRolePOList = userInnerRolePOMapper.selectByExample(userInnerRolePOExample);
        List<Integer> uids = userInnerRolePOS2Uids(userInnerRolePOList);
        List<UserAndRoleVO> userAndRoleVOS = new ArrayList<>();
        for (Integer uid:uids) {
            UserPOExample userPOExample = new UserPOExample();
            userPOExample.createCriteria().andUsernameLike("%"+username+"%").andIdEqualTo(uid);
            List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
            if(userPOS == null || userPOS.size() == 0){
                continue;
            }
            UserPO userPO = userPOS.get(0);
            UserAndRoleVO userAndRoleVO = new UserAndRoleVO();
            userAndRoleVO.setUid(uid);
            userAndRoleVO.setHeaderUrl(userPO.getHeaderUrl());
            userAndRoleVO.setUsername(userPO.getUsername());
            ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
            if(uid == projectPO.getCreatorId()){
                userAndRoleVO.setIsManager(1);
            }else {
                userAndRoleVO.setIsManager(0);
            }
            UserInnerRolePOExample example = new UserInnerRolePOExample();
            example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
            List<UserInnerRolePO> userInnerRolePOS = userInnerRolePOMapper.selectByExample(example);
            UserInnerRolePO userInnerRolePO = userInnerRolePOS.get(0);
            Integer rid = userInnerRolePO.getRid();
            RolePO rolePO = rolePOMapper.selectByPrimaryKey(rid);
            userAndRoleVO.setRoleName(rolePO.getRoleName());
            userAndRoleVO.setRid(rolePO.getId());
            userAndRoleVOS.add(userAndRoleVO);
        }
        return userAndRoleVOS;
    }

    /**
     * 将uids从userInnerProjectPOS提取出来
     * @param userInnerRolePOS
     * @return
     */
    private List<Integer> userInnerRolePOS2Uids(List<UserInnerRolePO> userInnerRolePOS){
        if(userInnerRolePOS == null){
            return null;
        }
        List<Integer> uids = new ArrayList<>();
        for (int i = 0; i < userInnerRolePOS.size(); i++) {
            uids.add(userInnerRolePOS.get(i).getUid());
        }
        return uids;
    }

    /**
     * 将PO转化为VO
     * @param rolePOS
     * @return
     */
    private List<RoleVO> rolePO2RoleVo(List<RolePO> rolePOS){
        List<RoleVO> roleVOList = new ArrayList<>();
        for (RolePO rolePO:rolePOS) {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(rolePO.getId());
            roleVO.setRoleName(rolePO.getRoleName());
            roleVOList.add(roleVO);
        }
        return roleVOList;
    }


}
