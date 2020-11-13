package com.apiSystem.entity.po.api;

import java.util.Date;

public class ApiWithBLOBs extends Api {
    private String returnExample;

    private String apiTags;

    private String queryParam;

    private String reqHeader;

    private String reqBody;

    private String respHeader;

    private String respBody;

    public ApiWithBLOBs(Integer id, Integer gid, String apiName, Integer reqProtocol, Integer reqMethod, Integer flag, Integer status, String url, Integer respJsonType, Integer reqBodyType, Integer creatorId, Integer managerId, Integer updaterId, Date updateTime, String returnExample, String apiTags, String queryParam, String reqHeader, String reqBody, String respHeader, String respBody) {
        super(id, gid, apiName, reqProtocol, reqMethod, flag, status, url, respJsonType, reqBodyType, creatorId, managerId, updaterId, updateTime);
        this.returnExample = returnExample;
        this.apiTags = apiTags;
        this.queryParam = queryParam;
        this.reqHeader = reqHeader;
        this.reqBody = reqBody;
        this.respHeader = respHeader;
        this.respBody = respBody;
    }



    public ApiWithBLOBs() {
        super();
    }

    public String getReturnExample() {
        return returnExample;
    }

    public void setReturnExample(String returnExample) {
        this.returnExample = returnExample;
    }

    public String getApiTags() {
        return apiTags;
    }

    public void setApiTags(String apiTags) {
        this.apiTags = apiTags;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public String getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(String reqHeader) {
        this.reqHeader = reqHeader;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(String respHeader) {
        this.respHeader = respHeader;
    }

    public String getRespBody() {
        return respBody;
    }

    public void setRespBody(String respBody) {
        this.respBody = respBody;
    }
}