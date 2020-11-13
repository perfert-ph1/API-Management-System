package com.apiSystem.controller.api;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.service.api.ApiGrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apiGrps")
public class ApiGrpController {

    @Autowired
    private ApiGrpService service;


    @PostMapping("/addApiGrp")
    public ResultEntity addApiGrp(@Valid ApiGrp grp, @RequestHeader String token){
        if(service.addNewApiGrp(grp, token)){
            return ResultEntity.successWithoutData();
        }

        return ResultEntity.failed(null, "添加分组出错");
    }

    @PostMapping("/deleteApiGrp")
    public ResultEntity deleteApiGrp(Integer grpId,@RequestHeader String token){
        if(grpId==null || grpId<=0){
            return ResultEntity.failed(null, "参数错误");
        }

        service.deleteApiGrp(grpId, token);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("/updateApiGrp")
    public ResultEntity updateApiGrp(ApiGrp grp,@RequestHeader String token){
        if(grp.getId()==null || grp.getId()<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        service.updateApiGrp(grp, token);
        return ResultEntity.successWithoutData();

    }

    @GetMapping("/queryAllInProject")
    public ResultEntity queryAllInProject(Integer pid){
        if(pid==null || pid<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.queryAllInProject(pid));
    }

    @GetMapping("/search")
    public ResultEntity searchInProject(String keyword, Integer pid){
        if(pid==null || pid<=0){
            return ResultEntity.failed(null, "参数错误");
        }
        return ResultEntity.successWithData(service.searchInProject(keyword, pid));
    }
}
