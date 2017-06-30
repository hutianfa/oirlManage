package com.ltmcp.entity;

import java.sql.Timestamp;

public class DW {

	private int id;  //表id
	private int pid; //位置id
	private int comid; //公司id
	private String pname; //位置名称
	private String success; //定位状态
	private String nfc; //nfc 卡片的序列号
	private Timestamp time; //定位的时间
	private String  imei;// 定位使用的手 imei
	private String appNum; //当前app的版本号
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getNfc() {
		return nfc;
	}
	public void setNfc(String nfc) {
		this.nfc = nfc;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAppNum() {
		return appNum;
	}
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	public int getComid() {
		return comid;
	}
	public void setComid(int comid) {
		this.comid = comid;
	}
	
}
