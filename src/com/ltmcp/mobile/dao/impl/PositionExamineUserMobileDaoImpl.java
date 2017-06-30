package com.ltmcp.mobile.dao.impl;

import java.util.List;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.PositionExamineUserMobileDao;

public class PositionExamineUserMobileDaoImpl extends BaseDaoHibImpl implements PositionExamineUserMobileDao{

	@Override
	public PositionExamineUser queryUser(String positionAccount,String positionPassword) {
		String hql=" from PositionExamineUser u left join fetch u.company where u.positionAccount=? and u.positionPassword=? ";
		return (PositionExamineUser) super.query(hql, positionAccount,positionPassword);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> queryList() {
		String hql="from Position p where 1=1";
		
		return super.findList(hql);
	}

}
