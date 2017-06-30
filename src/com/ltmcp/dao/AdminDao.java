package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.entity.Admin;

public interface AdminDao {
	/**
	 * ͨ���������Admin
	 * @param admin
	 * @return
	 */
	public Admin queryAdmin(Admin admin);

	/**
	 * ��ѯ��ͨ����Ա����
	 * @param pagebean
	 * @param condition
	 * @return
	 */
	public List<Admin> foundGeneralManagerlist(PageBean pagebean,AdminCondition condition);

	/**
	 * ������ͨ����Ա״̬
	 * @param id
	 */
	public void updateAdminStatus(Integer id);

	/**
	 * �����ͨ����Ա
	 * @param admin
	 * @throws Exception
	 */
	public void addGeneralAdmin(Admin admin) throws Exception;

	/**
	 * ����������ѯ��ͨ����Ա����
	 * @param condition
	 * @return
	 */
	public Integer queryGeneralManagerCount(AdminCondition condition);
	
	/**
	 * �޸�����
	 * @param admin
	 * @return
	 */
	public void updatePwd(String newPwd);
	
	
	
}
