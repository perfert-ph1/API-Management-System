package com.apiSystem.service.api;

import com.apiSystem.entity.po.api.ApiWithBLOBs;
import com.apiSystem.entity.vo.ApiDisplayVo;
import com.apiSystem.entity.vo.ApiInWhole;
import com.apiSystem.entity.vo.ApiToEdit;
import com.apiSystem.entity.vo.ApiVo;

import java.util.List;

public interface ApiService {

    void addApi(ApiVo apiVo, String token);

    void deleteApiById(Integer id, String token);

    void deleteApiBatch(List<Integer> ids, String token);

    /**
     * 获取指定项目中的全部api
     * 返回的ApiDisplayVo只包含在api列表中展示所需的属性
     */
    List<ApiDisplayVo> queryAllInPrj(Integer projectId);

    /**
     * 获取指定分组中的全部api
     * 返回的ApiDisplayVo只包含在api列表中展示所需的属性
     */
    List<ApiDisplayVo> queryAllInGrp(Integer groupId);

    /**
     * 获取整个api，即包含api的返回示例、各类参数等
     */
    ApiInWhole getWholeApi(Integer id);

    /**
     * 在当前项目下搜索
     * 搜索结果是api名或api的url中包含keyword的api
     */
    List<ApiDisplayVo> searchInPrj(String keyword, Integer projectId);

    /**
     * 在指定分组下搜索
     * 搜索结果是api名或api的url中包含keyword的api
     */
    List<ApiDisplayVo> searchInGrp(String keyword, Integer groupId);

    /**
     * 批量移动api到指定分组
     */
    void moveApiBatch(List<Integer> apiIds, Integer groupId, String token);

    /**
     * 标记或取消标记单个api
     * isMark为1则标记；反之未标记
     */
    void markApiById(Integer id, Integer isMark, String token);

    /**
     * 将指定的一或多个api标记（isMark==1）或取消标记（isMark==2）
     */
    void markApiBatch(List<Integer> ids, Integer isMark, String token);

    void updateApi(ApiToEdit api, String token);
}
