package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.EmpCondition;
import com.ltmcp.entity.Freeze;

public interface EmpWaybillDao {
	//查询授权 运单列表
	public List<Freeze> findEmp(EmpCondition condition,PageBean pageBean);
	//查询总数
	public Integer findCount(EmpCondition condition); 
}
