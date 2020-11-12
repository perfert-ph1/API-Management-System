package com.apiSystem.entity.po.api;

public class ReqBody {
    private Integer id;

    private Integer apiId;

    private String paramName;

    private String dataType;

    private Integer required;

    private String example;

    private String explanation;

    private String remarks;

    private Integer lenConstraint;

    private String otherConstraint;

    private String subField;

    public ReqBody(Integer id, Integer apiId, String paramName, String dataType, Integer required, String example, String explanation, String remarks, Integer lenConstraint, String otherConstraint, String subField) {
        this.id = id;
        this.apiId = apiId;
        this.paramName = paramName;
        this.dataType = dataType;
        this.required = required;
        this.example = example;
        this.explanation = explanation;
        this.remarks = remarks;
        this.lenConstraint = lenConstraint;
        this.otherConstraint = otherConstraint;
        this.subField = subField;
    }

    public ReqBody() {
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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getLenConstraint() {
        return lenConstraint;
    }

    public void setLenConstraint(Integer lenConstraint) {
        this.lenConstraint = lenConstraint;
    }

    public String getOtherConstraint() {
        return otherConstraint;
    }

    public void setOtherConstraint(String otherConstraint) {
        this.otherConstraint = otherConstraint;
    }

    public String getSubFirld() {
        return subField;
    }

    public void setSubFirld(String subFirld) {
        this.subField = subFirld;
    }
}