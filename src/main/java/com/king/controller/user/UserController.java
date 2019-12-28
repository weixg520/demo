package com.king.controller.user;

import com.king.auth.CheckAuthorization;
import com.king.auth.ChenkLogin;
import com.king.domian.dto.user.JwtTokenRespDTO;
import com.king.domian.dto.user.LoginRespDTO;
import com.king.domian.dto.user.UserLoginDTO;
import com.king.domian.dto.user.UserRespDTO;
import com.king.domian.entity.User;
import com.king.domian.enums.Status;
import com.king.utils.Constants;
import com.king.utils.JwtOperator;
import com.king.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weixiaogang
 * @date 2019-12-18 20:50
 * <p>
 * 类说明：用户Controller
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final JwtOperator jwtOperator;
    private final RedisCache redisCache;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO) {
        // 1、判断验证码
        String verifyKey = Constants.CAPTCHA_CODE_KEY + loginDTO.getUuid();
        String captcha = this.redisCache.getCacheObject(verifyKey);
        this.redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            log.warn("验证码为空");
            return LoginRespDTO.builder()
                    .status(Status.CODE_IS_NULL.getValue())
                    .message(Status.CODE_IS_NULL.getMessage())
                    .build();
        }
        if (!loginDTO.getCode().equalsIgnoreCase(captcha)) {
            log.warn("验证码不正确");
            return LoginRespDTO.builder()
                    .status(Status.CODE_IS_NOT_RIGHT.getValue())
                    .message(Status.CODE_IS_NOT_RIGHT.getMessage())
                    .build();
        }

        // 2、用户验证
        // 数据库查询用户信息
        /*User user = userDao.findByMobile(mobile);
        // 解密
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }*/
        if (!"admin".equals(loginDTO.getUsername()) || !"admin123".equals(loginDTO.getPassword())) {
            return LoginRespDTO.builder()
                    .status(Status.USERNAME_PASSWORD_IS_NOT_RIGHT.getValue())
                    .message(Status.USERNAME_PASSWORD_IS_NOT_RIGHT.getMessage())
                    .build();
        }

        // 3、如果一致颁发token并且跳转到首页
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", 1);
        userInfo.put("userName", "mm");
        userInfo.put("role", "admin");

        String token = this.jwtOperator.generateToken(userInfo);
        log.info("用户登录成功,生成token = {},有效期到：{}", token, jwtOperator.getExpirationTime());

        return LoginRespDTO.builder()
                .user(
                        UserRespDTO.builder()
                                .id(1)
                                .userName("mm")
                                .build()
                )
                .token(
                        JwtTokenRespDTO.builder()
                                .expirationTime(jwtOperator.getExpirationTime().getTime())
                                .token(token)
                                .build()
                )
                .status(Status.SUCCESS.getValue())
                .message(Status.SUCCESS.getMessage())
                .build();
    }

    @ChenkLogin
    @PostMapping("/checkToken")
    public void checkToken() {}

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public void add(UserLoginDTO loginDTO) {
        // user.setPassword(encoder.encode(user.getPassword()));
        User.builder()
                // 密码加密
                .password(encoder.encode(loginDTO.getPassword()))
                .username(loginDTO.getUsername())
                .build();

    }

    @GetMapping("/test-not-login")
    public String test() {
        return "测试成功";
    }

    @GetMapping("/test-login")
    @ChenkLogin
    public String l() {
        return "需要登录访问测试成功";
    }

    @ChenkLogin
    @GetMapping("/test-admin")
    @CheckAuthorization("admin")
    public String testAdmin() {
        return "需要登录并且权限为admin测试成功";
    }
}
