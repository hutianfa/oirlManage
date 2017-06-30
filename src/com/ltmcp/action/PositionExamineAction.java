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
 * 位置审核模块
 * 
 * @author Administrator
 * 
 */
public class PositionExamineAction extends BaseAction {
	private PositionExamineService positionExamineService; // 位置service

	private PositionExamineCondition condition; // 条件

	private PageBean pageBean; // 分页对象

	private List<PositionExamine> list; // 位置审核集合

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
	 * 前端获取List集合
	 */
	public String list() {
		pageBean = positionExamineService.getList(condition, pageBean);
		list = pageBean.getList();
		return super.returnToViewList(list);
	}

	/**
	 * 不通过，删除
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
	 * 详细
	 */
	public String detail() {
		positionExamine = positionExamineService.getPositionExamine(id);
		return "detailed";
	}

	/**
	 * 通过
	 * 
	 * @return
	 */
	public void updateState() {
		try {
			// 这里更新数据
			positionExamineService.updatePosState(id);  //这是你在做更新

			//获取更新后的数据
			PositionExamine examine = positionExamineService.getPositionExamine(id);
			
			//保存数据
			positionService.savePos(examine);
			
			super.getPringWriter().print(0);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(1);
		}
		
	}

	// ---------------------------------------------------属性的方法---------------------------------------------------------------------------------
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
