package com.ltmcp.mobile.action;

import java.util.HashMap;
import java.util.Map;
import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.OutsourcingMobileBiz;

public class OutsourcingMobileAction extends BaseAction{

	private String code;
	private OutsourcingMobileBiz outsourcingMobileBiz;
	
	public void post(){		
		Integer codeid = outsourcingMobileBiz.findCodeId(code);
		//根据codeid查询施封信息
		Sealed sea = outsourcingMobileBiz.findSeaByCodeId(codeid);
		Map<String,Object> map = new HashMap<String,Object>();

		if(sea != null){
			map.put("status", "运单完成");
			map.put("posi", sea.getPosition().getPosName());
			map.put("time", sea.getSeaTime().toString());
			map.put("person", sea.getPerson().getPerName());
			map.put("car", sea.getCar().getCarFlapper());
		}else{
			map.put("status", "该运单没有记录");
			map.put("posi", " ");
			map.put("time", " ");
			map.put("person", " ");
			map.put("car", " ");
		}
		
		super.outPrintJsonByMap(map);
	}
	
	
	
	
	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}








	public OutsourcingMobileBiz getOutsourcingMobileBiz() {
		return outsourcingMobileBiz;
	}

	public void setOutsourcingMobileBiz(OutsourcingMobileBiz outsourcingMobileBiz) {
		this.outsourcingMobileBiz = outsourcingMobileBiz;
	}
	
	
}
