package com.ltmcp.entity;

import java.util.HashSet;
import java.util.Set;

public class BasDict implements java.io.Serializable {

	private Integer dictId;  //字典id
	
	private Integer dictType; //字典类型
	
	private String dictValue;  //字段对应的数据
	
	private Integer dictIsEditable;
	
	private Integer dictVersion; 
	
	private Set excRecords = new HashSet(0);
	private Set persons = new HashSet(0);
	private Set positions = new HashSet(0);

	public BasDict() {
	}

	/** minimal constructor */
	public BasDict(Integer dictType, String dictValue, Integer dictIsEditable,
			Integer dictVersion) {
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictIsEditable = dictIsEditable;
		this.dictVersion = dictVersion;
	}

	/** full constructor */
	public BasDict(Integer dictType, String dictValue, Integer dictIsEditable,
			Integer dictVersion, Set excRecords, Set persons, Set positions) {
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictIsEditable = dictIsEditable;
		this.dictVersion = dictVersion;
		this.excRecords = excRecords;
		this.persons = persons;
		this.positions = positions;
	}

	// Property accessors

	public Integer getDictId() {
		return this.dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public Integer getDictType() {
		return this.dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public String getDictValue() {
		return this.dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Integer getDictIsEditable() {
		return this.dictIsEditable;
	}

	public void setDictIsEditable(Integer dictIsEditable) {
		this.dictIsEditable = dictIsEditable;
	}

	public Integer getDictVersion() {
		return this.dictVersion;
	}

	public void setDictVersion(Integer dictVersion) {
		this.dictVersion = dictVersion;
	}

	public Set getExcRecords() {
		return this.excRecords;
	}

	public void setExcRecords(Set excRecords) {
		this.excRecords = excRecords;
	}

	public Set getPersons() {
		return this.persons;
	}

	public void setPersons(Set persons) {
		this.persons = persons;
	}

	public Set getPositions() {
		return this.positions;
	}

	public void setPositions(Set positions) {
		this.positions = positions;
	}

}