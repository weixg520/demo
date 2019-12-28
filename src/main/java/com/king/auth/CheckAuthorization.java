package com.king.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author weixiaogang
 * @Date 2019-12-16 22:42
 * <p>
 * 类说明：校验权限,只在运行时反射能取到
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
