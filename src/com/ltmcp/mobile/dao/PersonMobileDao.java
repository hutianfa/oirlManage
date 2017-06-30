package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;

public interface PersonMobileDao {

	/**
	 * ��ѯ������Ա�Ƿ����
	 * 
	 * @param person
	 * @return
	 */
	public boolean queryPersonExist(Person person);

	/**
	 * ��ѯ������Ա��˾ID
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonCompanyId(Person person);
	
	public Person queryPersonByPerson(Person person);

	/**
	 * ����Person
	 * 
	 * @param person
	 */
	public void updatePerson(Person person, String newPassword)
			throws Exception;

	/**
	 * ��ѯ����Ա��������
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonType(Person person);

	/**
	 * �鿴������ԱID
	 * 
	 * @param person
	 * @return
	 */
	public Integer queryPersonId(Person person);

	public Person queryPerson(String name, String password);

	public void updatePersonPasswordById(Person p);

	/**
	 * ���Ҳ�����Ա��Ӧ���Ϳ����վ
	 */
	public List<Person> queryPersonByPos(String perName);

	//��ѯ��ǰ���õ�nfc��ʱʱ��
	public Limt queryLimitime();
	
}
