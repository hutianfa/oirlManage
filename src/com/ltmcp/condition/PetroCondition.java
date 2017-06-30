package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class PetroCondition {
	
	private Integer posId; // 位置ID

	private Integer areaid;
	
	private Integer comId; // 公司ID----->通过手持机人员的公司ID赋值

	private String seaLockCode; // 标签编码

	private Integer posTypeId; // 位置的类型

	private String perName; // 手持机操作人员姓名

	private String erweima;// 二维码内容

	private Integer status; // 状态,0：完成，1，正在途中 2，出现异常

	private String beginTime; // 开始时间

	private String endTime; // 结束时间\
	
	private String comName;
	
	private String carFlapper; //车辆号牌
	
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		try {
			comName=new String(comName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.comName = comName;
	}

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

	public String getSeaLockCode() {
		return seaLockCode;
	}

	public void setSeaLockCode(String seaLockCode) {
		this.seaLockCode = seaLockCode;
	}

	public Integer getPosTypeId() {
		return posTypeId;
	}

	public void setPosTypeId(Integer posTypeId) {
		this.posTypeId = posTypeId;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErweima() {
		return erweima;
	}

	public void setErweima(String erweima) {
		this.erweima = erweima;
	}

	public String getCarFlapper() {
		return carFlapper;
	}

	public void setCarFlapper(String carFlapper) {
		try {
			carFlapper=new String(carFlapper.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.carFlapper = carFlapper;
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

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
}
