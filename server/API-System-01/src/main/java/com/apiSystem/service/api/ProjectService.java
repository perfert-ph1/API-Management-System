package com.apiSystem.service.api;

import com.apiSystem.entity.bo.ProjectBO;
import com.apiSystem.entity.vo.ProjectSituationVO;
import com.apiSystem.entity.vo.ProjectVO;

import java.util.List;


public interface ProjectService {

    /**
     * 添加项目
     * @param projectBO
     */
    void addProject(ProjectBO projectBO);

    /**
     * 根据uid获取项目列表
     * @param uid
     * @return
     */
    List<ProjectVO> getAllProject(Integer uid);

    /**
     * 根据pid删除项目
     * @param pid
     */
    Integer deleteProject(Integer uid,Integer pid);

    /**
     * 根据pid修改项目
     * @param projectBO
     */
    Integer editProject(ProjectBO projectBO);

    /**
     * 根据pid获取单个项目
     * @param pid
     * @return
     */
    ProjectVO getProject(Integer pid);

    /**
     * 批量删除项目
     * @param pids
     * @return
     */
    Integer batchDeleteProject(Integer uid,List<Integer> pids);

    /**
     * 获取项目概况
     * @return
     */
    ProjectSituationVO getProjectSituation(Integer pid);

    void updateTime(Integer pid);
}
