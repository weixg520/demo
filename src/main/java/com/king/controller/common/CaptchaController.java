package com.king.controller.common;

import com.king.domian.dto.ajax.Result;
import com.king.domian.enums.Status;
import com.king.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author weixiaogang
 *
 * 验证码操作处理
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CaptchaController {
    private final RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            Map<String, Object> result = new HashMap<>(2);
            result.put("uuid", uuid);
            result.put("img", Base64.encode(stream.toByteArray()));
            return Result.builder()
                    .code(Status.SUCCESS.getValue())
                    .data(result)
                    .build();
        } catch (Exception e) {
            log.error("~~~~~~~~~~~~~~~~~~生成验证码失败~~~~~~~~~~~~~~~~~~");
            return Result.builder()
                    .code(Status.CODE_IS_EXCEPTION.getValue())
                    .message(Status.CODE_IS_EXCEPTION.getMessage())
                    .build();
        } finally {
            stream.close();
        }
    }
}
