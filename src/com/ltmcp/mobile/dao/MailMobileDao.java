package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.condition.MailCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Petrol;
import com.ltmcp.entity.Position;

public interface MailMobileDao {

	//��ѯ���г�����Ϣ
	public List<Car> queryCar(MailCondition condition);
	//��ѯ����վ����Ϣ
	public List<Position> queryPosi(MailCondition condition);
	//���ݳ����Լ�վ�㼰ʱ���ȡ������Ϣ
//	public List<Petrol> queryPetrol(String  carFlag,int posid,MailCondition condition);
	
	//���ݳ����Լ�վ�㼰ʱ���ȡ������Ϣ
	public Petrol queryPetrol(String  carFlag,int posid,MailCondition condition);
	//���ݳ���/ʱ���ȡʩ������
	public Integer queryWayAllNum(int carid,MailCondition condition);
	//���ݳ���/ʱ��/վ��id��ȡʩ������
	public Integer queryWayNoAllNum(int carid,int posid,MailCondition condition);
}
