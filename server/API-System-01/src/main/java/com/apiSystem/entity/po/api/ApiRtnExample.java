package com.apiSystem.entity.po.api;

public class ApiRtnExample {
    private Integer id;

    private Integer apiId;

    private Integer exampleType;

    private String statusCode;

    private Integer contentType;

    private Integer detailsType;

    private String details;

    public ApiRtnExample(Integer id, Integer apiId, Integer exampleType, String statusCode, Integer contentType, Integer detailsType, String details) {
        this.id = id;
        this.apiId = apiId;
        this.exampleType = exampleType;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.detailsType = detailsType;
        this.details = details;
    }

    public ApiRtnExample() {
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

    public Integer getExampleType() {
        return exampleType;
    }

    public void setExampleType(Integer exampleType) {
        this.exampleType = exampleType;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(Integer detailsType) {
        this.detailsType = detailsType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}