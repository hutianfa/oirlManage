package com.ltmcp.dao;

import java.util.Date;
import java.util.List;
import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;

public interface PhotoDao {

	/*
	 * 运单图片信息
	 */
	List<Sealed> selectSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * 保存审图信息
	 */
	Integer updateSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * 查询要审核的总数
	 */
	public Integer queryTotalCountByCondition(PhotoCondition condition);
	
	/**
	 * 按条件查询person
	 */
	List<Person> findPersons(PhotoCondition condition);
	/**
	 * 按条件查询person的数量
	 */
	Integer findPerTotal(PhotoCondition condition);
	
	/**
	 * 根据personId查询所有不规范操作的运单数量
	 */
	public Integer queryAllBadTotalSeaPhoto(Person person,PhotoCondition condition);
	/**
	 * 根据personId查询所有操作的运单数量
	 */
	public Integer queryAllTotalSeaPhoto(Person person,PhotoCondition condition);
	
	/**
	 * 根据personId查询所有不规范操作的运单数量
	 */
	public Integer queryAllBadTotalFrePhoto(Person person,PhotoCondition condition);
	/**
	 * 根据personId查询所有操作的运单数量
	 */
	public Integer queryAllTotalFrePhoto(Person person,PhotoCondition condition);
	
	/**
	 * 根据personId查询每月的情况
	 */
	public Integer queryAllTotalFre(Person person,String beginTime,String endTime);
	/**
	 * 根据personId查询每月的情况
	 */
	public Integer queryAllBadTotalFre(Person person,String beginTime,String endTime);
	/**
	 * 根据personId查询每月的情况
	 */
	public Integer queryAllBadTotalSea(Person person,String beginTime,String endTime);
	/**
	 * 根据personId查询每月的情况
	 */
	public Integer queryAllTotalSea(Person person,String beginTime,String endTime);
}
