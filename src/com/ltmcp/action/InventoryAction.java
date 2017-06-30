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
	private String login_account;//直接获取前端传递参数：登录账号名称，判断地区。方便于查询
	private Integer comid;//把comid表示所属地：攀枝花还是广安，还是测试账号：在登陆的sesson中去获取这个comid
	private AdminService adminService;//登陆admin
	private InventoryService inventoryService;//要get，set，否则配置在action.xml中出错
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
	 *统计库存：二级分公司
	 * @param
	 */
	public void queryTheCompany()
	{
		/**
		 * 1.片区总数相加即为使用封签数目
		 *    根据comid查询各片区名称  
		 *    根据comid的值再去shoufa_person中查询对应的人（例如：xxx科长），这里 注意在该表中只有com_id只有3个有效值1（王科长）,5（科长）,3（xxxx科长） 
		 *    根据各片区名称查询数量总计（片区总数）secondorder
		 *    
		 * 2.厂家发放的总数为first_order总数
		 * 
		 */
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//还必须使用httpSession的类型才可以获取ServletActionContext.getRequest().getSession()
		List<shoufa_person> lsp = null;
		List<Area> secondlist = null;
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			//System.out.println("打印获取comid:"+admin.getCompany().getComId()+"；打印获取登录帐户名："+admin.getAdmName());
			lsp = inventoryService.findCompanyPersonNameByComid(comid);
			String companyAddress = null;
			for(shoufa_person f : lsp)
			{
				companyAddress = f.getAddress();
				//System.out.println("打印二级公司营销科科长姓名:"+f.getName());
			}
			//逻辑与app统计总数相同公司id，查询片区，再查总数，list，map放入
			secondlist = inventoryService.serachAreaAddress(comid);//根据公司id去查询各个片区
			listTw = new ArrayList<Map<String,Object>>();
			int counts = 0;
			mapTw = new HashMap<String, Object>();
			for(Area a : secondlist)//片区总数totalcount
			{
				int totalAreaGainByCompany = inventoryService.countByArea_name(a.getArea_name()+"片区");//总数是采用直接查询结果之后count1+count2返回此totalAreaGainByCompany
				//这里要根据片区的id去表：position_inventory表中去查询各个片区的下发到站点的总数，注意：狗血的金江油库没有进行分发操作，直接从二级分公司处获取并使用
				//System.out.println("打印totalcount："+totalAreaGainByCompany);
				//mapTw.put("totalAreaGainByCompany", totalAreaGainByCompany);//片区从二级分公司处收到总数
				counts = totalAreaGainByCompany+counts;
			}
			//System.out.println("打印二级分公司下发片区总数："+counts);
			mapTw.put("alreadyDistributed", counts);//营销科发往片区已经发放总数，      》使用数
			
			//在去first_order表中获取分公司总数：条件就是comid
			List<first_order> folist = inventoryService.companyContByFirstOrder(comid);
			int countsCompany = 0;
			for(first_order fo : folist)
			{
				countsCompany = fo.getFahuo_number() + countsCompany;
			}
			mapTw.put("countsCompany", countsCompany);//                      》总数
			mapTw.put("companyInventory", countsCompany-counts);//二级分公司库存    》剩下
			mapTw.put("companyAddress", companyAddress);//                    》地址
			mapTw.put("time", new Timestamp(System.currentTimeMillis()));//   》时间
			listTw.add(mapTw);
			super.outPrintJsonByArray(listTw);
		}catch (Exception e)//所有捕获异常的情况都将面对广安的情况
		{
				System.out.println("获取admin出错或者查询数据出错或者统计二级分公司营销科总计出错");
				super.getPringWriter().print("获取数据出错");
		}
		
			
	}
	
	
	/***
	 * 统计库存：片区
	 * @param
	 */
	public void queryTheInventory()//统计片区
	{
		/**
		 * 1.查询各片区总数，根据条件是登录时候获取是攀枝花，还是广安等其他地区的账号登录
		 *      根据comid查询各片区名称   完成
		 *      根据comid的值再去shoufa_person中查询对应的人（例如：xxx科长），这里 注意在该表中只有com_id只有3个有效值1（王科长）,5（科长）,3（xxxx科长） 完成
		 *      根据各片区名称查询数量总计（片区总数）secondorder 完成
		 *      
		 * 2.片区总数：二级分公司下发到该片区有记录的总数  
		 * 		根据各片区名称查询数量总计（片区总数）secondorder 完成
		 *      
		 *      
		 * 
		 * 
		 * 3.片区已发总数：查询出该片区下发到该片区下管理的站点总数
		 * 	          根据片区id查询下发站点数据，并注意金江油库数据     完成
		 *      汇总查询类似统计secondorder中的查询方式 去查询position_incentory表     完成         
		 *      position_incentory表中统计：片区发放各自所属站点数量累加，统计的是袋子和箱子2种情况下的总和数量封签数目   完成
		 * 
		 * 
		 *  4. 片区库存=片区总数-片区已发总数     完成
		 *     片区库存分析：         完成
		 *     四种情况：
		 *     正常情况库存大于0  完成
		 *     正常情况库存大于0，但是未必数据正确情况，这个是最大的可能性，厂家生产数据本身误差导致，人为因素  完成          
		 *     等于0极大可能性是金江油库  处理中··· 这里最好包含片区名下的所有站点
		 *     小于0的情况是：从二级分公司处领取的时候不经过分发app，片区直接领取，并在下发站点的时候使用分发app
		 */
		//System.out.println("进入查询片区-------");
		Timestamp ts=new Timestamp(System.currentTimeMillis());//时间格式化
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//还必须使用httpSession的类型才可以获取ServletActionContext.getRequest().getSession()
		List<shoufa_person> lsp = null;
		List<Area> secondlist = null;
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			//System.out.println("打印获取comid:"+admin.getCompany().getComId()+"；打印获取登录帐户名："+admin.getAdmName());
			lsp = inventoryService.findCompanyPersonNameByComid(comid);
			for(shoufa_person f : lsp)
			{
				//System.out.println("打印二级公司营销科科长姓名:"+f.getName());
			}
			//逻辑与app统计总数相同公司id，查询片区，再查总数，list，map放入
			secondlist = inventoryService.serachAreaAddress(comid);//根据公司id去查询各个片区
			listTw = new ArrayList<Map<String,Object>>();
			for(Area a : secondlist)//片区总数totalcount
			{
				mapTw = new HashMap<String, Object>();
				mapTw.put("areaId", a.getId());//片区id
				mapTw.put("name", a.getArea_name()+"片区");//名称
				mapTw.put("managerName", a.getArea_name()+"经理");//领取人：例如：仁和经理，本来不应该是这样的，姓名最好
				int totalAreaGainByCompany = inventoryService.countByArea_name(a.getArea_name()+"片区");//总数是采用直接查询结果之后count1+count2返回此totalAreaGainByCompany
				//这里要根据片区的id去表：position_inventory表中去查询各个片区的下发到站点的总数，注意：狗血的金江油库没有进行分发操作，直接从二级分公司处获取并使用
				//System.out.println("打印totalcount："+totalAreaGainByCompany);
				//jinjiangYoukuCount = totalAreaGainByCompany;
				mapTw.put("totalAreaGainByCompany", totalAreaGainByCompany);//片区从二级分公司处收到总数
				int totalSiteGainByArea = inventoryService.countByArea_id(a.getId());//片区下发各自管理站点总数
				
				int areaInventory = totalAreaGainByCompany-totalSiteGainByArea;
				//System.out.println("打印差值:"+areaInventory);
				if(areaInventory == 0 || areaInventory < 0)//二种情况，等于0表示片区经理处发完封签
				{                                          //小于0的情况是：从二级分公司处领取的时候不经过分发app，片区直接领取，并在下发站点的时候使用分发app,负数
					                                       //这种情况处理为0，片区经理得到的总数和下发的总数一样多，框死为差值为0 的情况
					mapTw.put("totalSiteGainByArea", totalAreaGainByCompany);//设置下发管理站点总数为从从二级分公司处收到总数，迫不得已设置
					mapTw.put("areaInventory", 0);
					mapTw.put("time", new Timestamp(System.currentTimeMillis()));
					
				}else if(areaInventory == totalAreaGainByCompany)//这里才是狗血金江库存
				{	
					/**
					 * 站点：金江油库（其余站点也应该差不多）
					 * 第一步：金江油库使用总数：查询金江油库使用封签情况：
					 * 第二步：
					 *       1.只施封然而并未解封被其他加油站或者金江油库的人自己不扫解封码减掉封签数量（永远运输中）【完成】    +  封签周期正常结束 【完成】  +  异常数目3（异常只能统计哪些异常：通过追溯（条件却是：二维码）一个功能找到没有施封的结构中，没有注册，自解自封情况又该如何处理）。并且加上时间限制
					 *       2.下面3种异常只能统计异常数目：
					 *       
					 *       异常数目（1）：施封码无效，可以统计
					 *       异常数目（2）：外码或者内码损坏，无法统计
					 *       异常数目（3）：（增加数据库一张表）：统计一种极为有效并常见的统计数目（解封同时发现未施封）：在写入异常数据的时候，同时去找寻能查到的异常（解封未施封，其余情况都不管），采用追溯功能找到相关的数据，写入一张表中   
					 *       
					 * 第二步：金江油库库存：二级分公司分发的总数（至金江油库）-金江油库使用总数（封签周期正常结束+异常数目（异常数目））
					 */
					int areaid = a.getId();//3就是金江片区
					//第二步（3）小点的异常数目
					List<Map<String, Object>> unlockNotsealed = inventoryService.unlockNotsealed(areaid);
					int unlockNotsealeds = 0;
					for (Map<String, Object> m : unlockNotsealed)  
				    {  
				      for (String k : m.keySet())  
				      {  
				    	  unlockNotsealeds = (Integer) m.get(k);
				    	 // System.out.println("jj打印站点jf-(封签未施封)数量:"+k + " : " + m.get(k));//获取键和值  
				      }  
				    }  
					
					//第二步（1）小点的异常数目
					List<Map<String, Object>> sealNotRegistered = inventoryService.sealNotRegistered(areaid);
					int sealNotRegistereds = 0;
					for (Map<String, Object> m : sealNotRegistered)  
				    {  
				      for (String k : m.keySet())  
				      {  
				        sealNotRegistereds = (Integer) m.get(k);
				        //System.out.println("jj打印站点sf-(二维码未注册)数量:"+k + " : " + m.get(k));//获取键和值  
				      }  
				    }  
					//第二步1小点中的（永远运输中）【完成】    +  封签周期正常结束 【完成】
					List<Map<String, Object>> periodNormallyEndsNumber = inventoryService.periodNormallyEndsNumber(areaid);//（永远运输中）    +  封签周期正常结束，一定精确到站点，参数为片区id	，返回值是list各站点统计的正常总数
					for (Map<String, Object> m : periodNormallyEndsNumber)  
				    {  
				      for (String k : m.keySet())  
				      {  
				       // System.out.println("jj2016-12-10之后>>>打印站点正常使用施封数目:"+k + " : " + m.get(k));  
				        mapTw.put("totalSiteGainByArea", (Integer)m.get(k)+sealNotRegistereds+unlockNotsealeds);//千万注意这里是：并不是片区下发总数，而是金江油库正常使用的总数
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
		} catch (Exception e)//所有捕获异常的情况都将面对广安的情况
		{
			
			super.getPringWriter().print("获取数据出错");
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
	 * 库存统计：站点
	 * 携带参数片区id传入此方法中
	 */
	public void queryThePositionInventory()
	{
		/**
		 * 站点库存=第一步-第二步：
		 * 第一步：站点通过片区id进入查询每一个片区下站点领取总数   完成
		 *      1.片区id传入到数据操作
		 *      2.查出该片区下的站点posid和posname
		 *      3.根据posid和posname再去查到行数：select count(er.id) from position_inventory er where er.pianqu_id=1 and er.position_name='江南龙井站'
		 *      4.在遍历这些行：因为有的行中有袋装和箱装的情况50和500的情况
		 *      5.累加
		 *      6.得出结果
		 *      
		 * 第二步： 这个在施封表中只要出现即可计数：只施封然而并未解封被其他加油站或者金江油库的人自己不扫解封码减掉封签数量（永远运输中）【完成】    +  封签周期正常结束 【完成】 
		 *       1.只施封然而并未解封被其他加油站或者金江油库的人自己不扫解封码减掉封签数量（永远运输中）【完成】    +  封签周期正常结束 【完成】  +  异常数目3（异常只能统计哪些异常：通过追溯（条件却是：二维码）一个功能找到没有施封的结构中，没有注册，自解自封情况又该如何处理）。并且加上时间限制
		 *       2.下面3种异常只能统计异常数目：
		 *       
		 *       异常数目（1）：施封码无效，可以统计
		 *       异常数目（2）：外码或者内码损坏，无法统计
		 *       异常数目（3）：（增加数据库一张表）：统计一种极为有效并常见的统计数目（解封同时发现未施封）：在写入异常数据的时候，同时去找寻能查到的异常（解封未施封，其余情况都不管），采用追溯功能找到相关的数据，写入一张表中   
		 *       
		 * 第二步：金江油库库存：二级分公司分发的总数（至金江油库）-金江油库使用总数（封签周期正常结束+异常数目（异常数目））
		 */
		
		
		
		
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		
		
		List<Map<String, Object>> listPositon = null;//serachTotalPositionByArea(areaid)这个方法使用的
		try
		{
			//解封未施封
			List<Map<String, Object>> unlockNotsealed = inventoryService.unlockNotsealed(areaid);
			int unlockNotsealeds = 0;
			for (Map<String, Object> m : unlockNotsealed)  
		    {  
		      for (String k : m.keySet())  
		      {  
		    	  unlockNotsealeds = (Integer) m.get(k);
		    	  //System.out.println("2016-12-10之后>>>打印站点jf-(封签未施封)追溯到站点数量:"+k + " : " + m.get(k));//获取键和值  
		      }  
		    }  
			//正常消耗
			List<Map<String, Object>> periodNormallyEndsNumber = inventoryService.periodNormallyEndsNumber(areaid);
			
//			for (Map<String, Object> m : periodNormallyEndsNumber)  
//		    {  
//		      for (String k : m.keySet())  
//		      {  
//		       // System.out.println("2016-12-10之后>>>打印正常消耗数目:"+k + " : " + m.get(k));  
//		       
//		      }  
//		    }  
			//施封码无效
			List<Map<String, Object>> sealNotRegistered = inventoryService.sealNotRegistered(areaid);
//			for (Map<String, Object> m : sealNotRegistered)  
//		    {  
//		      for (String k : m.keySet())  
//		      {  
//		    	  //System.out.println("2016-12-10之后>>>打印施封码无效总数目:"+k + " : " + m.get(k));  //System.out.println("打印站点sf-(二维码未注册)数量:"+k + " : " + m.get(k));//获取键和值  
//		      }  
//		    }  
			//站点总数
			listPositon = inventoryService.serachTotalPositionByArea(areaid);	
			int alreadyInUsedPosition = 0;
			listTw = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> m : listPositon)  
		    { 
				mapTw = new HashMap<String, Object>();
		      for (String k : m.keySet())  
		      {  
		       // System.out.println("总数目<<<2016-12-10之后>>>打印站点总数目:"+k + " : " + m.get(k));
		        int mk = Integer.parseInt(String.valueOf(m.get(k)));
		        String jinjiangName = k;
		        if( "金江油库".equals(jinjiangName) && mk == 0)
		        {
		        	//根据片区id查询片区名称
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
			        //System.out.println("2016-12-10之后>>>打印正常消耗数目:"+kNormally + " : " + mNormally.get(kNormally)); 
			        if(k == kNormally)
			        {
			        	//int i = Integer.parseInt(String.valueOf(m.get(k)))-Integer.parseInt(String.valueOf(mNormally.get(kNormally)));
			        	//alreadyInUsedPosition = Integer.parseInt(String.valueOf(mNormally.get(kNormally)));
			        	
			        	for (Map<String, Object> msealNot : sealNotRegistered)  
					    { 
			        	   
					      for (String ksealNot : msealNot.keySet())  
					      {
					    	  //System.out.println("2016-12-10之后>>>打印施封码无效总数目:"+ksealNot + " : " + msealNot.get(ksealNot));
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
					  		    		  
					  		    		  //System.out.println("20161210之后>>>打印站点库存名称:"+k + " , 库存:" + positionInventory);//获取键和值  
					  		    		  mapTw.put("positionName", k);                                          //站点名称
					  		    		  mapTw.put("positionCount", mk);                                        //站点封签总数
					  		    		  mapTw.put("alreadyInUsedPositionCount", alreadyInUsedPosition);        //站点封签使用总数
					  		    		  //mapTw.put("positionInventory", positionInventory);                   
					  		    		  mapTw.put("time", new Timestamp(System.currentTimeMillis()));          //时间
					  		    		  if(positionInventory == 0 || positionInventory<0)
					  		    		  {
					  		    			mapTw.put("positionInventory", "未使用分发功能，无法统计该站点库存");//差值小于0情况
					  		    		  }else
					  		    		  {
					  		    			mapTw.put("positionInventory", positionInventory);  //站点库存
					  		    		  }
					  		    	  }
					  		    	  
					  		    	  
					  		      }  
					  		    }  
					    		  
					    		  
					    	  }
					    	   //System.out.println("打印站点sf-(二维码未注册)数量:"+k + " : " + m.get(k));//获取键和值  
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
			super.getPringWriter().print("获取数据错误");
		}
	}
	
	/**
	 * 排序
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
	 * 暂时统计一个功能：解封未施封所有站点排行
	 * （纸上需求：所有施封成功与失败数据，解封成功与失败数据的统计总数，加上分析哪些站点经常出现不成功的施封（2个点：产品问题不成功和未施封），这里只统计未施封的情况排序）
	 * inventory_statisticalUnlockNotSealedRow
	 */
	public void statisticalUnlockNotSealedRow()
	{
		Admin admin = null;
		HttpSession session = ServletActionContext.getRequest().getSession();//还必须使用httpSession的类型才可以获取ServletActionContext.getRequest().getSession()
		Map<String,Object> mapTw = null;
		List<Map<String,Object>> listTw = null;
		List<Area> lareas = null;
		try 
		{
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			comid = admin.getCompany().getComId();
			/**
			 * 1.根据公司id查询所有该公司下所有片区id
			 * 
			 * 2.在根据片区id去查询各个片区下所有的站点
			 * 
			 * 3.根据站点id查询解封未施封数据统计
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
			    	  System.out.println("打印站点jf-(封签未施封)数量:"+k + " : " + m.get(k));//获取键和值
			    	  
			    	  mapTw.put("positionName", k);                                    //站点名称
			    	  mapTw.put("counts", m.get(k));                                   //该站点未施封总数
			    	  mapTw.put("time", new Timestamp(System.currentTimeMillis()));    //时间
			    	  listTw.add(mapTw);
			      }  
			    }  
			}
			sort(listTw, "counts", 0, listTw.size()-1);//参数设置：需要排序的List<Map<String, Object>>，map中排序的字段名称，开始排序点，结束排序点
			super.outPrintJsonByArray(listTw);
		}catch (Exception e)//所有捕获异常的情况都将面对广安的情况
		{
				//e.printStackTrace();
				System.out.println("获取admin出错或者查询数据出错");
				super.getPringWriter().print("获取数据出错");
		}
		
			
	
		
	}
	/***
	 * 修改订单（数量），web端点击保存之后传递数据后台此方法修改订单
	 * @param
	 */
	//public void orderModify(){}
	/***
	 * web端展示订单（添加订单之后展示，没有做分页处理）
	 * @param
	 */
	//public void list(){}
}
