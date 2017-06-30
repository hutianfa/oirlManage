package com.ltmcp.mobile.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.comparator.InvertibleComparator;

import com.ltmcp.action.BaseAction;
import com.ltmcp.action.ExcRecordAction;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Area_manager_inventory;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Inventor_BoxCode;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.biz.DistributionBiz;
import com.ltmcp.mobile.biz.Shoufa_personServiceBiz;
import com.ltmcp.mobile.biz.impl.Dbc_BagCodeBindBizImpl;
import com.ltmcp.mobile.biz.impl.SaveCodeBizImpl;
import com.ltmcp.mobile.biz.impl.caseCodeBindBizImpl;

import com.ltmcp.mobile.dao.impl.Dbc_BagCodeBindDaoImpl;
import com.ltmcp.mobile.dao.impl.DimensionalBarCodeMobileDaoImpl;
import com.ltmcp.mobile.dao.impl.caseCodeBindBizDaoImpl;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;


public class DistributionAction extends BaseAction{
	
	private DistributionBiz distributionBiz;
	private Shoufa_personServiceBiz shoufa_personServiceBiz;
	
	public Shoufa_personServiceBiz getShoufa_personServiceBiz() {
		return shoufa_personServiceBiz;
	}
	public void setShoufa_personServiceBiz(
			Shoufa_personServiceBiz shoufa_personServiceBiz) {
		this.shoufa_personServiceBiz = shoufa_personServiceBiz;
	}
	public DistributionBiz getDistributionBiz() {
		return distributionBiz;
	}
	public void setDistributionBiz(DistributionBiz distributionBiz) {
		this.distributionBiz = distributionBiz;
	}
	private String username;//�ַ���¼�˺�
	private String password;//�ַ���¼�˺�
	private Integer areaidTwo;
	private String address;//�ַ��˵�ַ
	private String sendAddress;//�ַ����ĸ��ط��ĵ�ַ��app������ѡ�񼴿ɣ�
	private String code;//��ά��
	public Integer getAreaidTwo() {
		return areaidTwo;
	}
	public void setAreaidTwo(Integer areaidTwo) {
		this.areaidTwo = areaidTwo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//�ַ���¼
	public void login(){
		//System.out.println("��ӡ�˺�������Ϣ�����˺ţ�"+username+";���룺"+password);
		//kk
		 try {
			 boolean sta = distributionBiz.checkDisLogin(username, password);//staΪtrue��ʾ�Ѿ����ڸ��˺ţ����Ե�¼
			// System.out.println("name:"+username+"; password:"+password);
			 //System.out.println("��ӡ�жϷַ���¼�˺������Ƿ���ڣ�"+sta);
			 if(sta){
				 //���ڲ�ѯ areaid��ֵ��������˾������Ƭ���������Ȱ��˺ͷַ����˵ص㷵�ظ�app������CarMobileAction���е�getSeaOrFreNum()������
				List<shoufa_person> firstlist = distributionBiz.serachPersonAddressorName(username, password);
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = null;//��Ϊ��firstlistȷʵֻ��һ����for����ֻ��һ��
				if(firstlist == null){
					super.getPringWriter().print("null");
				}else
				{
					//ArrayList<Map> disList = new ArrayList<Map>();
					for(shoufa_person f : firstlist){
						map = new HashMap<String, Object>();
						map.put("stain", "-2066");
						map.put("name", f.getName());//����
						map.put("address", f.getAddress());//��ַ
						map.put("username", f.getUsername());
						map.put("password", f.getPassword());
						map.put("comid", f.getCom_id());
						map.put("pid", f.getAreaid());
						//shoufa_personServiceBiz.saveShoufa_personInfoToSession(f);
						list.add(map);
					}
					//��ȡ���˺ź������Ӧ��areaid��ֵ����com_id�Ƿ���ֵ
					Integer areaid = null;
					Integer com_id = null;
					for(shoufa_person f : firstlist){
						areaid = f.getAreaid();
						com_id = f.getCom_id();
						}
					//System.out.println("������˾id��ֵ��"+com_id+"��Ƭ��idֵ��"+areaid);
					List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
					if(areaid==null || listTw==null){//areaidΪ�ձ�ʾΪ�����ֹ�˾��¼��������com_id��ֵ�ҵ��ֹ�˾������Ƭ��
						List<Area> secondlist = distributionBiz.serachAreaAddressor(com_id);
						Map<String,Object> mapTw = null;
						for(Area a : secondlist){
							mapTw = new HashMap<String, Object>();
							//mapTw.put("comid", " ");
							mapTw.put("id", a.getId());
							mapTw.put("area_name", a.getArea_name());
							
							listTw.add(mapTw);
						}
					}
					
					if(com_id == null || listTw==null){
						List<Position> secondlist = distributionBiz.serachPostionAddressor(areaid);
						for(Position p : secondlist){
							Map<String,Object> mapTw = new HashMap<String, Object>();
							//mapTw.put("pid", areaid);
							mapTw.put("id", p.getAreaid());
							mapTw.put("posName", p.getPosName());
							listTw.add(mapTw);
						}
					}
					
					try {
						JSONArray jsonArr = JSONArray.fromObject(firstlist);
						
						//super.outPrintJsonByArray(jsonArr);
						super.outPrintJsonByArray(list);
						//super.outPrintJsonByArray(listTw);
						
					} catch (Exception e) {
						super.getPringWriter().print("error"); 
					}
				}
				 
			 } else{
				 		super.getPringWriter().print(-2067);
			       }
		} catch (Exception e) {
			//System.out.println(e);
		}
		 //super.getPringWriter().print(-2001);
	}
	//�����ص�ѡ��2
	public void changeAddressTwo(){
		List<Position> firstlist = distributionBiz.serachPostionAddressor(areaidTwo);
		if(firstlist != null)
		{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> mapTw = null;
			for(Position p : firstlist)
			{
			    mapTw = new HashMap<String, Object>();
			    mapTw.put("arid", p.getPosId());
			    mapTw.put("id", p.getAreaid());
				mapTw.put("posName", p.getPosName());
				list.add(mapTw);
			}
			try 
			{
				super.outPrintJsonByArray(list);
			} catch (Exception e) {
				super.getPringWriter().print("error"); 
			}
		}else
		{
			super.getPringWriter().print(-2067);
		}
	}
	//�����ص��ѡ��
	public void changeAddress(){
		 try {
			 boolean sta = distributionBiz.checkDisLogin(username, password);
			 if(sta){
				List<shoufa_person> firstlist = distributionBiz.serachPersonAddressorName(username, password);
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = null;
				if(firstlist == null){
					super.getPringWriter().print("null");
				}else
				{
					
					for(shoufa_person f : firstlist){
						map = new HashMap<String, Object>();
						map.put("name", f.getName());//����
						map.put("address", f.getAddress());//��ַ
						
						list.add(map);
					}
					//��ȡ���˺ź������Ӧ��areaid��ֵ����com_id�Ƿ���ֵ
					Integer areaid = null;
					Integer com_id = null;
					for(shoufa_person f : firstlist){
						areaid = f.getAreaid();
						com_id = f.getCom_id();
						}
					List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
					if(areaid==null || listTw==null){      //areaidΪ�ձ�ʾΪ�����ֹ�˾��¼��������com_id��ֵ�ҵ��ֹ�˾������Ƭ��
						List<Area> secondlist = distributionBiz.serachAreaAddressor(com_id);
						Map<String,Object> mapTw = null;
						for(Area a : secondlist)
						{
							mapTw = new HashMap<String, Object>();
							mapTw.put("id", a.getId());
							mapTw.put("posName", a.getArea_name());
							listTw.add(mapTw);
						}
//							List<Position> treelist = distributionBiz.serachPostionAddressor(a.getId());
//							if(treelist != null)
//							{
//								int i = 0;
//								for(Position ps : treelist)
//								{
//									
//									mapTw.put("posName"+i, ps.getPosName());
//									mapTw.put("posName", ps.getPosName());
//                                    listTw.add(mapTw);
//                                    i++;
//								}
//							}else
//							    {
//									System.out.println(" ");
//							    }
							
						
//						for(Map m : listTw){
//							System.out.println(m.get("area_name"));
//						}
						for(Area a : secondlist)
						{
							//System.out.println("��ӡ�����˺Ż�ȡ���ĵ�ַ(�����ֹ�˾�µ�Ƭ��)��"+a.getArea_name());
						}
					}
					if(com_id == null || listTw==null){
						List<Position> secondlist = distributionBiz.serachPostionAddressor(areaid);
						for(Position p : secondlist){
							Map<String,Object> mapTw = new HashMap<String, Object>();
							mapTw.put("id", p.getPosId());
							mapTw.put("posName", p.getPosName());
							listTw.add(mapTw);
						}
					}
					
					try {
						super.outPrintJsonByArray(listTw);//���ص��ǵص�ѡ���json
						
					} catch (Exception e) {
						super.getPringWriter().print("error"); 
					}
				}
				 
			 } else{
				 		super.getPringWriter().print(-2067);
			       }
		} catch (Exception e) {
			//System.out.println(e);
		}
	}
	
	//����ύ�Ĵ��ӻ������ӵĶ�ά�� 
	public void checkCode()
	{
		//�пյĵ��ַ�����ȥsubstring����
		String codee= code;
		if(code == null)
		{
			super.getPringWriter().print(-2000);//�����쳣ɨ��Ϊ��
		}else
		{
			String co = codee.substring(0, 1);
			if("b".equals(co))
			{
				
				//��ֹ�ظ�ɨȥ��֤secondorder
				//��ֹ�ظ�ɨȥ��֤position_inventory
				boolean stas = distributionBiz.checkBagCodeBySecondOrderAndPosition_inventory(codee);//�������ʾ�Ѿ����ַ�stasΪfalse����������Ϊtrue
				//System.out.println(stas);
				if(!stas)
				{
					System.out.println("���Ϸ���-1999");
					super.getPringWriter().print(-1999);//�Ѿ���ɨ����˲��ѷַ�
				}
				boolean staOne = distributionBiz.checkBagCode(codee);
				if(staOne && stas)
				{
					super.getPringWriter().print(-666);//ɨ��ɹ�
				}
				    //System.out.println(" "+"���Ӷ�ά���Ƿ���ڣ�"+staOne);
			}else if("x".equals(co))
					{
				       //��ֹ�ظ�ɨȥ��֤secondorder
			           //��ֹ�ظ�ɨȥ��֤position_inventory
			            boolean stass = distributionBiz.checkBoxCodeBySecondOrderAndPosition_inventory(codee);//�������ʾ�Ѿ����ַ� 
			            if(!stass)
						{
							super.getPringWriter().print(-1999);//�Ѿ���ɨ����˲��ѷַ�
						}
						boolean staTwo = distributionBiz.checkBoxCode(codee);
						 
						if(staTwo && stass)//���������������д���
						{
							super.getPringWriter().print(-666);//ɨ��ɹ�
						}
						//System.out.println(" "+"���Ӷ�ά���Ƿ���ڣ�"+staTwo);
					}
				else{
						super.getPringWriter().print(-2067);//�������ӻ��ߴ��Ӷ�ά�룬������ɨ���������ӻ��ߴ��Ӷ�ά��
						//super.getPringWriter().print(-2069);
			     	}
		}
			
	}
	private String bagCode;//���Ӷ�ά��
	private String boxCode;//���Ӷ�ά��
	private String second_fh_person;
	private String second_fh_address;
	private String receive_sh_address;
	private String pianquId;//Ƭ��id
	private String zhandianId;//վ��id
	
	//ȷ���ύ    ���ַ�����,�ύ���������ݣ��ַ����˺ţ����룬�ַ����������ַ��˵�ַ�������ĵ�ַ��ɨ��������ӻ��Ǵ��Ӷ�ά�루�ѵ㣩��֤������ʱ�䣬һ��д�붩�����Ƭ����վ�㣩��������2�ţ�һ��Ƭ��һ��վ�㣩
	public void distributeInInventory()
	{
		System.out.println("����ȷ���ύ�Ĳ���");
		List<shoufa_person> firstlist = distributionBiz.serachPersonAddressorName(username, password);
		Integer areaid = null;
		Integer com_id = null;
		String  name = null;
		String  address = null;
		for(shoufa_person f : firstlist)
			{
				areaid = f.getAreaid();
				com_id = f.getCom_id();
				name = f.getName();
				address = f.getAddress();
			}
		//System.out.println("��ӡƬ��id��"+pianquId+"  ; ��ӡվ��id: "+zhandianId);
		if(areaid==null)//������˾��Ƭ���ύ����Ϣ��ǰ����������ɨ���ά��ɹ�������£���ɨ���ά���ʱ��Ҫ�ж϶�ά���Ƿ���ڣ��й����������,��ά���Ѿ��жϴ���֮���ύ��ȷ����
		{
			int zd = 99999;
			boolean zdi = false;
			try 
			{
				zd = Integer.parseInt(zhandianId);
			} catch (NumberFormatException e)
			{
				zdi = true;//��ʾ�����ֹ�˾û���÷ַ�app��û��ѡ��վ�㷢��
			}
			//System.out.println(zdi+"��ӡzdi");
			Area_manager_inventory ami  = null;
			Second_order so = null;
			String rsa = receive_sh_address;//�����1��5   �����ֹ�˾��id��Ƭ��id��վ��id
			       //System.out.println("���Ͻ����ж�Ƭ��id(pianquId)��վ��id(zhandianId)");//���ֻ��Ƭ���ĵ�id����ôҪд��Ƭ���ģ�Area_manager_inventory�������Ƭ����id����վ���id����ôֱ��д��second_order����
			if(zdi)//blΪtrue˵��zhandianIdת����intʧ��֤���������֣�app�˶����ֹ�˾ûѡ��վ�㷢��
			{
				int pq = Integer.parseInt(pianquId);
				List<shoufa_person> sp = distributionBiz.findAddressByPianquId(pq);
				String pqadress = null;
				String pqname = null;
				for(shoufa_person s: sp)
				{
					pqadress = s.getAddress();
					pqname = s.getName();
				}
				//String b = code.replaceAll("[\\[\\]]", ""); 
				String b = code;
				//System.out.println(b+":��ӡapp�˴�������ֵ�����ӻ��ߴ��Ӷ�ά��");
				String[] arr = b.split("_");         
				for(int i=0;i<arr.length;i++)
				{
					String codeThree = arr[i];
					System.out.println("��ӡ���յ��Ĵ��Ӷ�ά������:"+codeThree);
					String co = codeThree.substring(0, 1);
					//System.out.println(codeThree+":��ά��");
					if(codeThree.startsWith("b"))//distributionBiz.saveArea_manager_inventory(codee);
					{
						so = new Second_order();
						so.setSecond_fh_person(name);//�ַ�������
						so.setSecond_fh_address(address);//�ַ��˵�ַ
						so.setReceive_sh_address(pqadress);//�����ĵ�ַ(���ܷ������ﶼ�Ƕ�����˾�����˷��ģ���Ƭ�������Ϳ⣬վ�㶼���¼)
						so.setBag_code(arr[i]);//���Ӷ�ά��
						so.setBag_code_count(50);
						so.setFhtime(new Timestamp(System.currentTimeMillis()));//ʱ��
						
						ami = new Area_manager_inventory();
						ami.setManage_person(pqname);//Ƭ����������
						ami.setManage_address(pqadress);
						ami.setBag_code(arr[i]);
						ami.setBag_code_count(50);
						ami.setNowtime(new Timestamp(System.currentTimeMillis()));
						try 
						{
							distributionBiz.saveSecond_order(so);
							distributionBiz.saveArea_manager_inventory(ami);
						} catch (Exception e) {
							
						}	
					}else if(codeThree.startsWith("x"))//�ַ���[�˺ţ�����]�ж��õģ� �ַ����������ַ��˵�ַ     , �����ĵ�ַ��ɨ��������ӻ��Ǵ��Ӷ�ά�루�ѵ㣩��֤������ʱ��   
							{
						        so = new Second_order();
								so.setSecond_fh_person(name);//�ַ�������
								so.setSecond_fh_address(address);//�ַ��˵�ַ
								so.setReceive_sh_address(pqadress);//�����ĵ�ַ:Ƭ��
								so.setBox_code(arr[i]);//���Ӷ�ά��
								so.setBox_count(10);
								//System.out.println("�������뱣�����Ӷ�ά�뷽��");
								so.setFhtime(new Timestamp(System.currentTimeMillis()));//ʱ��
								//System.out.println(so.getFhtime());
							
								
								//��д����area_manager_inventory��
								ami = new Area_manager_inventory();
								ami.setManage_person(pqname);//Ƭ����������
								ami.setManage_address(pqadress);
								ami.setBox_code(arr[i]);
								ami.setBox_code_count(10);
								ami.setNowtime(new Timestamp(System.currentTimeMillis()));
								//System.out.println(ami.getManage_person());
								//System.out.println(ami.getBox_code());
								try 
								{
									distributionBiz.saveSecond_order(so);
									distributionBiz.saveArea_manager_inventory(ami);
									
								} catch (Exception e) 
								{
//									System.out.println(e);
//									//e.printStackTrace();
								}
							}
					    
						else{
								super.getPringWriter().print(-2067);//�������ӻ��ߴ��Ӷ�ά�룬������ɨ���������ӻ��ߴ��Ӷ�ά��
					     	}	
				}
				super.getPringWriter().print(-2066);//�ύ�ɹ�
			}
			if((zdi == false) && (pianquId != null))//������ܶ�����˾���Ϳ�
			{
				//System.out.println("Ƭ��id��վ��id�������ڿ�");
				int pq = Integer.parseInt(pianquId);
				int zhandian = Integer.parseInt(zhandianId);
				//System.out.println("վ�㣺"+zhandian);
				List<shoufa_person> sp = distributionBiz.findAddressByPianquId(pq);
				String pqadress = null;
				for(shoufa_person s: sp)
				{
					pqadress = s.getAddress();
					//System.out.println("Ƭ����"+pqadress);
				}
				List<Position>  zhandiantr = distributionBiz.findAddrressByZhandianId(zhandian);
				String zhandianress = null;
				for(Position p: zhandiantr)
				{
					zhandianress = p.getPosName();
					//System.out.println("վ�㣺"+zhandianress);
				}
				
				//String b = code.replaceAll("[\\[\\]]", ""); 
				String b = code;
				//System.out.println(b+":��ӡapp�˴�������ֵ�����ӻ��ߴ��Ӷ�ά��");
				String[] arr = b.split("_");
				for(int i=0;i<arr.length;i++)
				{
					String codeThree = arr[i];
					String co = codeThree.substring(0, 1);
					//System.out.println(codeThree+":��ά��");
					if(codeThree.startsWith("b"))//distributionBiz.saveArea_manager_inventory(codee);
					{
						so = new Second_order();
						so.setSecond_fh_person(name);//�ַ�������
						so.setSecond_fh_address(address);//�ַ��˵�ַ
						so.setReceive_sh_address(pqadress);//�����ĵ�ַ:Ƭ��д�����
						so.setBag_code(arr[i]);//���Ӷ�ά��
						so.setBag_code_count(50);
						so.setReceive_sh_addressInStation(zhandianress);//ʵ�ʷ����ǵ�վ��
						so.setFhtime(new Timestamp(System.currentTimeMillis()));//ʱ��
						//System.out.println(so.getSecond_fh_person());
						//System.out.println(so.getSecond_fh_address());
						//System.out.println(so.getReceive_sh_address());
						//System.out.println(so.getFhtime());
						try 
						{
							distributionBiz.saveSecond_order(so);
						} catch (Exception e) {
							
						}	
					}else if(codeThree.startsWith("x"))//�ַ���[�˺ţ�����]�ж��õģ� �ַ����������ַ��˵�ַ     , �����ĵ�ַ��ɨ��������ӻ��Ǵ��Ӷ�ά�루�ѵ㣩��֤������ʱ��   
							{
						        so = new Second_order();
								so.setSecond_fh_person(name);//�ַ�������
								so.setSecond_fh_address(address);//�ַ��˵�ַ
								so.setReceive_sh_address(pqadress);//�����ĵ�ַ:Ƭ��д�����
								so.setReceive_sh_addressInStation(zhandianress);//ʵ�ʷ����ǵ�վ��
								so.setBox_code(arr[i]);//���Ӷ�ά��
								so.setBox_count(10);
								//System.out.println("�������뱣�����Ӷ�ά�뷽��");
								so.setFhtime(new Timestamp(System.currentTimeMillis()));//ʱ��
								//System.out.println(so.getFhtime());
								try 
								{
									distributionBiz.saveSecond_order(so);
								} catch (Exception e) 
								{
									//System.out.println(e);
								}
							}
					    
						else{
								super.getPringWriter().print(-2067);//�������ӻ��ߴ��Ӷ�ά�룬������ɨ���������ӻ��ߴ��Ӷ�ά��
					     	}	
				}
				super.getPringWriter().print(-2066);//�ύ�ɹ�
			}
		}
		if(com_id == null)//Ƭ���ύ����Ϣ,Ƭ��ָ�򡪡���->վ��
		{
			int zd = 99999;
			int pq = 88888;
			boolean zdi = false;
			try 
			{
				pq = Integer.parseInt(pianquId);
				zd = Integer.parseInt(zhandianId);//վ��idת��
			} catch (NumberFormatException e)
			{
				zdi = true;//zdiһ��Ϊfalse
			}
			//��һ������վ���idȥ�ҵ�վ���������
			//�ڶ���������֮��ɨ����Ӷ�ά��
			//��һ��ʱ�ύ���裬��֤���������Ѿ������ˣ�����������Ƭ������վ�㡤��������[����ֻ��ֱ��ȥ���������ж��0-0�������ֱ�����û�����ֱ��û����ظ���ô����0-0���ظ�ȷ���ύ֮�󽫲���д��Ĺ���hql�������]���������ܿ�ʼȥ�����Ӷ�ά�루������Ƭ���洢�����Ƿ��ж����ֹ�˾���Ĵ��ӣ���ͨ���������еĴ��Ӷ�ά��������ӣ�ֻҪɨ������ȥ���������Ĵ��Ӷ�ά����ҵ����Ӷ�ά�룩
			//���Ĳ����ݲ�ѯ��������ϢΪtrue,�ı�Ƭ���洢����д��ӻ������Ӷ�ά��״̬���������Ǵ�����ʱ���ùܣ������ر�ע������ӷ����ӣ����״ֻ̬�ܸı�Ϊ1�����л�û���꣬����Ϊ2��
			//���岽�Ѵ��Ӷ�ά��д��position_inventory��
			List<Position> po = distributionBiz.findAddrressByZhandianId(zd);
			String posName = null;
			for(Position pp: po)
			{
				posName = pp.getPosName();
			}
			String b = code;
			//System.out.println(b+":��ӡapp�˴�������ֵ��Ƭ���ַ��������ӻ��ߴ��Ӷ�ά��");
			String[] arr = b.split("_");
			position_inventory pi = null;
			for(int i=0;i<arr.length;i++)
			{
				String codeThree = arr[i];
				if(codeThree.startsWith("b"))
				{
					pi = new position_inventory();
					pi.setBagCode(arr[i]);
					pi.setPosition_name(posName);
					//System.out.println(pi.getPosition_name()+"name");
					pi.setPianqu_id(pq);
					pi.setBag_code_count(50);
					pi.setTime(new Timestamp(System.currentTimeMillis()));
					try 
					{
						distributionBiz.savePosition_inventory(pi);
					} catch (Exception e) {
						
					}	
				}else if(codeThree.startsWith("x"))
						{
					pi = new position_inventory();
					pi.setBoxCode(arr[i]);
					pi.setPosition_name(posName);
					//System.out.println(pi.getPosition_name()+"name");
					pi.setPianqu_id(pq);
					pi.setBag_count(10);
					pi.setTime(new Timestamp(System.currentTimeMillis()));
							try 
							{
								distributionBiz.savePosition_inventory(pi);
							} catch (Exception e) 
							{
								
							}
						}
				    
					else{
							super.getPringWriter().print(-2067);//�������ӻ��ߴ��Ӷ�ά�룬������ɨ���������ӻ��ߴ��Ӷ�ά��
				     	}	
			}
			super.getPringWriter().print(-2066);
			
		}
	}
	
	//׷��,���ݷ�ǩ�����������׷�ݷ�ǩʹ������������쳣�ķ�ǩ�������ķ�ǩ�����Ƿ�ǩ������Ա��𻵲��ܲ�ѯ��
		public void traceAction()
		{
			
			/**
			1.ûʹ�õķ�ǩ��չʾ�÷�ǩδ��ʹ��
			2.ʹ���У�׷��app��û�취ɨ��ʹ���е�����ģ���Ϊ��ǩ�ڳ��ϣ����Ǹ��ų���ɨ
			3.ʹ�ú󣺸����쳣����ģ�δע�ᣬûʩ�⣩
			  (1)û��ע������ֱ��˵������
			  (2)ûʩ��������
			     1)��һ���ҵ��÷�ǩ��Ӧ�Ĵ��Ӷ�ά������Ӷ�ά��
			     2)վ���п϶��д��ӣ����� ���ӣ���ȥ�Ҹ�վ���Ƭ����position_inventory����������վ���Ƭ������
			     3)��������ExcRecordAction�е�list�����ң������ˣ�ʩ�⣬��⡾�������Ϣ����Դ������ʻԱ����ʩ��ص㣬���ص�
			*/
			List<Position> positionlist = null;
			List<Freeze> freezelist = null;
			List<Sealed> sealist = null;
			List<Area>  arealist = null;
			List<DimensionalBarCode> dbcList = null;
			List<Dbc_BagCodeBind> bcdBoxList = null;
			List<position_inventory> pilist = null;
			List<Second_order> so = null;
			List<shoufa_person> sp = null;
			int status = 99;
			int dimensionalBarCode_id = 99;//DimensionalBarCode���id
			String address = null;
			String findbagCode = null;
			String findboxCode = null;
			String returnCode = null;
			
			
			String companyAddress = null;//�����ֹ�˾��ַ
			Timestamp companySendTime = null;//������˾�ַ�ʱ��
			String areaAddress = null;//Ƭ����ַ
			Timestamp areaTime = null;//Ƭ���ַ�ʱ��
			String freezePerson = null;//ʩ����
			Timestamp freeze_time = null;//ʩ��ʱ��
			String freezeAddress = null;//ʩ��ص�
			String unfreezePerson = null;//�����
			Timestamp unfreeze_time = null;//���ʱ��
			String unfeezeAddress = null;//���ص�
//			String shifengRen = null;//ʩ����
//			String jiefenRen = null;//�����
			/**
			 * ʩ���ˣ�ע�����ʵ������˽�׷�ݵı�����ʲô����һ�����������⣬��Թ��������Ҫ�������飬��������ʩ��������������¶���չʾ��
			 * 
			 */
			
			if(code.startsWith("0"))
			{
				boolean sta = false;//iiii
				try {
					sta = distributionBiz.checkCodeNeiMa(code);//staΪfalse��ʾû���ҵ�
				} catch (Exception e) {
				}
				if(!sta)//false���ϡ�������ʾtrue����-107
				{
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("returnCode", returnCode);
					map.put("findbagCode", findbagCode);
					map.put("findboxCode", findboxCode);
					map.put("address", address);
					String statusChange = status+"";
					map.put("status",statusChange);
					map.put("success", "-107");//δע��
					map.put("companyAddress", companyAddress);
					map.put("companySendTime", companySendTime);
					map.put("areaAddress", areaAddress);
					map.put("areaTime", areaTime);
					map.put("freezePerson", freezePerson);
					map.put("freeze_time", freeze_time);
					map.put("unfreezePerson", unfreezePerson);
					map.put("unfreeze_time", unfreeze_time);
					map.put("freezeAddress", freezeAddress);
					map.put("unfeezeAddress",unfeezeAddress );
					list.add(map);
					super.outPrintJsonByArray(list);
					//super.getPringWriter().print(-107);//δע��
					return;
				}else
				{
					dbcList = distributionBiz.findBagCodeByCode(code);//�����
					for(DimensionalBarCode ac : dbcList)
					{
						returnCode = ac.getUnfreeze_content();
						//System.out.println(returnCode+":��ӡ����");
						findbagCode = ac.getBag_code();
						status = ac.getFreeze_status();//0��ʾûʹ�ã�1��ʾʹ���У�2ʹ�����
						//System.out.println(findbagCode+":��ӡ���ݷ�ǩ����׷�ݴ��Ӷ�ά��");
						dimensionalBarCode_id = ac.getId();
					}
					if(findbagCode == null)
					{
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
						String statusChange = status+"";
						map.put("status",statusChange);
						map.put("success", "-206");//�ö�ά��û�б����ӹ���������δ֪�쳣��
						map.put("companyAddress", companyAddress);
						map.put("companySendTime", companySendTime);
						map.put("areaAddress", areaAddress);
						map.put("areaTime", areaTime);
						map.put("freezePerson", freezePerson);
						map.put("freeze_time", freeze_time);
						map.put("unfreezePerson", unfreezePerson);
						map.put("unfreeze_time", unfreeze_time);
						map.put("freezeAddress", freezeAddress);
						map.put("unfeezeAddress",unfeezeAddress );
						list.add(map);
						super.outPrintJsonByArray(list);
						return;
					}else
					{
						try {
							//System.out.println(findbagCode+":�������ӹ�����ѯ:��ӡ���ݷ�ǩ����׷�ݴ��Ӷ�ά��");
							bcdBoxList = distributionBiz.findBoxCodeByFindbagCode(findbagCode);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						
						for(Dbc_BagCodeBind db: bcdBoxList)
						{
							findboxCode = db.getBox_code();
							//System.out.println(findboxCode+"���ݴ��Ӷ�ά���ҵ���Ӧ���Ӷ�ά��");
						}
						if(findboxCode == null)
						{
							List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
							Map<String,Object> map = new HashMap<String, Object>();
							 map.put("returnCode", returnCode);
							map.put("findbagCode", findbagCode);
							map.put("findboxCode", findboxCode);
							map.put("address", address);
							String statusChange = status+"";
							map.put("status",statusChange);
							map.put("success", "-209");//�ö�ά��û�б����ӹ���������δ֪�쳣��
							map.put("companyAddress", companyAddress);
							map.put("companySendTime", companySendTime);
							map.put("areaAddress", areaAddress);
							map.put("areaTime", areaTime);
							map.put("freezePerson", freezePerson);
							map.put("freeze_time", freeze_time);
							map.put("unfreezePerson", unfreezePerson);
							map.put("unfreeze_time", unfreeze_time);
							map.put("freezeAddress", freezeAddress);
							map.put("unfeezeAddress",unfeezeAddress );
							list.add(map);
							super.outPrintJsonByArray(list);
							//super.getPringWriter().print(-209);//�ö�ά��û�б����ӹ���������δ֪�쳣��
							return;
						}
						
					}
					//super.getPringWriter().print(-2066);//���Գɹ������������ҵ��÷�ǩ��Ӧ�Ĵ��Ӷ�ά������Ӷ�ά��
					//���ݴ��Ӷ�ά����վ��,��ȥ�����ӣ���Ϊ�п���Ϊ��pilist
					boolean sta1 = true;//mm
					try {
						pilist = distributionBiz.findPositionBybagCode(findbagCode);//���ݴ��Ӷ�ά����վ��pilist
						if(pilist.size() == 0){
							sta1 = false;
						}
						//System.out.println("��׷�ݣ��÷�ǩ�Ƿ���վ����Ϣ��"+sta1);
					} catch (Exception e) {
						//e.printStackTrace();//�ǵ�ȥ��
					}
					if(sta1)//���Ƿ�Ҫ����sta1 && findboxCode!=null��������Ϊfalse��ʾ�÷�ǩ�ڸ�վ�����û�ҵ����Ӷ�ά�룬�����п��ܴ��ϼ�����ֱ�ӵõ������ӵķ�����վ���
					{
						int pianquId = 99;
						for(position_inventory pi:pilist)
						{
							address = pi.getPosition_name();
							areaTime = pi.getTime();//Ƭ���ַ���վ���ʱ��
							pianquId = pi.getPianqu_id();
						}
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
							if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
							{
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									if(so == null)
									{
										so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								//���ݴ��Ӷ�ά���ѯ������˾���Ÿô��ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
								} catch (Exception e) {
									//
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}else if(status == 1)
							{
								//�ҵ�ʩ���˺�ʩ��ʱ��
								//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
								//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
								
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									freezeAddress = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									if(so.size() == 0)
									{
										so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
							}else if(status == 2)
							{
								//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
								int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
								//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									sealedId = se.getSeaId();
									freezeAddress = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
								try 
								{
									freezelist = distributionBiz.findFreezeBysealedId(sealedId);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Freeze fr : freezelist)
								{
									unfreezePerson = fr.getPerson().getPerTrueName();
									unfreeze_time = fr.getFreTime();
									unfeezeAddress = fr.getPosition().getPosName();
								}
								//System.out.println("��ӡ����ˣ�"+unfreezePerson);
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									if(so.size() == 0)
									{
										so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
								
							}//mm
						
						//super.outPrintJsonByArray(list);
					}else//�����п��ܴ��ϼ�����ֱ�ӵõ������ӵķ�����վ���
					{	
						int pianquId = 99;
						boolean sta2 = false;
						try {
							
							pilist = distributionBiz.findPositionByboxCode(findboxCode);
							if(pilist.size() != 0)
							{
								sta2 = true;
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
						if(sta2)
						{
							for(position_inventory pi:pilist)
							{
								address = pi.getPosition_name();
								pianquId = pi.getPianqu_id();
								areaTime = pi.getTime();
							}
							List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("returnCode", returnCode);
							map.put("findbagCode", findbagCode);
							map.put("findboxCode", findboxCode);
							map.put("address", address);
							if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
							{
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);//ʩ��ص�
								map.put("unfeezeAddress",unfeezeAddress );//���ص�
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}else if(status == 1)
							{
								//�ҵ�ʩ���˺�ʩ��ʱ��
								//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
								//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
								
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									freezeAddress = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);//�����ӣ�������˾����
								map.put("companySendTime", companySendTime);//�����ӣ�������˾����ʱ��
								map.put("areaAddress", areaAddress);//�����ӣ�Ƭ������
								map.put("areaTime", areaTime);//�����ӣ�Ƭ������ʱ��
								map.put("freezePerson", freezePerson);//�����ӣ�ʩ����
								map.put("freeze_time", freeze_time);//�����ӣ�ʩ��ʱ��
								map.put("unfreezePerson", unfreezePerson);//�����ӣ������
								map.put("unfreeze_time", unfreeze_time);//�����ӣ����ʱ��
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
							}else if(status == 2)
							{
								//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
								int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
								//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									sealedId = se.getSeaId();
									freezeAddress  = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
								try 
								{
									freezelist = distributionBiz.findFreezeBysealedId(sealedId);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Freeze fr : freezelist)
								{
									unfreezePerson = fr.getPerson().getPerTrueName();
									unfreeze_time = fr.getFreTime();
									unfeezeAddress = fr.getPosition().getPosName();
								}
								//System.out.println("��ӡ����ˣ�"+unfreezePerson);
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;	//kk1
							}else{
								
							}
						
//							String statusChange = status+"";
//							map.put("status",statusChange);
//							
//							map.put("success", "-2066");
//							list.add(map);
//							super.outPrintJsonByArray(list);
						}else{//����������ǣ��Ϳ�,վ�㣨��ѯ�Ϳ�Ĵ��Ӷ�ά������Ӷ�ά�룩���߶����ֹ�˾ֱ��Խ��Ƭ��������վ��secondorder��
							   boolean stayt = false;
							   try {
								   so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
								   //���Դ�ӡ
								   for(Second_order rt: so)
								   {
									   System.out.println("��ӡ����:"+rt.getBag_code());
									   System.out.println("��ӡ����:"+rt.getBox_code());
									   System.out.println("��ӡ�ַ����ĵط�ȥ��:"+rt.getReceive_sh_addressInStation());
									   System.out.println("��ӡ�ַ����ĵط�ȥ��:"+rt.getReceive_sh_address());
									   System.out.println("�ַ�ʱ�䣺"+rt.getFhtime());
								   }
								   if(so.size() != 0)
								   {
									   stayt = true;
								   }
							   		} catch (Exception e) {
							   				// TODO: handle exception
							   			//e.printStackTrace();
							   		}
							    if(stayt)
							    {
							    	for(Second_order sor: so)
							    	{
							    		address = sor.getReceive_sh_addressInStation();
							    		companySendTime = sor.getFhtime();
							    		areaAddress = sor.getReceive_sh_address();
							    	}
							    	String addresss = null;
							    	if(address == null)
							    	{
							    		addresss = areaAddress;
							    	}else
							    	{
							    		addresss = address;
							    	}
							    	//���ݵ�ַaddress�ҵ�Ƭ��id ����ѯshoufa_person
							    	try 
							    	{
							    		positionlist = distributionBiz.findPianQuIdByReceive_sh_address(addresss);
							    		sp = distributionBiz.findPianQuIdByshoufa_personAddress(addresss);
									} catch (Exception e)
									{
										//e.printStackTrace();
									}
							    	for(Position sper : positionlist)
							    	{
							    		pianquId = sper.getAreaid();
							    		
							    	}
							    	for(shoufa_person sper : sp)
							    	{
							    		pianquId = sper.getAreaid();
							    	}
							    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
									Map<String,Object> map = new HashMap<String, Object>();
									map.put("returnCode", returnCode);
									map.put("findbagCode", findbagCode);
									map.put("findboxCode", findboxCode);
									map.put("address", address);
									if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
									{
										int comId = 99;//
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;
									}else if(status == 1)
									{
										//�ҵ�ʩ���˺�ʩ��ʱ��
										//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
										//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
										
										try {
											sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Sealed se : sealist)
										{
											freezePerson = se.getPerson().getPerTrueName();
											freeze_time = se.getSeaTime();
											freezeAddress = se.getPosition().getPosName();
											//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
											
										}
										int comId = 99;
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;
										
									}else if(status == 2)
									{
										//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
										int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
										//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
										try {
											sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Sealed se : sealist)
										{
											freezePerson = se.getPerson().getPerTrueName();
											freeze_time = se.getSeaTime();
											sealedId = se.getSeaId();
											freezeAddress = se.getPosition().getPosName();
											//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
											
										}
										//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
										try 
										{
											freezelist = distributionBiz.findFreezeBysealedId(sealedId);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Freeze fr : freezelist)
										{
											unfreezePerson = fr.getPerson().getPerTrueName();
											unfreeze_time = fr.getFreTime();
											unfeezeAddress = fr.getPosition().getPosName();
										}
										//System.out.println("��ӡ����ˣ�"+unfreezePerson);
										int comId = 99;
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;	
									}else{
										
									}//hh1
//									String statusChange = status+"";
//									map.put("status",statusChange);
//									map.put("success", "-2066");
//									list.add(map);
//									super.outPrintJsonByArray(list);
							    	
							    }else{
							    	 	try {
							    	 			so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);//iiii
							    	 			for(Second_order sor: so)
							    	 			{
							    	 				System.out.println("��ӡ���Ӷ�ά��:"+sor.getBox_code());
							    	 				System.out.println("��ӡ���ŵص�:"+sor.getSecond_fh_address());
							    	 				System.out.println("��ӡ�շ�ǩ��ַ��"+sor.getReceive_sh_address());
							    	 				address = sor.getReceive_sh_addressInStation();
							    	 				
							    	 			}
									   		} catch (Exception e) 
									   		{
									   			//e.printStackTrace();
									   		}
								    	 try 
								    	 	{
								    		 	//so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												// TODO: handle exception
											}
							    	 	    if(so!= null)
							    	 	    {
							    	 	    	so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
							    	 	    	for(Second_order sor: so)
										    	{
										    		address = sor.getReceive_sh_addressInStation();
										    		
										    	}
							    	 	    	try 
							    	 	    	{
							    	 	    		positionlist = distributionBiz.findPianQuIdByReceive_sh_address(address);
												} catch (Exception e) {
													// TODO: handle exception
												}
							    	 	    	try {
							    	 	    		sp = distributionBiz.findPianQuIdByshoufa_personAddress(address);
												} catch (Exception e) {
													// TODO: handle exception
												}
							    	 	    	for(Position po : positionlist)
							    	 	    	{
							    	 	    		pianquId = po.getAreaid();
							    	 	    	}
							    	 	    	for(shoufa_person spo : sp)
							    	 	    	{
							    	 	    		pianquId = spo.getAreaid();
							    	 	    	}
										    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
												Map<String,Object> map = new HashMap<String, Object>();
												map.put("returnCode", returnCode);
												map.put("findbagCode", findbagCode);
												map.put("findboxCode", findboxCode);
												map.put("address", address);
												
												if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
												{
													int comId = 99;
													//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
													}
													//����Ƭ��id�ҵ������ֹ�˾��id
													try
													{
														arealist= distributionBiz.findComIdByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													
													for(Area ae : arealist)
													{
														comId = ae.getCom_id();
													}
													//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
													}
													//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
													}
													
													String statusChange = status+"";
													map.put("status",statusChange);
													map.put("companyAddress", companyAddress);
													map.put("companySendTime", companySendTime);
													map.put("areaAddress", areaAddress);
													map.put("areaTime", areaTime);
													map.put("freezePerson", freezePerson);
													map.put("freeze_time", freeze_time);
													map.put("unfreezePerson", unfreezePerson);
													map.put("unfreeze_time", unfreeze_time);
													map.put("freezeAddress", freezeAddress);
													map.put("unfeezeAddress",unfeezeAddress );
													map.put("success", "-2066");
													list.add(map);
													super.outPrintJsonByArray(list);
													return;
												}else if(status == 1)
												{
													//�ҵ�ʩ���˺�ʩ��ʱ��
													//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
													//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
													
													try {
														sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
													} catch (Exception e) {
														//e.printStackTrace();
													}
													for(Sealed se : sealist)
													{
														freezePerson = se.getPerson().getPerTrueName();
														freeze_time = se.getSeaTime();
														freezeAddress = se.getPosition().getPosName();
														//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
														
													}
													int comId = 99;
													//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
													}
													//����Ƭ��id�ҵ������ֹ�˾��id
													try
													{
														arealist= distributionBiz.findComIdByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													
													for(Area ae : arealist)
													{
														comId = ae.getCom_id();
													}
													//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
													}
													//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
													}
													String statusChange = status+"";
													map.put("status",statusChange);
													map.put("companyAddress", companyAddress);//�����ӣ�������˾����
													map.put("companySendTime", companySendTime);//�����ӣ�������˾����ʱ��
													map.put("areaAddress", areaAddress);//�����ӣ�Ƭ������
													map.put("areaTime", areaTime);//�����ӣ�Ƭ������ʱ��
													map.put("freezePerson", freezePerson);//�����ӣ�ʩ����
													map.put("freeze_time", freeze_time);//�����ӣ�ʩ��ʱ��
													map.put("unfreezePerson", unfreezePerson);//�����ӣ������
													map.put("unfreeze_time", unfreeze_time);//�����ӣ����ʱ��
													map.put("freezeAddress", freezeAddress);
													map.put("unfeezeAddress",unfeezeAddress );
													map.put("success", "-2066");
													list.add(map);
													super.outPrintJsonByArray(list);
													return;
													
												}else if(status == 2)
												{
													//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
													int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
													//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
													try {
														sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
													} catch (Exception e) {
														//e.printStackTrace();
													}
													for(Sealed se : sealist)
													{
														freezePerson = se.getPerson().getPerTrueName();
														freeze_time = se.getSeaTime();
														sealedId = se.getSeaId();
														freezeAddress = se.getPosition().getPosName();
														//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
														
													}
													//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
													try 
													{
														freezelist = distributionBiz.findFreezeBysealedId(sealedId);
													} catch (Exception e) {
														//e.printStackTrace();
													}
													for(Freeze fr : freezelist)
													{
														unfreezePerson = fr.getPerson().getPerTrueName();
														unfreeze_time = fr.getFreTime();
														unfeezeAddress = fr.getPosition().getPosName();													}
													//System.out.println("��ӡ����ˣ�"+unfreezePerson);
													int comId = 99;
													//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
													}
													//����Ƭ��id�ҵ������ֹ�˾��id
													try
													{
														arealist= distributionBiz.findComIdByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													
													for(Area ae : arealist)
													{
														comId = ae.getCom_id();
													}
													//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
													}
													//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
													}
													String statusChange = status+"";
													map.put("status",statusChange);
													map.put("companyAddress", companyAddress);
													map.put("companySendTime", companySendTime);
													map.put("areaAddress", areaAddress);
													map.put("areaTime", areaTime);
													map.put("freezePerson", freezePerson);
													map.put("freeze_time", freeze_time);
													map.put("unfreezePerson", unfreezePerson);
													map.put("unfreeze_time", unfreeze_time);
													map.put("freezeAddress", freezeAddress);
													map.put("unfeezeAddress",unfeezeAddress );
													map.put("success", "-2066");
													list.add(map);
													super.outPrintJsonByArray(list);
													return;//	iiiii
												}else{
													
												}//rr1
//												String statusChange = status+"";
//												map.put("status",statusChange);
//												map.put("success", "-2066");
//												list.add(map);
//												super.outPrintJsonByArray(list);
							    	 	    }
							         }
								//super.getPringWriter().print(-206);
						     }
					}
				}	
			}else if(code.startsWith("1"))
			{
				boolean sta = false;//iiii
				try {
					sta = distributionBiz.checkCodeWaiMa(code);//������룬staΪfalse��ʾû���ҵ�
				} catch (Exception e) {
				}
				if(!sta)//false���ϡ�������ʾtrue����-107
				{
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("returnCode", returnCode);
					map.put("findbagCode", findbagCode);
					map.put("findboxCode", findboxCode);
					map.put("address", address);
					String statusChange = status+"";
					map.put("status",statusChange);
					map.put("success", "-107");//δע��
					map.put("companyAddress", companyAddress);
					map.put("companySendTime", companySendTime);
					map.put("areaAddress", areaAddress);
					map.put("areaTime", areaTime);
					map.put("freezePerson", freezePerson);
					map.put("freeze_time", freeze_time);
					map.put("unfreezePerson", unfreezePerson);
					map.put("unfreeze_time", unfreeze_time);
					map.put("freezeAddress", freezeAddress);
					map.put("unfeezeAddress",unfeezeAddress );
					list.add(map);
					super.outPrintJsonByArray(list);
					//super.getPringWriter().print(-107);//δע��
					return;
				}else
				{
					dbcList = distributionBiz.findBagCodeByWaimaCode(code);//������������
					for(DimensionalBarCode ac : dbcList)
					{
						returnCode = ac.getFreeze_content();//�õ�����
						//System.out.println(returnCode+":��ӡ����");
						findbagCode = ac.getBag_code();
						status = ac.getFreeze_status();//0��ʾûʹ�ã�1��ʾʹ���У�2ʹ�����
						//System.out.println(findbagCode+":��ӡ���ݷ�ǩ����׷�ݴ��Ӷ�ά��");
						dimensionalBarCode_id = ac.getId();
					}
					if(findbagCode == null)
					{
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
						String statusChange = status+"";
						map.put("status",statusChange);
						map.put("success", "-206");//�ö�ά��û�б����ӹ���������δ֪�쳣��
						map.put("companyAddress", companyAddress);
						map.put("companySendTime", companySendTime);
						map.put("areaAddress", areaAddress);
						map.put("areaTime", areaTime);
						map.put("freezePerson", freezePerson);
						map.put("freeze_time", freeze_time);
						map.put("unfreezePerson", unfreezePerson);
						map.put("unfreeze_time", unfreeze_time);
						map.put("freezeAddress", freezeAddress);
						map.put("unfeezeAddress",unfeezeAddress );
						list.add(map);
						super.outPrintJsonByArray(list);
						return;
					}else
					{
						try {
							//System.out.println(findbagCode+":�������ӹ�����ѯ:��ӡ���ݷ�ǩ����׷�ݴ��Ӷ�ά��");
							bcdBoxList = distributionBiz.findBoxCodeByFindbagCode(findbagCode);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						
						for(Dbc_BagCodeBind db: bcdBoxList)
						{
							findboxCode = db.getBox_code();
							//System.out.println(findboxCode+"���ݴ��Ӷ�ά���ҵ���Ӧ���Ӷ�ά��");
						}
						if(findboxCode == null)
						{
							List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
							Map<String,Object> map = new HashMap<String, Object>();
							 map.put("returnCode", returnCode);
							map.put("findbagCode", findbagCode);
							map.put("findboxCode", findboxCode);
							map.put("address", address);
							String statusChange = status+"";
							map.put("status",statusChange);
							map.put("success", "-209");//�ö�ά��û�б����ӹ���������δ֪�쳣��
							map.put("companyAddress", companyAddress);
							map.put("companySendTime", companySendTime);
							map.put("areaAddress", areaAddress);
							map.put("areaTime", areaTime);
							map.put("freezePerson", freezePerson);
							map.put("freeze_time", freeze_time);
							map.put("unfreezePerson", unfreezePerson);
							map.put("unfreeze_time", unfreeze_time);
							map.put("freezeAddress", freezeAddress);
							map.put("unfeezeAddress",unfeezeAddress );
							list.add(map);
							super.outPrintJsonByArray(list);
							//super.getPringWriter().print(-209);//�ö�ά��û�б����ӹ���������δ֪�쳣��
							return;
						}
						
					}
					//super.getPringWriter().print(-2066);//���Գɹ������������ҵ��÷�ǩ��Ӧ�Ĵ��Ӷ�ά������Ӷ�ά��
					//���ݴ��Ӷ�ά����վ��,��ȥ�����ӣ���Ϊ�п���Ϊ��pilist
					boolean sta1 = true;//mm
					try {
						pilist = distributionBiz.findPositionBybagCode(findbagCode);//���ݴ��Ӷ�ά����վ��pilist
						if(pilist.size() == 0){
							sta1 = false;
						}
						//System.out.println("��׷�ݣ��÷�ǩ�Ƿ���վ����Ϣ��"+sta1);
					} catch (Exception e) {
						//e.printStackTrace();//�ǵ�ȥ��
					}
					if(sta1)//���Ƿ�Ҫ����sta1 && findboxCode!=null��������Ϊfalse��ʾ�÷�ǩ�ڸ�վ�����û�ҵ����Ӷ�ά�룬�����п��ܴ��ϼ�����ֱ�ӵõ������ӵķ�����վ���
					{
						int pianquId = 99;
						for(position_inventory pi:pilist)
						{
							address = pi.getPosition_name();
							areaTime = pi.getTime();//Ƭ���ַ���վ���ʱ��
							pianquId = pi.getPianqu_id();
						}
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
							if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
							{
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								//���ݴ��Ӷ�ά���ѯ������˾���Ÿô��ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
								} catch (Exception e) {
									//
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}else if(status == 1)
							{
								//�ҵ�ʩ���˺�ʩ��ʱ��
								//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
								//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
								
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									freezeAddress = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									if(so.size() == 0)
									{
										so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;//iii
								
							
							}else if(status == 2)
							{
								//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
								int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
								//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									sealedId = se.getSeaId();
									freezeAddress = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
								try 
								{
									freezelist = distributionBiz.findFreezeBysealedId(sealedId);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Freeze fr : freezelist)
								{
									unfreezePerson = fr.getPerson().getPerTrueName();
									unfreeze_time = fr.getFreTime();
									unfeezeAddress = fr.getPosition().getPosName();
								}
								//System.out.println("��ӡ����ˣ�"+unfreezePerson);
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									if(so.size() == 0)
									{
										so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}//mm1
					}else//�����п��ܴ��ϼ�����ֱ�ӵõ������ӵķ�����վ���
					{     
						int pianquId = 99;
						boolean sta2 = false;
						try {
							
							pilist = distributionBiz.findPositionByboxCode(findboxCode);
							if(pilist.size() != 0)
							{
								sta2 = true;
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
						if(sta2)
						{
							for(position_inventory pi:pilist)
							{
								address = pi.getPosition_name();
								pianquId = pi.getPianqu_id();
								areaTime = pi.getTime();
							}
							List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
							Map<String,Object> map = new HashMap<String, Object>();
							map.put("returnCode", returnCode);
							map.put("findbagCode", findbagCode);
							map.put("findboxCode", findboxCode);
							map.put("address", address);
							if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
							{
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}else if(status == 1)
							{
								//�ҵ�ʩ���˺�ʩ��ʱ��
								//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
								//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
								
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									freezeAddress  = se.getPosition().getPosName();
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);//�����ӣ�������˾����
								map.put("companySendTime", companySendTime);//�����ӣ�������˾����ʱ��
								map.put("areaAddress", areaAddress);//�����ӣ�Ƭ������
								map.put("areaTime", areaTime);//�����ӣ�Ƭ������ʱ��
								map.put("freezePerson", freezePerson);//�����ӣ�ʩ����
								map.put("freeze_time", freeze_time);//�����ӣ�ʩ��ʱ��
								map.put("unfreezePerson", unfreezePerson);//�����ӣ������
								map.put("unfreeze_time", unfreeze_time);//�����ӣ����ʱ��
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
							}else if(status == 2)
							{
								//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
								int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
								//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
								try {
									sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Sealed se : sealist)
								{
									freezePerson = se.getPerson().getPerTrueName();
									freeze_time = se.getSeaTime();
									sealedId = se.getSeaId();
									freezeAddress = se.getPosition().getPosName();
									
									//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
									
								}
								//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
								try 
								{
									freezelist = distributionBiz.findFreezeBysealedId(sealedId);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								for(Freeze fr : freezelist)
								{
									unfreezePerson = fr.getPerson().getPerTrueName();
									unfreeze_time = fr.getFreTime();
									unfeezeAddress = fr.getPosition().getPosName();
								}
								//System.out.println("��ӡ����ˣ�"+unfreezePerson);
								int comId = 99;
								//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
								}
								//����Ƭ��id�ҵ������ֹ�˾��id
								try
								{
									arealist= distributionBiz.findComIdByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								for(Area ae : arealist)
								{
									comId = ae.getCom_id();
								}
								//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
								}
								//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);
								map.put("companySendTime", companySendTime);
								map.put("areaAddress", areaAddress);
								map.put("areaTime", areaTime);
								map.put("freezePerson", freezePerson);
								map.put("freeze_time", freeze_time);
								map.put("unfreezePerson", unfreezePerson);
								map.put("unfreeze_time", unfreeze_time);
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;	//kk1
							}else{
								
							}
						}else{//����������ǣ��Ϳ�,վ�㣨��ѯ�Ϳ�Ĵ��Ӷ�ά������Ӷ�ά�룩���߶����ֹ�˾ֱ��Խ��Ƭ��������վ��secondorder��
							 boolean stayt = false;
							   try {
								   so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
								   if(so.size() != 0)
								   {
									   stayt = true;
								   }
							   		} catch (Exception e) {
							   				// TODO: handle exception
							   			//e.printStackTrace();
							   		}
							    if(stayt)
							    {
							    	for(Second_order sor: so)
							    	{
							    		address = sor.getReceive_sh_addressInStation();
							    		companySendTime = sor.getFhtime();
							    	}
							    	//���ݵ�ַaddress�ҵ�Ƭ��id ����ѯshoufa_person
							    	try 
							    	{
							    		positionlist = distributionBiz.findPianQuIdByReceive_sh_address(address);
									} catch (Exception e)
									{
										//e.printStackTrace();
									}
							    	for(Position sper : positionlist)
							    	{
							    		pianquId = sper.getAreaid();
							    		
							    	}
							    	
							    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
									Map<String,Object> map = new HashMap<String, Object>();
									map.put("returnCode", returnCode);
									map.put("findbagCode", findbagCode);
									map.put("findboxCode", findboxCode);
									map.put("address", address);
									if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
									{
										int comId = 99;
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;
									}else if(status == 1)
									{
										//�ҵ�ʩ���˺�ʩ��ʱ��
										//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
										//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
										
										try {
											sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Sealed se : sealist)
										{
											freezePerson = se.getPerson().getPerTrueName();
											freeze_time = se.getSeaTime();
											freezeAddress = se.getPosition().getPosName();
											//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
											
										}
										int comId = 99;
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;
										
									}else if(status == 2)
									{
										//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
										int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
										//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
										try {
											sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Sealed se : sealist)
										{
											freezePerson = se.getPerson().getPerTrueName();
											freeze_time = se.getSeaTime();
											sealedId = se.getSeaId();
											freezeAddress = se.getPosition().getPosName();
											//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
											
										}
										//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
										try 
										{
											freezelist = distributionBiz.findFreezeBysealedId(sealedId);
										} catch (Exception e) {
											//e.printStackTrace();
										}
										for(Freeze fr : freezelist)
										{
											unfreezePerson = fr.getPerson().getPerTrueName();
											unfreeze_time = fr.getFreTime();
											unfeezeAddress = fr.getPosition().getPosName();
										}
										//System.out.println("��ӡ����ˣ�"+unfreezePerson);
										int comId = 99;
										//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
										}
										//����Ƭ��id�ҵ������ֹ�˾��id
										try
										{
											arealist= distributionBiz.findComIdByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										for(Area ae : arealist)
										{
											comId = ae.getCom_id();
										}
										//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
										}
										//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
										}
										String statusChange = status+"";
										map.put("status",statusChange);
										map.put("companyAddress", companyAddress);
										map.put("companySendTime", companySendTime);
										map.put("areaAddress", areaAddress);
										map.put("areaTime", areaTime);
										map.put("freezePerson", freezePerson);
										map.put("freeze_time", freeze_time);
										map.put("unfreezePerson", unfreezePerson);
										map.put("unfreeze_time", unfreeze_time);
										map.put("freezeAddress", freezeAddress);
										map.put("unfeezeAddress",unfeezeAddress );
										map.put("success", "-2066");
										list.add(map);
										super.outPrintJsonByArray(list);
										return;	
									}else{
										
									}//gg1
							    	
							    }else{
							    	try {
							    		so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);//iiii
					    	 			for(Second_order sor: so)
					    	 			{
					    	 				System.out.println("��ӡ���Ӷ�ά��:"+sor.getBox_code());
					    	 				System.out.println("��ӡ���ŵص�:"+sor.getSecond_fh_address());
					    	 				System.out.println("��ӡ�շ�ǩ��ַ��"+sor.getReceive_sh_address());
					    	 				address = sor.getReceive_sh_addressInStation();
					    	 				
					    	 			}
							   		} catch (Exception e) 
							   		{
							   			//e.printStackTrace();
							   		}
						    	 try 
						    	 	{
						    		 	//so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
									} catch (Exception e) {
										// TODO: handle exception
									}
					    	 	    if(so!= null)
					    	 	    {
					    	 	    	so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
					    	 	    	for(Second_order sor: so)
								    	{
								    		address = sor.getReceive_sh_addressInStation();
								    		
								    	}
					    	 	    	try 
					    	 	    	{
					    	 	    		positionlist = distributionBiz.findPianQuIdByReceive_sh_address(address);
										} catch (Exception e) {
											// TODO: handle exception
										}
					    	 	    	try {
					    	 	    		sp = distributionBiz.findPianQuIdByshoufa_personAddress(address);
										} catch (Exception e) {
											// TODO: handle exception
										}
					    	 	    	for(Position po : positionlist)
					    	 	    	{
					    	 	    		pianquId = po.getAreaid();
					    	 	    	}
					    	 	    	for(shoufa_person spo : sp)
					    	 	    	{
					    	 	    		pianquId = spo.getAreaid();
					    	 	    	}
								    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
										Map<String,Object> map = new HashMap<String, Object>();
										map.put("returnCode", returnCode);
										map.put("findbagCode", findbagCode);
										map.put("findboxCode", findboxCode);
										map.put("address", address);
										
										if(status == 0)//Ϊ0��״̬��2�ࣺû��ʹ�õ�����ͽ��δʩ������������ע��status��app���жϵ�ʱ��Ϊ0��ʱ�����δʩ�⣨����δʹ�ã�
										{
											int comId = 99;
											//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
											}
											//����Ƭ��id�ҵ������ֹ�˾��id
											try
											{
												arealist= distributionBiz.findComIdByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											for(Area ae : arealist)
											{
												comId = ae.getCom_id();
											}
											//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
											}
											//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
											}
											
											String statusChange = status+"";
											map.put("status",statusChange);
											map.put("companyAddress", companyAddress);
											map.put("companySendTime", companySendTime);
											map.put("areaAddress", areaAddress);
											map.put("areaTime", areaTime);
											map.put("freezePerson", freezePerson);
											map.put("freeze_time", freeze_time);
											map.put("unfreezePerson", unfreezePerson);
											map.put("unfreeze_time", unfreeze_time);
											map.put("freezeAddress", freezeAddress);
											map.put("unfeezeAddress",unfeezeAddress );
											map.put("success", "-2066");
											list.add(map);
											super.outPrintJsonByArray(list);
											return;
										}else if(status == 1)
										{
											//�ҵ�ʩ���˺�ʩ��ʱ��
											//��һ������dimensionalBarCode�����ҵ��÷�ǩ��Ӧ��id,�Ѿ��õ���dimensionalBarCode_id
											//�ڶ�����ȥ���ݷ�ǩ��idȥsealedʩ������ҵ�ʩ���˺�ʩ��ʱ��
											
											try {
												sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
											} catch (Exception e) {
												//e.printStackTrace();
											}
											for(Sealed se : sealist)
											{
												freezePerson = se.getPerson().getPerTrueName();
												freeze_time = se.getSeaTime();
												freezeAddress = se.getPosition().getPosName();
												//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
												
											}
											int comId = 99;
											//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
											}
											//����Ƭ��id�ҵ������ֹ�˾��id
											try
											{
												arealist= distributionBiz.findComIdByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											for(Area ae : arealist)
											{
												comId = ae.getCom_id();
											}
											//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
											}
											//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
											}
											String statusChange = status+"";
											map.put("status",statusChange);
											map.put("companyAddress", companyAddress);//�����ӣ�������˾����
											map.put("companySendTime", companySendTime);//�����ӣ�������˾����ʱ��
											map.put("areaAddress", areaAddress);//�����ӣ�Ƭ������
											map.put("areaTime", areaTime);//�����ӣ�Ƭ������ʱ��
											map.put("freezePerson", freezePerson);//�����ӣ�ʩ����
											map.put("freeze_time", freeze_time);//�����ӣ�ʩ��ʱ��
											map.put("unfreezePerson", unfreezePerson);//�����ӣ������
											map.put("unfreeze_time", unfreeze_time);//�����ӣ����ʱ��
											map.put("freezeAddress", freezeAddress);
											map.put("unfeezeAddress",unfeezeAddress );
											map.put("success", "-2066");
											list.add(map);
											super.outPrintJsonByArray(list);
											return;
											
										}else if(status == 2)
										{
											//��״̬Ϊ2������sealed��idȥfreeze��ȥ�ҵ������Ϣ
											int sealedId = 5;//5��sealed��id�����ڵģ���������Ϊ5
											//System.out.println("����״̬Ϊ2�Ĳ�ѯ׷��");
											try {
												sealist = distributionBiz.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
											} catch (Exception e) {
												//e.printStackTrace();
											}
											for(Sealed se : sealist)
											{
												freezePerson = se.getPerson().getPerTrueName();
												freeze_time = se.getSeaTime();
												sealedId = se.getSeaId();
												freezeAddress = se.getPosition().getPosName();
												//System.out.println("��ӡʩ������Ϣ:"+se.getPerson().getPerTrueName());
												
											}
											//����sealedId�ҵ��������Ϣ������������񿴿�������Ŀ�Ƿ����
											try 
											{
												freezelist = distributionBiz.findFreezeBysealedId(sealedId);
											} catch (Exception e) {
												//e.printStackTrace();
											}
											for(Freeze fr : freezelist) 
											{
												unfreezePerson = fr.getPerson().getPerTrueName();
												unfreeze_time = fr.getFreTime();
												unfeezeAddress = fr.getPosition().getPosName();													}
											//System.out.println("��ӡ����ˣ�"+unfreezePerson);
											try 
											{
												freezelist = distributionBiz.findFreezeBysealedId(sealedId);
											} catch (Exception e) {
												//e.printStackTrace();
											}
											for(Freeze fr : freezelist)
											{
												unfreezePerson = fr.getPerson().getPerTrueName();
												unfreeze_time = fr.getFreTime();
												unfeezeAddress = fr.getPosition().getPosName();
											}
											int comId = 99;
											//�ٸ���position_inventory���е�Ƭ��id�ҵ���Ƭ�������ַ
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//�õ�Ƭ�������ַ
											}
											//����Ƭ��id�ҵ������ֹ�˾��id
											try
											{
												arealist= distributionBiz.findComIdByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											for(Area ae : arealist)
											{
												comId = ae.getCom_id();
											}
											//���ݶ����ֹ�˾id�ҵ������ֹ�˾��ַ
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//�õ������ֹ�˾��ַ
											}
											//�������Ӷ�ά���ѯ������˾���Ÿ����ӵ�ʱ��
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//�õ������ֹ�˾����Ƭ����ʱ��
											}
											String statusChange = status+"";
											map.put("status",statusChange);
											map.put("companyAddress", companyAddress);
											map.put("companySendTime", companySendTime);
											map.put("areaAddress", areaAddress);
											map.put("areaTime", areaTime);
											map.put("freezePerson", freezePerson);
											map.put("freeze_time", freeze_time);
											map.put("unfreezePerson", unfreezePerson);
											map.put("unfreeze_time", unfreeze_time);
											map.put("freezeAddress", freezeAddress);
											map.put("unfeezeAddress",unfeezeAddress );
											map.put("success", "-2066");
											list.add(map);
											super.outPrintJsonByArray(list);
											return;
										}else{
											
										}

					    	 	    }
					         }
						
				     }
					}
				}	
			}else
			{
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("findbagCode", findbagCode);
				map.put("findboxCode", findboxCode);
				map.put("address", address);
				String statusChange = status+"";
				map.put("status",statusChange);
				map.put("success", "-208");//ɨ��Ĳ��Ƿ�ǩ�ϵ�������
				list.add(map);
				super.outPrintJsonByArray(list);
				//super.getPringWriter().print(-107);//δע��
				return;
				//super.getPringWriter().print(-208);//ɨ��Ĳ��Ƿ�ǩ��ά�루����0����1��ͷ��
			}
			
    }
		
//�ַ���¼
public void DistributionRecords()
{
	
	/* 1.�����˺ź�����ȥ��ѯsecondorder��area_manager_inventory��
	 * 2.�����ˣ������ص㣬ʵ���ջ��ˣ������һ�仹��һ���ӣ�����ǩ��Ŀ������
	 * 3.������ʲô��
	 *   ��1���㡰�ַ���¼���ĵ�һ�������ǣ�ʲôʱ��ε�չʾ����
	 *   ��2����ȥ֮����һ�����ڣ�ѡ����Ǿ����ĳһ��ķַ���¼����
	 * */
}
private PageBean pageBean;
private ExcRecordAction  excRecordAction;
private ExcRecordCondition condition; // ��ѯ�б�ʱ������
public PageBean getPageBean() {
	return pageBean;
}
public void setPageBean(PageBean pageBean) {
	this.pageBean = pageBean;
}
public ExcRecordCondition getCondition() {
	return condition;
}
public void setCondition(ExcRecordCondition condition) {
	this.condition = condition;
}
public ExcRecordAction getExcRecordAction() {
	return excRecordAction;
}
public void setExcRecordAction(ExcRecordAction excRecordAction) {
	this.excRecordAction = excRecordAction;
}
private ExcRecordActionAp excRecordActionAp;

public ExcRecordActionAp getExcRecordActionAp() {
	return excRecordActionAp;
}
public void setExcRecordActionAp(ExcRecordActionAp excRecordActionAp) {
	this.excRecordActionAp = excRecordActionAp;
}
//ʱ��ӿ�
public void showTime()
{
	System.out.println(condition.getTime());
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	Map<String,Object> map = new HashMap<String, Object>();
	map.put("yearOne", "2016");
	map.put("month1", "01");
	map.put("month2", "02");
	map.put("month3", "03");
	map.put("month4", "04");
	map.put("month5", "05");
	map.put("month6", "06");
	map.put("month7", "07");
	map.put("month8", "08");
	map.put("month9", "09");
	map.put("month10", "10");
	map.put("month11", "11");
	map.put("month12", "12");
	list.add(map);
	super.outPrintJsonByArray(list);
}
//�쳣�ӿڣ������仯��ѯ��ͬ���
public void anomalyAction(){
//�������	
//System.out.println(condition.getTime()+":��ӡ����");
//	if("7".equals(condition.getTime()))
//	{
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
//		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());
//		String endTime = "";
//		endTime = sdf.format(endTime1);
//		    
//		Date begintime1 = new java.util.Date( (new java.util.Date()).getTime()-86400000*7);
//		String begintime ="";
//		begintime = sdf.format(begintime1);
//			
//		condition.setBeginTime(begintime);//��ʼʱ��
//		condition.setEndTime(endTime);//����ʱ��
//		System.out.println("�ս��뷽����ӡ��ǰ�ڼ�ҳ��"+pageBean.getCurentPage());
//		System.out.println("��ӡ����ʱ��:"+condition.getEndTime()+";��ӡexctype: "+condition.getExcType()+";��ӡʱ�䣺"+condition.getBeginTime());
//		excRecordActionAp.setCondition(condition);
//		excRecordActionAp.setPageBean(pageBean);
//		//excRecordAction.illegality();nn
//		try 
//		{
//			excRecordActionAp.illegality();
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//	}else if("30".equals(condition.getTime()))
//	{
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
//		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());
//		String endTime = "";
//		endTime = sdf.format(endTime1);
//		    
//		Date begintime1 = new java.util.Date( (new java.util.Date()).getTime()-86400000*7*4);
//		String begintime ="";
//		begintime = sdf.format(begintime1);
//			
//		condition.setBeginTime(begintime);//��ʼʱ��
//		condition.setEndTime(endTime);//����ʱ��
//		System.out.println("�ս��뷽����ӡ��ǰ�ڼ�ҳ��"+pageBean.getCurentPage());
//		System.out.println("��ӡ����ʱ��:"+condition.getEndTime()+";��ӡexctype: "+condition.getExcType()+";��ӡʱ�䣺"+condition.getBeginTime());
//		excRecordActionAp.setCondition(condition);
//		excRecordActionAp.setPageBean(pageBean);
//		//excRecordAction.illegality();nn
//		try 
//		{
//			excRecordActionAp.illegality();
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		
//	}
//System.out.println("��ӡtime��"+condition.getTime());
//2016.02     2016.11 �ж�ʱ��
//Ĭ�ϲ�ѯһ��
//System.out.println("��ӡ�쳣״̬��"+condition.getExcType());
//System.out.println("��ӡվ�㣺"+condition.getSite());
	
	boolean sta = false;
	
	if("".equals(condition.getTime()))//condition.time=,�Ƚ�����
	{
		sta = true;
	}
	
	if(sta)
	{
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());
		String endTime = "";
		endTime = sdf.format(endTime1);
		//��ʼʱ���޸�
		String beginTi = endTime.substring(0, 4);
		String beginTim = beginTi+"-01-01 00:00:000";
		//System.out.println(beginTim);
		
		Date begintime1 = new java.util.Date( (new java.util.Date()).getTime()-86400000*7*4);
		String begintime ="";
		begintime = sdf.format(begintime1);
		condition.setBeginTime(beginTim);//��ʼʱ��
		condition.setEndTime(endTime);//����ʱ��
		
		//System.out.println("�ս��뷽����ӡ��ǰ�ڼ�ҳ��"+pageBean.getCurentPage());
		//System.out.println("��ӡ����ʱ��:"+condition.getEndTime()+";��ӡexctype: "+condition.getExcType()+";��ӡʱ�䣺"+condition.getBeginTime());
		excRecordActionAp.setCondition(condition);
		excRecordActionAp.setPageBean(pageBean);
		//excRecordAction.illegality();
		try 
		{
			excRecordActionAp.illegality();
		} catch (Exception e) 
		{
			
		}
		
	}
	else if(sta == false)
	{
		//�ж�ʱ��
		String beTime = null;
		String enTime = null;
		
		String time_interception_year = condition.getTime().substring(0, 4);//2016.12��0-4��ȡ��ݣ�5-7��ȡ�·�
		String time_interception_month = "ty";
		try {
			time_interception_month = condition.getTime().substring(5, 7);
		} catch (Exception e) {
			
		}
		String year_month = time_interception_year +"-"+time_interception_month;
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());//��ȡ��ǰʱ��
		String endTime = "";
		endTime = sdf.format(endTime1);
		String ww = endTime.substring(0,7);
		//ֻ��condition.getTime()=ĳһ�꣨����2016,2017����ʱ��
		if("ty".equals(time_interception_month))//��condition.getTime()ֵ�·�Ϊ�յ��������ʾ��ѯ����
		{
			String beginTim = time_interception_year+"-01-01 00:00:000";
			 String ent     = time_interception_year+"-12-31 23:59:000";
			beTime = beginTim;
			enTime = ent;
		}
		else if(ww.equals(year_month))//�ж��Ƿ��ǵ�ǰ��,ע���м��-���� �� �Ƚ�����equals��
		{
			
			//��ʼʱ���޸�
			String beginTim = year_month+"-01 00:00:000";
			beTime = beginTim;
			enTime = endTime;
		}else//���ǵ�ǰ��,���ݻ�ȡ��������ȥ���Ƿ�Ϊ�������ƽ��
		{
			int yearr = Integer.parseInt(time_interception_year);
			int monthh = Integer.parseInt(time_interception_month);
			if(yearr%4==0 && yearr%100!=0 || yearr%400==0) 
			{
				if(monthh==1 || monthh==3 || monthh==5 || monthh==7 || monthh==8 || monthh==10 || monthh==12)
				{
					//31��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-31 23:59:000";
					
				}else if(monthh==4 || monthh==6 || monthh==9 || monthh==11)
				{
					//30��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-30 23:59:000";
				}else
				{
					//29��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-29 23:59:000";
					
				}
				//System.out.println("������");
				
			}else
			{
				if(monthh==1 || monthh==3 || monthh==5 || monthh==7 || monthh==8 || monthh==10 || monthh==12)
				{
					//31��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-31 23:59:000";
					
				}else if(monthh==4 || monthh==6 || monthh==9 || monthh==11)
				{
					//30��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-30 23:59:000";
				}else
				{
					//28��
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-28 23:59:000";
				}
				//System.out.println("��ƽ��");
			}
		}
		//System.out.println("��ӡ��ʼ��ѯʱ��:"+beTime+"/n"+"��ӡ������ѯʱ�䣺"+enTime);
		condition.setBeginTime(beTime);
		condition.setEndTime(enTime);
		excRecordActionAp.setCondition(condition);
		excRecordActionAp.setPageBean(pageBean);
		try 
		{
			excRecordActionAp.illegality();
		} catch (Exception e) 
		{
				
		}	
	}	
}
//ͳ������(������˾���˺Ż���Ƭ�����˺Ž�����ͳ�Ƹ��˺���ÿһ��Ƭ����������ÿһ��վ�������)
public void totalCount()
{
	//�����˺������ҵ����˺��µ������ӽڵ����ƣ��ַ��ˣ��ַ��������ӽڵ����ƣ��ַ�����
	String str = condition.getPerName();
	try 
	{
		str = new String(str.getBytes("UTF-8"),"utf-8");
	} catch (UnsupportedEncodingException e1) 
	{
		
	}
	//kk
	//��ȡ�����ֶ�Ӧ��areaid��ֵ����com_id�Ƿ���ֵ
	int i = 0;
	Integer areaid = null;
	Integer com_id = null;
	Integer totalCount = 0;
	List<shoufa_person> solist = null;
	if("".equals(str))
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		super.outPrintJsonByArray(list);//ע������˺����ƣ�xxxx�Ƴ����㰲�ģ��Ƴ�
	}else
	{
		solist = distributionBiz.findPianquIdByName(str);
		if(solist == null)
		{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			super.outPrintJsonByArray(list);//ע������˺����ƣ�xxxx�Ƴ����㰲�ģ��Ƴ�
			
		}else
		{
			for(shoufa_person sp : solist)
			{
				areaid = sp.getAreaid();
				com_id = sp.getCom_id();
			}
			List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
			if(areaid==null || listTw==null)//areaidΪ�ձ�ʾΪ�����ֹ�˾��¼��������com_id��ֵ�ҵ��ֹ�˾������Ƭ��
			{   
				List<Area> secondlist = distributionBiz.serachAreaAddressor(com_id);
				Map<String,Object> mapTw = null;
				for(Area a : secondlist)//Ƭ������
				{
					mapTw = new HashMap<String, Object>();
					//mapTw.put("comid", " ");
					mapTw.put("id", a.getId());//Ƭ��id
					mapTw.put("name", a.getArea_name()+"Ƭ��");//����
					mapTw.put("managerName", a.getArea_name()+"����");
					//�ٸ���Ƭ����ַȥsecondorder��ȥ��ÿһ��Ƭ���������������Ǹ���sql��hql����ѯ
					int totalcount = distributionBiz.findCountByArea_name(a.getArea_name()+"Ƭ��");//�ٸ����ӽڵ������ȥ��ÿһ���ӽڵ�������ۼ�
					mapTw.put("totalcount", totalcount);//����
					listTw.add(mapTw);
				}
			}
			if(com_id == null || listTw==null)//Ƭ�����·�������
			{
				List<Position> secondlist = distributionBiz.serachPostionAddressor(areaid);
				for(Position p : secondlist)
				{
					Map<String,Object> mapTw = new HashMap<String, Object>();
					//mapTw.put("pid", areaid);
					//mapTw.put("id", p.getAreaid());
					mapTw.put("id", p.getPosId());//վ��id
					mapTw.put("name", p.getPosName());//����
					mapTw.put("managerName", str);
					int totalcount = distributionBiz.findCountByZhandian_name(p.getPosName());//����վ������ȥ��ÿһ��վ������������������Ƭ������ĳһ������վ��������
					mapTw.put("totalcount", totalcount);//����
					listTw.add(mapTw);
				}
			}
			super.outPrintJsonByArray(listTw);
		}	
	}
	
	
}
//�ַ���¼
public void record()
{
	/**
	 * 1.����idȥ��ȡ��ǰ�˺ŵ���Ϣ��չʾ�ַ������ݣ�������˾��Ƭ���ķַ���¼��
	 * (1)�ж�id��������˾��Ƭ����
	 * (2)��1����5���Ƕ����ֹ�˾��id
	 * (3)Ƭ����id
	 * 
	 * 
	 * 
	 * 
	 * �ַ��ӿڣ�http://192.168.0.104:8080/Ltmcp/mobile/distribution_anomalyAction?condition.comId=5&pageBean.curentPage=
	 * 2.����condition�е���һ����
	 * 
	 * 
	 */
//	System.out.println("�ַ���¼��comId(������˾):"+condition.getComId());
//	System.out.println("�ַ���¼��comId(Ƭ����id):"+condition.getAreaid());
	String str = condition.getPerName();//������˾�Ƴ����ƻ��߾�������
	String address = condition.getStaName();//�������ص����ƣ�ʵ����address����������ɣ��ܷ���
	//System.out.println(str+":111");
	try 
	{
		address = new String(address.getBytes("UTF-8"),"utf-8");
		str = new String(str.getBytes("UTF-8"),"utf-8");
	} catch (UnsupportedEncodingException e1) 
	{
		e1.printStackTrace();
	}
	//System.out.println("�ַ���������"+str);
	String rename = str;
	String addre = address;
	String addffe = addre.substring(2, 4);
	//ʱ����������������������ڵ������ֱ��ȥ��ѯ
	//condition.setPerName(str);
	//condition.setStaName(address);
	String st_one = "xxxx�Ƴ�";
	String st_ga = "�Ƴ�";
	String st_pzh = "���Ƴ�";
	try {
		excRecordActionAp.setCondition(condition);//����ֻ��һ��comid���� areaid
		excRecordActionAp.setPageBean(pageBean);
		//boolean  a= st_one.equals(rename);
		//System.out.println(a);
	} catch (Exception e) {
		//e.printStackTrace();
	}

	if("".equals(rename))
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		super.outPrintJsonByArray(list);//ע������˺����ƣ�xxxx�Ƴ����㰲�ģ��Ƴ�
	}//else if(st_one.equals(rename) || st_ga.equals(rename) || st_pzh.equals(rename))//��֦�����㰲�����ԣ����������˺�id������ѯsecondorder��
	 else if("Ƭ��".endsWith(addffe))	
	{
		System.out.println("���뷽�����ַ���ѯ��������˾��");
		try 
		{
			excRecordActionAp.recordDis();//����ַ���¼����
		} catch (Exception e) 
		{
			
		}	
		
	}else//�������Ƭ���˺ŵ�¼��ѯ�ķַ���¼����¼Ϊǰ����������ѯ����position_inventory
	{
		System.out.println("���뷽�����ַ���ѯ��Ƭ����");
		try 
		{
			excRecordActionAp.recordDis_pianqu();//����ַ���¼����
		} catch (Exception e) 
		{
			
		}
		//1.����Ƭ���������ҵ�Ƭ����id
		//������ ,������ַ������վ������ƣ�����
		//2.����Ƭ����idȥ��ѯposition_inventory
		
		
		
	}
	
}
//����¼


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}
	public String getSecond_fh_person() {
		return second_fh_person;
	}
	public void setSecond_fh_person(String second_fh_person) {
		this.second_fh_person = second_fh_person;
	}
	public String getSecond_fh_address() {
		return second_fh_address;
	}
	public void setSecond_fh_address(String second_fh_address) {
		this.second_fh_address = second_fh_address;
	}
	public String getReceive_sh_address() {
		return receive_sh_address;
	}
	public void setReceive_sh_address(String receive_sh_address) {
		this.receive_sh_address = receive_sh_address;
	}
	public String getPianquId() {
		return pianquId;
	}
	public void setPianquId(String pianquId) {
		this.pianquId = pianquId;
	}
	public String getZhandianId() {
		return zhandianId;
	}
	public void setZhandianId(String zhandianId) {
		this.zhandianId = zhandianId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
