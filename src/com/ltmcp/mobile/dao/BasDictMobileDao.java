package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictMobileDao {
	
	public List<BasDict> findExceptionList();
	
	/**
	 * 查询油品的类型
	 * @return
	 */
	public List<BasDict> findOilPinList();
	
	/**
	 * 获取fix字典
	 */
	public List<BasDict> findFixList();
	
	/**
	 * 获取车辆去向列表
	 * @return
	 */
	
	public List<BasDict> findTagList();
	
	/**
	 * 授权字典
	 * @return
	 */
	
	public List<BasDict> findEmList();
}
