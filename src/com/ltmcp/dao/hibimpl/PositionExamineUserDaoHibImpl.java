package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.dao.PositionExamineUserDao;
import com.ltmcp.entity.PositionExamineUser;

public class PositionExamineUserDaoHibImpl extends BaseDaoHibImpl implements PositionExamineUserDao {

	@Override
	public void savePositionExamineUser(PositionExamineUser user) {
		super.insert(user);
	}

	@Override
	public void updatePositionExamineUser(PositionExamineUser user) {
		super.update(user);
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<PositionExamineUser> findPositionExamineUserList(Integer currentPage, Integer pageSize,PositionExamineUserCondition condition) {
		String hql="from PositionExamineUser u where 1=1 ";
		if(condition!=null){
			if(condition.getAccount()!=null && !"".equals(condition.getAccount())){
				hql+=" and u.positionAccount = '"+condition.getAccount()+"'";
			}
			if(condition.getName()!=null && !"".equals(condition.getName())){
				hql+=" and u.positionName = '"+condition.getAccount()+"'";
			}
		}
		if(AdminComm.getAdminPower() != 1){
			hql+=" and u.company.comId="+AdminComm.getComIdByAdmin();
		}
		return super.find(hql, currentPage, pageSize);
		
	}

	@Override
	public Integer queryListCountByCondition(PositionExamineUserCondition condition) {
		String hql="select count(u.id) from PositionExamineUser u where 1=1 ";
		if(condition!=null){
			if(condition.getAccount()!=null){
				hql+=" and u.positionAccount = '"+condition.getAccount()+"'";
			}
			if(condition.getName()!=null){
				hql+=" and u.positionName = '"+condition.getAccount()+"'";
			}
		}
		if(AdminComm.getAdminPower() != 1){
			hql+=" and u.company.comId="+AdminComm.getComIdByAdmin();
		}
		return super.queryRowCount(hql);
	}

}
