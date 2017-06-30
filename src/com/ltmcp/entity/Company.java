package com.ltmcp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private Integer comId; //公司id
	private String comName; //公司名称
	private String comAddr; //公司地址
	private String comDesc; //公司描述
	private Set freezes = new HashSet(0);
	private Set cars = new HashSet(0);
	private Set excRecords = new HashSet(0);
	private Set sealeds = new HashSet(0);
	private Set persons = new HashSet(0);
	private Set admins = new HashSet(0);
	private Set positions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String comName) {
		this.comName = comName;
	}

	/** full constructor */
	public Company(String comName, String comAddr, String comDesc, Set freezes,
			Set cars, Set excRecords, Set sealeds, Set persons, Set admins,
			Set positions) {
		this.comName = comName;
		this.comAddr = comAddr;
		this.comDesc = comDesc;
		this.freezes = freezes;
		this.cars = cars;
		this.excRecords = excRecords;
		this.sealeds = sealeds;
		this.persons = persons;
		this.admins = admins;
		this.positions = positions;
	}

	// Property accessors

	public Integer getComId() {
		return this.comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComAddr() {
		return this.comAddr;
	}

	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}

	public String getComDesc() {
		return this.comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public Set getFreezes() {
		return this.freezes;
	}

	public void setFreezes(Set freezes) {
		this.freezes = freezes;
	}

	public Set getCars() {
		return this.cars;
	}

	public void setCars(Set cars) {
		this.cars = cars;
	}

	public Set getExcRecords() {
		return this.excRecords;
	}

	public void setExcRecords(Set excRecords) {
		this.excRecords = excRecords;
	}

	public Set getSealeds() {
		return this.sealeds;
	}

	public void setSealeds(Set sealeds) {
		this.sealeds = sealeds;
	}

	public Set getPersons() {
		return this.persons;
	}

	public void setPersons(Set persons) {
		this.persons = persons;
	}

	public Set getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set admins) {
		this.admins = admins;
	}

	public Set getPositions() {
		return this.positions;
	}

	public void setPositions(Set positions) {
		this.positions = positions;
	}	
}