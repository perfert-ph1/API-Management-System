package com.apiSystem.entity.po.api;

public class ApiGrp {
    private Integer id;

    private String grpName;

    private Integer pid;

    public ApiGrp(Integer id, String grpName, Integer pid) {
        this.id = id;
        this.grpName = grpName;
        this.pid = pid;
    }

    public ApiGrp() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}