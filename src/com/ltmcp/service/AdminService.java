package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface AdminService {
	
	/**
	 * 根据密码和用户名获得用户信息
	 * @param admin
	 * @return
	 */
	public Admin getAdmin(Admin admin);

	/**
	 * 保存当前登录的信息到session中
	 * @param admin
	 * @param powers
	 * @param menus
	 */
	public void saveAdminInfoToSession(Admin admin, List<Power> powers, List<Power> menus);

	/**
	 * 获得list集合
	 * @param pageBean
	 * @param condition
	 * @return
	 */
	public PageBean searchList(PageBean pageBean,AdminCondition condition);

	/**
	 * 删除普通管理员
	 * @param id
	 */
	public void delGeneralmanager(Integer id) throws Exception;

	/**
	 * 添加普通管理员
	 * @param admin
	 */
	public void addAdmin(Admin admin,AdminCondition condition) throws Exception;
	
	/**
	 * 修改密码
	 * @param id
	 * @throws Exception
	 */
	
	
	public void updateAdminPwd(String pwd, String newPwd, String confirmPwd) throws Exception;

}
