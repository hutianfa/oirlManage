package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Sealed;

public interface FixBillMobileBiz {

	//解封
	public Integer addLockInfo(String name, String password, String code,String plateNumber, String filePath, Integer positionId ,String tips)throws Exception;
	//施封
	public Integer addUnlockInfo(String name, String password,String code,Integer positionId, String filePath,String tips)throws Exception;
	//获取车牌号
	public String getCarByQRCode(String name, String password, String code);
	
	//获取施封二维码
	public String getCodeByQRCode(String code);
}
