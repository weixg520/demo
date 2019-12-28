package com.king.domian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author weixiaogang
 * @date 2019-12-21 12:57
 * <p>
 * 类说明：
 */
@Getter
@AllArgsConstructor
public enum Status {
    /**
     * 成功
     */
    SUCCESS(200, "登录成功"),
    /**
     * 失败
     */
    ERROR(201,"登录失败"),
    /**
     * 验证码为空
     */
    CODE_IS_NULL(202, "验证码为空"),
    /**
     * 验证码错误
     */
    CODE_IS_NOT_RIGHT(203, "验证码错误"),
    /**
     * 获取验证码异常
     */
    CODE_IS_EXCEPTION(204, "获取验证码异常"),
    /**
     * 用户名或密码不正确
     */
    USERNAME_PASSWORD_IS_NOT_RIGHT(205, "用户名或密码不正确");

    private final int value;
    private final String message;
}
