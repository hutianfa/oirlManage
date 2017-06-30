package com.ltmcp.dao.hibimpl;

import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.condition.FixCondition;
import com.ltmcp.dao.FixDao;
import com.ltmcp.entity.FixedSeal;

public class FixDaoHibImpl extends BaseDaoHibImpl implements FixDao{

	@Override
	public List<FixedSeal> findFixs(Integer currentPage, Integer pageSize,FixCondition condition) {
		StringBuilder sb=new StringBuilder("from FixedSeal fix where 1=1 ");
		condition(condition,sb);
		sb.append(" order by fix.seaTime desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}

	@Override
	public Integer queryTotalCountByCondition(FixCondition condition) {
		StringBuilder sb=new StringBuilder("select count(fix.id) from FixedSeal fix where 1=1 ");
		condition(condition,sb);
		return super.queryRowCount(sb.toString());
	}
	
	
	
	
	
	
	private void condition(FixCondition condition, StringBuilder sb) {
		
		if(condition != null){
			
			if(AdminComm.getAdminPower() == 1 && null != condition.getComId()){
				sb.append(" and fix.company= "+ condition.getComId());
			}else if(AdminComm.getAdminPower() != 1){
				sb.append(" and fix.company= "+ AdminComm.getComIdByAdmin());
			}
			//对站点分区
			if(null != condition.getPosId()){
				sb.append(" and fix.seaPosi = "+condition.getPosId()+"");
			}
			if(null != condition.getAreaid() && !"".equals(condition.getAreaid())){
				sb.append(" and fix.areaid = "+condition.getAreaid()+"");
			}
			
			if(null != condition.getPerName() && !"".equals(condition.getPerName())){
				sb.append(" and fix.seaName like '%"+condition.getPerName()+"%'  or  fix.freName like '%"+condition.getPerName()+"%'");
			}
			if(null!=condition.getStatus()){
				sb.append(" and fix.fixStatus= "+condition.getStatus());
			}
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null || "".equals(condition.getEndTime()) && condition.getEndTime() == null){
				sb.append(" and fix.seaTime > "+ "'"+condition.getBeginTime()+"'");
			}		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and fix.seaTime < "+ "'"+condition.getEndTime()+"'");
			}			
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && !"".equals(condition.getBeginTime()) && condition.getBeginTime() != null){
				sb.append(" and (fix.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and fix.company ="+AdminComm.getComIdByAdmin());
			}
		}
	}

}
