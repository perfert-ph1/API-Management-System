package com.apiSystem.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoginToken {

    private int userId;
    private String token;

    public LoginToken() {
    }


    public LoginToken(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
