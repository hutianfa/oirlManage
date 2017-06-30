package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.entity.PositionExamineUser;

public interface PositionExamineUserService {
	
	/**
	 * 审核者添加
	 * @param user
	 */
	public void addPositionExamineUser(PositionExamineUser user) throws Exception;

	/**
	 * 获取list集合
	 * @param pageBean 分页对象
	 * @param condition  条件
	 * @return 分页对象
	 */
	public PageBean getPositionExamineUserList(PageBean pageBean,PositionExamineUserCondition condition);
	
	
	//public Integer getListCountByCondition(PositionExamineUserCondition condition);
	
}
