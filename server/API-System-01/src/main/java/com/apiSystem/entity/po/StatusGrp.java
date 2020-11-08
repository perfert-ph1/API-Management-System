package com.apiSystem.entity.po;

public class StatusGrp {
    private Integer id;

    private String grpName;

    private Integer pid;

    public StatusGrp(Integer id, String grpName, Integer pid) {
        this.id = id;
        this.grpName = grpName;
        this.pid = pid;
    }

    public StatusGrp() {
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