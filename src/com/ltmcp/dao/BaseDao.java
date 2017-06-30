package com.ltmcp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.ltmcp.entity.Position;

public interface BaseDao {
	/**
	 * 通过hql语句查询一个List集合
	 * 
	 * @param hql
	 *            查询语句
	 * @param param
	 *            条件参数
	 * @return 集合
	 */
	List find(String hql, Object... param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 *            查询语句
	 * @param currentPage
	 *            当前页码数
	 * @param pageSize
	 *            查询条数
	 * @param param
	 *            条件参数
	 * @return 集合
	 */
	List find(String hql, Integer currentPage, Integer pageSize,Object... param);

	/**
	 * 查询单个对象
	 * 
	 * @param clazz
	 *            查询类
	 * @param id
	 *            查询ID
	 * @return 查询结果
	 */
	Object query(Class clazz, Serializable id);

	/**
	 * 查询单个对象
	 * 
	 * @param hql
	 *            查询语句
	 * @param param
	 *            查询参数
	 * @return 结果
	 */
	Object query(String hql, Object... param);

	/**
	 * 添加一个对象同步到数据库
	 * 
	 * @param obj
	 *            添加的对象
	 * @return 添加结果
	 */
	Serializable insert(Object obj);
	
	void insertcode(Object obj);

	/**
	 * 更新一个对象
	 * 
	 * @param obj
	 *            更新对象
	 */
	void update(Object obj);

	/**
	 * 删除一个对象
	 * 
	 * @param obj
	 *            删除对象
	 */
	void delete(Object obj);

	/**
	 * 获取一个行数
	 * 
	 * @param hql
	 *            查询语句
	 * @param param
	 *            参数
	 * @return 行数
	 */
	int queryRowCount(String hql, Object... param);

	Session openSession();

	void saveOrUpdate(Object obj);

	/**
	 * 更新带返回值的
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	Integer returnByUpdate(String hql, Object[] param);

}
