package com.apiSystem.service.impl;

import com.apiSystem.entity.bo.ProjectBO;
import com.apiSystem.entity.po.*;
import com.apiSystem.entity.po.api.ApiExample;
import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.po.api.ApiGrpExample;
import com.apiSystem.entity.vo.ProjectSituationVO;
import com.apiSystem.entity.vo.ProjectVO;
import com.apiSystem.mapper.*;
import com.apiSystem.service.api.ProjectService;
import com.apiSystem.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private UserInnerProjectPOMapper userInnerProjectPOMapper;

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private StatusGrpMapper statusGrpMapper;

    @Autowired
    private ApiMapper apiMapper;

    @Autowired
    private ApiGrpMapper apiGrpMapper;

    @Autowired
    private RolePOMapper rolePOMapper;

    @Autowired
    private UserInnerRolePOMapper userInnerRolePOMapper;



    /**
     * 添加项目
     * @param projectBO
     */
    @Override
    public void addProject(ProjectBO projectBO) {
        if(projectBO.getVersion() == null || projectBO.getVersion().trim().equals("")){
            projectBO.setVersion("1.0");
        }
        if(projectBO.getRemarks() == null){
            projectBO.setRemarks("");
        }
        //创建ProjectPO
        ProjectPO projectPO = new ProjectPO();
        projectPO.setCreatorId(projectBO.getCreatorId());
        projectPO.setProjectName(projectBO.getProjectName());
        projectPO.setProjType(projectBO.getProjType());
        projectPO.setRemarks(projectBO.getRemarks());
        projectPO.setUpdateTime(new Date());
        projectPO.setVersion(projectBO.getVersion());
        //插入数据库project表
        projectPOMapper.insert(projectPO);
        //插入project_user中间表
        ProjectPOExample projectPOExample = new ProjectPOExample();
        projectPOExample.createCriteria().andCreatorIdEqualTo(projectPO.getCreatorId()).andProjectNameEqualTo(projectPO.getProjectName());
        List<ProjectPO> projectPOS = projectPOMapper.selectByExample(projectPOExample);
        ProjectPO project = projectPOS.get(0);
        Integer pid = project.getId();
        UserInnerProjectPO userInnerProjectPO = new UserInnerProjectPO();
        userInnerProjectPO.setPid(pid);
        userInnerProjectPO.setUid(projectPO.getCreatorId());
        userInnerProjectPOMapper.insert(userInnerProjectPO);
        //分配角色
        //插入Role角色表
        RolePO rolePO = new RolePO();
        rolePO.setRoleName("最高权限管理员");
        rolePO.setPid(pid);
        rolePOMapper.insert(rolePO);
        //插入inner_role_user
        RolePOExample rolePOExample = new RolePOExample();
        rolePOExample.createCriteria().andRoleNameEqualTo("最高权限管理员").andPidEqualTo(pid);
        List<RolePO> rolePOList = rolePOMapper.selectByExample(rolePOExample);
        if(rolePOList.size() > 0){
            RolePO rolePO1 = rolePOList.get(0);
            UserInnerRolePO userInnerRolePO = new UserInnerRolePO();
            userInnerRolePO.setRid(rolePO1.getId());
            userInnerRolePO.setUid(projectPO.getCreatorId());
            userInnerRolePO.setPid(pid);
            userInnerRolePOMapper.insert(userInnerRolePO);
        }

    }

    /**
     * 获取所有项目
     * @param uid
     * @return
     */
    @Override
    public List<ProjectVO> getAllProject(Integer uid) {
        UserInnerProjectPOExample userInnerProjectPOExample = new UserInnerProjectPOExample();
        userInnerProjectPOExample.createCriteria().andUidEqualTo(uid);
        List<UserInnerProjectPO> userInnerProjectPOS = userInnerProjectPOMapper.selectByExample(userInnerProjectPOExample);
        if(userInnerProjectPOS == null || userInnerProjectPOS.size() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (UserInnerProjectPO userInnerProjectPO:userInnerProjectPOS) {
            Integer id = userInnerProjectPO.getPid();
            list.add(id);
        }
        ProjectPOExample projectPOExample = new ProjectPOExample();
        projectPOExample.createCriteria().andIdIn(list);
        List<ProjectPO> projectPOS = projectPOMapper.selectByExample(projectPOExample);
        if(projectPOS == null || projectPOS.size() == 0){
            return null;
        }
        List<ProjectVO> projectVOS = new ArrayList<>();
        for (ProjectPO projectPO:projectPOS) {
            ProjectVO projectVO = new ProjectVO();
            projectVO.setId(projectPO.getId());
            projectVO.setProjectName(projectPO.getProjectName());
            projectVO.setProjType(projectPO.getProjType());
            projectVO.setRemarks(projectPO.getRemarks());
            projectVO.setUpdateTime(projectPO.getUpdateTime().getTime() );
            projectVO.setVersion(projectPO.getVersion());
            projectVOS.add(projectVO);
        }
        return projectVOS;
    }

    /**
     * 根据pid和uid删除项目
     * @param uid
     * @param pid
     * @return
     */
    @Override
    public Integer deleteProject(Integer uid,Integer pid) {
        UserInnerProjectPOExample userInnerProjectPOExample = new UserInnerProjectPOExample();
        userInnerProjectPOExample.createCriteria().andPidEqualTo(pid).andUidEqualTo(uid);
        Integer status = userInnerProjectPOMapper.deleteByExample(userInnerProjectPOExample);
        return status;
    }

    /**
     * 根据项目修改项目
     * @param projectBO
     * @return
     */
    @Override
    public Integer editProject(ProjectBO projectBO) {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setId(projectBO.getId());
        projectPO.setCreatorId(projectBO.getCreatorId());
        projectPO.setProjectName(projectBO.getProjectName());
        projectPO.setProjType(projectBO.getProjType());
        projectPO.setRemarks(projectBO.getRemarks());
        projectPO.setUpdateTime(new Date());
        projectPO.setVersion(projectBO.getVersion());
        Integer status = projectPOMapper.updateByPrimaryKey(projectPO);
        return status;
    }

    /**
     * 根据pid获取单个项目信息
     * @param pid
     * @return
     */
    @Override
    public ProjectVO getProject(Integer pid) {
        ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(projectPO.getId());
        projectVO.setProjectName(projectPO.getProjectName());
        projectVO.setProjType(projectPO.getProjType());
        projectVO.setRemarks(projectPO.getRemarks());
        projectVO.setUpdateTime(projectPO.getUpdateTime().getTime());
        projectVO.setVersion(projectPO.getVersion());
        return projectVO;
    }

    /**
     * 根据pids批量删除项目
     * @param uid
     * @param pids
     * @return
     */
    @Override
    public Integer batchDeleteProject(Integer uid,List<Integer> pids){
        UserInnerProjectPOExample userInnerProjectPOExample = new UserInnerProjectPOExample();
        userInnerProjectPOExample.createCriteria().andUidEqualTo(uid).andPidIn(pids);
        Integer status = userInnerProjectPOMapper.deleteByExample(userInnerProjectPOExample);
        return status;
    }

    /**
     * 根据pid获取项目概况
     * 状态码数量和API数量没有完全写完
     * @param pid
     * @return
     */
    @Override
    public ProjectSituationVO getProjectSituation(Integer pid) {
        ProjectPO projectPO = projectPOMapper.selectByPrimaryKey(pid);
        ProjectSituationVO projectSituationVO = new ProjectSituationVO();
        projectSituationVO.setId(projectPO.getId());
        projectSituationVO.setProjectName(projectPO.getProjectName());
        projectSituationVO.setProjType(projectPO.getProjType());
        projectSituationVO.setRemarks(projectPO.getRemarks());
        projectSituationVO.setUpdateTime(projectPO.getUpdateTime().getTime());
        projectSituationVO.setVersion(projectPO.getVersion());

        //设置参与项目的人数
        UserInnerProjectPOExample example = new UserInnerProjectPOExample();
        example.createCriteria().andPidEqualTo(pid);
        int count = userInnerProjectPOMapper.countByExample(example);
        projectSituationVO.setMemberNum(count);

        //设置状态码数量
        StatusGrpExample statusGrpExample = new StatusGrpExample();
        statusGrpExample.createCriteria().andPidEqualTo(pid);
        List<StatusGrp> statusGrps = statusGrpMapper.selectByExample(statusGrpExample);
        List<Integer> gids = new ArrayList<>();
        for (StatusGrp statusGrp: statusGrps) {
            gids.add(statusGrp.getId());
        }
        StatusExample statusExample = new StatusExample();
        statusExample.createCriteria().andGidEqualTo(pid);
        int statusNum = statusMapper.countByExample(statusExample);
        projectSituationVO.setStatusCodeNum(statusNum);

        //设置API数量
        ApiGrpExample apiGrpExample = new ApiGrpExample();
        apiGrpExample.createCriteria().andPidEqualTo(pid);
        List<ApiGrp> apiGrps = apiGrpMapper.selectByExample(apiGrpExample);
        List<Integer> apiGids = new ArrayList<>();
        for (ApiGrp apiGrp: apiGrps) {
            apiGids.add(apiGrp.getId());
        }
        ApiExample apiExample = new ApiExample();
        apiExample.createCriteria().andGidEqualTo(pid);
        int apiNums = apiMapper.countByExample(apiExample);
        projectSituationVO.setApiNum(apiNums);
        return projectSituationVO;
    }

    @Override
    public void updateTime(Integer pid) {
        ProjectPO project = new ProjectPO();
        project.setId(pid);
        project.setUpdateTime(new Date());
        projectPOMapper.updateByPrimaryKeySelective(project);
    }
}
