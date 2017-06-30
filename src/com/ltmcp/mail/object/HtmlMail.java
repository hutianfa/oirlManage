package com.ltmcp.mail.object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.ltmcp.mail.dao.CityMailDao;
import com.ltmcp.mail.entity.CityMail;
import com.ltmcp.mail.entity.Mail;
import com.ltmcp.mail.main.MailService;
import com.ltmcp.util.UrlAndPathComm;


public class HtmlMail { 
    static int count = 0;
    Mail mail = new Mail();
    
    private static String beginTime;
    private static String endTime;
	   public static void gettime(){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	 Calendar c1 = Calendar.getInstance();
	    	    c1.set(Calendar.HOUR_OF_DAY, 0);
	    	    c1.set(Calendar.MINUTE, 0);
	    	    c1.set(Calendar.SECOND, 0);
	    	    Date date_begin = c1.getTime();
	    	    beginTime = sdf.format(date_begin);
	    	    System.out.println(beginTime);
	    	    
	    	    c1.set(Calendar.HOUR_OF_DAY, 23);
	    	    c1.set(Calendar.MINUTE, 59);
	    	    c1.set(Calendar.SECOND, 59);
	    	    Date date_end = c1.getTime();
	    	    endTime = sdf.format(date_end);
	    	    System.out.println(endTime);
	   }
	/**
     * 定时执行任务
     */
    public void service() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                MailService sender = new MailService();   
	        		List<CityMail> cm = new CityMailDao().cityMail();
	        		
	        		HttpClient client = new HttpClient();
	        		String url ="http://www.56tr.cn/mobile/mail_mail?condition.comId=1&condition.BigPosid=8";
	        		HttpMethod method0 =new GetMethod(url);
	        		try {
						client.executeMethod(method0);
						String status= method0.getResponseBodyAsString();//状态：0没有注册，1已注册
		    			method0.releaseConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
	        		
	        		
	        		for(CityMail c : cm){
	        			if(c.getRankMail()==0){
	    	                ++count;
	    	                sender.mail.setFrom("*****@163.com");
	    	                sender.mail.setSubject("市区系统邮件");  
	    	        		List<CityMail> listcm = new CityMailDao().QueryAllById(0);
	    	        		for(CityMail cc : listcm){
		    	                sender.mail.setTo(cc.getSiteMail());
		    	                System.out.println(cc.getSiteMail()+"*************");
	    	        		}
	    	        		
	    	        		String filePath = UrlAndPathComm.comm+"mailFile"+File.separator+"VillageMyFile.html";
	    	        		
	    	                sender.mail.setBody(readTxtFile(filePath).toString());
	    	                sender.mail.setCopyto("883354039@qq.com");//抄送
	    	                sender.sendMail(); 
	    	                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
	        				System.out.println("市区");
	        			}
	        			else if(c.getRankMail()==1){
	    	                ++count;
	    	                sender.mail.setFrom("****@163.com");
	    	                sender.mail.setSubject("县区系统邮件");  
	    	        		List<CityMail> listcm = new CityMailDao().QueryAllById(1);
	    	        		for(CityMail cc : listcm){
		    	                sender.mail.setTo(cc.getSiteMail());
		    	                System.out.println(cc.getSiteMail()+"*************");
	    	        		}
	    	        		String filePath = UrlAndPathComm.comm+"mailFile"+File.separator+"VillageMyFile.html";
	    	        		sender.mail.setBody(readTxtFile(filePath).toString());
	    	                sender.mail.setCopyto("469256014@qq.com");//抄送
	    	                sender.sendMail(); 
	    	                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
	        				System.out.println("县区");
	        			}else if(c.getRankMail()==2){
	    	                ++count;
	    	                sender.mail.setFrom("*****@163.com");
	    	                sender.mail.setSubject("乡下系统邮件");  
	    	        		List<CityMail> listcm = new CityMailDao().QueryAllById(2);
	    	        		for(CityMail cc : listcm){
		    	                sender.mail.setTo(cc.getSiteMail());
		    	                System.out.println(cc.getSiteMail()+"*************");
	    	        		}
	    	        		String filePath = UrlAndPathComm.comm+"mailFile"+File.separator+"VillageMyFile.html";
	    	                sender.mail.setBody(readTxtFile(filePath).toString());
	    	                sender.mail.setCopyto("469256014@qq.com");//抄送
	    	                sender.sendMail(); 
	    	                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
	        				System.out.println("乡");
	        			}
	        		}
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 11, 50, 0);
        Date date = calendar.getTime();
        Timer timer = new Timer();

        timer.schedule(task, date,24*60*60*1000);
    }
	public static StringBuffer readTxtFile(String filePath){
        try {
        	StringBuffer su = new StringBuffer();
                String encoding="UTF8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        su.append(lineTxt);
                    }
                    read.close();
                    return su;
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     return null;
    }
}
