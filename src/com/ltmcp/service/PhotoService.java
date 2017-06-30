package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;

public interface PhotoService {


	/*
	 * 运单图片信息
	 */
	List<Sealed> findSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * 保存审图信息
	 */
	Integer saveSeaAndFreImg(PhotoCondition condition);
	
	
	/**
	 * 待审核图片的数量
	 */
	Integer findPhotoTotal(PhotoCondition condition);
	
	/**
	 *查询 （不规范操作/总操作数）的比例
	 */
	List<Map<String, Object>> findScale(Person person,PhotoCondition condition);
	
	/**
	 *查询 （不规范操作/总操作数）的比例
	 */
	List<Map<String, Object>> findPersonScale(Person person);
	
}
