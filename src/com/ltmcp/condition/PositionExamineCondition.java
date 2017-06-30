package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class PositionExamineCondition {
	
	private String name;
	
	private Integer state;	//1´ýÉó²é

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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			this.name =new String(name.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	
}
