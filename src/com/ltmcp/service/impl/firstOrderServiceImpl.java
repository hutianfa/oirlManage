package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.dao.firstOrderDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.first_order;
import com.ltmcp.service.CarService;
import com.ltmcp.service.firstOrderService;

public class firstOrderServiceImpl extends BaseServiceImpl implements firstOrderService {
	private firstOrderDao  firstOrderDao;
	
	
	@Override
	public boolean checkOrderStatus(Integer id, Integer status) {
		return firstOrderDao.checkOrderStatus(id, status);
	}
	@Override
	public List<first_order> queryFirst_order(int comid) {
		return firstOrderDao.findFirst_order(comid);
	}
	@Override
	public void addFirst_order(first_order first_order) throws Exception {
		firstOrderDao.insertFirst_order(first_order);
	}
	@Override
	public void orderModify(Integer id, Integer num) {
		firstOrderDao.orderModify(id,num);
	}
	
	
	public firstOrderDao getFirstOrderDao() {
		return firstOrderDao;
	}
	public void setFirstOrderDao(firstOrderDao firstOrderDao) {
		this.firstOrderDao = firstOrderDao;
	}
	
	
}
