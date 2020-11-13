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
    private Integer id;
    private ApiGrp grp;///
    private String apiName;
    private Integer reqProtocol;
    private Integer reqMethod;
    private Integer flag;
    private Integer status;
    private String url;
    private Integer respJsonType;
    private Integer reqBodyType;
    private String creator;///
    private String manager;///
    private String updater;///
    private long updateTime;///

    private String rtnExamples;
    private String apiTags;
    private String queryParams;
    private String reqHeaders;
    private String reqBodies;
    private String respHeaders;
    private String respBodies;
}
