package com.apiSystem.service.api;

import com.apiSystem.entity.po.StatusGrp;
import com.apiSystem.entity.vo.StatusGrpVo;

import java.util.List;

public interface StatusGrpService {

    boolean addNewStatusGrp(StatusGrp grp, String token);

    boolean deleteStatusGrpById(Integer id, String token);

    List<StatusGrp> queryAll();

    /**
     * 根据分组名模糊查询指定项目（pid）的状态码分组
     */
    List<StatusGrp> queryByGNameAndPid(String grpName, Integer pid);

    List<StatusGrp> queryByPid(Integer pid);

    /**
     * 根据id值查询项目id
     */
    int queryProjectIdById(Integer id);

    StatusGrp queryById(Integer id);

    /**
     * 查询同个项目下的所有分组的id
     * @param pid
     * @return
     */
    List<Integer> queryIdsByPid(Integer pid);

    boolean updateStatusGrp(StatusGrpVo grp, String token);
}
