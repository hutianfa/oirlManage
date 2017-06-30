package com.ltmcp.comm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用方法
 * @author Administrator
 *
 */
public class CommonAction extends ActionSupport{
	
	protected HttpSession session = null;
	protected HttpServletRequest request = null;
	protected HttpServletResponse respose = null;
	protected ServletContext context = null;
	protected void getParam() {
		this.session = ServletActionContext.getRequest().getSession();
		this.request = ServletActionContext.getRequest();
		this.respose = ServletActionContext.getResponse();
		this.context =  ServletActionContext.getServletContext();
	}
	
}
