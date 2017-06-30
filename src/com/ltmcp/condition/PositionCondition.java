package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class PositionCondition {
	
	private Integer comId; //��˾ID
	
	private Integer areaid;
	
	private Integer posType; //λ������
	
	private String posName; //λ������
	
	private Integer posid;

	private String comName;
	
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

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}



	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		try {
			this.posName =new String(posName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
}
