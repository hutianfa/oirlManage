package com.ltmcp.mobile.biz.impl;

import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.OutsourcingMobileBiz;
import com.ltmcp.mobile.dao.OutsourcingMobileDao;

public class OutsourcingMobileBizImple implements OutsourcingMobileBiz{

	private OutsourcingMobileDao outsourcingMobileDao;
	@Override
	public Integer findCodeId(String code) {
		return outsourcingMobileDao.queryCodeId(code);
	}

	
	@Override
	public Sealed findSeaByCodeId(Integer id) {
		return outsourcingMobileDao.querySeaByCodeId(id);
	}

	
	
	
	
	
	
	
	
	
	
	public OutsourcingMobileDao getOutsourcingMobileDao() {
		return outsourcingMobileDao;
	}

	public void setOutsourcingMobileDao(OutsourcingMobileDao outsourcingMobileDao) {
		this.outsourcingMobileDao = outsourcingMobileDao;
	}

}
