package com.king.auth;

import com.king.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author weixiaogang
 * @date 2019-12-16 21:14
 * <p>
 * 类说明：检查是否登录和授权切面
 */
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthAspect {
    private final JwtOperator jwtOperator;

    /**
     * 检查是否登录
     *
     * @param point point
     * @return 返回值
     */
    @Around("@annotation(com.king.auth.ChenkLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
        this.checkToken();
        return point.proceed();
    }

    /**
     * 检查是否授权
     */
    @Around("@annotation(com.king.auth.CheckAuthorization)")
    public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable {
        try {
            // 1、验证token是否合法
            this.checkToken();

            // 2、验证用户角色是否匹配
            HttpServletRequest request = getHttpServletRequest();
            String role = (String) request.getAttribute("role");

            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            CheckAuthorization annotation = method.getAnnotation(CheckAuthorization.class);

            // 拿到CheckAuthorization注解value()里面的值
            String value = annotation.value();

            // 拿到角色可以到数据库里面查询是否权限,有权限放行，没有则抛异常
            if (!Objects.equals(role, value)) {
                throw new SecurityException("用户无权访问!");
            }
        } catch (Throwable throwable) {
            throw new SecurityException("用户无权访问!", throwable);
        }
        return point.proceed();
    }

    public void checkToken() {
        try {
            // 1、从header里面获取token
            HttpServletRequest request = getHttpServletRequest();
            String token = request.getHeader("M-Token");

            // 2、校验token是否合法并且是否过期,如果不合法或已过期,就直接抛异常，如果合法就放行
            Boolean isValid = jwtOperator.validateToken(token);
            if (!isValid) {
                throw new SecurityException("Token不合法!");
            }

            // 3、如果校验成功,那么就将用户的信息设置到request的attribute里面
            // 用户信息
            Claims claims = jwtOperator.getClaimsFromToken(token);
            request.setAttribute("id", claims.get("id"));
            request.setAttribute("userName", claims.get("userName"));
            request.setAttribute("role", claims.get("role"));
        } catch (Throwable throwable) {
            throw new SecurityException("Token不合法!");
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        // RequestAttributes静态方法获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        if (attributes == null) {
            throw new SecurityException("request为空");
        }
        return attributes.getRequest();
    }
}
