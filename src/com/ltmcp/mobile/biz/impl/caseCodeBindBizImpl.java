package com.ltmcp.mobile.biz.impl;


import com.ltmcp.entity.Inventor_BoxCode;
import com.ltmcp.mobile.biz.caseCodeBindBiz;
import com.ltmcp.mobile.dao.caseCodeBindBizDao;


public class caseCodeBindBizImpl implements caseCodeBindBiz{
	private caseCodeBindBizDao caseCodeBindDao;

	public caseCodeBindBizDao getCaseCodeBindDao() {
		return caseCodeBindDao;
	}

	public void setCaseCodeBindDao(caseCodeBindBizDao caseCodeBindDao) {
		this.caseCodeBindDao = caseCodeBindDao;
	}
	
	

	@Override
	public boolean checkCaseCodeExist(String caseCose) {
		return caseCodeBindDao.checkCaseCodeExist(caseCose);
	}

	@Override
	public void saveCaseCode(Inventor_BoxCode ib) {
		caseCodeBindDao.saveCaseCode(ib);
		
	}
}
