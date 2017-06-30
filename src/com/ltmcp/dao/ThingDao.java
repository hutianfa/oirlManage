package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ThingCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.AreaThings;
import com.ltmcp.entity.AreaThingsTotal;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Things;
import com.ltmcp.entity.ThingsTotal;

public interface ThingDao {

	//list
	public List<Things> thingDaoList(Integer comId,ThingCondition thingCondition,Integer currentPage);
	//add
	public void thingDaoAdd(Things th);
	//����ӱ�ǩ��
	public void insertAreaThings(AreaThings at);
	//���AreaThingsTotalǩ���
	public void insertAreaThingsTotal(AreaThingsTotal att);
	//��ѯ
	public AreaThingsTotal findAreaThingsTotal(AreaThings att);
	//��ѯ
	public AreaThingsTotal findThingsTotal(Things tt);
	//update
	public void thingDaoUpdate(Things th);
	//��ѯ���е�ǩ����Ϣ
	public List<AreaThingsTotal> queryAreaThings(ThingCondition condition);
	//��ѯվ��
	public List<Area> queryArea(Integer areaid);
	//����ID��ѯ��ǩ����
	public List<AreaThingsTotal> queryAreaThingsTotal();
	//
	public List<AreaThings> queryAreaIdThings();
	
	//comid-->comName
	public String queryComNm(Integer comid);
	
	//posid-->posName
	public String queryPosNm(Integer posid);
	
	
	//��ѯ���е�վ����Ϣ
	public List<Position> queryPosiD(Integer currentPage,ThingCondition thingCondition);
	public Integer queryPosNum(ThingCondition condition);
	
	//����վ���ѯʣ����
	public Integer queryLossNum(Integer posid,ThingCondition thingCondition); 
	//����վ���ѯ���������	
	public Object queryTotNum(Integer posid,ThingCondition thingCondition); 
	
	//��ѯthing��¼����
	public Integer QueryThing(Integer comId,ThingCondition thingCondition);
}
