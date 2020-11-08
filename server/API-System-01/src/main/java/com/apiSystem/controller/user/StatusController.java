package com.apiSystem.controller.user;

import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.vo.StatusVO;
import com.apiSystem.service.api.StatusService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService service;

    @GetMapping("/cookie")
    public ResultEntity getCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("token", "123456");
        response.addCookie(cookie);
        return ResultEntity.successWithoutData();
    }

    /**
     * 批量插入
     * 请求体数据示例：
     * statuses:
     *  [{"statusCode":"1" , "description":"a"},{"statusCode":"2" , "description":"b"},{"statusCode":"3" , "description":"c"}]
     * gid: 3
     */
    @PostMapping("/addStatus")
    public ResultEntity addBatch(@RequestParam String statuses,@RequestParam Integer gid, @CookieValue String token){
        List<StatusVO> statusVOS = new ArrayList<>();
        //将前端发送的json数组字符串转换为json数组
        JSONArray jsonArray = JSONArray.fromObject(statuses);
        for (int i = 0; i < jsonArray.size(); i++) {
            //依次从jsonArray中获取JSONObject对象
            JSONObject jsonOjb = JSONObject.fromObject(jsonArray.get(i));
            //将JSONObject对象转换为StatusVO
            StatusVO vo = (StatusVO) JSONObject.toBean(jsonOjb, StatusVO.class);
            statusVOS.add(vo);
        }

        if(service.addStatusBatch(statusVOS, gid, token)) {
            return ResultEntity.successWithoutData();
        }
        else {
            return ResultEntity.failed(null, "添加状态码时出错");
        }
    }

    @PostMapping("/deleteStatus")
    public ResultEntity deleteBatch(@RequestParam("ids") String statusIds, @CookieValue String token){
        String[] idsStr = statusIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idsStr) {
            ids.add(Integer.parseInt(idStr));
        }

        if(service.deleteStatusBatch(ids, token)){
            return ResultEntity.successWithoutData();
        }
        else {
            return ResultEntity.failed(null, "删除出错");
        }
    }

    @GetMapping("/queryByGroupId")
    public ResultEntity queryByGid(@RequestParam("groupId") Integer groupId){
        if(groupId==null){
            return ResultEntity.failed(null, "参数为空");
        }

        List<Status> statuses = service.queryByGroupId(groupId);
        return ResultEntity.successWithData(statuses);
    }

    /**
     * 获取一个项目下所有的状态码
     */
    @GetMapping("/queryAllInPrj")
    public ResultEntity queryAllInPrj(@RequestParam Integer projectId){
        if(projectId==null){
            return ResultEntity.failed(null, "参数错误");
        }

        List<Status> result = service.queryAllInProject(projectId);
        return ResultEntity.successWithData(result);
    }

    @GetMapping("/searchStatus")
    public ResultEntity searchStatus(@RequestParam("keyword")String keyword){
        return ResultEntity.successWithData(service.searchStatus(keyword));
    }

    @PostMapping("/updateStatus")
    public ResultEntity updateStatus(Status status, @CookieValue String token){
        if(service.updateStatus(status, token)){
            return ResultEntity.successWithoutData();
        }

        return ResultEntity.failed(null, "更新失败");
    }
}
