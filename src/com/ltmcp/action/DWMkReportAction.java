package com.ltmcp.action;

public class DWMkReportAction extends BaseAction{

	private String type;//区分日、周、月
	private String types;//区分站点，人，车
	private Integer posi;
	
	//private DWMReportService dWMReportService;
	
	//根据types判断是那种类型（站点，人，车）
	public void report(){
		if("day".equals(type)){
			if("posi".equals(types)){
				//将返回的数据转换成json
				//dWMReportService.Day(types);
				
			} else if("peop".equals(types)){
				//将返回的数据转换成json
			} else if("car".equals(types)){
				//将返回的数据转换成json
			}
		} else if("week".equals(type)){
			if("posi".equals(types)){
				//将返回的数据转换成json				
			} else if("peop".equals(types)){
				//将返回的数据转换成json
			} else if("car".equals(types)){
				//将返回的数据转换成json
			}
		} else if("month".equals(type)){
			if("posi".equals(types)){
				//将返回的数据转换成json				
			} else if("peop".equals(types)){
				//将返回的数据转换成json
			} else if("car".equals(types)){
				//将返回的数据转换成json
			}
		}
	}
	
	
	
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Integer getPosi() {
		return posi;
	}
	public void setPosi(Integer posi) {
		this.posi = posi;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

//	public DWMReportService getdWMReportService() {
//		return dWMReportService;
//	}
//
//	public void setdWMReportService(DWMReportService dWMReportService) {
//		this.dWMReportService = dWMReportService;
//	}

	
	
	
	
	
	
	
	
	
	
	
	
}
