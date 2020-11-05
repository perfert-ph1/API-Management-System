package com.apiSystem.service.impl;

import com.apiSystem.entity.po.LoginTokenPO;
import com.apiSystem.entity.po.UserPO;
import com.apiSystem.entity.po.UserPOExample;
import com.apiSystem.entity.vo.User;
import com.apiSystem.mapper.LoginTokenPOMapper;
import com.apiSystem.mapper.UserPOMapper;
import com.apiSystem.service.api.UserService;
import com.apiSystem.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPOMapper userPOMapper;

    @Autowired
    private LoginTokenPOMapper loginTokenPOMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public Map<String,Object> regist(User user) {

        Map<String,Object> map = new HashMap<>();

        if(user == null){
            map.put("systemMsg","服务器内部出错");
            return map;
        }

        //验证账号
        UserPOExample userPOExample = new UserPOExample();
        userPOExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
        if(userPOS != null && userPOS.size()>0){
            map.put("usernameMsg","该账号已存在");
            return map;
        }

        //注册用户
        UserPO userPO = new UserPO();
        userPO.setUsername(user.getUsername());
        userPO.setSalt(CommonUtil.generateUUID().substring(0,5));
        userPO.setPassword(CommonUtil.md5(user.getPassword()+userPO.getSalt()));
        userPO.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        userPO.setCreateTime(new Date());
        userPOMapper.insert(userPO);

        return map;

    }

    @Override
    public Map<String, Object> login(String username, String password, int expiredSeconds){
        Map<String,Object> map = new HashMap<>();

        //验证账号
        UserPOExample userPOExample = new UserPOExample();
        userPOExample.createCriteria().andUsernameEqualTo(username);
        List<UserPO> userPOS = userPOMapper.selectByExample(userPOExample);
        if(userPOS == null || userPOS.size() == 0){
            map.put("Message","用户名不存在");
            return map;
        }
        //校验密码
        UserPO userPO = userPOS.get(0);
        if(!userPO.getPassword().equals(CommonUtil.md5(password+userPO.getSalt()))){
            map.put("Message","密码错误");
            return map;
        }

        //生成登录凭证
        LoginTokenPO loginTokenPO = new LoginTokenPO();
        loginTokenPO.setUserId(userPO.getId());
        loginTokenPO.setToken(CommonUtil.generateUUID());
        loginTokenPO.setStatus(0);
        loginTokenPO.setExpired(new Date(System.currentTimeMillis()+ expiredSeconds));

        //将token插入到数据库表中
        loginTokenPOMapper.insert(loginTokenPO);
        map.put("token",loginTokenPO.getToken());

        return map;
    }
}
