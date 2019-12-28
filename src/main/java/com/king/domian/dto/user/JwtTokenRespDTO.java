package com.king.domian.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author weixiaogang
 * @Date 2019-12-18 20:57
 * <p>
 * 类说明：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenRespDTO {
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Long expirationTime;
}
