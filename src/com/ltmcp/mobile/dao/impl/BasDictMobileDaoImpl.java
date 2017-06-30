package com.ltmcp.mobile.dao.impl;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.BasDictDao;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.BasDict;
import com.ltmcp.mobile.dao.BasDictMobileDao;

public class BasDictMobileDaoImpl extends BaseDaoHibImpl implements BasDictMobileDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<BasDict> findExceptionList() {
		StringBuilder sb=new StringBuilder(" from BasDict b where b.dictType=? ");
		return super.findList(sb.toString(),Comm.BASDICT_EXCEPTION);
	}
	
	/**
	 * 查询油品的类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BasDict> findOilPinList(){
		StringBuilder sb = new StringBuilder(" from BasDict b where b.dictType=?");
		return super.findList(sb.toString(), Comm.BASDICT_OILPIN);
	}

	@Override
	public List<BasDict> findFixList() {
		StringBuilder sb = new StringBuilder(" from BasDict b where b.dictType=?");
		return super.findList(sb.toString(), Comm.BASDICT_FIX);
	}
	
	
	@Override
	public List<BasDict> findEmList() {
		StringBuilder sb = new StringBuilder(" from BasDict b where b.dictType=?");
		return super.findList(sb.toString(), Comm.BASDICT_EM);
	}
	
	@Override
	public List<BasDict> findTagList() {
		StringBuilder sb = new StringBuilder(" from BasDict b where b.dictType=?");
		return super.findList(sb.toString(), Comm.BASDICT_TAG);
	}

}