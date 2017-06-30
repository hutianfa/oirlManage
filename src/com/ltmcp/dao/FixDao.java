package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.FixCondition;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.entity.Sealed;

public interface FixDao {

	
	//根据条件获取固定运单
	List<FixedSeal> findFixs(Integer currentPage,Integer pageSize,FixCondition condition);
	//根据条件获取固定运单总数
	Integer queryTotalCountByCondition(FixCondition condition);
}
