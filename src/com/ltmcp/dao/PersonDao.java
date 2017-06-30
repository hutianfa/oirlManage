package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PersonCondition;
import com.ltmcp.entity.Person;

public interface PersonDao {
	
	/**
	 * 根据ID查看员工的详细信息
	 * @param person
	 * @return
	 */
	Person queryPersonById(Person person);
	
	/**
	 * 根据条件查询员工详细信息
	 * @param currentPage 当前页
	 * @param pageSize 页大小
	 * @param condition 条件
	 * @return
	 */
	List<Person> findPersons(Integer currentPage,Integer pageSize,PersonCondition condition);
	
	/**
	 * 更新员工信息
	 * @param person
	 */
	void updatePerson(Person person);
	
	/**
	 * 删除员工信息
	 * @param person
	 */
	void deletePerson(Person person);

	/**
	 * 添加员工信息
	 * @param addPerson
	 * @throws Exception 
	 */
	void insertPerson(Person addPerson);

	boolean queryPersonExits(Person addPerson);
	/**
	 * 根据条件获取人员数
	 * @param condition
	 * @return
	 */
	Integer queryPersonCountByCondition(PersonCondition condition);

	/**
	 * 检测personId是否存在
	 * @return
	 */
	boolean personIdIsExit(Integer personId);
}
