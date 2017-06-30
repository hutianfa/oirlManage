package com.ltmcp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTool {
	
	/**
	 * 数据类型String验证正则表达式
	 * @param regex 正则表达式
	 * @param str 验证的字符串
	 * @return 验证结果：true表示验证正确，false表示验证错误
	 */
	public static boolean regexString(String regex,String obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj);
		return m.find();
	}
	
	/**
	 * 数据类型Integer验证正则表达式
	 * @param regex 正则表达式
	 * @param str 验证的字符串
	 * @return 验证结果：true表示验证正确，false表示验证错误
	 */
	public static boolean regexInteger(String regex,Integer obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj.toString());
		return m.find();
	}
	
	/**
	 * 数据类型Long验证正则表达式
	 * @param regex 正则表达式
	 * @param str 验证的字符串
	 * @return 验证结果：true表示验证正确，false表示验证错误
	 */
	public static boolean regexLong(String regex,Long obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj.toString());
		return m.find();
	}
	
}
