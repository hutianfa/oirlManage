package com.ltmcp.entity;

public class Province {

	private Integer id;
	private String com_a_name;//公司名称
	private Integer area_id;//区域id
	private Integer com_id;//公司id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCom_a_name() {
		return com_a_name;
	}
	public void setCom_a_name(String com_a_name) {
		this.com_a_name = com_a_name;
	}
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public Integer getCom_id() {
		return com_id;
	}
	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}
	
	
}
