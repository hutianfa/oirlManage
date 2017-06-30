package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.EmpCondition;
import com.ltmcp.entity.Freeze;

public interface EmpWaybillService {

	//条件查询
	public List<Freeze> queryEmp(EmpCondition condition,PageBean pageBean);
	
	//返回总的条数，以及页数
	public PageBean queryCount(EmpCondition condition);
}
