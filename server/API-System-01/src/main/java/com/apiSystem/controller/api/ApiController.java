package com.apiSystem.controller.api;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.vo.ApiSimple;
import com.apiSystem.entity.vo.ApiToEdit;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.service.api.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
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
        if(apiId==null || apiId<=0 || isMark==null || isMark>1){
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

    // //指定第page页，每页5条数据
    //     PageHelper.startPage(page, 5);
    // //查询所有employee
    // List<Employee> employees = service.getAllEmps();
    // //使用PageInfo对象封装结果集，其中传入5表示在导航条显示5个导航页；
    // //PageInfo对象是对查询出的结果进行分页处理，而不是在数据库查询中使用分页查询
    // PageInfo<Employee> pageInfo = new PageInfo<>(employees, 3);
    //
    // //PageInfo对象提供了很多获取分页信息的方法：
    // // pageInfo.getSize();//当前页的数量
    // // pageInfo.getPageSize();//每页的数量
    // // pageInfo.getTotal();//所有数量
    // // pageInfo.getList();//分页后的结果集
    //
    //     return Msg.success().add("pageInfo", pageInfo);

    @GetMapping("/getApiStatistics")
    public ResultEntity getApiStatistics(Integer projectId){
        if(projectId==null || projectId<=0 ){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.getApiStatistics(projectId));
    }

    @GetMapping("/getApiByStatus")
    public ResultEntity getApiByStatus(Integer projectId, Integer status, @RequestParam(defaultValue = "1") Integer page){
        if(projectId==null || status==null || projectId<=0 || status<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        PageHelper.startPage(page, 5);
        PageInfo<ApiSimple> pageInfo = new PageInfo<>(service.getApiByStatusInPrj(status, projectId), 5);
        pageInfo.setPageSize(5);
        return ResultEntity.successWithData(pageInfo);
    }
}
