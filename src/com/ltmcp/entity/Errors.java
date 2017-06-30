package com.ltmcp.entity;

import java.sql.Timestamp;

public class Errors {

	private Integer id;  // 表id
	private String code; // 对应的二维码
	private String returnNum; // app 返回的异常类型的返回码
	private String username;  // 当前用户名
	private String posid; //位置id
	private String appNum; //app 版本号
	private Timestamp time;// 提交 的时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getAppNum() {
		return appNum;
	}
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
}
