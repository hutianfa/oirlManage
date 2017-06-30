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
	 * ��ȡ�Ѿ�������쳣����
	 * 
	 * @return
	 */
	Integer getTreatedCount();

	/**
	 * ��ȡδ������쳣����
	 * 
	 * @return
	 */
	Integer getUnTreatedCount();

	/**
	 * ��ȡ�쳣��Ϣ����
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
	 * @param condition ������ʱֻ��һ��comid����areaid����ѯ�ı���position_inventory
	 * @param pageBean
	 * @return һ��pagebean
	 */
	PageBean searchIllegalityDis_pianqu(ExcRecordCondition condition, PageBean pageBean);
	
	/***
	 * 
	 * @param condition ������ʱֻ��һ��comid����areaid����ѯ�ı���secondorder
	 * @param pageBean
	 * @return һ��pagebean
	 */
	PageBean searchIllegalityDis(ExcRecordCondition condition, PageBean pageBean);
	
	/**
	 * ���쳣��Ϣ
	 * @param newErrors
	 * @param pageBean
	 * @return
	 */
	PageBean searchIllegality(ExcRecordCondition condition, PageBean pageBean);
	/**
	 * ��ȡվ����Ϣ
	 * @param posid
	 * @return
	 */
	public List<Position> QueryPositionNameS(Integer posid);
	/**
	 * ��ȡ�����쳣��Ϣ
	 * 
	 * @return
	 */
	ExcRecord getExcRecord(Integer id);

	/**
	 * �����˵�ID����ѯ
	 * 
	 * @param seaId
	 * @return
	 */
	ExcRecord getExcRecordBySeaId(Integer seaId);

	/**
	 * �����쳣���
	 * 
	 * @param id
	 */
	void changeExcRecordStatus(Integer id);
	/**
	 * ��ȡ�쳣���ͼ���
	 * 
	 * @return
	 */
	List<BasDict> searchExceptionTypes();

	/**
	 * ��ȡȫ���쳣��Ϣ
	 * 
	 * @param condition
	 * @return
	 */
	List<ExcRecord> findAllRecords(ExcRecordCondition condition);

	/**
	 * ʱ���쳣
	 * 
	 * @param dictId
	 * @param endId
	 * @return
	 */
	java.util.Map findTimeList(ExcRecordCondition condition);

	/**
	 * ��ʱ�쳣 48Сʱ
	 * 
	 * @param condition
	 * @return
	 */
	PageBean findTimeOutList(ExcRecordCondition condition,
			PageBean pageBean);

	/**
	 * �������������excrecord����
	 * @param handleText
	 */
	void updateHandleMethod(String excText, Integer id);
	/**
	 * �������������NewErrors����
	 * @param handleText
	 */
	void newupdateHandleMethod(String newexcText, Integer newid);
	
}
