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
		//���Ȼ�ȡ��Ӧ��˾�����г���
		List<Car> car = mailMobileDao.queryCar(condition);
		//��ȡ��Ӧ��˾������վ����Ϣ
		List<Position> position = mailMobileDao.queryPosi(condition);
		
		try {			  
            creatTxtFile("VillageMyFile");
            StringBuffer style = new StringBuffer();
            StringBuffer body = new StringBuffer();
            /**
             * ��html���뵽�����ļ���
             */
  	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
    	    Date date = new Date();
           
    	    try {
    	    	
			    	    	//������һ��������Ϣ���Լ���ȡ�ó��ڵ���������Ϳ�ʩ������
			    			for(i = 0 ;i<car.size();i++){
			    				Integer carId = car.get(i).getCarId();
			    				String carFlapper = car.get(i).getCarFlapper();
			    				Integer totalWay = mailMobileDao.queryWayAllNum(carId, condition);
			    				//1.��ȡ�ó�����վ���ʩ������2.��ȡ��Ӧ������
			    				
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
		    	          	        		body.append("<td>"+posiTotal+"������</td>");
		              	        		
			              	        	//��Ʒ���
		    	          	        		Petrol petrol = mailMobileDao.queryPetrol(carFlapper, posid, condition);
					    					if(petrol != null){
					    							Double faTotal = petrol.getPetrol_total();
						    						Double suTotal = petrol.getPetrol_loss();
						    						Double shTotal = faTotal - suTotal;
						    						DecimalFormat  df = new   DecimalFormat("0.000");   
						    						String pai = df.format(suTotal/faTotal);

		    	              	        		body.append("<td>"+petrol.getSea_oilpin()+"</td>" +
					    	              	        		"<td>"+faTotal+"������</td>" +
					    	              	        		"<td>"+shTotal+"������</td>" +
					    	              	        		"<td>"+suTotal+"������</td>" +
					    	              	        		"<td>"+pai+"���룩</td>");
	    	              	        		}else{
	    	              	        			body.append("<td>��</td>" +
	    		    	              	        		"<td>0������</td>" +
	    		    	              	        		"<td>0������</td>" +
	    		    	              	        		"<td>0������</td>" +
	    		    	              	        		"<td>0���룩</td>");
	    	              	        		}

		    	          	        		body.append("</tr>");
	              	        			}else{
	              	        				
	              	        				body.append("<tr>");
	              	        				body.append("<td>"+posName+"</td>");
	              	        				body.append("<td>"+posiTotal+"������</td>");
		              	        		
			              	        	//��Ʒ���
	              	        				Petrol petrol = mailMobileDao.queryPetrol(carFlapper, posid, condition);
					    					if(petrol != null){
					    							Double faTotal = petrol.getPetrol_total();
						    						Double suTotal = petrol.getPetrol_loss();
						    						Double shTotal = faTotal - suTotal;
						    						DecimalFormat  df = new   DecimalFormat("0.000");   
						    						String pai = df.format(suTotal/faTotal);

		    	              	        		body.append("<td>"+petrol.getSea_oilpin()+"</td>" +
			    	              	        				"<td>"+faTotal+"������</td>" +
					    	              	        		"<td>"+shTotal+"������</td>" +
					    	              	        		"<td>"+suTotal+"������</td>" +
					    	              	        		"<td>"+pai+"���룩</td>");
//					    						}
					    					}else{
					    						body.append("<td>��</td>" +
				    	              	        		"<td>0������</td>" +
				    	              	        		"<td>0������</td>" +
				    	              	        		"<td>0������</td>" +
				    	              	        		"<td>0���룩</td>");
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
	

	  //д�ļ��ķ��� 
	  //style css����ʽ�ַ���
	  //body ����tr td �ַ��� 
	 public  void writeTxtFile(StringBuffer style,StringBuffer body) throws IOException{
//	        // �ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
	       
	       
	       StringBuffer str = new StringBuffer();
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	       String time = sdf.format(new Date());
	       
	       str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")  
	    	          	        .append("<html>")  
	    	          	        .append("<head>") 
	    	          	        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
	    	          	        .append("<title>�����ʼ�</title>") 
	    	          	        .append("<style> *{margin: 0 auto;font-size: 14px;}" +
	    	          	        		" .mian{margin: 0 auto;width: 820px;}" +
	    	          	        		"table tr td{ text-align: center; white-space:nowrap; }" +
	    	          	        		"div input{ width:70px; }" +
	    	          	        		".header{margin:0 auto; width:820px;height:auto;text-align: center;}" +
	    	          	        		".header h1{font-size: 24px;color: red;font-family: \"����\";font-weight: bold;}" +
	    	  	        				".header div{font-size: 16px;padding: 10px 0px;color: #0d0d0d;font-weight: bold;}" +
	    	  	        				".title div{text-align: left;padding: 10px 0px;font-weight: bolder;}" +
	    	  	        				".content1 div:nth-child(1){text-align: left;font-weight: bolder;}" +
	    	  	        				".content1 div:nth-child(2){text-align: left;letter-spacing:2px;line-height: 32px;}" +
	    	  	        				".content1 div:nth-child(3){text-align: left;font-weight: bolder;}" +
	    	  	        				".content1 div{text-align: left;font-weight: 500;font-family: \"��������\";}"
	    				+ style)
	                                .append("</style>")
	                                .append("</head>")  
	    	          	        .append("<body>")  
	    	          	        .append(" <div class=\"header\">") 
	    	          	        .append("<h1>��ʯ����һ���͹޳��������������Ŀ</h1>")
	    	          	        .append("<div>���ַ�ǩ��Ŀ�ձ�</div>" +
	    	          	        		" <div>ʱ�䣺"+time+"</div>" +
	    	          	        		" </div>" +
	    	          	        		" <div class=\"title header\">" +
	    	          	        		"<div>���ͣ����̴����˵������й�������Ŀ��</div>" +
	    	          	        		"<div>���ͣ���֦���ֹ�˾</div></div><div class=\"content1 header\"> <div>" +
	    	          	        		"һ���������</div>" +
	    	          	        		"<div>&nbsp;&nbsp;��֦����ʯ�͹���1���Ϳ⣬64������վ�������Ե���2��4�տ�ʼ����1���Ϳ⣬4������վ,47���͹޳���������װ������װ�������ڶ����Ե���3��28�տ�ʼ����1���Ϳ⣬55������վ��47���͹޳����Ϳ�ÿ�췢�����110-120�˴Σ�����վ����ÿ��0-3������ʹ�ö�ά�����ַ�ǩ��ʼ,�ҷ����ɼ�����Ա���Ϳ⵽����վ����ȫ�̸��٣���ʩ�������Ա���в�����ѵ���Գ��ֵ�������м�ʱ�������������ϵͳ�����õ��������������������</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"header content1\">" +
	    	              	        		"<div>���������ƽ��������������</div>" +
	    	              	        		"<div class=\"content1-1\"> " +
	    	              	        		"<div style=\"color: #8b2767;\">��һ����Ŀ�����</div>" +
	    	              	        		"<div>1���������ݣ�" +
	    	          	        		"</div>" +
	    	          	        		"<div style=\"margin-top: 20px\" >")
	    	          	        .append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top: 5px;width:820px;\">" +
                	        		"<tr>" +
	                	        		"<td>���ƺ�</td>" +
	                	        		"<td>�Ϳ�ʩ����������</td>" +
	                	        		"<td>����վ����</td>" +
	                	        		"<td>����վʩ����������</td>" +
	                	        		"<td>��Ʒ</td>" +
	                	        		"<td>ԭ��������</td>" +
	                	        		"<td>ʵ�գ�����</td>" +
	                	        		"<td>���죨����</td>" +
	                	        		"<td>�����ʣ��룩</td>" +
                	        		"</tr>"+ body )
	                            .append("</table>")
	    	          	        .append("</div>")
	    	          	        .append("<div style=\"color: red\">ע������ȫ�����ߺ���֦���ֹ�˾���ڹ����ϵ�Ҫ����ʡ��˾���ģ��Ż��ṩ����վ��ÿ��ÿ����Ʒ������ݣ�����Ŀǰ����������Ϊ����ȡ״̬��</div>")
	    	          	        .append("<div>���ۣ���֦���ֹ�˾�����淶���д���ȡ������ݽ��з�����</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-2\">")
	    	          	        .append("<div>2����Ʒ��ı�</div>" +
	    	          	        		"<div>����ȡ���ݡ�</div>" +
	    	          	        		"<div>���ۣ���������Ʒ��ı���������ͼ���£�</div>" +
	    	          	        		"<div>����ȡ���ݺ����ɡ�</div>" +
	    	          	        		"<div>�����ۺ���Ʒ��ı�Ϊ������ȡ���ݼ��㣬�ھ�����ǧ��֮�����¡�</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-3\">")
	    	          	        .append("<div>3����Ŀ�����Ż�APP������3.6�汾��������������</div>" +
	    	          	        		"<div>��ǿɨ��ҳ��ı���</div>" +
	    	          	        		"<div>�޸�����߼�</div>" +
	    	          	        		"<div>WEB��Ƭ����ѯ���ݡ�</div>" +
	    	          	        		"<div>���ۣ��¹�����������Ϊ3��28�գ�����������⹦�ܣ���������Ч�ܣ�WEB�˿ɷ�Ƭ����ѯ���ݣ����Ϲ�������</div>")
	    	          	        .append("</div>")
	    	          	        .append("<div class=\"content1-4\">" +
	    	          	        		"<div>4���ռ�ʵ��ʹ������Ϣ����3����׼�����ڿͻ��˿����Ż����ܡ�</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"content1-5\">" +
	    	          	        		"<div style=\"color: #8b2767;\">�������������⣺</div>" +
	    	          	        		"<div>1���û���������ȫ���Ե��Ժ󣬳������ַ�ǩɨ��ά��ʱ����ɨ��Ƚ������ύ�������ݲ����������</div>" +
	    	          	        		"<div>�ҷ�������</div>" +
	    	          	        		"<div style=\"text-indent: 24px;letter-spacing:2px;line-height: 32px;\">�����η�ǩ��������������г��ֽ����صļ�ѹĦ���������������Σ���ά���𻵵�����������Ѹ���������˾�������µ�������˾�����棬��֤������������֤�Ժ����������ᷢ����</div>" +
	    	          	        		"<div style=\"text-indent: 24px;letter-spacing:2px;line-height: 32px;\"> ��Է�ǩ���ܳ�����������������������������Ӧ��Ԥ�����ٻ����������з�ǩ�����������η�ǩ���и�������ֹ4��3�ţ���һ��������ǩ�Ѿ�ȫ���ʹ���֦���ֹ�˾����Ҫ����֦���ֹ�˾�����������ڶ���������ǩԤ��8�������ʹ���֦�����������ɵĿ�������������ķ�ǩ�������оͲ���ʹ�á�</div></div>")
	    	          	        .append("<div class=\"content1-6\"> " +
	    	          	        		"<div>�������ռƻ�</div><div>1���������Ʋ��Ż�WEB�˹���</div>" +
	    	          	        		"<div>2���������Ʋ��Ż�APP��</div>" +
	    	          	        		"</div>")
	    	          	        .append("<div class=\"content1-7\">" +
	    	          	        		"<div>�ġ���������</div> " +
	    	          	        		"<div style=\"text-indent: 24px;color: red;\">ȫ���Ե��̿���������֦���ֹ�˾�����ϵ�Ҫ����Ҫʡ��˾Э�����Ż����ĸ�վ������Ŀ���ṩÿ��ÿ����Ʒ������ݡ�</div>" +
	    	          	        		"<div style=\"text-indent: 24px;color: red;\">Ŀǰ��������������Ʒ������ݿ�ȱ��</div> " +
	    	          	        		"</div>")
	    	          	        .append("<div>��Ŀ����ϵ�ˣ��¼� 17781698930</div>")
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
     * �����ļ�
     * 
     * @throws IOException
     */
    public  void creatTxtFile(String name) throws IOException {
        
        String fileDir = UrlAndPathComm.comm+"mailFile"+File.separator;
        
        filenameTemp = fileDir+ name +".html";
        
        File file = new File(fileDir);
		// ����dir�����ڣ��򴴽�
		if (!file.exists()) {
			file.mkdir();
		}
    }
	
    
    public String readDate() {
        // ����һ�������صĿ��ַ���
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
