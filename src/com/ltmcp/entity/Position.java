package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer posId; //��id
	private Company company;// �����Ĺ�˾��
	private BasDict basDict;//�������ֵ��
	private String posLongitude;//����
	private String posLatitude;//γ��
	private String posName;//վ������
	private String comName;//��˾����
	private Integer areaid;//����id
	private Timestamp posDate;//վ�㴴��ʱ��
	private Integer posStatus;//վ��״̬
	private String phoneMac;//�ֻ���imei��
	private String posCardNumber;//���ƺ�(��λ��������)
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