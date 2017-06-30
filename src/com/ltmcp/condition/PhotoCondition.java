package com.ltmcp.condition;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;

import com.ltmcp.comm.Comm;

public class PhotoCondition {

	private Integer comId;
	
	private Integer seaId;
	
	private Integer freId;
	
	private String seaTip;
	
	private String freTip;
	
	private Integer seaStatus;
	
	private Integer freStatus;
		
	private Integer currentPage;
	
	private Integer pageSize;
	
	private String startDate; //添加员工的时间
	
	private String endDate; //添加员工的时间
	
	private String comName;
	
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		try {
			comName=new String(comName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.comName = comName;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getSeaId() {
		return seaId;
	}

	public void setSeaId(Integer seaId) {
		this.seaId = seaId;
	}

	public Integer getFreId() {
		return freId;
	}

	public void setFreId(Integer freId) {
		this.freId = freId;
	}

	public String getSeaTip() {
		return seaTip;
	}

	public void setSeaTip(String seaTip) {
		this.seaTip = seaTip;
	}

	public String getFreTip() {
		return freTip;
	}

	public void setFreTip(String freTip) {
		this.freTip = freTip;
	}

	public Integer getSeaStatus() {
		return seaStatus;
	}

	public void setSeaStatus(Integer seaStatus) {
		this.seaStatus = seaStatus;
	}

	public Integer getFreStatus() {
		return freStatus;
	}

	public void setFreStatus(Integer freStatus) {
		this.freStatus = freStatus;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {		
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
