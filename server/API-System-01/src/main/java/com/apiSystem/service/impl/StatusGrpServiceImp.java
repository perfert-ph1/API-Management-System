package com.apiSystem.service.impl;

import com.apiSystem.entity.po.StatusGrp;
import com.apiSystem.entity.po.StatusGrpExample;
import com.apiSystem.entity.vo.StatusGrpVo;
import com.apiSystem.mapper.StatusGrpMapper;
import com.apiSystem.service.api.StatusGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusGrpServiceImp implements StatusGrpService {

    @Autowired
    private StatusGrpMapper mapper;

    @Override
    public boolean addNewStatusGrp(StatusGrp grp, String token) {
        return mapper.insert(grp) > 0;
    }

    @Override
    public boolean deleteStatusGrpById(Integer id, String token) {
        return mapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public List<StatusGrp> queryAll() {
        StatusGrpExample example = new StatusGrpExample();
        return mapper.selectByExample(example);
    }

    @Override
    public List<StatusGrp> queryByGNameAndPid(String grpName, Integer pid) {
        String key = "%" + grpName + "%";
        StatusGrpExample example = new StatusGrpExample();
        example.or().andGrpNameLike(key)
                .andPidEqualTo(pid);

        return mapper.selectByExample(example);
    }

    @Override
    public List<StatusGrp> queryByPid(Integer pid) {
        StatusGrpExample example = new StatusGrpExample();
        example.or().andPidEqualTo(pid);

        return mapper.selectByExample(example);
    }

    @Override
    public int queryProjectIdById(Integer id) {
        StatusGrp grp = mapper.selectByPrimaryKey(id);
        if(grp!=null){
            return grp.getPid();
        }

        return -1;
    }

    @Override
    public StatusGrp queryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> queryIdsByPid(Integer pid) {
        List<Integer> result = new ArrayList<>();
        StatusGrpExample example = new StatusGrpExample();
        example.or().andPidEqualTo(pid);
        List<StatusGrp> grps = mapper.selectByExample(example);

        for (StatusGrp grp : grps) {
            result.add(grp.getId());
        }

        return result;
    }

    /**
     * 修改完成后，返回修改后的结果
     */
    @Override
    public boolean updateStatusGrp(StatusGrpVo grp, String token) {
        StatusGrp grpPo = new StatusGrp();
        grpPo.setId(grp.getId());
        grpPo.setGrpName(grp.getGrpName());

        return mapper.updateByPrimaryKeySelective(grpPo) > 0;
    }
}
