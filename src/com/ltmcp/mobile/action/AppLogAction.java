package com.ltmcp.mobile.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.ltmcp.action.BaseAction;
import com.ltmcp.util.UrlAndPathComm;

public class AppLogAction extends BaseAction{
	
	/**
	 * 上传log日志文件
	 */
	private File txt; // 上传的文件
	private String txtFileName; // 文件名称
	private String txtContentType; // 文件类型
	
	
	
	
	/**
	 * 上传app Log日志
	 */
	public void addLogInfo() {
		try {		
			String filePath = "";			
			try {
				filePath = readPhoto("appLogs");
			} catch (Exception e) {
				super.getPringWriter().print(-4); // 图片保存出现异常
				return;
			}
			if (filePath.equals("")) {
				super.getPringWriter().print(-5); // 图片保存出现异常
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(-3);
		}
	}
	

//	上传日志
	public String readPhoto(String directoryName) throws IOException {

		String realpath = UrlAndPathComm.comm+ directoryName;
		
		if (txt != null) {
			File savefile = new File(new File(realpath), txtFileName);
			
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			
			FileUtils.copyFile(txt, savefile);
			
			return "/" + directoryName + "/" + savefile.getName();
		}
		return "";
	}


	public File getTxt() {
		return txt;
	}


	public void setTxt(File txt) {
		this.txt = txt;
	}


	public String getTxtFileName() {
		return txtFileName;
	}


	public void setTxtFileName(String txtFileName) {
		this.txtFileName = txtFileName;
	}


	public String getTxtContentType() {
		return txtContentType;
	}


	public void setTxtContentType(String txtContentType) {
		this.txtContentType = txtContentType;
	}

	
	
	
	
}
