package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.DW;

public interface AppNumService {

	/**
	 * 根据条件查询app定位的相关统计信息 
	 */
	public PageBean findDw(PageBean pageBean, AppNumCondition condition);
}
