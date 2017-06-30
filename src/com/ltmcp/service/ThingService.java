package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ThingCondition;
import com.ltmcp.entity.AreaThings;
import com.ltmcp.entity.AreaThingsTotal;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Things;

public interface ThingService {

	//list
	public List<Map<String, Object>>  returnThingService(ThingCondition thingCondition,Integer currentPage);
	//add
	public void thingAddService(Things th);
	
	public void insertAreaThingsService(AreaThings at);
	public void insertAreaThingsTotal(AreaThings att);
	//
	public List<Map<String,Object>> queryAreaThingsService(ThingCondition condition);
	//查询所有站点
	public List<Map<String,Object>> quertArea(Integer areaid);
	//update
	public void thingUpdateService(Things th);
	
	//查询管理员所属公司的全部站点
	public List<Position> queryPosi(ThingCondition thingCondition,Integer currentPage);
	
	
	//返回list<map<String,Object>>	
	public List<Map<String,Object>> RetuenList(ThingCondition thingCondition,Integer currentPage);
	
	public List<Map<String,Object>> queryAreaThingsTotal();
	
	
	
}
