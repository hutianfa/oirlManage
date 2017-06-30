package com.ltmcp.service.impl;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PetroCondition;
import com.ltmcp.dao.InputPetDao;
import com.ltmcp.entity.Petrol;
import com.ltmcp.service.InputPetServcie;

public class InputPetServcieImpl extends BaseServiceImpl implements InputPetServcie{

	private InputPetDao inputPetDao;
	
	@Override
	public PageBean queryPetrol(PageBean pageBean,PetroCondition petroCondition) {
		if(null==pageBean){
			pageBean=new PageBean();
		}
		List<Petrol> list = inputPetDao.petrolList(pageBean,petroCondition);
		
		int totalCount = inputPetDao.queryToatl(pageBean, petroCondition);
		
		return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}

	@Override
	public void save(Petrol petrol) {
		inputPetDao.insertPetrol(petrol);
	}

	@Override
	public String queryComNmByComid(Integer comId) {
		return inputPetDao.queryComNm(comId);
	}
	
	
	
	
	
	
	
	
	public InputPetDao getInputPetDao() {
		return inputPetDao;
	}

	public void setInputPetDao(InputPetDao inputPetDao) {
		this.inputPetDao = inputPetDao;
	}



	
}
