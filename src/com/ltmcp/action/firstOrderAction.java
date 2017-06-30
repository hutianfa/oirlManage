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
	private Integer id;//做为订单编号
	private Integer status;
	private Integer num;
	private String name;
	private String phone;
	private String address;
	private firstOrderService firstOrderService;
	


/***
 * web端添加订单信息
 * @param
 */
public void add()
{
	Admin admin = null;
	HttpSession session = ServletActionContext.getRequest().getSession();
	admin = (Admin) session.getAttribute(Comm.CURRENT_ADMIN);
	int comid = admin.getCompany().getComId();
	//System.out.println("打印公司:"+comid);	
	
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
	System.out.println("打印web端下订单信息："+num+";"+name+";"+phone+";"+address+";"+ts);
	try {
		firstOrderService.addFirst_order(fo);
		super.getResponse().getWriter().print(0);
		System.out.println("添加订单成功！！");
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
 * 修改订单（数量），web端点击保存之后传递数据后台此方法修改订单
 * @param
 */
public void orderModify(){
	//判断订单状态：为1或者是2都不能更新订单,状态为0才能更新，更新内容暂时只有数量，后续设计增加
//	boolean sta = firstOrderService.checkOrderStatus(id,status);
//	if(sta){
//		try {
//			super.getResponse().getWriter().print(0);//web提示不能更新已经发货或者该订单已经收货
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
			super.getResponse().getWriter().print(0);//更新订单成功
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
 * web端展示订单（添加订单之后展示，没有做分页处理）
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
	//测试打印
	/*for(first_order f : first_orderr){
		System.out.println("下单人："+f.getShouhuo_person()+"，下单数量："+f.getFahuo_number()+"，下单人地址："+f.getShouhuo_address()+",下单人电话："+f.getShouhuo_phone()+",下单时间："+f.getXiadan_time());
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
