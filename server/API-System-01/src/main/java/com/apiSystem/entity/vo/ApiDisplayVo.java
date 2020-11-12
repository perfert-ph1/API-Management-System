package com.apiSystem.entity.vo;

import lombok.Data;

/**
 * 包含在api列表展示所需数据
 */
@Data
public class ApiDisplayVo {
    private Integer id;

    private Integer gid;

    private String apiName;

    private Integer reqMethod;

    private Integer flag;

    private Integer status;

    private String url;

    private String creator;

    private String manager;

    private String updater;
}
