package com.ltmcp.entity;

public class Picture {
	
	private int id;
	private String picPath; //ͼƬ�ı���·��
	private String wayNum;// �˵����
	private int comid;//������˾
	
	
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
