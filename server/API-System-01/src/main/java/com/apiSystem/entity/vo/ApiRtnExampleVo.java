package com.apiSystem.entity.vo;

import lombok.Data;

@Data
public class ApiRtnExampleVo {

    private Integer exampleType;

    private String statusCode;

    private Integer contentType;

    private Integer detailsType;

    private String details;
}
