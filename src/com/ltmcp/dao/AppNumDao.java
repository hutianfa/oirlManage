package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.entity.DW;
import com.ltmcp.entity.Limt;

public interface AppNumDao  {
	//查询当前app版本升级列表
	public List<Object> querydDw(PageBean pageBean,AppNumCondition condition);
	//查寻总数，方便分页处理
	public Integer queryDwTotal(PageBean pageBean, AppNumCondition condition);
	//查询当前版本号
	public Limt queryLimitime();
}
