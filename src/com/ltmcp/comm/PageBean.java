package com.ltmcp.comm;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private Integer totalCount; //������
	private Integer curentPage; //��ǰҳ
	private Integer pageSize; //ҳ���С
	private Integer totalPage; //��ҳ��
	private boolean pageIsEnd; //�Ƿ������һҳ
	private boolean pageIsFirst; //�Ƿ��ǵ�һҳ
	private List list;//?
	
	public Integer getTotalCount() {
		if(null==totalCount){
			return 0;
		}
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurentPage() {
		if(null==curentPage){
			return 1;
		}
		return curentPage;
	}
	public void setCurentPage(Integer curentPage) {
		this.curentPage = curentPage;
	}
	public Integer getPageSize() {
		if(null==pageSize){
			return Comm.SYSTEM_PAGESIZE;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return this.getTotalCount()%this.getPageSize()==0?this.getTotalCount()/this.getPageSize():(this.getTotalCount()/this.getPageSize())+1;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public boolean isPageIsEnd() {
		if(this.getTotalPage()==this.getCurentPage()){
			return true;
		}else{
			return false;
		}
	}
	public void setPageIsEnd(boolean pageIsEnd) {
		this.pageIsEnd = pageIsEnd;
	}
	public boolean isPageIsFirst() {
		if(this.getCurentPage()==1){
			return true;
		}else{
			return false;
		}
	}
	public void setPageIsFirst(boolean pageIsFirst) {
		this.pageIsFirst = pageIsFirst;
	}
	public List getList() {
		if(null==list){
			list=new ArrayList();
		}
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	//չʾ��һҳ������13����¼
	public static PageBean getPageBean(List list, Integer totalCount, Integer pageSize, Integer curentPage) {
		PageBean pb=new PageBean();
		pb.setList(list);//����list
		pb.setTotalCount(totalCount);//����
		pb.setPageSize(pageSize);//ÿҳ��С����
		pb.setCurentPage(curentPage);//��ǰҳ
		if(null==pb.getPageSize()){
			pb.setTotalPage(pb.getTotalCount()%pb.getPageSize()==0?pb.getTotalCount()/pb.getPageSize():(pb.getTotalCount()/pb.getPageSize())+1);
		}
		return pb;
	}
	
}
