package com.ltmcp.mobile.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.action.BaseAction;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.biz.DistributionBiz;
import com.ltmcp.mobile.biz.ExcRecordServiceBiz;
import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.SealedService;

public class ExcRecordActionAp extends BaseAction {
	private DistributionBiz distributionBiz;
	public DistributionBiz getDistributionBiz() {
		return distributionBiz;
	}

	public void setDistributionBiz(DistributionBiz distributionBiz) {
		this.distributionBiz = distributionBiz;
	}

	private PageBean pageBean;
	private ExcRecordCondition condition; // 查询列表时的条件
	private ExcRecordServiceBiz excRecordServiceBiz;
	
	public ExcRecordServiceBiz getExcRecordServiceBiz() {
		return excRecordServiceBiz;
	}

	public void setExcRecordServiceBiz(ExcRecordServiceBiz excRecordServiceBiz) {
		this.excRecordServiceBiz = excRecordServiceBiz;
	}

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
	private List<Second_order> solist = new ArrayList<Second_order>();//设置Second_order的list接收
	private List<position_inventory> pilist = new ArrayList<position_inventory>();//设置position_inventory的list接收
	
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
		pageBean = excRecordServiceBiz.searchExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
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
	//片区分发记录
	public String recordDis_pianqu()
	{
		String second_fh_person = null;//发货人
		String second_fh_address = null;//发货地址
		int areaId = 99;								//时间是te类型
		
		List<shoufa_person> sflist = null;
		if(condition.getPerName() != null)
		{
			String str = condition.getPerName();
			try 
			{
				str = new String(str.getBytes("UTF-8"),"utf-8");
			} catch (UnsupportedEncodingException e1) 
			{
				//e1.printStackTrace();
			}
			sflist = distributionBiz.findPianquIdByName(str);
			if(sflist.size() == 0)
			{
				
			}else
			{
				for(shoufa_person sp : sflist)
				{
					second_fh_person = sp.getName();
					second_fh_address = sp.getAddress();
					areaId = sp.getAreaid();
				}
			}	
		}
		condition.setAreaid(areaId);
		
		pageBean = excRecordServiceBiz.searchIllegalityDis_pianqu(condition, pageBean);
		System.out.println("总条数:"+pageBean.getTotalCount()+";打印当前页数："+pageBean.getCurentPage());
		pilist = pageBean.getList();
		//得到发货地址
		
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		if(nelist == null)
		{
			super.getPringWriter().print("null");
		}else
		{
			for(position_inventory pi : pilist)
			{
				map = new HashMap<String, Object>();
				map.put("second_fh_person", second_fh_person);//   发货人
				map.put("second_fh_address",second_fh_address);//  发货地址
				map.put("fhtime", pi.getTime());//片区发往站点的时间
				map.put("receive_sh_addressInStation",pi.getPosition_name());//站点
				//判断数量
				if(pi.getBoxCode() == null)
				{
					map.put("count", "50");
				}else
				{
					map.put("count", "500");//暂时500一箱
				}
				list.add(map);
			}	
		}
		super.outPrintJsonByArray(list);
		return super.returnToViewList(pageBean);
	}
	//分发记录
	public String recordDis()
	{
//		String second_fh_person = null;//发货人
//		String second_fh_address = null;//发货地址
//		int areaId = 99;								//时间是te类型
//		
//		List<shoufa_person> sflist = null;
//		if(condition.getPerName() != null)
//		{
//			String str = condition.getPerName();
//			try 
//			{
//				str = new String(str.getBytes("UTF-8"),"utf-8");
//			} catch (UnsupportedEncodingException e1) 
//			{
//				//e1.printStackTrace();
//			}
//			sflist = distributionBiz.findPianquIdByName(str);
//			if(sflist.size() == 0)
//			{
//				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//				super.outPrintJsonByArray(list);
//				
//			}else
//			{
//				for(shoufa_person sp : sflist)
//				{
//					second_fh_person = sp.getName();
//					second_fh_address = sp.getAddress();
//					areaId = sp.getAreaid();
//				}
//			}	
//		}
		//System.out.println("打印pageBean："+pageBean.toString());
		//System.out.println("第二页会再次进入这个方法？？？？");
		pageBean = excRecordServiceBiz.searchIllegalityDis(condition, pageBean);
		System.out.println("总条数:"+pageBean.getTotalCount()+";打印当前页数："+pageBean.getCurentPage());
		
		solist = pageBean.getList();
		
		//exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		Map<String,Object> map1 = null;
		Map<String,Object> map2 = null;
		if(nelist == null)
		{
			super.getPringWriter().print("null");
		}else
		{
			if(solist.size() != 0)
			{
				map1 = new HashMap<String, Object>();
				map1.put("stain", "-2066");//状态
			}
			//ArrayList<Map> disList = new ArrayList<Map>();
			for(Second_order so : solist)
			{
				map = new HashMap<String, Object>();
				//map.put("stain", "-2066");//状态
				map.put("second_fh_person", so.getSecond_fh_person());//   发货人
				map.put("second_fh_address",so.getSecond_fh_address());//  发货地址
				map.put("fhtime", so.getFhtime());//二级公司发往片区或者站点的时间
				//判断地区
				if(so.getReceive_sh_address() == null)
				{
					map.put("receive_sh_addressInStation",so.getReceive_sh_addressInStation());//发往站点的名称
				}else
				{
					map.put("receive_sh_addressInStation",so.getReceive_sh_address());//当无站点，则为片区
				}
				//判断数量
				if(so.getBox_code() == null)
				{
					map.put("count", "50");
				}else
				{
					map.put("count", "500");//暂时500一箱
				}
				//map.put("box_code",so.getBox_code());//发的箱子二维码
				//map.put("box_count",so.getBox_count());//每一箱子中袋子数量
			    //map.put("receive_sh_person",so.getReceive_sh_person());//收货人暂无
			    //map.put("receive_sh_address",so.getReceive_sh_address());//发往片区的名称
				//map.put("receive_sh_station",so.getReceive_sh_station());//收货状态
				//map.put("bag_code",so.getBag_code());//袋子二维码
				//map.put("bag_code_count", so.getBag_code_count());//每一个袋子中二维码数量
				list.add(map);
			}
			
		}
		map2 = new HashMap<String, Object>();
		map2.put("date", list);
		List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
		list2.add(map1);
		list2.add(map2);
		
		

		//super.outPrintJsonByObject(list2);
		//super.outPrintJsonByObject(ert);
		super.outPrintJsonByArray(list);
		return super.returnToViewList(pageBean);
	}
	@SuppressWarnings("unchecked")
	public String illegality(){
		//System.out.println("打印pageBean："+pageBean.toString());
		//System.out.println("第二页会再次进入这个方法？？？？");
		pageBean = excRecordServiceBiz.searchIllegality(condition, pageBean);
		//System.out.println("总条数:"+pageBean.getTotalCount()+";打印当前页数："+pageBean.getCurentPage());
		
		nelist = pageBean.getList();
		
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
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
				map.put("doName", neer.getDoName());//   手持机登录解封账号名
				map.put("posiName",neer.getPosiName());//地址
				map.put("time",neer.getTime());
				map.put("code",neer.getCode());
				map.put("status",neer.getStatus());
				
				if("jf-(封签未施封)".equals(neer.getRe()))
				{
					map.put("excType", "2");
				}
				if("sf-(二维码未注册)".equals(neer.getRe()))
				{
					map.put("excType", "0");
				}
				if("jf-(二维码未注册)".equals(neer.getRe()))
				{
					map.put("excType", "1");
				}//不返回3
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
		
		pageBean = excRecordServiceBiz.selectExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		return SUCCESS;
	}
	/**
	 * 查询非法操作异常已处理 
	 */
	@SuppressWarnings("unchecked")
	public String allHasillegality() {
		pageBean = excRecordServiceBiz.newselectExcRecords(condition, pageBean);
		nelist = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
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
		list = excRecordServiceBiz.findAllRecords(condition);
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
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
		excRecord = excRecordServiceBiz.getExcRecord(id);
		return super.returnToViewDetailed(excRecord);
	}

	/**
	 * 改变异常状态
	 */
	public void change() {

		if (null != id) {
			try {
				excRecordServiceBiz.changeExcRecordStatus(id);
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

				excRecordServiceBiz.newupdateHandleMethod(newexcText, newid);
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

				excRecordServiceBiz.updateHandleMethod(excText, id);
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
