package com.ltmcp.entity;

public class shoufa_person {
	private Integer id;//收发系统收发人表的id
	private String username;//登录账号
	private String password;//登录密码
	private String name;//登录人姓名
	private Integer com_id;//所属二级
	private Integer areaid;//片区
	private Integer pos_id;//站点
	private String  address;//地址
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCom_id() {
		return com_id;
	}
	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public Integer getPos_id() {
		return pos_id;
	}
	public void setPos_id(Integer pos_id) {
		this.pos_id = pos_id;
	}
	
}
