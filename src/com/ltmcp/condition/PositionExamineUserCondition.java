package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;

public class PositionExamineUserCondition {
	
	private String name; //用户名
	
	private String account;//账户名

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		try {
			this.account =new String(account.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
}
