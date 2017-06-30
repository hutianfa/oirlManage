package com.ltmcp.mobile.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.ltmcp.action.BaseAction;
import com.ltmcp.util.UrlAndPathComm;

public class AppLogAction extends BaseAction{
	
	/**
	 * �ϴ�log��־�ļ�
	 */
	private File txt; // �ϴ����ļ�
	private String txtFileName; // �ļ�����
	private String txtContentType; // �ļ�����
	
	
	
	
	/**
	 * �ϴ�app Log��־
	 */
	public void addLogInfo() {
		try {		
			String filePath = "";			
			try {
				filePath = readPhoto("appLogs");
			} catch (Exception e) {
				super.getPringWriter().print(-4); // ͼƬ��������쳣
				return;
			}
			if (filePath.equals("")) {
				super.getPringWriter().print(-5); // ͼƬ��������쳣
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(-3);
		}
	}
	

//	�ϴ���־
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
