package com.ltmcp.mobile.biz;

import com.ltmcp.entity.Sealed;

public interface OutsourcingMobileBiz {
	//����code ��ȡ��ά��ļ�¼id
	public Integer findCodeId(String code);
	//����code �� id ��ȡ ��Ӧ���˵�
	public Sealed findSeaByCodeId(Integer id);
}
