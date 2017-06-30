package com.ltmcp.dao.hibimpl;

import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.DWMReportDao;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;

public class DWMReportDaoHibImpl extends BaseDaoHibImpl implements DWMReportDao {

	@Override
	public List<Position> getPosi() {
		
		StringBuilder sb = new StringBuilder("from Position p ");
        sb.append(" where p.posStatus = "+Comm.POSITION_NORMAL);
        if(AdminComm.getAdminPower() !=1){
        	sb.append(" and p.company.comId="+AdminComm.getComIdByAdmin());
        }
        return super.findList(sb.toString());
	}

	@Override
	public List getPeop() {
		return null;
	}

	@Override
	public List getCar() {
		return null;
	}

	@Override
	public List<Sealed> d(String types) {
		return null;
	}

	@Override
	public List<Sealed> w(String types) {
		return null;
	}

	@Override
	public List<Sealed> m(String types) {
		return null;
	}

}
