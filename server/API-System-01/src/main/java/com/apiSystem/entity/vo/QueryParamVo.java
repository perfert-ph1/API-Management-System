package com.apiSystem.entity.vo;

import lombok.Data;

@Data
public class QueryParamVo {
    private String paramName;

    private String dataType;

    private Integer required;

    private String example;

    private String explanation;

    private String remarks;

    private Integer lenConstraint;

    private String otherConstraint;
}
