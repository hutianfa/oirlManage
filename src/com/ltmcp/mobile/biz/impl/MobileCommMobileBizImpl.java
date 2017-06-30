package com.ltmcp.mobile.biz.impl;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;
import com.ltmcp.mobile.biz.MobileCommMobileBiz;
import com.ltmcp.mobile.dao.MobileCommMobileDao;
import com.ltmcp.util.CharTools;

public class MobileCommMobileBizImpl implements MobileCommMobileBiz{

	private MobileCommMobileDao mobileCommMobileDao;



	@Override
	public String queryYouPinBySealCode(String code) {
		
		return mobileCommMobileDao.findYouPinBySealCode(code);
	}
	
	@Override
	public PetrolName queryYouPinByNfc(String nfc,String username) {
		String name = "";
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(username)), "UTF-8");
//			System.out.println("name:"+name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int comid = mobileCommMobileDao.findComidByUsername(name);
		
		return mobileCommMobileDao.findYouPinByNfc(nfc,comid);
	}
	/*
	 * /vedio/xxw-2016-07-17-14-33-23-712
		                                  0URBPYRcObYpn
	   /vedio/xxw-2016-07-17-14-33-23-712-0URBPYRcObYpn.mp4
	 * @see com.ltmcp.mobile.biz.MobileCommMobileBiz#updateSeaByYunDan(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateSeaByYunDan(String yundan, String twoCode, String filePath) {
		try {
			mobileCommMobileDao.changeSeaByYunDan(yundan, twoCode, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateSeaPicByYunDan(String type, String bianhao, String picPath) {
		try {
			mobileCommMobileDao.changeSeaPicByYunDan(type, bianhao, picPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public List<Picture> quertPicByWayNum(String wayNum) {
		
		return mobileCommMobileDao.findPicByWayNum(wayNum);
	}	
	
	
	
	
	
	
	
	
	
	public MobileCommMobileDao getMobileCommMobileDao() {
		return mobileCommMobileDao;
	}

	public void setMobileCommMobileDao(MobileCommMobileDao mobileCommMobileDao) {
		this.mobileCommMobileDao = mobileCommMobileDao;
	}

	@Override
	public int quertComidByUsername(String username) {
		
		return 0;
	}

}
