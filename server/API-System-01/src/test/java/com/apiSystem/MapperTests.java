package com.apiSystem;

import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.vo.ProjectSituationVO;
import com.apiSystem.entity.vo.ProjectVO;
import com.apiSystem.mapper.LoginTokenPOMapper;
import com.apiSystem.service.api.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTests {

    @Autowired
    private LoginTokenPOMapper loginTokenPOMapper;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testInsertLoginToken(){
        LoginTokenPO loginTokenPO = new LoginTokenPO();
        loginTokenPO.setUserId(2);
        loginTokenPO.setToken("abc");
        loginTokenPO.setExpired(new Date(System.currentTimeMillis() + 1000*60*30));
        loginTokenPO.setStatus(0);
        System.out.println(loginTokenPO.getExpired());
        loginTokenPOMapper.insert(loginTokenPO);
    }

    @Test
    public void testTime(){
        System.out.println(new Date(System.currentTimeMillis() + 1000*60*10));
    }

    @Test
    public void testProjectService(){
        List<ProjectVO> allProject = projectService.getAllProject(3);
        for (ProjectVO p : allProject) {
            System.out.println(p);
        }
    }

    @Test
    public void testProjectServiceDelete(){
        Integer integer = projectService.deleteProject(3, 1);
        System.out.println(integer);
    }

    @Test
    public void testGetProjectSituation(){
        ProjectSituationVO projectSituation = projectService.getProjectSituation(4);
        System.out.println(projectSituation);
    }


}
