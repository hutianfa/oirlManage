package com.ltmcp.mobile.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.AppErrorMobileBiz;

public class AppErrorMobileAction extends BaseAction{

	private String code;
	private String returnNum;
	private String username;
	private String posid;
	private String appNum;
	private String carFlapper;
	private AppErrorMobileBiz appErrorMobileBiz;
	
	//上传app端异常时的问题
	public void appReturn(){
		try {
			appErrorMobileBiz.appError_nor(code,returnNum,username,posid,appNum);
			super.getResponse().getWriter().print(1);
			return;
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
	}
	
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(String returnNum) {
		try {
			returnNum=new String(returnNum.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.returnNum = returnNum;
	}

	public AppErrorMobileBiz getAppErrorMobileBiz() {
		return appErrorMobileBiz;
	}

	public void setAppErrorMobileBiz(AppErrorMobileBiz appErrorMobileBiz) {
		this.appErrorMobileBiz = appErrorMobileBiz;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPosid() {
		return posid;
	}



	public void setPosid(String posid) {
		this.posid = posid;
	}



	public String getAppNum() {
		return appNum;
	}



	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}



	public String getCarFlapper() {
		return carFlapper;
	}



	public void setCarFlapper(String carFlapper) {
		this.carFlapper = carFlapper;
	}

}
