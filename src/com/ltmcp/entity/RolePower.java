package com.ltmcp.entity;

/**
 * RolePower entity. @author MyEclipse Persistence Tools
 */

public class RolePower implements java.io.Serializable {

	// Fields

	private Integer rpId;//��id
	private Role role;//��ɫ��
	private Power power;//�˵�Ȩ�ޱ�

	// Constructors

	/** default constructor */
	public RolePower() {
	}

	/** full constructor */
	public RolePower(Role role, Power power) {
		this.role = role;
		this.power = power;
	}

	// Property accessors

	public Integer getRpId() {
		return this.rpId;
	}

	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

}