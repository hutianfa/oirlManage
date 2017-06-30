package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.entity.PositionExamineUser;

/**
 * λ���ϴ� �û�DAO
 * @author Administrator
 *
 */
public interface PositionExamineUserDao {
	/**
	 * ���������
	 * @param user
	 */
	public void savePositionExamineUser(PositionExamineUser user);
	
	/**
	 * ����λ����Ϣ�ϴ���
	 * @param user
	 */
	public void updatePositionExamineUser(PositionExamineUser user);
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public List<PositionExamineUser> findPositionExamineUserList(Integer currentPage,Integer pageSize,PositionExamineUserCondition condition);

	public Integer queryListCountByCondition(PositionExamineUserCondition condition);
	
	
}
