package com.apiSystem.controller.status;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.vo.StatusVO;
import com.apiSystem.service.api.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 批量插入
     * 请求体数据示例：
     */
    @PostMapping("/addStatus")
    public ResultEntity addBatch(String statuses,Integer gid, @RequestHeader String token){
        List<StatusVO> statusVOS;

        if(gid==null || gid<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        try {
            statusVOS = objectMapper.readValue(statuses, new TypeReference<List<StatusVO>>() {});
        } catch (JsonProcessingException e) {
            return ResultEntity.failed(null, "参数错误");
        }

        service.addStatusBatch(statusVOS, gid, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/deleteStatus")
    public ResultEntity deleteBatch(@RequestParam("ids") String statusIds, @RequestHeader String token){
        List<Integer> ids;

        try {
            ids = objectMapper.readValue(statusIds, new TypeReference<List<Integer>>() {});
        } catch (JsonProcessingException e) {
            return ResultEntity.failed(null, "参数错误");
        }

        service.deleteStatusBatch(ids, token);
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/queryByGroupId")
    public ResultEntity queryByGid(Integer groupId){
        if(groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        List<Status> statuses = service.queryByGroupId(groupId);
        return ResultEntity.successWithData(statuses);
    }

    /**
     * 获取一个项目下所有的状态码
     */
    @GetMapping("/queryAllInPrj")
    public ResultEntity queryAllInPrj(Integer projectId){
        if(projectId==null || projectId<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        List<Status> result = service.queryAllInProject(projectId);
        return ResultEntity.successWithData(result);
    }

    @GetMapping("/searchStatus")
    public ResultEntity searchStatus(String keyword, Integer pid
            , @RequestParam(required = false) Integer groupId){
        if(pid==null || pid<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        return ResultEntity.successWithData(service.searchStatus(keyword, pid, groupId));
    }

    @PostMapping("/updateStatus")
    public ResultEntity updateStatus(Status status, @RequestHeader String token){
        if(status.getId()==null || status.getId()<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        service.updateStatus(status, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/moveStatus")
    public ResultEntity updateStatusBatch(@RequestParam("ids") String idsStr, Integer groupId
            , @RequestHeader String token){
        List<Integer> ids;

        if(groupId==null || groupId<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        try {
            ids = objectMapper.readValue(idsStr, new TypeReference<List<Integer>>() {});
        }catch (JsonProcessingException e){
            return ResultEntity.failed(null, "参数错误");
        }

        service.updateStatusBatch(ids, groupId, token);
        return ResultEntity.successWithoutData();
    }
}
