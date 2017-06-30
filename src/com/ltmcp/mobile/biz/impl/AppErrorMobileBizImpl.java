package com.ltmcp.mobile.biz.impl;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.mobile.biz.AppErrorMobileBiz;
import com.ltmcp.mobile.dao.AppErrorMobileDao;

public class AppErrorMobileBizImpl extends BaseAction implements AppErrorMobileBiz{

	private AppErrorMobileDao appErrorMobileDao;
	@Override
	public void appError_nor(String code,String returnNum,String username,String posid,String appNum) {
		
		if(returnNum.equals("jf-(��ǩδʩ��)" ) || returnNum.equals("sf-(��ά��δע��)") || returnNum.equals("jf-(��ά��δע��)")){
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
		//���жϸ�code������ڷǷ���Ϣ
		//System.out.println(code);
		NewErrors nError = appErrorMobileDao.queryNewErrors(code);
		//System.out.println("nError:"+nError);
		if(nError == null){//����������򱣴�
			//System.out.println("û���򱣴�����ʩ�������new_errors��");
			appErrorMobileDao.insert_illegality(code, returnNum, username, posid);
		}else{
			//������� �򲻴���
		}
		
	}
	public AppErrorMobileDao getAppErrorMobileDao() {
		return appErrorMobileDao;
	}
	public void setAppErrorMobileDao(AppErrorMobileDao appErrorMobileDao) {
		this.appErrorMobileDao = appErrorMobileDao;
	}


	
}
