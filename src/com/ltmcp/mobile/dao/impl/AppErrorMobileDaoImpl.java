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
	 * ��ͨ�쳣��¼
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
	 * �Ƿ��쳣��¼
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
		FoundSiteUnlockNotSeal foundSiteUnlockNotSeal = new FoundSiteUnlockNotSeal();//�����231��ͬʱ��
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
		e.setTime(new Timestamp(System.currentTimeMillis()));//so.setFhtime(new Timestamp(System.currentTimeMillis()));//ʱ��
		//super.saveOrUpdate(e);
		//���浽new_errors����
		//���ӵ��ö��Žӿڷ��Ͷ��ŵķ���
		//System.out.println("��ӡ�쳣���ͣ�"+returnNum);
		if(returnNum.equals("jf-(��ǩδʩ��)"))//�����ֽ��δʩ������֮����������������Ͷ��Ź���
		{
			//�������ݣ�ʱ�䣬�ص㣬����
			Timestamp tim = e.getTime();//�쳣����ʱ��
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
			String tim_sub = "";
			tim_sub = sdf.format(tim);
		    String sit = e.getPosiName();//�ص�Ҫ������Ҫ��Ҫ�Ǹ�����.		    
		    
		    //�ж�����֦�����ǹ㰲
		    //System.out.println("��ӡ��˾id׼�����ͽ��δʩ��Ķ���:"+e.getComid());
		    String messageOne = null;
		    String phone = null;
		    SendDuanxin sendDuanxin =  null;
		    
		    
		    //����һ��������returnNum.equals("jf-(��ǩδʩ��)")֮��ȥ��ӽ����ݿ��foundSiteUnlockNotSealr
		    //20170525�������һ������Ҫִ��һ����������ʹ�÷ַ������Ժ��jf-(��ǩδʩ��)����ȫ��ͨ����ѯ����д��׷�ݱ���
		    
		    
//		    List<NewErrors> neorlist = null;
//		    try
//		    {
//		    	StringBuilder sbcesi = new StringBuilder(" from NewErrors ne where ne.time>'2016-12-10' and ne.re='jf-(��ǩδʩ��)'");
//		    	neorlist = super.findList(sbcesi.toString());
//		    	System.out.println("�ɹ�������ִ�����ռ��ķ���");
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
//		    	System.out.println("ִ���ܴ�����"+io);
//		    	foundSiteUnlockNotSeal.setFsun_unfreeze_code(eceshi.getCode());// 1
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_account(eceshi.getDoName());//���ֽ��δʩ����˺� 2
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_person(eceshi.getDoName());//��������� 3
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_posname(eceshi.getPosiName());//���δʩ����֣����֣��ĵط� 4
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_posid(eceshi.getPosid());//����posid 5
//			    foundSiteUnlockNotSeal.setFsun_unfreeze_time(eceshi.getTime());//���ص㷢��δʩ���ʱ�䣬�����ڵ�ǰһ�� 6
//			    
//			    
//			    StringBuilder json = new StringBuilder(); //����׷�ݹ��ܲ�ѯ��ʩ��δ����Դͷ��Դ 
//		        try 
//		        {  
//		            URL urlObject = new URL("http://192.168.0.122:8080/Ltmcp/mobile/distribution_traceAction?&code="+eceshi.getCode());  //ע������֮ǰһ���޸Ĵ����Ӽ���http��http://www.56tr.cn/mobile/distribution_traceAction?&code=
//		            URLConnection uc = urlObject.openConnection();  
//		            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  //��Ҫע��ת��
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
//			         * System.out.println("��ӡ���δʩ��׷��վ��:"+json.toString());
//			         * ������ͨ��׷�ݹ��ܻ�ȡ��������ͨ��java�������json.toString();
//			         * ������֪������׷�ݵ�·��ֻ�ܵ�վ������κ�������Υ�������Ϊ��������������Ч���䱾�ʿ�������������
//			         * [{"returnCode":"0iSETVgcvq",
//			         * "companyAddress":"���Զ�����˾��ַ",
//			         * "status":"0",
//			         * "findbagCode":"bgtrewqq",
//			         * "unfeezeAddress":null,
//			         * "findboxCode":"xcccc1112",
//			         * "areaTime":"2017-05-23 09:42:20.0",
//			         * "unfreezePerson":null,
//			         * "unfreeze_time":null,
//			         * "freezePerson":null,
//			         * "address":"����Aվ",
//			         * "areaAddress":"����Ƭ��",
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
//			        	companyAddress[i] = jsonArr.getJSONObject(i).getString("companyAddress");//��˾��ַ
//			        	status[i] = jsonArr.getJSONObject(i).getString("status");
//			        	findbagCode[i] = jsonArr.getJSONObject(i).getString("findbagCode");
//			        	unfeezeAddress[i] = jsonArr.getJSONObject(i).getString("unfeezeAddress");
//			        	findboxCode[i] = jsonArr.getJSONObject(i).getString("findboxCode");
//			        	
//			        	areaTime[i] = jsonArr.getJSONObject(i).getString("areaTime");//Ƭ����վ��ʱ��
//			        	unfreezePerson[i] = jsonArr.getJSONObject(i).getString("unfreezePerson");
//			        	unfreeze_time[i] = jsonArr.getJSONObject(i).getString("unfreeze_time");
//			        	freezePerson[i] = jsonArr.getJSONObject(i).getString("freezePerson");
//			        	address[i] = jsonArr.getJSONObject(i).getString("address");//վ��
		                //����վ��ȥ��posid���ڸ���podidȥ���ˣ�ʹ���˺ţ�ʹ���ˣ�
		    			//String hql1 = "from Position p where p.posName = "+address[i];
		    
//20170605		    			List poss1 = new ArrayList();
//		    			List<Position> plist1 = null;
//		    			//plist1 = (List<Position>) this.queryHQL(hql1, poss1);
//		    			for (Position p :plist1)
//		    			{
//		    				p.getPosId();
//20170605		    			}
		    			
//			        	areaAddress[i] = jsonArr.getJSONObject(i).getString("areaAddress");//Ƭ���ص�
//			        	
//			        	freezeAddress[i] = jsonArr.getJSONObject(i).getString("freezeAddress");
//			        	companySendTime[i] = jsonArr.getJSONObject(i).getString("companySendTime");//������˾����Ƭ��ʱ��
//			        	freeze_time[i] = jsonArr.getJSONObject(i).getString("freeze_time");
//			        	success[i] = jsonArr.getJSONObject(i).getString("success");	//״̬
//			        	
//			        	System.out.println("׷��·������˾��ַ��"+companyAddress[i]+"\n��˾��Ƭ��ʱ�䣺"+companySendTime[i]+"\nƬ����ַ��"+
//			        			           areaAddress[i]+"\nƬ����վ��ʱ�䣺"+areaTime[i]+"\n׷�ݵ����ճɹ��鵽δʩ��վ�㣺"+address[i]+"\n����״̬��"+success[i]);
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
//					System.out.println("׷�ݳ�����߲������ݿ����");
//					e2.printStackTrace();
//				}
//		       
//		    }
		    
		    
		    foundSiteUnlockNotSeal.setFsun_unfreeze_code(code);// 1
		    foundSiteUnlockNotSeal.setFsun_unfreeze_account(e.getDoName());//���ֽ��δʩ����˺� 2
		    foundSiteUnlockNotSeal.setFsun_unfreeze_person(e.getDoName());//��������� 3
		    foundSiteUnlockNotSeal.setFsun_unfreeze_posname(e.getPosiName());//���δʩ����֣����֣��ĵط� 4
		    foundSiteUnlockNotSeal.setFsun_unfreeze_posid(e.getPosid());//����posid 5
		    foundSiteUnlockNotSeal.setFsun_unfreeze_time(e.getTime());//���ص㷢��δʩ���ʱ�䣬�����ڵ�ǰһ�� 6
		    
		    
		    StringBuilder json = new StringBuilder(); //����׷�ݹ��ܲ�ѯ��ʩ��δ����Դͷ��Դ 
	        try 
	        {  
	            URL urlObject = new URL("http://192.168.0.122:8080/Ltmcp/mobile/distribution_traceAction?&code="+code);  //ע������֮ǰһ���޸Ĵ����Ӽ���http��http://www.56tr.cn/mobile/distribution_traceAction?&code=
	            URLConnection uc = urlObject.openConnection();  
	            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  //��Ҫע��ת��
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
		         * System.out.println("��ӡ���δʩ��׷��վ��:"+json.toString());
		         * ������ͨ��׷�ݹ��ܻ�ȡ��������ͨ��java�������json.toString();
		         * ������֪������׷�ݵ�·��ֻ�ܵ�վ������κ�������Υ�������Ϊ��������������Ч���䱾�ʿ�������������
		         * [{"returnCode":"0iSETVgcvq",
		         * "companyAddress":"���Զ�����˾��ַ",
		         * "status":"0",
		         * "findbagCode":"bgtrewqq",
		         * "unfeezeAddress":null,
		         * "findboxCode":"xcccc1112",
		         * "areaTime":"2017-05-23 09:42:20.0",
		         * "unfreezePerson":null,
		         * "unfreeze_time":null,
		         * "freezePerson":null,
		         * "address":"����Aվ",
		         * "areaAddress":"����Ƭ��",
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
		        	companyAddress[i] = jsonArr.getJSONObject(i).getString("companyAddress");//��˾��ַ
		        	status[i] = jsonArr.getJSONObject(i).getString("status");
		        	findbagCode[i] = jsonArr.getJSONObject(i).getString("findbagCode");
		        	unfeezeAddress[i] = jsonArr.getJSONObject(i).getString("unfeezeAddress");
		        	findboxCode[i] = jsonArr.getJSONObject(i).getString("findboxCode");
		        	
		        	areaTime[i] = jsonArr.getJSONObject(i).getString("areaTime");//Ƭ����վ��ʱ��
		        	unfreezePerson[i] = jsonArr.getJSONObject(i).getString("unfreezePerson");
		        	unfreeze_time[i] = jsonArr.getJSONObject(i).getString("unfreeze_time");
		        	freezePerson[i] = jsonArr.getJSONObject(i).getString("freezePerson");
		        	address[i] = jsonArr.getJSONObject(i).getString("address");//վ��
		        	areaAddress[i] = jsonArr.getJSONObject(i).getString("areaAddress");//Ƭ���ص�
		        	
		        	freezeAddress[i] = jsonArr.getJSONObject(i).getString("freezeAddress");
		        	companySendTime[i] = jsonArr.getJSONObject(i).getString("companySendTime");//������˾����Ƭ��ʱ��
		        	freeze_time[i] = jsonArr.getJSONObject(i).getString("freeze_time");
		        	success[i] = jsonArr.getJSONObject(i).getString("success");	//״̬
		        	
		        	System.out.println("׷��·������˾��ַ��"+companyAddress[i]+"\n��˾��Ƭ��ʱ�䣺"+companySendTime[i]+"\nƬ����ַ��"+
		        			           areaAddress[i]+"\nƬ����վ��ʱ�䣺"+areaTime[i]+"\n׷�ݵ����ճɹ��鵽δʩ��վ�㣺"+address[i]+"\n����״̬��"+success[i]);
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
				
				System.out.println("׷�ݳ�����߲������ݿ����");
				e2.printStackTrace();
			}
//		  
		    
		    
		    if(e.getComid() == 1)
		    {
		    	//��sendDuanxin = new SendDuanxin();
		    	messageOne = "���ã���һ���쳣���δʩ����Ϣ�������Ե�¼app���쳣��¼�鿴��ʱ�䣺"+tim_sub.substring(0, 16)+",�ص㣺"+sit;
		    	
			    try 
			    {
			    	phone="18280076437";//�мǣ����޸ĺ���
					//��sendDuanxin.send(phone,messageOne);
				} catch (Exception e1)
				{
				}
			  //��e.setSend_phone(phone);
			  //�� e.setSend_status(1);

		    }else if(e.getComid() == 5)
		    {
		    	    phone="15882279820";//�мǣ����޸ĺ���
		    	//��sendDuanxin = new SendDuanxin();
		    	messageOne = "���ã���һ���쳣���δʩ����Ϣ�������Ե�¼app���쳣��¼�鿴��ʱ�䣺"+tim_sub.substring(0, 16)+",�ص㣺"+sit;
			    try 
			    {
					//��sendDuanxin.send(phone,messageOne);
				} catch (Exception e1)
				{
				}
			  //��e.setSend_phone(phone);
			  //��e.setSend_status(1);
		    }else if(e.getComid() == 4)//�����õ���������
		    {
		    	phone="15882279820";//�мǣ����޸ĺ���
		    	//sendDuanxin = new SendDuanxin();
		    	messageOne = "���ã���һ���쳣���δʩ����Ϣ�������Ե�¼app���쳣��¼�鿴��ʱ�䣺"+tim_sub.substring(0, 16)+",�ص㣺"+sit;
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
