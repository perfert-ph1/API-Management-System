package com.apiSystem.service.api;

public interface LoginTokenService {
    /**
     * 根据token查询用户id，若查询不到，返回-1
     */
    int queryUserIdByToken(String token);
}
