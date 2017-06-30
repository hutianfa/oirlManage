package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.FixCondition;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.entity.Sealed;

public interface FixDao {

	
	//����������ȡ�̶��˵�
	List<FixedSeal> findFixs(Integer currentPage,Integer pageSize,FixCondition condition);
	//����������ȡ�̶��˵�����
	Integer queryTotalCountByCondition(FixCondition condition);
}
