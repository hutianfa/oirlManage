package com.ltmcp.mail.entity;
/**
 * 邮箱实体类
 * @author Administrator
 *
 */
public class CityMail {
	private int id;
	private String siteMail;	//邮件地址
	private int rankMail;	//级别
	private String standby1;	//备用字段
	private String standby2;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSiteMail() {
		return siteMail;
	}
	public void setSiteMail(String siteMail) {
		this.siteMail = siteMail;
	}
	public int getRankMail() {
		return rankMail;
	}
	public void setRankMail(int rankMail) {
		this.rankMail = rankMail;
	}
	public String getStandby1() {
		return standby1;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	public String getStandby2() {
		return standby2;
	}
	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	@Override
	public String toString() {
		return "CityMail [id=" + id + ", siteMail=" + siteMail + ", rankMail="
				+ rankMail + ", standby1=" + standby1 + ", standby2="
				+ standby2 + "]";
	}
	public CityMail(int id, String siteMail, int rankMail, String standby1,
			String standby2) {
		super();
		this.id = id;
		this.siteMail = siteMail;
		this.rankMail = rankMail;
		this.standby1 = standby1;
		this.standby2 = standby2;
	}
	public CityMail() {
		super();
		// TODO Auto-generated constructor stub
	}
}
