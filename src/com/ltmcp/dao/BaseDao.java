package com.ltmcp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.ltmcp.entity.Position;

public interface BaseDao {
	/**
	 * ͨ��hql����ѯһ��List����
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param param
	 *            ��������
	 * @return ����
	 */
	List find(String hql, Object... param);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param currentPage
	 *            ��ǰҳ����
	 * @param pageSize
	 *            ��ѯ����
	 * @param param
	 *            ��������
	 * @return ����
	 */
	List find(String hql, Integer currentPage, Integer pageSize,Object... param);

	/**
	 * ��ѯ��������
	 * 
	 * @param clazz
	 *            ��ѯ��
	 * @param id
	 *            ��ѯID
	 * @return ��ѯ���
	 */
	Object query(Class clazz, Serializable id);

	/**
	 * ��ѯ��������
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param param
	 *            ��ѯ����
	 * @return ���
	 */
	Object query(String hql, Object... param);

	/**
	 * ���һ������ͬ�������ݿ�
	 * 
	 * @param obj
	 *            ��ӵĶ���
	 * @return ��ӽ��
	 */
	Serializable insert(Object obj);
	
	void insertcode(Object obj);

	/**
	 * ����һ������
	 * 
	 * @param obj
	 *            ���¶���
	 */
	void update(Object obj);

	/**
	 * ɾ��һ������
	 * 
	 * @param obj
	 *            ɾ������
	 */
	void delete(Object obj);

	/**
	 * ��ȡһ������
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param param
	 *            ����
	 * @return ����
	 */
	int queryRowCount(String hql, Object... param);

	Session openSession();

	void saveOrUpdate(Object obj);

	/**
	 * ���´�����ֵ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	Integer returnByUpdate(String hql, Object[] param);

}
