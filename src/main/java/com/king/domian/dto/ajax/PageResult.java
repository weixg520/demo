package com.king.domian.dto.ajax;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author weixiaogang
 * @date 2019-12-23 21:05
 * <p>
 * 类说明：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据
     */
    private List<T> rows;
}
