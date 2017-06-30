package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Empower;
import com.ltmcp.entity.Freeze;

public interface FreezeMobileDao {
	
	public Integer saveFreeze(Freeze freeze)throws Exception;
	
	//ÊÚÈ¨Âë  ²éÑ¯
	
	public Empower queryPowerCode(String powerCode);
	
}
