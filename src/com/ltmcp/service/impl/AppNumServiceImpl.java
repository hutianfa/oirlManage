package com.ltmcp.service.impl;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.dao.AppNumDao;
import com.ltmcp.service.AppNumService;

public class AppNumServiceImpl  extends BaseServiceImpl  implements AppNumService{

	private AppNumDao appNumDao;

	@Override
	public PageBean findDw(PageBean pageBean, AppNumCondition condition) {
		if(null==pageBean){
			pageBean=new PageBean();
		}
		Integer count = 0;
		String verison = appNumDao.queryLimitime().getAppNum();
		condition.setCarFlapper(verison);
		List<Object> list = null;
		try {
			list = appNumDao.querydDw(pageBean, condition);
			count = appNumDao.queryDwTotal(pageBean, condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回一个PageBean对象
		return PageBean.getPageBean(list, count,pageBean.getPageSize(),pageBean.getCurentPage());
	}

	
	
	
	
	public AppNumDao getAppNumDao() {
		return appNumDao;
	}

	public void setAppNumDao(AppNumDao appNumDao) {
		this.appNumDao = appNumDao;
	}

}
