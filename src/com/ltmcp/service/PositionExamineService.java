package com.ltmcp.service;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.entity.PositionExamine;

public interface PositionExamineService {
	/**
	 * 获取集合
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	public PageBean getList(PositionExamineCondition condition,PageBean pageBean);

	/**
	 * 删除
	 * @param positionExamine
	 * @throws Exception
	 */
	public void delPositionExamineService(Integer id) throws Exception;

	/**
	 * 获取单个
	 * @param positionExamine
	 * @return
	 */
	public PositionExamine getPositionExamine(Integer id );
	
	
	public Integer updatePosState(Integer id) throws Exception;
	
}
