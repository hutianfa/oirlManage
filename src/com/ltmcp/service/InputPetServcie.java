package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PetroCondition;
import com.ltmcp.entity.Petrol;

public interface InputPetServcie {

	public PageBean queryPetrol(PageBean pageBean,PetroCondition petroCondition);

	public void save(Petrol petrol);
	
	public String queryComNmByComid(Integer comId);
}
