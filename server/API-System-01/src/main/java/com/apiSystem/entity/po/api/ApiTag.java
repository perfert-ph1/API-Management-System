package com.apiSystem.entity.po.api;

public class ApiTag {
    private Integer id;

    private Integer apiId;

    private String tagName;

    public ApiTag(Integer id, Integer apiId, String tagName) {
        this.id = id;
        this.apiId = apiId;
        this.tagName = tagName;
    }

    public ApiTag() {
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}