package com.apiSystem.service.impl;

import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.po.api.ApiGrpExample;
import com.apiSystem.mapper.ApiGrpMapper;
import com.apiSystem.service.api.ApiGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiGrpServiceImp implements ApiGrpService {

    @Autowired
    private ApiGrpMapper mapper;

    @Override
    public boolean addNewApiGrp(ApiGrp grp, String token) {
        return mapper.insert(grp)>0;
    }

    @Override
    public boolean deleteApiGrp(Integer grpId, String token) {
        return mapper.deleteByPrimaryKey(grpId)>0;
    }

    @Override
    public boolean updateApiGrp(ApiGrp grp, String token) {

        return mapper.updateByPrimaryKey(grp)>0;

    }

    @Override
    public List<ApiGrp> queryAllInProject(Integer pid) {
        ApiGrpExample example = new ApiGrpExample();
        example.or().andPidEqualTo(pid);

        return mapper.selectByExample(example);
    }

    @Override
    public List<ApiGrp> searchInProject(String keyword, Integer pid) {
        ApiGrpExample example = new ApiGrpExample();
        keyword = "%" + keyword + "%";
        example.or().andPidEqualTo(pid).andGrpNameLike(keyword);

        return mapper.selectByExample(example);
    }
}
