package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Company;
import com.ltmcp.entity.Empower;

public interface EmpowerDao {
	//��Ȩlist
	public List<Empower> listDao(Integer comid,String EmName,Integer currentPage);
	
	//list����
	public Integer  queryemTotal(String EmName);
	
	//����comid��ȡcomName
	public String queryComNm(Integer comId);
	//�����Ȩ
	public void addDao(Empower em);
	//ɾ����Ȩ
	public void delDao(Integer poId);
	//�޸���Ȩ
	public void updateDao(Empower em);
	//��˾�б�
	public List<Company> comList();
}
