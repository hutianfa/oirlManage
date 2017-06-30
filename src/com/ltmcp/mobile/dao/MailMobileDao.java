package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.condition.MailCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Petrol;
import com.ltmcp.entity.Position;

public interface MailMobileDao {

	//查询所有车辆信息
	public List<Car> queryCar(MailCondition condition);
	//查询所有站点信息
	public List<Position> queryPosi(MailCondition condition);
	//根据车牌以及站点及时间获取油损信息
//	public List<Petrol> queryPetrol(String  carFlag,int posid,MailCondition condition);
	
	//根据车牌以及站点及时间获取油损信息
	public Petrol queryPetrol(String  carFlag,int posid,MailCondition condition);
	//根据车牌/时间获取施封总数
	public Integer queryWayAllNum(int carid,MailCondition condition);
	//根据车牌/时间/站点id获取施封总数
	public Integer queryWayNoAllNum(int carid,int posid,MailCondition condition);
}
