package com.king.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ID生成器工具类
 *
 * @author weixiaogang
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 获取时间类型的随机字符串
     *
     * @return
     */
    public static String getRandomCode() {
        String randomCode = null;
        UUID uuid = UUID.randomUUID();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        randomCode = sDateFormat.format(new Date()) + uuid.hashCode();
        return randomCode.replace("-", "").substring(0, 20);
    }
}
