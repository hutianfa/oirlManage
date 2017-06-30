package com.ltmcp.mobile.biz;

import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;

public interface SaveCodeBiz {
 
	/**
	 * ×¢²á
	 */
	public void addCode(DimensionalBarCode code);
	/**
	 * °ó¶¨´ü×Ó
	 */
	public void addBagCode(String bagcode,String name);
	/**
	 * ´ü×Ó
	 */
	public void saveCodeBag(Dbc_BagCodeBind bacb);
	
	/**
	 * ¼ì²â´ü×Ó
	 */
	public boolean checkBagCodeInDBC(String code);
	
}
