package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.entity.Car;

public interface SafeDao {

	//��ȡ������Ϣ
	public List<Car> carInfor(ReportCondition condition);
	//��ȡ�����Ľ����
	public Integer freInfor(Integer car,ReportCondition condition);
	//��ȡ�������쳣��
	public Integer exrFreInfor(Integer car,ReportCondition condition);
}
