package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

public class ReportCondition {

	private Integer id;
	
	private Integer posid;
	
	private Integer areaid;
	
	private Integer comId;
	
	private String comName;
	
	private String beginTime; // 开始时间

	private String endTime; // 结束时间\

	private String sortT;
	
	private String carFlapper;//汽车牌号
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComid(Integer comId) {
		this.comId = comId;
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

	public String getSortT() {
		return sortT;
	}

	public void setSortT(String sortT) {
		this.sortT = sortT;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer getPosid() {
		return posid;
	}

	public void setPosid(Integer posid) {
		this.posid = posid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getCarFlapper() {
		return carFlapper;
	}

	public void setCarFlapper(String carFalpper) {
		try {
			carFlapper=new String(carFalpper.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.carFlapper = carFlapper;
	}
}
