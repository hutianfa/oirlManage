package com.ltmcp.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.entity.Position;
import com.ltmcp.service.BaseService;
import com.opensymphony.xwork2.ActionContext;

public class BaseServiceImpl  implements BaseService{

	@Override
	public Object getSessionObject(String name) {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Object obj=session.getAttribute(name);
		return obj;
	}

}
