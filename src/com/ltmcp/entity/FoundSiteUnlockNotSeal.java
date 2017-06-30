package com.ltmcp.entity;

import java.sql.Timestamp;

public class FoundSiteUnlockNotSeal {
	private Integer id;
	private String fsun_unfreeze_code;//解封码
	private String fsun_unfreeze_account;//解封人使用账号
	private String fsun_unfreeze_person;//解封人姓名
	private String fsun_unfreeze_posname;//解封未施封出现（发现）的地方
	private Integer fsun_unfreeze_posid;//解封地posid
	private Timestamp fsun_unfreeze_time;//解封地点发现未施封的时间，限制在当前一刻（还有一个限制是使用此系统之后时间）
	private String fsun_sela_posname;//根据追溯找到未施封站点（油库）
	private Integer fsun_seal_posid;//根据追溯找到未施封站点posid
	private String fsun_seal_earaname;//未施封下发片区名称
	private Integer fsun_seal_areaid;//根据追溯发现的未施封下发片区areaid
	private String fsun_company_name;//根据追溯发现所属分发的二级分公司
	private Integer fsun_company_comid;//所属分发二级分公司id（未施封追踪）
	private String fsun_seal_personuseccount;//本应该施封的账号名称(为了找人啊)
	
	
	public String getFsun_seal_personuseccount() {
		return fsun_seal_personuseccount;
	}
	public void setFsun_seal_personuseccount(String fsun_seal_personuseccount) {
		this.fsun_seal_personuseccount = fsun_seal_personuseccount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFsun_unfreeze_code() {
		return fsun_unfreeze_code;
	}
	public void setFsun_unfreeze_code(String fsun_unfreeze_code) {
		this.fsun_unfreeze_code = fsun_unfreeze_code;
	}
	public String getFsun_unfreeze_account() {
		return fsun_unfreeze_account;
	}
	public void setFsun_unfreeze_account(String fsun_unfreeze_account) {
		this.fsun_unfreeze_account = fsun_unfreeze_account;
	}
	public String getFsun_unfreeze_person() {
		return fsun_unfreeze_person;
	}
	public void setFsun_unfreeze_person(String fsun_unfreeze_person) {
		this.fsun_unfreeze_person = fsun_unfreeze_person;
	}
	public String getFsun_unfreeze_posname() {
		return fsun_unfreeze_posname;
	}
	public void setFsun_unfreeze_posname(String fsun_unfreeze_posname) {
		this.fsun_unfreeze_posname = fsun_unfreeze_posname;
	}
	public Integer getFsun_unfreeze_posid() {
		return fsun_unfreeze_posid;
	}
	public void setFsun_unfreeze_posid(Integer fsun_unfreeze_posid) {
		this.fsun_unfreeze_posid = fsun_unfreeze_posid;
	}
	public Timestamp getFsun_unfreeze_time() {
		return fsun_unfreeze_time;
	}
	public void setFsun_unfreeze_time(Timestamp fsun_unfreeze_time) {
		this.fsun_unfreeze_time = fsun_unfreeze_time;
	}
	public String getFsun_sela_posname() {
		return fsun_sela_posname;
	}
	public void setFsun_sela_posname(String fsun_sela_posname) {
		this.fsun_sela_posname = fsun_sela_posname;
	}
	public Integer getFsun_seal_posid() {
		return fsun_seal_posid;
	}
	public void setFsun_seal_posid(Integer fsun_seal_posid) {
		this.fsun_seal_posid = fsun_seal_posid;
	}
	public String getFsun_seal_earaname() {
		return fsun_seal_earaname;
	}
	public void setFsun_seal_earaname(String fsun_seal_earaname) {
		this.fsun_seal_earaname = fsun_seal_earaname;
	}
	public Integer getFsun_seal_areaid() {
		return fsun_seal_areaid;
	}
	public void setFsun_seal_areaid(Integer fsun_seal_areaid) {
		this.fsun_seal_areaid = fsun_seal_areaid;
	}
	public String getFsun_company_name() {
		return fsun_company_name;
	}
	public void setFsun_company_name(String fsun_company_name) {
		this.fsun_company_name = fsun_company_name;
	}
	public Integer getFsun_company_comid() {
		return fsun_company_comid;
	}
	public void setFsun_company_comid(Integer fsun_company_comid) {
		this.fsun_company_comid = fsun_company_comid;
	}
	
}
