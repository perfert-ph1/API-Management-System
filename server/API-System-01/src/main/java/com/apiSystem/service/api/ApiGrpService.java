package com.apiSystem.service.api;

import com.apiSystem.entity.po.api.ApiGrp;

import java.util.List;

public interface ApiGrpService {

    /**
     * required: grpName pid
     */
    boolean addNewApiGrp(ApiGrp grp, String token);

    boolean deleteApiGrp(Integer grpId, String token);

    /**
     * required: id grpName pid
     */
    boolean updateApiGrp(ApiGrp grp, String token);

    List<ApiGrp> queryAllInProject(Integer pid);

    List<ApiGrp> searchInProject(String keyword, Integer pid);
}
