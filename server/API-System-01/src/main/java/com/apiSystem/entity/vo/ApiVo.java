package com.apiSystem.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用于封装添加api时前端发送的数据
 */
@Data
public class ApiVo {

    @NotNull(message = "分组id不能为空")
    private Integer gid;

    @NotNull(message = "api名称不能为空")
    @NotEmpty(message = "api名称不能为空")
    private String apiName;

    @NotNull(message = "请求协议类型不能为空")
    private Integer reqProtocol;

    @NotNull(message = "请求方法不能为空")
    private Integer reqMethod;

    @NotNull(message = "api状态不能为空")
    private Integer status;

    @NotNull
    @NotEmpty(message = "url不能为空")
    private String url;

    @NotNull(message = "必需指定创建者id")
    private Integer creatorId;

    @NotNull(message = "必需指定管理者id")
    private Integer managerId;

    private Integer reqBodyType;

    private Integer respJsonType;

    private String extraExplain;

    private List<String> apiTags;

    private List<ApiRtnExampleVo> apiRtnExample;

    private List<QueryParamVo> queryParam;

    private List<ReqHeaderVo> reqHeader;

    private List<ReqBodyVo> reqBody;

    private List<RespHeaderVo> respHeader;

    private List<RespBodyVo> respBody;
}
