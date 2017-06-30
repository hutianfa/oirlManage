package com.ltmcp.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Freeze entity. @author MyEclipse Persistence Tools
 */

public class Freeze implements java.io.Serializable {

	// Fields

	private Integer freId; //�� id
	private Car car; //�����ĳ�����Ϣ��
	private Person person; // ������ person��
	private Sealed sealed; //������ʩ����Ϣ��
	private Position position; //������λ����Ϣ��
	private DimensionalBarCode dimensionalBarCode;//������ ��ά�� �洢��
	private Company company; //�����Ĺ�˾��Ϣ
	private Timestamp freTime;//������ʱ��
	private String frePhoto;// ���ʱ ��ȡ��ͼƬ
	private String freLockCode;
	private String freLongitude;
	private String freLatitud;
	private Integer freStatus; //���״̬
	private String freImg; //�ֶ�������  app¼����Ƶ
	private String powCodName; //��Ȩ����Ϣ
	private String freNumber; //�������� �˵����
	private Integer powerTips; //��Ȩԭ��

	private String photo_check_tip;
	private Integer photo_check_status;
	
	private Set excRecords = new HashSet(0);
	private Set sealeds = new HashSet(0);

	// Constructors

	/** default constructor */
	public Freeze() {
	}

	/** minimal constructor */
	public Freeze(Car car, Person person, Company company, Timestamp freTime,
			Integer freStatus,String photo_check_tip,Integer photo_check_status,
			String powCodName,Integer powerTips,String freNumber) {
		this.car = car;
		this.person = person;
		this.company = company;
		this.freTime = freTime;
		this.freStatus = freStatus;
		this.photo_check_tip = photo_check_tip;
		this.photo_check_status = photo_check_status;
		this.powCodName = powCodName;
		this.powerTips = powerTips;
		this.freNumber = freNumber;
	}

	/** full constructor */
	public Freeze(Car car, Person person, Sealed sealed, Position position,
			DimensionalBarCode dimensionalBarCode, Company company,
			Timestamp freTime, String frePhoto, String freLockCode,
			String freLongitude, String freLatitud, Integer freStatus,
			String freImg, Set excRecords, Set sealeds,String photo_check_tip,
			Integer photo_check_status,String powCodName,Integer powerTips,String freNumber) {
		this.car = car;
		this.person = person;
		this.sealed = sealed;
		this.position = position;
		this.dimensionalBarCode = dimensionalBarCode;
		this.company = company;
		this.freTime = freTime;
		this.frePhoto = frePhoto;
		this.freLockCode = freLockCode;
		this.freLongitude = freLongitude;
		this.freLatitud = freLatitud;
		this.freStatus = freStatus;
		this.freImg = freImg;
		this.powCodName = powCodName;
		this.excRecords = excRecords;
		this.sealeds = sealeds;
		this.photo_check_tip = photo_check_tip;
		this.photo_check_status = photo_check_status;
		this.powerTips = powerTips;
		this.freNumber = freNumber;
	}

	// Property accessors

	public Integer getFreId() {
		return this.freId;
	}

	public void setFreId(Integer freId) {
		this.freId = freId;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Sealed getSealed() {
		return this.sealed;
	}

	public void setSealed(Sealed sealed) {
		this.sealed = sealed;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public DimensionalBarCode getDimensionalBarCode() {
		return this.dimensionalBarCode;
	}

	public void setDimensionalBarCode(DimensionalBarCode dimensionalBarCode) {
		this.dimensionalBarCode = dimensionalBarCode;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Timestamp getFreTime() {
		return this.freTime;
	}

	public void setFreTime(Timestamp freTime) {
		this.freTime = freTime;
	}

	public String getFrePhoto() {
		return this.frePhoto;
	}

	public void setFrePhoto(String frePhoto) {
		this.frePhoto = frePhoto;
	}

	public String getFreLockCode() {
		return this.freLockCode;
	}

	public void setFreLockCode(String freLockCode) {
		this.freLockCode = freLockCode;
	}

	public String getFreLongitude() {
		return this.freLongitude;
	}

	public void setFreLongitude(String freLongitude) {
		this.freLongitude = freLongitude;
	}

	public String getFreLatitud() {
		return this.freLatitud;
	}

	public void setFreLatitud(String freLatitud) {
		this.freLatitud = freLatitud;
	}

	public Integer getFreStatus() {
		return this.freStatus;
	}

	public void setFreStatus(Integer freStatus) {
		this.freStatus = freStatus;
	}

	public Set getExcRecords() {
		return this.excRecords;
	}

	public void setExcRecords(Set excRecords) {
		this.excRecords = excRecords;
	}

	public Set getSealeds() {
		return this.sealeds;
	}

	public void setSealeds(Set sealeds) {
		this.sealeds = sealeds;
	}

	public String getFreImg() {
		return freImg;
	}

	public void setFreImg(String freImg) {
		this.freImg = freImg;
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

	public String getPowCodName() {
		return powCodName;
	}

	public void setPowCodName(String powCodName) {
		this.powCodName = powCodName;
	}

	public Integer getPowerTips() {
		return powerTips;
	}

	public void setPowerTips(Integer powerTips) {
		this.powerTips = powerTips;
	}

	public String getFreNumber() {
		return freNumber;
	}

	public void setFreNumber(String freNumber) {
		this.freNumber = freNumber;
	}
}