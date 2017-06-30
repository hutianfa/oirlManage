package com.ltmcp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Empower;
import com.ltmcp.service.EmpowerService;

public class EmpowerAction extends BaseAction{
	private Empower em;
	private Integer comId;
	private Integer poId;
	private Integer currentPage;
	private List<Empower> list = null;
	private EmpowerService empowerService;
	
	
	/**
	 * 授权人员 信息列表
	 */
	public void listPow(){
		try {
			String name = (em.getName()) == null ? "": em.getName();
			
			List<Empower> list = empowerService.listService(AdminComm.getComIdByAdmin(),name,currentPage);
			
			Integer emNum = empowerService.findEmTotal(name);
			ArrayList<Object> arrList = new ArrayList<Object>();			
			for(int i = 0 ;i<list.size();i++){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", list.get(i).getId());
				map.put("name", list.get(i).getName());
				
				//根据comid获取公司名称
				String str = empowerService.queryComNmByComid(list.get(i).getCompany());
				map.put("company", str);
				map.put("createName", list.get(i).getCreateName());
				map.put("status", list.get(i).getStatus());
				
				int num = 0 ;
				double Page1 = (double) emNum / Comm.SYSTEM_PERSONSIZE;
				
				int Page2 = emNum % Comm.SYSTEM_PERSONSIZE;
				
				 if(Page1 >1 && Page2 != 0 ){
					 num = (emNum / Comm.SYSTEM_PERSONSIZE)+1;
				 }else if(Page1 < 1 || Page2 == 0 ){
					 num = 1;
				 }
				map.put("emTotal", num);
				
				arrList.add(JSONObject.fromObject(map).toString());
			}
			super.getPringWriter().print(arrList.toString());
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(2);
		}
		
	}
	
	public String Alllist(){
		return "Alllist";
	}
	/**
	 * 添加授权人员
	 */
	public void addPow(){
		try {
			empowerService.addService(em);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
		
	}
	/**
	 * 删除授权人员
	 */
	public void delPow(){
		try {
			empowerService.delService(poId);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
		
	}
	/*
	 * 修改授权人员的授权密码
	 */
	public void UpdatePow(){
		try {
			empowerService.updateServcie(em);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
		
	}
	/*
	 * 获取公司的想关信息
	 */
	public void getComList() {
		try {
			List<Company> list = empowerService.queryCom();
			ArrayList<Object> arrList = new ArrayList<Object>();	
			
			for(int i = 0 ;i<list.size();i++){
				Map<String, Object> map = new HashMap<String, Object>();
					map.put("comid", list.get(i).getComId());
		        	map.put("comName", list.get(i).getComName());
		        	map.put("comAddr", list.get(i).getComAddr());
		        	arrList.add(JSONObject.fromObject(map).toString());
			}
			super.getPringWriter().print(arrList.toString());
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
	}
	
	public EmpowerService getEmpowerService() {
		return empowerService;
	}

	public void setEmpowerService(EmpowerService empowerService) {
		this.empowerService = empowerService;
	}

	public Empower getEm() {
		return em;
	}

	public void setEm(Empower em) {
		this.em = em;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getPoId() {
		return poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	public List<Empower> getList() {
		return list;
	}

	public void setList(List<Empower> list) {
		this.list = list;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
}
