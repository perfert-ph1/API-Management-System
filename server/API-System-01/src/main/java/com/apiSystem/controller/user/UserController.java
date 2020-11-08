package com.apiSystem.controller.user;

import com.apiSystem.Constant.UserConstant;
import com.apiSystem.entity.bo.UserBo;
import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.vo.UserVo;
import com.apiSystem.service.api.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @ResponseBody
    @PostMapping("/register")
    public ResultEntity<String> regist(UserBo user){
        if(user == null){
            return ResultEntity.failed(null,"服务器内部出错");
        }
        if(user.getUsername() == null || user.getUsername().trim().equals("")){
            return ResultEntity.failed(null,"username不能为空");
        }
        if(user.getPassword() == null || user.getPassword().trim().equals("")){
            return ResultEntity.failed(null,"password不能为空");
        }

        Map<String, Object> registMap = userService.regist(user);
        if(registMap == null || registMap.isEmpty() ){
            return ResultEntity.successWithoutData();
        }else {
            return ResultEntity.failed(null, (String) registMap.get("usernameMsg"));
        }

    }

    @ResponseBody
    @PostMapping("/login")
    public ResultEntity<String> login(UserBo user, HttpServletResponse response){
        if(user == null){
            return ResultEntity.failed(null,"服务器内部出错");
        }
        if(user.getUsername() == null || user.getUsername().trim().equals("")){
            return ResultEntity.failed(null,"username不能为空");
        }
        if(user.getPassword() == null || user.getPassword().trim().equals("")){
            return ResultEntity.failed(null,"password不能为空");
        }

        Map<String, Object> loginMap = userService.login(user.getUsername(), user.getPassword(), UserConstant.expiredTime);
        if(loginMap.containsKey("Message")){
            return ResultEntity.failed(null, (String) loginMap.get("Message"));
        }
        Cookie cookie = new Cookie("token",loginMap.get("token").toString());
        cookie.setPath(contextPath);
        cookie.setMaxAge(UserConstant.expiredTime);
        response.addCookie(cookie);
        return ResultEntity.successWithoutData();
    }

    @GetMapping("/user/getUserInfo")
    @ResponseBody
    public ResultEntity<UserVo> getUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        UserVo user = userService.findUserByToken(token);
        return ResultEntity.successWithData(user);
    }



}
