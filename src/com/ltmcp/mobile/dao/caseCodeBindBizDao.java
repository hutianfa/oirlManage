package com.ltmcp.mobile.dao;
import com.ltmcp.entity.Inventor_BoxCode;



public interface caseCodeBindBizDao {
	/**
	 * ����app�˴�������Ӷ�ά�����жϸ����Ӷ�ά���Ƿ��Ѿ�����
	 *@param ���Ӷ�ά��
	 */
	public boolean checkCaseCodeExist(String caseCode); 
	/**
	 * ���Ӷ�ά��󶨴���֮��д������У�����ע������������д�����
	 * @param ���Ӷ���
	 */
	public void saveCaseCode(Inventor_BoxCode ib);
	
}
