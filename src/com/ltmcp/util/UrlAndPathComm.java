package com.ltmcp.util;

import java.io.File;

public class UrlAndPathComm {
	/**
	 * �ļ���ȡ��·��
	 */
	public static String winPath ="d:"+File.separator+"ltmcp"+File.separator;
	public static String linuxPath = File.separator+"home"+File.separator+"ltmcp"+File.separator;
	
	public static String comm = winPath;
	
	/**
	 * ���ݿ����ӵĵ�ַ
	 */
	public static String localDataBases ="192.168.0.66:3306";
	public static String onlineDataBases ="127.0.0.1:3306";
	
	public static String DataBases = onlineDataBases;
}
