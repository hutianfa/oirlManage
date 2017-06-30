package com.ltmcp.dao.hibimpl;

import java.text.DecimalFormat;
import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AppNumCondition;
import com.ltmcp.dao.AppNumDao;
import com.ltmcp.entity.Limt;

public class AppNumDaoHibImpl extends BaseDaoHibImpl implements AppNumDao{

	@Override
	public List<Object> querydDw(PageBean pageBean,AppNumCondition condition) {
//		StringBuffer sb = new StringBuffer("select distinct (dw.nfc) , dw.pname , dw.appNum from DW dw where dw.comid = "+AdminComm.getComIdByAdmin());
		StringBuffer sb = new StringBuffer("select dw.nfc , dw.pname , dw.appNum from DW dw where dw.comid = "+AdminComm.getComIdByAdmin());
		condition(condition,sb);
		sb.append("order by dw.time desc");
		return super.find(sb.toString(), pageBean.getCurentPage(), pageBean.getPageSize());
	}
	
	@Override
	public Integer queryDwTotal(PageBean pageBean, AppNumCondition condition) {
		StringBuffer sb = new StringBuffer("select count(dw.id) from DW dw  where dw.comid = "+AdminComm.getComIdByAdmin());
		condition(condition,sb);
		return super.queryRowCount(sb.toString(), null);
	}
	
	private void condition(AppNumCondition condition, StringBuffer sb) {		
		if(condition != null){
			
			String app = condition.getCarFlapper();
			Double apps = Double.parseDouble(app);

			if("YES".equals(condition.getStatus())){
				
				sb.append(" and dw.appNum like '"+apps+"'");
				
			}else if("NO".equals(condition.getStatus())){
								
				sb.append(" and ( dw.appNum like '"+new DecimalFormat("#.0").format(apps-0.1)+"'  or dw.appNum = null )"); 
			}
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && "".equals(condition.getEndTime()) || condition.getEndTime() == null){
				
				sb.append(" and dw.time > '"+condition.getBeginTime()+"'");
			}
		
			if(!"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){
				sb.append(" and dw.time < '"+condition.getEndTime()+"'");
			}
			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime()) && condition.getEndTime() != null){
				sb.append(" and (dw.time between "+ "'"+condition.getBeginTime()+"'and '"+condition.getEndTime()+"')");
			}
		}
		
	}

	@Override
	public Limt queryLimitime() {
		String hql="from Limt l where 1=1";
		return (Limt) super.query(hql, null);
	}

}
