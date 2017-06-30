package com.ltmcp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Power entity. @author MyEclipse Persistence Tools
 */

public class Power implements java.io.Serializable {

	// Fields

	private Integer poId;//表id
	private Integer poParentId;
	private String poText;//权限描述
	private String poUrl; // 权限菜单
	private String poTip;
	private Integer poShow;//权限时候生效
	private Set rolePowers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Power() {
	}

	/** minimal constructor */
	public Power(Integer poShow) {
		this.poShow = poShow;
	}

	/** full constructor */
	public Power(Integer poParentId, String poText, String poUrl, String poTip,
			Integer poShow, Set rolePowers) {
		this.poParentId = poParentId;
		this.poText = poText;
		this.poUrl = poUrl;
		this.poTip = poTip;
		this.poShow = poShow;
		this.rolePowers = rolePowers;
	}

	// Property accessors

	public Integer getPoId() {
		return this.poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	public Integer getPoParentId() {
		return this.poParentId;
	}

	public void setPoParentId(Integer poParentId) {
		this.poParentId = poParentId;
	}

	public String getPoText() {
		return this.poText;
	}

	public void setPoText(String poText) {
		this.poText = poText;
	}

	public String getPoUrl() {
		return this.poUrl;
	}

	public void setPoUrl(String poUrl) {
		this.poUrl = poUrl;
	}

	public String getPoTip() {
		return this.poTip;
	}

	public void setPoTip(String poTip) {
		this.poTip = poTip;
	}

	public Integer getPoShow() {
		return this.poShow;
	}

	public void setPoShow(Integer poShow) {
		this.poShow = poShow;
	}

	public Set getRolePowers() {
		return this.rolePowers;
	}

	public void setRolePowers(Set rolePowers) {
		this.rolePowers = rolePowers;
	}

}