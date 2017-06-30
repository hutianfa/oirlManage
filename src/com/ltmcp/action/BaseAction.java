package com.ltmcp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action的父类，主要存放常用的方法
 * @author 
 *
 */
public class BaseAction extends ActionSupport{
	 
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public HttpSession getSession(){
		return this.getRequest().getSession();
	}
	
	public PrintWriter getPringWriter(){
		PrintWriter pw=null;
		try {
			pw=this.getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}
	
	/**
	 * 设置消息提示
	 * @param name
	 * @param tip
	 */
	public void setMessage(String name,String tip){
		if(null!=name&&!"".equals(tip)){
			this.getSession().setAttribute(name,tip);
		}
	}
	
	public String returnToViewList(Object obj){
		if(null != obj){
			return "list";
		}else{
			return "error";
		}
	}
	
	public String returnToViewDetailed(Object obj){
		if(null!=obj){
			System.out.println("跳转详情");
			return "detailed";
		}else{
			System.out.println("list");
			return "list";
		}
	}
	
	
	
	/**
	 * 向页面输出JSON
	 * @param obj Map,String,Object
	 */
	public void outPrintJsonByObject(Object obj){
		JSONObject data=new JSONObject(obj);
		try {
			this.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 向页面输出JSON表达式
	 * @param list Collection,String,JSONTokener
	 */
	public void outPrintJsonByArray(List list){
		JSONArray data=new JSONArray(list);
		try {
			this.getResponse().getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换成JSON并输出到页面
	 * @param obj 需要转换的对象
	 * @param ignoreAttribute 忽略的属性
	 */
	public void outPrintJsonByObject(Object obj,String[] ignoreAttribute) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setExcludes(ignoreAttribute);
		net.sf.json.JSONObject data = net.sf.json.JSONObject.fromObject(obj,jsonConfig);  
		PrintWriter out=this.getPringWriter();
		out.print(data);
		out.flush();
		out.close();
	}
	
	/**
	 * 转换成JSON并输出到页面
	 * @param list 需要转换的对象
	 * @param ignoreAttribute 忽略的属性
	 */
	public void outPrintJsonByArray(List list,String[] ignoreAttribute) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		if(ignoreAttribute!=null){
			jsonConfig.setExcludes(ignoreAttribute);
		}
		net.sf.json.JSONArray data = net.sf.json.JSONArray.fromObject(list, jsonConfig);  
		PrintWriter out=this.getPringWriter();
		out.print(data);
		out.flush();
		out.close();
	}
	
	public void outPrintJsonByMap(Map map){
		
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			net.sf.json.JSONArray data = net.sf.json.JSONArray.fromObject(map, jsonConfig);  
			PrintWriter out=this.getPringWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
