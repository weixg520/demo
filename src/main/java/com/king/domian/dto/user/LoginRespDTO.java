package com.king.domian.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weixiaogang
 * @Date 2019-12-18 20:55
 * <p>
 * 类说明：登录返回DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO {
    /**
     * token
     */
    private JwtTokenRespDTO token;
    /**
     * 用户信息
     */
    private UserRespDTO user;
    /**
     * 返回状态
     */
    private Integer status;
    /**
     * 提示消息
     */
    private String message;
}
