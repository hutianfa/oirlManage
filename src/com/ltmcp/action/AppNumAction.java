package com.ltmcp.action;

import java.util.ArrayList;
import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.entity.DW;
import com.ltmcp.service.AppNumService;

public class AppNumAction extends BaseAction{

	private AppNumService appNumService;
	private AppNumCondition condition;
	List<DW> list = new ArrayList<DW>();
	private PageBean pageBean; // 页码控制
	/**
	 * 统计aap定位的imei序列号
	 */
	public String num(){
		try {
			pageBean= appNumService.findDw(pageBean, condition);
			//将pageBean对象封装在list中

			List<Object> list1 = pageBean.getList();
			
			for(int i =0 ;i<list1.size();i++){
				
				DW  dw = new DW();
				Object[] obj = (Object[]) list1.get(i);
				for(int j =0 ;j<obj.length;j++){

					if(j==0){
						dw.setImei((String) obj[j]);
					}else if(j==1){
						dw.setPname((String) obj[j]);
					}else if(j==2){
						dw.setAppNum((String) obj[j]);
					}		
				}
				list.add(dw);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.returnToViewList(pageBean);
	}
	
	
	public AppNumService getAppNumService() {
		return appNumService;
	}
	public void setAppNumService(AppNumService appNumService) {
		this.appNumService = appNumService;
	}
	public AppNumCondition getCondition() {
		return condition;
	}
	public void setCondition(AppNumCondition condition) {
		this.condition = condition;
	}
	public List<DW> getList() {
		return list;
	}
	public void setList(List<DW> list) {
		this.list = list;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

}
