package com.apiSystem.entity.po;

public class Status {
    private Integer id;

    private Integer statusCode;

    private String description;

    private Integer gid;

    public Status(Integer id, Integer statusCode, String description, Integer gid) {
        this.id = id;
        this.statusCode = statusCode;
        this.description = description;
        this.gid = gid;
    }

    public Status() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}