package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.DW;

public interface AppNumService {

	/**
	 * ����������ѯapp��λ�����ͳ����Ϣ 
	 */
	public PageBean findDw(PageBean pageBean, AppNumCondition condition);
}
