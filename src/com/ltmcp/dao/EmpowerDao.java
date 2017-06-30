package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Company;
import com.ltmcp.entity.Empower;

public interface EmpowerDao {
	//授权list
	public List<Empower> listDao(Integer comid,String EmName,Integer currentPage);
	
	//list总数
	public Integer  queryemTotal(String EmName);
	
	//根据comid获取comName
	public String queryComNm(Integer comId);
	//添加授权
	public void addDao(Empower em);
	//删除授权
	public void delDao(Integer poId);
	//修改授权
	public void updateDao(Empower em);
	//公司列表
	public List<Company> comList();
}
