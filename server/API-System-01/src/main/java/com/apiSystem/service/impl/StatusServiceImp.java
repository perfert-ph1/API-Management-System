package com.apiSystem.service.impl;

import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.po.StatusExample;
import com.apiSystem.entity.po.StatusGrpExample;
import com.apiSystem.entity.vo.StatusVO;
import com.apiSystem.mapper.StatusMapper;
import com.apiSystem.service.api.StatusGrpService;
import com.apiSystem.service.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImp implements StatusService {

    @Autowired
    private StatusMapper mapper;

    @Autowired
    private StatusGrpService grpService;

    @Override
    public boolean addStatusBatch(List<StatusVO> statusVOS, Integer groupId, String token) {
        List<Status> statuses = new ArrayList<>();
        for (StatusVO vo : statusVOS) {
            Status status = new Status();
            status.setStatusCode(vo.getStatusCode());
            status.setDescription(vo.getDescription());
            status.setGid(groupId);
            statuses.add(status);
        }

        return mapper.insertBatch(statuses) > 0;
    }

    @Override
    public boolean deleteStatusBatch(List<Integer> ids, String token) {
        return mapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<Status> queryByGroupId(Integer groupId) {
        StatusExample example = new StatusExample();
        example.or().andGidEqualTo(groupId);

        return mapper.selectByExample(example);
    }

    @Override
    public List<Status> queryAllInProject(Integer projectId) {
        List<Integer> grpIds = grpService.queryIdsByPid(projectId);

        StatusExample example = new StatusExample();
        example.or().andGidIn(grpIds);

        return mapper.selectByExample(example);
    }

    @Override
    public List<Status> queryByIds(List<Integer> ids) {
        StatusExample example = new StatusExample();
        example.or().andIdIn(ids);

        return mapper.selectByExample(example);
    }

    @Override
    public Status queryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Status> searchStatus(String keyword, Integer pid, Integer groupId) {
        StatusExample example = new StatusExample();
        String keyStr = "%" + keyword + "%";
        //若没有指定分组id，则是对项目下所有状态码搜索
        if(groupId==null) {
            List<Integer> ids = grpService.queryIdsByPid(pid);
            example.or().andDescriptionLike(keyStr).andGidIn(ids);
            example.or().andStatusCodeLike(keyStr).andGidIn(ids);
        }
        //否则只是在指定分组下搜索
        else{
            example.or().andDescriptionLike(keyStr).andGidEqualTo(groupId);
            example.or().andStatusCodeLike(keyStr).andGidEqualTo(groupId);
        }

        return mapper.selectByExample(example);
    }

    @Override
    public boolean updateStatus(Status status, String token) {
        return mapper.updateByPrimaryKeySelective(status) > 0;
    }

    @Override
    public boolean updateStatusBatch(List<Integer> ids, Integer groupId, String token) {
        Status status = new Status();
        status.setGid(groupId);

        for (Integer id : ids) {
            status.setId(id);
            mapper.updateByPrimaryKeySelective(status);
        }
        return true;
    }
}
