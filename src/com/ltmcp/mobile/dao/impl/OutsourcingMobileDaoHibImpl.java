package com.ltmcp.mobile.dao.impl;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.OutsourcingMobileDao;

public class OutsourcingMobileDaoHibImpl extends BaseDaoHibImpl implements OutsourcingMobileDao{

	@Override
	public Integer queryCodeId(String code) {
		StringBuilder sb = new StringBuilder("select d.id from DimensionalBarCode d");
		sb.append(" where d.freeze_content=? or d.unfreeze_content =?");
		sb.append(" and (d.freeze_status=2 or d.unfreeze_status =2)");
		return (Integer) super.queryUniqueObject(sb.toString(),code,code);
	}

	@Override
	public Sealed querySeaByCodeId(Integer id) {
		StringBuilder sb=new StringBuilder("from Sealed s where s.dimensionalBarCode.id=? ");
		sb.append(" and s.tag = 71 ");
		return (Sealed) super.query(sb.toString(),id);
	}
}
