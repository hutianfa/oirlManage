package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.dao.AdminDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Power;
import com.ltmcp.entity.Role;
import com.ltmcp.service.AdminService;
import com.ltmcp.util.MD5;

public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
	private AdminDao adminDao;

	@Override
	public Admin getAdmin(Admin admin) {
		System.out.println(admin);
		if (null == admin) {
			return new Admin();
		} else {
			if (null == admin.getAdmName() || null == admin.getAdmPassword()) {
				return new Admin();
			}
			admin.setAdmPassword(MD5.md5crypt(admin.getAdmPassword())); // 对输入的密码进行MD5加密
			return adminDao.queryAdmin(admin);
		}
	}

	@Override
	public void saveAdminInfoToSession(Admin admin, List<Power> powers,List<Power> menus) {
		if (null != admin && null != powers && null != menus) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute(Comm.CURRENT_ADMIN, admin);
			session.setAttribute(Comm.CURRENT_ADMIN_ID, admin.getAdmId());
			session.setAttribute(Comm.CURRENT_ADMIN_ALL_POWER, powers);
			session.setAttribute(Comm.CURRENT_ADMIN_MENU_NAME, menus);
		}
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public PageBean searchList(PageBean pagebean, AdminCondition condition) {
		if (null == pagebean) {
			pagebean = new PageBean();
		}
		List<Admin> list = adminDao.foundGeneralManagerlist(pagebean, condition);
		pagebean.setList(list);
		Integer totalCount = adminDao.queryGeneralManagerCount(condition);
		pagebean.setTotalCount(totalCount);
		return pagebean;
	}

	@Override
	public void delGeneralmanager(Integer id) throws Exception {
		adminDao.updateAdminStatus(id);
	}

	@Override
	public void addAdmin(Admin admin,AdminCondition condition) throws Exception {
		if (null != admin) {
			admin.setAdmCreateDate(new Timestamp(System.currentTimeMillis()));
			admin.setAdmStatus(Comm.ADMIN_NORMAL);
			
			if(AdminComm.getAdminPower() != 1){
				Company company = new Company();
				company.setComId(AdminComm.getComIdByAdmin());
				admin.setCompany(company);
			}else if(AdminComm.getAdminPower() == 1){
				Company company = new Company();
				company.setComId(condition.getComId());
				admin.setCompany(company);
			}
			Role role = new Role();
			role.setRoleId(Comm.GENERAL_MANAGER);
			admin.setRole(role);
			admin.setAdmPassword(MD5.md5crypt(admin.getAdmPassword()));
			adminDao.addGeneralAdmin(admin);
		}

	}

	/**
	 * 修改密码
	 * 
	 * @throws Exception
	 */
	@Override
	public void updateAdminPwd(String pwd, String newPwd, String confirmPwd)throws Exception {
		Admin ad = (Admin) ServletActionContext.getRequest().getSession().getAttribute(Comm.CURRENT_ADMIN);
		if (null == pwd || null == newPwd) {
			throw new Exception("密码或用户名为空!");
		}
		String oloPwd = MD5.md5crypt(pwd);
		String name = ad.getAdmName();
		pwd = ad.getAdmPassword();
		Integer id = ad.getAdmId();
		if (id == null) {
			throw new Exception(name + ",该用户不存在!");
		}
		ad.setAdmId(id);
		// 新密码加密
		String newPwd2 = MD5.md5crypt(newPwd);
		String oldPwd = ad.getAdmPassword();
		// 新密码和旧密码不能一样
		if (oloPwd.equals(oldPwd) && !newPwd2.equals(oldPwd)) {
			adminDao.updatePwd(newPwd2);
		} else {
			throw new Exception("新密码和原密码不一致");
		}
		return;
	}

}