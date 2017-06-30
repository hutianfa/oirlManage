package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.dao.PowerDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public class PowerDaoHibImpl extends BaseDaoHibImpl implements PowerDao {

	@Override
	public List<Power> listPowers(Admin admin) {
		StringBuilder sb=new StringBuilder("from Power as p ");
		sb.append(" inner join fetch p.rolePowers as rp ");
		sb.append(" where rp.role.roleId = ?");
		return super.find(sb.toString(), admin.getRole().getRoleId());
	}

}
