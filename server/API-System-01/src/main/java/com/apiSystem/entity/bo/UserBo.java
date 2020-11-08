package com.apiSystem.entity.bo;

import lombok.Data;

import java.util.Date;

@Data
public class UserBo {

    private Integer id;

    private String username;

    private String password;

    private String headerUrl;

    private Date createTime;


}
