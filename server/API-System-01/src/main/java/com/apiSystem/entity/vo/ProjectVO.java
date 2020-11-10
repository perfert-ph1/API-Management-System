package com.apiSystem.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectVO {
    private Integer id;

    private String projectName;

    private Integer projType;

    private String remarks;

    private String version;

    private long updateTime;

}
