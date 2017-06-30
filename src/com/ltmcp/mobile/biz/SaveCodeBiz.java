package com.ltmcp.mobile.biz;

import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;

public interface SaveCodeBiz {
 
	/**
	 * ע��
	 */
	public void addCode(DimensionalBarCode code);
	/**
	 * �󶨴���
	 */
	public void addBagCode(String bagcode,String name);
	/**
	 * ����
	 */
	public void saveCodeBag(Dbc_BagCodeBind bacb);
	
	/**
	 * ������
	 */
	public boolean checkBagCodeInDBC(String code);
	
}
