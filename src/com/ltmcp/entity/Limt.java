package com.ltmcp.entity;

import java.io.Serializable;

public class Limt  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int id;
	private String limitime; //��λ�������ʱ��
	private String stat; //����app¼��Ƶ��ʱ������
	private String frelimit;// ��Ҫ��Ȩ���ʱ��
	private String appNum;//����app�İ汾��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLimitime() {
		return limitime;
	}
	public void setLimitime(String limitime) {
		this.limitime = limitime;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getFrelimit() {
		return frelimit;
	}
	public void setFrelimit(String frelimit) {
		this.frelimit = frelimit;
	}
	public String getAppNum() {
		return appNum;
	}
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	
	
}
