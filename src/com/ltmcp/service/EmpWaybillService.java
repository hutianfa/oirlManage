package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.EmpCondition;
import com.ltmcp.entity.Freeze;

public interface EmpWaybillService {

	//������ѯ
	public List<Freeze> queryEmp(EmpCondition condition,PageBean pageBean);
	
	//�����ܵ��������Լ�ҳ��
	public PageBean queryCount(EmpCondition condition);
}
