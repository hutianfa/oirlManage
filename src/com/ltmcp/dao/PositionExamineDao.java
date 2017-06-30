package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.entity.PositionExamine;

public interface PositionExamineDao {
	/**
	 * ��ѯ����
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<PositionExamine> queryList(Integer currentPage,Integer pageSize, PositionExamineCondition condition);

	/**
	 * ��ѯposition�������
	 * @param condition
	 * @return
	 */
	public Integer queryPositionExamineCoutByCondition(PositionExamineCondition condition);

	/**
	 * ɾ����Ϣ
	 * @param positionExamine
	 */
	public void deletePositionExamine(Integer id);

	/**
	 * ��ѯ���������Ϣ
	 * @param positionExamine
	 * @return
	 */
	public PositionExamine queryPositionExamine(PositionExamine positionExamine);
	
	/**
	 * �������״̬
	 * @param positionExamine
	 * @return
	 */
	public Integer updatePositionState(Integer id);
	
}
