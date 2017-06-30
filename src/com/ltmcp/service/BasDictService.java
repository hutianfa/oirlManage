package com.ltmcp.service;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictService {
	
	/**
	 * 获取字典集合
	 * @return 集合
	 */
	public List<BasDict> listBasDicts();
	
	/**
	 * 根据ID查询一条字典信息
	 * @param basDict
	 * @return
	 */
	public BasDict getBasDictByDictId(BasDict basDict);
	
	/**
	 * 根据字典类型查询多条字典信息
	 * @param basDict
	 * @return
	 */
	public List<BasDict> searchBasDictByDictType(BasDict basDict);
	
	
	
	
}
