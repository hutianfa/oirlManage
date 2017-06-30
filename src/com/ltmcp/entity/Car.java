package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Car entity. @author MyEclipse Persistence Tools
 */

public class Car implements java.io.Serializable {

	// Fields

	private Integer carId; //����id
	private Admin admin; //������ admin��Ϣ��
	private Company company; //������ ��˾��
	private String carFlapper; //���ƺ�
	private String carFlag; //��Ʊ��
	private Timestamp carCreatetime; //�������ʱ��
	private String adminName;//����˵�����
	private String comName;//��˾����
	private Integer carStatus;//������ǰ��״̬
	private Integer CarFixFlag; //����
	private Integer CarUnFixFlag;//����
	private Set freezes = new HashSet(0);
	private Set sealeds = new HashSet(0);


	/** default constructor */
	public Car() {
	}

	/** minimal constructor */
	public Car(Admin admin, Company company, String carFlapper,
			Timestamp carCreatetime, Integer carStatus,Integer CarFixFlag,Integer CarUnFixFlag) {
		this.admin = admin;
		this.company = company;
		this.carFlapper = carFlapper;
		this.carCreatetime = carCreatetime;
		this.carStatus = carStatus;
		this.CarFixFlag = CarFixFlag;
		this.CarUnFixFlag = CarUnFixFlag;
	}

	/** full constructor */
	public Car(Admin admin, Company company, String carFlapper, String carFlag,
			Timestamp carCreatetime, String adminName, String comName,
			Integer carStatus, Set freezes, Set sealeds,Integer CarFixFlag,Integer CarUnFixFlag) {
		this.admin = admin;
		this.company = company;
		this.carFlapper = carFlapper;
		this.carFlag = carFlag;
		this.carCreatetime = carCreatetime;
		this.adminName = adminName;
		this.comName = comName;
		this.carStatus = carStatus;
		this.freezes = freezes;
		this.sealeds = sealeds;
		this.CarFixFlag = CarFixFlag;
		this.CarUnFixFlag = CarUnFixFlag;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCarFlapper() {
		return this.carFlapper;
	}

	public void setCarFlapper(String carFlapper) {
		this.carFlapper = carFlapper;
	}

	public String getCarFlag() {
		return this.carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}

	public Timestamp getCarCreatetime() {
		return this.carCreatetime;
	}

	public void setCarCreatetime(Timestamp carCreatetime) {
		this.carCreatetime = carCreatetime;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer getCarStatus() {
		return this.carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public Set getFreezes() {
		return this.freezes;
	}

	public void setFreezes(Set freezes) {
		this.freezes = freezes;
	}

	public Set getSealeds() {
		return this.sealeds;
	}

	public void setSealeds(Set sealeds) {
		this.sealeds = sealeds;
	}

	public Integer getCarFixFlag() {
		return CarFixFlag;
	}

	public void setCarFixFlag(Integer carFixFlag) {
		CarFixFlag = carFixFlag;
	}

	public Integer getCarUnFixFlag() {
		return CarUnFixFlag;
	}

	public void setCarUnFixFlag(Integer carUnFixFlag) {
		CarUnFixFlag = carUnFixFlag;
	}
	

	
}