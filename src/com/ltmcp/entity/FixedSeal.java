package com.ltmcp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class FixedSeal implements Serializable{

	private Integer id;
	private Integer company;//公司
	private Timestamp seaTime;//施封时间
	private Timestamp freTime;//解封时间
	private String seaCode;//施封码
	private String freCode;//解封吗
	private String seaSign;//施封签名
	private String freSign;//解封签名
	private String seaTip;//施封原因
	private String freTip;//解封时间
	private String seaName;//施封人
	private String freName;//解封人
	private Integer seaPosi;//施封作业点
	private Integer frePosi;//解封作业点
	private Integer areaid; //所属的区域id
	private String plateNumber;//车牌号
	private Integer fixStatus;//封签状态
	
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
