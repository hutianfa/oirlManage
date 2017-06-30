package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.entity.PositionExamineUser;

/**
 * 位置上传 用户DAO
 * @author Administrator
 *
 */
public interface PositionExamineUserDao {
	/**
	 * 保存审核者
	 * @param user
	 */
	public void savePositionExamineUser(PositionExamineUser user);
	
	/**
	 * 更新位置信息上传者
	 * @param user
	 */
	public void updatePositionExamineUser(PositionExamineUser user);
	
	/**
	 * 获取集合
	 * @return
	 */
	public List<PositionExamineUser> findPositionExamineUserList(Integer currentPage,Integer pageSize,PositionExamineUserCondition condition);

	public Integer queryListCountByCondition(PositionExamineUserCondition condition);
	
	
}
