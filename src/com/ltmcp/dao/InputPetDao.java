package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PetroCondition;
import com.ltmcp.entity.Petrol;

public interface InputPetDao {

	public List<Petrol> petrolList(PageBean pageBean,PetroCondition petroCondition);
	
	public void insertPetrol(Petrol petrol);
	
	public Integer queryToatl(PageBean pageBean,PetroCondition petroCondition);
	
	public String queryComNm(Integer comId);
}
