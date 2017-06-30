package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;

public class MailCondition {
	
	private Integer posId; // 位置ID

	private Integer comId; // 公司ID----->通过手持机人员的公司ID赋值

	private Integer areaid;
	
	private Integer perId;

	private Integer status; // 状态,0：完成，1，正在途中 2，出现异常

	private String beginTime; // 开始时间

	private String endTime; // 结束时间\
		
	private Integer BigPosid;//油库的id
	
	private String carFlapper;
	
	private Integer tag;
	
	private String empowerType;

	private Integer petrol_sea_id;//油品
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
