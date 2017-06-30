package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;

public interface ExcRecordServiceBiz {

	/**
	 * 获取已经处理的异常条数
	 * 
	 * @return
	 */
	Integer getTreatedCount();

	/**
	 * 获取未处理的异常条数
	 * 
	 * @return
	 */
	Integer getUnTreatedCount();

	/**
	 * 获取异常信息集合
	 * 
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchExcRecords(ExcRecordCondition condition, PageBean pageBean);
	
	PageBean selectExcRecords(ExcRecordCondition condition, PageBean pageBean);
	
	PageBean newselectExcRecords(ExcRecordCondition condition, PageBean pageBean);
	
	/***
	 * 
	 * @param condition 条件暂时只有一个comid或者areaid，查询的表是position_inventory
	 * @param pageBean
	 * @return 一个pagebean
	 */
	PageBean searchIllegalityDis_pianqu(ExcRecordCondition condition, PageBean pageBean);
	
	/***
	 * 
	 * @param condition 条件暂时只有一个comid或者areaid，查询的表是secondorder
	 * @param pageBean
	 * @return 一个pagebean
	 */
	PageBean searchIllegalityDis(ExcRecordCondition condition, PageBean pageBean);
	
	/**
	 * 非异常信息
	 * @param newErrors
	 * @param pageBean
	 * @return
	 */
	PageBean searchIllegality(ExcRecordCondition condition, PageBean pageBean);
	/**
	 * 获取站点信息
	 * @param posid
	 * @return
	 */
	public List<Position> QueryPositionNameS(Integer posid);
	/**
	 * 获取单个异常信息
	 * 
	 * @return
	 */
	ExcRecord getExcRecord(Integer id);

	/**
	 * 根据运单ID来查询
	 * 
	 * @param seaId
	 * @return
	 */
	ExcRecord getExcRecordBySeaId(Integer seaId);

	/**
	 * 处理异常情况
	 * 
	 * @param id
	 */
	void changeExcRecordStatus(Integer id);
	/**
	 * 获取异常类型集合
	 * 
	 * @return
	 */
	List<BasDict> searchExceptionTypes();

	/**
	 * 获取全部异常信息
	 * 
	 * @param condition
	 * @return
	 */
	List<ExcRecord> findAllRecords(ExcRecordCondition condition);

	/**
	 * 时间异常
	 * 
	 * @param dictId
	 * @param endId
	 * @return
	 */
	java.util.Map findTimeList(ExcRecordCondition condition);

	/**
	 * 超时异常 48小时
	 * 
	 * @param condition
	 * @return
	 */
	PageBean findTimeOutList(ExcRecordCondition condition,
			PageBean pageBean);

	/**
	 * 将处理意见存在excrecord表中
	 * @param handleText
	 */
	void updateHandleMethod(String excText, Integer id);
	/**
	 * 将处理意见存在NewErrors表中
	 * @param handleText
	 */
	void newupdateHandleMethod(String newexcText, Integer newid);
	
}
