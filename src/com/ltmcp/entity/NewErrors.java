package com.ltmcp.entity;

import java.sql.Timestamp;

/**
 * NewErrors entity. @author MyEclipse Persistence Tools
 */

public class NewErrors implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer comid;//��˾id
	private String re;	//����ֵ
	private String code;	//��ά��
	private Timestamp time;	//ʱ��
	private String doName;	//APP������
	private int posid;	//վ��
	private String posiName;//վ������
	private String status;	//�������״̬
	private String opinion;	//�������
	private String detialName;	//������
	private String send_phone;//�绰����
	private Integer send_status;//���ͳɹ�״̬��1��
	


	public String getSend_phone() {
		return send_phone;
	}

	public void setSend_phone(String send_phone) {
		this.send_phone = send_phone;
	}

	public Integer getSend_status() {
		return send_status;
	}

	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRe() {
		return this.re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDoName() {
		return this.doName;
	}

	public void setDoName(String doName) {
		this.doName = doName;
	}

	public int getPosid() {
		return this.posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getDetialName() {
		return this.detialName;
	}

	public void setDetialName(String detialName) {
		this.detialName = detialName;
	}

	public String getPosiName() {
		return posiName;
	}

	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}

	public Integer getComid() {
		return comid;
	}

	public void setComid(Integer comid) {
		this.comid = comid;
	}

}