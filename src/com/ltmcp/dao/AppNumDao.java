package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.entity.DW;
import com.ltmcp.entity.Limt;

public interface AppNumDao  {
	//��ѯ��ǰapp�汾�����б�
	public List<Object> querydDw(PageBean pageBean,AppNumCondition condition);
	//��Ѱ�����������ҳ����
	public Integer queryDwTotal(PageBean pageBean, AppNumCondition condition);
	//��ѯ��ǰ�汾��
	public Limt queryLimitime();
}
