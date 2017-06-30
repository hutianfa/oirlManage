package com.ltmcp.mobile.action;

import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.PositionExamineMobileBiz;

public class PositionExamineMobileAction extends BaseAction{

	private static final long serialVersionUID = 5948560521293358484L;
	
	private String longitude;
	private String latitude;
	private String name;
	private String type;
	private String cardNumber;
	private PositionExamineMobileBiz positionExamineMobileBiz;
	private String positionAccount;
	private String positionPassword;
	
	public void add(){
		boolean result=false;
		try {
			result = positionExamineMobileBiz.add(longitude, latitude, name,
					type, cardNumber, positionAccount, positionPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result){
			super.getPringWriter().print(0);
		}else{
			super.getPringWriter().print(1);
		}
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public PositionExamineMobileBiz getPositionExamineMobileBiz() {
		return positionExamineMobileBiz;
	}

	public void setPositionExamineMobileBiz(
			PositionExamineMobileBiz positionExamineMobileBiz) {
		this.positionExamineMobileBiz = positionExamineMobileBiz;
	}

	public String getPositionAccount() {
		return positionAccount;
	}

	public void setPositionAccount(String positionAccount) {
		this.positionAccount = positionAccount;
	}

	public String getPositionPassword() {
		return positionPassword;
	}

	public void setPositionPassword(String positionPassword) {
		this.positionPassword = positionPassword;
	}
	
	
}
