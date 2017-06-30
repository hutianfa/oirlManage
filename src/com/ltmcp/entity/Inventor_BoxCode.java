package com.ltmcp.entity;

public class Inventor_BoxCode {
	private Integer id;//箱子表（也就是库存表的id）
	private String box_code;//箱子二维码
	private Integer status;//状态（暂时设计，但是一定会用到）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBox_code() {
		return box_code;
	}
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
