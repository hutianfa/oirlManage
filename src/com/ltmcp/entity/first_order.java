package com.ltmcp.entity;

import java.sql.Timestamp;

public class first_order {
	private Integer id;//���ҷ���������id
	private String box_code;//���Ӷ�ά��
	private Integer status;//����״̬
	private String fahuo_person;//������
	private String fahuo_phone;//�����˵绰
	private String fahuo_address;//������ַ
	private Integer fahuo_number;//����������Ҳ����ͨ��web���µ��ӵ�����������ǧΪ��λ��
	private Integer bagcode_number;//�ܵĴ�������
	private String shouhuo_person;//�ջ���
	private String shouhuo_phone;//�ջ�����ϵ��ʽ
	private String shouhuo_address;//�ջ��˵�ַ
	private String tracking_number;//�˵��š���ѯ�ö��������
	private String order_number;//�����ţ��µ���ʱ���Զ����ɣ�
	private Timestamp xiadan_time;//�µ�ʱ��
	private String fahuo_time;//����ʱ��
	private String shouhuo_time;//�ջ�ʱ��
	private String comname;//�����ֹ�˾����
	private Integer comid;//�����ֹ�˾id
	
	
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public Integer getComid() {
		return comid;
	}
	public void setComid(Integer comid) {
		this.comid = comid;
	}
	public Timestamp getXiadan_time() {
		return xiadan_time;
	}
	public void setXiadan_time(Timestamp xiadan_time) {
		this.xiadan_time = xiadan_time;
	}
	public String getFahuo_time() {
		return fahuo_time;
	}
	public void setFahuo_time(String fahuo_time) {
		this.fahuo_time = fahuo_time;
	}
	public String getShouhuo_time() {
		return shouhuo_time;
	}
	public void setShouhuo_time(String shouhuo_time) {
		this.shouhuo_time = shouhuo_time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBox_code() {
		return box_code;
	}
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFahuo_person() {
		return fahuo_person;
	}
	public void setFahuo_person(String fahuo_person) {
		this.fahuo_person = fahuo_person;
	}
	public String getFahuo_phone() {
		return fahuo_phone;
	}
	public void setFahuo_phone(String fahuo_phone) {
		this.fahuo_phone = fahuo_phone;
	}
	public String getFahuo_address() {
		return fahuo_address;
	}
	public void setFahuo_address(String fahuo_address) {
		this.fahuo_address = fahuo_address;
	}
	public Integer getFahuo_number() {
		return fahuo_number;
	}
	public void setFahuo_number(Integer fahuo_number) {
		this.fahuo_number = fahuo_number;
	}
	public Integer getBagcode_number() {
		return bagcode_number;
	}
	public void setBagcode_number(Integer bagcode_number) {
		this.bagcode_number = bagcode_number;
	}
	public String getShouhuo_person() {
		return shouhuo_person;
	}
	public void setShouhuo_person(String shouhuo_person) {
		this.shouhuo_person = shouhuo_person;
	}
	public String getShouhuo_phone() {
		return shouhuo_phone;
	}
	public void setShouhuo_phone(String shouhuo_phone) {
		this.shouhuo_phone = shouhuo_phone;
	}
	public String getShouhuo_address() {
		return shouhuo_address;
	}
	public void setShouhuo_address(String shouhuo_address) {
		this.shouhuo_address = shouhuo_address;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	
}
