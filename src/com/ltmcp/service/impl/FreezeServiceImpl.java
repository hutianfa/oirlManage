package com.ltmcp.service.impl;

import com.ltmcp.dao.FreezeDao;
import com.ltmcp.entity.Freeze;
import com.ltmcp.service.FreezeService;

public class FreezeServiceImpl  extends BaseServiceImpl  implements FreezeService {
	private FreezeDao freezeDao;
	
	@Override
	public Freeze getFreezeBySealedId(Integer seaId) {
		if(null==seaId){
			return new Freeze();
		}
		Freeze f=freezeDao.queryFreezeBySealedId(seaId);
		return f;
	}

	public FreezeDao getFreezeDao() {
		return freezeDao;
	}

	public void setFreezeDao(FreezeDao freezeDao) {
		this.freezeDao = freezeDao;
	}

}
