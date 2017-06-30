package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictMobileDao {
	
	public List<BasDict> findExceptionList();
	
	/**
	 * ��ѯ��Ʒ������
	 * @return
	 */
	public List<BasDict> findOilPinList();
	
	/**
	 * ��ȡfix�ֵ�
	 */
	public List<BasDict> findFixList();
	
	/**
	 * ��ȡ����ȥ���б�
	 * @return
	 */
	
	public List<BasDict> findTagList();
	
	/**
	 * ��Ȩ�ֵ�
	 * @return
	 */
	
	public List<BasDict> findEmList();
}
