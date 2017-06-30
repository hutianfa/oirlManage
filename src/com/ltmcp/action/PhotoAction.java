package com.ltmcp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.PhotoService;

public class PhotoAction extends BaseAction{

	private PhotoService photoService;
	private PhotoCondition condition;
	private Person person;
	/**
	 * 查询所有运单的施解封图片以及施解封id
	 */
	public void seaPhoto() {
		
		List<Sealed> list = photoService.findSeaAndFreImg(condition);
		
		Integer num = photoService.findPhotoTotal(condition);

		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
			
					if (list.get(i).getFreeze() != null) {
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put("seaId", list.get(i).getSeaId());
						map.put("seaSrc", list.get(i).getSeaPhoto());
						map.put("Stip",0);
						
						map.put("freId", list.get(i).getFreeze().getFreId());
						map.put("freSrc", list.get(i).getFreeze().getFrePhoto());
						map.put("Ftips",1);
						
						map.put("pageTotal", num);
						jsonList.add(map);
					}				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			super.outPrintJsonByArray(jsonList, null);
		} catch (IOException e) {
			super.getPringWriter().print("转换失败!" + e.getMessage());
		}
	}
	
	public String photolist() {
		return "photolist";
	}
	
	/**
	 * 根据施解封标记、id以及审核结果更改信息
	 */
	
	public void photoAdvice(){
		Integer num = photoService.saveSeaAndFreImg(condition);
		super.getPringWriter().print(num);
	}
	
	/**
	 * 图片审核报表
	 * @return
	 */
	
	public void photoReport(){
		List<Map<String, Object>> list = photoService.findScale(person, condition);
		
		try {
			super.outPrintJsonByArray(list, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据personId 查 最近前后6个月的情况
	 */
	public void photoPersonReport(){
		List<Map<String, Object>> list = photoService.findPersonScale(person);
		try {
			super.outPrintJsonByArray(list, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String reportList() {
		return "reportList";
	}
	
	public PhotoService getPhotoService() {
		return photoService;
	}
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	public PhotoCondition getCondition() {
		return condition;
	}
	public void setCondition(PhotoCondition condition) {
		this.condition = condition;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
