package com.ltmcp.action;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.action.DistributionAction;
import com.ltmcp.service.AdminService;
import com.ltmcp.service.InventoryService;
import com.ltmcp.service.firstOrderService;


@SuppressWarnings("serial")
public class InventoryAction extends BaseAction
{
	private String login_account;//ֱ�ӻ�ȡǰ�˴��ݲ�������¼�˺����ƣ��жϵ����������ڲ�ѯ
	private Integer comid;//��comid��ʾ�����أ���֦�����ǹ㰲�����ǲ����˺ţ��ڵ�½��sesson��ȥ��ȡ���comid
	private AdminService adminService;//��½admin
	private InventoryService inventoryService;//Ҫget��set������������action.xml�г���
    Integer jinjiangYoukuCount;
  

	public AdminService getAdminService()
	{
		return adminService;
	}

	public void setAdminService(AdminService adminService) 
	{
		this.adminService = adminService;
	}
	public Integer getComid() 
	{
		return comid;
	}

	public void setComid(Integer comid) 
	{
		this.comid = comid;
	}
	public String getLogin_account() 
	{
		return login_account;
	}

	public void setLogin_account(String login_account) 
	{
		this.login_account = login_account;
	}
	public InventoryService getInventoryService()
	{
		return inventoryService;
	}

	public void setInventoryService(InventoryService inventoryService)
	{
		this.inventoryService = inventoryService;
	}

	/**
	 *ͳ�ƿ�棺�����ֹ�˾
	 * @param
	 */
	public void queryTheCompany()
	{
		/**
		 * 1.Ƭ��������Ӽ�Ϊʹ�÷�ǩ��Ŀ
		 *    ����comid��ѯ��Ƭ������  
		 *    ����comid��ֵ��ȥshoufa_person�в�ѯ��Ӧ���ˣ����磺xxx�Ƴ��������� ע���ڸñ���ֻ��com_idֻ��3����Чֵ1�����Ƴ���,5���Ƴ���,3��xxxx�Ƴ��� 
		 *    ���ݸ�Ƭ�����Ʋ�ѯ�����ܼƣ�Ƭ��������secondorder
		 *    
		 * 2.���ҷ��ŵ�����Ϊfirst_order����
		 * 
		 */
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//������ʹ��httpSession�����Ͳſ��Ի�ȡServletActionContext.getRequest().getSession()
		List<shoufa_person> lsp = null;
		List<Area> secondlist = null;
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			//System.out.println("��ӡ��ȡcomid:"+admin.getCompany().getComId()+"����ӡ��ȡ��¼�ʻ�����"+admin.getAdmName());
			lsp = inventoryService.findCompanyPersonNameByComid(comid);
			String companyAddress = null;
			for(shoufa_person f : lsp)
			{
				companyAddress = f.getAddress();
				//System.out.println("��ӡ������˾Ӫ���ƿƳ�����:"+f.getName());
			}
			//�߼���appͳ��������ͬ��˾id����ѯƬ�����ٲ�������list��map����
			secondlist = inventoryService.serachAreaAddress(comid);//���ݹ�˾idȥ��ѯ����Ƭ��
			listTw = new ArrayList<Map<String,Object>>();
			int counts = 0;
			mapTw = new HashMap<String, Object>();
			for(Area a : secondlist)//Ƭ������totalcount
			{
				int totalAreaGainByCompany = inventoryService.countByArea_name(a.getArea_name()+"Ƭ��");//�����ǲ���ֱ�Ӳ�ѯ���֮��count1+count2���ش�totalAreaGainByCompany
				//����Ҫ����Ƭ����idȥ��position_inventory����ȥ��ѯ����Ƭ�����·���վ���������ע�⣺��Ѫ�Ľ��Ϳ�û�н��зַ�������ֱ�ӴӶ����ֹ�˾����ȡ��ʹ��
				//System.out.println("��ӡtotalcount��"+totalAreaGainByCompany);
				//mapTw.put("totalAreaGainByCompany", totalAreaGainByCompany);//Ƭ���Ӷ����ֹ�˾���յ�����
				counts = totalAreaGainByCompany+counts;
			}
			//System.out.println("��ӡ�����ֹ�˾�·�Ƭ��������"+counts);
			mapTw.put("alreadyDistributed", counts);//Ӫ���Ʒ���Ƭ���Ѿ�����������      ��ʹ����
			
			//��ȥfirst_order���л�ȡ�ֹ�˾��������������comid
			List<first_order> folist = inventoryService.companyContByFirstOrder(comid);
			int countsCompany = 0;
			for(first_order fo : folist)
			{
				countsCompany = fo.getFahuo_number() + countsCompany;
			}
			mapTw.put("countsCompany", countsCompany);//                      ������
			mapTw.put("companyInventory", countsCompany-counts);//�����ֹ�˾���    ��ʣ��
			mapTw.put("companyAddress", companyAddress);//                    ����ַ
			mapTw.put("time", new Timestamp(System.currentTimeMillis()));//   ��ʱ��
			listTw.add(mapTw);
			super.outPrintJsonByArray(listTw);
		}catch (Exception e)//���в����쳣�����������Թ㰲�����
		{
				System.out.println("��ȡadmin������߲�ѯ���ݳ������ͳ�ƶ����ֹ�˾Ӫ�����ܼƳ���");
				super.getPringWriter().print("��ȡ���ݳ���");
		}
		
			
	}
	
	
	/***
	 * ͳ�ƿ�棺Ƭ��
	 * @param
	 */
	public void queryTheInventory()//ͳ��Ƭ��
	{
		/**
		 * 1.��ѯ��Ƭ�����������������ǵ�¼ʱ���ȡ����֦�������ǹ㰲�������������˺ŵ�¼
		 *      ����comid��ѯ��Ƭ������   ���
		 *      ����comid��ֵ��ȥshoufa_person�в�ѯ��Ӧ���ˣ����磺xxx�Ƴ��������� ע���ڸñ���ֻ��com_idֻ��3����Чֵ1�����Ƴ���,5���Ƴ���,3��xxxx�Ƴ��� ���
		 *      ���ݸ�Ƭ�����Ʋ�ѯ�����ܼƣ�Ƭ��������secondorder ���
		 *      
		 * 2.Ƭ�������������ֹ�˾�·�����Ƭ���м�¼������  
		 * 		���ݸ�Ƭ�����Ʋ�ѯ�����ܼƣ�Ƭ��������secondorder ���
		 *      
		 *      
		 * 
		 * 
		 * 3.Ƭ���ѷ���������ѯ����Ƭ���·�����Ƭ���¹����վ������
		 * 	          ����Ƭ��id��ѯ�·�վ�����ݣ���ע����Ϳ�����     ���
		 *      ���ܲ�ѯ����ͳ��secondorder�еĲ�ѯ��ʽ ȥ��ѯposition_incentory��     ���         
		 *      position_incentory����ͳ�ƣ�Ƭ�����Ÿ�������վ�������ۼӣ�ͳ�Ƶ��Ǵ��Ӻ�����2������µ��ܺ�������ǩ��Ŀ   ���
		 * 
		 * 
		 *  4. Ƭ�����=Ƭ������-Ƭ���ѷ�����     ���
		 *     Ƭ����������         ���
		 *     ���������
		 *     �������������0  ���
		 *     �������������0������δ��������ȷ�������������Ŀ����ԣ������������ݱ������£���Ϊ����  ���          
		 *     ����0����������ǽ��Ϳ�  �����С����� ������ð���Ƭ�����µ�����վ��
		 *     С��0������ǣ��Ӷ����ֹ�˾����ȡ��ʱ�򲻾����ַ�app��Ƭ��ֱ����ȡ�������·�վ���ʱ��ʹ�÷ַ�app
		 */
		//System.out.println("�����ѯƬ��-------");
		Timestamp ts=new Timestamp(System.currentTimeMillis());//ʱ���ʽ��
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//������ʹ��httpSession�����Ͳſ��Ի�ȡServletActionContext.getRequest().getSession()
		List<shoufa_person> lsp = null;
		List<Area> secondlist = null;
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			//System.out.println("��ӡ��ȡcomid:"+admin.getCompany().getComId()+"����ӡ��ȡ��¼�ʻ�����"+admin.getAdmName());
			lsp = inventoryService.findCompanyPersonNameByComid(comid);
			for(shoufa_person f : lsp)
			{
				//System.out.println("��ӡ������˾Ӫ���ƿƳ�����:"+f.getName());
			}
			//�߼���appͳ��������ͬ��˾id����ѯƬ�����ٲ�������list��map����
			secondlist = inventoryService.serachAreaAddress(comid);//���ݹ�˾idȥ��ѯ����Ƭ��
			listTw = new ArrayList<Map<String,Object>>();
			for(Area a : secondlist)//Ƭ������totalcount
			{
				mapTw = new HashMap<String, Object>();
				mapTw.put("areaId", a.getId());//Ƭ��id
				mapTw.put("name", a.getArea_name()+"Ƭ��");//����
				mapTw.put("managerName", a.getArea_name()+"����");//��ȡ�ˣ����磺�ʺ;���������Ӧ���������ģ��������
				int totalAreaGainByCompany = inventoryService.countByArea_name(a.getArea_name()+"Ƭ��");//�����ǲ���ֱ�Ӳ�ѯ���֮��count1+count2���ش�totalAreaGainByCompany
				//����Ҫ����Ƭ����idȥ��position_inventory����ȥ��ѯ����Ƭ�����·���վ���������ע�⣺��Ѫ�Ľ��Ϳ�û�н��зַ�������ֱ�ӴӶ����ֹ�˾����ȡ��ʹ��
				//System.out.println("��ӡtotalcount��"+totalAreaGainByCompany);
				//jinjiangYoukuCount = totalAreaGainByCompany;
				mapTw.put("totalAreaGainByCompany", totalAreaGainByCompany);//Ƭ���Ӷ����ֹ�˾���յ�����
				int totalSiteGainByArea = inventoryService.countByArea_id(a.getId());//Ƭ���·����Թ���վ������
				
				int areaInventory = totalAreaGainByCompany-totalSiteGainByArea;
				//System.out.println("��ӡ��ֵ:"+areaInventory);
				if(areaInventory == 0 || areaInventory < 0)//�������������0��ʾƬ�����������ǩ
				{                                          //С��0������ǣ��Ӷ����ֹ�˾����ȡ��ʱ�򲻾����ַ�app��Ƭ��ֱ����ȡ�������·�վ���ʱ��ʹ�÷ַ�app,����
					                                       //�����������Ϊ0��Ƭ������õ����������·�������һ���࣬����Ϊ��ֵΪ0 �����
					mapTw.put("totalSiteGainByArea", totalAreaGainByCompany);//�����·�����վ������Ϊ�ӴӶ����ֹ�˾���յ��������Ȳ���������
					mapTw.put("areaInventory", 0);
					mapTw.put("time", new Timestamp(System.currentTimeMillis()));
					
				}else if(areaInventory == totalAreaGainByCompany)//������ǹ�Ѫ�𽭿��
				{	
					/**
					 * վ�㣺���Ϳ⣨����վ��ҲӦ�ò�ࣩ
					 * ��һ�������Ϳ�ʹ����������ѯ���Ϳ�ʹ�÷�ǩ�����
					 * �ڶ�����
					 *       1.ֻʩ��Ȼ����δ��ⱻ��������վ���߽��Ϳ�����Լ���ɨ����������ǩ��������Զ�����У�����ɡ�    +  ��ǩ������������ ����ɡ�  +  �쳣��Ŀ3���쳣ֻ��ͳ����Щ�쳣��ͨ��׷�ݣ�����ȴ�ǣ���ά�룩һ�������ҵ�û��ʩ��Ľṹ�У�û��ע�ᣬ�Խ��Է�����ָ���δ��������Ҽ���ʱ������
					 *       2.����3���쳣ֻ��ͳ���쳣��Ŀ��
					 *       
					 *       �쳣��Ŀ��1����ʩ������Ч������ͳ��
					 *       �쳣��Ŀ��2����������������𻵣��޷�ͳ��
					 *       �쳣��Ŀ��3�������������ݿ�һ�ű���ͳ��һ�ּ�Ϊ��Ч��������ͳ����Ŀ�����ͬʱ����δʩ�⣩����д���쳣���ݵ�ʱ��ͬʱȥ��Ѱ�ܲ鵽���쳣�����δʩ�⣬������������ܣ�������׷�ݹ����ҵ���ص����ݣ�д��һ�ű���   
					 *       
					 * �ڶ��������Ϳ��棺�����ֹ�˾�ַ��������������Ϳ⣩-���Ϳ�ʹ����������ǩ������������+�쳣��Ŀ���쳣��Ŀ����
					 */
					int areaid = a.getId();//3���ǽ�Ƭ��
					//�ڶ�����3��С����쳣��Ŀ
					List<Map<String, Object>> unlockNotsealed = inventoryService.unlockNotsealed(areaid);
					int unlockNotsealeds = 0;
					for (Map<String, Object> m : unlockNotsealed)  
				    {  
				      for (String k : m.keySet())  
				      {  
				    	  unlockNotsealeds = (Integer) m.get(k);
				    	 // System.out.println("jj��ӡվ��jf-(��ǩδʩ��)����:"+k + " : " + m.get(k));//��ȡ����ֵ  
				      }  
				    }  
					
					//�ڶ�����1��С����쳣��Ŀ
					List<Map<String, Object>> sealNotRegistered = inventoryService.sealNotRegistered(areaid);
					int sealNotRegistereds = 0;
					for (Map<String, Object> m : sealNotRegistered)  
				    {  
				      for (String k : m.keySet())  
				      {  
				        sealNotRegistereds = (Integer) m.get(k);
				        //System.out.println("jj��ӡվ��sf-(��ά��δע��)����:"+k + " : " + m.get(k));//��ȡ����ֵ  
				      }  
				    }  
					//�ڶ���1С���еģ���Զ�����У�����ɡ�    +  ��ǩ������������ ����ɡ�
					List<Map<String, Object>> periodNormallyEndsNumber = inventoryService.periodNormallyEndsNumber(areaid);//����Զ�����У�    +  ��ǩ��������������һ����ȷ��վ�㣬����ΪƬ��id	������ֵ��list��վ��ͳ�Ƶ���������
					for (Map<String, Object> m : periodNormallyEndsNumber)  
				    {  
				      for (String k : m.keySet())  
				      {  
				       // System.out.println("jj2016-12-10֮��>>>��ӡվ������ʹ��ʩ����Ŀ:"+k + " : " + m.get(k));  
				        mapTw.put("totalSiteGainByArea", (Integer)m.get(k)+sealNotRegistereds+unlockNotsealeds);//ǧ��ע�������ǣ�������Ƭ���·����������ǽ��Ϳ�����ʹ�õ�����
				        mapTw.put("areaInventory", totalAreaGainByCompany-(Integer)m.get(k)-sealNotRegistereds-unlockNotsealeds);
				        mapTw.put("time", new Timestamp(System.currentTimeMillis()));
				      }  
				    }  
				}else
				{
					mapTw.put("totalSiteGainByArea", totalSiteGainByArea);
					mapTw.put("areaInventory", areaInventory);
					mapTw.put("time", new Timestamp(System.currentTimeMillis()));
				}
				listTw.add(mapTw);
			}
			super.outPrintJsonByArray(listTw);
		} catch (Exception e)//���в����쳣�����������Թ㰲�����
		{
			
			super.getPringWriter().print("��ȡ���ݳ���");
		}
	}
	
	
	int areaid;
	
	public int getAreaid() 
	{
		return areaid;
	}

	public void setAreaid(int areaid) 
	{
		this.areaid = areaid;
	}
	/***
	 * ���ͳ�ƣ�վ��
	 * Я������Ƭ��id����˷�����
	 */
	public void queryThePositionInventory()
	{
		/**
		 * վ����=��һ��-�ڶ�����
		 * ��һ����վ��ͨ��Ƭ��id�����ѯÿһ��Ƭ����վ����ȡ����   ���
		 *      1.Ƭ��id���뵽���ݲ���
		 *      2.�����Ƭ���µ�վ��posid��posname
		 *      3.����posid��posname��ȥ�鵽������select count(er.id) from position_inventory er where er.pianqu_id=1 and er.position_name='��������վ'
		 *      4.�ڱ�����Щ�У���Ϊ�е������д�װ����װ�����50��500�����
		 *      5.�ۼ�
		 *      6.�ó����
		 *      
		 * �ڶ����� �����ʩ�����ֻҪ���ּ��ɼ�����ֻʩ��Ȼ����δ��ⱻ��������վ���߽��Ϳ�����Լ���ɨ����������ǩ��������Զ�����У�����ɡ�    +  ��ǩ������������ ����ɡ� 
		 *       1.ֻʩ��Ȼ����δ��ⱻ��������վ���߽��Ϳ�����Լ���ɨ����������ǩ��������Զ�����У�����ɡ�    +  ��ǩ������������ ����ɡ�  +  �쳣��Ŀ3���쳣ֻ��ͳ����Щ�쳣��ͨ��׷�ݣ�����ȴ�ǣ���ά�룩һ�������ҵ�û��ʩ��Ľṹ�У�û��ע�ᣬ�Խ��Է�����ָ���δ��������Ҽ���ʱ������
		 *       2.����3���쳣ֻ��ͳ���쳣��Ŀ��
		 *       
		 *       �쳣��Ŀ��1����ʩ������Ч������ͳ��
		 *       �쳣��Ŀ��2����������������𻵣��޷�ͳ��
		 *       �쳣��Ŀ��3�������������ݿ�һ�ű���ͳ��һ�ּ�Ϊ��Ч��������ͳ����Ŀ�����ͬʱ����δʩ�⣩����д���쳣���ݵ�ʱ��ͬʱȥ��Ѱ�ܲ鵽���쳣�����δʩ�⣬������������ܣ�������׷�ݹ����ҵ���ص����ݣ�д��һ�ű���   
		 *       
		 * �ڶ��������Ϳ��棺�����ֹ�˾�ַ��������������Ϳ⣩-���Ϳ�ʹ����������ǩ������������+�쳣��Ŀ���쳣��Ŀ����
		 */
		
		
		
		
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		
		
		List<Map<String, Object>> listPositon = null;//serachTotalPositionByArea(areaid)�������ʹ�õ�
		try
		{
			//���δʩ��
			List<Map<String, Object>> unlockNotsealed = inventoryService.unlockNotsealed(areaid);
			int unlockNotsealeds = 0;
			for (Map<String, Object> m : unlockNotsealed)  
		    {  
		      for (String k : m.keySet())  
		      {  
		    	  unlockNotsealeds = (Integer) m.get(k);
		    	  //System.out.println("2016-12-10֮��>>>��ӡվ��jf-(��ǩδʩ��)׷�ݵ�վ������:"+k + " : " + m.get(k));//��ȡ����ֵ  
		      }  
		    }  
			//��������
			List<Map<String, Object>> periodNormallyEndsNumber = inventoryService.periodNormallyEndsNumber(areaid);
			
//			for (Map<String, Object> m : periodNormallyEndsNumber)  
//		    {  
//		      for (String k : m.keySet())  
//		      {  
//		       // System.out.println("2016-12-10֮��>>>��ӡ����������Ŀ:"+k + " : " + m.get(k));  
//		       
//		      }  
//		    }  
			//ʩ������Ч
			List<Map<String, Object>> sealNotRegistered = inventoryService.sealNotRegistered(areaid);
//			for (Map<String, Object> m : sealNotRegistered)  
//		    {  
//		      for (String k : m.keySet())  
//		      {  
//		    	  //System.out.println("2016-12-10֮��>>>��ӡʩ������Ч����Ŀ:"+k + " : " + m.get(k));  //System.out.println("��ӡվ��sf-(��ά��δע��)����:"+k + " : " + m.get(k));//��ȡ����ֵ  
//		      }  
//		    }  
			//վ������
			listPositon = inventoryService.serachTotalPositionByArea(areaid);	
			int alreadyInUsedPosition = 0;
			listTw = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> m : listPositon)  
		    { 
				mapTw = new HashMap<String, Object>();
		      for (String k : m.keySet())  
		      {  
		       // System.out.println("����Ŀ<<<2016-12-10֮��>>>��ӡվ������Ŀ:"+k + " : " + m.get(k));
		        int mk = Integer.parseInt(String.valueOf(m.get(k)));
		        String jinjiangName = k;
		        if( "���Ϳ�".equals(jinjiangName) && mk == 0)
		        {
		        	//����Ƭ��id��ѯƬ������
		        	List<shoufa_person> areaName = null;
		        	int totalAreaGainByCompany = 0;
		        	try 
		        	{
		        		areaName = inventoryService.searchAreaNameByAreaid(areaid);
		        		for(shoufa_person sp : areaName)
		        		{
		        			totalAreaGainByCompany= inventoryService.countByArea_name(sp.getAddress());
		        			///888888888888
		        			mk = totalAreaGainByCompany;
		        		}
					} catch (Exception e) 
					{
						// TODO: handle exception
					}
		        	
		        	
		        }
		        for (Map<String, Object> mNormally : periodNormallyEndsNumber)  
			    {  
			      for (String kNormally : mNormally.keySet())  
			      {  
			        //System.out.println("2016-12-10֮��>>>��ӡ����������Ŀ:"+kNormally + " : " + mNormally.get(kNormally)); 
			        if(k == kNormally)
			        {
			        	//int i = Integer.parseInt(String.valueOf(m.get(k)))-Integer.parseInt(String.valueOf(mNormally.get(kNormally)));
			        	//alreadyInUsedPosition = Integer.parseInt(String.valueOf(mNormally.get(kNormally)));
			        	
			        	for (Map<String, Object> msealNot : sealNotRegistered)  
					    { 
			        	   
					      for (String ksealNot : msealNot.keySet())  
					      {
					    	  //System.out.println("2016-12-10֮��>>>��ӡʩ������Ч����Ŀ:"+ksealNot + " : " + msealNot.get(ksealNot));
					    	  //int l = i-Integer.parseInt(String.valueOf(msealNot.get(ksealNot)));
					    	  if(kNormally == ksealNot)
					    	  {
					    		for (Map<String, Object> munlockNotsealed : unlockNotsealed)  
					  		    {  
					  		      for (String kunlockNotsealed : munlockNotsealed.keySet())  
					  		      {  
					  		    	  if(ksealNot == kunlockNotsealed)
					  		    	  {
					  		    		  alreadyInUsedPosition = Integer.parseInt(String.valueOf(mNormally.get(kNormally)))+Integer.parseInt(String.valueOf(msealNot.get(ksealNot)))+Integer.parseInt(String.valueOf(munlockNotsealed.get(kunlockNotsealed)));
					  		    		  int positionInventory = mk-alreadyInUsedPosition;
					  		    		  
					  		    		  //System.out.println("20161210֮��>>>��ӡվ��������:"+k + " , ���:" + positionInventory);//��ȡ����ֵ  
					  		    		  mapTw.put("positionName", k);                                          //վ������
					  		    		  mapTw.put("positionCount", mk);                                        //վ���ǩ����
					  		    		  mapTw.put("alreadyInUsedPositionCount", alreadyInUsedPosition);        //վ���ǩʹ������
					  		    		  //mapTw.put("positionInventory", positionInventory);                   
					  		    		  mapTw.put("time", new Timestamp(System.currentTimeMillis()));          //ʱ��
					  		    		  if(positionInventory == 0 || positionInventory<0)
					  		    		  {
					  		    			mapTw.put("positionInventory", "δʹ�÷ַ����ܣ��޷�ͳ�Ƹ�վ����");//��ֵС��0���
					  		    		  }else
					  		    		  {
					  		    			mapTw.put("positionInventory", positionInventory);  //վ����
					  		    		  }
					  		    	  }
					  		    	  
					  		    	  
					  		      }  
					  		    }  
					    		  
					    		  
					    	  }
					    	   //System.out.println("��ӡվ��sf-(��ά��δע��)����:"+k + " : " + m.get(k));//��ȡ����ֵ  
					      }  
					    }  
			        	
			        }
			       
			      }  
			    }  
		        
		      }
		    listTw.add(mapTw);
		    }  
			
			super.outPrintJsonByArray(listTw);
		} catch (Exception e)
		{
			e.printStackTrace();
			super.getPringWriter().print("��ȡ���ݴ���");
		}
	}
	
	/**
	 * ����
	 */
	public static void sort(List<Map<String, Object>> list,String key, int low1, int high1)
	{  
        int low = low1;  
        int high = high1;  
        Integer x = (Integer) list.get(low).get(key);  
        Map<String,Object> temp = list.get(low);  
        if(low1<high1)
        {  
            while(low<high)
            {  
                while(low<high&&(Integer)list.get(high).get(key)>=x)
                {  
                    high--;  
                }  
                list.set(low, list.get(high));  
                while(low < high && (Integer)list.get(low).get(key)<=x)
                {  
                    low++;  
                }  
                list.set(high, list.get(low));  
            }  
            list.set(low, temp);  
            if(low>low1) sort(list,key,low1,low-1);  
            if(high<high1) sort(list,key,low+1,high1);  
        }  
    }  
	/**
	 * ��ʱͳ��һ�����ܣ����δʩ������վ������
	 * ��ֽ����������ʩ��ɹ���ʧ�����ݣ����ɹ���ʧ�����ݵ�ͳ�����������Ϸ�����Щվ�㾭�����ֲ��ɹ���ʩ�⣨2���㣺��Ʒ���ⲻ�ɹ���δʩ�⣩������ֻͳ��δʩ����������
	 * inventory_statisticalUnlockNotSealedRow
	 */
	public void statisticalUnlockNotSealedRow()
	{
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//������ʹ��httpSession�����Ͳſ��Ի�ȡServletActionContext.getRequest().getSession()
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		List<Area> lareas = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			/**
			 * 1.���ݹ�˾id��ѯ���иù�˾������Ƭ��id
			 * 
			 * 2.�ڸ���Ƭ��idȥ��ѯ����Ƭ�������е�վ��
			 * 
			 * 3.����վ��id��ѯ���δʩ������ͳ��
			 */
			lareas = inventoryService.findAreaIdByComId(comid);
		    listTw = new ArrayList<Map<String,Object>>();
			for(Area a : lareas)
			{
				int  areaid = a.getId();
				List<Map<String, Object>> unlockNotsealed = inventoryService.unlockNotsealed(areaid);
				for (Map<String, Object> m : unlockNotsealed)  
			    {  
			      for (String k : m.keySet())  
			      {  
			    	  mapTw = new HashMap<String, Object>();
			    	 //unlockNotsealeds = (Integer) m.get(k);
			    	  System.out.println("��ӡվ��jf-(��ǩδʩ��)����:"+k + " : " + m.get(k));//��ȡ����ֵ
			    	  
			    	  mapTw.put("positionName", k);                                    //վ������
			    	  mapTw.put("counts", m.get(k));                                   //��վ��δʩ������
			    	  mapTw.put("time", new Timestamp(System.currentTimeMillis()));    //ʱ��
			    	  listTw.add(mapTw);
			      }  
			    }  
			}
			sort(listTw, "counts", 0, listTw.size()-1);//�������ã���Ҫ�����List<Map<String, Object>>��map��������ֶ����ƣ���ʼ����㣬���������
			super.outPrintJsonByArray(listTw);
		}catch (Exception e)//���в����쳣�����������Թ㰲�����
		{
				//e.printStackTrace();
				System.out.println("��ȡadmin������߲�ѯ���ݳ���");
				super.getPringWriter().print("��ȡ���ݳ���");
		}
		
			
	
		
	}
	/***
	 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
	 * @param
	 */
	//public void orderModify(){}
	/***
	 * web��չʾ��������Ӷ���֮��չʾ��û������ҳ����
	 * @param
	 */
	//public void list(){}
}
