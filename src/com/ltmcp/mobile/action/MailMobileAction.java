package com.ltmcp.mobile.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ltmcp.action.BaseAction;
import com.ltmcp.condition.MailCondition;
import com.ltmcp.mobile.biz.MailMobileBiz;

public class MailMobileAction extends BaseAction{

	private MailMobileBiz mailMobileBiz;
	private MailCondition condition;
	private List<Map<String,Object>> list;
	
	
	  private String beginTime;
	    private String endTime;
		   public void gettime(){
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	 Calendar c1 = Calendar.getInstance();
		    	    c1.set(Calendar.HOUR_OF_DAY, 0);
		    	    c1.set(Calendar.MINUTE, 0);
		    	    c1.set(Calendar.SECOND, 0);
		    	    Date date_begin = c1.getTime();
		    	    beginTime = sdf.format(date_begin);		    	    
		    	    c1.set(Calendar.HOUR_OF_DAY, 23);
		    	    c1.set(Calendar.MINUTE, 59);
		    	    c1.set(Calendar.SECOND, 59);
		    	    Date date_end = c1.getTime();
		    	    endTime = sdf.format(date_end);
		   }
	/*自动发送邮件需求数据
	 * 
	 */
	public void mail(){
		gettime();
		condition.setBeginTime(beginTime);
		condition.setEndTime(endTime);
		mailMobileBiz.findInfor(condition);
	}
	
	public MailMobileBiz getMailMobileBiz() {
		return mailMobileBiz;
	}
	public void setMailMobileBiz(MailMobileBiz mailMobileBiz) {
		this.mailMobileBiz = mailMobileBiz;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public MailCondition getCondition() {
		return condition;
	}
	public void setCondition(MailCondition condition) {
		this.condition = condition;
	}
	
	
	
}
