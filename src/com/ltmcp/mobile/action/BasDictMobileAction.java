package com.ltmcp.mobile.action;

import java.io.IOException;
import java.util.List;
import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.BasDict;
import com.ltmcp.mobile.biz.BasDictMobileBiz;

public class BasDictMobileAction  extends BaseAction{
	private String name;
	private String password;
	private BasDictMobileBiz basDictMobileBiz;
	
	
	/**
	 * 异常列表  访问：/mobile/bas_exception_list 获取
	 */
	public void exception_list(){
		List<BasDict> list=basDictMobileBiz.searchExceptionList(name, password);
		String[] ignoreAttribute=new String[]{"dictIsEditable","dictVersion","dictType","excRecords","positions","persons"};
		try {
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void oilpin_list() throws IOException{
		List<BasDict> list=basDictMobileBiz.searchOilPinList(name, password);
		String[] ignoreAttribute=new String[]{"dictIsEditable","dictVersion","dictType","excRecords","positions","persons"};
		try {
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取施封车辆去向类型
	 */
	public void tag_list(){
		List<BasDict> list=basDictMobileBiz.searchTagList(name, password);
		String[] ignoreAttribute=new String[]{"dictIsEditable","dictVersion","dictType","excRecords","positions","persons"};
		try {
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 获取固定封签异常字典
	 */
	public void fix_list(){
		List<BasDict> list=basDictMobileBiz.searchFixList(name, password);
		String[] ignoreAttribute=new String[]{"dictIsEditable","dictVersion","dictType","excRecords","positions","persons"};
		try {
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 获取固定封签异常字典
	 */
	public void empower_list(){
		List<BasDict> list=basDictMobileBiz.searchEmList(name, password);
		String[] ignoreAttribute=new String[]{"dictIsEditable","dictVersion","dictType","excRecords","positions","persons"};
		try {
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BasDictMobileBiz getBasDictMobileBiz() {
		return basDictMobileBiz;
	}

	public void setBasDictMobileBiz(BasDictMobileBiz basDictMobileBiz) {
		this.basDictMobileBiz = basDictMobileBiz;
	}
}
