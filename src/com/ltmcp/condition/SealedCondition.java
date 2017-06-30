package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;

public class SealedCondition {
	private Integer posId; // λ��ID

	private Integer comId; // ��˾ID----->ͨ���ֳֻ���Ա�Ĺ�˾ID��ֵ

	private Integer areaid;
	
	private String seaLockCode; // ��ǩ����

	private Integer posTypeId; // λ�õ�����

	private String perName; // �ֳֻ�������Ա����
	
	private Integer perId;

	private String erweima;// ��ά������

	private Integer status; // ״̬,0����ɣ�1������;�� 2�������쳣

	private String beginTime; // ��ʼʱ��

	private String endTime; // ����ʱ��\
	
	private Long info;	//�˵������б��
	
	private String comName;
	
	private String posName;
	
	private String carFlapper;
	
	private Integer tag;
	
	private String empowerType;
	
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

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getSeaLockCode() {
		return seaLockCode;
	}

	public void setSeaLockCode(String seaLockCode) {
		this.seaLockCode = seaLockCode;
	}

	public Integer getPosTypeId() {
		return posTypeId;
	}

	public void setPosTypeId(Integer posTypeId) {
		this.posTypeId = posTypeId;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErweima() {
		return erweima;
	}

	public void setErweima(String erweima) {
		this.erweima = erweima;
	}

	public Long getInfo() {
		return info;
	}

	public void setInfo(Long info) {
		this.info = info;
	}



	public String getCarFlapper() {
		return carFlapper;
	}

	public void setCarFlapper(String carFlapper) {
		try {
			carFlapper = new String(carFlapper.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.carFlapper = carFlapper;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		try {
			posName=new String(posName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.posName = posName;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public String getEmpowerType() {
		return empowerType;
	}

	public void setEmpowerType(String empowerType) {
		this.empowerType = empowerType;
	}
	
	
}
