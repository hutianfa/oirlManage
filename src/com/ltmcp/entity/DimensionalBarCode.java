package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * DimensionalBarCode entity. @author MyEclipse Persistence Tools
 */

public class DimensionalBarCode implements java.io.Serializable {

	// Fields

	private Integer id;
//	private String content;
//	private Integer status;
	private String freeze_content;  //施封二维码 信息
	private Integer freeze_status; //施封二维码 使用状态
	private String unfreeze_content; // 解封二维码的内容
	private Integer unfreeze_status; // 解封二维码的状态
	private String regist_name;//注册二维码登录账号名称
	private String bag_code;


	

	private Set freezes = new HashSet(0);
	private Set sealeds = new HashSet(0);
	//添加时间
	private Timestamp registime;

	// Constructors

	/** default constructor */
	public DimensionalBarCode() {
	}

	/** minimal constructor */
//	public DimensionalBarCode(String content, Integer status) {
//		this.content = content;
//		this.status = status;
//	}
//
//	/** full constructor */
//	public DimensionalBarCode(String content, Integer status, Set freezes,
//			Set sealeds) {
//		this.content = content;
//		this.status = status;
//		this.freezes = freezes;
//		this.sealeds = sealeds;
//	}

	// Property accessors

	
	public Integer getId() {
		return this.id;
	}

	public Timestamp getRegistime() {
		return registime;
	}

	public void setRegistime(Timestamp registime) {
		this.registime = registime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public Integer getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}

	
	
	public Set getFreezes() {
		return this.freezes;
	}
	
	public DimensionalBarCode(String freeze_content,
			Integer freeze_status, String unfreeze_content,
			Integer unfreeze_status) {
		super();
		this.freeze_content = freeze_content;
		this.freeze_status = freeze_status;
		this.unfreeze_content = unfreeze_content;
		this.unfreeze_status = unfreeze_status;
	}

	public DimensionalBarCode(String freeze_content,
		Integer freeze_status, String unfreeze_content,
		Integer unfreeze_status, Set freezes, Set sealeds) {
	super();
	this.freeze_content = freeze_content;
	this.freeze_status = freeze_status;
	this.unfreeze_content = unfreeze_content;
	this.unfreeze_status = unfreeze_status;
	this.freezes = freezes;
	this.sealeds = sealeds;
}

	public String getFreeze_content() {
		return freeze_content;
	}

	public void setFreeze_content(String freeze_content) {
		this.freeze_content = freeze_content;
	}

	public Integer getFreeze_status() {
		return freeze_status;
	}

	public void setFreeze_status(Integer freeze_status) {
		this.freeze_status = freeze_status;
	}

	public String getUnfreeze_content() {
		return unfreeze_content;
	}

	public void setUnfreeze_content(String unfreeze_content) {
		this.unfreeze_content = unfreeze_content;
	}

	public Integer getUnfreeze_status() {
		return unfreeze_status;
	}

	public void setUnfreeze_status(Integer unfreeze_status) {
		this.unfreeze_status = unfreeze_status;
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
	public String getRegist_name() {
		return regist_name;
	}

	public void setRegist_name(String regist_name) {
		this.regist_name = regist_name;
	}
	public String getBag_code() {
		return bag_code;
	}

	public void setBag_code(String bag_code) {
		this.bag_code = bag_code;
	}

}