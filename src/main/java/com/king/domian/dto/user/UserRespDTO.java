package com.king.domian.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weixiaogang
 * @date 2019-12-18 21:03
 * <p>
 * 类说明：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
}
