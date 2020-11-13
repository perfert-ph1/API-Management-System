package com.apiSystem.controller.status;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.StatusGrp;
import com.apiSystem.entity.vo.StatusGrpVo;
import com.apiSystem.service.api.StatusGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/statusGrps")
public class StatusGrpController {

    @Autowired
    private StatusGrpService service;

    /**
     * 前端发送的参数应包括：
     * pid 状态码分组对应的项目id
     * grpName 分组名
     */
    @PostMapping("/addGroup")
    public ResultEntity addStatusGrp(StatusGrp grp, @RequestHeader String token){
        if(grp.getGrpName()==null || grp.getGrpName().trim().equals("") || grp.getPid()==null || grp.getPid()<=0) {
            return ResultEntity.failed(null, "参数错误");
        }
        service.addNewStatusGrp(grp, token);
        return ResultEntity.successWithData(grp);
    }

    @PostMapping("/deleteGroup")
    public ResultEntity deleteById(Integer groupId, @RequestHeader String token){
        if(groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        if(service.deleteStatusGrpById(groupId, token)){
            return ResultEntity.successWithoutData();
        }
        else {
            return ResultEntity.failed(null, "删除错误");
        }
    }

    @GetMapping("/getAllByPid")
    public ResultEntity queryAllByPid(Integer pid){
        if(pid==null || pid<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        return ResultEntity.successWithData(service.queryByPid(pid));
    }

    @GetMapping("/search")
    public ResultEntity searchByGName(Integer pid, String keyword){
        if(pid==null || pid<=0){
            return ResultEntity.failed(null, "参数出错");
        }

        return ResultEntity.successWithData(service.queryByGNameAndPid(keyword, pid));
    }

    @GetMapping("/getAll")
    public ResultEntity queryAll(){
        return ResultEntity.successWithData(service.queryAll());
    }

    @PostMapping("/updateGroup")
    public ResultEntity updateStatusGrp(StatusGrpVo grpVo, @RequestHeader String token){
        if(grpVo.getId()==null || grpVo.getId()<=0 || grpVo.getGrpName()==null || grpVo.getGrpName().trim().equals("")){
            return ResultEntity.failed(null, "参数错误");
        }

        service.updateStatusGrp(grpVo, token);
        return ResultEntity.successWithoutData();
    }
}
