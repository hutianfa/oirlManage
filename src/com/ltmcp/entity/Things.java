package com.ltmcp.entity;

import java.sql.Timestamp;

public class Things {

	private Integer id;
	
	private Timestamp time; //封签入库时间
	
	private Integer inNum;//封签入库的数量
	
	private Integer outNum;//封签出库的数量
	
	private String beNum;//封签的起始标号
	
	private String enNum;//标签的结束标号
	
	private Integer tag;//封签的标记
	
	private Integer position;//位置信息
	
	private String perName;//操作人姓名
	
	private Integer company;//公司信息
	
	private Integer totalNum;//总共入库数
	
	private Integer areaid;//区id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getInNum() {
		return inNum;
	}
	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}
	public Integer getOutNum() {
		return outNum;
	}
	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	public String getBeNum() {
		return beNum;
	}
	public void setBeNum(String beNum) {
		this.beNum = beNum;
	}
	public String getEnNum() {
		return enNum;
	}
	public void setEnNum(String enNum) {
		this.enNum = enNum;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
}
