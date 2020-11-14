package com.apiSystem.controller.project;

import com.apiSystem.entity.bo.ProjectBO;
import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.po.api.Request;
import com.apiSystem.entity.vo.ProjectSituationVO;
import com.apiSystem.entity.vo.ProjectVO;
import com.apiSystem.entity.vo.UserVo;
import com.apiSystem.service.api.ProjectService;
import com.apiSystem.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
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

    @GetMapping("/getAllProject")
    public ResultEntity<List<ProjectVO>> getAllProject(HttpServletRequest request){
        UserVo user = userService.findUserByToken(request.getHeader("token"));
        Integer uid = user.getId();
        if(uid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        List<ProjectVO> allProject = projectService.getAllProject(uid);
        System.out.println(allProject);
        return ResultEntity.successWithData(allProject);
    }


    @PostMapping("/deleteProject")
    public ResultEntity<String> deleteProject(HttpServletRequest request,Integer pid){
        UserVo user = userService.findUserByToken(request.getHeader("token"));
        Integer uid = user.getId();
        if(pid == null || uid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        Integer status = projectService.deleteProject(uid, pid);
        if(status == 0){
            ResultEntity.failed(null,"删除失败");
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * 修改项目
     * @param projectBO
     * @return
     */
    @PostMapping("/editProject")
    public ResultEntity<String> editProject(ProjectBO projectBO){
        if(projectBO == null){
            ResultEntity.failed(null,"服务器出错");
        }
        if(projectBO.getProjectName() == null || projectBO.getProjectName().trim().equals("")){
            ResultEntity.failed(null,"项目名不能为空");
        }
        Integer status = projectService.editProject(projectBO);
        if(status == 0){
            return ResultEntity.failed(null,"修改失败");
        }
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/getProject")
    public ResultEntity<ProjectVO> getProject(@RequestParam("pid") Integer pid){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        ProjectVO projectVO = projectService.getProject(pid);
        return ResultEntity.successWithData(projectVO);
    }

    @GetMapping("/batchDeleteProject")
    public ResultEntity<String> batchDeleteProject(@RequestParam("deleteListStr") String deleteListStr,@RequestParam("uid") Integer uid){
        if(deleteListStr == null){
            ResultEntity.failed(null,"服务器出错");
        }
        String[] split = deleteListStr.split(",");
        List<Integer> deleteList = new ArrayList<>();
        for (String pid:split) {
            deleteList.add(Integer.parseInt(pid));
        }
        Integer status = projectService.batchDeleteProject(uid,deleteList);
        if(status == 0){
            return ResultEntity.failed(null,"删除失败");
        }
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/getProjectSituation")
    public ResultEntity<ProjectSituationVO> getProjectSituation(@RequestParam("pid") Integer pid){
        if(pid == null){
            ResultEntity.failed(null,"服务器出错");
        }
        ProjectSituationVO projectSituationVO = projectService.getProjectSituation(pid);
        return ResultEntity.successWithData(projectSituationVO);
    }


}
