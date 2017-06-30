package com.ltmcp.action;

import java.util.ArrayList;
import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.FixCondition;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.service.FixService;

public class FixAction extends BaseAction{

	private FixCondition condition;
	private PageBean pageBean; // ҳ�����
	List<FixedSeal> list = new ArrayList<FixedSeal>();
	private FixService fixService;
	
	
	/**
	 * �̶���ǩ��list 
	 * @return
	 */
	
	public String list() {
		pageBean = fixService.searchFixByCondition(condition, pageBean);
		//��pageBean�����װ��list��
		list = pageBean.getList();
		return super.returnToViewList(pageBean);
	}




	
	
	
	
	
	
	
	public FixCondition getCondition() {
		return condition;
	}




	public void setCondition(FixCondition condition) {
		this.condition = condition;
	}




	public PageBean getPageBean() {
		return pageBean;
	}




	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}




	public List<FixedSeal> getList() {
		return list;
	}




	public void setList(List<FixedSeal> list) {
		this.list = list;
	}




	public FixService getFixService() {
		return fixService;
	}




	public void setFixService(FixService fixService) {
		this.fixService = fixService;
	}

}
