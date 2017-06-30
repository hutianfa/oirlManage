package com.ltmcp.entity;

public class PetrolName {
	
	private int id;
	private int com_id;//公司id
	private String you_nfc_id;//nfc 序列号
	private String you_name;// 油品的名称
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public String getYou_nfc_id() {
		return you_nfc_id;
	}
	public void setYou_nfc_id(String you_nfc_id) {
		this.you_nfc_id = you_nfc_id;
	}
	public String getYou_name() {
		return you_name;
	}
	public void setYou_name(String you_name) {
		this.you_name = you_name;
	}
}
