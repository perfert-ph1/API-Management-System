package com.apiSystem;

import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.mapper.LoginTokenPOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MapperTests {

    @Autowired
    private LoginTokenPOMapper loginTokenPOMapper;

    @Test
    public void testInsertLoginToken(){
        LoginTokenPO loginTokenPO = new LoginTokenPO();
        loginTokenPO.setUserId(2);
        loginTokenPO.setToken("abc");
        loginTokenPO.setExpired(new Date(System.currentTimeMillis() + 1000*60*30));
        loginTokenPO.setStatus(0);
        System.out.println(loginTokenPO.getExpired());
        loginTokenPOMapper.insert(loginTokenPO);
    }

    @Test
    public void testTime(){
        System.out.println(new Date(System.currentTimeMillis() + 1000*60*10));
    }

}
