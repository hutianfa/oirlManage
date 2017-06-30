package com.ltmcp.service;

import java.util.List;

import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;

public interface PowerService {
	
	/**
	 * ����������ѯĳ���û���ӵ�е�Ȩ��
	 * @param admin
	 * @return
	 */
	public List<Power> searchPowers(Admin admin);

	/**
	 * ��õ�ǰ�û��˵���Ϣ
	 * @param powers
	 * @return
	 */
	public List<Power> searchPowerMenus(List<Power> powers);
	
}
