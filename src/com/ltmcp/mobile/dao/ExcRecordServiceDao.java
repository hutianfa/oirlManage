package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;

public interface ExcRecordServiceDao {

	public List<ExcRecord> findExcRecords(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	
	
	/***
	 * 201701.03
	 * @param currentPage  ��ǰҳ��
	 * @param pageSize ÿҳ��С
	 * @param erc ����
	 * @return һ��list position_inventory
	 */
	public List<position_inventory> findfindExcIllegalityDisPosition_inventory(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	
	/***
	 * 201701.03
	 * @param currentPage  ��ǰҳ��
	 * @param pageSize ÿҳ��С
	 * @param erc ����
	 * @return һ��list Second_order(����һ��position_inventory)
	 */
	public List<Second_order> findfindExcIllegalityDisSecond_order(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	
	/**
	 * �Ƿ��쳣����
	 * @param currentPage
	 * @param pageSize
	 * @param erc
	 * @return
	 */
	public List<NewErrors> findExcIllegality(Integer currentPage,Integer pageSize, ExcRecordCondition erc);
	/**
	 * ��ѯվ������
	 * @param pos
	 * @return
	 */
	public List<Position> QueryPositionName(Integer pos);
	/**
	 * ��ѯ�Ѵ����˵��˵�
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
	 * �������ͣ��Ѵ���δ������ѯ����
	 * 
	 * @param type
	 * @return
	 */
	public Integer queryExcRecordByType(Integer type);
	
	/***
	 * 
	 * @param condition ����
	 * @return �ַ�����������������˾����ʱ�������޸��������Ĳ�ѯ�ַ���¼����
	 */
	public Integer queryExcRecordByStatus_s(ExcRecordCondition condition);
	
	/***
	 * 
	 * @param condition ����
	 * @return �ַ�����������Ƭ������ʱ�������޸��������Ĳ�ѯ�ַ���¼����
	 */
	public Integer queryExcRecordByStatus_y(ExcRecordCondition condition);

	/**
	 * �����쳣״̬���в�ѯ����
	 * 
	 * @param status
	 * @return
	 */
	public Integer queryExcRecordByStatus_t(ExcRecordCondition condition);
	
	
	public Integer queryNewErrorsByStatus_t(ExcRecordCondition condition);
	
	public Integer queryExcRecordByStatus(Integer status);
	
	public Integer queryExcRecordByStatus_t(Integer status,ExcRecordCondition condition);

	/**
	 * ����������ȡ�쳣����
	 * 
	 * @param condition
	 * @return
	 */
	public Integer queryExcRecordCountByCondition(ExcRecordCondition condition);

	public ExcRecord queryexcRecordBySeaId(Integer seaId);

	/**
	 * �����쳣״̬
	 * 
	 * @param id
	 */
	public void updateRecordStatus(Integer id);
	/**
	 * ʱ���쳣��Ϣ
	 * 
	 * @param condition
	 * @return
	 */
	public List<Sealed> queryTimeList(ExcRecordCondition condition);

	/**
	 * ��ʱ�쳣 �˵��Ѿ���ɣ�ֻ��û�н����Ϣ�� ʱ�䳬��48Сʱ��������ʱ�쳣
	 * 
	 * @param condition
	 * @return
	 */
	public List<Sealed> queryTimeOutList(Integer currentPage, Integer pageSize,
			ExcRecordCondition condition);

	/**
	 * ������������浽�쳣����
	 * 
	 * @param exc
	 */
	public void updateHasHandle(String excText, Integer id);
	/**
	 * ������������浽�쳣����
	 * 
	 * @param exc
	 */
	public void newupdateHasHandle(String excText, Integer id);

}
