package com.ltmcp.entity;

import java.sql.Timestamp;

public class Second_order {
	private Integer id;
	private String second_fh_person;//������
	private String second_fh_address;//������ַ
	private String box_code;//���Ӷ�ά��
	private Integer box_count;//ÿһ�����д�������
	private String receive_sh_person;//�ջ���
	private String receive_sh_addressInStation;//վ���ջ�
	private String receive_sh_address;//�ջ���ַ
	private Integer receive_sh_station;//�ջ�״̬
	private String  bag_code;//���Ӷ�ά��
	private Integer bag_code_count;//ÿһ��������Գɹ��Ķ�ά�������
	private Timestamp fhtime;//ʱ��
	public String getReceive_sh_addressInStation() {
		return receive_sh_addressInStation;
	}
	public void setReceive_sh_addressInStation(String receive_sh_addressInStation) {
		this.receive_sh_addressInStation = receive_sh_addressInStation;
	}
	public Timestamp getFhtime() {
		return fhtime;
	}
	public void setFhtime(Timestamp fhtime) {
		this.fhtime = fhtime;
	}
	public Integer getReceive_sh_station() {
		return receive_sh_station;
	}
	public void setReceive_sh_station(Integer receive_sh_station) {
		this.receive_sh_station = receive_sh_station;
	}
	public String getBox_code() {
		return box_code;
	}
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	public Integer getBox_count() {
		return box_count;
	}
	public void setBox_count(Integer box_count) {
		this.box_count = box_count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSecond_fh_person() {
		return second_fh_person;
	}
	public void setSecond_fh_person(String second_fh_person) {
		this.second_fh_person = second_fh_person;
	}
	public String getSecond_fh_address() {
		return second_fh_address;
	}
	public void setSecond_fh_address(String second_fh_address) {
		this.second_fh_address = second_fh_address;
	}
	public String getReceive_sh_person() {
		return receive_sh_person;
	}
	public void setReceive_sh_person(String receive_sh_person) {
		this.receive_sh_person = receive_sh_person;
	}
	public String getReceive_sh_address() {
		return receive_sh_address;
	}
	public void setReceive_sh_address(String receive_sh_address) {
		this.receive_sh_address = receive_sh_address;
	}
	public String getBag_code() {
		return bag_code;
	}
	public void setBag_code(String bag_code) {
		this.bag_code = bag_code;
	}
	public Integer getBag_code_count() {
		return bag_code_count;
	}
	public void setBag_code_count(Integer bag_code_count) {
		this.bag_code_count = bag_code_count;
	}
	
	
	
	
}
