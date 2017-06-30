package com.ltmcp.mobile.dao.impl;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.SealedMobileDao;

public class SealedMobileDaoImpl extends BaseDaoHibImpl implements SealedMobileDao{

	@Override
	public void saveLockInfo(Sealed sealed) throws Exception{
		super.saveOrUpdate(sealed);
	}

	@Override
	public Sealed querySealedByCodeIdAndCarId(Integer codeId, Integer carId,Integer wAYBILL_UNFINISHED,Integer comId) {
		StringBuilder sb=new StringBuilder("from Sealed  ");
		sb.append(" where dimensionalBarCode.id=? ");
		sb.append(" and car.carId=? ");
		sb.append(" and seaStatus=? ");
		sb.append(" and company.comId=? ");
		return (Sealed) super.query(sb.toString(),codeId,carId,wAYBILL_UNFINISHED,comId);
	}

	@Override
	public void updateStatus(Integer seaId, Integer status) {
		StringBuilder sb=new StringBuilder("update Sealed s set s.seaStatus=? where s.seaId=? ");
		super.updateByHql(sb.toString(),status,seaId);
		
	}

	@Override
	public void updateFreezeId(Integer seaId, Integer freId) {
		StringBuilder sb=new StringBuilder("update Sealed s set s.freeze.freId=? where s.seaId=?");
		super.updateByHql(sb.toString(),freId,seaId);
	}



}
