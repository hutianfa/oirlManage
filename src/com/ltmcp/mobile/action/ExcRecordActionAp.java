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
	private ExcRecordCondition condition; // ��ѯ�б�ʱ������
	private ExcRecordServiceBiz excRecordServiceBiz;
	
	public ExcRecordServiceBiz getExcRecordServiceBiz() {
		return excRecordServiceBiz;
	}

	public void setExcRecordServiceBiz(ExcRecordServiceBiz excRecordServiceBiz) {
		this.excRecordServiceBiz = excRecordServiceBiz;
	}

	private ExcRecord excRecord; // �����ҳ����ϸ���ݵĶ���
	private Integer id; // ��Ҫ��ȡ�쳣��ϸ��Ϣ��ID
	private List<ExcRecord> list = new ArrayList<ExcRecord>();
	private List<BasDict> exceptionTypes; // �쳣���ͼ���
	private Integer dictId; // ���մ������� �Ϳ�Id
	private Integer endId;// ����վId
	private Double avg;
	private List<Sealed> sealedList;
	private SealedService sealedService;
	private Double parcent;
	private SealedCondition con;
	private String excText;// ���մ������Ĵ������
	private String newexcText;
	private Integer newid;
	private List<NewErrors> nelist = new ArrayList<NewErrors>();//����newErrors��list����
	private List<Second_order> solist = new ArrayList<Second_order>();//����Second_order��list����
	private List<position_inventory> pilist = new ArrayList<position_inventory>();//����position_inventory��list����
	
	/**
	 * ����������ѯδ�����쳣
	 * list()����������ѯ������Ҫ�ķ���
	 * ������condition.excType=2                      ��2ʩ��δ��⣬0ʩ����δע�ᣬ1�����δע�ᣩ
	 *     condition.carFlapper=�����ƺ�   ����Ҫ��
	 *     condition.beginTime=2016-12-15%2010:34:12   
	 *     condition.endTime=2016-12-22%2010:34:12
	 *     condition.excStatus=1                    �쳣��״̬(0:�Ѵ���1:δ����)
	 *     condition.posid=             վ��id
	 *     pageBean.curentPage=         ��ǰҳ
	 * @return
	 */
	public String list() {
		pageBean = excRecordServiceBiz.searchExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		return super.returnToViewList(pageBean);
	}

	/**
	 * ��ѯ�Ƿ������쳣���� 
	 *  * ������condition.excType=2                      ��2ʩ��δ��⣬0ʩ����δע�ᣬ1�����δע�ᣩ
	 *     condition.carFlapper=�����ƺ�   ����Ҫ��
	 *     condition.beginTime=2016-12-15%2010:34:12   
	 *     condition.endTime=2016-12-22%2010:34:12
	 *     condition.excStatus=1                    �쳣��״̬(0:�Ѵ���1:δ����)
	 *     condition.posid=             վ��id
	 *     pageBean.curentPage=         ��ǰҳ
	 * 
	 */
	//Ƭ���ַ���¼
	public String recordDis_pianqu()
	{
		String second_fh_person = null;//������
		String second_fh_address = null;//������ַ
		int areaId = 99;								//ʱ����te����
		
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
		System.out.println("������:"+pageBean.getTotalCount()+";��ӡ��ǰҳ����"+pageBean.getCurentPage());
		pilist = pageBean.getList();
		//�õ�������ַ
		
		
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
				map.put("second_fh_person", second_fh_person);//   ������
				map.put("second_fh_address",second_fh_address);//  ������ַ
				map.put("fhtime", pi.getTime());//Ƭ������վ���ʱ��
				map.put("receive_sh_addressInStation",pi.getPosition_name());//վ��
				//�ж�����
				if(pi.getBoxCode() == null)
				{
					map.put("count", "50");
				}else
				{
					map.put("count", "500");//��ʱ500һ��
				}
				list.add(map);
			}	
		}
		super.outPrintJsonByArray(list);
		return super.returnToViewList(pageBean);
	}
	//�ַ���¼
	public String recordDis()
	{
//		String second_fh_person = null;//������
//		String second_fh_address = null;//������ַ
//		int areaId = 99;								//ʱ����te����
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
		//System.out.println("��ӡpageBean��"+pageBean.toString());
		//System.out.println("�ڶ�ҳ���ٴν������������������");
		pageBean = excRecordServiceBiz.searchIllegalityDis(condition, pageBean);
		System.out.println("������:"+pageBean.getTotalCount()+";��ӡ��ǰҳ����"+pageBean.getCurentPage());
		
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
				map1.put("stain", "-2066");//״̬
			}
			//ArrayList<Map> disList = new ArrayList<Map>();
			for(Second_order so : solist)
			{
				map = new HashMap<String, Object>();
				//map.put("stain", "-2066");//״̬
				map.put("second_fh_person", so.getSecond_fh_person());//   ������
				map.put("second_fh_address",so.getSecond_fh_address());//  ������ַ
				map.put("fhtime", so.getFhtime());//������˾����Ƭ������վ���ʱ��
				//�жϵ���
				if(so.getReceive_sh_address() == null)
				{
					map.put("receive_sh_addressInStation",so.getReceive_sh_addressInStation());//����վ�������
				}else
				{
					map.put("receive_sh_addressInStation",so.getReceive_sh_address());//����վ�㣬��ΪƬ��
				}
				//�ж�����
				if(so.getBox_code() == null)
				{
					map.put("count", "50");
				}else
				{
					map.put("count", "500");//��ʱ500һ��
				}
				//map.put("box_code",so.getBox_code());//�������Ӷ�ά��
				//map.put("box_count",so.getBox_count());//ÿһ�����д�������
			    //map.put("receive_sh_person",so.getReceive_sh_person());//�ջ�������
			    //map.put("receive_sh_address",so.getReceive_sh_address());//����Ƭ��������
				//map.put("receive_sh_station",so.getReceive_sh_station());//�ջ�״̬
				//map.put("bag_code",so.getBag_code());//���Ӷ�ά��
				//map.put("bag_code_count", so.getBag_code_count());//ÿһ�������ж�ά������
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
		//System.out.println("��ӡpageBean��"+pageBean.toString());
		//System.out.println("�ڶ�ҳ���ٴν������������������");
		pageBean = excRecordServiceBiz.searchIllegality(condition, pageBean);
		//System.out.println("������:"+pageBean.getTotalCount()+";��ӡ��ǰҳ����"+pageBean.getCurentPage());
		
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
				map.put("doName", neer.getDoName());//   �ֳֻ���¼����˺���
				map.put("posiName",neer.getPosiName());//��ַ
				map.put("time",neer.getTime());
				map.put("code",neer.getCode());
				map.put("status",neer.getStatus());
				
				if("jf-(��ǩδʩ��)".equals(neer.getRe()))
				{
					map.put("excType", "2");
				}
				if("sf-(��ά��δע��)".equals(neer.getRe()))
				{
					map.put("excType", "0");
				}
				if("jf-(��ά��δע��)".equals(neer.getRe()))
				{
					map.put("excType", "1");
				}//������3
				list.add(map);
			}
		}
		//super.outPrintJsonByObject(list);
		super.outPrintJsonByArray(list);
		return super.returnToViewList(pageBean);
		
	}
	/**
	 * ����������ѯ�Ѵ����쳣
	 */
	@SuppressWarnings("unchecked")
	public String allHasHandle() {
		
		pageBean = excRecordServiceBiz.selectExcRecords(condition, pageBean);
		list = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		return SUCCESS;
	}
	/**
	 * ��ѯ�Ƿ������쳣�Ѵ��� 
	 */
	@SuppressWarnings("unchecked")
	public String allHasillegality() {
		pageBean = excRecordServiceBiz.newselectExcRecords(condition, pageBean);
		nelist = pageBean.getList();
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		return SUCCESS;
	}
	
	
	
	
	/**
	 * ʱ���쳣��Ϣ
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
//	 * ��ʱ�쳣 ����ʩ���˵�48Сʱ û�н����Ϣ
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
	 * ��ѯȫ���쳣��Ϣ δ����  ����excel
	 * 
	 * @return
	 */
	public String allExcList() {
		list = excRecordServiceBiz.findAllRecords(condition);
		exceptionTypes = excRecordServiceBiz.searchExceptionTypes();
		return super.returnToViewList(list);
	}



	/**
	 * ��ȡ��ϸ��Ϣ
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
	 * �ı��쳣״̬
	 */
	public void change() {

		if (null != id) {
			try {
				excRecordServiceBiz.changeExcRecordStatus(id);
				super.getResponse().getWriter().print(0); // ����ɹ�
				return;
			} catch (Exception e) {
				try {
					super.getResponse().getWriter().print(1);
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				} // ����ʧ��
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
			super.getResponse().getWriter().print(0); // ����ɹ�
			return;
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			} // ����ʧ��
		}
	}
	/**
	 * ���쳣�������д���쳣��
	 * 
	 * @return
	 */
	public void addExcHandleMethod() {
		try {

			if (id != null) {

				excRecordServiceBiz.updateHandleMethod(excText, id);
			}
			super.getResponse().getWriter().print(0); // ����ɹ�
			return;
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			} // ����ʧ��
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

	// ����ӵ�
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
