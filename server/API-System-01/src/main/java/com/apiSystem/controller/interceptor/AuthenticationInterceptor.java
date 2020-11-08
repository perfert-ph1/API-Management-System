package com.apiSystem.controller.interceptor;

import com.apiSystem.Constant.UserConstant;
import com.apiSystem.entity.dto.ResultEntity;
import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.mapper.LoginTokenPOMapper;
import com.apiSystem.service.api.UserService;
import com.apiSystem.util.CookieUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        JSONObject res = new JSONObject();

        if(token != null){
            LoginTokenPO loginToken = userService.findLoginToken(token);
            //如果token不存在
            if(loginToken == null) {
                res.put("result", ResultEntity.FAILED);
                res.put("message", "无效的token，请重新登录");
                res.put("data", null);
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            //token失效
            if(loginToken.getStatus() == 1){
                res.put("result", ResultEntity.FAILED);
                res.put("message", "无效的token,请重新登录");
                res.put("data", null);
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            //token过期，刷新token
            if(loginToken.getExpired().before(new Date())){
                LoginTokenPO loginTokenPO = userService.refreshToken(token);
                Cookie cookie = new Cookie("token",loginTokenPO.getToken());
                cookie.setPath(contextPath);
                cookie.setMaxAge(UserConstant.expiredTime);
                response.addCookie(cookie);
                res.put("result", ResultEntity.TOKEN_INVALID_CODE);
                res.put("message", "过期的token,请在cookie重新获取");
                res.put("data", null);
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
