package com.ltmcp.service;

import java.util.List;
import java.util.Map;

public interface DWMReportService {

	//�ձ�
	public List<Map<String,Object>> Day(String types);
	//�ܱ�
	public List<Map<String,Object>> Week(String types);
	//�±�
	public List<Map<String,Object>> Month(String types);
}
