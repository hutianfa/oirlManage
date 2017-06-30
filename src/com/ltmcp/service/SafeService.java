package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;

public interface SafeService {

	public List<Map<String,Object>> infor2(ReportCondition condition);
}
