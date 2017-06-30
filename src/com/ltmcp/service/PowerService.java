package com.ltmcp.service;

import java.util.List;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface PowerService {
	
	/**
	 * 根据条件查询某个用户所拥有的权限
	 * @param admin
	 * @return
	 */
	public List<Power> searchPowers(Admin admin);

	/**
	 * 获得当前用户菜单信息
	 * @param powers
	 * @return
	 */
	public List<Power> searchPowerMenus(List<Power> powers);
	
}
