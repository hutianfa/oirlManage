package com.ltmcp.comm;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.shoufa_person;

public class Shoufa_personComm {
	/**
	 * ��װ��ص�shoufa_person��Ϣ������������������
	 * @return
	 */
	
	/**
	 * ��װapp��¼���˻���Ϣ֮һ����ѯ�ö�����˾��¼���˺ţ�pangzhihua��guangan����id����ʱֻ��װ�������������app�˸���id������com_id
	 * ΪnullƬ���˺�
	 * @return ������˾��id
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
