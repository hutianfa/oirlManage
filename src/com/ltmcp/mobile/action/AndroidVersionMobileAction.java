package com.ltmcp.mobile.action;

import java.util.Map;

import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.AndroidVersionMobileBiz;

public class AndroidVersionMobileAction extends BaseAction{
	private String versionType;
	private AndroidVersionMobileBiz androidVersionMobileBiz;
	
	//获取当前数据标记的app最新的版本号
	public void getVersion(){
		String s = androidVersionMobileBiz.getAndroidVersion(versionType);
		super.getPringWriter().print(s);
	}
	/**
	 * 新版的app 版本号验证不在查数据，所以此表 重用做 app端一些 活动参数的 设置  （全部以毫秒为单位）
	 * 例如  VersionName    设置app的 截图时间  
	 * 例如 VersionContent  设置app的录像时间
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
