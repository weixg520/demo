package com.king.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author weixiaogang
 * @date 2019-12-18 20:50
 * <p>
 * 类说明：时间工具类
 */
public class DateUtil extends DateUtils {
    private static final SimpleDateFormat DATE_MONTH = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat DATE_YEAR = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat DATE_DAY = new SimpleDateFormat("yyyy-MM-dd");

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

	/**
	 * 获得当前日期后几小时
	 *
	 * @param date
	 * @param num
	 * @return
	 */
    public static Date getDayByHour(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.HOUR);
        calendar.set(Calendar.HOUR, day + num);
        return calendar.getTime();
    }

	/**
	 * 获得当前日期后几天
	 *
	 * @param date
	 * @param num
	 * @return
	 */
    public static Date getDayByDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + num);
        return calendar.getTime();
    }

	/**
	 * 获得当前日期
	 *
	 * @param num
	 * @return
	 */
	public static Date getHourByDay(int num) {
        Calendar c = Calendar.getInstance();
		// 设置当前日期
        c.setTime(new Date());
		// 日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
		c.add(Calendar.HOUR, num);
		// 结果
        Date date = c.getTime();
        return date;
    }

	/**
	 * 获得当前日期后几月
	 *
	 * @param date
	 * @param num
	 * @return
	 */
    public static Date getDayByMonth(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + num);
        return calendar.getTime();
    }

	/**
	 * 两个日期相减获得差(日期,月)
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static String getDayForSub(Date date1, Date date2) {
        int quot = (int) (getQuot(date1, date2) / 30);
        String returnString;
        switch (quot) {
            case 0:
                returnString = "10";
                break;
            case 1:
                returnString = "1";
                break;
            case 3:
                returnString = "3";
                break;
            case 6:
                returnString = "6";
                break;
			default:
				returnString = "";
        }
        return returnString;
    }

	/**
	 * 两个日期相差天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static long getQuot(Date date1, Date date2) {
        long quot = 0;
        quot = date1.getTime() - date2.getTime();
        quot = quot / 1000 / 60 / 60 / 24;
        return quot;
    }

    public static Date parseDate(String date, String format, Locale local) {
        SimpleDateFormat DATE_TIME = new SimpleDateFormat(format, local);
        try {
            return DATE_TIME.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return DATE_TIME.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public static Date parseDateDay(String date) {
        SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return DATE_TIME.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

	/**
	 * 返回当前日期[yyyy-MM-dd]Date类型
	 *
	 * @return
	 */
	public static Date todate() {
        try {
            SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
            return matter1.parse(matter1.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hourDiff(Date endTime) {
        // 按照传入的格式生成一个simpledateformate对象
		// 一小时的毫秒数
        long nh = 1000 * 60 * 60;
		// 一分钟的毫秒数
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - System.currentTimeMillis();
		// 计算差多少小时
        long hour = diff / nh;
        if (hour > 0) {
            return "剩余：" + hour + "小时";
        } else if (hour == 0) {
			// 计算差多少分钟
            long min = diff / nm;
            if (min > 0) {
                return "剩余：" + min + "分钟";
            } else {
                return "已过期";
            }
        } else {
            return "已过期";
        }
    }

    public static String formatDateMonth(Date date) {
        if (date == null) {
			return null;
		} else {
			return DATE_MONTH.format(date);
		}
    }

    public static String formatDateYear(Date date) {
        if (date == null) {
			return null;
		} else {
			return DATE_YEAR.format(date);
		}
    }

    /**
     * @param date
     * @return 字符串年月日
     * @date : 2019-12-18 20:50
	 *
	 * 格式化年月日
     */
    public static String formatDateDay(Date date) {
        if (date == null) {
			return null;
		} else {
			return DATE_DAY.format(date);
		}
    }

    public static String dayByDiff(Date endTime) {
        // 按照传入的格式生成一个simpledateformate对象
		// 一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;
		// 一小时的毫秒数
        long nh = 1000 * 60 * 60;
		// 一分钟的毫秒数
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - System.currentTimeMillis();
		// 计算差多少小时
        long day = diff / nd;
        if (day > 0) {
            return "剩余：" + day + "天";
        } else if (day == 0) {
			// 计算差多少小时
            long hour = diff / nh;
            if (hour > 0) {
                return "剩余：" + hour + "小时";
            } else if (hour == 0) {
				// 计算差多少分钟
                long min = diff / nm;
                if (min > 0) {
                    return "剩余：" + min + "分钟";
                } else {
                    return "已过期";
                }
            } else {
                return "已过期";
            }
        } else {
            return "已过期";
        }

    }

    /**
     * @param d1 开始时间
     * @param d2 结束时间
     * @return d1大于d2, 返回1, d1小于d2, 返回-1,相等返回0
     * @date : 2019-12-18 20:50
	 *
	 * 两个时间比较大小，d1=2018-03-19 00:00:00,d2=2018-03-24 00:00:00,返回-1
     */
    public static int compareDate(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {
			// 相等
            return 0;
        }
    }

    public static void main(String[] args) {
        Date d1 = DateUtil.parseDate("2018-03-19 00:00:00");
        Date d2 = DateUtil.parseDate("2018-03-24 00:00:00");
        int compareDate = compareDate(d1, d2);
        System.out.println(compareDate);
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @date : 2019-12-18 20:50
	 *
	 * 若data1大于data2 返回1
     */
    public static int compare_date(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * @param date1 日期
     * @param date2 日期
     * @return 是同一天就返回true
     * @date : 2019-12-18 20:50
	 *
	 * 验证两个日期是否是同一天
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) ==
				cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) ==
				cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) ==
				cal2.get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }
}