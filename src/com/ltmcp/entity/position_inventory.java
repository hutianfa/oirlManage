package com.ltmcp.entity;

import java.sql.Timestamp;

public class position_inventory {
	
	private Integer id; //id
	private String position_name;//վ������
	private String bagCode;//���Ӷ�ά��
	private String boxCode;//���Ӷ�ά��
    private Integer state;//״̬
	private Timestamp time;//������վ��ʱ��
	private String position_head;//վ�㸺����
	private Integer ami_id;//��Ӧaera_manager_incentory�е�id�����������ʹ��
	private Integer pianqu_id;//��վ������Ƭ����id
	private Integer bag_count;//ÿһ�������д������������������
	private Integer bag_code_count;//ÿ�������������������
	
	
	
	
	public Integer getBag_count() {
		return bag_count;
	}
	public void setBag_count(Integer bag_count) {
		this.bag_count = bag_count;
	}
	public Integer getBag_code_count() {
		return bag_code_count;
	}
	public void setBag_code_count(Integer bag_code_count) {
		this.bag_code_count = bag_code_count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getPosition_head() {
		return position_head;
	}
	public void setPosition_head(String position_head) {
		this.position_head = position_head;
	}
	public Integer getAmi_id() {
		return ami_id;
	}
	public void setAmi_id(Integer ami_id) {
		this.ami_id = ami_id;
	}
	public Integer getPianqu_id() {
		return pianqu_id;
	}
	public void setPianqu_id(Integer pianqu_id) {
		this.pianqu_id = pianqu_id;
	}
	
	
}
