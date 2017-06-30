package com.ltmcp.mobile.action;

import java.io.IOException;
import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.PositionMobileBiz;

public class PositionMobileAction extends BaseAction {

	private String cardNumber;

	private String name;

	private String password;
	
	private String phoneMac;
	
	private String appNum;

	private PositionMobileBiz positionMobileBiz;

	/**
	 * —È÷§ø®∆¨ID
	 */
	public void getPositionId() {
		Integer result = positionMobileBiz.validationPositionCardNumber(name,password, cardNumber,phoneMac,appNum);
		try {
			super.getResponse().getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PositionMobileBiz getPositionMobileBiz() {
		return positionMobileBiz;
	}

	public void setPositionMobileBiz(PositionMobileBiz positionMobileBiz) {
		this.positionMobileBiz = positionMobileBiz;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

	public String getAppNum() {
		return appNum;
	}

	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	
	
}
