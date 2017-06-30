package com.ltmcp.mobile.dao;

import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;

public interface AppErrorMobileDao {

	public void insert_nor(String code,String returnNum,String username,String posid,String appNum);
	
	public void insert_illegality(String code,String returnNum,String username,String posid);
	
	public NewErrors queryNewErrors(String code);
	
	public Position queryPosition(String posid);
}
