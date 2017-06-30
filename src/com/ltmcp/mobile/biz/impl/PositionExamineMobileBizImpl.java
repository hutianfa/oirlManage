package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import com.ltmcp.entity.PositionExamine;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.mobile.biz.PositionExamineMobileBiz;
import com.ltmcp.mobile.biz.PositionExamineUserMobileBiz;
import com.ltmcp.mobile.dao.PositionExamineMobileDao;

public class PositionExamineMobileBizImpl implements PositionExamineMobileBiz {

	private PositionExamineUserMobileBiz positionExamineUserMobileBiz;
	private PositionExamineMobileDao positionExamineMobileDao;
	
	@Override
	public boolean add(String longitude, String latitude, String name,String type, String cardNumber,String positionAccount,String positionPassword) {
		if(longitude==null||latitude==null||name==null||type==null||cardNumber==null||positionAccount==null||positionPassword==null){
			return false;
		}
		PositionExamineUser user;
		try {
			user=positionExamineUserMobileBiz.getPositionExamineUser(positionAccount, positionPassword);
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		if(user==null){
			return false;
		}
		try {
			name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			return false;
		}
		PositionExamine examine=new PositionExamine();
		examine.setLongitude(longitude);
		examine.setLatitude(latitude);
		examine.setName(name);
		examine.setType(type);
		examine.setCardNumber(cardNumber);
		examine.setPositionExamineUser(user);
		examine.setCompany(user.getCompany());
		examine.setTime(new Timestamp(System.currentTimeMillis()));
		examine.setState(1);
		try {
			positionExamineMobileDao.insert(examine);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public PositionExamineUserMobileBiz getPositionExamineUserMobileBiz() {
		return positionExamineUserMobileBiz;
	}

	public void setPositionExamineUserMobileBiz(
			PositionExamineUserMobileBiz positionExamineUserMobileBiz) {
		this.positionExamineUserMobileBiz = positionExamineUserMobileBiz;
	}

	public PositionExamineMobileDao getPositionExamineMobileDao() {
		return positionExamineMobileDao;
	}

	public void setPositionExamineMobileDao(
			PositionExamineMobileDao positionExamineMobileDao) {
		this.positionExamineMobileDao = positionExamineMobileDao;
	}
	
	

}
