package com.ltmcp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.ExrMobileBiz;

public class ExrMobileAction extends BaseAction {
	
	private ExcRecord exr;
	private String excText;
	private String name;
	private String password;
	private Integer currPage;
	private ExrMobileBiz exrMobileBiz;
	private Integer freId;
	/**
	 * 显示异常列表(授权)
	 */
	public void timeExrList(){
		try {
			List<Sealed> list =exrMobileBiz.queryPow(name, password,currPage);
			
			
			ArrayList<Object> arrList = new ArrayList<Object>();			
			for(int i = 0 ;i<list.size();i++){
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("id", list.get(i).getFreeze().getFreId());
				
				long time = list.get(i).getFreeze().getFreTime().getTime();				
				
				Date d = new Date(time);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				map.put("id", list.get(i).getFreeze().getFreId());
				map.put("time", sdf.format(d));
				map.put("img", list.get(i).getFreeze().getFrePhoto());
				map.put("perName", list.get(i).getFreeze().getPowCodName());
				map.put("position", list.get(i).getPosition().getPosName());
				map.put("car", list.get(i).getFreeze().getCar().getCarFlapper());
				String result = "";
				
				if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 72){
					result = "误操作";
				}else if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 73){
					result = "操作不合格";
				}
				map.put("result", result);
				
				arrList.add(map);
			}
			super.outPrintJsonByArray(arrList);
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
	}
	/**
	 * 显示异常列表(超时异常)
	 */
	public void timeoutExrList(){
		try {
			List<Sealed> list = exrMobileBiz.queryTimeOutList(name, password,currPage);		
			if(list != null){

				ArrayList<Object> arrList = new ArrayList<Object>();
				
				for(int i = 0 ;i<list.size();i++){
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", list.get(i).getSeaId());
					map.put("img", list.get(i).getSeaPhoto());
					long time = list.get(i).getSeaTime().getTime();				
					Date d = new Date(time);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					map.put("time", sdf.format(d));
					map.put("perName", list.get(i).getPerson().getPerName());
					map.put("car", list.get(i).getCar().getCarFlapper());
					map.put("position",list.get(i).getPosition().getPosName() );
					map.put("car", list.get(i).getFreeze().getCar().getCarFlapper());
					arrList.add(map);
				}
				super.outPrintJsonByArray(arrList);
			}else{
				super.getPringWriter().print(3);
			}
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
		
	}
	/**
	 * 显示异常列表(解封异常)
	 */
	public void freExrList(){
		try {
			List<ExcRecord> list = exrMobileBiz.queryFreExrLi(name, password,currPage);
			
			if(list != null){
				ArrayList<Object> arrList = new ArrayList<Object>();			
				for(int i = 0 ;i<list.size();i++){
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", list.get(i).getExcId());
					
					long time = list.get(i).getFreeze().getFreTime().getTime();				
					Date d = new Date(time);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					map.put("time", sdf.format(d));
					map.put("perName",list.get(i).getFreeze().getPerson().getPerName() );
					map.put("position",list.get(i).getFreeze().getPosition().getPosName() );
					map.put("type", list.get(i).getBasDict().getDictValue());
					map.put("img", list.get(i).getFreeze().getFrePhoto());
					map.put("car", list.get(i).getFreeze().getCar().getCarFlapper());
					arrList.add(map);
				}
				super.outPrintJsonByArray(arrList);
			}else{
				super.getPringWriter().print(3);
			}
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
	}
	/**
	 *显示详细内容
	 */
	public void ExrDetailed(){
		try {
			ExcRecord e = exrMobileBiz.queryDeatil(exr);
			
			if(e != null){
				ArrayList<Object> arrList = new ArrayList<Object>();
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("id",e.getBasDict().getDictId());
				
				long time = e.getSealed().getFreeze().getFreTime().getTime();				
				Date d = new Date(time);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				map.put("time", sdf.format(d));
				map.put("perName",e.getSealed().getFreeze().getPerson().getPerName());
				map.put("position",e.getSealed().getFreeze().getPosition().getPosName());
				map.put("type", e.getExcHandleMethod());
				arrList.add(map);
				
				super.outPrintJsonByArray(arrList);
			}else{
				super.getPringWriter().print(3);
			}
	
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
	}
	/**
	 * 处理异常
	 */
	public void dealExr(){
		try {
			exrMobileBiz.moditfyEXrSta(exr);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(2);
		}
		
	}
	/**
	 * 根据授权运单的id更新运单异常是否查看
	 * @return
	 */
	public void updateEmpo(){
		try {
			exrMobileBiz.moditfyEmpo(freId);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(2);
		}
		
	}
	
	
	
	public ExrMobileBiz getExrMobileBiz() {
		return exrMobileBiz;
	}
	public void setExrMobileBiz(ExrMobileBiz exrMobileBiz) {
		this.exrMobileBiz = exrMobileBiz;
	}
	public ExcRecord getExr() {
		return exr;
	}
	public void setExr(ExcRecord exr) {
		this.exr = exr;
	}
	public String getExcText() {
		return excText;
	}
	public void setExcText(String excText) {
		this.excText = excText;
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
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getFreId() {
		return freId;
	}
	public void setFreId(Integer freId) {
		this.freId = freId;
	}	
}
