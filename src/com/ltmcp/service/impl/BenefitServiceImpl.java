package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.BenefitDao;
import com.ltmcp.entity.Car;
import com.ltmcp.service.BenefitService;

public class BenefitServiceImpl extends BaseServiceImpl implements BenefitService{

	private BenefitDao benefitDao;
	
	@Override
	public List<Map<String, Object>> info1(ReportCondition condition) {
		
		List<Car> car = benefitDao.carInfor(condition);
		
		List<Map<String, Object>> li = new ArrayList<Map<String,Object>>();
		
		for(int i = 0 ;i<car.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Object[]> results = benefitDao.findPetrol(car.get(i).getCarFlapper(), condition);
			

				map.put("car", car.get(i).getCarFlapper());
				map.put("sjName", "");
				map.put("tel", "");
				map.put("com", car.get(i).getCompany().getComName());
				
				for(Object[] row : results){
					map.put("total", row[0] != null ? row[0] : 0);
					map.put("loss", row[1] != null ? row[1] : 0);
					map.put("tl",row[2] != null ? row[2] : 0.0000001);					
					
				}
				li.add(map);
			
		}
		
		return li;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public BenefitDao getBenefitDao() {
		return benefitDao;
	}

	public void setBenefitDao(BenefitDao benefitDao) {
		this.benefitDao = benefitDao;
	}

	
	
	
	
	
	
	
	
	
}
