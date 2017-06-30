package com.ltmcp.action;

import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.SealedService;

/**
 * 报表Action
 * @author zoujianjun
 *
 */
public class ReportFormAction extends BaseAction{
	
	private ExcRecordService excRecordService;
	private SealedService sealedService; 
	private Integer excTreatedCount; //已经处理的异常条数
	private Integer excUntreatedCount; //未处理的异常条数
	private Integer wayBillUnfinishedCount; //未完成的运单条数
	private Integer wayBillCompletedCount; //已经完成的运单条数
	private Integer wayBillExcrecordCount; //出现异常的运单
	
	
	/**
	 * 获取报表信息
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public String list(){
//		excTreatedCount = excRecordService.getTreatedCount();//获取已处理的异常条数
//		excUntreatedCount = excRecordService.getUnTreatedCount();//获取未处理的异常条数
//		wayBillUnfinishedCount = sealedService.getWayBillUnfinishedCount();//获取未完成的运单条数
//		wayBillCompletedCount = sealedService.getWayBillCompletedCount();//获取已经完成的运单条数
//		wayBillExcrecordCount = sealedService.getWayBillExcCount();//获取出现异常的运单
//		return "list";
//	}
	public String list(){
		/*excTreatedCount = excRecordService.getTreatedCount();//获取已处理的异常条数
		excUntreatedCount = excRecordService.getUnTreatedCount();//获取未处理的异常条数
		wayBillUnfinishedCount = sealedService.getWayBillUnfinishedCount();//获取未完成的运单条数
		wayBillCompletedCount = sealedService.getWayBillCompletedCount();//获取已经完成的运单条数
		wayBillExcrecordCount = sealedService.getWayBillExcCount();//获取出现异常的运单
*/		return "list";
	}
	/**
	 * 新模块界面跳转
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
