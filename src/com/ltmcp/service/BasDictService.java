package com.ltmcp.service;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictService {
	
	/**
	 * ��ȡ�ֵ伯��
	 * @return ����
	 */
	public List<BasDict> listBasDicts();
	
	/**
	 * ����ID��ѯһ���ֵ���Ϣ
	 * @param basDict
	 * @return
	 */
	public BasDict getBasDictByDictId(BasDict basDict);
	
	/**
	 * �����ֵ����Ͳ�ѯ�����ֵ���Ϣ
	 * @param basDict
	 * @return
	 */
	public List<BasDict> searchBasDictByDictType(BasDict basDict);
	
	
	
	
}
