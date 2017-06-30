package com.ltmcp.mobile.biz.impl;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.mobile.biz.AppErrorMobileBiz;
import com.ltmcp.mobile.dao.AppErrorMobileDao;

public class AppErrorMobileBizImpl extends BaseAction implements AppErrorMobileBiz{

	private AppErrorMobileDao appErrorMobileDao;
	@Override
	public void appError_nor(String code,String returnNum,String username,String posid,String appNum) {
		
		if(returnNum.equals("jf-(封签未施封)" ) || returnNum.equals("sf-(二维码未注册)") || returnNum.equals("jf-(二维码未注册)")){
			//System.out.println(11);
			try {
				saveInfor(code, returnNum, username, posid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				appErrorMobileDao.insert_nor(code, returnNum,username,posid,appNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	

	private void saveInfor(String code, String returnNum, String username,String posid) {
		//先判断该code否存是在非法信息
		//System.out.println(code);
		NewErrors nError = appErrorMobileDao.queryNewErrors(code);
		//System.out.println("nError:"+nError);
		if(nError == null){//如果不存在则保存
			//System.out.println("没有则保存错误的施封码进入new_errors中");
			appErrorMobileDao.insert_illegality(code, returnNum, username, posid);
		}else{
			//如果存在 则不处理
		}
		
	}
	public AppErrorMobileDao getAppErrorMobileDao() {
		return appErrorMobileDao;
	}
	public void setAppErrorMobileDao(AppErrorMobileDao appErrorMobileDao) {
		this.appErrorMobileDao = appErrorMobileDao;
	}


	
}
