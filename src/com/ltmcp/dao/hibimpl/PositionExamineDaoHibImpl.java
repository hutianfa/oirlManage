package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.dao.PositionExamineDao;
import com.ltmcp.entity.PositionExamine;

public class PositionExamineDaoHibImpl extends BaseDaoHibImpl implements PositionExamineDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionExamine> queryList(Integer currentPage,Integer pageSize, PositionExamineCondition condition) {
		StringBuilder sb = new StringBuilder("from PositionExamine pe where 1=1 ");
		if (condition != null) {
			if (condition.getName() != null) {
				sb.append(" and pe.name like '%" + condition.getName() + "%'");
			}
			if (condition.getState() != null) {
				sb.append(" and pe.state=" + condition.getState());
			}
		}
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and pe.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.find(sb.toString(), currentPage, pageSize);
	}

	@Override
	public Integer queryPositionExamineCoutByCondition(PositionExamineCondition condition) {
		StringBuilder sb = new StringBuilder(" select count(pe.id) from PositionExamine pe where 1=1 ");
		if (condition != null) {
			sb.append(" and pe.name like '%" + condition.getName() + "%'");
		}
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and pe.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.queryRowCount(sb.toString());
	}

	@Override
	public void deletePositionExamine(Integer id) {
		StringBuilder sb = new StringBuilder(" update PositionExamine pe set pe.state=2");
		sb.append("	where pe.id=? ");
		super.updateByHql(sb.toString(), id);
	}

	@Override
	public PositionExamine queryPositionExamine(PositionExamine positionExamine) {
		StringBuilder sb = new StringBuilder(" from PositionExamine pe where pe.id=? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and pe.company.comId = "+AdminComm.getComIdByAdmin());
		}
		return (PositionExamine) super.query(sb.toString(),positionExamine.getId());
	}

	/**
	 * ¸üÐÂ×´Ì¬
	 */
	@Override
	public Integer updatePositionState(Integer id) {
		StringBuilder sb = new StringBuilder(" update PositionExamine pe set pe.state=0");
		sb.append("	where pe.id=? ");
		return (Integer) super.returnByUpdate(sb.toString(), id);
	}

}
