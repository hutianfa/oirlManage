package com.ltmcp.mobile.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;
import com.ltmcp.mobile.biz.MobileCommMobileBiz;
import com.ltmcp.util.UrlAndPathComm;

public class MobileCommAction extends BaseAction {

	private MobileCommMobileBiz mobileCommMobileBiz;
	private String code;
	private String nfcId;
	private String username;

	private File vedio; // �ϴ����ļ�
	private String vedioFileName; // �ļ�����
	private String vedioContentType; // �ļ�����

	private File pic; // �ϴ����ļ�
	private String picFileName; // �ļ�����
	private String picContentType; // �ļ�����

	private String wayNum;

	/*
	 * ��������⣬ͨ��ʩ�����ȡ��Ʒ��Ϣ
	 */
	public void getYouPinBySealCode() {
		try {
			String youPin = mobileCommMobileBiz.queryYouPinBySealCode(code);
			if ("".equals(youPin) || null == youPin) {

				System.out.println("1");
				super.getPringWriter().print("null");
				return;
			}
			System.out.println("2");
			super.getPringWriter().print(youPin);
		} catch (Exception e) {
			System.out.println("3");
			super.getPringWriter().print("null");
			e.printStackTrace();
		}
	}

	/**
	 * ͨ��nfc id ��ȡ��Ʒ��Ϣ
	 */
	public void getYouNameByNfc() {
		try {
			PetrolName petrolName = mobileCommMobileBiz.queryYouPinByNfc(nfcId,username);

			if (petrolName != null) {
				super.getPringWriter().print(petrolName.getYou_name());
			} else {
				super.getPringWriter().print("null");
			}
		} catch (Exception e) {
			super.getPringWriter().print("null");
		}
	}

	/**
	 * �ϴ������ļ�������
	 * 
	 * @return
	 */
	public void uploadVedio() {
		try {
			String ss = readVedio("vedio");
			String filePath = readVedio_cpoy("vedio");

			String yundan = filePath.substring(filePath.lastIndexOf(File.separator) + 1,filePath.lastIndexOf("-"));

			System.out.println("filePath:"+filePath);
			
			String name = filePath.substring(filePath.lastIndexOf(File.separator) + 1,filePath.lastIndexOf("."));
			
			String twoCode = name.substring(name.lastIndexOf("-") + 1);
			// ����yundan ������Ƶ����
			doConnect(yundan, twoCode, ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ϴ���׿�˴������� ͼƬ���������ļ����˵���Ϣ����
	 */
	public void picsSave() {
		try {
			String picPath = readPhoto("pics");
			String picPath1 = readPhoto_copy("pics");


			String bianhao = picPath.substring(picPath.indexOf("-") + 1,picPath.lastIndexOf("-"));
			String type = picPath.substring(picPath.lastIndexOf(File.separator) + 1,picPath.lastIndexOf(File.separator) + 3);
			//��ͼƬ���˵���Ž��а󶨣�������picture�ı���
			doSaveToDB(type, bianhao, picPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param type
	 *            �ж���ʩ���ĵ� ���ǽ���ĵ���Ƭ
	 * @param bianhao
	 *            �˵����
	 * @param picPath
	 *            ͼƬ�����·��
	 */
	public void doSaveToDB(String type, String bianhao, String picPath) {
		mobileCommMobileBiz.updateSeaPicByYunDan(type, bianhao, picPath);
	}

	/**
	 * ��ֹ ��Ƶ�ļ����� ���ļ��ĸ��ǣ������ ɨ��Ķ�ά�룬��������
	 * 
	 * @param yundan
	 *            �˵����
	 * @param twoCode
	 *            ��Ƶ����Ӧ�Ķ�ά��
	 */
	public void doConnect(String yundan, String twoCode, String filePath) {

		try {
			mobileCommMobileBiz.updateSeaByYunDan(yundan, twoCode, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ���� �˵���Ų�ѯ ���˵���Ŷ�Ӧ�� ͼƬ
	 */
	public void wayNumPic() {
		try {
			List<Picture> list = mobileCommMobileBiz.quertPicByWayNum(wayNum);
			
			List<Map<String,String>> li = new ArrayList<Map<String,String>>();
			
			for(Picture pic : list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("picpath", pic.getPicPath());
				li.add(map);
			}
			
			super.outPrintJsonByArray(li);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readPhoto(String directoryName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath(File.separator + directoryName);
		if (pic != null) {
			File savefile = new File(new File(realpath), picFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(pic, savefile);
			return File.separator + directoryName + File.separator + savefile.getName();
		}
		return "";
	}

	public String readPhoto_copy(String directoryName) throws IOException {

		String realpath = UrlAndPathComm.comm + "Ltmcp_img" + File.separator+ directoryName + File.separator;

		if (pic != null) {
			File savefile = new File(new File(realpath), picFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(pic, savefile);
			return realpath + "" + savefile.getName();
		}
		return "";
	}

	public String readVedio(String directoryName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath(File.separator + directoryName);
		if (vedio != null) {
			File savefile = new File(new File(realpath), vedioFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(vedio, savefile);
			return File.separator + directoryName + File.separator + savefile.getName();
		}
		return "";
	}

	public String readVedio_cpoy(String directoryName) throws IOException {

		String realpath = UrlAndPathComm.comm + "Ltmcp_img" + File.separator+ directoryName + File.separator;

		if (vedio != null) {
			File savefile = new File(new File(realpath), vedioFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(vedio, savefile);
			return realpath + "" + savefile.getName();
		}
		return "";
	}

	public MobileCommMobileBiz getMobileCommMobileBiz() {
		return mobileCommMobileBiz;
	}

	public void setMobileCommMobileBiz(MobileCommMobileBiz mobileCommMobileBiz) {
		this.mobileCommMobileBiz = mobileCommMobileBiz;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNfcId() {
		return nfcId;
	}

	public void setNfcId(String nfcId) {
		this.nfcId = nfcId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public File getVedio() {
		return vedio;
	}

	public void setVedio(File vedio) {
		this.vedio = vedio;
	}

	public String getVedioFileName() {
		return vedioFileName;
	}

	public void setVedioFileName(String vedioFileName) {
		this.vedioFileName = vedioFileName;
	}

	public String getVedioContentType() {
		return vedioContentType;
	}

	public void setVedioContentType(String vedioContentType) {
		this.vedioContentType = vedioContentType;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getWayNum() {
		return wayNum;
	}

	public void setWayNum(String wayNum) {
		this.wayNum = wayNum;
	}

}
