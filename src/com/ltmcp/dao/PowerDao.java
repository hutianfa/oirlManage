package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface PowerDao {

	/**
	 * 根据管理员的角色查询权限
	 * @param admin 管理员
	 * @return
	 */
	List<Power> listPowers(Admin admin);

}
