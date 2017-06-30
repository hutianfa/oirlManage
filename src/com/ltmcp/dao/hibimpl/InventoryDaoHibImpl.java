package com.ltmcp.dao.hibimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.dao.InventoryDao;
import com.ltmcp.dao.firstOrderDao;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;

public class InventoryDaoHibImpl extends BaseDaoHibImpl implements InventoryDao 
{

	@Override
	public void query_The_Inventory() 
	{
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> findCompanyPersonNameByComid(Integer comid)
	{
		List<shoufa_person> splist = null;
		StringBuilder sb = null;
		try 
		{
			sb=new StringBuilder(" from shoufa_person sp where sp.com_id=?");
			splist = super.findList(sb.toString(), comid);//镇压警告
			return splist;
		} catch (Exception e) //查询不出来，返回null，注意监测
		{
			return null;
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> serachAreaAddress(Integer comid)
	{
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder(" from Area ar where ar.com_id=?");
		return super.findList(sb.toString(), comid);//镇压警告
	}

	@Override
	public int countByArea_name(String area_name) 
	{
		StringBuilder sb1=new StringBuilder("SELECT (SUM(bag_count)*50) as count1,SUM(bag_code_count) as count2 from `secondorder`where receive_sh_address = "+"'"+area_name+"'");
		//super.findList(sb.toString(), name);//镇压警告
		int co = 0;
		try 
		{
			co = super.queryCountBySql_co(sb1.toString());
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return co;
	}

	@Override
	public int countByArea_id(Integer area_id) 
	{
		StringBuilder sb1=new StringBuilder("SELECT (SUM(bag_count)*50) as count1,SUM(bag_code_count) as count2 from `position_inventory`where pianqu_id = "+"'"+area_id+"'");
		//super.findList(sb.toString(), name);//镇压警告
		int co = 0;
		try 
		{
			co = super.queryCountBySql_co(sb1.toString());
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return co;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> periodNormallyEndsNumber(int areaid) 
	{
		//第一步去查询片区下的站点
		//第二步去查询每一个站点的正常消耗数目
		List<Position> postions = null;
		List<Map<String, Object>> listTw = null;
		Map<String, Object> mapTw = null;
		int countStaZero;
		int countStaONe;
		try
		{
			StringBuilder sb=new StringBuilder(" from Position po where po.areaid=?");
			postions = super.findList(sb.toString(), areaid);//镇压警告
			listTw = new ArrayList<Map<String,Object>>();
			for(Position p : postions)//遍历站点统计站点总数
			{
				mapTw = new HashMap<String, Object>();//注意下一行中查询时间:s.seaTime>'2016-12-10'
				StringBuilder sb0 = new StringBuilder(" select count(*)from Sealed s where s.seaTime>'2016-12-10' and s.position.posId="+p.getPosId()+" and s.seaStatus=0");//查询施封表sealed中状态为0的，并且时间2016-12-11 00:00:00之后的数据
				countStaZero = super.queryRowCount(sb0.toString());
				StringBuilder sb1 = new StringBuilder(" select count(*)from Sealed s where s.seaTime>'2016-12-10' and s.position.posId="+p.getPosId()+" and s.seaStatus=1");//查询施封表sealed中状态为1的，并且时间2016-12-11 00:00:00之后的数据
				countStaONe = super.queryRowCount(sb1.toString());
			
				//System.out.println("countStaZero:"+countStaZero);
				//System.out.println("countStaONe:"+countStaONe);
				int total = countStaZero + countStaONe;
				//System.out.println(p.getPosName()+"站点总数："+(countStaZero+countStaONe));
				mapTw.put(p.getPosName(), total);
				listTw.add(mapTw);
			}
			return listTw;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("站点统计出错");
			return null;
		}
	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<first_order> companyContByFirstOrder(int comid) 
	{
		List<first_order> lfo = null;
		try 
		{
			StringBuilder sb=new StringBuilder(" from first_order fo where fo.comid=?");
			lfo = super.findList(sb.toString(), comid);//镇压警告
			return lfo;
		} catch (Exception e)
		{
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> sealNotRegistered(int areaid) {
		// TODO Auto-generated method stub
		List<Position> postions = null;
		List<Map<String, Object>> listTw = null;
		Map<String, Object> mapTw = null;
		int countStaZero;//这个值是否只能存一个？？？？？？
		try
		{
			StringBuilder sb=new StringBuilder(" from Position po where po.areaid=?");
			postions = super.findList(sb.toString(), areaid);//镇压警告
			listTw = new ArrayList<Map<String,Object>>();
			for(Position p : postions)//遍历站点统计站点总数：未注册的各站点总数
			{
				mapTw = new HashMap<String, Object>();//注意下一行中查询时间:s.seaTime>'2016-12-10'
				StringBuilder sb0 = new StringBuilder(" select count(*)from NewErrors ne where ne.time>'2016-12-10' and ne.re='sf-(二维码未注册)' and ne.posid="+p.getPosId());//查询施封表new_errors中：sf-(二维码未注册)，并且时间2016-12-11 00:00:00之后的数据
				countStaZero = super.queryRowCount(sb0.toString());
				//System.out.println("2016-12-10之后>>>sf-(二维码未注册)站点总数："+p.getPosName()+":"+(countStaZero));
				mapTw.put(p.getPosName(), countStaZero);
				listTw.add(mapTw);
			}
			return listTw;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("站点统计---sf-(二维码未注册)---出错");
			return null;
		}
	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> unlockNotsealed(int areaid) {
		// TODO Auto-generated method stub
		List<Position> postions = null;
		List<Map<String, Object>> listTw = null;
		Map<String, Object> mapTw = null;
		int countStaZero;//这个值是否只能存一个？？？？？？
		try
		{
			StringBuilder sb=new StringBuilder(" from Position po where po.areaid=?");
			postions = super.findList(sb.toString(), areaid);//镇压警告
			listTw = new ArrayList<Map<String,Object>>();
			for(Position p : postions)
			{
				mapTw = new HashMap<String, Object>();//注意下一行中查询时间:s.seaTime>'2016-12-10'
				StringBuilder sb0 = new StringBuilder(" select count(*)from FoundSiteUnlockNotSeal fs where fs.fsun_unfreeze_time>'2016-12-10' and fs.fsun_sela_posname="+"'"+p.getPosName()+"'");//查询施封表new_errors中：sf-(二维码未注册)，并且时间2016-12-11 00:00:00之后的数据
				countStaZero = super.queryRowCount(sb0.toString());
				//System.out.println("2016-12-10之后>>>jf-(封签未施封)站点总数："+p.getPosName()+":"+(countStaZero));
				mapTw.put(p.getPosName(), countStaZero);
				listTw.add(mapTw);
			}
			return listTw;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("站点统计---jf-(二维码未施封)---出错");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> serachTotalPositionByArea(int areaid) 
	{
		
		List<Position> postions = null;
		List<Map<String, Object>> listTw = null;
		Map<String, Object> mapTw = null;
		try 
		{
			StringBuilder sb=new StringBuilder(" from Position po where po.areaid=?");//1.根据这个片区id查询该片区下所有站点id和所有站点名称
			postions = super.findList(sb.toString(), areaid);//镇压警告
			listTw = new ArrayList<Map<String,Object>>();
			for(Position p : postions)//该片区下所有站点id和所有站点名称再通过position_inventory 站点库存表去查每一个站点已经得到封签总数
			{
				mapTw = new HashMap<String, Object>();
				StringBuilder sb1=new StringBuilder("SELECT (SUM(bag_count)*50) as count1,SUM(bag_code_count) as count2 from `position_inventory`where position_name = "+"'"+p.getPosName()+"'");
				int co = 0;
				try 
				{
					co = super.queryCountBySql_co(sb1.toString());
					mapTw.put(p.getPosName(), co);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				listTw.add(mapTw);
			}
			return listTw;
			
		} catch (Exception e) 
		{
			System.out.println("查询片区下各个站点总数目出错");
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> searchAreaNameByAreaid(int areaid) 
	{
		List<shoufa_person> lfo = null;
		try 
		{
			StringBuilder sb=new StringBuilder(" from shoufa_person fo where fo.areaid=?");
			lfo = super.findList(sb.toString(), areaid);//镇压警告
			return lfo;
		} catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findAreaIdByComId(int comid) 
	{
		List<Area> arlist = null;
		StringBuilder sb = null;
		try 
		{
			sb=new StringBuilder(" from Area ar where ar.com_id=?");
			arlist = super.findList(sb.toString(), comid);//镇压警告
			return arlist;
		} catch (Exception e) //查询不出来，返回null，注意监测
		{
			return null;
		}	
	}

	

//	@Override
//	public List<first_order> findFirst_order() {
//		StringBuilder sb=new StringBuilder("from first_order");
//		return super.find(sb.toString(),null);
//	}
//
//	@Override
//	public void insertFirst_order(first_order first_order) {
//		super.insert(first_order);
//		
//	}
//
//	@Override
//	public void orderModify(Integer id, Integer num) {
//		StringBuilder sb = new StringBuilder("update  first_order p set p.fahuo_number=?");
//		sb.append(" where p.id=? ");
//		try {
//			super.updateByHql(sb.toString(), num,id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public boolean checkOrderStatus(Integer id, Integer status) {
//		//这个hql可能错误
//		StringBuilder sb=new StringBuilder("select count(id) from first_order where id=? and status=?");
//		Integer count=super.queryRowCount(sb.toString(), id, 0);
//		if(count>0){
//			return true;//不能更新
//		}
//		return false;//可以更新
//	}

	
	
}
