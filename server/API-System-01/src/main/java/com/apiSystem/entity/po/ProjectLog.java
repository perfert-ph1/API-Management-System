package com.apiSystem.entity.po;

import java.util.Date;

public class ProjectLog {
    private Integer id;

    private String username;

    private Date time;

    private String operationInfo;

    private Integer pid;

    public ProjectLog(Integer id, String username, Date time, String operationInfo, Integer pid) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.operationInfo = operationInfo;
        this.pid = pid;
    }

    public ProjectLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}