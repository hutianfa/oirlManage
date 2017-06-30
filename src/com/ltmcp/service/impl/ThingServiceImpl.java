package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.ThingCondition;
import com.ltmcp.dao.ThingDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.AreaThings;
import com.ltmcp.entity.AreaThingsTotal;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Things;
import com.ltmcp.service.ThingService;

public class ThingServiceImpl extends BaseServiceImpl implements ThingService{

	private ThingDao thingDao;
	@Override
	public List<Position> queryPosi(ThingCondition condition,Integer currentPage) {
		return thingDao.queryPosiD(currentPage,condition);
	}

	@Override
	public List<Map<String, Object>> quertArea(Integer areaid) {
		List<Area> list = thingDao.queryArea(areaid);
		ArrayList<Map<String, Object>> li = new ArrayList<Map<String, Object>>();	
		for(int i = 0 ;i<list.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("area_name", list.get(i).getArea_name());
			map.put("com_id", list.get(i).getCom_id());
			li.add(map);
		}
		return li;
	}

	@Override
	public List<Map<String,Object>> queryAreaThingsTotal() {
		List<AreaThingsTotal> list = thingDao.queryAreaThingsTotal();
		ArrayList<Map<String,Object>> al = new ArrayList<Map<String,Object>>();
		for(int i = 0 ;i<list.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).getId());
			map.put("admName", list.get(i).getAdmName());
			long time = list.get(i).getTime().getTime();
			Date d = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("time", sdf.format(d));
			map.put("areaId", list.get(i).getAreaId());
			map.put("comId", list.get(i).getComId());
			map.put("inNum", list.get(i).getInNum());
			map.put("status", list.get(i).getStatus());
			map.put("detailName", list.get(i).getDetailName());
			al.add(map);
			
		}
		return al;
	}

	/**
	 * 查询所有的签封
	 */
	@Override
	public List<Map<String,Object>> queryAreaThingsService(ThingCondition condition) {
		
		List<AreaThingsTotal> list = thingDao.queryAreaThings(condition);
		
		ArrayList<Map<String, Object>> li = new ArrayList<Map<String, Object>>();	
		
		for(int i = 0 ;i<list.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("admName", list.get(i).getAdmName());
			long time = list.get(i).getTime().getTime();
			Date d = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("time", sdf.format(d));
			map.put("areaId",list.get(i).getAreaId());
			map.put("comId", list.get(i).getComId());
			map.put("inNum", list.get(i).getInNum());
			map.put("shengyuNum", list.get(i).getShengyuNum());
			map.put("status", list.get(i).getStatus());
			map.put("detailName", list.get(i).getDetailName());
			li.add(map);
		}
		return li;
	}
	@Override
	public List<Map<String, Object>> RetuenList(ThingCondition condition,Integer currentPage) {
		
		
		List<Position> list=thingDao.queryPosiD(currentPage,condition);
		
		Integer totalNu = thingDao.queryPosNum(condition);
		
		int pageNum = totalNu % 13 == 0?totalNu/13:(totalNu/13)+1;
		
		ArrayList<Map<String, Object>> li = new ArrayList<Map<String, Object>>();	
		
		for(int i = 0 ;i<list.size();i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("company", list.get(i).getCompany().getComName());
			
			map.put("position",list.get(i).getPosName());
			
			map.put("pageNum",pageNum);
						
			map.put("total", totalNu);
			//入库总数
			//int total =thingDao.queryTotNum(list.get(i).getPosId(),condition);
			//int total =0 ;
			//total =thingDao.queryTotNum(list.get(i).getPosId(),condition);	
			long total =0 ;
			
			try {
				Object obj =thingDao.queryTotNum(list.get(i).getPosId(),condition);
				
				if(obj != null){
					//时间段里领的封签总数
					map.put("totalNum", obj);
					//入库总数
					total = (Long) obj;
				}else{
					map.put("totalNum", 0);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			//使用
			int loss = thingDao.queryLossNum(list.get(i).getPosId(),condition);
			
			//时间段里封签库存剩余
			if(loss == 0){
				map.put("lossNum", total);
			}else if(total == 0){
				map.put("lossNum", 0);
			}else{
				map.put("lossNum", total - loss);
			}
			
			//使用的数
			map.put("used", loss);
			
			li.add(map);
			
	}
	
	return li;
	
}
	
	@Override
	public List<Map<String, Object>>  returnThingService(ThingCondition thingCondition,Integer currentPage) {
		
		List<Things> list = thingDao.thingDaoList(AdminComm.getComIdByAdmin(),thingCondition,currentPage);
		
		ArrayList<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		
		for(int i = 0 ;i<list.size();i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", list.get(i).getId());
			
			long time = list.get(i).getTime().getTime();				
			Date d = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("time", sdf.format(d));
			
			map.put("company", thingDao.queryComNm(list.get(i).getCompany()));
			
			map.put("position", thingDao.queryPosNm(list.get(i).getPosition()));
			
			map.put("inNum", list.get(i).getInNum());

			map.put("perName", list.get(i).getPerName());
			
			
			int totalNu = thingDao.QueryThing(AdminComm.getComIdByAdmin(), thingCondition);
			
			int pageNum = totalNu % 13 == 0?totalNu/13:(totalNu/13)+1;
			
			map.put("pageNum", pageNum);
			
			map.put("total", totalNu);
			li.add(map);
		}
		return li;
	}
	/**
	 * 新签封领用添加方法
	 */
	@Override
	public void insertAreaThingsService(AreaThings at){
		Admin admin=(Admin) super.getSessionObject(Comm.CURRENT_ADMIN);
		at.setAdmName(admin.getAdmName());
		at.setComId(AdminComm.getComIdByAdmin());
		at.setTime(new Timestamp(System.currentTimeMillis()));
		at.setDetailName(AdminComm.getAdminName());
		thingDao.insertAreaThings(at);
	}

	@Override
	public void thingAddService(Things th) {
		AreaThingsTotal att = thingDao.findThingsTotal(th);
		Admin admin=(Admin) super.getSessionObject(Comm.CURRENT_ADMIN);
		th.setPerName(admin.getAdmName());
		th.setCompany(AdminComm.getComIdByAdmin());
		th.setTime(new Timestamp(System.currentTimeMillis()));
		th.setAreaid(AdminComm.getAdminAreaId());
		th.setPosition(th.getPosition());
		//首先获取到页面输入的数
		//再获取到存储表中的数是否存在或者是否小于要取得数
		//已经获取到最后减去的数，
		int temp = att.getInNum()-th.getInNum();
		att.setShengyuNum(temp);
		thingDao.insertAreaThingsTotal(att);
		
		thingDao.thingDaoAdd(th);
	}
	@Override
	public void insertAreaThingsTotal(AreaThings at) {
		//先查处 区信息是否存在
		AreaThingsTotal att = thingDao.findAreaThingsTotal(at);
		
		if(att == null){//不存在
			AreaThingsTotal a = new AreaThingsTotal();
			a.setAdmName(AdminComm.getAdminName());
			a.setComId(AdminComm.getComIdByAdmin());
			a.setTime(new Timestamp(System.currentTimeMillis()));
			a.setDetailName(AdminComm.getAdminName());
			a.setInNum(at.getInNum());
			a.setAreaId(at.getAreaId());
			thingDao.insertAreaThingsTotal(a);
		}else if(att != null){//存在
			//先取出来
			int innum = att.getInNum();
			//相加
			int temp = innum + at.getInNum();
			//插入
			att.setInNum(temp);
			thingDao.insertAreaThingsTotal(att);
			Things t = new Things();
		}
		
	}
	@Override
	public void thingUpdateService(Things th) {
		thingDao.thingDaoUpdate(th);
	}

	public ThingDao getThingDao() {
		return thingDao;
	}

	public void setThingDao(ThingDao thingDao) {
		this.thingDao = thingDao;
	}
	
}
