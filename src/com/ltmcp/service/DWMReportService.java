package com.ltmcp.service;

import java.util.List;
import java.util.Map;

public interface DWMReportService {

	//日报
	public List<Map<String,Object>> Day(String types);
	//周报
	public List<Map<String,Object>> Week(String types);
	//月报
	public List<Map<String,Object>> Month(String types);
}
