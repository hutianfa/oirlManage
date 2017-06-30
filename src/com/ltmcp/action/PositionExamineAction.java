package com.ltmcp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.service.PositionExamineService;
import com.ltmcp.service.PositionService;

/**
 * λ�����ģ��
 * 
 * @author Administrator
 * 
 */
public class PositionExamineAction extends BaseAction {
	private PositionExamineService positionExamineService; // λ��service

	private PositionExamineCondition condition; // ����

	private PageBean pageBean; // ��ҳ����

	private List<PositionExamine> list; // λ����˼���

	private PositionExamine positionExamine = new PositionExamine();

	private Integer id;

	private PositionService positionService;

	private Position position = new Position();

	private HttpServletRequest request;

	// -------------------
	private String name;
	private String positionAccount;
	private String time;
	private String longitude;
	private String latitude;
	private Integer user_id;
	private String carNumber;
	private Integer comId;
	private Integer state;

	/**
	 * ǰ�˻�ȡList����
	 */
	public String list() {
		pageBean = positionExamineService.getList(condition, pageBean);
		list = pageBean.getList();
		return super.returnToViewList(list);
	}

	/**
	 * ��ͨ����ɾ��
	 */
	public void del() {
		try {
			positionExamineService.delPositionExamineService(id);
			super.getPringWriter().print(0);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(1);
		}
	}

	/**
	 * ��ϸ
	 */
	public String detail() {
		positionExamine = positionExamineService.getPositionExamine(id);
		return "detailed";
	}

	/**
	 * ͨ��
	 * 
	 * @return
	 */
	public void updateState() {
		try {
			// �����������
			positionExamineService.updatePosState(id);  //��������������

			//��ȡ���º������
			PositionExamine examine = positionExamineService.getPositionExamine(id);
			
			//��������
			positionService.savePos(examine);
			
			super.getPringWriter().print(0);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(1);
		}
		
	}

	// ---------------------------------------------------���Եķ���---------------------------------------------------------------------------------
	public PositionExamineService getPositionExamineService() {
		return positionExamineService;
	}

	public void setPositionExamineService(
			PositionExamineService positionExamineService) {
		this.positionExamineService = positionExamineService;
	}

	public PositionExamineCondition getCondition() {
		return condition;
	}

	public void setCondition(PositionExamineCondition condition) {
		this.condition = condition;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<PositionExamine> getList() {
		return list;
	}

	public void setList(List<PositionExamine> list) {
		this.list = list;
	}

	public PositionExamine getPositionExamine() {
		return positionExamine;
	}

	public void setPositionExamine(PositionExamine positionExamine) {
		this.positionExamine = positionExamine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	// ------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionAccount() {
		return positionAccount;
	}

	public void setPositionAccount(String positionAccount) {
		this.positionAccount = positionAccount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
