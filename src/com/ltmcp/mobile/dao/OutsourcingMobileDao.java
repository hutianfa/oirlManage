package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Sealed;

public interface OutsourcingMobileDao {

	//����code��ѯʩ���룬����id
	public Integer queryCodeId(String code);
	//����codeid��ѯ�˵�
	public Sealed querySeaByCodeId(Integer id);
}
