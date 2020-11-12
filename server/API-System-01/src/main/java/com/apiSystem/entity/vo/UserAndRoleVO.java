package com.apiSystem.entity.vo;

import lombok.Data;

@Data
public class UserAndRoleVO {
    private Integer uid;
    private Integer rid;
    private String username;
    private String headerUrl;
    private String roleName;
    private Integer isManager;
}
