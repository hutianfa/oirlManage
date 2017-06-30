package com.ltmcp.entity;

import java.io.Serializable;

public class Limt  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int id;
	private String limitime; //定位操作的最长时间
	private String stat; //用作app录视频的时间限制
	private String frelimit;// 需要授权的最长时间
	private String appNum;//最新app的版本号
	
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
