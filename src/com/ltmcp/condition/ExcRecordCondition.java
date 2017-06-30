package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class ExcRecordCondition {
	
	private Integer comId; //公司Id
	
	private String lockCode; //erweima
	
	private Integer excStatus; //异常的状态(0:已处理，1:未处理)
	
	private Integer excType; //异常类型（0施封码未注册，1解封码未注册，2解封未施封）
	
	private String erweima;//二维码
	
	private String perName;//用户名
	
	private String perId;
	
	private String beginTime;//开始时间
	
	private String endTime;//结束时间
	
	private Long info;	//下拉框
	
	private String dictName;//油库ID
	
	private String staName;//加油站ID
	
	private Integer areaid;
	
	private Integer posid;//站点id

	private String comName;
	
	private String carFlapper;

	private String time;//最近七天（7），最近一个月（30），一年（2016,2017）
	
	private int site;//id（加油站或者油库）
	

	
	public int getSite() {
		return site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

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
	

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}


	public String getLockCode() {
		return lockCode;
	}

	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}

	public Integer getExcStatus() {
		return excStatus;
	}

	public void setExcStatus(Integer excStatus) {
		this.excStatus = excStatus;
	}

	public Integer getExcType() {
		return excType;
	}

	public void setExcType(Integer excType) {
		this.excType = excType;
	}

	public String getErweima() {
		return erweima;
	}

	public void setErweima(String erweima) {
		this.erweima = erweima;
	}

	public Long getInfo() {
		return info;
	}

	public void setInfo(Long info) {
		this.info = info;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		try {
			perName=new String(perName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.perName = perName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		try {
			this.dictName =new String(dictName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		try {
			this.staName =new String(staName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

	public Integer getPosid() {
		return posid;
	}

	public void setPosid(Integer posid) {
		this.posid = posid;
	}

	public String getCarFlapper() {
		return carFlapper;
	}

	public void setCarFlapper(String carFlapper) {
		this.carFlapper = carFlapper;
	}

	public String getPerId() {
		return perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}

	@Override
	public String toString() {
		return "ExcRecordCondition [comId=" + comId + ", lockCode=" + lockCode
				+ ", excStatus=" + excStatus + ", excType=" + excType
				+ ", erweima=" + erweima + ", perName=" + perName + ", perId="
				+ perId + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", info=" + info + ", dictName=" + dictName + ", staName="
				+ staName + ", areaid=" + areaid + ", posid=" + posid
				+ ", comName=" + comName + ", carFlapper=" + carFlapper + "]";
	}
}
