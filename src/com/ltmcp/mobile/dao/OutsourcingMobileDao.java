package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Sealed;

public interface OutsourcingMobileDao {

	//根据code查询施封码，返回id
	public Integer queryCodeId(String code);
	//根据codeid查询运单
	public Sealed querySeaByCodeId(Integer id);
}
