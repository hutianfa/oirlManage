package com.ltmcp.service;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.FixCondition;

public interface FixService {

	
	public PageBean searchFixByCondition(FixCondition condition,PageBean pageBean);
}
