package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Position;

public interface PositionMobileBiz {
	
	/**
	 * 根据用户名验证是否当前位置相匹配
	 * @param name 账号
	 * @param password 密码
	 * @param cardNumber 卡号ID
	 * @return -1 获取位置信息错误
	 * 		   >0 位置ID
	 */
	public Integer validationPositionCardNumber(String name,String password,String cardNumber,String phoneMac,String appNum);
	
}
