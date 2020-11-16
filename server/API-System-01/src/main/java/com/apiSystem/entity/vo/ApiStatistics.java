package com.apiSystem.entity.vo;

import lombok.Data;

/**
 * 统计api中指定状态下的api数量
 */
@Data
public class ApiStatistics {
    private Integer status;
    private Integer count;
}
