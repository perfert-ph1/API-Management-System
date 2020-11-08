package com.apiSystem.controller.project;

import com.apiSystem.entity.bo.ProjectBO;
import com.apiSystem.entity.bo.UserBo;
import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.service.api.ProjectService;
import com.apiSystem.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @PostMapping("/project/addProject")
    public ResultEntity<String> addProject(ProjectBO projectBO){
        if(projectBO == null){
            ResultEntity.failed(null,"服务器出错");
        }
        if(projectBO.getProjectName() == null || projectBO.getProjectName().trim().equals("")){
            ResultEntity.failed(null,"项目名不能为空");
        }
        projectService.addProject(projectBO);
        return ResultEntity.successWithoutData();
    }



}
