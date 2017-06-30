package com.ltmcp.dao;

import java.util.Date;
import java.util.List;
import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;

public interface PhotoDao {

	/*
	 * �˵�ͼƬ��Ϣ
	 */
	List<Sealed> selectSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * ������ͼ��Ϣ
	 */
	Integer updateSeaAndFreImg(PhotoCondition condition);
	
	/**
	 * ��ѯҪ��˵�����
	 */
	public Integer queryTotalCountByCondition(PhotoCondition condition);
	
	/**
	 * ��������ѯperson
	 */
	List<Person> findPersons(PhotoCondition condition);
	/**
	 * ��������ѯperson������
	 */
	Integer findPerTotal(PhotoCondition condition);
	
	/**
	 * ����personId��ѯ���в��淶�������˵�����
	 */
	public Integer queryAllBadTotalSeaPhoto(Person person,PhotoCondition condition);
	/**
	 * ����personId��ѯ���в������˵�����
	 */
	public Integer queryAllTotalSeaPhoto(Person person,PhotoCondition condition);
	
	/**
	 * ����personId��ѯ���в��淶�������˵�����
	 */
	public Integer queryAllBadTotalFrePhoto(Person person,PhotoCondition condition);
	/**
	 * ����personId��ѯ���в������˵�����
	 */
	public Integer queryAllTotalFrePhoto(Person person,PhotoCondition condition);
	
	/**
	 * ����personId��ѯÿ�µ����
	 */
	public Integer queryAllTotalFre(Person person,String beginTime,String endTime);
	/**
	 * ����personId��ѯÿ�µ����
	 */
	public Integer queryAllBadTotalFre(Person person,String beginTime,String endTime);
	/**
	 * ����personId��ѯÿ�µ����
	 */
	public Integer queryAllBadTotalSea(Person person,String beginTime,String endTime);
	/**
	 * ����personId��ѯÿ�µ����
	 */
	public Integer queryAllTotalSea(Person person,String beginTime,String endTime);
}
