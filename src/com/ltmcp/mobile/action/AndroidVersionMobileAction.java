package com.ltmcp.mobile.action;

import java.util.Map;

import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.AndroidVersionMobileBiz;

public class AndroidVersionMobileAction extends BaseAction{
	private String versionType;
	private AndroidVersionMobileBiz androidVersionMobileBiz;
	
	//��ȡ��ǰ���ݱ�ǵ�app���µİ汾��
	public void getVersion(){
		String s = androidVersionMobileBiz.getAndroidVersion(versionType);
		super.getPringWriter().print(s);
	}
	/**
	 * �°��app �汾����֤���ڲ����ݣ����Դ˱� ������ app��һЩ ������� ����  ��ȫ���Ժ���Ϊ��λ��
	 * ����  VersionName    ����app�� ��ͼʱ��  
	 * ���� VersionContent  ����app��¼��ʱ��
	 */
	public void getVedioTime(){
		Map<String, String> ss = androidVersionMobileBiz.getTimeForVedio();
		String s = net.sf.json.JSONObject.fromObject(ss).toString();
		super.getPringWriter().print(s);
	}
	
	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public AndroidVersionMobileBiz getAndroidVersionMobileBiz() {
		return androidVersionMobileBiz;
	}

	public void setAndroidVersionMobileBiz(
			AndroidVersionMobileBiz androidVersionMobileBiz) {
		this.androidVersionMobileBiz = androidVersionMobileBiz;
	}
}
