package com.ltmcp.entity;

import java.io.Serializable;

public class Empower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; //��id
	private String name; //��Ȩ���û���
	private String powerCode; // ��Ȩʶ����
	private Integer company; //�����Ĺ�˾
	private String createName; //�����Ȩ�˵����û���
	private String status; // ��ǰ��Ȩ�˵� ״̬
	
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