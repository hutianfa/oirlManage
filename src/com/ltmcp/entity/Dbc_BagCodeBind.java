package com.ltmcp.entity;

import java.sql.Timestamp;

public class Dbc_BagCodeBind {
	private Integer id;//袋子二维码表里面的id
	private String bag_code;//袋子二维码
	private Integer status;//袋子二维码的状态（备用）
	private Timestamp regist_time; //添加时间
	private String regist_name;//账号名
	private String box_code;//箱子二维码
	
	public Timestamp getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Timestamp regist_time) {
		this.regist_time = regist_time;
	}
	public String getRegist_name() {
		return regist_name;
	}
	public void setRegist_name(String regist_name) {
		this.regist_name = regist_name;
	}
	public String getBox_code() {
		return box_code;
	}
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBag_code() {
		return bag_code;
	}
	public void setBag_code(String bag_code) {
		this.bag_code = bag_code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
