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
        if(service.addNewStatusGrp(grp, token)) {
            return ResultEntity.successWithData(grp);
        }
        else {
            return ResultEntity.failed(null, "添加分组错误");
        }
    }

    @PostMapping("/deleteGroup")
    public ResultEntity deleteById(@RequestParam Integer groupId, @RequestHeader String token){
        if(groupId==null){
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
    public ResultEntity queryAllByPid(@RequestParam(value = "pid") Integer pid){
        if(pid==null){
            return ResultEntity.failed(null, "获取分组出错");
        }

        return ResultEntity.successWithData(service.queryByPid(pid));
    }

    @GetMapping("/search")
    public ResultEntity searchByGName(@RequestParam Integer pid, @RequestParam String keyword){
        if(pid==null || keyword==null){
            return ResultEntity.failed(null, "获取分组出错");
        }

        return ResultEntity.successWithData(service.queryByGNameAndPid(keyword, pid));
    }

    @GetMapping("/getAll")
    public ResultEntity queryAll(){
        return ResultEntity.successWithData(service.queryAll());
    }

    @PostMapping("/updateGroup")
    public ResultEntity updateStatusGrp(StatusGrpVo grpVo, @RequestHeader String token){
        if(service.updateStatusGrp(grpVo, token)){
            return ResultEntity.successWithoutData();
        }
        else {
            return ResultEntity.failed(null, "修改出现错误");
        }
    }
}
