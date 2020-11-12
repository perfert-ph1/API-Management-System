package com.apiSystem.service.impl;

import com.apiSystem.entity.po.api.*;
import com.apiSystem.entity.vo.*;
import com.apiSystem.mapper.*;
import com.apiSystem.service.api.ApiGrpService;
import com.apiSystem.service.api.ApiService;
import com.apiSystem.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApiServiceImp implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Autowired
    private ApiTagMapper tagMapper;

    @Autowired
    private ApiRtnExampleMapper rtnExampleMapper;

    @Autowired
    private ReqHeaderMapper reqHeaderMapper;

    @Autowired
    private ReqBodyMapper reqBodyMapper;

    @Autowired
    private RespHeaderMapper respHeaderMapper;

    @Autowired
    private RespBodyMapper respBodyMapper;

    @Autowired
    private QueryParamMapper queryParamMapper;

    @Autowired
    private ApiGrpService grpService;

    @Autowired
    private UserService userService;

    @Override
    public void addApi(ApiVo apiVo, String token) {
        Api api = new Api();
        List<String> tagNames;
        List<ApiRtnExampleVo> rtnExamples;
        Integer apiId;

        setDataIntoApi(api, apiVo);//装填所需数据到api
        api.setUpdaterId(apiVo.getCreatorId());//创建时更新者设置为创建者
        api.setFlag(0);//标记默认为0（未标记）
        api.setUpdateTime(new Date());//更新时间设为创建时的时间

        //插入API
        apiMapper.insertSelective(api);
        apiId = api.getId();

        //插入api的标签(若有）
        insertTags(apiId, apiVo.getApiTags());

        //插入返回示例(若有）
        insertRtnExample(apiId, apiVo.getApiRtnExample());

        //插入请求头参数(若有）
        insertReqHeaders(apiId, apiVo.getReqHeader());

        //插入请求体参数(若有）
        insertReqBodys(apiId, apiVo.getReqBody());

        //插入响应头参数(若有）
        insertRespHeaders(apiId, apiVo.getRespHeader());

        //插入响应内容(若有）
        insertRespBodys(apiId, apiVo.getRespBody());

        //插入query参数(若有）
        insertQueryParam(apiId, apiVo.getQueryParam());
    }

    @Override
    public void deleteApiById(Integer id, String token) {
        apiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteApiBatch(List<Integer> ids, String token) {
        ApiExample example = new ApiExample();
        example.or().andIdIn(ids);

        apiMapper.deleteByExample(example);
    }

    @Override
    public List<ApiDisplayVo> queryAllInPrj(Integer projectId) {
        ApiExample example = new ApiExample();
        List<Integer> grpIds = new ArrayList<>();
        List<ApiDisplayVo> displayVos = null;

        List<ApiGrp> grps = grpService.queryAllInProject(projectId);
        if(grps.size()>0) {
            for (ApiGrp grp : grps) {
                grpIds.add(grp.getId());
            }
        }
        //该项目下没有分组，即没有api
        else {
            return new ArrayList<>();
        }

        example.or().andGidIn(grpIds);
        List<Api> apis = apiMapper.selectByExample(example);
        displayVos = getApiDisplayVo(apis);

        return displayVos;
    }

    @Override
    public List<ApiDisplayVo> queryAllInGrp(Integer groupId) {
        ApiExample example = new ApiExample();
        example.or().andGidEqualTo(groupId);

        List<ApiDisplayVo> result = getApiDisplayVo(apiMapper.selectByExample(example));
        return result;
    }

    @Override
    public ApiInWhole getWholeApi(Integer id) {
        ApiInWhole result = new ApiInWhole();
        Api api = apiMapper.selectByPrimaryKey(id);

        ApiRtnExampleExample rtnExample = new ApiRtnExampleExample();
        ApiTagExample tagExample = new ApiTagExample();
        QueryParamExample queryParamExample = new QueryParamExample();
        ReqHeaderExample reqHeaderExample = new ReqHeaderExample();
        ReqBodyExample reqBodyExample = new ReqBodyExample();
        RespHeaderExample respHeaderExample = new RespHeaderExample();
        RespBodyExample respBodyExample = new RespBodyExample();

        //查询出所有属于该api的数据
        rtnExample.or().andApiIdEqualTo(id);
        tagExample.or().andApiIdEqualTo(id);
        queryParamExample.or().andApiIdEqualTo(id);
        reqHeaderExample.or().andApiIdEqualTo(id);
        reqBodyExample.or().andApiIdEqualTo(id);
        respHeaderExample.or().andApiIdEqualTo(id);
        respBodyExample.or().andApiIdEqualTo(id);
        List<ApiRtnExample> rtnExamples = rtnExampleMapper.selectByExampleWithBLOBs(rtnExample);
        List<ApiTag> tags = tagMapper.selectByExample(tagExample);
        List<QueryParam> queryParams = queryParamMapper.selectByExample(queryParamExample);
        List<ReqHeader> reqHeaders = reqHeaderMapper.selectByExample(reqHeaderExample);
        List<ReqBody> reqBodies = reqBodyMapper.selectByExampleWithBLOBs(reqBodyExample);
        List<RespHeader> respHeaders = respHeaderMapper.selectByExample(respHeaderExample);
        List<RespBody> respBodies = respBodyMapper.selectByExampleWithBLOBs(respBodyExample);

        result.setApi(api);
        result.setApiTags(tags);
        result.setRtnExamples(rtnExamples);
        result.setQueryParams(queryParams);
        result.setReqHeaders(reqHeaders);
        result.setReqBodies(reqBodies);
        result.setRespHeaders(respHeaders);
        result.setRespBodies(respBodies);
        //diu ni lao mv
        return result;
    }

    @Override
    public List<ApiDisplayVo> searchInPrj(String keyword, Integer projectId) {
        List<Integer> grpIds = new ArrayList<>();
        List<ApiGrp> grps = grpService.queryAllInProject(projectId);

        for (ApiGrp grp : grps) {
            grpIds.add(grp.getId());
        }
        keyword = "%" + keyword + "%";
        ApiExample example = new ApiExample();
        example.or().andApiNameLike(keyword).andGidIn(grpIds);
        example.or().andUrlLike(keyword).andGidIn(grpIds);
        List<ApiDisplayVo> result = getApiDisplayVo(apiMapper.selectByExample(example));
        return result;
    }

    @Override
    public List<ApiDisplayVo> searchInGrp(String keyword, Integer groupId) {
        ApiExample example = new ApiExample();
        keyword = "%" + keyword + "%";
        example.or().andApiNameLike(keyword).andGidEqualTo(groupId);
        example.or().andUrlLike(keyword).andGidEqualTo(groupId);

        List<ApiDisplayVo> result = getApiDisplayVo(apiMapper.selectByExample(example));
        return result;
    }

    @Override
    public void moveApiBatch(List<Integer> apiIds, Integer groupId, String token) {
        Api api = new Api();
        api.setGid(groupId);
        for (Integer apiId : apiIds) {
            api.setId(apiId);
            apiMapper.updateByPrimaryKeySelective(api);
        }
    }

    @Override
    public void markApiById(Integer id, Integer isMark, String token) {
        Api api = new Api();
        api.setId(id);
        api.setFlag(isMark);

        apiMapper.updateByPrimaryKeySelective(api);
    }

    @Override
    public void markApiBatch(List<Integer> ids, Integer isMark, String token) {
        Api api = new Api();
        api.setFlag(isMark);
        for (Integer id : ids) {
            api.setId(id);
            apiMapper.updateByPrimaryKeySelective(api);
        }
    }

    @Override
    public void updateApi(ApiInWhole apiVo, String token) {
        //shit
    }


    //插入api相关数据的一组方法
    private void insertTags(Integer apiId, List<String> tagNames){
        for (String tagName : tagNames) {
            ApiTag tag = new ApiTag();
            tag.setApiId(apiId);
            tag.setTagName(tagName);
            tagMapper.insertSelective(tag);
        }
    }

    private void insertRtnExample(Integer apiId, List<ApiRtnExampleVo> rtnExampleVos){
        for (ApiRtnExampleVo rtnExampleVo : rtnExampleVos) {
            ApiRtnExample rtnExample = new ApiRtnExample();
            rtnExample.setApiId(apiId);
            setDataToRtnExample(rtnExample, rtnExampleVo);

            rtnExampleMapper.insertSelective(rtnExample);
        }
    }

    private void insertReqHeaders(Integer apiId, List<ReqHeaderVo> reqHeaderVos){
        for (ReqHeaderVo vo : reqHeaderVos) {
            ReqHeader header = new ReqHeader();
            header.setApiId(apiId);
            setDataToReqHeader(header, vo);
            reqHeaderMapper.insertSelective(header);
        }
    }

    private void insertReqBodys(Integer apiId, List<ReqBodyVo> reqBodyVos){
        for (ReqBodyVo vo : reqBodyVos) {
            ReqBody body = new ReqBody();
            body.setApiId(apiId);
            setDataToReqBody(body, vo);
            reqBodyMapper.insertSelective(body);
        }
    }

    private void insertRespHeaders(Integer apiId, List<RespHeaderVo> vos){
        for (RespHeaderVo vo : vos) {
            RespHeader header = new RespHeader();
            header.setApiId(apiId);
            setDataToRespHeader(header, vo);
            respHeaderMapper.insertSelective(header);
        }
    }

    private void insertRespBodys(Integer apiId, List<RespBodyVo> vos){
        for (RespBodyVo vo : vos) {
            RespBody body = new RespBody();
            body.setApiId(apiId);
            setDataToRespBody(body, vo);
            respBodyMapper.insertSelective(body);
        }
    }

    private void insertQueryParam(Integer apiId, List<QueryParamVo> vos){
        for (QueryParamVo vo : vos) {
            QueryParam queryParam = new QueryParam();
            queryParam.setApiId(apiId);
            setDataToQueryParam(queryParam, vo);
            queryParamMapper.insertSelective(queryParam);
        }
    }


    //将vo数据转换为po的一组方法
    private void setDataIntoApi(Api api, ApiVo apiVo){
        api.setGid(apiVo.getGid());
        api.setApiName(apiVo.getApiName());
        api.setReqProtocol(apiVo.getReqProtocol());
        api.setReqMethod(apiVo.getReqMethod());
        api.setStatus(apiVo.getStatus());
        api.setUrl(apiVo.getUrl());
        api.setRespJsonType(apiVo.getRespJsonType());
        api.setReqBodyType(apiVo.getReqBodyType());
        api.setCreatorId(apiVo.getCreatorId());
        api.setManagerId(apiVo.getManagerId());
    }

    private void setDataToRtnExample(ApiRtnExample rtnExample, ApiRtnExampleVo rtnExampleVo){
        rtnExample.setExampleType(rtnExampleVo.getExampleType());
        rtnExample.setStatusCode(rtnExampleVo.getStatusCode());
        rtnExample.setContentType(rtnExampleVo.getContentType());
        rtnExample.setDetailsType(rtnExampleVo.getDetailsType());
        rtnExample.setDetails(rtnExampleVo.getDetails());
    }

    private void setDataToReqHeader(ReqHeader header, ReqHeaderVo vo){
        header.setTagName(vo.getTagName());
        header.setDataType(vo.getDataType());
        header.setRequired(vo.getRequired());
        header.setExample(vo.getExample());
        header.setExplanation(vo.getExplanation());
        header.setRemarks(vo.getRemarks());
        header.setLenConstraint(vo.getLenConstraint());
        header.setOtherConstraint(vo.getOtherConstraint());
    }

    private void setDataToReqBody(ReqBody body, ReqBodyVo vo) {
        body.setParamName(vo.getParamName());
        body.setDataType(vo.getDataType());
        body.setRequired(vo.getRequired());
        body.setExample(vo.getExample());
        body.setExplanation(vo.getExplanation());
        body.setRemarks(vo.getRemarks());
        body.setLenConstraint(vo.getLenConstraint());
        body.setOtherConstraint(vo.getOtherConstraint());
        body.setSubFirld(vo.getSubField());
    }

    private void setDataToRespHeader(RespHeader header, RespHeaderVo vo){
        header.setTagName(vo.getTagName());
        header.setDataType(vo.getDataType());
        header.setRequired(vo.getRequired());
        header.setExample(vo.getExample());
        header.setExplanation(vo.getExplanation());
        header.setRemarks(vo.getRemarks());
        header.setLenConstraint(vo.getLenConstraint());
        header.setOtherConstraint(vo.getOtherConstraint());
    }

    private void setDataToRespBody(RespBody body, RespBodyVo vo) {
        body.setFieldName(vo.getFieldName());
        body.setDataType(vo.getDataType());
        body.setRequired(vo.getRequired());
        body.setExample(vo.getExample());
        body.setExplanation(vo.getExplanation());
        body.setRemarks(vo.getRemarks());
        body.setLenConstraint(vo.getLenConstraint());
        body.setOtherConstraint(vo.getOtherConstraint());
        body.setSubFirld(vo.getSubField());
    }

    private void setDataToQueryParam(QueryParam queryParam, QueryParamVo vo) {
        queryParam.setParamName(vo.getParamName());
        queryParam.setDataType(vo.getDataType());
        queryParam.setRequired(vo.getRequired());
        queryParam.setExample(vo.getExample());
        queryParam.setExplanation(vo.getExplanation());
        queryParam.setRemarks(vo.getRemarks());
        queryParam.setLenConstraint(vo.getLenConstraint());
        queryParam.setOtherConstraint(vo.getOtherConstraint());
    }


    //po转换为vo
    private List<ApiDisplayVo> getApiDisplayVo(List<Api> apis) {
        List<ApiDisplayVo> result = new ArrayList<>();
        for (Api api : apis) {
            ApiDisplayVo displayVo = new ApiDisplayVo();
            //根据vo中的id查询对应用户名
            String creator = userService.queryById(api.getCreatorId()).getUsername();
            String manager = userService.queryById(api.getManagerId()).getUsername();
            String updater = userService.queryById(api.getUpdaterId()).getUsername();

            displayVo.setId(api.getId());
            displayVo.setGid(api.getGid());
            displayVo.setApiName(api.getApiName());
            displayVo.setReqMethod(api.getReqMethod());
            displayVo.setFlag(api.getFlag());
            displayVo.setStatus(api.getStatus());
            displayVo.setUrl(api.getUrl());
            displayVo.setCreator(creator);
            displayVo.setManager(manager);
            displayVo.setUpdater(updater);
            result.add(displayVo);
        }
        return result;
    }

}
