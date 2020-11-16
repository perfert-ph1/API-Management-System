package com.apiSystem.controller.role;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.vo.RoleVO;
import com.apiSystem.entity.vo.UserAndRoleVO;
import com.apiSystem.entity.vo.UserVo;
import com.apiSystem.service.api.RoleService;
import com.apiSystem.service.api.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 获取所有角色
     * @param pid
     * @return
     */
    @GetMapping("/getAllRole")
    public ResultEntity<List<RoleVO>> getAllRole(@RequestParam(name = "pid",required = true) Integer pid){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        List<RoleVO> roleVOS = roleService.getAllRole(pid);
        return ResultEntity.successWithData(roleVOS);
    }

    /**
     * 添加角色
     * @param pid
     * @param roleName
     * @return
     */
    @PostMapping("/addRole")
    public ResultEntity<String> addRole(@RequestParam(name = "pid",required = true) Integer pid,
                                        @RequestParam(name = "roleName",required = true) String roleName){
        if(pid == null || roleName == null){
            ResultEntity.failed(null,"服务器出错");
        }
        if(StringUtils.isBlank(roleName.trim())){
            ResultEntity.failed(null,"角色名不能为空");
        }
        Integer status = roleService.addRole(pid, roleName);
        if(status == -1){
            ResultEntity.failed(null,"添加失败");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 获取用户和对应的角色
     * @param pid
     * @param rid
     * @return
     */
    @GetMapping("/getMembers")
    public ResultEntity<List<UserAndRoleVO>> getMembers(@RequestParam(name = "pid",required = true) Integer pid,
                                                        @RequestParam(name = "rid",required = false,defaultValue = "0") Integer rid){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        List<UserAndRoleVO> userAndRoleVOS;
        if(rid == 0){
            userAndRoleVOS = roleService.getMembers(pid);
        }else {
            userAndRoleVOS = roleService.getMembers(pid,rid);
        }
        return ResultEntity.successWithData(userAndRoleVOS);
    }

    /**
     * 获取项目非管理者用户信息以及其对应的角色
     * @param pid
     * @return
     */
    @GetMapping("/getMembersNotManager")
    public ResultEntity<List<UserAndRoleVO>> getMembers(@RequestParam(name = "pid",required = true) Integer pid){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        List<UserAndRoleVO> userAndRoleVOS;
        userAndRoleVOS = roleService.getMemberNotManager(pid);
        return ResultEntity.successWithData(userAndRoleVOS);
    }


    /**
     * 根据rid、uid、pid添加用户角色
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    @GetMapping("/addMember")
    public ResultEntity<String> addMember(@RequestParam(name = "rid")Integer rid,
                                               @RequestParam(name = "uid")Integer uid,
                                               @RequestParam(name = "pid")Integer pid){
        Integer status = roleService.addMember(rid,uid,pid);
        if(status <= 1){
            if(status == -1){
                return ResultEntity.failed(null,"不可重复添加");
            }
            return ResultEntity.failed(null,"服务器出错");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 根据rid、uid、pid修改用户角色
     * @param rid
     * @param uid
     * @param pid
     * @return
     */
    @GetMapping("/editMember")
    public ResultEntity<String> editRole(@RequestParam(name = "rid",required = true)Integer rid,
                                         @RequestParam(name = "uid",required = true)Integer uid,
                                         @RequestParam(name = "pid",required = true)Integer pid) {
        Integer status = roleService.editMemberRole(rid,uid,pid);
        if(status <= 0){
            return ResultEntity.failed(null,"修改失败");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 根据pid和uid删除成员
     * 待测试
     * @param request
     * @param pid
     * @param uid
     * @return
     */
    @GetMapping("/deleteMember")
    public ResultEntity<String> deleteMember(HttpServletRequest request,
                                                  @RequestParam(name = "pid",required = true)Integer pid,
                                                  @RequestParam(name = "uid",required = true)Integer uid){
        if(pid == null || uid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        String token = request.getHeader("token");
        Boolean flag = roleService.checkDeletePower(token, uid);
        if(!flag){
            return ResultEntity.failed(null,"权限不足");
        }
        Integer status = roleService.deleteMember(uid,pid);
        if(status <= 1){
            ResultEntity.failed(null,"删除失败");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 退出项目
     * 待测试！！
     * @param pid
     * @param uid
     * @return
     */
    @GetMapping("/quitProject")
    public ResultEntity<String> quitProject(@RequestParam(name = "pid",required = true)Integer pid,
                                            @RequestParam(name = "uid",required = true)Integer uid){
        if(pid == null || uid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        Integer status = roleService.deleteMember(uid,pid);
        if(status <= 1){
            ResultEntity.failed(null,"退出失败");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 批量删除项目成员
     * 待测试
     * @param uidsStr
     * @param pid
     * @return
     */
    @GetMapping("/batchDeleteMember")
    public ResultEntity<String> batchDeleteMember(@RequestParam(name = "uidsStr",required = true)String uidsStr,
                                                  @RequestParam(name = "pid",required = true)Integer pid){
        if(pid == null || uidsStr == null || uidsStr.trim().isEmpty()){
            ResultEntity.failed(null,"服务器出错");
        }
        if(!uidsStr.contains(",")){
            Integer status = roleService.deleteMember(Integer.parseInt(uidsStr), pid);
            if(status <= 0){
                return ResultEntity.failed(null,"删除失败");
            }
            return ResultEntity.successWithoutData();
        }
        String[] split = uidsStr.trim().split(",");
        List<Integer> uids = new ArrayList<>();
        for (String uid:split) {
            uids.add(Integer.parseInt(uid));
        }
        Integer status = roleService.batchDeleteMember(uids, pid);
        if(status<=0){
            return ResultEntity.failed(null,"删除失败");
        }
        return ResultEntity.successWithoutData();
    }

    @GetMapping("QueryUserAndRole")
    public ResultEntity<List<UserAndRoleVO>> QueryUserAndRole(@RequestParam(name = "pid",required = true) Integer pid,
                                                              @RequestParam(name = "username",required = false,defaultValue = "") String username){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        if(username.equals("")){
            return ResultEntity.successWithData(roleService.getMembers(pid));
        }
        List<UserAndRoleVO> userAndRoleVOS;
        userAndRoleVOS = roleService.getMemberByUsername(pid,username);
        return ResultEntity.successWithData(userAndRoleVOS);
    }
}
