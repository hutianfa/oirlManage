package com.ltmcp.mobile.biz;

import java.util.Map;

public interface AndroidVersionMobileBiz {
	//获取数据库最新版本的app版本号
	public String getAndroidVersion(String versionType);
	
	public Map<String, String> getTimeForVedio();
}
