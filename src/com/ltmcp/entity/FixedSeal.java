package com.ltmcp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class FixedSeal implements Serializable{

	private Integer id;
	private Integer company;//��˾
	private Timestamp seaTime;//ʩ��ʱ��
	private Timestamp freTime;//���ʱ��
	private String seaCode;//ʩ����
	private String freCode;//�����
	private String seaSign;//ʩ��ǩ��
	private String freSign;//���ǩ��
	private String seaTip;//ʩ��ԭ��
	private String freTip;//���ʱ��
	private String seaName;//ʩ����
	private String freName;//�����
	private Integer seaPosi;//ʩ����ҵ��
	private Integer frePosi;//�����ҵ��
	private Integer areaid; //����������id
	private String plateNumber;//���ƺ�
	private Integer fixStatus;//��ǩ״̬
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getSeaTime() {
		return seaTime;
	}
	public void setSeaTime(Timestamp seaTime) {
		this.seaTime = seaTime;
	}
	public Timestamp getFreTime() {
		return freTime;
	}
	public void setFreTime(Timestamp freTime) {
		this.freTime = freTime;
	}
	public String getSeaCode() {
		return seaCode;
	}
	public void setSeaCode(String seaCode) {
		this.seaCode = seaCode;
	}
	public String getFreCode() {
		return freCode;
	}
	public void setFreCode(String freCode) {
		this.freCode = freCode;
	}
	public String getSeaSign() {
		return seaSign;
	}
	public void setSeaSign(String seaSign) {
		this.seaSign = seaSign;
	}
	public String getFreSign() {
		return freSign;
	}
	public void setFreSign(String freSign) {
		this.freSign = freSign;
	}
	public String getSeaTip() {
		return seaTip;
	}
	public void setSeaTip(String seaTip) {
		this.seaTip = seaTip;
	}
	public String getFreTip() {
		return freTip;
	}
	public void setFreTip(String freTip) {
		this.freTip = freTip;
	}
	public Integer getSeaPosi() {
		return seaPosi;
	}
	public void setSeaPosi(Integer seaPosi) {
		this.seaPosi = seaPosi;
	}
	public Integer getFrePosi() {
		return frePosi;
	}
	public void setFrePosi(Integer frePosi) {
		this.frePosi = frePosi;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getSeaName() {
		return seaName;
	}
	public void setSeaName(String seaName) {
		this.seaName = seaName;
	}
	public String getFreName() {
		return freName;
	}
	public void setFreName(String freName) {
		this.freName = freName;
	}
	
	public Integer getFixStatus() {
		return fixStatus;
	}
	public void setFixStatus(Integer fixStatus) {
		this.fixStatus = fixStatus;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
}
