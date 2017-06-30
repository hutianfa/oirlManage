package com.ltmcp.mobile.biz;

public interface PositionExamineMobileBiz {

	/**
	 * 添加审核信息
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param name 名称
	 * @param type 类型
	 * @param cardNumber NFCid
	 * @return 添加结果
	 */
	boolean add(String longitude, String latitude, String name, String type,String cardNumber,String positionAccount,String positionPassword);

}
