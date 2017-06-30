package com.ltmcp.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.ltmcp.condition.ReportCondition;
import com.ltmcp.service.SafeService;

public class SafeAction extends BaseAction{

	private ReportCondition condition;
	private String sortT;//排序标识
	private SafeService safeService;
	/*
	 * 获取解封与异常数之比
	 */
	public void safe(){
		
		List<Map<String, Object>> li = safeService.infor2(condition);

		if("1".equals(sortT)){//降序
			Collections.sort(li, new MapComparatorJ());
		} else if("0".equals(sortT)){//升序
			Collections.sort(li, new MapComparatorS());			
		}else{
			Collections.sort(li, new MapComparatorJ());
		}
		
		
		super.outPrintJsonByArray(li);
		
	}
	
	
	
	
	
	public String Seculist(){
		return "Seculist";
	}
	
	public static class MapComparatorS implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  Double s1 = (Double) o1.get("tl");  
			  Double s2 = (Double) o2.get("tl"); 
	          
			  Integer s11 = (Integer) o1.get("exrTotal");  
			  Integer s22 = (Integer) o2.get("exrTotal");
			  
			  Integer s111 = (Integer) o1.get("freTotal");  
			  Integer s222 = (Integer) o2.get("freTotal");

			  if(s1 > s2) {  
	             return -1;  
	                }
			  if(s1 < s2){  
	              return 1;  
	                }
			  if(s1 == s2){
				  
				  if(s11 > s22) {  
			            return -1;  
			             }
				  if(s11 < s22){  
			            return 1;  
			             }
				  if(s11 == s22){
						  
						  if(s111 > s222) {  
					           return -1;  
					             }
						  if(s111 < s222){  
					           return 1;  
					             }
						  if(s111 == s222){
							   return 0;
							     }
					  	}
	                }
			  return 0;
		  		}
		}
	
	public static class MapComparatorJ implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  Double s1 = (Double) o1.get("tl");  
			  Double s2 = (Double) o2.get("tl"); 
			  
			  Integer s11 = (Integer) o1.get("exrTotal");  
			  Integer s22 = (Integer) o2.get("exrTotal");
			  
			  Integer s111 = (Integer) o1.get("freTotal");  
			  Integer s222 = (Integer) o2.get("freTotal");
			  
	             if(s1 > s2) {  
	                return 1;  
	                	}
	             if(s1 < s2){  
	                return -1;  
	                	}
	             if(s1 == s2){  
	   				  
	   				  if(s11 > s22) {  
	   			            return 1;  
	   			            }
	   				  if(s11 < s22){  
	   			             return -1;  
	   			             }
	   				  if(s11 == s22){
							  							  
							  if(s111 > s222) {  
						            return 1;  
						          }
							  if(s111 < s222){  
						            return -1;  
						          }
							  if(s111 == s222){
									return 0;
								  }
						  }
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
	public SafeService getSafeService() {
		return safeService;
	}
	public void setSafeService(SafeService safeService) {
		this.safeService = safeService;
	}
	
	
	
	
}
