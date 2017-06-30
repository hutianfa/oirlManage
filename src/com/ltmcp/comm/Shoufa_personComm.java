package com.ltmcp.comm;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.shoufa_person;

public class Shoufa_personComm {
	/**
	 * 封装相关的shoufa_person信息，方便其他方法调用
	 * @return
	 */
	
	/**
	 * 封装app登录的账户信息之一：查询该二级公司登录的账号（pangzhihua，guangan）的id（暂时只封装了这个方法），app端根据id屏蔽无com_id
	 * 为null片区账号
	 * @return 二级公司的id
	 */
	public static Integer getComIdByShoufa_person(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		shoufa_person shoufa_person = (shoufa_person)session.getAttribute(Comm.CURRENT_SHOUFA_PERSON);
		return shoufa_person.getCom_id();
	
	}
	
//	public static String getComNmByAdmin(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getCompany().getComName();
//	}
//	
//	public static Integer getAdminId(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getAdmId();
//
//	}
//	
//	public static Integer getAdminPower(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getRole().getRoleId();
//
//	}
//	public static Integer getAdminAreaId(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getAreaId();
//	}
//	
//	public static String getAdminName(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getAdmName();
//
//	}
//	
//	public static String getAdminTrueName(){
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
//		return admin.getAdmTrueName();
//
//	}
}
