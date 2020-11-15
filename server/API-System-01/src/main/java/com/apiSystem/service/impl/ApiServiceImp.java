package com.apiSystem.service.impl;

import com.apiSystem.entity.po.api.Api;
import com.apiSystem.entity.po.api.ApiExample;
import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.po.api.ApiWithBLOBs;
import com.apiSystem.entity.vo.ApiDisplayVo;
import com.apiSystem.entity.vo.ApiInWhole;
import com.apiSystem.entity.vo.ApiToEdit;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.mapper.*;
import com.apiSystem.service.api.ApiGrpService;
import com.apiSystem.service.api.ApiService;
import com.apiSystem.service.api.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApiServiceImp implements ApiService {

    @Autowired
    private ApiGrpService grpService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiMapper mapper;

    @Override
    public void addApi(ApiVo apiVo, String token) {
        ApiWithBLOBs api = new ApiWithBLOBs();

        try {
            setDataIntoApi(api, apiVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        api.setUpdateTime(new Date());
        api.setUpdaterId(apiVo.getCreatorId());
        mapper.insertSelective(api);
    }

    @Override
    public void deleteApiById(Integer id, String token) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteApiBatch(List<Integer> ids, String token) {
        ApiExample example = new ApiExample();
        example.or().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    @Override
    public List<ApiDisplayVo> queryAllInPrj(Integer projectId) {
        ApiExample example = new ApiExample();
        List<Integer> grpIds = new ArrayList<>();

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
        List<Api> apis = mapper.selectByExample(example);
        return getApiDisplayVo(apis);
    }

    @Override
    public List<ApiDisplayVo> queryAllInGrp(Integer groupId) {
        ApiExample example = new ApiExample();
        example.or().andGidEqualTo(groupId);

        return getApiDisplayVo(mapper.selectByExample(example));
    }

    @Override
    public Api queryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public ApiInWhole getWholeApi(Integer id) {
        ApiWithBLOBs apiWithBLOBs = mapper.selectByPrimaryKey(id);

        return getWholeApiByBlobs(apiWithBLOBs);
    }

    @Override
    public List<ApiDisplayVo> searchInPrj(String keyword, Integer projectId) {
        List<Integer> grpIds = new ArrayList<>();
        List<ApiGrp> grps = grpService.queryAllInProject(projectId);
        ApiExample example = new ApiExample();
        keyword = "%" + keyword + "%";

        for (ApiGrp grp : grps) {
            grpIds.add(grp.getId());
        }

        example.or().andApiNameLike(keyword).andGidIn(grpIds);
        example.or().andUrlLike(keyword).andGidIn(grpIds);

        return getApiDisplayVo(mapper.selectByExample(example));
    }

    @Override
    public List<ApiDisplayVo> searchInGrp(String keyword, Integer groupId) {
        ApiExample example = new ApiExample();
        keyword = "%" + keyword + "%";
        example.or().andApiNameLike(keyword).andGidEqualTo(groupId);
        example.or().andUrlLike(keyword).andGidEqualTo(groupId);

        return getApiDisplayVo(mapper.selectByExample(example));
    }

    @Override
    public void moveApiBatch(List<Integer> apiIds, Integer groupId, String token) {
        ApiWithBLOBs api = new ApiWithBLOBs();
        api.setGid(groupId);
        for (Integer apiId : apiIds) {
            api.setId(apiId);
            mapper.updateByPrimaryKeySelective(api);
        }
    }

    @Override
    public void markApiById(Integer id, Integer isMark, String token) {
        ApiWithBLOBs api = new ApiWithBLOBs();
        api.setId(id);
        api.setFlag(isMark);
        mapper.updateByPrimaryKeySelective(api);
    }

    @Override
    public void markApiBatch(List<Integer> ids, Integer isMark, String token) {
        ApiWithBLOBs api = new ApiWithBLOBs();
        api.setFlag(isMark);
        for (Integer id : ids) {
            api.setId(id);
            mapper.updateByPrimaryKeySelective(api);
        }
    }

    @Override
    public void updateApi(ApiToEdit api, String token) {
        ApiWithBLOBs apiWithBLOBs = api.generateApiWithBLOBs();
        apiWithBLOBs.setUpdateTime(new Date());

        //创建者不可修改
        if(api.getCreatorId()!=null){
            api.setCreatorId(null);
        }

        if(api.getUpdaterId()==0){
            api.setUpdaterId(null);
        }

        mapper.updateByPrimaryKeySelective(apiWithBLOBs);
    }

    //将vo数据转换为po的一组方法
    private void setDataIntoApi(ApiWithBLOBs api, ApiVo apiVo) throws JsonProcessingException {
        ObjectMapper jsonReslover = new ObjectMapper();
        String apiTags = jsonReslover.writeValueAsString(apiVo.getApiTags());
        String apiRtnExample = jsonReslover.writeValueAsString(apiVo.getApiRtnExample());
        String queryParam = jsonReslover.writeValueAsString(apiVo.getQueryParam());
        String reqHeader = jsonReslover.writeValueAsString(apiVo.getReqHeader());
        String reqBody = jsonReslover.writeValueAsString(apiVo.getReqBody());
        String respHeader = jsonReslover.writeValueAsString(apiVo.getRespHeader());
        String respBody = jsonReslover.writeValueAsString(apiVo.getRespBody());

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
        api.setApiTags(apiTags);
        api.setReturnExample(apiRtnExample);
        api.setQueryParam(queryParam);
        api.setReqHeader(reqHeader);
        api.setReqBody(reqBody);
        api.setRespHeader(respHeader);
        api.setRespBody(respBody);
        api.setExtraExplain(apiVo.getExtraExplain());
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

    private ApiInWhole getWholeApiByBlobs(ApiWithBLOBs apiWithBLOBs){
        ApiInWhole wholeApi = new ApiInWhole();
        ApiGrp grp = grpService.queryById(apiWithBLOBs.getGid());
        String creator = userService.queryById(apiWithBLOBs.getCreatorId()).getUsername();
        String manager = userService.queryById(apiWithBLOBs.getManagerId()).getUsername();
        String updator = userService.queryById(apiWithBLOBs.getUpdaterId()).getUsername();
        Long updateTime = apiWithBLOBs.getUpdateTime().getTime();

        wholeApi.setId(apiWithBLOBs.getId());
        wholeApi.setGrp(grp);
        wholeApi.setApiName(apiWithBLOBs.getApiName());
        wholeApi.setReqProtocol(apiWithBLOBs.getReqProtocol());
        wholeApi.setReqMethod(apiWithBLOBs.getReqMethod());
        wholeApi.setFlag(apiWithBLOBs.getFlag());
        wholeApi.setStatus(apiWithBLOBs.getStatus());
        wholeApi.setUrl(apiWithBLOBs.getUrl());
        wholeApi.setReqBodyType(apiWithBLOBs.getReqBodyType());
        wholeApi.setRespJsonType(apiWithBLOBs.getRespJsonType());

        wholeApi.setUpdater(updator);
        wholeApi.setCreator(creator);
        wholeApi.setManager(manager);
        wholeApi.setUpdateTime(updateTime);

        wholeApi.setApiTags(apiWithBLOBs.getApiTags());
        wholeApi.setRtnExamples(apiWithBLOBs.getReturnExample());
        wholeApi.setQueryParams(apiWithBLOBs.getQueryParam());
        wholeApi.setReqHeaders(apiWithBLOBs.getReqHeader());
        wholeApi.setReqBodies(apiWithBLOBs.getReqBody());
        wholeApi.setRespHeaders(apiWithBLOBs.getRespHeader());
        wholeApi.setRespBodies(apiWithBLOBs.getRespBody());
        wholeApi.setExtraExplain(apiWithBLOBs.getExtraExplain());

        return wholeApi;
    }

}
