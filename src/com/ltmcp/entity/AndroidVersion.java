package com.ltmcp.entity;

/**
 * AndroidVersion entity. @author MyEclipse Persistence Tools
 */

public class AndroidVersion implements java.io.Serializable {

	// Fields

	private Integer id;  //表id
	private String versionCode; // 版本号
	private String versionName;//版本号名称
	private String versionDownloadAddr; //新版app的下载地址
	private String vrsionType; 
	private String versionContent; //更新日志字段

	// Constructors

	/** default constructor */
	public AndroidVersion() {
	}

	/** minimal constructor */
	public AndroidVersion(String versionCode, String versionName,
			String versionDownloadAddr, String vrsionType) {
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.versionDownloadAddr = versionDownloadAddr;
		this.vrsionType = vrsionType;
	}

	/** full constructor */
	public AndroidVersion(String versionCode, String versionName,
			String versionDownloadAddr, String vrsionType, String versionContent) {
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.versionDownloadAddr = versionDownloadAddr;
		this.vrsionType = vrsionType;
		this.versionContent = versionContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionDownloadAddr() {
		return this.versionDownloadAddr;
	}

	public void setVersionDownloadAddr(String versionDownloadAddr) {
		this.versionDownloadAddr = versionDownloadAddr;
	}

	public String getVrsionType() {
		return this.vrsionType;
	}

	public void setVrsionType(String vrsionType) {
		this.vrsionType = vrsionType;
	}

	public String getVersionContent() {
		return this.versionContent;
	}

	public void setVersionContent(String versionContent) {
		this.versionContent = versionContent;
	}

}