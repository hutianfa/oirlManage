package com.ltmcp.action;

import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.SealedService;

/**
 * ����Action
 * @author zoujianjun
 *
 */
public class ReportFormAction extends BaseAction{
	
	private ExcRecordService excRecordService;
	private SealedService sealedService; 
	private Integer excTreatedCount; //�Ѿ�������쳣����
	private Integer excUntreatedCount; //δ������쳣����
	private Integer wayBillUnfinishedCount; //δ��ɵ��˵�����
	private Integer wayBillCompletedCount; //�Ѿ���ɵ��˵�����
	private Integer wayBillExcrecordCount; //�����쳣���˵�
	
	
	/**
	 * ��ȡ������Ϣ
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public String list(){
//		excTreatedCount = excRecordService.getTreatedCount();//��ȡ�Ѵ�����쳣����
//		excUntreatedCount = excRecordService.getUnTreatedCount();//��ȡδ������쳣����
//		wayBillUnfinishedCount = sealedService.getWayBillUnfinishedCount();//��ȡδ��ɵ��˵�����
//		wayBillCompletedCount = sealedService.getWayBillCompletedCount();//��ȡ�Ѿ���ɵ��˵�����
//		wayBillExcrecordCount = sealedService.getWayBillExcCount();//��ȡ�����쳣���˵�
//		return "list";
//	}
	public String list(){
		/*excTreatedCount = excRecordService.getTreatedCount();//��ȡ�Ѵ�����쳣����
		excUntreatedCount = excRecordService.getUnTreatedCount();//��ȡδ������쳣����
		wayBillUnfinishedCount = sealedService.getWayBillUnfinishedCount();//��ȡδ��ɵ��˵�����
		wayBillCompletedCount = sealedService.getWayBillCompletedCount();//��ȡ�Ѿ���ɵ��˵�����
		wayBillExcrecordCount = sealedService.getWayBillExcCount();//��ȡ�����쳣���˵�
*/		return "list";
	}
	/**
	 * ��ģ�������ת
	 * @return
	 */
    public String orderlist(){
    	return SUCCESS;
    }
    public String detailed(){
    	return "detailed";
    }
    public String tonowlist(){
    	return "tonowlist";    	
    }
    public String inarea(){
    	return "inarea";    	
    }
    public String inoil(){
    	return "inoil";    	
    }
    public String instation(){
    	return "instation";    	
    }
    public String outarea(){
    	return "outarea";    	
    }
    public String tjlist(){
    	return "tjlist";    	
    }
    public String outoil(){
    	return "outoil";
    }


	public ExcRecordService getExcRecordService() {
		return excRecordService;
	}

	public void setExcRecordService(ExcRecordService excRecordService) {
		this.excRecordService = excRecordService;
	}

	public SealedService getSealedService() {
		return sealedService;
	}

	public void setSealedService(SealedService sealedService) {
		this.sealedService = sealedService;
	}



	public Integer getExcTreatedCount() {
		return excTreatedCount;
	}



	public void setExcTreatedCount(Integer excTreatedCount) {
		this.excTreatedCount = excTreatedCount;
	}



	public Integer getExcUntreatedCount() {
		return excUntreatedCount;
	}



	public void setExcUntreatedCount(Integer excUntreatedCount) {
		this.excUntreatedCount = excUntreatedCount;
	}



	public Integer getWayBillUnfinishedCount() {
		return wayBillUnfinishedCount;
	}



	public void setWayBillUnfinishedCount(Integer wayBillUnfinishedCount) {
		this.wayBillUnfinishedCount = wayBillUnfinishedCount;
	}



	public Integer getWayBillCompletedCount() {
		return wayBillCompletedCount;
	}



	public void setWayBillCompletedCount(Integer wayBillCompletedCount) {
		this.wayBillCompletedCount = wayBillCompletedCount;
	}



	public Integer getWayBillExcrecordCount() {
		return wayBillExcrecordCount;
	}



	public void setWayBillExcrecordCount(Integer wayBillExcrecordCount) {
		this.wayBillExcrecordCount = wayBillExcrecordCount;
	}



	
	
	
	
}
