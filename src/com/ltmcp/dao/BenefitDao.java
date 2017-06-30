package com.ltmcp.dao;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Petrol;
import com.ltmcp.entity.Sealed;

public interface BenefitDao {
	//��ȡ������Ϣ
	public List<Car> carInfor(ReportCondition condition);
	
	//���ݳ�����ѯ�������
	public List<Object[]>  findPetrol(String carFlapper,ReportCondition condition);
	
}
