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
	//新添加标签表
	public void insertAreaThings(AreaThings at);
	//添加AreaThingsTotal签封表
	public void insertAreaThingsTotal(AreaThingsTotal att);
	//查询
	public AreaThingsTotal findAreaThingsTotal(AreaThings att);
	//查询
	public AreaThingsTotal findThingsTotal(Things tt);
	//update
	public void thingDaoUpdate(Things th);
	//查询所有的签封信息
	public List<AreaThingsTotal> queryAreaThings(ThingCondition condition);
	//查询站点
	public List<Area> queryArea(Integer areaid);
	//根据ID查询封签总数
	public List<AreaThingsTotal> queryAreaThingsTotal();
	//
	public List<AreaThings> queryAreaIdThings();
	
	//comid-->comName
	public String queryComNm(Integer comid);
	
	//posid-->posName
	public String queryPosNm(Integer posid);
	
	
	//查询所有的站点信息
	public List<Position> queryPosiD(Integer currentPage,ThingCondition thingCondition);
	public Integer queryPosNum(ThingCondition condition);
	
	//根据站点查询剩余数
	public Integer queryLossNum(Integer posid,ThingCondition thingCondition); 
	//根据站点查询总入库数数	
	public Object queryTotNum(Integer posid,ThingCondition thingCondition); 
	
	//查询thing记录总数
	public Integer QueryThing(Integer comId,ThingCondition thingCondition);
}
