package com.apiSystem.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ApiVo {
    private Integer gid;

    private String apiName;

    private Integer reqProtocol;

    private Integer reqMethod;

    private Integer status;

    private String url;

    private Integer creatorId;

    private Integer managerId;

    private Integer reqBodyType;

    private Integer respJsonType;

    private List<String> apiTags;

    private List<ApiRtnExampleVo> apiRtnExample;

    private List<QueryParamVo> queryParam;

    private List<ReqHeaderVo> reqHeader;

    private List<ReqBodyVo> reqBody;

    private List<RespHeaderVo> respHeader;

    private List<RespBodyVo> respBody;
}
