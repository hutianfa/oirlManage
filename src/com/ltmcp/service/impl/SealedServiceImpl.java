package com.ltmcp.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionCondition;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.SealedService;
import com.ltmcp.util.UrlAndPathComm;

public class SealedServiceImpl  extends BaseServiceImpl  implements SealedService{

	private static SealedDao sealedDao;
	
	@Override
	public Integer getWayBillUnfinishedCount() {
		Limt limt = sealedDao.queryLimitime();
		Integer timer = Integer.parseInt(limt.getStat());//时间可调
		
		Integer count=sealedDao.querySealedCountByStatus(timer,Comm.WAYBILL_UNFINISHED);
		if(null!=count){
			return count;
		}else{
			return 0;
		}
	}

	@Override
	public Integer getWayBillCompletedCount() {
		
		Limt limt = sealedDao.queryLimitime();
		Integer timer = Integer.parseInt(limt.getStat());//时间可调
		
		Integer count=sealedDao.querySealedCountByStatus(timer,Comm.WAYBILL_COMPLETED);
		if(null!=count){
			return count;
		}else{
			return 0;
		}
	}

//	@Override
//	public PageBean searchWaybillByCondition(SealedCondition condition,PageBean pageBean) {
//			if(null==pageBean){
//				pageBean=new PageBean();
//			}
//			//查找所有的运单
//			List<Sealed> list= sealedDao.findSealeds(pageBean.getCurentPage(),pageBean.getPageSize(), condition);
//			//返回运单条数
//			Integer totalCount=sealedDao.queryTotalCountByCondition(condition);
//			//返回一个PageBean对象
//			return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
//	}
	
	@Override
	public PageBean searchWaybillByCondition(SealedCondition condition,PageBean pageBean) {
			if(null==pageBean){
				pageBean=new PageBean();
			}
			Limt limt = sealedDao.queryLimitime();
			Integer timer = Integer.parseInt(limt.getStat());//时间可调

			//查找所有的运单
			List<Sealed> list= sealedDao.findSealeds(timer,pageBean.getCurentPage(),pageBean.getPageSize(), condition);
			//返回运单条数
			Integer totalCount=sealedDao.queryTotalCountByCondition(timer,condition);
			//返回一个PageBean对象
			return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	public SealedDao getSealedDao() {
		return sealedDao;
	}
	
	public void setSealedDao(SealedDao sealedDao) {
		this.sealedDao = sealedDao;
	}

	@Override
	
	public Sealed getWaybillById(Integer id) {
		if(null != id){
			Sealed sealed=new Sealed();
			sealed.setSeaId(id);
			return sealedDao.querySealed(sealed);
		}
		return null;
	}

	@Override
	public PageBean searchByPersonId(PageBean pageBean, Integer id) {
		if(null==id){
			return pageBean;
		}
		if(null==pageBean){
			pageBean=new PageBean();
		}
		List<Sealed> list=sealedDao.findSealeds(pageBean.getCurentPage(), pageBean.getPageSize(), id);
		Integer totalCount=sealedDao.queryTotalCountByPerId(id);
		return  PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}

	@Override
	public Integer getWayBillExcCount() {
		Limt limt = sealedDao.queryLimitime();
		Integer timer = Integer.parseInt(limt.getStat());//时间可调

		return sealedDao.querySealedCountByStatus(timer,Comm.WAYBILL_EXCEPTION);
	}

	/*
	 * 查询全部
	 */
	@Override
	public List<Sealed> findAllWaybillByCondition(SealedCondition condition) {
		Limt limt = sealedDao.queryLimitime();
		Integer timer = Integer.parseInt(limt.getStat());//时间可调
		return sealedDao.selectSealeds(timer,condition);
	}

	/*
	 * 实时运单信息查询
	 */
	@Override
	public List<Sealed> findSeaAndFre(SealedCondition condition) {
		return sealedDao.selectSeaAndFre(condition);
	}

}
