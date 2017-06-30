package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.dao.BasDictDao;
import com.ltmcp.entity.BasDict;

public class BasDictDaoHibImpl extends BaseDaoHibImpl implements BasDictDao{

	@Override
	public List<BasDict> listBasDicts() {
		String hql="from BasDict";
		return super.find(hql);
	}

	@Override
	public BasDict queryBasDictByDictId(BasDict basDict) {
		String hql="from BasDict where dictId=?";
		return (BasDict) super.query(hql, basDict.getDictId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BasDict> queryBasDictByType(Integer type) {
		StringBuilder sb=new StringBuilder("from BasDict ");
		sb.append(" where dictType =? ");
		return super.find(sb.toString(),type);
	}
	
	

}
