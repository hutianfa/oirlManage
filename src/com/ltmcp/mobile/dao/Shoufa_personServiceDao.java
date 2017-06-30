package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.shoufa_person;

public interface Shoufa_personServiceDao {

	/**
	 * 把分发人登录信息保存session中
	 * @param shoufa_person
	 */
	public void saveShoufa_personInfoToSession(shoufa_person shoufa_person);
}
