package com.ltmcp.entity;

import java.sql.Timestamp;

/**
 * AreaThings entity. @author MyEclipse Persistence Tools
 */

public class AreaThings implements java.io.Serializable {

	// Fields

	private Integer id;
	private String admName; //��˭��ӵ���Ϣ
	private Timestamp time; //��ӵ�ʱ��
	private Integer areaId;//��Ӧ������id
	private Integer comId;//�����Ĺ�˾id
	private Integer inNum;//�������
	private String status;//ʮ��������
	private String detailName;//����ʱ��

	// Constructors

	/** default constructor */
	public AreaThings() {
	}

	/** full constructor */
	public AreaThings(String admName, Timestamp time, Integer areaId,
			Integer comId, Integer inNum, String status, String detailName) {
		this.admName = admName;
		this.time = time;
		this.areaId = areaId;
		this.comId = comId;
		this.inNum = inNum;
		this.status = status;
		this.detailName = detailName;
	}

	// Property accessors

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