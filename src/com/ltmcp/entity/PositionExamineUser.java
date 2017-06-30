package com.ltmcp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * PositionExamineUser entity. @author MyEclipse Persistence Tools
 */

public class PositionExamineUser implements java.io.Serializable {

	private static final long serialVersionUID = 1883738890766200584L;
	private Integer id;
	private Company company;
	private String positionAccount;
	private String positionPassword;
	private String positionName;
	private Set positionExamines = new HashSet(0);

	// Constructors

	/** default constructor */
	public PositionExamineUser() {
	}

	/** full constructor */
	public PositionExamineUser(Company company, String positionAccount,
			String positionPassword, String positionName, Set positionExamines) {
		this.company = company;
		this.positionAccount = positionAccount;
		this.positionPassword = positionPassword;
		this.positionName = positionName;
		this.positionExamines = positionExamines;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPositionAccount() {
		return this.positionAccount;
	}

	public void setPositionAccount(String positionAccount) {
		this.positionAccount = positionAccount;
	}

	public String getPositionPassword() {
		return this.positionPassword;
	}

	public void setPositionPassword(String positionPassword) {
		this.positionPassword = positionPassword;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Set getPositionExamines() {
		return this.positionExamines;
	}

	public void setPositionExamines(Set positionExamines) {
		this.positionExamines = positionExamines;
	}

}