package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface PowerDao {

	/**
	 * ���ݹ���Ա�Ľ�ɫ��ѯȨ��
	 * @param admin ����Ա
	 * @return
	 */
	List<Power> listPowers(Admin admin);

}
