package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PersonCondition;
import com.ltmcp.entity.Person;

public interface PersonDao {
	
	/**
	 * ����ID�鿴Ա������ϸ��Ϣ
	 * @param person
	 * @return
	 */
	Person queryPersonById(Person person);
	
	/**
	 * ����������ѯԱ����ϸ��Ϣ
	 * @param currentPage ��ǰҳ
	 * @param pageSize ҳ��С
	 * @param condition ����
	 * @return
	 */
	List<Person> findPersons(Integer currentPage,Integer pageSize,PersonCondition condition);
	
	/**
	 * ����Ա����Ϣ
	 * @param person
	 */
	void updatePerson(Person person);
	
	/**
	 * ɾ��Ա����Ϣ
	 * @param person
	 */
	void deletePerson(Person person);

	/**
	 * ���Ա����Ϣ
	 * @param addPerson
	 * @throws Exception 
	 */
	void insertPerson(Person addPerson);

	boolean queryPersonExits(Person addPerson);
	/**
	 * ����������ȡ��Ա��
	 * @param condition
	 * @return
	 */
	Integer queryPersonCountByCondition(PersonCondition condition);

	/**
	 * ���personId�Ƿ����
	 * @return
	 */
	boolean personIdIsExit(Integer personId);
}
