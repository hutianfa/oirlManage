package com.ltmcp.service;

import java.util.List;

import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Empower;

public interface EmpowerService {

	//��Ȩlist
	public List<Empower> listService(Integer comid,String EmName,Integer currentPage);
	//��Ȩlist����
	public Integer findEmTotal(String EmName);
	//����comid��ȡ��˾����
	public String queryComNmByComid(Integer comId);
	//�����Ȩ
	public void addService(Empower em);
	//ɾ����Ȩ
	public void delService(Integer poId);
	//�޸���Ȩ
	public void updateServcie(Empower em);
	//��ѯ��˾�б�
	public List queryCom();
	
}
