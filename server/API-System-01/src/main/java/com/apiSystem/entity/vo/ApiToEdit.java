package com.apiSystem.entity.vo;

import com.apiSystem.entity.po.api.ApiWithBLOBs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ApiToEdit {
    @NotNull(message = "必需指定api的id")
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

    @NotNull(message = "必需指定编辑者id")
    private Integer updaterId;
    private List<String> apiTags;
    private List<ApiRtnExampleVo> apiRtnExample;
    private List<QueryParamVo> queryParam;
    private List<ReqHeaderVo> reqHeader;
    private List<ReqBodyVo> reqBody;
    private List<RespHeaderVo> respHeader;
    private List<RespBodyVo> respBody;

    /**
     * 转换为ApiWithBLOBs对象
     */
    public ApiWithBLOBs generateApiWithBLOBs(){
        ObjectMapper objectMapper = new ObjectMapper();
        String rtnStr = null;
        String tagsStr = null;
        String queryParamStr = null;
        String reqHeaderStr = null;
        String reqBodyStr = null;
        String respHeaderStr = null;
        String respBodyStr = null;
        ApiWithBLOBs apiWithBLOBs;

        //不为空的数据转换为对应的json对象数组字符串
        try {
            if(apiRtnExample!=null) {
                rtnStr = objectMapper.writeValueAsString(apiRtnExample);
            }
            if(apiTags!=null) {
                tagsStr = objectMapper.writeValueAsString(apiTags);
            }
            if(queryParam!=null) {
                queryParamStr = objectMapper.writeValueAsString(queryParam);
            }
            if(reqHeader!=null) {
                reqHeaderStr = objectMapper.writeValueAsString(reqHeader);
            }
            if(reqBody!=null) {
                reqBodyStr = objectMapper.writeValueAsString(reqBody);
            }
            if(respHeader!=null) {
                respHeaderStr = objectMapper.writeValueAsString(respHeader);
            }
            if(respBody!=null) {
                respBodyStr = objectMapper.writeValueAsString(respBody);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        apiWithBLOBs = new ApiWithBLOBs(id,gid, apiName, reqProtocol, reqMethod, flag, status, url, respJsonType,
                    reqBodyType,creatorId, managerId, updaterId, null, rtnStr, tagsStr,
                    queryParamStr, reqHeaderStr, reqBodyStr,
                    respHeaderStr, respBodyStr)
        ;

        return apiWithBLOBs;
    }
}
