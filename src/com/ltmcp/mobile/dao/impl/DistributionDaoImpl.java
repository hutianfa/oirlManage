package com.ltmcp.mobile.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Area_manager_inventory;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.dao.CarMobileDao;
import com.ltmcp.mobile.dao.DistributionDao;

public class DistributionDaoImpl extends BaseDaoHibImpl implements DistributionDao {

	@Override
	public boolean checkDisLogin(String username, String password) {
		StringBuilder sb=new StringBuilder("select count(id) from shoufa_person");
		sb.append(" where username=? and password=?");
		Integer count=super.queryRowCount(sb.toString(), username, password);
		if(count>0){
			return true;
		}
	    return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> serachPersonAddressorName(String username, String password) {
		StringBuilder sb=new StringBuilder(" from shoufa_person sp where sp.username=? and sp.password=?");
		return super.findList(sb.toString(),username,password);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> serachAreaAddressor(int com_id) {
		StringBuilder sb=new StringBuilder(" from Area ar where ar.com_id=?");
		return super.findList(sb.toString(),com_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> serachPostionAddressor(int areaid) {
		StringBuilder sb=new StringBuilder(" from Position po where po.areaid=?");
		return super.findList(sb.toString(),areaid);
	}

	@Override
	public boolean checkBagCode(String bagCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Dbc_BagCodeBind");
		sb.append(" where bag_code=?");
		Integer count=super.queryRowCount(sb.toString(), bagCode);
		if(count>0){
			return true;
		}
	    return false;
	}

	@Override
	public boolean checkBoxCode(String boxCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Inventor_BoxCode");
		sb.append(" where box_code=?");
		Integer count=super.queryRowCount(sb.toString(), boxCode);
		if(count>0){
			return true;
		}
	    return false;
	}

	@Override
	public void saveSecond_order(Second_order so) {
		System.out.println("���뱣�����Ӷ�ά�����շ���");
		super.saveOrUpdate(so);
		//super.insertcode(so);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> findAddressByPianquId(int pianquId) {
		StringBuilder sb=new StringBuilder(" from shoufa_person sp where sp.areaid=?");
		return super.findList(sb.toString(),pianquId);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> findAddrressByZhandianId(int zhandianId) {
		System.out.println("����վ���жϣ�"+zhandianId);
		StringBuilder sb=new StringBuilder(" from Position po where po.posId=?");
		return super.findList(sb.toString(),zhandianId);//��ѹ����
	}

	@Override
	public void saveArea_manager_inventory(Area_manager_inventory ami) {
		System.out.println("���뱣��");
		//super.insertcode(ami);
		super.saveOrUpdate(ami);
		
	}
	
	@Override
	public void savePosition_inventory(position_inventory pi) {
		//super.insertcode(pi);
		super.saveOrUpdate(pi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DimensionalBarCode> findBagCodeByCode(String code) {
		StringBuilder sb=new StringBuilder(" from DimensionalBarCode db where db.freeze_content=?");
		return super.findList(sb.toString(),code);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dbc_BagCodeBind> findBoxCodeByFindbagCode(String findbagCode) {
		StringBuilder sb=new StringBuilder(" from Dbc_BagCodeBind db where db.bag_code=?");
		return super.findList(sb.toString(),findbagCode);//��ѹ����
	}

	@Override
	public boolean checkCodeNeiMa(String code) {
		StringBuilder sb=new StringBuilder("select count(id) from DimensionalBarCode");
		sb.append(" where freeze_content=?");
		Integer count=super.queryRowCount(sb.toString(), code);
		if(count>0){
			return true;
		}
	    return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<position_inventory> findPositionBybagCode(String findbagCode) {
		StringBuilder sb=new StringBuilder(" from position_inventory fi where fi.bagCode=?");
		return super.findList(sb.toString(),findbagCode);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<position_inventory> findPositionByboxCode(String findboxCode) {
		StringBuilder sb=new StringBuilder(" from position_inventory fi where fi.boxCode=?");
		return super.findList(sb.toString(),findboxCode);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Second_order> findSecondre_sh_addressInStationBybagCode(String findbagCode) {
		StringBuilder sb=new StringBuilder(" from Second_order so where so.bag_code=?");
		return super.findList(sb.toString(),findbagCode);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Second_order> findSecondre_sh_addressInStationByboxCode(String findboxCode) {
		StringBuilder sb=new StringBuilder(" from Second_order so where so.box_code=?");
		return super.findList(sb.toString(),findboxCode);//��ѹ����
	}

	@Override
	public boolean checkCodeWaiMa(String code) {
		StringBuilder sb=new StringBuilder("select count(id) from DimensionalBarCode");
		sb.append(" where unfreeze_content=?");
		Integer count=super.queryRowCount(sb.toString(), code);
		if(count>0){
			return true;
		}
	    return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DimensionalBarCode> findBagCodeByWaimaCode(String code) {
		StringBuilder sb=new StringBuilder(" from DimensionalBarCode db where db.unfreeze_content=?");
		return super.findList(sb.toString(),code);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findComIdByPianquId(int pianquId) {
		StringBuilder sb=new StringBuilder(" from Area ar where ar.id=?");
		return super.findList(sb.toString(),pianquId);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> findcompanyAddressBycomId(int comId) {
		StringBuilder sb=new StringBuilder(" from shoufa_person sp where sp.com_id=?");
		return super.findList(sb.toString(),comId);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> findSealedInformationBydimensionalBarCode_id(int dimensionalBarCode_id) {
		StringBuilder sb=new StringBuilder(" from Sealed where dimensionalBarCode.id=?");
		//                                  from  Sealed s join fetch s.aa a where  a.id=1
		return super.findList(sb.toString(),dimensionalBarCode_id);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Freeze> findFreezeBysealedId(int sealedId) {
		StringBuilder sb=new StringBuilder(" from Freeze where sealed.seaId=?");
		return super.findList(sb.toString(),sealedId);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> findPianQuIdByReceive_sh_address(String address) {
		StringBuilder sb=new StringBuilder(" from Position p where p.posName=?");
		return super.findList(sb.toString(),address);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> findPianQuIdByshoufa_personAddress(String address) {
		StringBuilder sb=new StringBuilder(" from shoufa_person sp where sp.address=?");
		return super.findList(sb.toString(),address);//��ѹ����
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<shoufa_person> findPianquIdByName(String name) {
		//System.out.println("��ӡname,nameΪ����ַ���������ݣ�"+name);
		List<shoufa_person> splist = null;
		StringBuilder sb=new StringBuilder(" from shoufa_person sp where sp.name=?");
		StringBuilder sb1=new StringBuilder("select count(id) from shoufa_person");
		sb1.append(" where name=?");//ע��������sb1
		int  sta = 0;
		try 
		{
			sta = super.queryRowCount(sb1.toString(), name);//���name�Ƿ���ڴ�����sta����0,����null����
		} catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
				
		if(sta>0)
		{
			splist = super.findList(sb.toString(), name);//��ѹ����
		} else
		{
			splist = null;
		}
		return splist;
	}

	@Override
	public Integer findCountByArea_name(String name) {
		//�������box_count��hbm.xml�����õ���bag_count
		StringBuilder sb=new StringBuilder(" select new map(so.SUM(box_count)*50) as count1,so.SUM(bag_code_count) as count2) from Second_order so wherer so.receive_sh_address=?");
		StringBuilder sb1=new StringBuilder("SELECT (SUM(bag_count)*50) as count1,SUM(bag_code_count) as count2 from `secondorder`where receive_sh_address = "+"'"+name+"'");
		//super.findList(sb.toString(), name);//��ѹ����
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
	//ff
	public Integer findCountByZhandian_name(String zhanDianName) {
		StringBuilder sb1=new StringBuilder("SELECT (SUM(bag_count)*50) as count1,SUM(bag_code_count) as count2 from `position_inventory`where position_name = "+"'"+zhanDianName+"'");
		//super.findList(sb.toString(), name);//��ѹ����
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
	public boolean checkBagCodeBySecondOrderAndPosition_inventory(String code) {
		StringBuilder sb=new StringBuilder("select count(id) from Second_order");
		sb.append(" where bag_code=?");
		StringBuilder sb1=new StringBuilder("select count(id) from position_inventory");
		sb1.append(" where bagCode=?");
		Integer count=super.queryRowCount(sb.toString(), code);
		Integer count1=super.queryRowCount(sb1.toString(), code);
		boolean sta = false;
		boolean sta1 = false;
		if(count>0)
		{
			sta = true;
		}else
		{
			sta = false;
		}
		if(count1>0)
		{
			sta1 =  true;
		}else
		{
			sta1 =  false;
		}
		if(sta || sta1)
		{
			return false;//ֻҪ��һ���������ɱ�ʾ�Ѿ��������˸�����
		}else
		{
			return true;
		}
	    
	}

	@Override
	public boolean checkBoxCodeBySecondOrderAndPosition_inventory(String code) {
		StringBuilder sb=new StringBuilder("select count(id) from Second_order");
		sb.append(" where box_code=?");
		StringBuilder sb1=new StringBuilder("select count(id) from position_inventory");
		sb1.append(" where boxCode=?");
		Integer count=super.queryRowCount(sb.toString(), code);
		Integer count1=super.queryRowCount(sb1.toString(), code);
		boolean sta = false;
		boolean sta1 = false;
		if(count>0)
		{
			sta = true;
		}else
		{
			sta = false;
		}
		if(count1>0)
		{
			sta1 =  true;
		}else
		{
			sta1 =  false;
		}
		if(sta || sta1)
		{
			return false;//ֻҪ��һ���������ɱ�ʾ�Ѿ��������˸�����
		}else
		{
			return true;
		}
	}
	
}
