package com.ltmcp.entity;

import java.sql.Timestamp;

/**
 * AreaThings entity. @author MyEclipse Persistence Tools
 */

public class AreaThingsTotal implements java.io.Serializable {

	// Fields

	private Integer id;
	private String admName; // ˭��ӵ������Ϣ
	private Timestamp time; // ��ӵ�ʱ��
	private Integer areaId; // ����id
	private Integer comId; //�����Ĺ�˾id
	private Integer inNum; // �����Ϣ
	private String status; // ���״̬
	private String detailName; //˭�����ɷ���
	private int shengyuNum;  //���ʣ����
	
	
	public int getShengyuNum() {
		return shengyuNum;
	}
	public void setShengyuNum(int shengyuNum) {
		this.shengyuNum = shengyuNum;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdmName() {
		return this.admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getComId() {
		return this.comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getInNum() {
		return this.inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetailName() {
		return this.detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

}