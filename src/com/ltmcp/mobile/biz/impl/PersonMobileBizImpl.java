package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.biz.PersonMobileBiz;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class PersonMobileBizImpl implements PersonMobileBiz {
	private PersonMobileDao personMobileDao;

	@Override
	public boolean validationPerson(String name, String password) {
		if (name != null && password != null) {
			if (!"".equals(name) && !"".equals(password)) {

				// 解密
				try {
					name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				// 解密
				try {
					password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Person p = new Person();
				p.setPerName(name);
				p.setPerPassword(MD5.md5crypt(password));
				return personMobileDao.queryPersonExist(p);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void changePerson(String name, String password, String newPassword)throws Exception {
		if (null == name || null == password) {
			throw new Exception("密码或用户名为空!");
		}
		if ("".equals(name) || "".equals(password)) {
			throw new Exception("用户名或密码为空字符串!");
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
		try {
			newPassword = new String(Base64.decodeBase64(CharTools.allToUTF8(newPassword)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// 解密
		Person p = new Person();
		p.setPerName(name);
		p.setPerPassword(MD5.md5crypt(password));
		Integer id = personMobileDao.queryPersonId(p);
		if (id == null) {
			throw new Exception(name + ",该用户不存在!");
		}
		p.setPerId(id);
		p.setPerPassword(MD5.md5crypt(newPassword));
		try {
			personMobileDao.updatePersonPasswordById(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * if(validationPerson(name, password)==true){ Person person=new
		 * Person(); person.setPerName(name);
		 * person.setPerPassword(MD5.md5crypt(password)); newPassword=new
		 * String(
		 * Base64.decodeBase64(CharTools.allToUTF8(newPassword)),"UTF-8");//解密
		 * newPassword=MD5.md5crypt(newPassword);
		 * personMobileDao.updatePerson(person,newPassword);//更新 }else{ name=new
		 * String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
		 * 
		 * }
		 */

	}

	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

	@Override
	public Integer getPersonType(String name, String password) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		
		if (name != null && password != null) {
			if (!"".equals(name) && !"".equals(password)) {
				// 第一步：解密
				try {
					name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}// 解密
				try {
					password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}// 解密

				// 第二步：获取类型
				
				Person p = new Person();
				p.setPerName(name);
				p.setPerPassword(MD5.md5crypt(password));
				
				
				Person person = personMobileDao.queryPerson(name, MD5.md5crypt(password));
				
				Integer type = null;
				
				if(person != null ){
					type = person.getBasDict().getDictId();
				}else if(person == null){
					type = -1;
				}				
				
				// 第三步：判断类型
				Integer lock = Comm.PERSON_LOCK;
				Integer unlock = Comm.PERSON_UNLOCK;
				Integer unlock_lock = Comm.PERSON_UNLOCK_LOCK;
				Integer fix = Comm.PERSON_FIX_UNLOCK_LOCK;
				Integer adm = Comm.PERSON_ADMIN;
				
				if ((type + 0) == (lock + 0)) {
					System.out.println("app端:"+name+"---("+time+")已登录！");
					return 1; // 施封员
				} else if ((type + 0) == (unlock + 0)) {
					System.out.println("app端:"+name+"---("+time+")已登录！");
					return 2; // 解封员
				}else if ((type + 0) == (unlock_lock + 0)) {
					System.out.println("app端:"+name+"---("+time+")已登录！");
					return 3; // 施解封员
				} else if ((type + 0) == (fix + 0)) {
					System.out.println("app端:"+name+"---("+time+")已登录！");
					return 4; // 施解封员
				} else if ((type + 0) == (adm + 0)) {
					System.out.println("app端:"+name+"---("+time+")已登录！");
					return 5; // 施解封员
				} else {
					System.out.println("app端:"+name+"---("+time+")登录失败！");
					return -1;// 什么都不是
				}
			} else {
				return -1;
			}
		} else {

			return -1;
		}
	}
		
//	public Person getPersonType(String name, String password) {
//		if (name == null && password == null && "".equals(name) && "".equals(password)) {
//			return null;
//		}
//			 //第一步：解密
//			try {
//				String username = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}// 解密
//			try {
//				String pwd= new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}// 解密
//
//			return personMobileDao.queryPerson(name, MD5.md5crypt(password));
//	}

	@Override
	public Limt Limitime() {
		return personMobileDao.queryLimitime();
	}
	

	@Override
	public List<Person> findPersonByPos(String perName) {
		return personMobileDao.queryPersonByPos(perName);
	}
}
