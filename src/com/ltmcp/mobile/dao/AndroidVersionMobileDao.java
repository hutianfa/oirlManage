package com.ltmcp.mobile.dao;

import com.ltmcp.entity.AndroidVersion;

public interface AndroidVersionMobileDao {
	public AndroidVersion queryAndroidVersion(String versionType);
	
	public AndroidVersion queryTimeForVedio();
}
