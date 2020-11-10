package com.apiSystem.entity.po.api;

public class RespHeader {
    private Integer id;

    private Integer respId;

    private String tagName;

    private String dataType;

    private Integer required;

    private String example;

    private String explanation;

    private String remarks;

    private Integer lenConstraint;

    private String otherConstraint;

    public RespHeader(Integer id, Integer respId, String tagName, String dataType, Integer required, String example, String explanation, String remarks, Integer lenConstraint, String otherConstraint) {
        this.id = id;
        this.respId = respId;
        this.tagName = tagName;
        this.dataType = dataType;
        this.required = required;
        this.example = example;
        this.explanation = explanation;
        this.remarks = remarks;
        this.lenConstraint = lenConstraint;
        this.otherConstraint = otherConstraint;
    }

    public RespHeader() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRespId() {
        return respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
}