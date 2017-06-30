package com.ltmcp.mobile.dao.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.ExrMobileDao;
import com.ltmcp.util.CharTools;

public class ExrMobileDaoHibImpl extends BaseDaoHibImpl implements ExrMobileDao {

	@Override
	public List<Sealed> exrListTime(Integer comId,int currPage) {
		SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当日开始时间
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    //当前时间
	    Date end = c.getTime();
	    String endTime=matter.format(end);
	    
	    c.add(Calendar.MINUTE, -5);
	    //开始时间
	    Date begin = c.getTime();
	    String beginTime=matter.format(begin);

		StringBuilder sb = new StringBuilder(" from Sealed s where 1=1");
		sb.append(" and s.company.comId= "+comId);
		sb.append(" and (s.freeze.freStatus ="+Comm.WAYBILL_COMPLETED +"OR s.freeze.freStatus="+Comm.WAYBILL_EXCEPTION+")" );
		sb.append(" and s.freeze.powCodName <> null");
		sb.append(" and s.freeze.freLockCode ='1'");
		sb.append(" order by s.freeze.freTime desc ");
		return super.find(sb.toString(),currPage,12);
	}

	@Override
	public List<Sealed> exrListTimeout(Integer comId,int currPage) {
		SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当日开始时间
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    //当前时间
	    Date end = c.getTime();
	    String endTime=matter.format(end);
	    
	    c.add(Calendar.DATE, -5);
	    //开始时间
	    Date begin = c.getTime();
	    String beginTime=matter.format(begin);
		

		StringBuilder sb = new StringBuilder(" from Sealed s where 1=1");
		sb.append(" and s.company.comId= "+comId);
		sb.append(" and s.seaStatus="+Comm.WAYBILL_UNFINISHED);
		sb.append(" and (s.seaTime between '"+beginTime+"' and  '"+endTime+"' )");
		sb.append(" order by s.seaTime desc");
		return super.find(sb.toString(), currPage, 12);
	}

	@Override
	public List<ExcRecord> exrListFre(Integer comId,int currPage) {
		StringBuilder sb = new StringBuilder("from ExcRecord er where 1=1 ");
		sb.append(" and er.company.comId=" + comId);
		sb.append(" and er.excStatus= "+Comm.EXCEPTION_UNTREATED);
		sb.append(" order by excDate desc ");
		return super.find(sb.toString(), currPage, 12);
	}

	@Override
	public void gaibianEmpo(Integer id) {	
		StringBuilder sb=new StringBuilder("update Freeze f set f.freLockCode='0' where f.freId = ?");
		super.updateByHql(sb.toString(),id);
	}
	
	
	@Override
	public ExcRecord detailed(ExcRecord exr) {
		return (ExcRecord) super.query(ExcRecord.class, exr.getExcId());
	}

	@Override
	public void updateExr(ExcRecord exr) {
		ExcRecord e = (ExcRecord) super.query(ExcRecord.class, exr.getExcId());
		e.setExcStatus(0);
		if(!"".equals(exr.getExcHandleMethod()) || exr.getExcHandleMethod() != null){
			try {
				String advi = new String(Base64.decodeBase64(CharTools.allToUTF8(exr.getExcHandleMethod())),"UTF-8");
				e.setExcHandleMethod(advi);
				e.setComName(exr.getComName());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}			
		}
		super.saveOrUpdate(e);
		super.getSession().beginTransaction().commit();
	}

}
