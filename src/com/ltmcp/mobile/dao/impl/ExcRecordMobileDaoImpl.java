package com.ltmcp.mobile.dao.impl;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.mobile.dao.ExcRecordMobileDao;

public class ExcRecordMobileDaoImpl extends BaseDaoHibImpl implements ExcRecordMobileDao {

	@Override
	public void saveException(ExcRecord record) {
		super.insert(record);
	}

}
