package com.ltmcp.entity;

import java.io.Serializable;

public class Empower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; //表id
	private String name; //授权人用户名
	private String powerCode; // 授权识别码
	private Integer company; //所属的公司
	private String createName; //添加授权人的人用户名
	private String status; // 当前授权人的 状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPowerCode() {
		return powerCode;
	}
	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", powerCode="
				+ powerCode + ", company=" + company + ", createName="
				+ createName + ", status=" + status;
	}
}