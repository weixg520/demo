package com.king.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author weixiaogang
 * @date 2019-12-18 20:50
 * <p>
 * 类说明：数据格式转换工具类
 */
public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(ToCN(163230.99));
    }

    /**
     * 把页面String类型的科学计数法转换成普通计数法
     *
     * @param doubleString
     * @return
     */
    public static String doubleStringFormat(String doubleString) {
        Double d = Double.parseDouble(doubleString);
        //格式化设置
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String str = decimalFormat.format(d);
        return str;
    }

    /**
     * 把页面double类型的科学计数法转换成普通计数法
     *
     * @param doub
     * @return
     */
    public static String doubleFormat(double doub) {
        // 格式化设置
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String str = decimalFormat.format(doub);
        return str;
    }

    public static Double div(Double v1, Double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static long mathFormat(Double douBum) {
        if (douBum == null) {
            return 0L;
        }
        return Math.round(douBum);
    }

    public static String subTel(String tel) {
        if (tel != null && !tel.equals("") && tel.length() > 8) {
            int between = tel.length() / 2;
            // 拼接最终值
            return tel.substring(0, between - 2) + "****"
                    + tel.substring(between + 2, tel.length());
        } else {
            return "";
        }
    }

    /**
     * * 100返回
     *
     * @param amount
     * @return
     */
    public static Double enlargeHundred(Double amount) {
        if (amount == null) {
            return null;
        }
        return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(new Double(100))).doubleValue();
    }

	/**
	 * 除100返回
	 *
	 * @param amount
	 * @return
	 */
	public static Double reduceHundred(Double amount) {
        if (amount == null) {
            return 0.00;
        }
        return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(new Double(100))).doubleValue();
    }

	/**
	 * @param amount
	 * @return
	 */
	public static String diviseHundered(Double amount) {
		// 格式化设置
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        if (amount == null) {
            return "0.00";
        }
		return decimalFormat.format(BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(new Double(1))).doubleValue());
    }

	/**
	 * 获取一个double类型数据的有几位小数
	 *
	 * @param d
	 * @return
	 */
	public static int getDoubleFigure(double d) {
        String ds = d + "";
        int figure = ds.length() - ds.indexOf(".") - 1;
        return figure;
    }

    /**
     * 财务 钱大写
     *
     * @param d
     * @return
     */
    public static String ToCN(double d) {
        String money = String.valueOf(d);
        String[] part = money.split("\\.");
        StringBuffer integer = new StringBuffer();
        for (int i = 0; i < part[0].length(); i++) {
            char perchar = part[0].charAt(i);
            integer.append(upperNumber(perchar));
            integer.append(upperNumber(part[0].length() - i - 1));
        }
        StringBuffer decimal = new StringBuffer();
        if (part.length > 1 && !"00".equals(part[1])) {
            int length = part[1].length() >= 2 ? 2 : part[1].length();
            for (int i = 0; i < length; i++) {
                char perchar = part[1].charAt(i);
                decimal.append(upperNumber(perchar));
                if (i == 0) {
					decimal.append('角');
				}
                if (i == 1) {
					decimal.append('分');
				}
            }
        }
        String result = integer.toString() + decimal.toString();
        return dispose(result);
    }

    private static char upperNumber(char number) {
        switch (number) {
            case '0':
                return '零';
            case '1':
                return '壹';
            case '2':
                return '贰';
            case '3':
                return '叁';
            case '4':
                return '肆';
            case '5':
                return '伍';
            case '6':
                return '陆';
            case '7':
                return '柒';
            case '8':
                return '捌';
            case '9':
                return '玖';
            default:
                return 0;
        }
    }

    private static char upperNumber(int index) {
        switch (index) {
            case 0:
                return '圆';
            case 1:
                return '拾';
            case 2:
                return '佰';
            case 3:
                return '仟';
            case 4:
                return '万';
            case 5:
                return '拾';
            case 6:
                return '佰';
            case 7:
                return '仟';
            case 8:
                return '亿';
            case 9:
                return '拾';
            case 10:
                return '佰';
            case 11:
                return '仟';
            default:
                return 0;
        }
    }

    private static String dispose(String result) {
        result = result.replaceAll("零仟零佰零拾|零仟零佰|零佰零拾|零仟|零佰|零拾", "零")
                .replaceAll("零+", "零").replace("零亿", "亿");
        result = result.matches("^.*亿零万[^零]仟.*$") ? result.replace("零万", "零")
                : result.replace("零万", "万");
        result = result.replace("亿万", "亿").replace("零圆", "圆").replace("零分", "")
                .replaceAll("圆零角零分|圆零角$|圆$", "圆整");
        return result;
    }
}
