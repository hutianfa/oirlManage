package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.EmpCondition;
import com.ltmcp.entity.Freeze;

public interface EmpWaybillDao {
	//��ѯ��Ȩ �˵��б�
	public List<Freeze> findEmp(EmpCondition condition,PageBean pageBean);
	//��ѯ����
	public Integer findCount(EmpCondition condition); 
}
