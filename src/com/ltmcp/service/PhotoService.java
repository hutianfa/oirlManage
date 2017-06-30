package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;

public interface PhotoService {


	/*
	 * �˵�ͼƬ��Ϣ
	 */
	List<Sealed> findSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * ������ͼ��Ϣ
	 */
	Integer saveSeaAndFreImg(PhotoCondition condition);
	
	
	/**
	 * �����ͼƬ������
	 */
	Integer findPhotoTotal(PhotoCondition condition);
	
	/**
	 *��ѯ �����淶����/�ܲ��������ı���
	 */
	List<Map<String, Object>> findScale(Person person,PhotoCondition condition);
	
	/**
	 *��ѯ �����淶����/�ܲ��������ı���
	 */
	List<Map<String, Object>> findPersonScale(Person person);
	
}
