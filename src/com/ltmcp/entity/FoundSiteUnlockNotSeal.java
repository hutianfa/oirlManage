package com.ltmcp.entity;

import java.sql.Timestamp;

public class FoundSiteUnlockNotSeal {
	private Integer id;
	private String fsun_unfreeze_code;//�����
	private String fsun_unfreeze_account;//�����ʹ���˺�
	private String fsun_unfreeze_person;//���������
	private String fsun_unfreeze_posname;//���δʩ����֣����֣��ĵط�
	private Integer fsun_unfreeze_posid;//����posid
	private Timestamp fsun_unfreeze_time;//���ص㷢��δʩ���ʱ�䣬�����ڵ�ǰһ�̣�����һ��������ʹ�ô�ϵͳ֮��ʱ�䣩
	private String fsun_sela_posname;//����׷���ҵ�δʩ��վ�㣨�Ϳ⣩
	private Integer fsun_seal_posid;//����׷���ҵ�δʩ��վ��posid
	private String fsun_seal_earaname;//δʩ���·�Ƭ������
	private Integer fsun_seal_areaid;//����׷�ݷ��ֵ�δʩ���·�Ƭ��areaid
	private String fsun_company_name;//����׷�ݷ��������ַ��Ķ����ֹ�˾
	private Integer fsun_company_comid;//�����ַ������ֹ�˾id��δʩ��׷�٣�
	private String fsun_seal_personuseccount;//��Ӧ��ʩ����˺�����(Ϊ�����˰�)
	
	
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
