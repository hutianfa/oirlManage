package com.ltmcp.mobile.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.ltmcp.action.BaseAction;
import com.ltmcp.mobile.biz.FixBillMobileBiz;
import com.ltmcp.mobile.biz.WayBillMobileBiz;
import com.ltmcp.util.CharTools;

public class FixSeaFreMobileAction extends BaseAction{

	
	private String name;
	private String password;
	private String code;// 二维码
	private String plateNumber;// 车牌号
	private Integer positionId;//固定封签的位置
	private String tips;
	
	private FixBillMobileBiz fixBillMobileBiz;
	private WayBillMobileBiz wayBillMobileBiz;

	// ---------------------------------------文件上传属性-------------------------------------
	/**
	 * 第一次上传图片的信息
	 */
	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型
	
	//固定封签的解封
	public void FixSea(){
		try {		
			String filePath = "";		
			try {
				filePath = readPhoto("seaSign");
			} catch (Exception e) {
				super.getPringWriter().print(-4); // 图片保存出现异常
				return;
			}
			if (filePath.equals("")) {
				super.getPringWriter().print(-5); // 图片保存出现异常
				return;
			}
			Integer result = fixBillMobileBiz.addLockInfo(name, password,code,plateNumber,filePath,positionId,tips);
			if (result != 0) {// 如果保存信息出现异常，就删除图片
				File file = new File(filePath);
				file.delete();
			}
			super.getPringWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(-3);
		}
	}
	
	/**
	 * 返回二维码状态的接口
	 * 
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public void returnCodeContentStatus() throws Exception {

		String bs = code.substring(0, 1);
		
		if(!"0".equals(bs)){ // 施封扫了解封的二维码
			super.getPringWriter().print(-207);
			return ;
		}
		
		Integer codeStatus = wayBillMobileBiz.returnCodeStatus(code);//施封
		super.getPringWriter().print(codeStatus);

	}
	
	//固定封签的解封
	public void FixFre(){
		try {
			code = new String(Base64.decodeBase64(CharTools.allToUTF8(code)),"UTF-8");// 解密
			String bs = code.substring(0, 1);

			String filePath = "";
			try {
				filePath = readPhoto("freSign");
			} catch (Exception e) {
				super.getPringWriter().print(-6); // 图片保存出现异常
				return;
			}
			if (filePath.equals("")) {
				super.getPringWriter().print(-7); // 图片保存出现异常
				return;
			}
			
			if(!"1".equals(bs)){
				super.getPringWriter().print(-207); // 解封扫了施封的二维码
				return;
			}
			Integer result = fixBillMobileBiz.addUnlockInfo(name, password, code, positionId, filePath, tips);
			if (result != 0) {// 如果保存信息出现异常，就删除图片
				File file = new File(filePath);
				file.delete();
			}
			super.getPringWriter().print(result);
		} catch (Exception e) {
			super.getPringWriter().print(-5);
			e.printStackTrace();
		}
	}
	
    //上传图片
	public String readPhoto(String directoryName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath("/" + directoryName);
		if (image != null) {
			File savefile = new File(new File(realpath), imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(image, savefile);
			return "/" + directoryName + "/" + savefile.getName();
		}
		return "";
	}
	
	//根据二维码获取车牌号
	public void getFixCarByQRCode(){
		
	     String bs = code.substring(0, 1);
	     
			if(!"1".equals(bs)){ // 施封扫了解封的二维码
				super.getPringWriter().print(-201);
				return ;
			}
			//根据当前的二维码，获取到关联的施封二维码，用施封二维码查询车牌
			String carNum;
			String str_co = fixBillMobileBiz.getCodeByQRCode(code);
			if(str_co != null){
				String str = fixBillMobileBiz.getCarByQRCode(name, password, str_co);
				if(str != null){
					carNum = str;
				} else{
					carNum = "-121";
				}
			} else{
				carNum = "-121";
			}
			super.getPringWriter().print(carNum);
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public FixBillMobileBiz getFixBillMobileBiz() {
		return fixBillMobileBiz;
	}
	public void setFixBillMobileBiz(FixBillMobileBiz fixBillMobileBiz) {
		this.fixBillMobileBiz = fixBillMobileBiz;
	}

	public WayBillMobileBiz getWayBillMobileBiz() {
		return wayBillMobileBiz;
	}

	public void setWayBillMobileBiz(WayBillMobileBiz wayBillMobileBiz) {
		this.wayBillMobileBiz = wayBillMobileBiz;
	}	
	
}
