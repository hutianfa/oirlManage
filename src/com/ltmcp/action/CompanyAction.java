package com.ltmcp.action;

import java.util.List;
import com.ltmcp.service.CompanyService;

public class CompanyAction extends BaseAction{

	private CompanyService companyService;
	//���ݹ�˾����ģ����ѯ
	public void getListByName() {
        List list = companyService.searchCompanyByCondition();

        super.outPrintJsonByArray(list);
    }
	
	public CompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
}
