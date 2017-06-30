package com.ltmcp.service;

import java.util.List;

import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Empower;

public interface EmpowerService {

	//授权list
	public List<Empower> listService(Integer comid,String EmName,Integer currentPage);
	//授权list总数
	public Integer findEmTotal(String EmName);
	//根据comid获取公司名称
	public String queryComNmByComid(Integer comId);
	//添加授权
	public void addService(Empower em);
	//删除授权
	public void delService(Integer poId);
	//修改授权
	public void updateServcie(Empower em);
	//查询公司列表
	public List queryCom();
	
}
