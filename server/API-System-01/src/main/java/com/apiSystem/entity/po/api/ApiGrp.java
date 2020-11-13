package com.apiSystem.entity.po.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ApiGrp {
    private Integer id;

    @NotEmpty(message = "分组名称不能为空")
    @NotNull(message = "分组名称不能为空")
    private String grpName;

    @NotNull(message = "必需指定分组所属项目id")
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