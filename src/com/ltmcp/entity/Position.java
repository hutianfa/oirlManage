package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer posId; //表id
	private Company company;// 关联的公司表
	private BasDict basDict;//关联的字典表
	private String posLongitude;//经度
	private String posLatitude;//纬度
	private String posName;//站点名称
	private String comName;//公司名称
	private Integer areaid;//区域id
	private Timestamp posDate;//站点创建时间
	private Integer posStatus;//站点状态
	private String phoneMac;//手机的imei码
	private String posCardNumber;//车牌号(定位卡？？？)
	private Set freezes = new HashSet(0);
	private Set persons = new HashSet(0);
	private Set sealeds = new HashSet(0);

	/** default constructor */
	public Position() {
	}

	/** minimal constructor */
	public Position(Company company, BasDict basDict, String posLongitude,String posLatitude, Timestamp posDate, Integer posStatus) {
		this.company = company;
		this.basDict = basDict;
		this.posLongitude = posLongitude;
		this.posLatitude = posLatitude;
		this.posDate = posDate;
		this.posStatus = posStatus;
	}

	/** full constructor */
	public Position(Company company, BasDict basDict, String posLongitude,String posLatitude, String posName, String comName,
			Timestamp posDate, Integer posStatus,String posCardNumber,Set freezes, Set persons,Set sealeds) {
		
		this.company = company;
		this.basDict = basDict;
		this.posLongitude = posLongitude;
		this.posLatitude = posLatitude;
		this.posName = posName;
		this.comName = comName;
		this.posDate = posDate;
		this.posStatus = posStatus;
		this.posCardNumber=posCardNumber;
		this.freezes = freezes;
		this.persons = persons;
		this.sealeds = sealeds;
	}

	public Integer getPosId() {
		return this.posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public BasDict getBasDict() {
		return this.basDict;
	}

	public void setBasDict(BasDict basDict) {
		this.basDict = basDict;
	}

	public String getPosLongitude() {
		return this.posLongitude;
	}

	public void setPosLongitude(String posLongitude) {
		this.posLongitude = posLongitude;
	}

	public String getPosLatitude() {
		return this.posLatitude;
	}

	public void setPosLatitude(String posLatitude) {
		this.posLatitude = posLatitude;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Timestamp getPosDate() {
		return this.posDate;
	}

	public void setPosDate(Timestamp posDate) {
		this.posDate = posDate;
	}

	public Integer getPosStatus() {
		return this.posStatus;
	}

	public void setPosStatus(Integer posStatus) {
		this.posStatus = posStatus;
	}

	public Set getFreezes() {
		return this.freezes;
	}

	public void setFreezes(Set freezes) {
		this.freezes = freezes;
	}

	public Set getPersons() {
		return this.persons;
	}

	public void setPersons(Set persons) {
		this.persons = persons;
	}

	public Set getSealeds() {
		return this.sealeds;
	}

	public void setSealeds(Set sealeds) {
		this.sealeds = sealeds;
	}

	public String getPosCardNumber() {
		return posCardNumber;
	}

	public void setPosCardNumber(String posCardNumber) {
		this.posCardNumber = posCardNumber;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}	
}