package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.entity.PositionExamine;

public interface PositionExamineDao {
	/**
	 * 查询集合
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<PositionExamine> queryList(Integer currentPage,Integer pageSize, PositionExamineCondition condition);

	/**
	 * 查询position审核条数
	 * @param condition
	 * @return
	 */
	public Integer queryPositionExamineCoutByCondition(PositionExamineCondition condition);

	/**
	 * 删除信息
	 * @param positionExamine
	 */
	public void deletePositionExamine(Integer id);

	/**
	 * 查询单个审核信息
	 * @param positionExamine
	 * @return
	 */
	public PositionExamine queryPositionExamine(PositionExamine positionExamine);
	
	/**
	 * 更新审核状态
	 * @param positionExamine
	 * @return
	 */
	public Integer updatePositionState(Integer id);
	
}
