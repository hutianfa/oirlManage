package com.ltmcp.mobile.biz;

import com.ltmcp.condition.MailCondition;


public interface MailMobileBiz {
	//获取数据并生成html文件，用于邮件发送
	public void findInfor(MailCondition condition);
}
