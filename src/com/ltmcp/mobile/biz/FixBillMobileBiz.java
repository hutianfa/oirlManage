package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Sealed;

public interface FixBillMobileBiz {

	//���
	public Integer addLockInfo(String name, String password, String code,String plateNumber, String filePath, Integer positionId ,String tips)throws Exception;
	//ʩ��
	public Integer addUnlockInfo(String name, String password,String code,Integer positionId, String filePath,String tips)throws Exception;
	//��ȡ���ƺ�
	public String getCarByQRCode(String name, String password, String code);
	
	//��ȡʩ���ά��
	public String getCodeByQRCode(String code);
}
