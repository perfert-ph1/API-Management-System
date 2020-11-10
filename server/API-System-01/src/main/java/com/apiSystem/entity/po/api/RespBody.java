package com.apiSystem.entity.po.api;

public class RespBody {
    private Integer id;

    private String fieldName;

    private Integer respId;

    private Integer required;

    private String example;

    private String explanation;

    private String remarks;

    private Integer lenConstraint;

    private String otherConstraint;

    private String dataType;

    public RespBody(Integer id, String fieldName, Integer respId, Integer required, String example, String explanation, String remarks, Integer lenConstraint, String otherConstraint, String dataType) {
        this.id = id;
        this.fieldName = fieldName;
        this.respId = respId;
        this.required = required;
        this.example = example;
        this.explanation = explanation;
        this.remarks = remarks;
        this.lenConstraint = lenConstraint;
        this.otherConstraint = otherConstraint;
        this.dataType = dataType;
    }

    public RespBody() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getRespId() {
        return respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}