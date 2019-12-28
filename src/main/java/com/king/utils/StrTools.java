package com.king.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weixiaogang
 * @date 2019-12-18 20:50
 * <p>
 * 类说明：字符串工具类
 */
public class StrTools {
	/**
	 * 隐藏手机号
	 *
	 * @param tel 手机号
	 * @return 返回处理过的手机号,123***789
	 */
	public static String hideTel(String tel){
		if(null != tel && tel.length() == 11){
			String str1 = tel.substring(0, 3);
			String str2 = tel.substring(7, 11);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("****").append(str2);
			return sb.toString();
		}
		return tel;
	}

	/**
	 * 隐藏身份证号码
	 *
	 * @param idNumber 身份证号码
	 * @return 返回处理过的身份证号码
	 */
	public static String hideIdNumber(String idNumber){
		if(null != idNumber && idNumber.length() == 18){
			String str1 = idNumber.substring(0, 3);
			String str2 = idNumber.substring(14, 18);
			StringBuffer sb = new StringBuffer();
			sb.append(str1).append("***********").append(str2);
			return sb.toString();                                                                                                            
		}
		return idNumber;
	}

	/**
	 * 前台页面中的某一个字符串是否包含另一个字符串，用,隔开
	 *
	 * @param parent
	 * @param child
	 * @return
	 */
	public static boolean isContainStr(String parent,String child){
		if (StringUtils.isBlank(parent)||StringUtils.isBlank(child)) {
			return false;
		}else {
			List<String> list = Arrays.asList(StringUtils.split(parent,","));
			if (list.contains(child)) {
				return true;
			}else {
				return false;
			}
		}
	}
	/**
	 * 处理内容
	 *
	 * @param content
	 * @param key
	 * @return
	 */
	public static String replaceContent(String content,String... key){
		for (int i = 0; i < key.length; i++) {
			content=content.replace("varValue"+i, key[i]);
		}
		return content;
	}
	
	public static List<String> stringToList(String ids) {
		String[] arrids = ids.split(",");
		List<String> idslist = new ArrayList<String>();
		for (int i = 0; i < arrids.length; i++) {
			idslist.add(arrids[i]);
		}
		return idslist;
	}
	/** 
     * 去除字符串中所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	 *
     * @param s 
     * @return 
     */  
    public static String removeAllBlank(String s){  
        String result = "";  
        if(null!=s && !"".equals(s)){  
            result = s.replaceAll("[　*| *| *|//s*]*", "");  
        }  
        return result;  
    }  
  
    /** 
     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	 *
     * @param s 
     * @return 
     */  
    public static String trim(String s){  
        String result = "";  
        if(null!=s && !"".equals(s)){  
            result = s.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");  
        }  
        return result;  
    }  

	/**
	 * 将emoji表情替换成空串
	 *
	 * @param source 字符串
	 * @return 过滤后的字符串
	 */
	public static String filterEmoji(String source) {
        if (source != null && source.length() > 0) {
            return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return source;
        }
    }
	public static void main(String[] args) {
	    String source = "张三";
	    System.out.println(filterEmoji(source));
	}
}