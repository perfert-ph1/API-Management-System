package com.apiSystem.controller.api;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.vo.ApiToEdit;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.service.api.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class ApiController {

    @Autowired
    private ApiService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/addApi")
    public ResultEntity addApi(@Valid @RequestBody ApiVo apiVo, @RequestHeader String token){
        service.addApi(apiVo, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/deleteApi")
    public ResultEntity deleteApi(@RequestParam("apiId") Integer id, @RequestHeader String token){
        if(id==null || id<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        service.deleteApiById(id, token);

        return ResultEntity.successWithoutData();
    }

    @PostMapping("/deleteApiBatch")
    public ResultEntity deleteApiBatch(@RequestParam("ids") String strids, @RequestHeader String token){
        List<Integer> ids;
        try {
            ids = objectMapper.readValue(strids, new TypeReference<List<Integer>>(){});
        } catch (JsonProcessingException e) {
            return ResultEntity.failed(null, "参数错误");
        }


        service.deleteApiBatch(ids, token);
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/queryAllInPrj")
    public ResultEntity queryAllInPrj(Integer projectId){
        if(projectId==null || projectId<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.queryAllInPrj(projectId));
    }

    @GetMapping("/queryAllInGrp")
    public ResultEntity queryAllInGrp(Integer groupId){
        if(groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.queryAllInGrp(groupId));
    }

    @GetMapping("/getWholeApi")
    public ResultEntity getWholeApi(Integer apiId){
        if(apiId==null || apiId<=0) {
            return ResultEntity.failed(null, "参数错误");
        }

        return ResultEntity.successWithData(service.getWholeApi(apiId));
    }

    @GetMapping("/searchInPrj")
    public ResultEntity searchInPrj(String keyword, Integer projectId){
        if(keyword==null || projectId==null || projectId<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        else {
            return ResultEntity.successWithData(service.searchInPrj(keyword, projectId));
        }
    }

    @GetMapping("/searchInGrp")
    public ResultEntity searchInGrp(String keyword, Integer groupId){
        if(keyword==null || groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.searchInGrp(keyword, groupId));
    }

    @PostMapping("/moveApiBatch")
    public ResultEntity moveApiBatch(@RequestParam("apiIds") String apiIdsStr, Integer groupId,@RequestHeader String token){
        List<Integer> ids;

        if(apiIdsStr==null || apiIdsStr.trim().equals("") || groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        try {
            ids = objectMapper.readValue(apiIdsStr, new TypeReference<List<Integer>>(){});
        } catch (JsonProcessingException e) {
            return ResultEntity.failed(null, "参数错误");
        }

        service.moveApiBatch(ids, groupId, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/markApi")
    public ResultEntity markApiById(Integer apiId, Integer isMark, @RequestHeader String token) {
        if(apiId==null || apiId<=0 || isMark==null){
            return ResultEntity.failed(null, "参数错误");
        }

        service.markApiById(apiId, isMark, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/markApiBatch")
    public ResultEntity markApiBatch(@RequestParam("apiIds") String idsStr, Integer isMark,@RequestHeader String token) {
        List<Integer> apiIds;
        if(idsStr==null || isMark==null){
            return ResultEntity.failed(null, "参数错误");
        }

        try {
            apiIds = objectMapper.readValue(idsStr, new TypeReference<List<Integer>>() {});
        } catch (JsonProcessingException e) {
            return ResultEntity.failed(null, "参数错误");
        }

        service.markApiBatch(apiIds, isMark, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/editApi")
    public ResultEntity editApi(@Valid @RequestBody ApiToEdit api, @RequestHeader String token){
        if(api.getId()<=0 || api.getUpdaterId()<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        service.updateApi(api, token);
        return ResultEntity.successWithoutData();
    }
}
