package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Person entity. @author MyEclipse Persistence Tools
 */

public class Person implements java.io.Serializable {

	// Fields

	private Integer perId; //表id
	private Admin admin; //关联的admin
	private BasDict basDict; //关联的字典表
	private Position position;//关联的位置表
	private Company company; //关联的公司表
	private String perName;// 用户名
	private String perPassword;//用户密码
	private String perTrueName; //用户真实姓名
	private Integer perSex;//用户的性别
	private String adminCreateName;//用户创建人
	private Timestamp perCreateDate;//用户创建时间
	private String perPhone; //用户的手机
	private String perTelephone;//用户的手机
	private String perEmail;//字段重用  属性改为 person的类型：0操作类型  1管理类型
	private Integer perStatus;//用户当前状态
	private String perCode; //用户的app操作权限
	private Set sealeds = new HashSet(0);
	private Set freezes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** minimal constructor */
	public Person(Admin admin, Position position, Company company,
			String perName, String perPassword, Timestamp perCreateDate,
			Integer perStatus, String perCode) {
		this.admin = admin;
		this.position = position;
		this.company = company;
		this.perName = perName;
		this.perPassword = perPassword;
		this.perCreateDate = perCreateDate;
		this.perStatus = perStatus;
		this.perCode = perCode;
	}

	/** full constructor */
	public Person(Admin admin, BasDict basDict, Position position,
			Company company, String perName, String perPassword,
			String perTrueName, Integer perSex, String adminCreateName,
			Timestamp perCreateDate, String perPhone, String perTelephone,
			String perEmail, Integer perStatus, String perCode, Set sealeds,
			Set freezes) {
		this.admin = admin;
		this.basDict = basDict;
		this.position = position;
		this.company = company;
		this.perName = perName;
		this.perPassword = perPassword;
		this.perTrueName = perTrueName;
		this.perSex = perSex;
		this.adminCreateName = adminCreateName;
		this.perCreateDate = perCreateDate;
		this.perPhone = perPhone;
		this.perTelephone = perTelephone;
		this.perEmail = perEmail;
		this.perStatus = perStatus;
		this.perCode = perCode;
		this.sealeds = sealeds;
		this.freezes = freezes;
	}

	public Integer getPerId() {
		return this.perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public BasDict getBasDict() {
		return this.basDict;
	}

	public void setBasDict(BasDict basDict) {
		this.basDict = basDict;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPerName() {
		return this.perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerPassword() {
		return this.perPassword;
	}

	public void setPerPassword(String perPassword) {
		this.perPassword = perPassword;
	}

	public String getPerTrueName() {
		return this.perTrueName;
	}

	public void setPerTrueName(String perTrueName) {
		this.perTrueName = perTrueName;
	}

	public Integer getPerSex() {
		return this.perSex;
	}

	public void setPerSex(Integer perSex) {
		this.perSex = perSex;
	}

	public String getAdminCreateName() {
		return this.adminCreateName;
	}

	public void setAdminCreateName(String adminCreateName) {
		this.adminCreateName = adminCreateName;
	}

	public Timestamp getPerCreateDate() {
		return this.perCreateDate;
	}

	public void setPerCreateDate(Timestamp perCreateDate) {
		this.perCreateDate = perCreateDate;
	}

	public String getPerPhone() {
		return this.perPhone;
	}

	public void setPerPhone(String perPhone) {
		this.perPhone = perPhone;
	}

	public String getPerTelephone() {
		return this.perTelephone;
	}

	public void setPerTelephone(String perTelephone) {
		this.perTelephone = perTelephone;
	}

	public String getPerEmail() {
		return this.perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public Integer getPerStatus() {
		return this.perStatus;
	}

	public void setPerStatus(Integer perStatus) {
		this.perStatus = perStatus;
	}

	public String getPerCode() {
		return this.perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

	public Set getSealeds() {
		return this.sealeds;
	}

	public void setSealeds(Set sealeds) {
		this.sealeds = sealeds;
	}

	public Set getFreezes() {
		return this.freezes;
	}

	public void setFreezes(Set freezes) {
		this.freezes = freezes;
	}
}