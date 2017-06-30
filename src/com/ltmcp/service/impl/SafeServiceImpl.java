package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.SafeDao;
import com.ltmcp.entity.Car;
import com.ltmcp.service.SafeService;
public class SafeServiceImpl extends BaseServiceImpl implements SafeService {

	private SafeDao safeDao;
		
	@Override
	public List<Map<String, Object>> infor2(ReportCondition condition) {
		
		List<Car> car = safeDao.carInfor(condition);
		
		List<Map<String, Object>> li = new ArrayList<Map<String,Object>>();
		
		for(int i = 0 ;i<car.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("car", car.get(i).getCarFlapper());
			map.put("sjName", "");
			map.put("tel", "");
			map.put("com", car.get(i).getCompany().getComName());
			//获取总的解封数
			Integer freTotal = safeDao.freInfor(car.get(i).getCarId(), condition);
			//获取总的异常数
			Integer exrTotal = safeDao.exrFreInfor(car.get(i).getCarId(), condition);
			
			map.put("freTotal", freTotal);
			
			map.put("exrTotal", exrTotal);
			
			if(freTotal != 0){
				map.put("tl", (double)exrTotal/freTotal);
			}else if(freTotal == 0 || exrTotal == 0){
				map.put("tl", 0.0);
			}

				
			li.add(map);
			
			
		}
		
		return li;
	}

	
	
	
	
	

	public SafeDao getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(SafeDao safeDao) {
		this.safeDao = safeDao;
	}


	
	
}
