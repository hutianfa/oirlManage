package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;

public class AppNumCondition {
	
	private Integer posId; // λ��ID

	private Integer comId; // ��˾ID----->ͨ���ֳֻ���Ա�Ĺ�˾ID��ֵ

	private Integer areaid;
	
	private Integer perId;

	private String status; // ״̬,0����ɣ�1������;�� 2�������쳣

	private String beginTime; // ��ʼʱ��

	private String endTime; // ����ʱ��\
		
	private Integer BigPosid;//�Ϳ��id
	
	private String carFlapper;//������ app ���°汾��
	
	private Integer tag;
	
	private String empowerType;

	private Integer petrol_sea_id;//��Ʒ
	
	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCarFlapper() {
		return carFlapper;
	}

	public void setCarFlapper(String carFlapper) {
		this.carFlapper = carFlapper;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getEmpowerType() {
		return empowerType;
	}

	public void setEmpowerType(String empowerType) {
		this.empowerType = empowerType;
	}

	public Integer getBigPosid() {
		return BigPosid;
	}

	public void setBigPosid(Integer bigPosid) {
		BigPosid = bigPosid;
	}

	public Integer getPetrol_sea_id() {
		return petrol_sea_id;
	}

	public void setPetrol_sea_id(Integer petrol_sea_id) {
		this.petrol_sea_id = petrol_sea_id;
	}
	
}
