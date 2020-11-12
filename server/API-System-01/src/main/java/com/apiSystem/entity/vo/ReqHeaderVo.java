package com.apiSystem.entity.vo;

import lombok.Data;

@Data
public class ReqHeaderVo {
    // private Integer id;
    //

    private String tagName;

    private String dataType;

    private Integer required;

    private String example;

    private String explanation;

    private String remarks;

    private Integer lenConstraint;

    private String otherConstraint;
}
