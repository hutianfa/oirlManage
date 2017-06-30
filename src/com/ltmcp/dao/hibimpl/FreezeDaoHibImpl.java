package com.ltmcp.dao.hibimpl;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.dao.FreezeDao;
import com.ltmcp.entity.Freeze;

public class FreezeDaoHibImpl extends BaseDaoHibImpl implements FreezeDao{

	@Override
	public Freeze queryFreezeBySealedId(Integer seaId) {
		System.out.println("������ƽ������ݿ��ѯ����");
		StringBuilder sb=new StringBuilder(" from Freeze f ");
		sb.append(" where f.sealed.seaId=? ");
		if(AdminComm.getAdminPower() != 1){
			System.out.println("������ƽ������ݿ��ѯ����1");
			sb.append(" and f.company.comId= "+AdminComm.getComIdByAdmin() );
		}
		return (Freeze) super.query(sb.toString(),seaId);
	}

}
