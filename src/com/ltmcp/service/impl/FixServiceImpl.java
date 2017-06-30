package com.ltmcp.service.impl;

import java.util.List;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.FixCondition;
import com.ltmcp.dao.FixDao;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.service.FixService;

public class FixServiceImpl extends BaseServiceImpl implements FixService{

	private FixDao fixDao;
	@Override
	public PageBean searchFixByCondition(FixCondition condition,PageBean pageBean) {
		if(null==pageBean){
			pageBean=new PageBean();
		}
		//查找所有的运单
		List<FixedSeal> list= fixDao.findFixs(pageBean.getCurentPage(),pageBean.getPageSize(), condition);
		//返回运单条数
		Integer totalCount=fixDao.queryTotalCountByCondition(condition);
		//返回一个PageBean对象
		return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public FixDao getFixDao() {
		return fixDao;
	}
	public void setFixDao(FixDao fixDao) {
		this.fixDao = fixDao;
	}

	
	
	
	
	
	
	
	
	
}
