package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;

public interface BenefitService {

	public List<Map<String,Object>> info1(ReportCondition condition);
}
