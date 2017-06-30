package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * ����鿴Ա����Ϣʱ������ɸѡ
 * @author zoujianjun
 *
 */
public class PersonCondition {
	
	private Integer comId; //��˾ID
	
	private Integer perId; //Ա��id
	
	private String perUserName;
	
	private String perTrueName;
	
	private Integer perSex; //Ա���Ա� (0��Ů��1����)
	
	private Timestamp startDate; //���Ա����ʱ��
	
	private Timestamp endDate; //���Ա����ʱ��

	private String comName;
	
	private Integer areaId;
	
	private Integer posId;
	
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		try {
			comName=new String(comName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.comName = comName;
	}
	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public Integer getPerSex() {
		return perSex;
	}

	public void setPerSex(Integer perSex) {
		this.perSex = perSex;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public String getPerTrueName() {
		return perTrueName;
	}

	public void setPerTrueName(String perTrueName) {
		try {
			perTrueName=new String(perTrueName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.perTrueName = perTrueName;
	}

	public String getPerUserName() {
		return perUserName;
	}

	public void setPerUserName(String perUserName) {
		this.perUserName = perUserName;
	}

}
