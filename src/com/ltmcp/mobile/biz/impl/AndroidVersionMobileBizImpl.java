package com.ltmcp.mobile.biz.impl;

import java.util.HashMap;
import java.util.Map;

import com.ltmcp.entity.AndroidVersion;
import com.ltmcp.mobile.biz.AndroidVersionMobileBiz;
import com.ltmcp.mobile.dao.AndroidVersionMobileDao;

public class AndroidVersionMobileBizImpl implements AndroidVersionMobileBiz{
	private AndroidVersionMobileDao androidVersionMobileDao;
	@Override
	public String getAndroidVersion(String versionType) {
		AndroidVersion av=androidVersionMobileDao.queryAndroidVersion(versionType);
		if(null==av){
			return "";
		}else{
			net.sf.json.JSONObject data = net.sf.json.JSONObject.fromObject(av);
			String s=data.toString();
			return s;
		}
	}
	public AndroidVersionMobileDao getAndroidVersionMobileDao() {
		return androidVersionMobileDao;
	}
	public void setAndroidVersionMobileDao(AndroidVersionMobileDao androidVersionMobileDao) {
		this.androidVersionMobileDao = androidVersionMobileDao;
	}
	@Override
	public Map<String, String> getTimeForVedio() {
		AndroidVersion av1 = androidVersionMobileDao.queryTimeForVedio();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("picTime", av1.getVersionName());
		map.put("vedioTime", av1.getVersionContent());
		return map;
	}
	
	

}
