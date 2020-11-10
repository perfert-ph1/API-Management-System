package com.apiSystem.service.impl;

import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.po.StatusExample;
import com.apiSystem.entity.vo.StatusVO;
import com.apiSystem.mapper.StatusMapper;
import com.apiSystem.service.api.StatusGrpService;
import com.apiSystem.service.api.StatusService;
import com.apiSystem.util.ProjectAlterLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// @ProjectAlterLog
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
    public List<Status> searchStatus(String keyword) {
        StatusExample example = new StatusExample();
        String keyStr = "%" + keyword + "%";

        example.or().andDescriptionLike(keyStr);
        example.or().andStatusCodeLike(keyStr);

        return mapper.selectByExample(example);
    }

    @Override
    public boolean updateStatus(Status status, String token) {
        return mapper.updateByPrimaryKeySelective(status) > 0;
    }

    @Override
    public boolean removeStatus(List<Integer> ids, Integer groupId) {
        return mapper.updateGidByIds(ids, groupId)>0;
    }
}
