package com.ltmcp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.SealedService;

public class ExcRecordAction extends BaseAction {
	
	private PageBean pageBean;
	private ExcRecordCondition condition; // 查询列表时的条件
	private ExcRecordService excRecordService;
	private ExcRecord excRecord; // 输出到页面详细内容的对象
	private Integer id; // 需要获取异常详细信息的ID
	private List<ExcRecord> list = new ArrayList<ExcRecord>();
	private List<BasDict> exceptionTypes; // 异常类型集合
	private Integer dictId; // 接收传回来的 油库Id
	private Integer endId;// 加油站Id
	private Double avg;
	private List<Sealed> sealedList;
	private SealedService sealedService;
	private Double parcent;
	private SealedCondition con;
	private String excText;// 接收传回来的处理意见
	private String newexcText;
	private Integer newid;
	private List<NewErrors> nelist = new ArrayList<NewErrors>();//设置newErrors的list接收

	/**
	 * 根据条件查询未处理异常
	 * list()才是条件查询的最主要的方法
	 * 条件：condition.excType=2                      （2施封未解封，0施封码未注册，1解封码未注册）
	 *     condition.carFlapper=汽车牌号   （不要）
	 *     condition.beginTime=2016-12-15%2010:34:12   
	 *     condition.endTime=2016-12-22%2010:34:12
	 *     condition.excStatus=1                    异常的状态(0:已处理，1:未处理)
	 *     condition.posid=             站点id
	 *     pageBean.curentPage=         当前页
	 * @return
	 */
	public String list() {
		pageBean = excRecordService.searchExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordService.searchExceptionTypes();
		return super.returnToViewList(pageBean);
	}

	/**
	 * 查询非法操作异常处理 
	 *  * 条件：condition.excType=2                      （2施封未解封，0施封码未注册，1解封码未注册）
	 *     condition.carFlapper=汽车牌号   （不要）
	 *     condition.beginTime=2016-12-15%2010:34:12   
	 *     condition.endTime=2016-12-22%2010:34:12
	 *     condition.excStatus=1                    异常的状态(0:已处理，1:未处理)
	 *     condition.posid=             站点id
	 *     pageBean.curentPage=         当前页
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String illegality(){
		System.out.println("打印pageBean："+pageBean.toString());
		System.out.println("第二页会再次进入这个方法？？？？");
		pageBean = excRecordService.searchIllegality(condition, pageBean);
		System.out.println("总条数:"+pageBean.getTotalCount()+";打印当前页数："+pageBean.getCurentPage());
		
		nelist = pageBean.getList();
		
		exceptionTypes = excRecordService.searchExceptionTypes();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		if(nelist == null)
		{
			super.getPringWriter().print("null");
		}else
		{
			//ArrayList<Map> disList = new ArrayList<Map>();
			for(NewErrors neer : nelist)
			{
				map = new HashMap<String, Object>();
				//map.put("stain", "-2066");
				map.put("doName", neer.getDoName());//手持机登录解封账号名
				map.put("posiName",neer.getPosiName());//地址
				map.put("time",neer.getTime());
				map.put("code",neer.getCode());
				map.put("status",neer.getStatus());
				//System.out.println(neer.getRe()+":sedn");
				if("jf-(封签未施封)".endsWith(neer.getRe()))
				{
					map.put("excType", "2");
				}
				if("sf-(二维码未注册)".endsWith(neer.getRe()))
				{
					map.put("excType", "0");
				}
				if("jf-(二维码未注册)".equals(neer.getRe()))
				{
					map.put("excType", "1");
				}
				list.add(map);
			}
		}
		//super.outPrintJsonByObject(list);
		super.outPrintJsonByArray(list);
		return super.returnToViewList(pageBean);
		
	}
	/**
	 * 根据条件查询已处理异常
	 */
	@SuppressWarnings("unchecked")
	public String allHasHandle() {
		
		pageBean = excRecordService.selectExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordService.searchExceptionTypes();
		return SUCCESS;
	}
	/**
	 * 查询非法操作异常已处理 
	 */
	@SuppressWarnings("unchecked")
	public String allHasillegality() {
		pageBean = excRecordService.newselectExcRecords(condition, pageBean);
		nelist = pageBean.getList();
		exceptionTypes = excRecordService.searchExceptionTypes();
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 时间异常信息
	 * 
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public String queryExcParcent() {
//		Map map = excRecordService.findTimeList(condition);
//		avg = (Double) map.get("avg");
//		sealedList = (List<Sealed>) map.get("newList");
//		return SUCCESS;
//	}
//
//	public String queryExc() {
//		return "queryExc";
//	}
//
//	/**
//	 * 超时异常 超出施封运单48小时 没有解封信息
//	 * 
//	 * @return
//	 */
//	public String queryTimeOut() {
//		pageBean = excRecordService.findTimeOutList(condition, pageBean);
//		sealedList = pageBean.getList();
//		return super.returnToViewList(pageBean);
//	}
//
//	public String queryExcTimeOut() {
//		return "queryExcTimeOut";
//	}


	/**
	 * 查询全部异常信息 未处理  导出excel
	 * 
	 * @return
	 */
	public String allExcList() {
		list = excRecordService.findAllRecords(condition);
		exceptionTypes = excRecordService.searchExceptionTypes();
		return super.returnToViewList(list);
	}



	/**
	 * 获取详细信息
	 * 
	 * @return
	 */
	public String detailed() {
		if (null == id) {
			return "error";
		}
		excRecord = excRecordService.getExcRecord(id);
		return super.returnToViewDetailed(excRecord);
	}

	/**
	 * 改变异常状态
	 */
	public void change() {

		if (null != id) {
			try {
				excRecordService.changeExcRecordStatus(id);
				super.getResponse().getWriter().print(0); // 处理成功
				return;
			} catch (Exception e) {
				try {
					super.getResponse().getWriter().print(1);
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				} // 处理失败
			}
		}
		try {
			super.getResponse().getWriter().print(1);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
   
	public void illegalitychange() {
		try {

			if (newid != null) {

				excRecordService.newupdateHandleMethod(newexcText, newid);
			}
			super.getResponse().getWriter().print(0); // 处理成功
			return;
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			} // 处理失败
		}
	}
	/**
	 * 将异常处理意见写入异常表
	 * 
	 * @return
	 */
	public void addExcHandleMethod() {
		try {

			if (id != null) {

				excRecordService.updateHandleMethod(excText, id);
			}
			super.getResponse().getWriter().print(0); // 处理成功
			return;
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			} // 处理失败
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExcRecord getExcRecord() {
		return excRecord;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public ExcRecordCondition getCondition() {
		return condition;
	}

	public void setCondition(ExcRecordCondition condition) {
		this.condition = condition;
	}

	public ExcRecordService getExcRecordService() {
		return excRecordService;
	}

	public void setExcRecordService(ExcRecordService excRecordService) {
		this.excRecordService = excRecordService;
	}

	public void setExcRecord(ExcRecord excRecord) {
		this.excRecord = excRecord;
	}

	public List<ExcRecord> getList() {
		return list;
	}

	public void setList(List<ExcRecord> list) {
		this.list = list;
	}

	public List<BasDict> getExceptionTypes() {
		return exceptionTypes;
	}

	public void setExceptionTypes(List<BasDict> exceptionTypes) {
		this.exceptionTypes = exceptionTypes;
	}

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public Integer getEndId() {
		return endId;
	}

	public void setEndId(Integer endId) {
		this.endId = endId;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public List<Sealed> getSealedList() {
		return sealedList;
	}

	public void setSealedList(List<Sealed> sealedList) {
		this.sealedList = sealedList;
	}

	public SealedService getSealedService() {
		return sealedService;
	}

	public void setSealedService(SealedService sealedService) {
		this.sealedService = sealedService;
	}

	// 新添加的
	public String getExcText() {
		return excText;
	}

	public void setExcText(String excText) {
		try {
			this.excText = new String(excText.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public SealedCondition getCon() {
		return con;
	}

	public void setCon(SealedCondition con) {
		this.con = con;
	}

	public String getNewexcText() {
		return newexcText;
	}

	public void setNewexcText(String newexcText) {
		try {
			this.newexcText = new String(newexcText.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Integer getNewid() {
		return newid;
	}

	public void setNewid(Integer newid) {
		this.newid = newid;
	}

}
