package com.ltmcp.mobile.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.biz.PersonMobileBiz;
import com.ltmcp.util.CharTools;

/**
 * ��׿������Ա�ӿ�
 * 
 * @author Administrator
 * 
 */
public class PersonMobileAction extends BaseAction {
	private PersonMobileBiz personMobileBiz;
	private String name;// ����
	private String password; // ������
	private String newPassword;// ������

	public static final Logger logger = Logger.getLogger("apperson");
	/**
	 * �����¼
	 */
	public void login() {
		Integer type = null;
		
		//���person��Ȩ������
		type = personMobileBiz.getPersonType(name, password);		
		try {
			super.getResponse().getWriter().print(type);// ����1��ʩ��Ա; ����2�����Ա; ����3��ʩ���Ա;
														//����4���̶���ǩʩ���Ա;����5  ����Ա����;����-1����¼ʧ��
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
//		logger.debug("APP==��"+name+"��==�û���¼");
//		try {
//			type = personMobileBiz.getPersonType(name, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.debug("APP=0=��"+name+"���û���¼���ͷ���:"+type);
//			logger.fatal("APP"+name+"��¼�쳣"+e);
//			try {
//				super.getResponse().getWriter().print(-1);
//				logger.debug("APP=a=��"+name+"���û���¼���ͷ��أ�-1");
//				return;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				logger.fatal("APP"+name+"��¼�쳣"+e1);
//			}
//		}
//		try {
//			super.getResponse().getWriter().print(type); // ����1��ʩ��Ա; ����2�����Ա; ����3��ʩ���Ա
//			logger.debug("APP=1=��"+name+"���û���¼���ͷ���:"+type);											// ����-1����¼ʧ��
//			return;
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.debug("app�û����ͷ���:"+e);
//			try {
//				super.getResponse().getWriter().print(-1);
//				logger.debug("APP=b=��"+name+"���û���¼���ͷ��أ�-1");
//				return;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				logger.fatal("APP"+name+"��¼�쳣"+e1);
//			}
//		}
	}

	
//	public void login() {		
//		person = personMobileBiz.getPersonType(name, password);
//		Limt limt = personMobileBiz.Limitime();
//		if(person != null){
//			try {
//				Position p = person.getPosition();
//				Map<String, Object> map = new HashMap<String, Object>();
//				
//				map.put("type", person.getBasDict().getDictId());
//				map.put("truename", person.getPerTrueName());
//				map.put("position", p.getPosName());
//				map.put("time", limt.getLimitime());
//				map.put("stat", limt.getStat());
//				JSONObject json = new JSONObject().fromObject(map);
//				super.getPringWriter().print(json.toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else{
//			super.getPringWriter().print("-1");
//		}	
//  }
	/**
	 * ��ȡ������Ա����Ӧ���Ϳ������վ������
	 */
	public void getPerBydictName() {
		try {
			List<Person> list = (List<Person>) personMobileBiz.findPersonByPos(name);
			Limt limt = personMobileBiz.Limitime();
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			ArrayList<Object> arrList = new ArrayList<Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			
			for (int i = 0; i < list.size(); i++) {
				
				map.put("perName", list.get(i).getPerTrueName());
				map.put("dict", list.get(i).getPosition().getPosName());
				//��������ʱ��Ĳ�ѯ
				map.put("time", limt.getLimitime());
				
				map.put("vediotime", limt.getStat());
			}
			arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			jsonObj.put("obj", arrList);
			super.outPrintJsonByObject(jsonObj, null);// ���
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("APP=b=��"+name+"���û���¼��ȡվ���쳣"+e);
		}
	}

	/**
	 * �޸�����
	 */
	public void change() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		
		try {
			personMobileBiz.changePerson(name, password, newPassword);
			System.out.println("APP==��"+name+"��--�û�--��"+time+"��--�������޸�Ϊ--"+new String(Base64.decodeBase64(CharTools.allToUTF8(newPassword)), "UTF-8"));
			super.getResponse().getWriter().print(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("APP=b=��"+name+"���û��޸������쳣"+e);
			try {
				super.getResponse().getWriter().print(1);
				logger.debug("APP=b=��"+name+"���û��޸������쳣");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonMobileBiz getPersonMobileBiz() {
		return personMobileBiz;
	}

	public void setPersonMobileBiz(PersonMobileBiz personMobileBiz) {
		this.personMobileBiz = personMobileBiz;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
