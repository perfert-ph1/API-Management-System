package com.apiSystem.service.api;


import com.apiSystem.entity.bo.UserBo;
import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.vo.UserVo;
import com.apiSystem.entity.po.UserPO;

import java.util.Map;

public interface UserService {
    /**
     * 注册业务
     * @param user
     * @return
     */
    public Map<String,Object> regist(UserBo user);

    /**
     * 登陆业务，颁发token
     * @param username
     * @param password
     * @param expiredSeconds
     * @return
     */
    public Map<String,Object> login(String username,String password,int expiredSeconds);

    /**
     * 退出
     * @param token
     */
    public void logout(String token);

    /**
     * 通过token查找凭证
     * @param token
     * @return
     */
    public LoginTokenPO findLoginToken(String token);

    /**
     * 刷新token
     * @param token
     * @return
     */
    public LoginTokenPO refreshToken(String token);

    /**
     * 根据Token查询User
     * @param token
     * @return
     */
    UserVo findUserByToken(String token);

    UserPO queryById(Integer id);

}
