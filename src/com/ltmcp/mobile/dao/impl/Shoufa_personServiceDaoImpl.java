package com.ltmcp.mobile.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.mobile.dao.Shoufa_personServiceDao;

public class Shoufa_personServiceDaoImpl extends BaseDaoHibImpl implements Shoufa_personServiceDao {

	@Override
	public void saveShoufa_personInfoToSession(shoufa_person shoufa_person) {
		if (null != shoufa_person) {
			System.out.println("进入保存app端账号信息!");
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute(Comm.CURRENT_SHOUFA_PERSON, shoufa_person);
//			session.setAttribute(Comm.CURRENT_ADMIN_ID, admin.getAdmId());
//			session.setAttribute(Comm.CURRENT_ADMIN_ALL_POWER, powers);
//			session.setAttribute(Comm.CURRENT_ADMIN_MENU_NAME, menus);
		}
		
	}

	
}
