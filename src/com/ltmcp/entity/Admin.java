package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer admId;  // ��id
	private Role role;//�����Ľ�ɫ��
	private Company company;//�����Ĺ�˾��
	private String admName;//admin���û���
	private String admPassword;//admin������
	private String admTrueName;//admin����ʵ����
	private Integer admSex;//admin���Ա�
	private Timestamp admCreateDate;//admin������ʱ��
	private String admPhone;//admin���ֻ���
	private String admTelephone;//admin���ֻ���
	private String admEmail;//admin������
	private Integer admStatus;//admin��״̬
	private Integer areaId;//admin��Ӧ����id
	private Set cars = new HashSet(0);
	private Set persons = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(Role role, Company company, String admName,
			String admPassword, Timestamp admCreateDate, Integer admStatus,Integer areaId) {
		this.role = role;
		this.company = company;
		this.admName = admName;
		this.admPassword = admPassword;
		this.admCreateDate = admCreateDate;
		this.admStatus = admStatus;
		this.areaId = areaId;
	}

	/** full constructor */
	public Admin(Role role, Company company, String admName,
			String admPassword, String admTrueName, Integer admSex,
			Timestamp admCreateDate, String admPhone, String admTelephone,
			String admEmail, Integer admStatus, Set cars, Set persons,Integer areaId) {
		this.role = role;
		this.company = company;
		this.admName = admName;
		this.admPassword = admPassword;
		this.admTrueName = admTrueName;
		this.admSex = admSex;
		this.admCreateDate = admCreateDate;
		this.admPhone = admPhone;
		this.admTelephone = admTelephone;
		this.admEmail = admEmail;
		this.admStatus = admStatus;
		this.cars = cars;
		this.persons = persons;
		this.areaId = areaId;
	}

	// Property accessors

	public Integer getAdmId() {
		return this.admId;
	}

	public void setAdmId(Integer admId) {
		this.admId = admId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAdmName() {
		return this.admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getAdmPassword() {
		return this.admPassword;
	}

	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}

	public String getAdmTrueName() {
		return this.admTrueName;
	}

	public void setAdmTrueName(String admTrueName) {
		this.admTrueName = admTrueName;
	}

	public Integer getAdmSex() {
		return this.admSex;
	}

	public void setAdmSex(Integer admSex) {
		this.admSex = admSex;
	}

	public Timestamp getAdmCreateDate() {
		return this.admCreateDate;
	}

	public void setAdmCreateDate(Timestamp admCreateDate) {
		this.admCreateDate = admCreateDate;
	}

	public String getAdmPhone() {
		return this.admPhone;
	}

	public void setAdmPhone(String admPhone) {
		this.admPhone = admPhone;
	}

	public String getAdmTelephone() {
		return this.admTelephone;
	}

	public void setAdmTelephone(String admTelephone) {
		this.admTelephone = admTelephone;
	}

	public String getAdmEmail() {
		return this.admEmail;
	}

	public void setAdmEmail(String admEmail) {
		this.admEmail = admEmail;
	}

	public Integer getAdmStatus() {
		return this.admStatus;
	}

	public void setAdmStatus(Integer admStatus) {
		this.admStatus = admStatus;
	}

	public Set getCars() {
		return this.cars;
	}

	public void setCars(Set cars) {
		this.cars = cars;
	}

	public Set getPersons() {
		return this.persons;
	}

	public void setPersons(Set persons) {
		this.persons = persons;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}