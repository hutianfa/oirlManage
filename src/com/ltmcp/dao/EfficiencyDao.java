package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.entity.Car;

public interface EfficiencyDao {

	//��ȡ������Ϣ
	public List<Car> carInfor(ReportCondition condition);
	//���ݳ��ƻ�ȡ�˵����
	public List<Object> carWayNum(Integer car,ReportCondition condition);
	//�����˵���Ż�ȡƽ������ʱ��
	public Integer carWayToTime(String  str);
	//�����˵���Ż�ȡ�������
	public Integer carWayToNum(String str);
	//�����˵���Ż�ȡƽ������ʱ��&&�������
	public Object queryWay(String str);
}
