package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;

public interface DWMReportDao {
	
	//获取站点
	public List<Position> getPosi();
	//获取人
	public List getPeop();
	//获取车
	public List getCar();
	
	//获取日报表
	public List<Sealed> d(String types);
	//获取周报表
	public List<Sealed> w(String types);
	//获取月报表
	public List<Sealed> m(String types);
	
	
}
