package com.ltmcp.action;

public class DWMkReportAction extends BaseAction{

	private String type;//�����ա��ܡ���
	private String types;//����վ�㣬�ˣ���
	private Integer posi;
	
	//private DWMReportService dWMReportService;
	
	//����types�ж����������ͣ�վ�㣬�ˣ�����
	public void report(){
		if("day".equals(type)){
			if("posi".equals(types)){
				//�����ص�����ת����json
				//dWMReportService.Day(types);
				
			} else if("peop".equals(types)){
				//�����ص�����ת����json
			} else if("car".equals(types)){
				//�����ص�����ת����json
			}
		} else if("week".equals(type)){
			if("posi".equals(types)){
				//�����ص�����ת����json				
			} else if("peop".equals(types)){
				//�����ص�����ת����json
			} else if("car".equals(types)){
				//�����ص�����ת����json
			}
		} else if("month".equals(type)){
			if("posi".equals(types)){
				//�����ص�����ת����json				
			} else if("peop".equals(types)){
				//�����ص�����ת����json
			} else if("car".equals(types)){
				//�����ص�����ת����json
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
