package com.ltmcp.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.service.EfficiencyService;

public class EfficiencyAction extends BaseAction{

	private ReportCondition condition;
	private String sortT;//≈≈–Ú±Í ∂
	private EfficiencyService efficiencyService;
	
	/*
	 * –«±®±Ì
	 */
	
	public void Efficiency(){
		
		List<Map<String, Object>> li = efficiencyService.infor3(condition);
		
		
		if("1".equals(sortT)){//Ωµ–Ú
			Collections.sort(li, new MapComparatorJ());
		} else if("0".equals(sortT)){//…˝–Ú
			Collections.sort(li, new MapComparatorS());			
		}else{
			Collections.sort(li, new MapComparatorJ());
		}
			
		super.outPrintJsonByArray(li);
		
	}

	public String efficlist(){
		return "efficlist";
	}
	
	
	public static class MapComparatorS implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  Double s1 = (Double) o1.get("tl");  
			  Double s2 = (Double) o2.get("tl"); 
	             
			  if(s1 > s2) {  
	             return -1;  
	                }
			  if(s1 < s2){  
	              return 1;  
	                }
			  if(s1 == s2){
	              return 0;
	                }
			  return 0;
		  		}
		}
	
	public static class MapComparatorJ implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  Double s1 = (Double) o1.get("tl");  
			  Double s2 = (Double) o2.get("tl"); 
			  
	             if(s1 > s2) {  
	                return 1;  
	                	}
	             if(s1 < s2){  
	                return -1;  
	                	}
	             if(s1 == s2){  
	                return 0;  
	                	}
	             
	             return 0;
		  		}
		}

	public ReportCondition getCondition() {
		return condition;
	}


	public void setCondition(ReportCondition condition) {
		this.condition = condition;
	}


	public String getSortT() {
		return sortT;
	}


	public void setSortT(String sortT) {
		this.sortT = sortT;
	}


	public EfficiencyService getEfficiencyService() {
		return efficiencyService;
	}


	public void setEfficiencyService(EfficiencyService efficiencyService) {
		this.efficiencyService = efficiencyService;
	}
}
