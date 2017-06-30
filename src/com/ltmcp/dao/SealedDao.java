package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Sealed;

public interface SealedDao {
	
	/**
	 * ����ʩ���Id��ѯһ��ʩ����Ϣ����ϸ��Ϣ
	 * @param sealed 
	 * @return
	 */
	Sealed querySealed(Sealed sealed);
	
//	List<Sealed> querySealed_test();
	
	/**
	 * �����û�ѡ����������ѯ������Ϣ
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	List<Sealed> findSealeds(Integer limt,Integer currentPage,Integer pageSize,SealedCondition condition);
	
	
	//��ѯ��ǰ���õļ��ʱ��
	public Limt queryLimitime();
	/**
	 * ��ѯȫ��
	 * @param condition
	 * @return
	 */
	List<Sealed> selectSealeds(Integer limt,SealedCondition condition);
	
	/**
	 * �����˵�״̬���в�ѯ
	 * @param status ״̬��Ϣ
	 * @return ����
	 */
	Integer querySealedCountByStatus(Integer limt,Integer status);
	
	
	/*
	 * ʵʱ�˵���Ϣ��ѯ
	 */
	List<Sealed> selectSeaAndFre(SealedCondition condition);
	
	
	
	/**
	 * ����������ѯ��ҳ��
	 * @param condition
	 * @return ��ҳ��
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
