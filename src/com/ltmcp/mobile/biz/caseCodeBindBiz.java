package com.ltmcp.mobile.biz;
import com.ltmcp.entity.Inventor_BoxCode;
public interface caseCodeBindBiz {
/**
 * ����app�˴�������Ӷ�ά�����жϸ����Ӷ�ά���Ƿ��Ѿ�����
 *@param ���Ӷ�ά��
 */
	public boolean checkCaseCodeExist(String caseCose);
	/**
	 * ���Ӷ�ά��󶨴���֮��д�������
	 * @param ���ӱ���Ϊ����
	 */
	public void saveCaseCode(Inventor_BoxCode ib);
}
