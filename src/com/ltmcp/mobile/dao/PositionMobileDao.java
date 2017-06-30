package com.ltmcp.mobile.dao;

import com.ltmcp.entity.DW;
import com.ltmcp.entity.Position;

public interface PositionMobileDao {
	public Integer queryPositionId(String name,String password,String cardNumber,String phoneMac);
	
	/**
	 * ����λ��id��ѯ λ������
	 * @param posId
	 * @return
	 */
	public String queryPosiName(Integer posId);
	
	public Position queryPosi(Integer posId);
	
	
	//����λ��������Ϣд�����ݿ�
//	public void insertDW(String success,String phoneMac,Integer id,String posiName,String cardNumber,String appNum,Integer comId);
	
	public void insertDW(DW dw);
	
	public void reInsertDW(DW dw);
	/*
	 * ��ѯоƬID�Ƿ���ϵͳ��ע�ᣬע�᷵��1�����ذ󶨵��Ϳ����վ������  δע�᷵��0
	 * @param cardNumber
	 * @return
	 *
	*public Integer queryCardNumExist(String cardNumber);
	
	*public List<Position> queryCardNumList(String cardNumber);
*/
	public DW queryDWByImei(String phoneMac);
}
