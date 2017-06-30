package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.CompanyCondition;
import com.ltmcp.dao.CompanyDao;
import com.ltmcp.entity.Company;
import com.ltmcp.service.CompanyService;

public class CompanyServiceImpl  extends BaseServiceImpl  implements CompanyService {

	private CompanyDao companyDao;
	@Override
	public List searchCompanyByCondition() {
		List<Map> li = new ArrayList<Map>();
		List<Company> list = companyDao.findCompanysByCondition();
        for(int i=0;i<list.size();i++){
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("comid", list.get(i).getComId());
        	map.put("comName", list.get(i).getComName());
        	map.put("comAddr", list.get(i).getComAddr());
        	li.add(map);
        }
        return li;
	}
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
}
