package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.EmpowerDao;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Empower;
import com.ltmcp.util.MD5;

public class EmpowerDaoHibImpl extends BaseDaoHibImpl implements EmpowerDao{


	@Override
	public List<Empower> listDao(Integer id,String EmName,Integer currentPage) {
		StringBuffer sb = new StringBuffer("from  Empower em where company ="+id);
		if(EmName != null && !"".equals(EmName)){
			sb.append("and em.name like  '%"+ EmName +"%'");
		}
		return find(sb.toString(), 1, Comm.SYSTEM_PERSONSIZE);
	}
	
	
	@Override
	public String queryComNm(Integer comId) {
		StringBuilder sb = new StringBuilder("select c.comName from Company c where c.comId ="+comId);
		return (String) super.query(sb.toString());
	}
	
	
	@Override
	public Integer queryemTotal(String EmName) {
		StringBuilder sb=new StringBuilder("select count(em.id) from Empower em where company = "+AdminComm.getComIdByAdmin());
		
		if(EmName != null && !"".equals(EmName)){
			sb.append("and em.name like  '%"+ EmName +"%'");
		}
		
		return super.queryRowCount(sb.toString());
	}
	
	@Override
	public void addDao(Empower em) {
		super.saveOrUpdate(em);
	}

	@Override
	public void delDao(Integer poId) {
		Empower m =(Empower) super.query(Empower.class, poId);
		super.delete(m);
	}

	@Override
	public void updateDao(Empower em) {
		Empower e =(Empower) super.query(Empower.class, em.getId());
		
		if(em.getCompany() != null){
			e.setCompany(em.getCompany());
		}
		if(em.getName() !=null ){
			e.setName(em.getName());
		}
		if(em.getPowerCode() !=null){
			e.setPowerCode(MD5.md5crypt(em.getPowerCode()));
		}
		super.saveOrUpdate(e);
	}

	@Override
	public List<Company> comList() {
		StringBuilder sb = new StringBuilder("from Company c ");
        return super.findList(sb.toString());
	}
}
