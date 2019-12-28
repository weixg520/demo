package com.king.domian.dto.ajax;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weixiaogang
 * @date 2019-12-23 21:00
 * <p>
 * 类说明：返回值封装类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
}
