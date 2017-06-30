package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.entity.Car;

public interface SafeDao {

	//获取车辆信息
	public List<Car> carInfor(ReportCondition condition);
	//获取车辆的解封数
	public Integer freInfor(Integer car,ReportCondition condition);
	//获取车辆的异常数
	public Integer exrFreInfor(Integer car,ReportCondition condition);
}
