package com.ltmcp.mobile.biz.impl;


import com.ltmcp.mobile.biz.Dbc_BagCodeBindBiz;
import com.ltmcp.mobile.dao.Dbc_BagCodeBindDao;


public class Dbc_BagCodeBindBizImpl implements Dbc_BagCodeBindBiz{
	private Dbc_BagCodeBindDao dbc_BagCodeBindDao;
	@Override
	public boolean checkBagcodeExist(String bagCode) {
		return dbc_BagCodeBindDao.checkBagcodeExist(bagCode);//增加的是有返回值的，需要return袋子二维码
	}

	public Dbc_BagCodeBindDao getDbc_BagCodeBindDao() {
		return dbc_BagCodeBindDao;
	}

	public void setDbc_BagCodeBindDao(Dbc_BagCodeBindDao dbc_BagCodeBindDao) {
		this.dbc_BagCodeBindDao = dbc_BagCodeBindDao;
	}
	public boolean checkBagcodeStaAndBagCode(String code){
		return dbc_BagCodeBindDao.checkBagcodeStaAndBagCode(code);
	}

	@Override
	public boolean checkCasecodeExist(String caseCode) {
		return dbc_BagCodeBindDao.checkCasecodeExist(caseCode);
	}

	@Override
	public boolean multipleScan(String bagCode) {
		// TODO Auto-generated method stub
		return dbc_BagCodeBindDao.multipleScan(bagCode);
	}

}
