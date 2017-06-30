package com.ltmcp.entity;

import java.sql.Timestamp;

public class Area_manager_inventory {
	
	private Integer id; //id
	private String manage_person;//二级分公司发送到片区经理，片区经理的姓名
	private String manage_address;//片区经理所在地点
	private Integer second_order_id;//订单id（二级分公司发货物的订单id，暂时不考虑）
	private String box_code;//箱子二维码
	private Integer box_code_count;//每一个箱子中袋装数量（?0）
	private Integer status;//状态
	private String bag_code;//袋子二维码
	private Integer bag_code_count;//每一袋中的数量（50）
	private Timestamp nowtime;//领取时间（下单时间）
	public String getManage_address() {
		return manage_address;
	}
	public void setManage_address(String manage_address) {
		this.manage_address = manage_address;
	}
	public Timestamp getNowtime() {
		return nowtime;
	}
	public void setNowtime(Timestamp nowtime) {
		this.nowtime = nowtime;
	}
	public Integer getBox_code_count() {
		return box_code_count;
	}
	public void setBox_code_count(Integer box_code_count) {
		this.box_code_count = box_code_count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getManage_person() {
		return manage_person;
	}
	public void setManage_person(String manage_person) {
		this.manage_person = manage_person;
	}
	public Integer getSecond_order_id() {
		return second_order_id;
	}
	public void setSecond_order_id(Integer second_order_id) {
		this.second_order_id = second_order_id;
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
