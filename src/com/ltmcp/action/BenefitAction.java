package com.ltmcp.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.service.BenefitService;

public class BenefitAction extends BaseAction{

	private ReportCondition condition;
	private BenefitService benefitService;
	private String sortT;
	
	//关于油量运输与损耗的统计
	public void petrolReport(){
		List<Map<String, Object>> li = benefitService.info1(condition);
		
		
		if("1".equals(sortT)){//降序
			Collections.sort(li, new MapComparatorJ());
		} else if("0".equals(sortT)){//升序
			Collections.sort(li, new MapComparatorS());			
		}else{
			Collections.sort(li, new MapComparatorJ());
		}
			
		super.outPrintJsonByArray(li);
		
	}
	
	
	
	public String Benefitlist(){
		return "Benefitlist";
	}
	
	
	
	public static class MapComparatorS implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  try {
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
		             
			} catch (Exception e) {
				e.printStackTrace();
			}
			  return 0;
		  		}
		}
	
	
	public static class MapComparatorJ implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  Double s1 = (Double) o1.get("tl");  
			  Double s2 = (Double) o2.get("tl"); 
			  
			  
			  try {
				  if(s1 > s2) {  
		                 return 1;  
		                }
		             if(s1 < s2){  
		                 return -1;  
		                } 
		             if(s1 == s2){
		            	 return 0;	
		                }
			} catch (Exception e) {
				e.printStackTrace();
			}
	             return 0;
		  		}
		  	
		}

	public BenefitService getBenefitService() {
		return benefitService;
	}
	public void setBenefitService(BenefitService benefitService) {
		this.benefitService = benefitService;
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
	
	
	
	
}
