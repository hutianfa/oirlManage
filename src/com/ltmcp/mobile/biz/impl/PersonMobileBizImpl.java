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

				// ����
				try {
					name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				// ����
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
			throw new Exception("������û���Ϊ��!");
		}
		if ("".equals(name) || "".equals(password)) {
			throw new Exception("�û���������Ϊ���ַ���!");
		}
		try {
			name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// ����
		try {
			password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// ����
		try {
			newPassword = new String(Base64.decodeBase64(CharTools.allToUTF8(newPassword)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// ����
		Person p = new Person();
		p.setPerName(name);
		p.setPerPassword(MD5.md5crypt(password));
		Integer id = personMobileDao.queryPersonId(p);
		if (id == null) {
			throw new Exception(name + ",���û�������!");
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
		 * Base64.decodeBase64(CharTools.allToUTF8(newPassword)),"UTF-8");//����
		 * newPassword=MD5.md5crypt(newPassword);
		 * personMobileDao.updatePerson(person,newPassword);//���� }else{ name=new
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
				// ��һ��������
				try {
					name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}// ����
				try {
					password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}// ����

				// �ڶ�������ȡ����
				
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
				
				// ���������ж�����
				Integer lock = Comm.PERSON_LOCK;
				Integer unlock = Comm.PERSON_UNLOCK;
				Integer unlock_lock = Comm.PERSON_UNLOCK_LOCK;
				Integer fix = Comm.PERSON_FIX_UNLOCK_LOCK;
				Integer adm = Comm.PERSON_ADMIN;
				
				if ((type + 0) == (lock + 0)) {
					System.out.println("app��:"+name+"---("+time+")�ѵ�¼��");
					return 1; // ʩ��Ա
				} else if ((type + 0) == (unlock + 0)) {
					System.out.println("app��:"+name+"---("+time+")�ѵ�¼��");
					return 2; // ���Ա
				}else if ((type + 0) == (unlock_lock + 0)) {
					System.out.println("app��:"+name+"---("+time+")�ѵ�¼��");
					return 3; // ʩ���Ա
				} else if ((type + 0) == (fix + 0)) {
					System.out.println("app��:"+name+"---("+time+")�ѵ�¼��");
					return 4; // ʩ���Ա
				} else if ((type + 0) == (adm + 0)) {
					System.out.println("app��:"+name+"---("+time+")�ѵ�¼��");
					return 5; // ʩ���Ա
				} else {
					System.out.println("app��:"+name+"---("+time+")��¼ʧ�ܣ�");
					return -1;// ʲô������
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
//			 //��һ��������
//			try {
//				String username = new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}// ����
//			try {
//				String pwd= new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}// ����
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
