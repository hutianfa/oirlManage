package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.Sealed;

public interface SealedService {

	/**
	 * ��ȡδ��ɵ��˵�����
	 * @return
	 */
	Integer getWayBillUnfinishedCount();

	/**
	 * ��ȡ�Ѿ���ɵ��˵�����
	 * @return
	 */
	Integer getWayBillCompletedCount();

	/**
	 * ��ѯ�˵��б�
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchWaybillByCondition(SealedCondition condition,PageBean pageBean);
	
	/*
	 * ��ѯȫ��
	 */
	List<Sealed> findAllWaybillByCondition(SealedCondition condition);

	/**
	 * ����ID��ѯ�˵���Ϣ
	 * @param id
	 * @return
	 */
	Sealed getWaybillById(Integer id);

	PageBean searchByPersonId(PageBean pageBean, Integer id);

	/**
	 * ��ȡ�����쳣�˵�������
	 * @return
	 */
	Integer getWayBillExcCount();
	
	/*
	 * ʵʱ�˵���Ϣ��ѯ
	 */
	List<Sealed> findSeaAndFre(SealedCondition condition);
	
}
