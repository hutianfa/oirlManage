package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.EmpCondition;
import com.ltmcp.dao.EmpWaybillDao;
import com.ltmcp.entity.Freeze;

public class EmpWaybillDaoHibImpl extends BaseDaoHibImpl implements EmpWaybillDao{

	@Override
	public List<Freeze> findEmp(EmpCondition condition,PageBean pageBean) {
		 StringBuilder sb = new StringBuilder("from Freeze fre where fre.powCodName <> null");
		 
		 condition(condition, sb);
		 sb.append(" order by fre.freTime desc ");
		 return super.find(sb.toString(), pageBean.getCurentPage(), pageBean.getPageSize());
	}

	private void condition(EmpCondition emp, StringBuilder sb){
		if(emp!=null){
			if(AdminComm.getAdminPower() == 1 && null != emp.getComId()){
				sb.append(" and fre.company.comId="+emp.getComId());
			}else if(AdminComm.getAdminPower() != 1){
				sb.append(" and fre.company.comId="+AdminComm.getComIdByAdmin());
			}
			//如果区域为空
			if(emp.getAreaid() != null && null == emp.getPosid() &&  !"".equals(emp.getAreaid()) && "".equals(emp.getPosid())){
				sb.append(" and fre.position.areaid = "+emp.getAreaid());
			}
			
			if(emp.getAreaid() != null && null != emp.getPosid() && !"".equals(emp.getAreaid()) && !"".equals(emp.getPosid())){
				sb.append(" and fre.position.areaid = "+emp.getAreaid());
				sb.append(" and fre.position.posId = "+emp.getPosid());
			}			
			if(null != emp.getCarFlapper() && !"".equals(emp.getCarFlapper())){
				sb.append(" and fre.car.carFlapper like  '%"+emp.getCarFlapper()+"%'");
			}			

			if(emp.getEmpType() != null){
				sb.append("and fre.powerTips = "+emp.getEmpType());
			}
			if(null!=emp.getPerId()&&!"".equals(emp.getPerId())){
				sb.append(" and fre.person.perId ="+emp.getPerId());
			}
			if(!"".equals(emp.getBeginTime()) && emp.getBeginTime() != null && "".equals(emp.getEndTime()) || emp.getEndTime() == null){

				sb.append(" and fre.freTime > "+ "'"+emp.getBeginTime()+"'");
			}		
			if(!"".equals(emp.getEndTime()) && emp.getEndTime() != null && "".equals(emp.getBeginTime()) || emp.getBeginTime() == null){

				sb.append(" and fre.freTime < "+ "'"+emp.getEndTime()+"'");
			}			
			if(!"".equals(emp.getBeginTime()) && emp.getBeginTime() != null && !"".equals(emp.getEndTime()) && emp.getEndTime() != null){

				sb.append(" and (fre.freTime between "+"'"+emp.getBeginTime()+"'"+"and "+ "'"+emp.getEndTime()+"'"+")");
			}
		} else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and fre.company.comId="+AdminComm.getComIdByAdmin());
			}
		}
	}

	@Override
	public Integer findCount(EmpCondition condition) {
		StringBuilder sb = new StringBuilder("select count(fre.freId) from Freeze fre where fre.powCodName <> null");
		condition(condition, sb);
		return super.queryRowCount(sb.toString());
	}
}
