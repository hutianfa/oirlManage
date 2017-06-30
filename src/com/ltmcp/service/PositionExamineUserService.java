package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.entity.PositionExamineUser;

public interface PositionExamineUserService {
	
	/**
	 * ��������
	 * @param user
	 */
	public void addPositionExamineUser(PositionExamineUser user) throws Exception;

	/**
	 * ��ȡlist����
	 * @param pageBean ��ҳ����
	 * @param condition  ����
	 * @return ��ҳ����
	 */
	public PageBean getPositionExamineUserList(PageBean pageBean,PositionExamineUserCondition condition);
	
	
	//public Integer getListCountByCondition(PositionExamineUserCondition condition);
	
}
