package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.SafeDao;
import com.ltmcp.entity.Car;

public class SafeDaoHibImpl extends BaseDaoHibImpl implements SafeDao{

	@Override
	public List<Car> carInfor(ReportCondition condition) {
		StringBuilder hql=new StringBuilder("from Car car where 1=1  ");
		if(null != condition){
			if(AdminComm.getAdminPower() == 1 && condition.getComId() != null){
				hql.append(" and car.company.comId = "+condition.getComId());
			}else if(AdminComm.getAdminPower() != 1){
				hql.append(" and car.company.comId="+AdminComm.getComIdByAdmin());
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
	public Integer freInfor(Integer car, ReportCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.car.carId = " + car);
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		
		if(condition!=null){

			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				
				sb.append(" and s.seaTime > "+ "'"+condition.getBeginTime()+"'");
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and s.seaTime < "+ "'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Integer exrFreInfor(Integer car, ReportCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.car.carId = " + car);
		if(condition!=null){
			if(AdminComm.getAdminPower() != 1){
				//如果在condition中公司ID为空的情况采用以下方式获取公司ID
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			} else if(AdminComm.getAdminPower() == 1 && null!=condition.getComId()){
				sb.append(" and s.company.comId="+condition.getComId());
			}

			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				
				sb.append(" and s.seaTime > "+ "'"+condition.getBeginTime()+"'");
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and s.seaTime < "+ "'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			}
		}
		sb.append("and s.seaStatus = 2 ");
		
		return super.queryRowCount(sb.toString());
	}

}
