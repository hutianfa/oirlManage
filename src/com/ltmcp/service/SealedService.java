package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.Sealed;

public interface SealedService {

	/**
	 * 获取未完成的运单条数
	 * @return
	 */
	Integer getWayBillUnfinishedCount();

	/**
	 * 获取已经完成的运单条数
	 * @return
	 */
	Integer getWayBillCompletedCount();

	/**
	 * 查询运单列表
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchWaybillByCondition(SealedCondition condition,PageBean pageBean);
	
	/*
	 * 查询全部
	 */
	List<Sealed> findAllWaybillByCondition(SealedCondition condition);

	/**
	 * 根据ID查询运单信息
	 * @param id
	 * @return
	 */
	Sealed getWaybillById(Integer id);

	PageBean searchByPersonId(PageBean pageBean, Integer id);

	/**
	 * 获取出现异常运单的条数
	 * @return
	 */
	Integer getWayBillExcCount();
	
	/*
	 * 实时运单信息查询
	 */
	List<Sealed> findSeaAndFre(SealedCondition condition);
	
}
