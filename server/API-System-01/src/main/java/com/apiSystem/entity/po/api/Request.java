package com.apiSystem.entity.po.api;

public class Request {
    private Integer id;

    private Integer apiId;

    private Integer reqBodyType;

    public Request(Integer id, Integer apiId, Integer reqBodyType) {
        this.id = id;
        this.apiId = apiId;
        this.reqBodyType = reqBodyType;
    }

    public Request() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getReqBodyType() {
        return reqBodyType;
    }

    public void setReqBodyType(Integer reqBodyType) {
        this.reqBodyType = reqBodyType;
    }
}