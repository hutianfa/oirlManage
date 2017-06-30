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
	 * 注册
	 */
	@Override
	public void addCode(DimensionalBarCode code) {
		dao.saveCode(code);	
	}
	/**
	 * 检测二维码（袋子）
	 * */
	public boolean checkBagCodeInDBC(String code){
	     return dao.checkBagCodeInDBC(code);
	}
	/**
	 * 袋子
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
	 * 袋子绑定50个配对成功的二维码，必须使用的配对成功之后的情况
	 * @return
	 */
	
	public void addBagCode(String bagcode, String name){
		dao.saveBagCode(bagcode,name);
	}
	
	public void addCaseCode(String caseCode, String name){
		dao.addCaseCode(caseCode, name);
	}
}
