package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;

public interface PersonMobileDao {

	/**
	 * 查询操作人员是否存在
	 * 
	 * @param person
	 * @return
	 */
	public boolean queryPersonExist(Person person);

	/**
	 * 查询操作人员公司ID
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonCompanyId(Person person);
	
	public Person queryPersonByPerson(Person person);

	/**
	 * 更新Person
	 * 
	 * @param person
	 */
	public void updatePerson(Person person, String newPassword)
			throws Exception;

	/**
	 * 查询操作员工作类型
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonType(Person person);

	/**
	 * 查看操作人员ID
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonId(Person person);

	public Person queryPerson(String name, String password);

	public void updatePersonPasswordById(Person p);

	/**
	 * 查找操作人员对应的油库和油站
	 */
	public List<Person> queryPersonByPos(String perName);

	//查询当前设置的nfc计时时长
	public Limt queryLimitime();
	
}
