package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;

public interface MobileCommMobileBiz {
	//根据 二维码 获取油品信息
	public String queryYouPinBySealCode(String code);
	//根据nfc 的id 获取油品信息 
	public PetrolName queryYouPinByNfc(String nfc,String username);
	//获取该用户的comid
	public int quertComidByUsername(String username);
	//根据运单号进行视频文件的绑定
	public void updateSeaByYunDan(String yundan, String twoCode,String filePath);
	//将图片与运单编号绑定
	public void updateSeaPicByYunDan(String type, String bianhao, String picPath);
	//向 web端提供该运单编号下的图片路劲
	public List<Picture> quertPicByWayNum(String wayNum);
}
