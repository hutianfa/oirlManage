package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.PowerDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;
import com.ltmcp.service.PowerService;

public class PowerServiceImpl  extends BaseServiceImpl  implements PowerService {

	private PowerDao powerDao;
	
	@Override
	public List<Power> searchPowers(Admin admin) {
		if(null!=admin){
			if(null==admin.getRole().getRoleId()){
				return null;
			}else{
				return powerDao.listPowers(admin);
			}
		}else{
			return new ArrayList<Power>();
		}
		
		
	}


	@Override
	public List<Power> searchPowerMenus(List<Power> powers) {
		List<Power> menus=new ArrayList<Power>();
		if(null!=powers){
			for(int i=0;i<powers.size();i++){
				if(Comm.POWER_SHOW==powers.get(i).getPoShow()){
					menus.add(powers.get(i));
				}
			}
		}
		return menus;
	}
	
	
	

	public PowerDao getPowerDao() {
		return powerDao;
	}
	
	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	}
}
