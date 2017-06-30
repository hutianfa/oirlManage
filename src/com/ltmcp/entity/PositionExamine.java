package com.ltmcp.entity;

import java.sql.Timestamp;

/**
 * PositionExamine entity. @author MyEclipse Persistence Tools
 */

public class PositionExamine implements java.io.Serializable {

	// Fields

	private Integer id;
	private PositionExamineUser positionExamineUser;
	private Company company; //公司表
	private String longitude;//经度
	private String latitude;//纬度
	private Timestamp time;//审核时间
	private String cardNumber;//车牌号
	private String name;//用户名
	private String type;//类型
	private Integer state;//状态

	// Constructors

	/** default constructor */
	public PositionExamine() {
	}

	/** full constructor */
	public PositionExamine(PositionExamineUser positionExamineUser,
			Company company, String longitude, String latitude, Timestamp time,
			String cardNumber, String name, String type) {
		this.positionExamineUser = positionExamineUser;
		this.company = company;
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.cardNumber = cardNumber;
		this.name = name;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PositionExamineUser getPositionExamineUser() {
		return this.positionExamineUser;
	}

	public void setPositionExamineUser(PositionExamineUser positionExamineUser) {
		this.positionExamineUser = positionExamineUser;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

}