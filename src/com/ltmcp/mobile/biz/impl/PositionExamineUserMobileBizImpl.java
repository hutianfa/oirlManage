package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.entity.Company;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.PositionExamineUserMobileBiz;
import com.ltmcp.mobile.dao.PositionExamineUserMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class PositionExamineUserMobileBizImpl implements PositionExamineUserMobileBiz {
	private PositionExamineUserMobileDao positionExamineUserMobileDao;
	@Override
	public PositionExamineUser getPositionExamineUser(String positionAccount,String positionPassword) throws UnsupportedEncodingException {
		if(positionAccount==null||positionPassword==null){
			return null;
		}
		if(positionAccount.trim().equals("")||positionPassword.trim().equals("")){
			return null;
		}
		//----------Ω‚√‹------------
		positionAccount=new String(Base64.decodeBase64(CharTools.allToUTF8(positionAccount)),"UTF-8");
		positionPassword=new String(Base64.decodeBase64(CharTools.allToUTF8(positionPassword)),"UTF-8");
		PositionExamineUser user=positionExamineUserMobileDao.queryUser(positionAccount,MD5.md5crypt(positionPassword));
		return user;
	}
	
	
	
	public PositionExamineUserMobileDao getPositionExamineUserMobileDao() {
		return positionExamineUserMobileDao;
	}
	public void setPositionExamineUserMobileDao(
			PositionExamineUserMobileDao positionExamineUserMobileDao) {
		this.positionExamineUserMobileDao = positionExamineUserMobileDao;
	}



	@Override
	public List<Position> findPosList() {
		
		return positionExamineUserMobileDao.queryList();
	}
	
}
