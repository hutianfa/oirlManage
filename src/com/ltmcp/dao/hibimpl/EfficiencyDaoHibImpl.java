package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.EfficiencyDao;
import com.ltmcp.entity.Car;

public class EfficiencyDaoHibImpl extends BaseDaoHibImpl implements EfficiencyDao{

	@Override
	public List<Car> carInfor(ReportCondition condition) {
		StringBuilder hql=new StringBuilder("from Car car where 1=1  ");
		if(null != condition){
			if(AdminComm.getAdminPower() != 1 && condition.getComId() == null){
				hql.append(" and car.company.comId = "+AdminComm.getComIdByAdmin());
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() != null){
				hql.append(" and car.company.comId = "+condition.getComId());
			}
			
			//如果carid不为空
			if(condition.getCarFlapper() != null && !"".equals(condition.getCarFlapper())){
				hql.append(" and car.carFlapper= '"+ condition.getCarFlapper()+"'");
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				hql.append(" and car.company.comId= "+AdminComm.getComIdByAdmin());
			}
		}
		hql.append(" and car.carStatus = " + Comm.CAR_NORMAL);
		return super.find(hql.toString());
	}

	@Override
	public List<Object> carWayNum(Integer car, ReportCondition condition) {
		StringBuilder sb=new StringBuilder("select distinct (s.freeze.freNumber) from Sealed s where s.car.carId = " + car );
		sb.append("and s.position.posName like '%油库%'");
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		
		if(condition!=null){

			if( !"".equals(condition.getBeginTime()) && condition.getBeginTime() != null &&  "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				
				sb.append(" and  s.freeze.freTime >" +"'"+condition.getEndTime()+"'");
			}
		
			if( !"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) ||  condition.getBeginTime() == null){
				sb.append(" and  s.freeze.freTime <" +"'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (s.freeze.freTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}	
		return (List<Object>)super.findList(sb.toString());
	}

	
	@Override
	public Double queryWay(String str) {
		
		StringBuilder sb=new StringBuilder("select sum(s.freeze.freTime - s.seaTime)/count(s.seaId) from Sealed s where s.freeze.freNumber = '" + str +"'");
		return (Double)super.query(sb.toString());
		
	}

	@Override
	public Integer carWayToTime(String str) {
		
		return null;
	}

	@Override
	public Integer carWayToNum(String str) {
		
		return null;
	}
}
