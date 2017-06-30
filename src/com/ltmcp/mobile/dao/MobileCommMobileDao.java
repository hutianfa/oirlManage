package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;

public interface MobileCommMobileDao {

	public String findYouPinBySealCode(String code);
	
	public PetrolName findYouPinByNfc(String nfc,int comid);
	
	public int findComidByUsername(String username);
	
	public void changeSeaByYunDan(String yundan, String twoCode,String filePath);
	
	public void changeSeaPicByYunDan(String type, String bianhao, String picPath);
	
	public List<Picture> findPicByWayNum(String wayNum);
}
