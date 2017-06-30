package com.ltmcp.comm;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.shoufa_person;

public class AdminComm {
	/**
	 * 封装相关的admin信息，方便其他方法调用
	 * @return
	 */
	public static Integer getComIdByAdmin(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		if(admin == null)
		{
			shoufa_person shoufa_person = (shoufa_person)session.getAttribute(Comm.CURRENT_ADMIN);
			return shoufa_person.getCom_id();
		}else
		{
			return admin.getCompany().getComId();
		}
		
	}
	
	public static String getComNmByAdmin(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getCompany().getComName();
	}
	
	public static Integer getAdminId(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getAdmId();

	}
	
	public static Integer getAdminPower(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getRole().getRoleId();

	}
	public static Integer getAdminAreaId(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getAreaId();
	}
	
	public static String getAdminName(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getAdmName();

	}
	
	public static String getAdminTrueName(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		return admin.getAdmTrueName();

	}
}
