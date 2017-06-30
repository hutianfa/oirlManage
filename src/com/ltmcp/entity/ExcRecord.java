package com.ltmcp.entity;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * ExcRecord entity. @author MyEclipse Persistence Tools
 */

public class ExcRecord implements java.io.Serializable {

	private Integer excId;  //表id
	
	private BasDict basDict; // 关联的BasDict表
	
	private Freeze freeze;  //关联的解封信息表
	 
	private Sealed sealed;  //关联的施封信息表
	
	private Company company; //关联的公司表
	
	private String excDesc;  //异常描述
	
	private Timestamp excDate;  //异常产生的时间
	
	private String comName; //公司名称
	
	private Integer excStatus; // 异常处理状态
	// 新加字段
	private String excHandleMethod; //处理意见

	public ExcRecord() {
	}

	/** minimal constructor */
	public ExcRecord(BasDict basDict, Sealed sealed, Company company,
			Timestamp excDate, Integer excStatus) {
		this.basDict = basDict;
		this.sealed = sealed;
		this.company = company;
		this.excDate = excDate;
		this.excStatus = excStatus;
	}

	/** full constructor */
	public ExcRecord(BasDict basDict, Freeze freeze, Sealed sealed,
			Company company, String excDesc, Timestamp excDate, String comName,
			Integer excStatus, String HandleMethod) {
		this.basDict = basDict;
		this.freeze = freeze;
		this.sealed = sealed;
		this.company = company;
		this.excDesc = excDesc;
		this.excDate = excDate;
		this.comName = comName;
		this.excStatus = excStatus;
		this.excHandleMethod = excHandleMethod;
	}

	// Property accessors

	public Integer getExcId() {
		return this.excId;
	}

	public void setExcId(Integer excId) {
		this.excId = excId;
	}

	public BasDict getBasDict() {
		return this.basDict;
	}

	public void setBasDict(BasDict basDict) {
		this.basDict = basDict;
	}

	public Freeze getFreeze() {
		return this.freeze;
	}

	public void setFreeze(Freeze freeze) {
		this.freeze = freeze;
	}

	public Sealed getSealed() {
		return this.sealed;
	}

	public void setSealed(Sealed sealed) {
		this.sealed = sealed;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getExcDesc() {
		return this.excDesc;
	}

	public void setExcDesc(String excDesc) {
		this.excDesc = excDesc;
	}

	public Timestamp getExcDate() {
		return this.excDate;
	}

	public void setExcDate(Timestamp excDate) {
		this.excDate = excDate;
	}

	public String getComName() {
		return this.comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer getExcStatus() {
		return this.excStatus;
	}

	public void setExcStatus(Integer excStatus) {
		this.excStatus = excStatus;
	}

	// 新添字段
	public String getExcHandleMethod() {

		return excHandleMethod;
	}

	public void setExcHandleMethod(String excHandleMethod) {
		this.excHandleMethod = excHandleMethod;
	}

	@Override
	public String toString() {
		return "excId=" + excId + ", basDict=" + basDict
				+ ", freeze=" + freeze + ", sealed=" + sealed + ", company="
				+ company + ", excDesc=" + excDesc + ", excDate=" + excDate
				+ ", comName=" + comName + ", excStatus=" + excStatus
				+ ", excHandleMethod=" + excHandleMethod;
	}

}