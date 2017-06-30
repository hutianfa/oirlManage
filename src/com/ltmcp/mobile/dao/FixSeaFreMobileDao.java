package com.ltmcp.mobile.dao;

import com.ltmcp.entity.FixedSeal;

public interface FixSeaFreMobileDao {

	//�̶���ǩ��ʩ��
	public void saveLockFix(FixedSeal sea) throws Exception;
	//ʩ��ɹ������Ķ�ά��״̬
	public void updateFreCodeSta(Integer codeId);
	//����ʩ������ҽ��code
	public String queryFixSeaCode(String code);
	//���߽��code ��ѯFix��id
	public Integer queryFixId(String code);
	//�̶���ǩ�Ľ��
	public void saveUnLockFix(FixedSeal sea) throws Exception;	
	//���ɹ������Ķ�ά��״̬
	public void updateUnFreCodeSta(Integer codeId);
	
	
	//��������ʹ�õĶ�ά���ȡ���ƺ�
	public String queryCarByQRCode(String qRCode);
	//���ݵ�ǰ��ά���ȡ������ʩ���ά��
	public String queryCodeByQRCode(String qRCode);
	//���ݵ�ǰ��ά���ѯcodeid
	public Integer queryIdByFixCodeUnstatus(String code, Integer Status);
	//����id��ѯFixedSeal
	public FixedSeal queryFixedSeal(Integer id);
}
