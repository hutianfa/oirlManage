package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Sealed;

public interface SealedDao {
	
	/**
	 * 根据施封的Id查询一条施封信息的详细信息
	 * @param sealed 
	 * @return
	 */
	Sealed querySealed(Sealed sealed);
	
//	List<Sealed> querySealed_test();
	
	/**
	 * 根据用户选择的条件输查询集合信息
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	List<Sealed> findSealeds(Integer limt,Integer currentPage,Integer pageSize,SealedCondition condition);
	
	
	//查询当前设置的间隔时长
	public Limt queryLimitime();
	/**
	 * 查询全部
	 * @param condition
	 * @return
	 */
	List<Sealed> selectSealeds(Integer limt,SealedCondition condition);
	
	/**
	 * 根据运单状态进行查询
	 * @param status 状态信息
	 * @return 条数
	 */
	Integer querySealedCountByStatus(Integer limt,Integer status);
	
	
	/*
	 * 实时运单信息查询
	 */
	List<Sealed> selectSeaAndFre(SealedCondition condition);
	
	
	
	/**
	 * 根据条件查询总页数
	 * @param condition
	 * @return 总页数
	 */
	Integer queryTotalCountByCondition(Integer limt,SealedCondition condition);

	List<Sealed> findSealeds(Integer curentPage, Integer pageSize, Integer id);

	Integer queryTotalCountByPerId(Integer id);

	List<Sealed> findSealedsByCarId(Integer currentPage, Integer pageSize, Integer carId);

	Integer querySealedCountByCarId(Integer id);

	List<Sealed> findSealedsByPositionId(Integer currentPage, Integer pageSize,
			Integer id);
	
	Integer querySealedCountByPositionId(Integer id);
}
