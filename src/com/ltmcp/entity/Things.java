package com.ltmcp.entity;

import java.sql.Timestamp;

public class Things {

	private Integer id;
	
	private Timestamp time; //��ǩ���ʱ��
	
	private Integer inNum;//��ǩ��������
	
	private Integer outNum;//��ǩ���������
	
	private String beNum;//��ǩ����ʼ���
	
	private String enNum;//��ǩ�Ľ������
	
	private Integer tag;//��ǩ�ı��
	
	private Integer position;//λ����Ϣ
	
	private String perName;//����������
	
	private Integer company;//��˾��Ϣ
	
	private Integer totalNum;//�ܹ������
	
	private Integer areaid;//��id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getInNum() {
		return inNum;
	}
	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}
	public Integer getOutNum() {
		return outNum;
	}
	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	public String getBeNum() {
		return beNum;
	}
	public void setBeNum(String beNum) {
		this.beNum = beNum;
	}
	public String getEnNum() {
		return enNum;
	}
	public void setEnNum(String enNum) {
		this.enNum = enNum;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
}
