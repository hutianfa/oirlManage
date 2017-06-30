package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictDao {

	List<BasDict> listBasDicts();

	BasDict queryBasDictByDictId(BasDict basDict);

	/**
	 * 根据类型来查询字典
	 * @param type
	 * @return
	 */
	List<BasDict> queryBasDictByType(Integer type);
	
}
