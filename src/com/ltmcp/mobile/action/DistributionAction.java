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
	private String username;//分发登录账号
	private String password;//分发登录账号
	private Integer areaidTwo;
	private String address;//分发人地址
	private String sendAddress;//分发到哪个地方的地址（app端下拉选择即可）
	private String code;//二维码
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
	//分发登录
	public void login(){
		//System.out.println("打印账号密码信息：：账号："+username+";密码："+password);
		//kk
		 try {
			 boolean sta = distributionBiz.checkDisLogin(username, password);//sta为true表示已经存在该账号，可以登录
			// System.out.println("name:"+username+"; password:"+password);
			 //System.out.println("打印判断分发登录账号密码是否存在："+sta);
			 if(sta){
				 //存在查询 areaid的值（二级公司或者是片区经理），先把人和分发的人地点返回给app（参照CarMobileAction类中的getSeaOrFreNum()方法）
				List<shoufa_person> firstlist = distributionBiz.serachPersonAddressorName(username, password);
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = null;//因为在firstlist确实只有一个在for里面只有一个
				if(firstlist == null){
					super.getPringWriter().print("null");
				}else
				{
					//ArrayList<Map> disList = new ArrayList<Map>();
					for(shoufa_person f : firstlist){
						map = new HashMap<String, Object>();
						map.put("stain", "-2066");
						map.put("name", f.getName());//姓名
						map.put("address", f.getAddress());//地址
						map.put("username", f.getUsername());
						map.put("password", f.getPassword());
						map.put("comid", f.getCom_id());
						map.put("pid", f.getAreaid());
						//shoufa_personServiceBiz.saveShoufa_personInfoToSession(f);
						list.add(map);
					}
					//获取该账号和密码对应的areaid的值或者com_id是否有值
					Integer areaid = null;
					Integer com_id = null;
					for(shoufa_person f : firstlist){
						areaid = f.getAreaid();
						com_id = f.getCom_id();
						}
					//System.out.println("二级公司id的值："+com_id+"，片区id值："+areaid);
					List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
					if(areaid==null || listTw==null){//areaid为空表示为二级分公司登录，并根据com_id的值找到分公司的所有片区
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
	//发货地点选择2
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
	//发货地点的选择
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
						map.put("name", f.getName());//姓名
						map.put("address", f.getAddress());//地址
						
						list.add(map);
					}
					//获取该账号和密码对应的areaid的值或者com_id是否有值
					Integer areaid = null;
					Integer com_id = null;
					for(shoufa_person f : firstlist){
						areaid = f.getAreaid();
						com_id = f.getCom_id();
						}
					List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
					if(areaid==null || listTw==null){      //areaid为空表示为二级分公司登录，并根据com_id的值找到分公司的所有片区
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
							//System.out.println("打印根据账号获取到的地址(二级分公司下的片区)："+a.getArea_name());
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
						super.outPrintJsonByArray(listTw);//返回的是地点选择的json
						
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
	
	//检查提交的袋子或者箱子的二维码 
	public void checkCode()
	{
		//有空的的字符串再去substring出错
		String codee= code;
		if(code == null)
		{
			super.getPringWriter().print(-2000);//其他异常扫描为空
		}else
		{
			String co = codee.substring(0, 1);
			if("b".equals(co))
			{
				
				//防止重复扫去验证secondorder
				//防止重复扫去验证position_inventory
				boolean stas = distributionBiz.checkBagCodeBySecondOrderAndPosition_inventory(codee);//存在则表示已经被分发stas为false，不存在则为true
				//System.out.println(stas);
				if(!stas)
				{
					System.out.println("马上返回-1999");
					super.getPringWriter().print(-1999);//已经被扫描过了并已分发
				}
				boolean staOne = distributionBiz.checkBagCode(codee);
				if(staOne && stas)
				{
					super.getPringWriter().print(-666);//扫描成功
				}
				    //System.out.println(" "+"袋子二维码是否存在："+staOne);
			}else if("x".equals(co))
					{
				       //防止重复扫去验证secondorder
			           //防止重复扫去验证position_inventory
			            boolean stass = distributionBiz.checkBoxCodeBySecondOrderAndPosition_inventory(codee);//存在则表示已经被分发 
			            if(!stass)
						{
							super.getPringWriter().print(-1999);//已经被扫描过了并已分发
						}
						boolean staTwo = distributionBiz.checkBoxCode(codee);
						 
						if(staTwo && stass)//满足条件：表中有存在
						{
							super.getPringWriter().print(-666);//扫描成功
						}
						//System.out.println(" "+"袋子二维码是否存在："+staTwo);
					}
				else{
						super.getPringWriter().print(-2067);//不是箱子或者袋子二维码，请重新扫描其他箱子或者袋子二维码
						//super.getPringWriter().print(-2069);
			     	}
		}
			
	}
	private String bagCode;//袋子二维码
	private String boxCode;//箱子二维码
	private String second_fh_person;
	private String second_fh_address;
	private String receive_sh_address;
	private String pianquId;//片区id
	private String zhandianId;//站点id
	
	//确认提交    ，分发动作,提交过来的数据：分发人账号，密码，分发人姓名，分发人地址，发往的地址，扫描的是箱子还是袋子二维码（难点）验证，创建时间，一并写入订单表和片区（站点）库存表（库存表2张，一张片区一张站点）
	public void distributeInInventory()
	{
		System.out.println("进入确认提交的步骤");
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
		//System.out.println("打印片区id："+pianquId+"  ; 打印站点id: "+zhandianId);
		if(areaid==null)//二级公司发片区提交的信息（前提条件是在扫描二维码成功的情况下，在扫描二维码的时候还要判断二维码是否存在，有关联的情况）,二维码已经判断存在之后，提交“确定”
		{
			int zd = 99999;
			boolean zdi = false;
			try 
			{
				zd = Integer.parseInt(zhandianId);
			} catch (NumberFormatException e)
			{
				zdi = true;//表示二级分公司没有用分发app端没有选择站点发货
			}
			//System.out.println(zdi+"打印zdi");
			Area_manager_inventory ami  = null;
			Second_order so = null;
			String rsa = receive_sh_address;//如果是1和5   二级分公司的id，片区id，站点id
			       //System.out.println("马上进入判断片区id(pianquId)和站点id(zhandianId)");//如果只有片区的的id，那么要写入片区的：Area_manager_inventory表，如果有片区的id又有站点的id，那么直接写入second_order表即可
			if(zdi)//bl为true说明zhandianId转化成int失败证明不是数字，app端二级分公司没选择站点发货
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
				//System.out.println(b+":打印app端传过来的值，箱子或者袋子二维码");
				String[] arr = b.split("_");         
				for(int i=0;i<arr.length;i++)
				{
					String codeThree = arr[i];
					System.out.println("打印接收到的袋子二维码数据:"+codeThree);
					String co = codeThree.substring(0, 1);
					//System.out.println(codeThree+":二维码");
					if(codeThree.startsWith("b"))//distributionBiz.saveArea_manager_inventory(codee);
					{
						so = new Second_order();
						so.setSecond_fh_person(name);//分发人姓名
						so.setSecond_fh_address(address);//分发人地址
						so.setReceive_sh_address(pqadress);//发往的地址(不管发到哪里都是二级公司负责人发的，发片区经理，油库，站点都会记录)
						so.setBag_code(arr[i]);//袋子二维码
						so.setBag_code_count(50);
						so.setFhtime(new Timestamp(System.currentTimeMillis()));//时间
						
						ami = new Area_manager_inventory();
						ami.setManage_person(pqname);//片区经理姓名
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
					}else if(codeThree.startsWith("x"))//分发人[账号，密码]判断用的； 分发人姓名，分发人地址     , 发往的地址，扫描的是箱子还是袋子二维码（难点）验证，创建时间   
							{
						        so = new Second_order();
								so.setSecond_fh_person(name);//分发人姓名
								so.setSecond_fh_address(address);//分发人地址
								so.setReceive_sh_address(pqadress);//发往的地址:片区
								so.setBox_code(arr[i]);//箱子二维码
								so.setBox_count(10);
								//System.out.println("即将进入保存箱子二维码方法");
								so.setFhtime(new Timestamp(System.currentTimeMillis()));//时间
								//System.out.println(so.getFhtime());
							
								
								//在写进入area_manager_inventory表
								ami = new Area_manager_inventory();
								ami.setManage_person(pqname);//片区经理姓名
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
								super.getPringWriter().print(-2067);//不是箱子或者袋子二维码，请重新扫描其他箱子或者袋子二维码
					     	}	
				}
				super.getPringWriter().print(-2066);//提交成功
			}
			if((zdi == false) && (pianquId != null))//极大可能二级公司发油库
			{
				//System.out.println("片区id和站点id都不等于空");
				int pq = Integer.parseInt(pianquId);
				int zhandian = Integer.parseInt(zhandianId);
				//System.out.println("站点："+zhandian);
				List<shoufa_person> sp = distributionBiz.findAddressByPianquId(pq);
				String pqadress = null;
				for(shoufa_person s: sp)
				{
					pqadress = s.getAddress();
					//System.out.println("片区："+pqadress);
				}
				List<Position>  zhandiantr = distributionBiz.findAddrressByZhandianId(zhandian);
				String zhandianress = null;
				for(Position p: zhandiantr)
				{
					zhandianress = p.getPosName();
					//System.out.println("站点："+zhandianress);
				}
				
				//String b = code.replaceAll("[\\[\\]]", ""); 
				String b = code;
				//System.out.println(b+":打印app端传过来的值，箱子或者袋子二维码");
				String[] arr = b.split("_");
				for(int i=0;i<arr.length;i++)
				{
					String codeThree = arr[i];
					String co = codeThree.substring(0, 1);
					//System.out.println(codeThree+":二维码");
					if(codeThree.startsWith("b"))//distributionBiz.saveArea_manager_inventory(codee);
					{
						so = new Second_order();
						so.setSecond_fh_person(name);//分发人姓名
						so.setSecond_fh_address(address);//分发人地址
						so.setReceive_sh_address(pqadress);//发往的地址:片区写入而已
						so.setBag_code(arr[i]);//袋子二维码
						so.setBag_code_count(50);
						so.setReceive_sh_addressInStation(zhandianress);//实际发的是到站点
						so.setFhtime(new Timestamp(System.currentTimeMillis()));//时间
						//System.out.println(so.getSecond_fh_person());
						//System.out.println(so.getSecond_fh_address());
						//System.out.println(so.getReceive_sh_address());
						//System.out.println(so.getFhtime());
						try 
						{
							distributionBiz.saveSecond_order(so);
						} catch (Exception e) {
							
						}	
					}else if(codeThree.startsWith("x"))//分发人[账号，密码]判断用的； 分发人姓名，分发人地址     , 发往的地址，扫描的是箱子还是袋子二维码（难点）验证，创建时间   
							{
						        so = new Second_order();
								so.setSecond_fh_person(name);//分发人姓名
								so.setSecond_fh_address(address);//分发人地址
								so.setReceive_sh_address(pqadress);//发往的地址:片区写入而已
								so.setReceive_sh_addressInStation(zhandianress);//实际发的是到站点
								so.setBox_code(arr[i]);//箱子二维码
								so.setBox_count(10);
								//System.out.println("即将进入保存箱子二维码方法");
								so.setFhtime(new Timestamp(System.currentTimeMillis()));//时间
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
								super.getPringWriter().print(-2067);//不是箱子或者袋子二维码，请重新扫描其他箱子或者袋子二维码
					     	}	
				}
				super.getPringWriter().print(-2066);//提交成功
			}
		}
		if(com_id == null)//片区提交的信息,片区指向——发->站点
		{
			int zd = 99999;
			int pq = 88888;
			boolean zdi = false;
			try 
			{
				pq = Integer.parseInt(pianquId);
				zd = Integer.parseInt(zhandianId);//站点id转化
			} catch (NumberFormatException e)
			{
				zdi = true;//zdi一定为false
			}
			//第一步根据站点的id去找到站点具体名称
			//第二步当拆箱之后，扫描袋子二维码
			//这一步时提交步骤，验证的事情早已经做过了，不过这里是片区发到站点·····[现在只能直接去生产表中判断喽0-0，有嘛就直接有嘛，没有嘛，就直接没有嘛，重复怎么搞呢0-0，重复确认提交之后将不再写入的哈，hql语句限制]第三步才能开始去检查袋子二维码（检查的是片区存储表中是否有二级分公司发的袋子，在通过生产表中的袋子二维码关联箱子，只要扫描马上去生产关联的袋子二维码表找到箱子二维码）
			//第四步根据查询出来的信息为true,改变片区存储表的中袋子或者箱子二维码状态，其中若是袋子暂时不用管（），特别注意拆箱子发袋子（这个状态只能改变为1箱子中还没发完，发完为2）
			//第五步把袋子二维码写入position_inventory表
			List<Position> po = distributionBiz.findAddrressByZhandianId(zd);
			String posName = null;
			for(Position pp: po)
			{
				posName = pp.getPosName();
			}
			String b = code;
			//System.out.println(b+":打印app端传过来的值（片区分发），箱子或者袋子二维码");
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
							super.getPringWriter().print(-2067);//不是箱子或者袋子二维码，请重新扫描其他箱子或者袋子二维码
				     	}	
			}
			super.getPringWriter().print(-2066);
			
		}
	}
	
	//追溯,根据封签内码或者外码追溯封签使用情况，包含异常的封签与正常的封签（除非封签内外码皆备损坏不能查询）
		public void traceAction()
		{
			
			/**
			1.没使用的封签：展示该封签未被使用
			2.使用中：追溯app是没办法扫到使用中的情况的，因为封签在车上，除非跟着车能扫
			3.使用后：各种异常情况的（未注册，没施封）
			  (1)没有注册的情况直接说明即可
			  (2)没施封的情况：
			     1)第一步找到该封签对应的袋子二维码和箱子二维码
			     2)站点中肯定有袋子，或者 箱子，再去找该站点和片区（position_inventory），在这里站点和片区找完
			     3)再来根据ExcRecordAction中的list方法找：车，人（施封，解封【这个是信息的来源】，驾驶员），施封地点，解封地点
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
			int dimensionalBarCode_id = 99;//DimensionalBarCode表的id
			String address = null;
			String findbagCode = null;
			String findboxCode = null;
			String returnCode = null;
			
			
			String companyAddress = null;//二级分公司地址
			Timestamp companySendTime = null;//二级公司分发时间
			String areaAddress = null;//片区地址
			Timestamp areaTime = null;//片区分发时间
			String freezePerson = null;//施封人
			Timestamp freeze_time = null;//施封时间
			String freezeAddress = null;//施封地点
			String unfreezePerson = null;//解封人
			Timestamp unfreeze_time = null;//解封时间
			String unfeezeAddress = null;//解封地点
//			String shifengRen = null;//施封人
//			String jiefenRen = null;//解封人
			/**
			 * 施封人（注意这里：实际情况了解追溯的本质是什么？不一定是责任问题，针对管理才是想要做的事情，所以这里施封人在正常情况下都会展示）
			 * 
			 */
			
			if(code.startsWith("0"))
			{
				boolean sta = false;//iiii
				try {
					sta = distributionBiz.checkCodeNeiMa(code);//sta为false表示没有找到
				} catch (Exception e) {
				}
				if(!sta)//false加上“！”表示true返回-107
				{
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("returnCode", returnCode);
					map.put("findbagCode", findbagCode);
					map.put("findboxCode", findboxCode);
					map.put("address", address);
					String statusChange = status+"";
					map.put("status",statusChange);
					map.put("success", "-107");//未注册
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
					//super.getPringWriter().print(-107);//未注册
					return;
				}else
				{
					dbcList = distributionBiz.findBagCodeByCode(code);//查袋子
					for(DimensionalBarCode ac : dbcList)
					{
						returnCode = ac.getUnfreeze_content();
						//System.out.println(returnCode+":打印外码");
						findbagCode = ac.getBag_code();
						status = ac.getFreeze_status();//0表示没使用，1表示使用中，2使用完毕
						//System.out.println(findbagCode+":打印根据封签内码追溯袋子二维码");
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
						map.put("success", "-206");//该二维码没有被袋子关联（或者未知异常）
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
							//System.out.println(findbagCode+":进入箱子关联查询:打印根据封签内码追溯袋子二维码");
							bcdBoxList = distributionBiz.findBoxCodeByFindbagCode(findbagCode);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						
						for(Dbc_BagCodeBind db: bcdBoxList)
						{
							findboxCode = db.getBox_code();
							//System.out.println(findboxCode+"根据袋子二维码找到对应箱子二维码");
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
							map.put("success", "-209");//该二维码没有被箱子关联（或者未知异常）
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
							//super.getPringWriter().print(-209);//该二维码没有被箱子关联（或者未知异常）
							return;
						}
						
					}
					//super.getPringWriter().print(-2066);//测试成功：根据内码找到该封签对应的袋子二维码和箱子二维码
					//根据袋子二维码找站点,再去找箱子，因为有可能为空pilist
					boolean sta1 = true;//mm
					try {
						pilist = distributionBiz.findPositionBybagCode(findbagCode);//根据袋子二维码找站点pilist
						if(pilist.size() == 0){
							sta1 = false;
						}
						//System.out.println("（追溯）该封签是否有站点信息："+sta1);
					} catch (Exception e) {
						//e.printStackTrace();//记得去掉
					}
					if(sta1)//（是否要这样sta1 && findboxCode!=null）若这里为false表示该封签在该站点表中没找到袋子二维码，但是有可能从上级那里直接得到整箱子的发给该站点的
					{
						int pianquId = 99;
						for(position_inventory pi:pilist)
						{
							address = pi.getPosition_name();
							areaTime = pi.getTime();//片区分发到站点的时间
							pianquId = pi.getPianqu_id();
						}
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
							if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
							{
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
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
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
								}
								//根据袋子二维码查询二级公司发放该袋子的时间
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
								//找到施封人和施封时间
								//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
								//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
								
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
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
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
								//若状态为2，根据sealed的id去freeze中去找到解封信息
								int sealedId = 5;//5在sealed中id不存在的，可以设置为5
								//System.out.println("进入状态为2的查询追溯");
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
								//System.out.println("打印解封人："+unfreezePerson);
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
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
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
					}else//但是有可能从上级那里直接得到整箱子的发给该站点的
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
							if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
							{
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
								map.put("freezeAddress", freezeAddress);//施封地点
								map.put("unfeezeAddress",unfeezeAddress );//解封地点
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
							}else if(status == 1)
							{
								//找到施封人和施封时间
								//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
								//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
								
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);//新增加：二级公司名称
								map.put("companySendTime", companySendTime);//新增加：二级公司发放时间
								map.put("areaAddress", areaAddress);//新增加：片区名称
								map.put("areaTime", areaTime);//新增加：片区发放时间
								map.put("freezePerson", freezePerson);//新增加：施封人
								map.put("freeze_time", freeze_time);//新增加：施封时间
								map.put("unfreezePerson", unfreezePerson);//新增加：解封人
								map.put("unfreeze_time", unfreeze_time);//新增加：解封时间
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
							}else if(status == 2)
							{
								//若状态为2，根据sealed的id去freeze中去找到解封信息
								int sealedId = 5;//5在sealed中id不存在的，可以设置为5
								//System.out.println("进入状态为2的查询追溯");
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
								//System.out.println("打印解封人："+unfreezePerson);
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
						}else{//最后在这里是：油库,站点（查询油库的袋子二维码和箱子二维码）或者二级分公司直接越级片区经理发到站点secondorder表
							   boolean stayt = false;
							   try {
								   so = distributionBiz.findSecondre_sh_addressInStationBybagCode(findbagCode);
								   //测试打印
								   for(Second_order rt: so)
								   {
									   System.out.println("打印袋子:"+rt.getBag_code());
									   System.out.println("打印箱子:"+rt.getBox_code());
									   System.out.println("打印分发到哪地方去了:"+rt.getReceive_sh_addressInStation());
									   System.out.println("打印分发到哪地方去了:"+rt.getReceive_sh_address());
									   System.out.println("分发时间："+rt.getFhtime());
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
							    	//根据地址address找到片区id ，查询shoufa_person
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
									if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
									{
										int comId = 99;//
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
										//找到施封人和施封时间
										//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
										//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
										
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
											//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
											
										}
										int comId = 99;
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
										//若状态为2，根据sealed的id去freeze中去找到解封信息
										int sealedId = 5;//5在sealed中id不存在的，可以设置为5
										//System.out.println("进入状态为2的查询追溯");
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
											//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
											
										}
										//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
										//System.out.println("打印解封人："+unfreezePerson);
										int comId = 99;
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
							    	 				System.out.println("打印箱子二维码:"+sor.getBox_code());
							    	 				System.out.println("打印发放地点:"+sor.getSecond_fh_address());
							    	 				System.out.println("打印收封签地址："+sor.getReceive_sh_address());
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
												
												if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
												{
													int comId = 99;
													//再根据position_inventory表中的片区id找到该片区经理地址
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//得到片区经理地址
													}
													//根据片区id找到二级分公司的id
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
													//根据二级分公司id找到二级分公司地址
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//得到二级分公司地址
													}
													//根据箱子二维码查询二级公司发放该箱子的时间
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
													//找到施封人和施封时间
													//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
													//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
													
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
														//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
														
													}
													int comId = 99;
													//再根据position_inventory表中的片区id找到该片区经理地址
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//得到片区经理地址
													}
													//根据片区id找到二级分公司的id
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
													//根据二级分公司id找到二级分公司地址
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//得到二级分公司地址
													}
													//根据箱子二维码查询二级公司发放该箱子的时间
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
													}
													String statusChange = status+"";
													map.put("status",statusChange);
													map.put("companyAddress", companyAddress);//新增加：二级公司名称
													map.put("companySendTime", companySendTime);//新增加：二级公司发放时间
													map.put("areaAddress", areaAddress);//新增加：片区名称
													map.put("areaTime", areaTime);//新增加：片区发放时间
													map.put("freezePerson", freezePerson);//新增加：施封人
													map.put("freeze_time", freeze_time);//新增加：施封时间
													map.put("unfreezePerson", unfreezePerson);//新增加：解封人
													map.put("unfreeze_time", unfreeze_time);//新增加：解封时间
													map.put("freezeAddress", freezeAddress);
													map.put("unfeezeAddress",unfeezeAddress );
													map.put("success", "-2066");
													list.add(map);
													super.outPrintJsonByArray(list);
													return;
													
												}else if(status == 2)
												{
													//若状态为2，根据sealed的id去freeze中去找到解封信息
													int sealedId = 5;//5在sealed中id不存在的，可以设置为5
													//System.out.println("进入状态为2的查询追溯");
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
														//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
														
													}
													//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
													//System.out.println("打印解封人："+unfreezePerson);
													int comId = 99;
													//再根据position_inventory表中的片区id找到该片区经理地址
													try 
													{
														sp = distributionBiz.findAddressByPianquId(pianquId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														areaAddress = spe.getAddress();//得到片区经理地址
													}
													//根据片区id找到二级分公司的id
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
													//根据二级分公司id找到二级分公司地址
													try 
													{
														sp = distributionBiz.findcompanyAddressBycomId(comId);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(shoufa_person spe : sp)
													{
														companyAddress = spe.getAddress();//得到二级分公司地址
													}
													//根据箱子二维码查询二级公司发放该箱子的时间
													try 
													{
														so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
													} catch (Exception e) {
														// TODO: handle exception
													}
													for(Second_order sod : so)
													{
														companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
					sta = distributionBiz.checkCodeWaiMa(code);//检测外码，sta为false表示没有找到
				} catch (Exception e) {
				}
				if(!sta)//false加上“！”表示true返回-107
				{
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("returnCode", returnCode);
					map.put("findbagCode", findbagCode);
					map.put("findboxCode", findboxCode);
					map.put("address", address);
					String statusChange = status+"";
					map.put("status",statusChange);
					map.put("success", "-107");//未注册
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
					//super.getPringWriter().print(-107);//未注册
					return;
				}else
				{
					dbcList = distributionBiz.findBagCodeByWaimaCode(code);//根据外码查袋子
					for(DimensionalBarCode ac : dbcList)
					{
						returnCode = ac.getFreeze_content();//得到内码
						//System.out.println(returnCode+":打印外码");
						findbagCode = ac.getBag_code();
						status = ac.getFreeze_status();//0表示没使用，1表示使用中，2使用完毕
						//System.out.println(findbagCode+":打印根据封签内码追溯袋子二维码");
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
						map.put("success", "-206");//该二维码没有被袋子关联（或者未知异常）
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
							//System.out.println(findbagCode+":进入箱子关联查询:打印根据封签内码追溯袋子二维码");
							bcdBoxList = distributionBiz.findBoxCodeByFindbagCode(findbagCode);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						
						for(Dbc_BagCodeBind db: bcdBoxList)
						{
							findboxCode = db.getBox_code();
							//System.out.println(findboxCode+"根据袋子二维码找到对应箱子二维码");
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
							map.put("success", "-209");//该二维码没有被箱子关联（或者未知异常）
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
							//super.getPringWriter().print(-209);//该二维码没有被箱子关联（或者未知异常）
							return;
						}
						
					}
					//super.getPringWriter().print(-2066);//测试成功：根据内码找到该封签对应的袋子二维码和箱子二维码
					//根据袋子二维码找站点,再去找箱子，因为有可能为空pilist
					boolean sta1 = true;//mm
					try {
						pilist = distributionBiz.findPositionBybagCode(findbagCode);//根据袋子二维码找站点pilist
						if(pilist.size() == 0){
							sta1 = false;
						}
						//System.out.println("（追溯）该封签是否有站点信息："+sta1);
					} catch (Exception e) {
						//e.printStackTrace();//记得去掉
					}
					if(sta1)//（是否要这样sta1 && findboxCode!=null）若这里为false表示该封签在该站点表中没找到袋子二维码，但是有可能从上级那里直接得到整箱子的发给该站点的
					{
						int pianquId = 99;
						for(position_inventory pi:pilist)
						{
							address = pi.getPosition_name();
							areaTime = pi.getTime();//片区分发到站点的时间
							pianquId = pi.getPianqu_id();
						}
						List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("returnCode", returnCode);
						map.put("findbagCode", findbagCode);
						map.put("findboxCode", findboxCode);
						map.put("address", address);
							if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
							{
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
								}
								//根据袋子二维码查询二级公司发放该袋子的时间
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
								//找到施封人和施封时间
								//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
								//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
								
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
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
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
								//若状态为2，根据sealed的id去freeze中去找到解封信息
								int sealedId = 5;//5在sealed中id不存在的，可以设置为5
								//System.out.println("进入状态为2的查询追溯");
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
								//System.out.println("打印解封人："+unfreezePerson);
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
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
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
					}else//但是有可能从上级那里直接得到整箱子的发给该站点的
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
							if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
							{
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
								//找到施封人和施封时间
								//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
								//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
								
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
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
								}
								String statusChange = status+"";
								map.put("status",statusChange);
								map.put("companyAddress", companyAddress);//新增加：二级公司名称
								map.put("companySendTime", companySendTime);//新增加：二级公司发放时间
								map.put("areaAddress", areaAddress);//新增加：片区名称
								map.put("areaTime", areaTime);//新增加：片区发放时间
								map.put("freezePerson", freezePerson);//新增加：施封人
								map.put("freeze_time", freeze_time);//新增加：施封时间
								map.put("unfreezePerson", unfreezePerson);//新增加：解封人
								map.put("unfreeze_time", unfreeze_time);//新增加：解封时间
								map.put("freezeAddress", freezeAddress);
								map.put("unfeezeAddress",unfeezeAddress );
								map.put("success", "-2066");
								list.add(map);
								super.outPrintJsonByArray(list);
								return;
								
							}else if(status == 2)
							{
								//若状态为2，根据sealed的id去freeze中去找到解封信息
								int sealedId = 5;//5在sealed中id不存在的，可以设置为5
								//System.out.println("进入状态为2的查询追溯");
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
									
									//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
									
								}
								//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
								//System.out.println("打印解封人："+unfreezePerson);
								int comId = 99;
								//再根据position_inventory表中的片区id找到该片区经理地址
								try 
								{
									sp = distributionBiz.findAddressByPianquId(pianquId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									areaAddress = spe.getAddress();//得到片区经理地址
								}
								//根据片区id找到二级分公司的id
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
								//根据二级分公司id找到二级分公司地址
								try 
								{
									sp = distributionBiz.findcompanyAddressBycomId(comId);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(shoufa_person spe : sp)
								{
									companyAddress = spe.getAddress();//得到二级分公司地址
								}
								//根据箱子二维码查询二级公司发放该箱子的时间
								try 
								{
									so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
								} catch (Exception e) {
									// TODO: handle exception
								}
								for(Second_order sod : so)
								{
									companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
						}else{//最后在这里是：油库,站点（查询油库的袋子二维码和箱子二维码）或者二级分公司直接越级片区经理发到站点secondorder表
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
							    	//根据地址address找到片区id ，查询shoufa_person
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
									if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
									{
										int comId = 99;
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
										//找到施封人和施封时间
										//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
										//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
										
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
											//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
											
										}
										int comId = 99;
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
										//若状态为2，根据sealed的id去freeze中去找到解封信息
										int sealedId = 5;//5在sealed中id不存在的，可以设置为5
										//System.out.println("进入状态为2的查询追溯");
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
											//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
											
										}
										//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
										//System.out.println("打印解封人："+unfreezePerson);
										int comId = 99;
										//再根据position_inventory表中的片区id找到该片区经理地址
										try 
										{
											sp = distributionBiz.findAddressByPianquId(pianquId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											areaAddress = spe.getAddress();//得到片区经理地址
										}
										//根据片区id找到二级分公司的id
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
										//根据二级分公司id找到二级分公司地址
										try 
										{
											sp = distributionBiz.findcompanyAddressBycomId(comId);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(shoufa_person spe : sp)
										{
											companyAddress = spe.getAddress();//得到二级分公司地址
										}
										//根据箱子二维码查询二级公司发放该箱子的时间
										try 
										{
											so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
										} catch (Exception e) {
											// TODO: handle exception
										}
										for(Second_order sod : so)
										{
											companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
					    	 				System.out.println("打印箱子二维码:"+sor.getBox_code());
					    	 				System.out.println("打印发放地点:"+sor.getSecond_fh_address());
					    	 				System.out.println("打印收封签地址："+sor.getReceive_sh_address());
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
										
										if(status == 0)//为0的状态有2类：没有使用的情况和解封未施封的情况，这里注意status在app端判断的时候为0的时候就是未施封（或者未使用）
										{
											int comId = 99;
											//再根据position_inventory表中的片区id找到该片区经理地址
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//得到片区经理地址
											}
											//根据片区id找到二级分公司的id
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
											//根据二级分公司id找到二级分公司地址
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//得到二级分公司地址
											}
											//根据箱子二维码查询二级公司发放该箱子的时间
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
											//找到施封人和施封时间
											//第一步：在dimensionalBarCode表中找到该封签对应的id,已经得到：dimensionalBarCode_id
											//第二步：去根据封签的id去sealed施封表中找到施封人和施封时间
											
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
												//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
												
											}
											int comId = 99;
											//再根据position_inventory表中的片区id找到该片区经理地址
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//得到片区经理地址
											}
											//根据片区id找到二级分公司的id
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
											//根据二级分公司id找到二级分公司地址
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//得到二级分公司地址
											}
											//根据箱子二维码查询二级公司发放该箱子的时间
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
											}
											String statusChange = status+"";
											map.put("status",statusChange);
											map.put("companyAddress", companyAddress);//新增加：二级公司名称
											map.put("companySendTime", companySendTime);//新增加：二级公司发放时间
											map.put("areaAddress", areaAddress);//新增加：片区名称
											map.put("areaTime", areaTime);//新增加：片区发放时间
											map.put("freezePerson", freezePerson);//新增加：施封人
											map.put("freeze_time", freeze_time);//新增加：施封时间
											map.put("unfreezePerson", unfreezePerson);//新增加：解封人
											map.put("unfreeze_time", unfreeze_time);//新增加：解封时间
											map.put("freezeAddress", freezeAddress);
											map.put("unfeezeAddress",unfeezeAddress );
											map.put("success", "-2066");
											list.add(map);
											super.outPrintJsonByArray(list);
											return;
											
										}else if(status == 2)
										{
											//若状态为2，根据sealed的id去freeze中去找到解封信息
											int sealedId = 5;//5在sealed中id不存在的，可以设置为5
											//System.out.println("进入状态为2的查询追溯");
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
												//System.out.println("打印施封人信息:"+se.getPerson().getPerTrueName());
												
											}
											//根据sealedId找到解封人信息，后续增加与否看看整个项目是否继续
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
											//System.out.println("打印解封人："+unfreezePerson);
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
											//再根据position_inventory表中的片区id找到该片区经理地址
											try 
											{
												sp = distributionBiz.findAddressByPianquId(pianquId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												areaAddress = spe.getAddress();//得到片区经理地址
											}
											//根据片区id找到二级分公司的id
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
											//根据二级分公司id找到二级分公司地址
											try 
											{
												sp = distributionBiz.findcompanyAddressBycomId(comId);
											} catch (Exception e) {
												// TODO: handle exception
											}
											for(shoufa_person spe : sp)
											{
												companyAddress = spe.getAddress();//得到二级分公司地址
											}
											//根据箱子二维码查询二级公司发放该箱子的时间
											try 
											{
												so = distributionBiz.findSecondre_sh_addressInStationByboxCode(findboxCode);
											} catch (Exception e) {
												
											}
											for(Second_order sod : so)
											{
												companySendTime = sod.getFhtime();//得到二级分公司发往片区的时间
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
				map.put("success", "-208");//扫描的不是封签上的内外码
				list.add(map);
				super.outPrintJsonByArray(list);
				//super.getPringWriter().print(-107);//未注册
				return;
				//super.getPringWriter().print(-208);//扫描的不是封签二维码（不是0或者1开头）
			}
			
    }
		
//分发记录
public void DistributionRecords()
{
	
	/* 1.根据账号和密码去查询secondorder和area_manager_inventory表
	 * 2.发货人，发货地点，实际收货人，规格（是一箱还是一袋子），封签数目，日期
	 * 3.条件是什么？
	 *   （1）点“分发记录”的第一个条件是：什么时间段的展示？？
	 *   （2）进去之后有一个日期：选择的是具体的某一天的分发记录？？
	 * */
}
private PageBean pageBean;
private ExcRecordAction  excRecordAction;
private ExcRecordCondition condition; // 查询列表时的条件
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
//时间接口
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
//异常接口，条件变化查询不同结果
public void anomalyAction(){
//最近七天	
//System.out.println(condition.getTime()+":打印条件");
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
//		condition.setBeginTime(begintime);//开始时间
//		condition.setEndTime(endTime);//结束时间
//		System.out.println("刚进入方法打印当前第几页："+pageBean.getCurentPage());
//		System.out.println("打印结束时间:"+condition.getEndTime()+";打印exctype: "+condition.getExcType()+";打印时间："+condition.getBeginTime());
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
//		condition.setBeginTime(begintime);//开始时间
//		condition.setEndTime(endTime);//结束时间
//		System.out.println("刚进入方法打印当前第几页："+pageBean.getCurentPage());
//		System.out.println("打印结束时间:"+condition.getEndTime()+";打印exctype: "+condition.getExcType()+";打印时间："+condition.getBeginTime());
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
//System.out.println("打印time："+condition.getTime());
//2016.02     2016.11 判断时间
//默认查询一年
//System.out.println("打印异常状态："+condition.getExcType());
//System.out.println("打印站点："+condition.getSite());
	
	boolean sta = false;
	
	if("".equals(condition.getTime()))//condition.time=,比较内容
	{
		sta = true;
	}
	
	if(sta)
	{
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());
		String endTime = "";
		endTime = sdf.format(endTime1);
		//开始时间修改
		String beginTi = endTime.substring(0, 4);
		String beginTim = beginTi+"-01-01 00:00:000";
		//System.out.println(beginTim);
		
		Date begintime1 = new java.util.Date( (new java.util.Date()).getTime()-86400000*7*4);
		String begintime ="";
		begintime = sdf.format(begintime1);
		condition.setBeginTime(beginTim);//开始时间
		condition.setEndTime(endTime);//结束时间
		
		//System.out.println("刚进入方法打印当前第几页："+pageBean.getCurentPage());
		//System.out.println("打印结束时间:"+condition.getEndTime()+";打印exctype: "+condition.getExcType()+";打印时间："+condition.getBeginTime());
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
		//判断时间
		String beTime = null;
		String enTime = null;
		
		String time_interception_year = condition.getTime().substring(0, 4);//2016.12，0-4截取年份，5-7截取月份
		String time_interception_month = "ty";
		try {
			time_interception_month = condition.getTime().substring(5, 7);
		} catch (Exception e) {
			
		}
		String year_month = time_interception_year +"-"+time_interception_month;
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		Timestamp endTime1 = new Timestamp(System.currentTimeMillis());//获取当前时间
		String endTime = "";
		endTime = sdf.format(endTime1);
		String ww = endTime.substring(0,7);
		//只有condition.getTime()=某一年（比如2016,2017）的时候
		if("ty".equals(time_interception_month))//当condition.getTime()值月份为空的情况，表示查询整年
		{
			String beginTim = time_interception_year+"-01-01 00:00:000";
			 String ent     = time_interception_year+"-12-31 23:59:000";
			beTime = beginTim;
			enTime = ent;
		}
		else if(ww.equals(year_month))//判断是否是当前月,注意中间的-符号 （ 比较内容equals）
		{
			
			//开始时间修改
			String beginTim = year_month+"-01 00:00:000";
			beTime = beginTim;
			enTime = endTime;
		}else//不是当前月,根据获取到的年月去查是否为闰年或者平年
		{
			int yearr = Integer.parseInt(time_interception_year);
			int monthh = Integer.parseInt(time_interception_month);
			if(yearr%4==0 && yearr%100!=0 || yearr%400==0) 
			{
				if(monthh==1 || monthh==3 || monthh==5 || monthh==7 || monthh==8 || monthh==10 || monthh==12)
				{
					//31天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-31 23:59:000";
					
				}else if(monthh==4 || monthh==6 || monthh==9 || monthh==11)
				{
					//30天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-30 23:59:000";
				}else
				{
					//29天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-29 23:59:000";
					
				}
				//System.out.println("是闰年");
				
			}else
			{
				if(monthh==1 || monthh==3 || monthh==5 || monthh==7 || monthh==8 || monthh==10 || monthh==12)
				{
					//31天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-31 23:59:000";
					
				}else if(monthh==4 || monthh==6 || monthh==9 || monthh==11)
				{
					//30天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-30 23:59:000";
				}else
				{
					//28天
					beTime = year_month+"-01 00:00:000";
					enTime = year_month+"-28 23:59:000";
				}
				//System.out.println("是平年");
			}
		}
		//System.out.println("打印开始查询时间:"+beTime+"/n"+"打印结束查询时间："+enTime);
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
//统计总数(二级公司的账号或者片区的账号进来，统计该账号下每一个片区的总数，每一个站点的总数)
public void totalCount()
{
	//根据账号姓名找到该账号下的所有子节点名称：分发人，分发至下属子节点名称，分发总数
	String str = condition.getPerName();
	try 
	{
		str = new String(str.getBytes("UTF-8"),"utf-8");
	} catch (UnsupportedEncodingException e1) 
	{
		
	}
	//kk
	//获取该名字对应的areaid的值或者com_id是否有值
	int i = 0;
	Integer areaid = null;
	Integer com_id = null;
	Integer totalCount = 0;
	List<shoufa_person> solist = null;
	if("".equals(str))
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		super.outPrintJsonByArray(list);//注意测试账号名称：xxxx科长，广安的：科长
	}else
	{
		solist = distributionBiz.findPianquIdByName(str);
		if(solist == null)
		{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			super.outPrintJsonByArray(list);//注意测试账号名称：xxxx科长，广安的：科长
			
		}else
		{
			for(shoufa_person sp : solist)
			{
				areaid = sp.getAreaid();
				com_id = sp.getCom_id();
			}
			List<Map<String,Object>> listTw = new ArrayList<Map<String,Object>>();
			if(areaid==null || listTw==null)//areaid为空表示为二级分公司登录，并根据com_id的值找到分公司的所有片区
			{   
				List<Area> secondlist = distributionBiz.serachAreaAddressor(com_id);
				Map<String,Object> mapTw = null;
				for(Area a : secondlist)//片区总数
				{
					mapTw = new HashMap<String, Object>();
					//mapTw.put("comid", " ");
					mapTw.put("id", a.getId());//片区id
					mapTw.put("name", a.getArea_name()+"片区");//名称
					mapTw.put("managerName", a.getArea_name()+"经理");
					//再根据片区地址去secondorder中去找每一个片区的总数，或者是根据sql（hql）查询
					int totalcount = distributionBiz.findCountByArea_name(a.getArea_name()+"片区");//再根据子节点的名称去查每一个子节点的数量累加
					mapTw.put("totalcount", totalcount);//总数
					listTw.add(mapTw);
				}
			}
			if(com_id == null || listTw==null)//片区往下发放总数
			{
				List<Position> secondlist = distributionBiz.serachPostionAddressor(areaid);
				for(Position p : secondlist)
				{
					Map<String,Object> mapTw = new HashMap<String, Object>();
					//mapTw.put("pid", areaid);
					//mapTw.put("id", p.getAreaid());
					mapTw.put("id", p.getPosId());//站点id
					mapTw.put("name", p.getPosName());//名称
					mapTw.put("managerName", str);
					int totalcount = distributionBiz.findCountByZhandian_name(p.getPosName());//根据站点名称去查每一个站点的总数（这个总数是片区发到某一个加油站的总数）
					mapTw.put("totalcount", totalcount);//总数
					listTw.add(mapTw);
				}
			}
			super.outPrintJsonByArray(listTw);
		}	
	}
	
	
}
//分发记录
public void record()
{
	/**
	 * 1.根据id去获取当前账号的信息，展示分发的内容（二级公司和片区的分发记录）
	 * (1)判断id（二级公司和片区）
	 * (2)是1或者5则是二级分公司的id
	 * (3)片区的id
	 * 
	 * 
	 * 
	 * 
	 * 分发接口：http://192.168.0.104:8080/Ltmcp/mobile/distribution_anomalyAction?condition.comId=5&pageBean.curentPage=
	 * 2.根据condition中的有一个：
	 * 
	 * 
	 */
//	System.out.println("分发记录的comId(二级公司):"+condition.getComId());
//	System.out.println("分发记录的comId(片区的id):"+condition.getAreaid());
	String str = condition.getPerName();//二级公司科长名称或者经理名称
	String address = condition.getStaName();//发货到地点名称（实际是address，就这样搞吧，很烦）
	//System.out.println(str+":111");
	try 
	{
		address = new String(address.getBytes("UTF-8"),"utf-8");
		str = new String(str.getBytes("UTF-8"),"utf-8");
	} catch (UnsupportedEncodingException e1) 
	{
		e1.printStackTrace();
	}
	//System.out.println("分发人姓名："+str);
	String rename = str;
	String addre = address;
	String addffe = addre.substring(2, 4);
	//时间的其他条件都不计入在内的情况，直接去查询
	//condition.setPerName(str);
	//condition.setStaName(address);
	String st_one = "xxxx科长";
	String st_ga = "科长";
	String st_pzh = "王科长";
	try {
		excRecordActionAp.setCondition(condition);//条件只有一个comid或者 areaid
		excRecordActionAp.setPageBean(pageBean);
		//boolean  a= st_one.equals(rename);
		//System.out.println(a);
	} catch (Exception e) {
		//e.printStackTrace();
	}

	if("".equals(rename))
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		super.outPrintJsonByArray(list);//注意测试账号名称：xxxx科长，广安的：科长
	}//else if(st_one.equals(rename) || st_ga.equals(rename) || st_pzh.equals(rename))//攀枝花，广安，测试（三大区的账号id），查询secondorder表
	 else if("片区".endsWith(addffe))	
	{
		System.out.println("进入方法。分发查询（二级公司）");
		try 
		{
			excRecordActionAp.recordDis();//进入分发记录方法
		} catch (Exception e) 
		{
			
		}	
		
	}else//否则就是片区账号登录查询的分发记录，登录为前提条件，查询的是position_inventory
	{
		System.out.println("进入方法。分发查询（片区）");
		try 
		{
			excRecordActionAp.recordDis_pianqu();//进入分发记录方法
		} catch (Exception e) 
		{
			
		}
		//1.根据片区的姓名找到片区的id
		//发货人 ,发货地址，发往站点的名称，数量
		//2.根据片区的id去查询position_inventory
		
		
		
	}
	
}
//库存记录


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
