package com.ltmcp.dao.hibimpl;

import java.util.List;
import com.ltmcp.dao.CompanyDao;
import com.ltmcp.entity.Company;

public class CompanyDaoHibImpl extends BaseDaoHibImpl implements CompanyDao {

	@Override
	public List<Company> findCompanysByCondition() {
		StringBuilder sb = new StringBuilder("from Company c ");
        return super.findList(sb.toString());
	}

	
	
}
