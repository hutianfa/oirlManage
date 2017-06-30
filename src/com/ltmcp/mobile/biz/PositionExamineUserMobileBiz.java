package com.ltmcp.mobile.biz;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ltmcp.entity.Company;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.entity.Sealed;

public interface PositionExamineUserMobileBiz {
	
	PositionExamineUser getPositionExamineUser(String positionAccount,String positionPassword) throws UnsupportedEncodingException;
	
	List<Position> findPosList();
}
