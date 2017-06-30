package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.BenefitDao;
import com.ltmcp.entity.Car;

public class BenefitDaoHibImpl extends BaseDaoHibImpl implements BenefitDao{

	//获取所属公司的车辆
	@Override
	public List<Car> carInfor(ReportCondition condition) {
		StringBuilder hql=new StringBuilder("from Car car where 1=1  ");
		if(null != condition){
			if(AdminComm.getAdminPower() == 1 && condition.getComId() != null){
				hql.append(" and car.company.comId = "+condition.getComId());
			}else if(AdminComm.getAdminPower() != 1 ){
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

	//根据车辆查询油量相关
	public List<Object[]> findPetrol(String carFlapper,ReportCondition condition){
		StringBuilder sb=new StringBuilder("select sum(pe.petrol_total),sum(pe.petrol_loss),sum(pe.petrol_loss)/sum(pe.petrol_total) from Petrol pe where pe.carFlaper = '"+carFlapper+"'");
		sb.append("and pe.petrol_total  <> null");
		
		if(condition != null){//条件不为空
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				sb.append(" and pe.time > "+ "'"+condition.getBeginTime()+"'");
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and pe.time < "+ "'"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (pe.time between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}
		return (List<Object[]>)super.findList(sb.toString());
	}
}
