package com.ltmcp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTool {
	
	/**
	 * ��������String��֤������ʽ
	 * @param regex ������ʽ
	 * @param str ��֤���ַ���
	 * @return ��֤�����true��ʾ��֤��ȷ��false��ʾ��֤����
	 */
	public static boolean regexString(String regex,String obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj);
		return m.find();
	}
	
	/**
	 * ��������Integer��֤������ʽ
	 * @param regex ������ʽ
	 * @param str ��֤���ַ���
	 * @return ��֤�����true��ʾ��֤��ȷ��false��ʾ��֤����
	 */
	public static boolean regexInteger(String regex,Integer obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj.toString());
		return m.find();
	}
	
	/**
	 * ��������Long��֤������ʽ
	 * @param regex ������ʽ
	 * @param str ��֤���ַ���
	 * @return ��֤�����true��ʾ��֤��ȷ��false��ʾ��֤����
	 */
	public static boolean regexLong(String regex,Long obj){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(obj.toString());
		return m.find();
	}
	
}
