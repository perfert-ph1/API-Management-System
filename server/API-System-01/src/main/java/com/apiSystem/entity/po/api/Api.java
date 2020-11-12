package com.apiSystem.entity.po.api;

import java.util.Date;

public class Api {
    private Integer id;

    private Integer gid;

    private String apiName;

    private Integer reqProtocol;

    private Integer reqMethod;

    private Integer flag;

    private Integer status;

    private String url;

    private Integer respJsonType;

    private Integer reqBodyType;

    private Integer creatorId;

    private Integer managerId;

    private Integer updaterId;

    private Date updateTime;

    public Api(Integer id, Integer gid, String apiName, Integer reqProtocol, Integer reqMethod, Integer flag, Integer status, String url, Integer respJsonType, Integer reqBodyType, Integer creatorId, Integer managerId, Integer updaterId, Date updateTime) {
        this.id = id;
        this.gid = gid;
        this.apiName = apiName;
        this.reqProtocol = reqProtocol;
        this.reqMethod = reqMethod;
        this.flag = flag;
        this.status = status;
        this.url = url;
        this.respJsonType = respJsonType;
        this.reqBodyType = reqBodyType;
        this.creatorId = creatorId;
        this.managerId = managerId;
        this.updaterId = updaterId;
        this.updateTime = updateTime;
    }

    public Api() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Integer getReqProtocol() {
        return reqProtocol;
    }

    public void setReqProtocol(Integer reqProtocol) {
        this.reqProtocol = reqProtocol;
    }

    public Integer getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(Integer reqMethod) {
        this.reqMethod = reqMethod;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRespJsonType() {
        return respJsonType;
    }

    public void setRespJsonType(Integer respJsonType) {
        this.respJsonType = respJsonType;
    }

    public Integer getReqBodyType() {
        return reqBodyType;
    }

    public void setReqBodyType(Integer reqBodyType) {
        this.reqBodyType = reqBodyType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}