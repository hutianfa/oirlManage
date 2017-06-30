package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictDao {

	List<BasDict> listBasDicts();

	BasDict queryBasDictByDictId(BasDict basDict);

	/**
	 * ������������ѯ�ֵ�
	 * @param type
	 * @return
	 */
	List<BasDict> queryBasDictByType(Integer type);
	
}
