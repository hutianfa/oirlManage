package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PetroCondition;
import com.ltmcp.dao.InputPetDao;
import com.ltmcp.entity.Petrol;

public class InputPetDaoHibImpl extends BaseDaoHibImpl implements InputPetDao{

	@Override
	public List<Petrol> petrolList(PageBean pageBean,PetroCondition petroCondition) {
		StringBuffer sb = new StringBuffer("from Petrol p where p.com = "+AdminComm.getComIdByAdmin());
		condition(petroCondition,sb);
		sb.append("order by p.time desc");
		return super.find(sb.toString(), pageBean.getCurentPage(), pageBean.getPageSize());
	}

	@Override
	public void insertPetrol(Petrol petrol) {
		super.saveOrUpdate(petrol);		
	}

	@Override
	public Integer queryToatl(PageBean pageBean, PetroCondition petroCondition) {
		StringBuffer sb = new StringBuffer("select count(p.id) from Petrol p where p.com = "+AdminComm.getComIdByAdmin());
		condition(petroCondition,sb);
		return super.queryRowCount(sb.toString(), null);
	}
	
	
	private void condition(PetroCondition condition, StringBuffer sb) {
		if(condition != null){
			if(null != condition.getCarFlapper() && !"".equals(condition.getCarFlapper())){
				sb.append(" and p.carFlaper like '%"+condition.getCarFlapper()+"%'");
			}

			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				
				sb.append(" and p.time > '"+condition.getBeginTime()+"'");
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and p.time < '"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (p.time between "+ "'"+condition.getBeginTime()+"'and '"+condition.getEndTime()+"')");
			}
		}else{
			
		}
	}
	
	
	@Override
	public String queryComNm(Integer comId) {
		StringBuilder sb = new StringBuilder("select c.comName from Company c where c.comId ="+comId);
		return (String) super.query(sb.toString());
	}

}
