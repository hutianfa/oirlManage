package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.EmpowerDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Empower;
import com.ltmcp.service.EmpowerService;
import com.ltmcp.util.MD5;

public class EmpowerServiceImpl extends BaseServiceImpl implements EmpowerService{

	private EmpowerDao empowerDao;
	@Override
	public List<Empower> listService(Integer comid,String EmName,Integer currentPage) {
		return empowerDao.listDao(comid,EmName,currentPage);
	}

	@Override
	public String queryComNmByComid(Integer comId) {
		
		return empowerDao.queryComNm(comId);
	}
	
	@Override
	public Integer findEmTotal(String EmName) {
		return empowerDao.queryemTotal(EmName);
	}	
	
	@Override
	public void addService(Empower em) {
		Admin admin=(Admin) super.getSessionObject(Comm.CURRENT_ADMIN);
		em.setPowerCode(MD5.md5crypt(em.getPowerCode()));
		em.setCompany(AdminComm.getComIdByAdmin());
		em.setCreateName(admin.getAdmName());
		em.setStatus("0");
		empowerDao.addDao(em);
	}

	@Override
	public void delService(Integer poId) {
		empowerDao.delDao(poId);
	}

	@Override
	public void updateServcie(Empower em) {
		empowerDao.updateDao(em);
	}

	@Override
	public List<Company> queryCom() {
      return empowerDao.comList();
	}
	

	public EmpowerDao getEmpowerDao() {
		return empowerDao;
	}

	public void setEmpowerDao(EmpowerDao empowerDao) {
		this.empowerDao = empowerDao;
	}

	


}
