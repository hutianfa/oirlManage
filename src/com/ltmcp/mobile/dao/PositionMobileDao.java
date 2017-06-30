package com.ltmcp.mobile.dao;

import com.ltmcp.entity.DW;
import com.ltmcp.entity.Position;

public interface PositionMobileDao {
	public Integer queryPositionId(String name,String password,String cardNumber,String phoneMac);
	
	/**
	 * 根据位置id查询 位置名称
	 * @param posId
	 * @return
	 */
	public String queryPosiName(Integer posId);
	
	public Position queryPosi(Integer posId);
	
	
	//将定位产生的信息写入数据库
//	public void insertDW(String success,String phoneMac,Integer id,String posiName,String cardNumber,String appNum,Integer comId);
	
	public void insertDW(DW dw);
	
	public void reInsertDW(DW dw);
	/*
	 * 查询芯片ID是否在系统中注册，注册返回1并返回绑定的油库或油站的名字  未注册返回0
	 * @param cardNumber
	 * @return
	 *
	*public Integer queryCardNumExist(String cardNumber);
	
	*public List<Position> queryCardNumList(String cardNumber);
*/
	public DW queryDWByImei(String phoneMac);
}
