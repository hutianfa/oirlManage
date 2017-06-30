package com.ltmcp.mobile.dao;

import java.sql.Timestamp;



public interface Dbc_BagCodeBindDao {
	/**
	 * �����Ӷ�ά���Ƿ��Ѿ�����
	 * */
	public boolean checkBagcodeExist(String bagCode);
	public void changeBagCodeStatusInRegister(String code, String name,Timestamp ts);//�ٴ�ɨ����Ӷ�ά�루����װ�䣩
	public boolean checkBagcodeStaAndBagCode(String code);//�Ѿ������ӹ����Ĵ���
	/***
	 * ������Ӷ�ά���Ƿ����dbc_bagcodebind
	 * @param caseCode ���Ӷ�ά��
	 * @return true or fase
	 */
	public boolean checkCasecodeExist(String caseCode);
	/***
	 * ���ظ�ɨ����Ӷ�ά�룬���һ�û�й��������Ӷ�ά���ʱ���ж��ظ�ɨ���Ӷ�ά��
	 * @param bagCode ���Ӷ�ά��
	 * @return
	 */
	public boolean multipleScan(String bagCode);
	
}
