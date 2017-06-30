package com.ltmcp.mail.main;

import java.util.*;  

import javax.mail.*;  
import javax.mail.internet.*;  

import com.ltmcp.mail.entity.Mail;


  
public class MailService {  
    static int count = 0;
	private static MimeMessage mimeMsg;
	public Mail mail = new Mail();
        /**  
         * 发送邮件. 
         * @return boolean - 发送结果  
         */  
        public boolean sendMail() {  
            if (mail.getBody() == null || mail.getTo() == null || mail.getFrom() == null  
                    || mail.getSubject() == null) { return false; }  
            try{
              Properties props = new Properties();
              props.put("username", "********@163.com");
              props.put("password", "********");
              props.put("mail.transport.protocol", "smtp");
              props.put("mail.smtp.host", "smtp.163.com");
              props.put("mail.smtp.port", "25");
              
              
              Session mailSession = Session.getInstance(props);
              
              Message msg = new MimeMessage(mailSession);

              msg.setFrom(new InternetAddress(mail.getFrom()));
              msg.addRecipients(Message.RecipientType.TO, 
                InternetAddress.parse(mail.getTo())); msg.addRecipients(Message.RecipientType.TO, 
                InternetAddress.parse(mail.getCopyto()));
              msg.setSentDate(new Date());
              msg.setSubject(mail.getSubject());

              msg.setContent(mail.getBody(), "text/html;charset=gb2312");
              msg.saveChanges();
              System.out.println("正在连接服务器...");
              Transport transport = mailSession.getTransport("smtp");
              transport.connect(props.getProperty("mail.smtp.host"), 
              props.getProperty("username"), props.getProperty("password"));

              System.out.println("正在发送邮件...");
              transport.sendMessage(msg, msg.getAllRecipients());
              transport.close();
              System.out.println("发送完毕...");
            }
            catch (Exception e) {
              e.printStackTrace();
              System.out.println(e);
              return false;
            }     
            return true;  
        }  

        /**
         * 抄送邮件
         * @param copyto
         * @return
         */
	    public boolean setCopyTo(String copyto) {
	        if (copyto == null)
	            return false;
	        try {
	        	mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress
	                    .parse(copyto));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	   
}  