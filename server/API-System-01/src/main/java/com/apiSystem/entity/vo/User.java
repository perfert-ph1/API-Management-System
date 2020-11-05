package com.apiSystem.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private String username;

    private String password;

    private String headerUrl;

    private Date createTime;

    public User() {
    }

    public User(String username, String password, String headerUrl, Date createTime) {
        this.username = username;
        this.password = password;
        this.headerUrl = headerUrl;
        this.createTime = createTime;
    }
}
