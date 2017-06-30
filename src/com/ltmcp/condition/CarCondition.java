package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class CarCondition {
	
	
	private String carFlapper; //≥µ¡æ∫≈≈∆
	
	private String carFlag; 
	
	private Integer comId;
	
	private String comName;


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

	public String getCarFlag() {
		return carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
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
	
	
}
