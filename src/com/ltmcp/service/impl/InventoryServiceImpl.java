package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.dao.InventoryDao;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.dao.firstOrderDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.service.CarService;
import com.ltmcp.service.InventoryService;
import com.ltmcp.service.firstOrderService;

public class InventoryServiceImpl extends BaseServiceImpl implements InventoryService 
{
	private InventoryDao  inventoryDao;

	public InventoryDao getInventoryDao() 
	{
		return inventoryDao;
	}
	public void setInventoryDao(InventoryDao inventoryDao) 
	{
		this.inventoryDao = inventoryDao;
	}
	@Override
	public void query_The_Inventory() 
	{
		inventoryDao.query_The_Inventory();	
	}
	@Override
	public List<shoufa_person> findCompanyPersonNameByComid(Integer comid)
	{
		
		return inventoryDao.findCompanyPersonNameByComid(comid);
	}
	@Override
	public List<Area> serachAreaAddress(Integer comid)
	{
		// TODO Auto-generated method stub
		return inventoryDao.serachAreaAddress(comid);
	}
	@Override
	public int countByArea_name(String area_name)
	{
		// TODO Auto-generated method stub
		return inventoryDao.countByArea_name(area_name);
	}
	@Override
	public int countByArea_id(Integer area_id) {
		// TODO Auto-generated method stub
		return inventoryDao.countByArea_id(area_id);
	}
	@Override
	public List<Map<String, Object>> periodNormallyEndsNumber(int areaid) {
		// TODO Auto-generated method stub
		return inventoryDao.periodNormallyEndsNumber(areaid);
	}
	@Override
	public List<first_order> companyContByFirstOrder(int comid) {
		// TODO Auto-generated method stub
		return inventoryDao.companyContByFirstOrder(comid);
	}
	@Override
	public List<Map<String, Object>> sealNotRegistered(int areaid) {
		// TODO Auto-generated method stub
		return inventoryDao.sealNotRegistered(areaid);
	}
	@Override
	public List<Map<String, Object>> unlockNotsealed(int areaid) {
		// TODO Auto-generated method stub
		return inventoryDao.unlockNotsealed(areaid);
	}
	@Override
	public List<Map<String, Object>> serachTotalPositionByArea(int areaid) {
		// TODO Auto-generated method stub
		return inventoryDao.serachTotalPositionByArea(areaid);
	}
	@Override
	public List<shoufa_person> searchAreaNameByAreaid(int areaid) {
		// TODO Auto-generated method stub
		return inventoryDao.searchAreaNameByAreaid(areaid);
	}
	@Override
	public List<Area> findAreaIdByComId(int comid) {
		// TODO Auto-generated method stub
		return inventoryDao.findAreaIdByComId(comid);
	}
	
	
	
//	@Override
//	public boolean checkOrderStatus(Integer id, Integer status) {
//		return firstOrderDao.checkOrderStatus(id, status);
//	}
//	@Override
//	public List<first_order> queryFirst_order() {
//		return firstOrderDao.findFirst_order();
//	}
//	@Override
//	public void addFirst_order(first_order first_order) throws Exception {
//		firstOrderDao.insertFirst_order(first_order);
//	}
//	@Override
//	public void orderModify(Integer id, Integer num) {
//		firstOrderDao.orderModify(id,num);
//	}
//	
//	
//	public firstOrderDao getFirstOrderDao() {
//		return firstOrderDao;
//	}
//	public void setFirstOrderDao(firstOrderDao firstOrderDao) {
//		this.firstOrderDao = firstOrderDao;
//	}
	
	
}
