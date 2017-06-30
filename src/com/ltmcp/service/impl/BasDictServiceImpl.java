package com.ltmcp.service.impl;

import java.util.List;

import com.ltmcp.dao.BasDictDao;
import com.ltmcp.entity.BasDict;
import com.ltmcp.service.BasDictService;

public class BasDictServiceImpl  extends BaseServiceImpl  implements BasDictService {

	private BasDictDao basDictDao;

	public BasDictDao getBasDictDao() {
		return basDictDao;
	}

	public void setBasDictDao(BasDictDao basDictDao) {
		this.basDictDao = basDictDao;
	}

	@Override
	public List<BasDict> listBasDicts() {
		return basDictDao.listBasDicts();
	}

	@Override
	public BasDict getBasDictByDictId(BasDict basDict) {
		if(null!=basDict){
			if(null!=basDict.getDictId()){
				return basDictDao.queryBasDictByDictId(basDict);
			}
		}
		return null;
	}

	@Override
	public List<BasDict> searchBasDictByDictType(BasDict basDict) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
