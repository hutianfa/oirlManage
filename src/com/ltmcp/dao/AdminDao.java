package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.entity.Admin;

public interface AdminDao {
	/**
	 * 通过条件获得Admin
	 * @param admin
	 * @return
	 */
	public Admin queryAdmin(Admin admin);

	/**
	 * 查询普通管理员集合
	 * @param pagebean
	 * @param condition
	 * @return
	 */
	public List<Admin> foundGeneralManagerlist(PageBean pagebean,AdminCondition condition);

	/**
	 * 更改普通管理员状态
	 * @param id
	 */
	public void updateAdminStatus(Integer id);

	/**
	 * 添加普通管理员
	 * @param admin
	 * @throws Exception
	 */
	public void addGeneralAdmin(Admin admin) throws Exception;

	/**
	 * 根据条件查询普通管理员人数
	 * @param condition
	 * @return
	 */
	public Integer queryGeneralManagerCount(AdminCondition condition);
	
	/**
	 * 修改密码
	 * @param admin
	 * @return
	 */
	public void updatePwd(String newPwd);
	
	
	
}
