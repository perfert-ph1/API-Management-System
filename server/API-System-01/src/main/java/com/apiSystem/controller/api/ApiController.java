package com.apiSystem.controller.api;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.service.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class ApiController {

    @Autowired
    private ApiService service;

    @PostMapping("/addApi")
    public ResultEntity addApi(@RequestBody ApiVo apiVo, @RequestHeader String token){
        service.addApi(apiVo, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/deleteApi")
    public ResultEntity deleteApi(@RequestParam("apiId") Integer id, @RequestHeader String token){
        service.deleteApiById(id, token);

        return ResultEntity.successWithoutData();
    }

    @PostMapping("/deleteApiBatch")
    public ResultEntity deleteApiBatch(@RequestParam("ids") String strids, String token){
        String[] strs = strids.split(",");
        List<Integer> ids = new ArrayList<>();

        try {
            for (String str : strs) {
                ids.add(Integer.parseInt(str));
            }
        }catch (NumberFormatException e){
            return ResultEntity.failed(null, "参数错误");
        }

        service.deleteApiBatch(ids, token);
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/queryAllInPrj")
    public ResultEntity queryAllInPrj(@RequestParam Integer projectId){
        return ResultEntity.successWithData(service.queryAllInPrj(projectId));
    }

    @GetMapping("/queryAllInGrp")
    public ResultEntity queryAllInGrp(@RequestParam Integer groupId){
        return ResultEntity.successWithData(service.queryAllInGrp(groupId));
    }

    @GetMapping("/getWholeApi")
    public ResultEntity getWholeApi(Integer apiId){
        if(apiId!=null) {
            return ResultEntity.successWithData(service.getWholeApi(apiId));
        }
        else {
            return ResultEntity.failed(null, "参数错误");
        }
    }

    @GetMapping("/searchInPrj")
    public ResultEntity searchInPrj(String keyword, Integer projectId){
        if(keyword==null || projectId==null){
            return ResultEntity.failed(null, "参数错误");
        }
        else {
            return ResultEntity.successWithData(service.searchInPrj(keyword, projectId));
        }
    }

    @GetMapping("/searchInGrp")
    public ResultEntity searchInGrp(String keyword, Integer groupId){
        if(keyword==null || groupId==null){
            return ResultEntity.failed(null, "参数错误");
        }
        else {
            return ResultEntity.successWithData(service.searchInGrp(keyword, groupId));
        }
    }
}
