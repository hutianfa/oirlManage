package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Position;

public interface PositionMobileBiz {
	
	/**
	 * �����û�����֤�Ƿ�ǰλ����ƥ��
	 * @param name �˺�
	 * @param password ����
	 * @param cardNumber ����ID
	 * @return -1 ��ȡλ����Ϣ����
	 * 		   >0 λ��ID
	 */
	public Integer validationPositionCardNumber(String name,String password,String cardNumber,String phoneMac,String appNum);
	
}
