package com.ltmcp.mobile.dao;

import java.sql.Timestamp;



public interface Dbc_BagCodeBindDao {
	/**
	 * 检测袋子二维码是否已经存在
	 * */
	public boolean checkBagcodeExist(String bagCode);
	public void changeBagCodeStatusInRegister(String code, String name,Timestamp ts);//再次扫描袋子二维码（工厂装箱）
	public boolean checkBagcodeStaAndBagCode(String code);//已经被箱子关联的袋子
	/***
	 * 检测箱子二维码是否存在dbc_bagcodebind
	 * @param caseCode 箱子二维码
	 * @return true or fase
	 */
	public boolean checkCasecodeExist(String caseCode);
	/***
	 * 当重复扫描袋子二维码，并且还没有关联到箱子二维码的时候，判断重复扫袋子二维码
	 * @param bagCode 袋子二维码
	 * @return
	 */
	public boolean multipleScan(String bagCode);
	
}
