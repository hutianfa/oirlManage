package com.ltmcp.entity;

import java.sql.Timestamp;

public class Errors {

	private Integer id;  // ��id
	private String code; // ��Ӧ�Ķ�ά��
	private String returnNum; // app ���ص��쳣���͵ķ�����
	private String username;  // ��ǰ�û���
	private String posid; //λ��id
	private String appNum; //app �汾��
	private Timestamp time;// �ύ ��ʱ��
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
