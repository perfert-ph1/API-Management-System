package com.apiSystem.service.api;


import com.apiSystem.entity.vo.User;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
    /**
     * 注册业务
     * @param user
     * @return
     */
    public Map<String,Object> regist(User user);

    /**
     * 登陆业务，颁发token
     * @param username
     * @param password
     * @param expiredSeconds
     * @return
     */
    public Map<String,Object> login(String username,String password,int expiredSeconds);
}
