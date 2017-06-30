package com.ltmcp.dao.hibimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.dao.AdminDao;
import com.ltmcp.entity.Admin;

public class AdminDaoHibImpl extends BaseDaoHibImpl implements AdminDao {

	@Override
	public Admin queryAdmin(Admin admin) {
		String hql = "from Admin ad  where  ad.admName=?  and  ad.admPassword=?  and ad.admStatus=? ";
		return (Admin) super.query(hql, admin.getAdmName(),admin.getAdmPassword(), Comm.ADMIN_NORMAL);
	}

	@Override
	public List<Admin> foundGeneralManagerlist(PageBean pagebean,AdminCondition condition) {
		StringBuilder sb = new StringBuilder("from Admin ad where 1=1 ");
		if (null != condition) {
			if (null != condition.getName() && !"".equals(condition.getName())) {
				sb.append(" and ad.admName like '%" + condition.getName()+ "%'");
			}
			if (null != condition.getSex()) {
				sb.append(" and ad.admSex =  " + condition.getSex());
			}
			if (null != condition.getStatus()) {
				sb.append(" and ad.admStatus= " + condition.getStatus());
			}
			if(AdminComm.getAdminPower() == 1 && condition.getComId() !=null){
				sb.append(" and ad.company.comId="+condition.getComId());
			}
		} else if(AdminComm.getAdminPower() != 1){
			sb.append(" and ad.company.comId="+AdminComm.getComIdByAdmin());
		}

		sb.append(" and ad.role.roleId=? ");
		sb.append(" order by ad.admCreateDate desc ");
		return super.find(sb.toString(), pagebean.getCurentPage(),pagebean.getPageSize(),Comm.GENERAL_MANAGER);
	}

	@Override
	public void updateAdminStatus(Integer id) {
		StringBuilder sb = new StringBuilder("update Admin ad set ad.admStatus=? ");
		sb.append(" where ad.role.roleId=? ");
		sb.append(" and ad.admStatus=? ");
		sb.append(" and ad.admId=? ");
		
		if(AdminComm.getAdminPower() != 1){
			sb.append(" where ad.company.comId= "+ AdminComm.getComIdByAdmin());
		}

		super.updateByHql(sb.toString(), Comm.ADMIN_LOSS,Comm.GENERAL_MANAGER,Comm.ADMIN_NORMAL, id);
	}

	@Override
	public void addGeneralAdmin(Admin admin) throws Exception {
		super.insert(admin);
	}

	@Override
	public Integer queryGeneralManagerCount(AdminCondition condition) {
		StringBuilder sb = new StringBuilder("select count(ad.admId) from Admin ad where 1=1 ");
		if (null != condition) {
			if (null != condition.getName() && !"".equals(condition.getName())) {
				sb.append(" and ad.admName like '%" + condition.getName()
						+ "%'");
			}
			if (null != condition.getSex()) {
				sb.append(" and ad.admSex =  " + condition.getSex());
			}
			
			if(AdminComm.getAdminPower() == 1 && condition.getComId()!=null){
				sb.append(" and ad.company.comId= "+ condition.getComId());
			}
		} else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and ad.company.comId= "+AdminComm.getComIdByAdmin());
			}
		}
		sb.append(" and ad.role.roleId=? ");
		return super.queryRowCount(sb.toString(),Comm.GENERAL_MANAGER);
	}

	/**
	 * 超级管理员修改密码
	 */
	@Override
	public void updatePwd(String newPwd) {
		StringBuilder sb = new StringBuilder(" update Admin ad set ad.admPassword=? where ad.admId=? and ad.admStatus=?");
		Admin ad = (Admin) ServletActionContext.getRequest().getSession().getAttribute(Comm.CURRENT_ADMIN);
		super.updateByHql(sb.toString(), newPwd, ad.getAdmId(),ad.getAdmStatus());
	}
}