package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class EmpCondition {
	
	private Integer comId; //��˾ID
		
	private Integer excStatus; //�쳣��״̬(0:�Ѵ���1:δ����)
	
	private Integer excType; //�쳣����
		
	private String perName;//�û���
	
	private String perId;
	
	private String beginTime;//��ʼʱ��
	
	private String endTime;//����ʱ��
				
	private Integer areaid;
	
	private Integer posid;

	private String comName;
	
	private String carFlapper;
	
	private Integer empType;
		
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

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		try {
			this.perName =new String(perName.getBytes("ISO8859-1"), "UTF-8");
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

	public Integer getEmpType() {
		return empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}
}
