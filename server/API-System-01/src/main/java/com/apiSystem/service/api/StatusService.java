package com.apiSystem.service.api;

import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.vo.StatusVO;

import java.util.List;

public interface StatusService {

    /**
     * 批量插入数据
     */
    boolean addStatusBatch(List<StatusVO> statusVOS, Integer groupId, String token);

    /**
     * 批量删除
     */
    boolean deleteStatusBatch(List<Integer> ids, String token);

    /**
     * 根据分组id查询
     */
    List<Status> queryByGroupId(Integer groupId);

    /**
     * 查询一个项目中的所有状态码
     */
    List<Status> queryAllInProject(Integer projectId);

    List<Status> queryByIds(List<Integer> ids);

    /**
     * 根据关键字对状态码和 状态码描述 进行模糊查询
     */
    List<Status> searchStatus(String keyword);

    boolean updateStatus(Status status, String token);

    /**
     * 移动指定状态码到指定分组
     */
    boolean removeStatus(List<Integer> ids, Integer groupId);
}
