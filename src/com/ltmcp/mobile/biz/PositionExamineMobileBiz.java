package com.ltmcp.mobile.biz;

public interface PositionExamineMobileBiz {

	/**
	 * ��������Ϣ
	 * @param longitude ����
	 * @param latitude γ��
	 * @param name ����
	 * @param type ����
	 * @param cardNumber NFCid
	 * @return ��ӽ��
	 */
	boolean add(String longitude, String latitude, String name, String type,String cardNumber,String positionAccount,String positionPassword);

}
