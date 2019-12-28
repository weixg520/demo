package com.king.domian.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weixiaogang
 * @date 2019-12-18 20:59
 * <p>
 * 类说明：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 记住我
     */
    private String rememberMe;
    /**
     * UUID
     */
    private String uuid;
}
