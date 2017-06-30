package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface AdminService {
	
	/**
	 * ����������û�������û���Ϣ
	 * @param admin
	 * @return
	 */
	public Admin getAdmin(Admin admin);

	/**
	 * ���浱ǰ��¼����Ϣ��session��
	 * @param admin
	 * @param powers
	 * @param menus
	 */
	public void saveAdminInfoToSession(Admin admin, List<Power> powers, List<Power> menus);

	/**
	 * ���list����
	 * @param pageBean
	 * @param condition
	 * @return
	 */
	public PageBean searchList(PageBean pageBean,AdminCondition condition);

	/**
	 * ɾ����ͨ����Ա
	 * @param id
	 */
	public void delGeneralmanager(Integer id) throws Exception;

	/**
	 * �����ͨ����Ա
	 * @param admin
	 */
	public void addAdmin(Admin admin,AdminCondition condition) throws Exception;
	
	/**
	 * �޸�����
	 * @param id
	 * @throws Exception
	 */
	
	
	public void updateAdminPwd(String pwd, String newPwd, String confirmPwd) throws Exception;

}
