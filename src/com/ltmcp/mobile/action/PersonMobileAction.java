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
 * 安卓操作人员接口
 * 
 * @author Administrator
 * 
 */
public class PersonMobileAction extends BaseAction {
	private PersonMobileBiz personMobileBiz;
	private String name;// 名称
	private String password; // 旧密码
	private String newPassword;// 新密码

	public static final Logger logger = Logger.getLogger("apperson");
	/**
	 * 处理登录
	 */
	public void login() {
		Integer type = null;
		
		//获得person的权限类型
		type = personMobileBiz.getPersonType(name, password);		
		try {
			super.getResponse().getWriter().print(type);// 返回1，施封员; 返回2，解封员; 返回3，施解封员;
														//返回4，固定封签施解封员;返回5  管理员操作;返回-1，登录失败
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
//		logger.debug("APP==（"+name+"）==用户登录");
//		try {
//			type = personMobileBiz.getPersonType(name, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.debug("APP=0=（"+name+"）用户登录类型返回:"+type);
//			logger.fatal("APP"+name+"登录异常"+e);
//			try {
//				super.getResponse().getWriter().print(-1);
//				logger.debug("APP=a=（"+name+"）用户登录类型返回：-1");
//				return;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				logger.fatal("APP"+name+"登录异常"+e1);
//			}
//		}
//		try {
//			super.getResponse().getWriter().print(type); // 返回1，施封员; 返回2，解封员; 返回3，施解封员
//			logger.debug("APP=1=（"+name+"）用户登录类型返回:"+type);											// 返回-1，登录失败
//			return;
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.debug("app用户类型返回:"+e);
//			try {
//				super.getResponse().getWriter().print(-1);
//				logger.debug("APP=b=（"+name+"）用户登录类型返回：-1");
//				return;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				logger.fatal("APP"+name+"登录异常"+e1);
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
	 * 获取操作人员所对应的油库或者油站呃名称
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
				//增加限制时间的查询
				map.put("time", limt.getLimitime());
				
				map.put("vediotime", limt.getStat());
			}
			arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			jsonObj.put("obj", arrList);
			super.outPrintJsonByObject(jsonObj, null);// 输出
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("APP=b=（"+name+"）用户登录获取站点异常"+e);
		}
	}

	/**
	 * 修改密码
	 */
	public void change() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		
		try {
			personMobileBiz.changePerson(name, password, newPassword);
			System.out.println("APP==（"+name+"）--用户--（"+time+"）--将密码修改为--"+new String(Base64.decodeBase64(CharTools.allToUTF8(newPassword)), "UTF-8"));
			super.getResponse().getWriter().print(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("APP=b=（"+name+"）用户修改密码异常"+e);
			try {
				super.getResponse().getWriter().print(1);
				logger.debug("APP=b=（"+name+"）用户修改密码异常");
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
