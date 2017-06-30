package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;

public interface MobileCommMobileBiz {
	//���� ��ά�� ��ȡ��Ʒ��Ϣ
	public String queryYouPinBySealCode(String code);
	//����nfc ��id ��ȡ��Ʒ��Ϣ 
	public PetrolName queryYouPinByNfc(String nfc,String username);
	//��ȡ���û���comid
	public int quertComidByUsername(String username);
	//�����˵��Ž�����Ƶ�ļ��İ�
	public void updateSeaByYunDan(String yundan, String twoCode,String filePath);
	//��ͼƬ���˵���Ű�
	public void updateSeaPicByYunDan(String type, String bianhao, String picPath);
	//�� web���ṩ���˵�����µ�ͼƬ·��
	public List<Picture> quertPicByWayNum(String wayNum);
}
