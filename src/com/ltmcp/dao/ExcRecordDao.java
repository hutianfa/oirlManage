package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;

public interface ExcRecordDao {

	public List<ExcRecord> findExcRecords(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	
	/**
	 * 非法异常处理
	 * @param currentPage
	 * @param pageSize
	 * @param erc
	 * @return
	 */
	public List<NewErrors> findExcIllegality(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	/**
	 * 查询站点名称
	 * @param pos
	 * @return
	 */
	public List<Position> QueryPositionName(Integer pos);
	/**
	 * 查询已处理了的运单
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param erc
	 * @return
	 */
	public List<ExcRecord> findHasHandle(Integer currentPage, Integer pageSize,ExcRecordCondition erc);
	
	public List<NewErrors> newfindHasHandle(Integer currentPage, Integer pageSize,ExcRecordCondition erc);

	public List<ExcRecord> selectAllRecords(ExcRecordCondition condition);

	public ExcRecord queryExcRecordByExcId(ExcRecord er);

	/**
	 * 根据类型（已处理，未处理）查询条数
	 * 
	 * @param type
	 * @return
	 */
	public Integer queryExcRecordByType(Integer type);

	/**
	 * 根据异常状态进行查询条数
	 * 
	 * @param status
	 * @return
	 */
	public Integer queryExcRecordByStatus_t(ExcRecordCondition condition);
	
	public Integer queryNewErrorsByStatus_t(ExcRecordCondition condition);
	
	public Integer queryExcRecordByStatus(Integer status);
	
	public Integer queryExcRecordByStatus_t(Integer status,ExcRecordCondition condition);

	/**
	 * 根据条件获取异常条数
	 * 
	 * @param condition
	 * @return
	 */
	public Integer queryExcRecordCountByCondition(ExcRecordCondition condition);

	public ExcRecord queryexcRecordBySeaId(Integer seaId);

	/**
	 * 更新异常状态
	 * 
	 * @param id
	 */
	public void updateRecordStatus(Integer id);
	/**
	 * 时间异常信息
	 * 
	 * @param condition
	 * @return
	 */
	public List<Sealed> queryTimeList(ExcRecordCondition condition);

	/**
	 * 超时异常 运单已经完成，只是没有解封信息的 时间超过48小时的算作超时异常
	 * 
	 * @param condition
	 * @return
	 */
	public List<Sealed> queryTimeOutList(Integer currentPage, Integer pageSize,
			ExcRecordCondition condition);

	/**
	 * 将处理意见保存到异常表中
	 * 
	 * @param exc
	 */
	public void updateHasHandle(String excText, Integer id);
	/**
	 * 将处理意见保存到异常表中
	 * 
	 * @param exc
	 */
	public void newupdateHasHandle(String excText, Integer id);

}
