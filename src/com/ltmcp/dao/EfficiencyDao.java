package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.entity.Car;

public interface EfficiencyDao {

	//获取车辆信息
	public List<Car> carInfor(ReportCondition condition);
	//根据车牌获取运单编号
	public List<Object> carWayNum(Integer car,ReportCondition condition);
	//根据运单编号获取平均运输时长
	public Integer carWayToTime(String  str);
	//根据运单编号获取运输次数
	public Integer carWayToNum(String str);
	//根据运单编号获取平均运输时长&&运输次数
	public Object queryWay(String str);
}
