package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;

public interface PersonMobileBiz {
	/**
	 * 验证改用户名和密码是否存在
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean validationPerson(String name, String password);

	/**
	 * 修改密码
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
	 * @return 1，施封员，2，解封员 -1，其他
	 */
	public Integer getPersonType(String name, String password);
//	public Person getPersonType(String name, String password);
	/**
	 * 操作人员的对应的油库操作
	 */

	public List<Person> findPersonByPos(String perName);
	
	//limitime
	public Limt Limitime();
}
