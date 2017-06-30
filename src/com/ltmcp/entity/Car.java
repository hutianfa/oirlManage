package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Car entity. @author MyEclipse Persistence Tools
 */

public class Car implements java.io.Serializable {

	// Fields

	private Integer carId; //车的id
	private Admin admin; //关联的 admin信息表
	private Company company; //关联的 公司表
	private String carFlapper; //车牌号
	private String carFlag; //车票号
	private Timestamp carCreatetime; //车辆田间时间
	private String adminName;//添加人的姓名
	private String comName;//公司名称
	private Integer carStatus;//车辆当前的状态
	private Integer CarFixFlag; //？？
	private Integer CarUnFixFlag;//？？
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