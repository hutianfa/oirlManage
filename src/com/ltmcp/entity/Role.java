package com.ltmcp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Integer roleId;//角色id
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private Set admins = new HashSet(0);
	private Set rolePowers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String roleName) {
		this.roleName = roleName;
	}

	/** full constructor */
	public Role(String roleName, String roleDesc, Set admins, Set rolePowers) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.admins = admins;
		this.rolePowers = rolePowers;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set admins) {
		this.admins = admins;
	}

	public Set getRolePowers() {
		return this.rolePowers;
	}

	public void setRolePowers(Set rolePowers) {
		this.rolePowers = rolePowers;
	}

}