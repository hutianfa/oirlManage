package com.ltmcp.entity;

import java.sql.Timestamp;

public class DW {

	private int id;  //��id
	private int pid; //λ��id
	private int comid; //��˾id
	private String pname; //λ������
	private String success; //��λ״̬
	private String nfc; //nfc ��Ƭ�����к�
	private Timestamp time; //��λ��ʱ��
	private String  imei;// ��λʹ�õ��� imei
	private String appNum; //��ǰapp�İ汾��
	
	
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
