package com.ltmcp.service.impl;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.dao.PositionExamineDao;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.service.PositionExamineService;

public class PositionExamineServiceImpl extends BaseServiceImpl implements
		PositionExamineService {

	private PositionExamineDao positionExamineDao;

	@Override
	public PageBean getList(PositionExamineCondition condition,
			PageBean pageBean) {
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		List<PositionExamine> list = null;
		try {
			list = positionExamineDao.queryList(pageBean.getCurentPage(),pageBean.getPageSize(), condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer totalCount = positionExamineDao.queryPositionExamineCoutByCondition(condition);
		return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),
				pageBean.getCurentPage());
	}

	@Override
	public void delPositionExamineService(Integer id)
			throws Exception {
		if (null == id) {
			throw new NullPointerException();
		}
		positionExamineDao.deletePositionExamine(id);
	}

	@Override
	public PositionExamine getPositionExamine(Integer id) {
		PositionExamine positionExamine = new PositionExamine();
		if (null == id) {
			return positionExamine;
		}
		positionExamine.setId(id);
		return positionExamineDao.queryPositionExamine(positionExamine);
	}

	// -------------------------------属性的方法----------------------------------------------
	public PositionExamineDao getPositionExamineDao() {
		return positionExamineDao;
	}

	public void setPositionExamineDao(PositionExamineDao positionExamineDao) {
		this.positionExamineDao = positionExamineDao;
	}

	@Override
	public Integer updatePosState(Integer id) throws Exception {
		if (null == id) {
			throw new NullPointerException();
		}
	return positionExamineDao.updatePositionState(id);

	}

}
