package com.apiSystem.service.impl;

import com.apiSystem.entity.po.ProjectLog;
import com.apiSystem.mapper.ProjectLogMapper;
import com.apiSystem.service.api.ProjectLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLogServiceImp implements ProjectLogService {

    @Autowired
    private ProjectLogMapper mapper;

    @Override
    public boolean insert(ProjectLog log) {
        return mapper.insert(log) > 0;
    }
}
