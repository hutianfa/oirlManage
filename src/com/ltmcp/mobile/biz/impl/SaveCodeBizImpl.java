package com.ltmcp.mobile.biz.impl;

import java.sql.Timestamp;

import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.mobile.biz.SaveCodeBiz;
import com.ltmcp.mobile.dao.DimensionalBarCodeMobileDao;
import com.ltmcp.mobile.dao.impl.Dbc_BagCodeBindDaoImpl;
import com.ltmcp.mobile.dao.impl.DimensionalBarCodeMobileDaoImpl;

public class SaveCodeBizImpl implements SaveCodeBiz{
	private DimensionalBarCodeMobileDaoImpl dao;
	
	/**
	 * ע��
	 */
	@Override
	public void addCode(DimensionalBarCode code) {
		dao.saveCode(code);	
	}
	/**
	 * ����ά�루���ӣ�
	 * */
	public boolean checkBagCodeInDBC(String code){
	     return dao.checkBagCodeInDBC(code);
	}
	/**
	 * ����
	 */
	public void saveCodeBag(Dbc_BagCodeBind bacb){
		dao.saveCodeBag(bacb);	
	}
	public DimensionalBarCodeMobileDaoImpl getDao() {
		return dao;
	}
	public void setDao(DimensionalBarCodeMobileDaoImpl dao) {
		this.dao = dao;
	}
	/**
	 * ���Ӱ�50����Գɹ��Ķ�ά�룬����ʹ�õ���Գɹ�֮������
	 * @return
	 */
	
	public void addBagCode(String bagcode, String name){
		dao.saveBagCode(bagcode,name);
	}
	
	public void addCaseCode(String caseCode, String name){
		dao.addCaseCode(caseCode, name);
	}
}
