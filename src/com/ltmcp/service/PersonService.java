package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PersonCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;

public interface PersonService {

	/**
	 * ���Ա����Ϣ
	 * @param addPerson
	 * @throws Exception 
	 */
	void addPerson(Person addPerson);

	boolean findPeronExtises(Person addPerson);
	/**
	 * ��ȡ��ϸ��Ϣ
	 * @param id
	 * @return
	 */
	Person getPerson(Integer id);

	/**
	 * ����������ȡ��Ա�б�
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchPersons(PersonCondition condition, PageBean pageBean);

	List<Position> searchPositions();

	/**
	 * ����IDɾ��
	 * @param id
	 */
	void delPerson(Integer id) throws Exception;
	
	
	/**
	 * ����id�޸�
	 */
	void modify(Integer id,String phoneNum,String pwd) throws Exception;

}
