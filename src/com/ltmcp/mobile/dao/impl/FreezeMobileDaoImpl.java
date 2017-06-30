package com.ltmcp.mobile.dao.impl;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Empower;
import com.ltmcp.entity.Freeze;
import com.ltmcp.mobile.dao.FreezeMobileDao;

public class FreezeMobileDaoImpl extends BaseDaoHibImpl implements FreezeMobileDao {

	@Override
	public Integer saveFreeze(Freeze freeze) throws Exception {
		super.insert(freeze);
		return freeze.getFreId();
	}

	@Override
	public Empower queryPowerCode(String powerCode) {
		StringBuilder sb = new StringBuilder(" from Empower ");
		sb.append(" where powerCode= '"+powerCode+"'");
		return (Empower) super.query(sb.toString());
	}
	
}
