package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Company;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.entity.Sealed;

public interface PositionExamineUserMobileDao {

	PositionExamineUser queryUser(String positionAccount,String positionPassword);
	
	/**
	 * 获取位置信息
	 * @param posId
	 * @return
	 */
	List<Position> queryList();
}
