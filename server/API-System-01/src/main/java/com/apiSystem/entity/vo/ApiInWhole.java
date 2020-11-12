package com.apiSystem.entity.vo;

import com.apiSystem.entity.po.api.*;
import lombok.Data;

import java.util.List;

/**
 * 完整的api
 * 即包含：
 *      api本身参数信息
 *      api的返回示例
 *      api的标签
 *      各类参数
 *      :)
 */
@Data
public class ApiInWhole {
    private Api api;
    private List<ApiRtnExample> rtnExamples;
    private List<ApiTag> apiTags;
    private List<QueryParam> queryParams;
    private List<ReqHeader> reqHeaders;
    private List<ReqBody> reqBodies;
    private List<RespHeader> respHeaders;
    private List<RespBody> respBodies;
}
