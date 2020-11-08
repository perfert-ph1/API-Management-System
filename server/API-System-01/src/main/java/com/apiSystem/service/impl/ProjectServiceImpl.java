package com.apiSystem.service.impl;

import com.apiSystem.entity.bo.ProjectBO;
import com.apiSystem.entity.po.ProjectPO;
import com.apiSystem.entity.po.ProjectPOExample;
import com.apiSystem.entity.po.UserInnerProjectPO;
import com.apiSystem.mapper.ProjectPOMapper;
import com.apiSystem.mapper.UserInnerProjectPOMapper;
import com.apiSystem.service.api.ProjectService;
import com.apiSystem.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private UserInnerProjectPOMapper userInnerProjectPOMapper;

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
    }
}
