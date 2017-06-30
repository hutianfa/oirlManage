package com.ltmcp.mail.action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ltmcp.mail.object.HtmlMail;

public class SendMailAction extends HttpServlet {
	/**
	 * 系统邮件容器的加载
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
		HtmlMail hm = new HtmlMail();
		hm.service();
		System.out.println("邮件容器加载成功！");
	}

}
