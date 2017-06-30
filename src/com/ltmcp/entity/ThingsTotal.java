package com.ltmcp.entity;

import java.sql.Timestamp;

/**
 * ThingsTotal entity. @author MyEclipse Persistence Tools
 */

public class ThingsTotal implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;//���ʱ��
	private Integer inNum;//�������
	private Integer outNum;//��������
	private String beNum;//��ŵ���ʼ
	private String enNum;//��ŵĿ�ʼ
	private Integer tag;//���
	private Integer position;//λ��id
	private Integer totalNum;//�ܿ��
	private String perName;//��������
	private Integer company;//��صĹ�˾
	private Integer areaid;//����id

	// Constructors

	/** default constructor */
	public ThingsTotal() {
	}

	/** full constructor */
	public ThingsTotal(Timestamp time, Integer inNum, Integer outNum,
			String beNum, String enNum, Integer tag, Integer position,
			Integer totalNum, String perName, Integer company, Integer areaid) {
		this.time = time;
		this.inNum = inNum;
		this.outNum = outNum;
		this.beNum = beNum;
		this.enNum = enNum;
		this.tag = tag;
		this.position = position;
		this.totalNum = totalNum;
		this.perName = perName;
		this.company = company;
		this.areaid = areaid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getInNum() {
		return this.inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public Integer getOutNum() {
		return this.outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public String getBeNum() {
		return this.beNum;
	}

	public void setBeNum(String beNum) {
		this.beNum = beNum;
	}

	public String getEnNum() {
		return this.enNum;
	}

	public void setEnNum(String enNum) {
		this.enNum = enNum;
	}

	public Integer getTag() {
		return this.tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getPerName() {
		return this.perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public Integer getCompany() {
		return this.company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public Integer getAreaid() {
		return this.areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

}