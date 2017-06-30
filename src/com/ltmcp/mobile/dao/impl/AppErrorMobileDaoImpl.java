package com.ltmcp.mobile.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Errors;
import com.ltmcp.entity.FoundSiteUnlockNotSeal;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.mobile.action.DistributionAction;
import com.ltmcp.mobile.dao.AppErrorMobileDao;
import com.ltmcp.test.SendDuanxin;

public class AppErrorMobileDaoImpl extends BaseDaoHibImpl implements AppErrorMobileDao {

	/*
	 * 普通异常记录
	 * (non-Javadoc)	
	 * @see com.ltmcp.mobile.dao.AppErrorMobileDao#insert(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void insert_nor(String code,String returnNum,String username,String posid,String appNum) {
		Errors e = new Errors();
		e.setCode(code);
		e.setReturnNum(returnNum);
		e.setAppNum(appNum);
		e.setPosid(posid);
		e.setUsername(username);
		e.setTime(new Timestamp(System.currentTimeMillis()));
		super.saveOrUpdate(e);
		
	}
	
	/*
	 * 非法异常记录
	 * (non-Javadoc)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert_illegality(String code,String returnNum,String username,String posid) {
		
		String hql = "from Position p where p.posId = "+Integer.parseInt(posid);
		List poss = new ArrayList();
		poss.add(posid);
		List<Position> plist = null;
		plist = (List<Position>) this.queryHQL(hql, poss);
		
		
		NewErrors e = new NewErrors();
		FoundSiteUnlockNotSeal foundSiteUnlockNotSeal = new FoundSiteUnlockNotSeal();//下面的231行同时打开
		e.setCode(code);
		e.setDoName(username);
		e.setStatus("1");
		NewErrors ne = new NewErrors();
		
		
		for (Position p :plist) {
			e.setPosid(p.getPosId());
			e.setPosiName(p.getPosName());
			e.setComid(p.getCompany().getComId());
		}
		
		e.setRe(returnNum);
		e.setTime(new Timestamp(System.currentTimeMillis()));//so.setFhtime(new Timestamp(System.currentTimeMillis()));//时间
		//super.saveOrUpdate(e);
		//保存到new_errors中了
		//增加调用短信接口发送短信的方法
		//System.out.println("打印异常类型："+returnNum);
		if(returnNum.equals("jf-(封签未施封)"))//当发现解封未施封的情况之后，立即开启这个发送短信功能
		{
			//短信内容，时间，地点，人物
			Timestamp tim = e.getTime();//异常发生时间
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
			String tim_sub = "";
			tim_sub = sdf.format(tim);
		    String sit = e.getPosiName();//地点要，人物要不要是个问题.		    
		    
		    //判断是攀枝花还是广安
		    //System.out.println("打印公司id准备发送解封未施封的短信:"+e.getComid());
		    String messageOne = null;
		    String phone = null;
		    SendDuanxin sendDuanxin =  null;
		    
		    
		    //根据一个条件：returnNum.equals("jf-(封签未施封)")之中去添加进数据库表：foundSiteUnlockNotSealr
		    //20170525根据这个一次请求将要执行一个操作，把使用分发功能以后的jf-(封签未施封)数据全部通过查询遍历写入追溯表中
		    
		    
//		    List<NewErrors> neorlist = null;
//		    try
//		    {
//		    	StringBuilder sbcesi = new StringBuilder(" from NewErrors ne where ne.time>'2016-12-10' and ne.re='jf-(封签未施封)'");
//		    	neorlist = super.findList(sbcesi.toString());
//		    	System.out.println("成功，马上执行最终极的方法");
//		    	
//				
//			} catch (Exception e2)
//			{
//				e2.printStackTrace();
//			}
//		    int io=0;
//		    for(NewErrors eceshi: neorlist)
//		    {
//		    	io++;
//		    	FoundSiteUnlockNotSeal foundSiteUnlockNotSeal = new FoundSiteUnlockNotSeal();
//		    	System.out.println("执行总次数："+io);
//		    	foundSiteUnlockNotSeal.setFsun_unfreeze_code(eceshi.getCode());// 1
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_account(eceshi.getDoName());//发现解封未施封的账号 2
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_person(eceshi.getDoName());//解封人姓名 3
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_posname(eceshi.getPosiName());//解封未施封出现（发现）的地方 4
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_posid(eceshi.getPosid());//解封地posid 5
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_time(eceshi.getTime());//解封地点发现未施封的时间，限制在当前一刻 6
//			    
//			    
//			    StringBuilder json = new StringBuilder(); //采用追溯功能查询该施封未解封的源头来源 
//		        try 
//		        {  
//		            URL urlObject = new URL("http://192.168.0.122:8080/Ltmcp/mobile/distribution_traceAction?&code="+eceshi.getCode());  //注意上线之前一定修改此连接加上http：http://www.56tr.cn/mobile/distribution_traceAction?&code=
//		            URLConnection uc = urlObject.openConnection();  
//		            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  //还要注意转码
//		            String inputLine = null;  
//		            while ( (inputLine = in.readLine()) != null) 
//		            {  
//		                json.append(inputLine); 
//		            }  
//		            in.close();  
//		        } catch (MalformedURLException e1)
//		        {  
//		            e1.printStackTrace();  
//		        } catch (IOException e2) 
//		        {  
//		            e2.printStackTrace();  
//		        } 
//		        
//		        try 
//		        {
//		        	json.toString();
//			        
//			        /**
//			         * System.out.println("打印解封未施封追溯站点:"+json.toString());
//			         * 将上面通过追溯功能获取到的数据通过java解析这个json.toString();
//			         * 根据已知条件：追溯的路径只能到站点结束任何其他的违规操作行为导致数量误差都将无效，其本质库存管理：？？？？
//			         * [{"returnCode":"0iSETVgcvq",
//			         * "companyAddress":"测试二级公司地址",
//			         * "status":"0",
//			         * "findbagCode":"bgtrewqq",
//			         * "unfeezeAddress":null,
//			         * "findboxCode":"xcccc1112",
//			         * "areaTime":"2017-05-23 09:42:20.0",
//			         * "unfreezePerson":null,
//			         * "unfreeze_time":null,
//			         * "freezePerson":null,
//			         * "address":"镇龙A站",
//			         * "areaAddress":"市中片区",
//			         * "freezeAddress":null,
//			         * "companySendTime":"2017-05-23 09:41:19.0",
//			         * "freeze_time":null,
//			         * "success":"-2066"}]
//			         */
//			        
//			        JSONArray jsonArr = JSONArray.fromObject(json.toString());
//			        
//			        String returnCode[] = new String[jsonArr.size()];
//			        String companyAddress[] = new String[jsonArr.size()];
//			        String status[] = new String[jsonArr.size()];
//			        String findbagCode[] = new String[jsonArr.size()];
//			        String unfeezeAddress[] = new String[jsonArr.size()];
//			        String findboxCode[] = new String[jsonArr.size()];
//			        
//			        String areaTime[] = new String[jsonArr.size()];
//			        String unfreezePerson[] = new String[jsonArr.size()];
//			        String unfreeze_time[] = new String[jsonArr.size()];
//			        String freezePerson[] = new String[jsonArr.size()];
//			        String address[] = new String[jsonArr.size()];
//			        String areaAddress[] = new String[jsonArr.size()];
//			        
//			        String freezeAddress[] = new String[jsonArr.size()];
//			        String companySendTime[] = new String[jsonArr.size()];
//			        String freeze_time[] = new String[jsonArr.size()];
//			        String success[] = new String[jsonArr.size()];
//			        for (int i = 0; i < jsonArr.size(); i++) 
//			        {
//			        	returnCode[i] = jsonArr.getJSONObject(i).getString("returnCode");
//			        	companyAddress[i] = jsonArr.getJSONObject(i).getString("companyAddress");//公司地址
//			        	status[i] = jsonArr.getJSONObject(i).getString("status");
//			        	findbagCode[i] = jsonArr.getJSONObject(i).getString("findbagCode");
//			        	unfeezeAddress[i] = jsonArr.getJSONObject(i).getString("unfeezeAddress");
//			        	findboxCode[i] = jsonArr.getJSONObject(i).getString("findboxCode");
//			        	
//			        	areaTime[i] = jsonArr.getJSONObject(i).getString("areaTime");//片区发站点时间
//			        	unfreezePerson[i] = jsonArr.getJSONObject(i).getString("unfreezePerson");
//			        	unfreeze_time[i] = jsonArr.getJSONObject(i).getString("unfreeze_time");
//			        	freezePerson[i] = jsonArr.getJSONObject(i).getString("freezePerson");
//			        	address[i] = jsonArr.getJSONObject(i).getString("address");//站点
		                //根据站点去找posid，在根据podid去找人（使用账号，使用人）
		    			//String hql1 = "from Position p where p.posName = "+address[i];
		    
//20170605		    			List poss1 = new ArrayList();
//		    			List<Position> plist1 = null;
//		    			//plist1 = (List<Position>) this.queryHQL(hql1, poss1);
//		    			for (Position p :plist1)
//		    			{
//		    				p.getPosId();
//20170605		    			}
		    			
//			        	areaAddress[i] = jsonArr.getJSONObject(i).getString("areaAddress");//片区地点
//			        	
//			        	freezeAddress[i] = jsonArr.getJSONObject(i).getString("freezeAddress");
//			        	companySendTime[i] = jsonArr.getJSONObject(i).getString("companySendTime");//二级公司发放片区时间
//			        	freeze_time[i] = jsonArr.getJSONObject(i).getString("freeze_time");
//			        	success[i] = jsonArr.getJSONObject(i).getString("success");	//状态
//			        	
//			        	System.out.println("追溯路径：公司地址："+companyAddress[i]+"\n公司发片区时间："+companySendTime[i]+"\n片区地址："+
//			        			           areaAddress[i]+"\n片区发站点时间："+areaTime[i]+"\n追溯的最终成功查到未施封站点："+address[i]+"\n返回状态："+success[i]);
//			        	foundSiteUnlockNotSeal.setFsun_sela_posname(address[i]);// 7
//			        	//foundSiteUnlockNotSeal.setFsun_seal_posid(999);// 8
//			        	foundSiteUnlockNotSeal.setFsun_seal_earaname(areaAddress[i]);//9
//			        	//foundSiteUnlockNotSeal.setFsun_seal_areaid(888)// 10
//			        	foundSiteUnlockNotSeal.setFsun_company_name(companyAddress[i]); //11
//			        	//foundSiteUnlockNotSeal.setFsun_company_comid(777); //12
//		             }
//			         super.saveOrUpdate(foundSiteUnlockNotSeal);
//				} catch (Exception e2) 
//				{
//					
//					System.out.println("追溯出错或者插入数据库出错");
//					e2.printStackTrace();
//				}
//		       
//		    }
		    
		    
		    foundSiteUnlockNotSeal.setFsun_unfreeze_code(code);// 1
		    foundSiteUnlockNotSeal.setFsun_unfreeze_account(e.getDoName());//发现解封未施封的账号 2
		    foundSiteUnlockNotSeal.setFsun_unfreeze_person(e.getDoName());//解封人姓名 3
		    foundSiteUnlockNotSeal.setFsun_unfreeze_posname(e.getPosiName());//解封未施封出现（发现）的地方 4
		    foundSiteUnlockNotSeal.setFsun_unfreeze_posid(e.getPosid());//解封地posid 5
		    foundSiteUnlockNotSeal.setFsun_unfreeze_time(e.getTime());//解封地点发现未施封的时间，限制在当前一刻 6
		    
		    
		    StringBuilder json = new StringBuilder(); //采用追溯功能查询该施封未解封的源头来源 
	        try 
	        {  
	            URL urlObject = new URL("http://192.168.0.122:8080/Ltmcp/mobile/distribution_traceAction?&code="+code);  //注意上线之前一定修改此连接加上http：http://www.56tr.cn/mobile/distribution_traceAction?&code=
	            URLConnection uc = urlObject.openConnection();  
	            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  //还要注意转码
	            String inputLine = null;  
	            while ( (inputLine = in.readLine()) != null) 
	            {  
	                json.append(inputLine); 
	            }  
	            in.close();  
	        } catch (MalformedURLException e1)
	        {  
	            e1.printStackTrace();  
	        } catch (IOException e2) 
	        {  
	            e2.printStackTrace();  
	        } 
	        
	        try 
	        {
	        	json.toString();
		        
		        /**
		         * System.out.println("打印解封未施封追溯站点:"+json.toString());
		         * 将上面通过追溯功能获取到的数据通过java解析这个json.toString();
		         * 根据已知条件：追溯的路径只能到站点结束任何其他的违规操作行为导致数量误差都将无效，其本质库存管理：？？？？
		         * [{"returnCode":"0iSETVgcvq",
		         * "companyAddress":"测试二级公司地址",
		         * "status":"0",
		         * "findbagCode":"bgtrewqq",
		         * "unfeezeAddress":null,
		         * "findboxCode":"xcccc1112",
		         * "areaTime":"2017-05-23 09:42:20.0",
		         * "unfreezePerson":null,
		         * "unfreeze_time":null,
		         * "freezePerson":null,
		         * "address":"镇龙A站",
		         * "areaAddress":"市中片区",
		         * "freezeAddress":null,
		         * "companySendTime":"2017-05-23 09:41:19.0",
		         * "freeze_time":null,
		         * "success":"-2066"}]
		         */
		        
		        JSONArray jsonArr = JSONArray.fromObject(json.toString());
		        
		        String returnCode[] = new String[jsonArr.size()];
		        String companyAddress[] = new String[jsonArr.size()];
		        String status[] = new String[jsonArr.size()];
		        String findbagCode[] = new String[jsonArr.size()];
		        String unfeezeAddress[] = new String[jsonArr.size()];
		        String findboxCode[] = new String[jsonArr.size()];
		        
		        String areaTime[] = new String[jsonArr.size()];
		        String unfreezePerson[] = new String[jsonArr.size()];
		        String unfreeze_time[] = new String[jsonArr.size()];
		        String freezePerson[] = new String[jsonArr.size()];
		        String address[] = new String[jsonArr.size()];
		        String areaAddress[] = new String[jsonArr.size()];
		        
		        String freezeAddress[] = new String[jsonArr.size()];
		        String companySendTime[] = new String[jsonArr.size()];
		        String freeze_time[] = new String[jsonArr.size()];
		        String success[] = new String[jsonArr.size()];
		        for (int i = 0; i < jsonArr.size(); i++) 
		        {
		        	returnCode[i] = jsonArr.getJSONObject(i).getString("returnCode");
		        	companyAddress[i] = jsonArr.getJSONObject(i).getString("companyAddress");//公司地址
		        	status[i] = jsonArr.getJSONObject(i).getString("status");
		        	findbagCode[i] = jsonArr.getJSONObject(i).getString("findbagCode");
		        	unfeezeAddress[i] = jsonArr.getJSONObject(i).getString("unfeezeAddress");
		        	findboxCode[i] = jsonArr.getJSONObject(i).getString("findboxCode");
		        	
		        	areaTime[i] = jsonArr.getJSONObject(i).getString("areaTime");//片区发站点时间
		        	unfreezePerson[i] = jsonArr.getJSONObject(i).getString("unfreezePerson");
		        	unfreeze_time[i] = jsonArr.getJSONObject(i).getString("unfreeze_time");
		        	freezePerson[i] = jsonArr.getJSONObject(i).getString("freezePerson");
		        	address[i] = jsonArr.getJSONObject(i).getString("address");//站点
		        	areaAddress[i] = jsonArr.getJSONObject(i).getString("areaAddress");//片区地点
		        	
		        	freezeAddress[i] = jsonArr.getJSONObject(i).getString("freezeAddress");
		        	companySendTime[i] = jsonArr.getJSONObject(i).getString("companySendTime");//二级公司发放片区时间
		        	freeze_time[i] = jsonArr.getJSONObject(i).getString("freeze_time");
		        	success[i] = jsonArr.getJSONObject(i).getString("success");	//状态
		        	
		        	System.out.println("追溯路径：公司地址："+companyAddress[i]+"\n公司发片区时间："+companySendTime[i]+"\n片区地址："+
		        			           areaAddress[i]+"\n片区发站点时间："+areaTime[i]+"\n追溯的最终成功查到未施封站点："+address[i]+"\n返回状态："+success[i]);
		        	foundSiteUnlockNotSeal.setFsun_sela_posname(address[i]);// 7
		        	//foundSiteUnlockNotSeal.setFsun_seal_posid(999);// 8
		        	foundSiteUnlockNotSeal.setFsun_seal_earaname(areaAddress[i]);//9
		        	//foundSiteUnlockNotSeal.setFsun_seal_areaid(888)// 10
		        	foundSiteUnlockNotSeal.setFsun_company_name(companyAddress[i]); //11
		        	//foundSiteUnlockNotSeal.setFsun_company_comid(777); //12
	             }
		        super.saveOrUpdate(foundSiteUnlockNotSeal);
			} catch (Exception e2) 
			{
				
				System.out.println("追溯出错或者插入数据库出错");
				e2.printStackTrace();
			}
//		  
		    
		    
		    if(e.getComid() == 1)
		    {
		    	//打开sendDuanxin = new SendDuanxin();
		    	messageOne = "您好！有一条异常解封未施封信息，您可以登录app中异常记录查看。时间："+tim_sub.substring(0, 16)+",地点："+sit;
		    	
			    try 
			    {
			    	phone="18280076437";//切记：打开修改号码
					//打开sendDuanxin.send(phone,messageOne);
				} catch (Exception e1)
				{
				}
			  //打开e.setSend_phone(phone);
			  //打开 e.setSend_status(1);

		    }else if(e.getComid() == 5)
		    {
		    	    phone="15882279820";//切记：打开修改号码
		    	//打开sendDuanxin = new SendDuanxin();
		    	messageOne = "您好！有一条异常解封未施封信息，您可以登录app中异常记录查看。时间："+tim_sub.substring(0, 16)+",地点："+sit;
			    try 
			    {
					//打开sendDuanxin.send(phone,messageOne);
				} catch (Exception e1)
				{
				}
			  //打开e.setSend_phone(phone);
			  //打开e.setSend_status(1);
		    }else if(e.getComid() == 4)//测试用的下面内容
		    {
		    	phone="15882279820";//切记：打开修改号码
		    	//sendDuanxin = new SendDuanxin();
		    	messageOne = "您好！有一条异常解封未施封信息，您可以登录app中异常记录查看。时间："+tim_sub.substring(0, 16)+",地点："+sit;
			    try 
			    {
			    	//sendDuanxin.send(phone,messageOne);
				} catch (Exception e1)
				{
				}
			  // e.setSend_phone(phone);
			  // e.setSend_status(1);
		    }else
		    {
		    	
		    }
		  System.out.println(messageOne);
		}
		super.saveOrUpdate(e);
		
	}
	

	@Override
	public NewErrors queryNewErrors(String code) {
		//String hql = "from NewErrors  where code = '"+code +"'";
		String hql = "from NewErrors  where code = '"+code+"'";
		//System.out.println(hql);
		return (NewErrors) super.query(hql, null);
	}

	@Override
	public Position queryPosition(String posid) {
		String hql = "from Position p where p.posId = "+Integer.parseInt(posid);
		return (Position) super.query(hql, null);
	}
}
