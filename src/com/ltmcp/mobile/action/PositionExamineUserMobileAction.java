package com.ltmcp.mobile.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamineUser;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.PositionExamineUserMobileBiz;

public class PositionExamineUserMobileAction extends BaseAction {
	private static final long serialVersionUID = 7842896051112719822L;

	private String positionAccount;
	private String positionPassword;
	private PositionExamineUserMobileBiz positionExamineUserMobileBiz;
	private List<Sealed> list = new ArrayList<Sealed>();

	/**
	 * 位置采集员处理登录
	 */
	public void login() {

		PositionExamineUser user = null;
		try {
			user = positionExamineUserMobileBiz.getPositionExamineUser(
					positionAccount, positionPassword);
		} catch (Exception e) {
			this.getPringWriter().print(1);
			return;
		}
		if (user == null) {
			this.getPringWriter().print(1);
		} else {
			this.getPringWriter().print(0);
		}
	}

	/**
	 * 获取油库和油站的位置
	 * 
	 * @return
	 * @throws JSONException
	 */
	public void getPosList() {
		try {
			List<Position> list = positionExamineUserMobileBiz.findPosList();
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			ArrayList<Object> arrList = new ArrayList<Object>();
			Map<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < list.size(); i++) {

				map.put("PosName" + (i + 1), list.get(i).getPosName());
				if (list.get(i).getBasDict() != null) {
					map.put("basId" + (i + 1), list.get(i).getBasDict()
							.getDictId());
				}
				map.put("number", list.size());
			}

			arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			jsonObj.put("obj", arrList);
			super.outPrintJsonByObject(jsonObj, null);// 输出
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPositionAccount() {
		return positionAccount;
	}

	public void setPositionAccount(String positionAccount) {
		this.positionAccount = positionAccount;
	}

	public String getPositionPassword() {
		return positionPassword;
	}

	public void setPositionPassword(String positionPassword) {
		this.positionPassword = positionPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PositionExamineUserMobileBiz getPositionExamineUserMobileBiz() {
		return positionExamineUserMobileBiz;
	}

	public void setPositionExamineUserMobileBiz(
			PositionExamineUserMobileBiz positionExamineUserMobileBiz) {
		this.positionExamineUserMobileBiz = positionExamineUserMobileBiz;
	}

	public List<Sealed> getList() {
		return list;
	}

	public void setList(List<Sealed> list) {
		this.list = list;
	}

}
