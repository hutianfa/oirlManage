package com.ltmcp.dao;

import com.ltmcp.entity.Freeze;

public interface FreezeDao {

	Freeze queryFreezeBySealedId(Integer seaId);
	
	
}
