package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.biz.BasDictMobileBiz;
import com.ltmcp.mobile.dao.BasDictMobileDao;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class BasDictMobileBizImpl implements BasDictMobileBiz {

	private BasDictMobileDao basDictMobileDao;
	private PersonMobileDao personMobileDao;

	@Override
	public List<BasDict> searchExceptionList(String name, String password) {
		Person person = new Person();
		if (null == name || password == null) {
			return new ArrayList<BasDict>();
		}
		if ("".equals(name) || "".equals(password)) {
			return new ArrayList<BasDict>();
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		try {
			password = new String(Base64.decodeBase64(CharTools
					.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b = personMobileDao.queryPersonExist(person);
		if (true != b) {
			return new ArrayList<BasDict>();
		}
		List<BasDict> list = basDictMobileDao.findExceptionList();
		return list;
	}

	public BasDictMobileDao getBasDictMobileDao() {
		return basDictMobileDao;
	}

	public void setBasDictMobileDao(BasDictMobileDao basDictMobileDao) {
		this.basDictMobileDao = basDictMobileDao;
	}

	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

	@Override
	public List<BasDict> searchOilPinList(String name, String password) {
		Person person = new Person();
		if (null == name || password == null) {
			return new ArrayList<BasDict>();
		}
		if ("".equals(name) || "".equals(password)) {
			return new ArrayList<BasDict>();
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		try {
			password = new String(Base64.decodeBase64(CharTools
					.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b = personMobileDao.queryPersonExist(person);

		if (true != b) {

			return new ArrayList<BasDict>();
		}
		List<BasDict> list = basDictMobileDao.findOilPinList();
		return list;

	}

	@Override
	public List<BasDict> searchFixList(String name, String password) {
		Person person = new Person();
		if (null == name || password == null) {
			return new ArrayList<BasDict>();
		}
		if ("".equals(name) || "".equals(password)) {
			return new ArrayList<BasDict>();
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		try {
			password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b = personMobileDao.queryPersonExist(person);
		if (true != b) {
			return new ArrayList<BasDict>();
		}
		List<BasDict> list = basDictMobileDao.findFixList();
		return list;
	}
	
	
	@Override
	public List<BasDict> searchEmList(String name, String password) {
		Person person = new Person();
		if (null == name || password == null) {
			return new ArrayList<BasDict>();
		}
		if ("".equals(name) || "".equals(password)) {
			return new ArrayList<BasDict>();
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		try {
			password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b = personMobileDao.queryPersonExist(person);
		if (true != b) {
			return new ArrayList<BasDict>();
		}
		List<BasDict> list = basDictMobileDao.findEmList();
		return list;
	}
	
	@Override
	public List<BasDict> searchTagList(String name, String password) {
		Person person = new Person();
		if (null == name || password == null) {
			return new ArrayList<BasDict>();
		}
		if ("".equals(name) || "".equals(password)) {
			return new ArrayList<BasDict>();
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		try {
			password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b = personMobileDao.queryPersonExist(person);
		if (true != b) {
			return new ArrayList<BasDict>();
		}
		List<BasDict> list = basDictMobileDao.findTagList();
		return list;
	}

}
