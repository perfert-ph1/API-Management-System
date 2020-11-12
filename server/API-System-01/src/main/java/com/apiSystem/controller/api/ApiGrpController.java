package com.apiSystem.controller.api;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.service.api.ApiGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiGrps")
public class ApiGrpController {

    @Autowired
    private ApiGrpService service;

    @PostMapping("/apiVo")
    public String apiVo(@RequestBody ApiVo apiVo){
        System.out.println(apiVo);
        return "OK";
    }

    @PostMapping("/addApiGrp")
    public ResultEntity addApiGrp(ApiGrp grp, @RequestHeader String token){
        if(service.addNewApiGrp(grp, token)){
            return ResultEntity.successWithoutData();
        }

        return ResultEntity.failed(null, "添加分组出错");
    }

    @PostMapping("/deleteApiGrp")
    public ResultEntity deleteApiGrp(@RequestParam Integer grpId,@RequestHeader String token){
        if(service.deleteApiGrp(grpId, token)){
            return ResultEntity.successWithoutData();
        }

        return ResultEntity.failed(null, "删除分组出错");
    }

    @PostMapping("/updateApiGrp")
    public ResultEntity updateApiGrp(ApiGrp grp,@RequestHeader String token){
        if(service.updateApiGrp(grp, token)){
            return ResultEntity.successWithoutData();
        }

        return ResultEntity.failed(null, "更新分组出错");
    }

    @GetMapping("/queryAllInProject")
    public ResultEntity queryAllInProject(@RequestParam Integer pid){
        return ResultEntity.successWithData(service.queryAllInProject(pid));
    }

    @GetMapping("/search")
    public ResultEntity searchInProject(@RequestParam String keyword, @RequestParam Integer pid){
        return ResultEntity.successWithData(service.searchInProject(keyword, pid));
    }
}
