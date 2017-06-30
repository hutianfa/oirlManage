package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.CompanyCondition;
import com.ltmcp.entity.Company;

public interface CompanyDao {
	
	public List<Company> findCompanysByCondition();
	
	
}
