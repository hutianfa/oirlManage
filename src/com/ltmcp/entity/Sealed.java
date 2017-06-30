package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Sealed entity. @author MyEclipse Persistence Tools
 */

public class Sealed implements java.io.Serializable {

	// Fields

	private Integer seaId; //�˵�id��������
	private Car car;//�����ĳ�����Ϣ
	private Freeze freeze;//������ ����
	private Person person;//������ ��Ա��
	private Position position;//������ λ����Ϣ��
	private DimensionalBarCode dimensionalBarCode;//�����Ķ�ά��ע�ᱣ���
	private Company company;//�����Ĺ�˾��
	private Timestamp seaTime;//ʩ���˵�������ʱ��
	private String seaPhoto;//ʩ���������Ƭ
	private String seaLockCode;
	private String seaLongitude;//����
	private String seaLatitude;//γ��
	private Integer seaStatus;//�˵�״̬
	private String actualTime;
	private String seaImg;//¼��ı����ֶ�

	private String photo_check_tip;
	private Integer photo_check_status;
	
	private Integer tag; //��Ȩ��Ϣ�ı༭
	private String seaNumber;//ʩ���˵��ı��
	private String avgTime;//ƽ��ʱ��
	private String parcentTime;
	private String timeOut;//��ʱ
	private Set excRecords = new HashSet(0);
	private Set freezes = new HashSet(0);

	
	private String youPinName;
	private String youPinPath;
	// Constructors

	/** default constructor */
	public Sealed() {
	}

	public Integer getSeaId() {
		return seaId;
	}

	public void setSeaId(Integer seaId) {
		this.seaId = seaId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Freeze getFreeze() {
		return freeze;
	}

	public void setFreeze(Freeze freeze) {
		this.freeze = freeze;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public DimensionalBarCode getDimensionalBarCode() {
		return dimensionalBarCode;
	}

	public void setDimensionalBarCode(DimensionalBarCode dimensionalBarCode) {
		this.dimensionalBarCode = dimensionalBarCode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Timestamp getSeaTime() {
		return seaTime;
	}

	public void setSeaTime(Timestamp seaTime) {
		this.seaTime = seaTime;
	}

	public String getSeaPhoto() {
		return seaPhoto;
	}

	public void setSeaPhoto(String seaPhoto) {
		this.seaPhoto = seaPhoto;
	}

	public String getSeaLockCode() {
		return seaLockCode;
	}

	public void setSeaLockCode(String seaLockCode) {
		this.seaLockCode = seaLockCode;
	}

	public String getSeaLongitude() {
		return seaLongitude;
	}

	public void setSeaLongitude(String seaLongitude) {
		this.seaLongitude = seaLongitude;
	}

	public String getSeaLatitude() {
		return seaLatitude;
	}

	public void setSeaLatitude(String seaLatitude) {
		this.seaLatitude = seaLatitude;
	}

	public Integer getSeaStatus() {
		return seaStatus;
	}

	public void setSeaStatus(Integer seaStatus) {
		this.seaStatus = seaStatus;
	}

	public String getActualTime() {
		return actualTime;
	}

	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}

	public String getParcentTime() {
		return parcentTime;
	}

	public void setParcentTime(String parcentTime) {
		this.parcentTime = parcentTime;
	}

	public Set getExcRecords() {
		return excRecords;
	}

	public void setExcRecords(Set excRecords) {
		this.excRecords = excRecords;
	}

	public Set getFreezes() {
		return freezes;
	}

	public void setFreezes(Set freezes) {
		this.freezes = freezes;
	}

	public String getSeaImg() {
		return seaImg;
	}

	public void setSeaImg(String seaImg) {
		this.seaImg = seaImg;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getPhoto_check_tip() {
		return photo_check_tip;
	}

	public void setPhoto_check_tip(String photo_check_tip) {
		this.photo_check_tip = photo_check_tip;
	}

	public Integer getPhoto_check_status() {
		return photo_check_status;
	}

	public void setPhoto_check_status(Integer photo_check_status) {
		this.photo_check_status = photo_check_status;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getSeaNumber() {
		return seaNumber;
	}

	public void setSeaNumber(String seaNumber) {
		this.seaNumber = seaNumber;
	}

	public String getYouPinName() {
		return youPinName;
	}

	public void setYouPinName(String youPinName) {
		this.youPinName = youPinName;
	}

	public String getYouPinPath() {
		return youPinPath;
	}

	public void setYouPinPath(String youPinPath) {
		this.youPinPath = youPinPath;
	}
}