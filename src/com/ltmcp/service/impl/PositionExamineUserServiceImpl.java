package com.ltmcp.service.impl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.dao.PositionExamineUserDao;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.service.PositionExamineUserService;
import com.ltmcp.util.MD5;

public class PositionExamineUserServiceImpl extends BaseServiceImpl implements PositionExamineUserService{
	private PositionExamineUserDao positionExamineUserDao;
	
	@Override
	public void addPositionExamineUser(PositionExamineUser user)throws Exception {
		Company company=new Company();
		company.setComId(AdminComm.getComIdByAdmin());
		user.setPositionPassword(MD5.md5crypt(user.getPositionPassword()));
		user.setCompany(company);
		positionExamineUserDao.savePositionExamineUser(user);
	}

	public PositionExamineUserDao getPositionExamineUserDao() {
		return positionExamineUserDao;
	}

	public void setPositionExamineUserDao(
			PositionExamineUserDao positionExamineUserDao) {
		this.positionExamineUserDao = positionExamineUserDao;
	}

	@Override
	public PageBean getPositionExamineUserList(PageBean pageBean,PositionExamineUserCondition condition) {
		if(pageBean==null){
			pageBean=new PageBean();
		}
		//获取集合
		List<PositionExamineUser> list= positionExamineUserDao.findPositionExamineUserList(pageBean.getCurentPage(),pageBean.getPageSize(),condition);
		//获取总数
		Integer totalCount=positionExamineUserDao.queryListCountByCondition(condition);
		pageBean=PageBean.getPageBean(list, totalCount, pageBean.getPageSize(), pageBean.getCurentPage());
		return pageBean;
	}
	

}
