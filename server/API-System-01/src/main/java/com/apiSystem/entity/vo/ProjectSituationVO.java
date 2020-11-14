package com.apiSystem.entity.vo;

import lombok.Data;

@Data
public class ProjectSituationVO {
    private Integer id;

    private String projectName;

    private Integer projType;

    private String remarks;

    private String version;

    private long updateTime;

    private Integer apiNum;

    private Integer statusCodeNum;

    private Integer memberNum;
}
