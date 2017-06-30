package com.ltmcp.dao.hibimpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.ThingCondition;
import com.ltmcp.dao.ThingDao;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.AreaThings;
import com.ltmcp.entity.AreaThingsTotal;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Things;

public class ThingDaoHibImpl extends BaseDaoHibImpl implements ThingDao{

	@Override
	public List<Things> thingDaoList(Integer comId,ThingCondition condition,Integer currentPage) {
		StringBuilder sb = new StringBuilder("from Things th where th.company ="+comId);	
		thCondition(condition,sb);
		sb.append("and th.perName = '" + AdminComm.getAdminName() +"'");
		sb.append(" order by th.time desc ");
		return super.find(sb.toString(), currentPage, Comm.SYSTEM_PAGESIZE);
	}

	@Override
	public String queryComNm(Integer comid) {
		StringBuilder sb = new StringBuilder("select c.comName from Company c where c.comId ="+comid);
		return (String) super.query(sb.toString());
	}
	
	
	@Override
	public String queryPosNm(Integer posid) {
		StringBuilder sb = new StringBuilder("select p.posName from Position p where p.posId ="+ posid );
		return (String) super.query(sb.toString());
	}

	
	/**
	 * 根据站点查询所有封签数
	 */
	public List<AreaThings> queryAreaIdThings(){
		String hql = "from AreaThings where areaId= "+AdminComm.getAdminAreaId();
		List<AreaThings> list = (List<AreaThings>) this.queryHQL(hql, null);
		return list;
	}
	
	/**
	 * 查询所有站点
	 */
	@Override
	public List<Area> queryArea(Integer areaid){
		String hql="from Area where id="+areaid;
		List<Area> list = null;
		try {
			list = (List<Area>) this.queryHQL(hql, null);
			if(list!=null && list.size()>0){
				return list;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有的签封信息
	 */
	@Override
	public List<AreaThingsTotal> queryAreaThings(ThingCondition condition) {
		StringBuffer sb = new StringBuffer("from AreaThingsTotal at where at.comId= "+AdminComm.getComIdByAdmin());
		if(condition != null && condition.getAreaid() != null && !"".equals(condition.getAreaid())){
			sb.append("and at.areaId = " + condition.getAreaid() );
		}
		return super.find(sb.toString());
	}

	@Override
	public List<Position> queryPosiD(Integer currentPage,ThingCondition condition) {
		StringBuffer sb = new StringBuffer("from Position po where po.company.comId ="+AdminComm.getComIdByAdmin());		

//		if(condition != null && condition.getAreaid() != null && !"".equals(condition.getAreaid())){
			sb.append("and po.areaid = " + AdminComm.getAdminAreaId() );
//		}
		if(condition != null && condition.getPosId() != null && !"".equals(condition.getPosId())){
			sb.append("and po.posId = " + condition.getPosId() );
		}
		
		return super.find(sb.toString(), currentPage, Comm.SYSTEM_PAGESIZE);
	}
	
	//站点分页
	@Override
	public Integer queryPosNum(ThingCondition condition) {
		StringBuffer sb = new StringBuffer("select count(po.posId) from Position po where po.company.comId ="+AdminComm.getComIdByAdmin());
		
		if(condition != null && condition.getPosId() != null && !"".equals(condition.getPosId())){
			sb.append("and po.posId = " + condition.getPosId() );
		}
//		if(condition != null && condition.getAreaid() != null && !"".equals(condition.getAreaid())){
			sb.append("and po.areaid = " + AdminComm.getAdminAreaId() );
//		}
		return super.queryRowCount(sb.toString());
	}
	
	
	
	//使用的数
	@Override
	public Integer queryLossNum(Integer posid,ThingCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.company.comId ="+AdminComm.getComIdByAdmin());
		seaCondition(condition,sb);
		sb.append("and s.position.posId= " + posid);
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Object queryTotNum(Integer posid,ThingCondition condition) {

		StringBuilder sb=new StringBuilder("select sum(th.inNum) from Things th where th.position = " + posid);
		thCondition(condition,sb);
		return (Object)super.query(sb.toString(), null);
	}

	
	@Override
	public Integer QueryThing(Integer comId, ThingCondition condition) {
		
		StringBuilder sb=new StringBuilder("select count(th.id) from Things th where th.company ="+comId);
		thCondition(condition,sb);
		sb.append("and th.perName = '" + AdminComm.getAdminName() +"'");
		return super.queryRowCount(sb.toString());
	}
	
	
	private void thCondition(ThingCondition condition, StringBuilder sb) {
		if(condition != null){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() ==null){
				
				sb.append(" and th.time > "+ "'"+condition.getBeginTime()+"'" );
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				
				sb.append(" and th.time < "+ "'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime())  && condition.getEndTime() != null){
				
				sb.append(" and (th.time between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}
	}
	
	
	private void seaCondition(ThingCondition condition, StringBuilder sb) {
		if(condition != null){

			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() ==null){
				
				sb.append(" and s.seaTime > "+ "'"+condition.getBeginTime()+"'" );
			}
		
			if( !"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				
				sb.append(" and s.seaTime < "+ "'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime())  && condition.getEndTime() != null){
				
				sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}
	}	
	
	@Override
	public void thingDaoAdd(Things th) {
		
		super.saveOrUpdate(th);
	}

	@Override
	public void insertAreaThings(AreaThings at){
		super.saveOrUpdate(at);
	}
	//添加数据加到新表insertAreaThingsTotal
	@Override
	public void insertAreaThingsTotal(AreaThingsTotal att) {
		super.saveOrUpdate(att);
	}

	@Override
	public void thingDaoUpdate(Things th) {
		Integer id = th.getId();
		Things t = (Things) super.query(Things.class, id);
		if(th.getBeNum()!= null){
			t.setBeNum(th.getBeNum());
		}
		if(th.getEnNum() != null){
			t.setEnNum(th.getEnNum());
		}
		if(th.getInNum() != null){
			t.setInNum(th.getInNum());
		}
		if(th.getOutNum() != null){
			t.setOutNum(th.getOutNum());
		}
		if(th.getPosition()!= null){
			t.setPosition(th.getPosition());
		}
		if(th.getTag() != null){
			t.setTag(th.getTag());
		}
		if(th.getTotalNum() != null){
			t.setTotalNum(th.getTotalNum());
		}
		t.setTime(new Timestamp(System.currentTimeMillis()));
		super.saveOrUpdate(t);
	}

	@Override
	public AreaThingsTotal findAreaThingsTotal(AreaThings att) {
		String hql = "from AreaThingsTotal where areaId = "+att.getAreaId();
		return (AreaThingsTotal) super.query(hql, null);
	}

	@Override
	public AreaThingsTotal findThingsTotal(Things th) {
		String hql = "from AreaThingsTotal where areaId = "+AdminComm.getAdminAreaId();
		return (AreaThingsTotal) super.query(hql, null);
	}

	@Override
	public List<AreaThingsTotal> queryAreaThingsTotal() {
		String hql = "from AreaThingsTotal where areaId = "+AdminComm.getAdminAreaId();
		List<AreaThingsTotal> list = null;
		try {
			list = (List<AreaThingsTotal>) this.queryHQL(hql, null);
			if(list!=null && list.size()>0){
				return list;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
