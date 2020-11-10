package com.apiSystem.entity.po.api;

public class Response {
    private Integer id;

    private Integer apiId;

    private Integer jsonType;

    public Response(Integer id, Integer apiId, Integer jsonType) {
        this.id = id;
        this.apiId = apiId;
        this.jsonType = jsonType;
    }

    public Response() {
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

    public Integer getJsonType() {
        return jsonType;
    }

    public void setJsonType(Integer jsonType) {
        this.jsonType = jsonType;
    }
}