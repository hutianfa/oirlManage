package com.ltmcp.mobile.biz.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ltmcp.condition.MailCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Petrol;
import com.ltmcp.entity.Position;
import com.ltmcp.mobile.biz.MailMobileBiz;
import com.ltmcp.mobile.dao.MailMobileDao;
import com.ltmcp.util.UrlAndPathComm;

public class MailMobileBizImpl implements MailMobileBiz{
	int ount = 0;
	int i = 0;
	int j = 0;
	int row = 0;
	int countId = 0;
	
	private MailMobileDao mailMobileDao;
    private String filenameTemp;
	@Override
	public void findInfor(MailCondition condition) {
		//首先获取相应公司的所有车辆
		List<Car> car = mailMobileDao.queryCar(condition);
		//获取相应公司的在用站点信息
		List<Position> position = mailMobileDao.queryPosi(condition);
		
		try {			  
            creatTxtFile("VillageMyFile");
            StringBuffer style = new StringBuffer();
            StringBuffer body = new StringBuffer();
            /**
             * 将html导入到本地文件中
             */
  	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    	    Date date = new Date();
           
    	    try {
    	    	
			    	    	//遍历第一辆车的信息，以及获取该车在当天的所有油库施封总数
			    			for(i = 0 ;i<car.size();i++){
			    				Integer carId = car.get(i).getCarId();
			    				String carFlapper = car.get(i).getCarFlapper();
			    				Integer totalWay = mailMobileDao.queryWayAllNum(carId, condition);
			    				//1.获取该车其他站点的施封数；2.获取对应的油损
			    				
			    				for(int x = 0 ;x<position.size();x++){
			    					Integer posid = position.get(x).getPosId();
			    					
			    					if(posid == 8){
			    						continue;
			    					}
			    					String posName = position.get(x).getPosName();
			    					Integer posiTotal =  mailMobileDao.queryWayNoAllNum(carId, posid, condition);
			    					
	              	        		if(posiTotal > 0){	              	        				
	              	        			++countId;
	              	        		}
			    				}
			    				
			    				
			    				for(j = 0 ;j<position.size();j++){
			    					Integer posid = position.get(j).getPosId();
			    					
			    					if(posid == 8){
			    						continue;
			    					}
			    					String posName = position.get(j).getPosName();
			    					Integer posiTotal =  mailMobileDao.queryWayNoAllNum(carId, posid, condition);
			    					
	              	        		if(posiTotal > 0){
	              	        			++row;
	              	        			if(row == 1){	              	        				
		    	          	        		body.append("<tr>");
		    	          	        		body.append("<td rowspan='"+countId+"'>"+ carFlapper+"</td>");
		    	          	        		body.append("<td rowspan='"+countId+"'>"+totalWay+"</td>");
		    	          	        		body.append("<td>"+posName+"</td>");
		    	          	        		body.append("<td>"+posiTotal+"（个）</td>");
		              	        		
			              	        	//油品相关
		    	          	        		Petrol petrol = mailMobileDao.queryPetrol(carFlapper, posid, condition);
					    					if(petrol != null){
					    							Double faTotal = petrol.getPetrol_total();
						    						Double suTotal = petrol.getPetrol_loss();
						    						Double shTotal = faTotal - suTotal;
						    						DecimalFormat  df = new   DecimalFormat("0.000");   
						    						String pai = df.format(suTotal/faTotal);

		    	              	        		body.append("<td>"+petrol.getSea_oilpin()+"</td>" +
					    	              	        		"<td>"+faTotal+"（升）</td>" +
					    	              	        		"<td>"+shTotal+"（升）</td>" +
					    	              	        		"<td>"+suTotal+"（升）</td>" +
					    	              	        		"<td>"+pai+"（‰）</td>");
	    	              	        		}else{
	    	              	        			body.append("<td>无</td>" +
	    		    	              	        		"<td>0（升）</td>" +
	    		    	              	        		"<td>0（升）</td>" +
	    		    	              	        		"<td>0（升）</td>" +
	    		    	              	        		"<td>0（‰）</td>");
	    	              	        		}

		    	          	        		body.append("</tr>");
	              	        			}else{
	              	        				
	              	        				body.append("<tr>");
	              	        				body.append("<td>"+posName+"</td>");
	              	        				body.append("<td>"+posiTotal+"（个）</td>");
		              	        		
			              	        	//油品相关
	              	        				Petrol petrol = mailMobileDao.queryPetrol(carFlapper, posid, condition);
					    					if(petrol != null){
					    							Double faTotal = petrol.getPetrol_total();
						    						Double suTotal = petrol.getPetrol_loss();
						    						Double shTotal = faTotal - suTotal;
						    						DecimalFormat  df = new   DecimalFormat("0.000");   
						    						String pai = df.format(suTotal/faTotal);

		    	              	        		body.append("<td>"+petrol.getSea_oilpin()+"</td>" +
			    	              	        				"<td>"+faTotal+"（升）</td>" +
					    	              	        		"<td>"+shTotal+"（升）</td>" +
					    	              	        		"<td>"+suTotal+"（升）</td>" +
					    	              	        		"<td>"+pai+"（‰）</td>");
//					    						}
					    					}else{
					    						body.append("<td>无</td>" +
				    	              	        		"<td>0（升）</td>" +
				    	              	        		"<td>0（升）</td>" +
				    	              	        		"<td>0（升）</td>" +
				    	              	        		"<td>0（‰）</td>");
					    					}
	              	        				
	              	        				body.append("</tr>");
	              	        			}
	              	        		}
			    				}
			    				row = 0;
			    				countId = 0;
			    			}			    			
			    			
    	          	    writeTxtFile(style,body);
    	          	     
			} catch (Exception e) {
				e.printStackTrace();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	

	  //写文件的方法 
	  //style css的样式字符串
	  //body 表格的tr td 字符串 
	 public  void writeTxtFile(StringBuffer style,StringBuffer body) throws IOException{
//	        // 先读取原有文件内容，然后进行写入操作
	       
	       
	       StringBuffer str = new StringBuffer();
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	       String time = sdf.format(new Date());
	       
	       str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")  
	    	          	        .append("<html>")  
	    	          	        .append("<head>") 
	    	          	        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
	    	          	        .append("<title>测试邮件</title>") 
	    	          	        .append("<style> *{margin: 0 auto;font-size: 14px;}" +
	    	          	        		" .mian{margin: 0 auto;width: 820px;}" +
	    	          	        		"table tr td{ text-align: center; white-space:nowrap; }" +
	    	          	        		"div input{ width:70px; }" +
	    	          	        		".header{margin:0 auto; width:820px;height:auto;text-align: center;}" +
	    	          	        		".header h1{font-size: 24px;color: red;font-family: \"黑体\";font-weight: bold;}" +
	    	  	        				".header div{font-size: 16px;padding: 10px 0px;color: #0d0d0d;font-weight: bold;}" +
	    	  	        				".title div{text-align: left;padding: 10px 0px;font-weight: bolder;}" +
	    	  	        				".content1 div:nth-child(1){text-align: left;font-weight: bolder;}" +
	    	  	        				".content1 div:nth-child(2){text-align: left;letter-spacing:2px;line-height: 32px;}" +
	    	  	        				".content1 div:nth-child(3){text-align: left;font-weight: bolder;}" +
	    	  	        				".content1 div{text-align: left;font-weight: 500;font-family: \"华文中宋\";}"
	    				+ style)
	                                .append("</style>")
	                                .append("</head>")  
	    	          	        .append("<body>")  
	    	          	        .append(" <div class=\"header\">") 
	    	          	        .append("<h1>中石油新一代油罐车运输防盗管理项目</h1>")
	    	          	        .append("<div>数字封签项目日报</div>" +
	    	          	        		" <div>时间："+time+"</div>" +
	    	          	        		" </div>" +
	    	          	        		" <div class=\"title header\">" +
	    	          	        		"<div>主送：工程处、运调处、中国电信项目组</div>" +
	    	          	        		"<div>抄送：攀枝花分公司</div></div><div class=\"content1 header\"> <div>" +
	    	          	        		"一、总体情况</div>" +
	    	          	        		"<div>&nbsp;&nbsp;攀枝花中石油共有1个油库，64个加油站。首批试点于2月4日开始，共1个油库，4个加油站,47辆油罐车（包括上装车和下装车）。第二批试点于3月28日开始，共1个油库，55个加油站，47辆油罐车，油库每天发车最多110-120趟次，加油站收油每天0-3车。从使用二维码数字封签开始,我方就派技术人员从油库到加油站进行全程跟踪，对施封解封的人员进行操作培训，对出现的问题进行及时解决，不断完善系统，更好的满足运输防盗管理需求。</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"header content1\">" +
	    	              	        		"<div>二、本日推进情况及存在问题</div>" +
	    	              	        		"<div class=\"content1-1\"> " +
	    	              	        		"<div style=\"color: #8b2767;\">（一）项目情况：</div>" +
	    	              	        		"<div>1、操作数据：" +
	    	          	        		"</div>" +
	    	          	        		"<div style=\"margin-top: 20px\" >")
	    	          	        .append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top: 5px;width:820px;\">" +
                	        		"<tr>" +
	                	        		"<td>车牌号</td>" +
	                	        		"<td>油库施封数（个）</td>" +
	                	        		"<td>加油站名称</td>" +
	                	        		"<td>加油站施封数（个）</td>" +
	                	        		"<td>油品</td>" +
	                	        		"<td>原发（升）</td>" +
	                	        		"<td>实收（升）</td>" +
	                	        		"<td>差异（升）</td>" +
	                	        		"<td>耗损率（‰）</td>" +
                	        		"</tr>"+ body )
	                            .append("</table>")
	    	          	        .append("</div>")
	    	          	        .append("<div style=\"color: red\">注：由于全市上线后，攀枝花分公司由于管理上的要求，需省公司下文，才会提供所有站点每日每车油品损耗数据，所以目前，上述数据为待获取状态。</div>")
	    	          	        .append("<div>结论：攀枝花分公司操作规范性有待获取损耗数据进行分析。</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-2\">")
	    	          	        .append("<div>2、油品损耗比</div>" +
	    	          	        		"<div>待获取数据。</div>" +
	    	          	        		"<div>结论：近七日油品损耗比数据趋势图如下：</div>" +
	    	          	        		"<div>待获取数据后生成。</div>" +
	    	          	        		"<div>当日综合油品损耗比为：待获取数据计算，在警戒线千分之二以下。</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-3\">")
	    	          	        .append("<div>3、项目数据门户APP升级到3.6版本，新增功能如下</div>" +
	    	          	        		"<div>增强扫描页面的辨别度</div>" +
	    	          	        		"<div>修复软件逻辑</div>" +
	    	          	        		"<div>WEB端片区查询数据。</div>" +
	    	          	        		"<div>结论：新功能上线日期为3月28日，满足网络侦测功能，提升操作效能；WEB端可分片区查询数据，符合管理需求。</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-4\">" +
	    	          	        		"<div>4、收集实际使用者信息反馈3条，准备用于客户端开发优化功能。</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"content1-5\">" +
	    	          	        		"<div style=\"color: #8b2767;\">（二）出现问题：</div>" +
	    	          	        		"<div>1、用户反馈：从全面试点以后，出现数字封签扫二维码时存在扫码比较慢或提交出现数据不符的情况。</div>" +
	    	          	        		"<div>我方分析：</div>" +
	    	          	        		"<div style=\"text-indent: 24px;letter-spacing:2px;line-height: 32px;\">本批次封签在物流运输过程中出现较严重的挤压摩擦，导致少量变形，二维码损坏等情况。我们已更换物流公司，并和新的物流公司做交涉，保证运输质量，保证以后此类情况不会发生。</div>" +
	    	          	        		"<div style=\"text-indent: 24px;letter-spacing:2px;line-height: 32px;\"> 针对封签可能出现质量问题的情况，我们立即启动应急预案：召回上批次所有封签，并对上批次封签进行更换，截止4月3号，第一批更换封签已经全部送达攀枝花分公司。并要求攀枝花分公司立即更换。第二批更换封签预计8号左右送达攀枝花。这样，旧的可能有质量问题的封签在生产中就不再使用。</div></div>")
	    	          	        .append("<div class=\"content1-6\"> " +
	    	          	        		"<div>三、明日计划</div><div>1、继续完善并优化WEB端管理。</div>" +
	    	          	        		"<div>2、继续完善并优化APP。</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"content1-7\">" +
	    	          	        		"<div>四、问题求助</div> " +
	    	          	        		"<div style=\"text-indent: 24px;color: red;\">全市试点铺开后，由于攀枝花分公司管理上的要求，需要省公司协调，才会下文给站点向项目组提供每日每车油品损耗数据。</div>" +
	    	          	        		"<div style=\"text-indent: 24px;color: red;\">目前，操作数据中油品损耗数据空缺。</div> " +
	    	          	        		"</div>")
	    	          	        .append("<div>项目组联系人：陈继 17781698930</div>")
	    	          	        .append("</div>")
	    	          	        .append("</div>")
	    	          	        .append("</body>")  
	    	          	        .append("</html>");
	       
	       
	       
	        boolean flag = false;
	    	
	        FileInputStream fis = null;
	        InputStreamReader isr = null;
	        BufferedReader br = null;

	        FileOutputStream fos = null;
	        PrintWriter pw = null;
	        try {            
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filenameTemp)), "UTF-8"));
	            
	            bw.write(str.toString().toCharArray());
	            bw.flush();
	            bw.close();  
	   
	        } catch (IOException e1) {
	            throw e1;
	        } finally {
	           if (pw != null) {
	               pw.close();
	           }
	           if (fos != null) {
	               fos.close();
	           }
	           if (br != null) {
	               br.close();
	           }
	           if (isr != null) {
	               isr.close();
	           }
	           if (fis != null) {
	               fis.close();
	           }
	       }
	   }
	
    /**
     * 创建文件
     * 
     * @throws IOException
     */
    public  void creatTxtFile(String name) throws IOException {
        
        String fileDir = UrlAndPathComm.comm+"mailFile"+File.separator;
        
        filenameTemp = fileDir+ name +".html";
        
        File file = new File(fileDir);
		// 假如dir不存在，则创建
		if (!file.exists()) {
			file.mkdir();
		}
    }
	
    
    public String readDate() {
        // 定义一个待返回的空字符串
        String strs = "";
        try {
            FileReader read = new FileReader(new File(filenameTemp));
            StringBuffer sb = new StringBuffer();
            char ch[] = new char[1024];
            int d = read.read(ch);
            while (d != -1) {
                String str = new String(ch, 0, d);
                sb.append(str);
                d = read.read(ch);
            }
            String a = sb.toString().replaceAll("@@@@@", ",");
            strs = a.substring(0, a.length() );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }
	
	public MailMobileDao getMailMobileDao() {
		return mailMobileDao;
	}
	public void setMailMobileDao(MailMobileDao mailMobileDao) {
		this.mailMobileDao = mailMobileDao;
	}
	public String getFilenameTemp() {
		return filenameTemp;
	}
	public void setFilenameTemp(String filenameTemp) {
		this.filenameTemp = filenameTemp;
	}
	
}
