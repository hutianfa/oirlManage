package com.ltmcp.mobile.dao.impl;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.mobile.dao.FixSeaFreMobileDao;

public class FixSeaFreMobileDaoImpl extends BaseDaoHibImpl implements FixSeaFreMobileDao{

	@Override
	public void saveLockFix(FixedSeal sea) throws Exception {		
		super.saveOrUpdate(sea);		
	}

	@Override
	public void updateFreCodeSta(Integer codeId) {
		StringBuilder sb = new StringBuilder(" update DimensionalBarCode set freeze_status=? , unfreeze_status=?");
		sb.append(" where id=?  ");
		super.updateByHql(sb.toString(), Comm.TWO_CODE_FIX_CENTER,Comm.TWO_CODE_FIX_CENTER , codeId);
	}
	
	@Override
	public Integer queryIdByFixCodeUnstatus(String code, Integer Status) {
		StringBuilder sb = new StringBuilder("select id from DimensionalBarCode ");
		sb.append(" where unfreeze_content=? and unfreeze_status =? ");
		return (Integer) super.queryUniqueObject(sb.toString(), code, Status);
	}
	
	@Override
	public void updateUnFreCodeSta(Integer codeId) {
		StringBuilder sb = new StringBuilder(" update DimensionalBarCode set Unfreeze_status=? , freeze_status=? ");
		sb.append(" where id=?  ");
		super.updateByHql(sb.toString(), Comm.TWO_CODE_FIX_LOSS,Comm.TWO_CODE_FIX_LOSS, codeId);
	}
	
	@Override
	public void saveUnLockFix(FixedSeal sea) throws Exception {
		super.saveOrUpdate(sea);
		super.getSession().beginTransaction().commit();
	}

	@Override
	public String queryFixSeaCode(String code) {
		StringBuilder sb = new StringBuilder("select freeze_content from DimensionalBarCode ");
		sb.append(" where unfreeze_content=? and unfreeze_status = 0");
		return (String) super.queryUniqueObject(sb.toString(), code);
	}


	@Override
	public Integer queryFixId(String code) {
		StringBuilder sb = new StringBuilder("select id from FixedSeal fix ");
		sb.append(" where  fix.seaCode = ?");
		return (Integer) super.queryUniqueObject(sb.toString(), code);
	}

	@Override
	public String queryCarByQRCode(String code) {
		StringBuilder sb=new StringBuilder(" select plateNumber from FixedSeal  where seaCode = ? ");
		return (String) super.queryUniqueObject(sb.toString(),code);
	}

	@Override
	public String queryCodeByQRCode(String code) {
		StringBuilder sb = new StringBuilder("select freeze_content from DimensionalBarCode ");
		sb.append(" where unfreeze_content=? and unfreeze_status = 11");
		return (String) super.queryUniqueObject(sb.toString(), code);
	}

	@Override
	public FixedSeal queryFixedSeal(Integer id) {
		return (FixedSeal) super.query(FixedSeal.class, id);
	}



}
