package com.apiSystem.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Integer id;

    private String username;

    private String headerUrl;

    private Date createTime;
}
