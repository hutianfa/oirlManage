package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PersonCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;

public interface PersonService {

	/**
	 * 添加员工信息
	 * @param addPerson
	 * @throws Exception 
	 */
	void addPerson(Person addPerson);

	boolean findPeronExtises(Person addPerson);
	/**
	 * 获取详细信息
	 * @param id
	 * @return
	 */
	Person getPerson(Integer id);

	/**
	 * 根据条件获取人员列表
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchPersons(PersonCondition condition, PageBean pageBean);

	List<Position> searchPositions();

	/**
	 * 根据ID删除
	 * @param id
	 */
	void delPerson(Integer id) throws Exception;
	
	
	/**
	 * 根据id修改
	 */
	void modify(Integer id,String phoneNum,String pwd) throws Exception;

}
