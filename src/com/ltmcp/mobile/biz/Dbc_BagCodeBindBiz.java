package com.ltmcp.mobile.biz;
import com.ltmcp.entity.Dbc_BagCodeBind;
public interface Dbc_BagCodeBindBiz {
/**
 * �����Ӷ�ά���Ƿ��Ѿ�����
 */
	public boolean checkBagcodeExist(String bagCode);
	/***
	 * ���Ӷ�ά������dbc_bagcodebind�����Ƿ����
	 * @param caseCode ���Ӷ�ά��
	 * @return true or fasle
	 */
	public boolean checkCasecodeExist(String caseCode);
	/***
	 * ���ظ�ɨ����Ӷ�ά�룬���һ�û�й��������Ӷ�ά���ʱ���ж��ظ�ɨ���Ӷ�ά��
	 * @param bagCode ���Ӷ�ά��
	 * @return
	 */
	public boolean multipleScan(String bagCode);
}
