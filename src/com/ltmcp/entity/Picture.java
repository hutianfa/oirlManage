package com.ltmcp.entity;

public class Picture {
	
	private int id;
	private String picPath; //图片的保存路径
	private String wayNum;// 运单编号
	private int comid;//所属公司
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getWayNum() {
		return wayNum;
	}
	public void setWayNum(String wayNum) {
		this.wayNum = wayNum;
	}
	public int getComid() {
		return comid;
	}
	public void setComid(int comid) {
		this.comid = comid;
	}
}
