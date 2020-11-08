package com.apiSystem.service.impl;

import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.po.LoginTokenPOExample;
import com.apiSystem.mapper.LoginTokenPOMapper;
import com.apiSystem.service.api.LoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginTokenServiceImp implements LoginTokenService {

    @Autowired
    private LoginTokenPOMapper mapper;

    @Override
    public int queryUserIdByToken(String token) {
        LoginTokenPOExample example = new LoginTokenPOExample();
        example.or().andTokenEqualTo(token);
        List<LoginTokenPO> loginTokens = mapper.selectByExample(example);

        if(loginTokens.size()>0){
            return loginTokens.get(0).getUserId();
        }

        return -1;
    }
}
