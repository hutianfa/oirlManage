package com.ltmcp.action;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineUserCondition;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.service.PositionExamineUserService;

public class PositionExamineUserAction extends BaseAction{
	
	private static final long serialVersionUID = 8324441223027098055L;

	/**��ӻ��޸Ķ���*/
	private PositionExamineUser user;//��Ҫ������ϴ�λ����Ϣ��
	
	/**service */
	private PositionExamineUserService positionExamineUserService;
	
	/**���� */
	private List<PositionExamineUser> list;
	
	/**���� */
	private PositionExamineUserCondition condition;
	
	/**��ҳ���� */
	private PageBean pageBean;
	
	
	/**
	 * ���λ���ϴ���Ϣ��
	 */
	public void add(){
		try {
			positionExamineUserService.addPositionExamineUser(user);
			super.getPringWriter().print(0);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(1);
		}
	}
	
	/**
	 * ��ȡlist����
	 */
	public String list(){
		pageBean=positionExamineUserService.getPositionExamineUserList(pageBean,condition);
		list=pageBean.getList();
		return super.returnToViewList(pageBean);
	}
	
	
	

	public PositionExamineUser getUser() {
		return user;
	}

	public void setUser(PositionExamineUser user) {
		this.user = user;
	}

	public PositionExamineUserService getPositionExamineUserService() {
		return positionExamineUserService;
	}

	public void setPositionExamineUserService(
			PositionExamineUserService positionExamineUserService) {
		this.positionExamineUserService = positionExamineUserService;
	}

	public List<PositionExamineUser> getList() {
		return list;
	}

	public void setList(List<PositionExamineUser> list) {
		this.list = list;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public PositionExamineUserCondition getCondition() {
		return condition;
	}

	public void setCondition(PositionExamineUserCondition condition) {
		this.condition = condition;
	}

	
	
	
	
}
