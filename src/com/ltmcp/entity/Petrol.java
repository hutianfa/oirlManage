package com.ltmcp.entity;

import java.sql.Timestamp;

public class Petrol {

	private Integer id;//
	private Integer com; //comid
	private String carFlaper;// ����
	private Double petrol_total;//�͵���������
	private Double petrol_loss;////������
	private Timestamp time;// ������ӵ�ʱ��
	private String comName;//��˾����
	private String sea_oilpin;//��Ʒ��Ϣ
	private Integer petrol_sea_id;//��Ʒid
	private String tiyou;//���͵ĵط�
	private String shouyou;//���͵ĵط�
	private Double shengyu;//ʣ�����
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCom() {
		return com;
	}
	public void setCom(Integer com) {
		this.com = com;
	}
	public String getCarFlaper() {
		return carFlaper;
	}
	public void setCarFlaper(String carFlaper) {
		this.carFlaper = carFlaper;
	}
	public Double getPetrol_total() {
		return petrol_total;
	}
	public void setPetrol_total(Double petrol_total) {
		this.petrol_total = petrol_total;
	}
	public Double getPetrol_loss() {
		return petrol_loss;
	}
	public void setPetrol_loss(Double petrol_loss) {
		this.petrol_loss = petrol_loss;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getSea_oilpin(){		
		return sea_oilpin;
	}
	public void setSea_oilpin(String sea_oilpin) {
		this.sea_oilpin = sea_oilpin;
	}
	public Integer getPetrol_sea_id() {
		return petrol_sea_id;
	}
	public void setPetrol_sea_id(Integer petrol_sea_id) {
		this.petrol_sea_id = petrol_sea_id;
	}
	public String getTiyou() {
		return tiyou;
	}
	public void setTiyou(String tiyou) {
		this.tiyou = tiyou;
	}
	public String getShouyou() {
		return shouyou;
	}
	public void setShouyou(String shouyou) {
		this.shouyou = shouyou;
	}
	public Double getShengyu() {
		return shengyu;
	}
	public void setShengyu(Double shengyu) {
		this.shengyu = shengyu;
	}
	
}
