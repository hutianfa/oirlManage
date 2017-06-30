package com.ltmcp.action;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;
import com.ltmcp.service.firstOrderService;


@SuppressWarnings("serial")
public class firstOrderAction extends BaseAction{
	private Integer id;//��Ϊ�������
	private Integer status;
	private Integer num;
	private String name;
	private String phone;
	private String address;
	private firstOrderService firstOrderService;
	


/***
 * web����Ӷ�����Ϣ
 * @param
 */
public void add()
{
	Admin admin = null;
	HttpSession session = ServletActionContext.getRequest().getSession();
	admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
	int comid = admin.getCompany().getComId();
	//System.out.println("��ӡ��˾:"+comid);	
	
	first_order fo = new first_order();
	fo.setComname("");
	fo.setComid(comid);
	
	fo.setFahuo_number(num);
	fo.setShouhuo_person(name);
	fo.setShouhuo_phone(phone);
	fo.setShouhuo_address(address);
	fo.setStatus(0);
	Timestamp ts=new Timestamp(System.currentTimeMillis());
	fo.setXiadan_time(new Timestamp(System.currentTimeMillis()));
	System.out.println("��ӡweb���¶�����Ϣ��"+num+";"+name+";"+phone+";"+address+";"+ts);
	try {
		firstOrderService.addFirst_order(fo);
		super.getResponse().getWriter().print(0);
		System.out.println("��Ӷ����ɹ�����");
	} catch (Exception e) {
		try {
			super.getResponse().getWriter().print(1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	
}
/***
 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
 * @param
 */
public void orderModify(){
	//�ж϶���״̬��Ϊ1������2�����ܸ��¶���,״̬Ϊ0���ܸ��£�����������ʱֻ�������������������
//	boolean sta = firstOrderService.checkOrderStatus(id,status);
//	if(sta){
//		try {
//			super.getResponse().getWriter().print(0);//web��ʾ���ܸ����Ѿ��������߸ö����Ѿ��ջ�
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}else{
		try {
			Admin admin = null;
			HttpSession session = ServletActionContext.getRequest().getSession();
			admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			int comid = admin.getCompany().getComId();
			
			first_order fo = new first_order();
			fo.setFahuo_number(num);
			fo.setId(id);
			fo.setXiadan_time(new Timestamp(System.currentTimeMillis()));
			firstOrderService.orderModify(id,num);
			super.getResponse().getWriter().print(0);//���¶����ɹ�
		} catch (IOException e) {
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	//}	
}
/***
 * web��չʾ��������Ӷ���֮��չʾ��û������ҳ����
 * @param
 */
public void list(){
	Admin admin = null;
	HttpSession session = ServletActionContext.getRequest().getSession();
	admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
	int comid = admin.getCompany().getComId();
	
	List<first_order> first_orderr = firstOrderService.queryFirst_order(comid);
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	for(first_order f : first_orderr){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", f.getId());
		//map.put("box_code", f.getBox_code());
		map.put("status", f.getStatus());
		//map.put("fahuo_person", f.getFahuo_person());
		//map.put("fahuo_phone", f.getFahuo_phone());
		//map.put("fahuo_address", f.getFahuo_address());
		map.put("fahuo_number", f.getFahuo_number());
		//map.put("bagcode_number", f.getBagcode_number());
		map.put("shouhuo_person", f.getShouhuo_person());
		map.put("shouhuo_phone", f.getShouhuo_phone());
		map.put("shouhuo_address", f.getShouhuo_address());
		map.put("tracking_number", f.getTracking_number());
		map.put("order_number", f.getOrder_number());
		map.put("xiadan_time", f.getXiadan_time());
		//map.put("fahuo_time", f.getFahuo_time());
		//map.put("shouhuo_time", f.getShouhuo_time());
		list.add(map);
	}	
	//���Դ�ӡ
	/*for(first_order f : first_orderr){
		System.out.println("�µ��ˣ�"+f.getShouhuo_person()+"���µ�������"+f.getFahuo_number()+"���µ��˵�ַ��"+f.getShouhuo_address()+",�µ��˵绰��"+f.getShouhuo_phone()+",�µ�ʱ�䣺"+f.getXiadan_time());
	}*/
	super.outPrintJsonByArray(list);
	//return "list";
}


	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public firstOrderService getFirstOrderService() {
		return firstOrderService;
	}

	public void setFirstOrderService(firstOrderService firstOrderService) {
		this.firstOrderService = firstOrderService;
	}


}
