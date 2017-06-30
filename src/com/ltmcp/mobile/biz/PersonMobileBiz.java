package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;

public interface PersonMobileBiz {
	/**
	 * ��֤���û����������Ƿ����
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean validationPerson(String name, String password);

	/**
	 * �޸�����
	 * 
	 * @param name
	 * @param password
	 * @throws Exception
	 */
	public void changePerson(String name, String password, String newPassword)
			throws Exception;

	/**
	 * 
	 * @param name
	 * @param password
	 * @return 1��ʩ��Ա��2�����Ա -1������
	 */
	public Integer getPersonType(String name, String password);
//	public Person getPersonType(String name, String password);
	/**
	 * ������Ա�Ķ�Ӧ���Ϳ����
	 */

	public List<Person> findPersonByPos(String perName);
	
	//limitime
	public Limt Limitime();
}
