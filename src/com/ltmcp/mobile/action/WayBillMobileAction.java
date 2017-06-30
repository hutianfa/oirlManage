package com.ltmcp.mobile.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Empower;
import com.ltmcp.mobile.biz.WayBillMobileBiz;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;
import com.ltmcp.util.UrlAndPathComm;

public class WayBillMobileAction extends BaseAction {

	private String name;
	private String password;
	private String code;// 二维码
	private String plateNumber;// 车牌号
	private WayBillMobileBiz wayBillMobileBiz;
	private Integer excIds;// 异常
	private String latitude; // 纬度
	private String longitude;// 经度
	private Integer positionId;
	private String powerCode;
	private String wayNumber;
	private Empower  em1;
	private Integer tag ;
	private Integer powerTips;
	private String time;
	private String youpin;
	
	
	
//	private List<Integer> seaOilPin;
//	private Integer seaOilMass;
//	private Integer seaBatch;
	private HttpServletRequest request = null;

	// ---------------------------------------文件上传属性-------------------------------------
	/**
	 * 第一次上传图片的信息
	 */
	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型
	
	/**
	 * 油品二维码图片
	 */
	private File youimage; // 上传的文件
	private String youimageFileName; // 文件名称
	private String youimageContentType; // 文件类型

	/**
	 * 第二次上传图片的信息
	 */
//	private File imgs;
//	private String imgsFileName;
//	private String imgsContentType;

	/**
	 * 添加施封信息
	 */
	public void addLockInfo() {
		//System.out.println("添加施封信息");
		try {		
			String filePath = "";
			String filePath1 = "";
			String path = "";
			String youPath = "";
			String youPath1 = "";
			try {
				filePath = readPhoto("sealedImage");
				
				if(youimage != null && youimage.length() > 2988){
					youPath = readYouPhoto("youPin");
					youPath1 = readYouPhoto_copy("youPin");
				}
				filePath1 = readPhoto_copy("sealedImage");
			} catch (Exception e) {
				super.getPringWriter().print(-4); // 图片保存出现异常
				return;
			}
			if (filePath.equals("")) {
				super.getPringWriter().print(-5); // 图片保存出现异常
				return;
			}
			Integer result = wayBillMobileBiz.addLockInfo(name, password, code,plateNumber, filePath,youpin,youPath,latitude, longitude, positionId,path,tag,wayNumber,time);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			System.out.println(new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8")+"---"+new String(Base64.decodeBase64(CharTools.allToUTF8(code)), "UTF-8")+"---"+positionId+"---"+sdf.format(new Date()) +"---sf---"+result);
			
			if (result != 0) {// 如果保存信息出现异常，就删除图片
				File file = new File(filePath);
				file = new File(path);
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
		//System.out.println("二维码状态的接口");
		Integer codeStatus = -404;		
		if(!"".equals(code)){
			String bs = code.substring(0, 1);			
			
			if(! "0".equals(bs)){ // 施封扫了解封的二维码
				super.getPringWriter().print(-207);
				return ;
			}
			codeStatus = wayBillMobileBiz.returnCodeStatus(code);//施封
		}else{
			codeStatus = -405;
		}
		super.getPringWriter().print(codeStatus);

	}

	/**
	 * 添加解封信息
	 */
	public void addUnLockInfo() {
		try {
			code = new String(Base64.decodeBase64(CharTools.allToUTF8(code)),"UTF-8");// 解密
			String bs = code.substring(0, 1);

			String filePath = "";
			String filePath1 = "";
			try {
				filePath = readPhoto("freezeImage");
				filePath1 = readPhoto_copy("freezeImage");
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
			Integer result = wayBillMobileBiz.addUnlockInfo(name, password,code, plateNumber, excIds, filePath, latitude, longitude,positionId ,powerCode,powerTips,wayNumber,time);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			System.out.println(new String(Base64.decodeBase64(CharTools.allToUTF8(name)), "UTF-8")+"---"+code+"---"+positionId+"---"+sdf.format(new Date()) +"---jf---"+result);
			
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
//	上传第一张图片
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
	
//	油品二维码图片
	public String readYouPhoto(String directoryName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath("/" + directoryName);
		if (youimage != null) {
			File savefile = new File(new File(realpath), youimageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(youimage, savefile);
			return "/" + directoryName + "/" + savefile.getName();
		}
		return "";
	}
	
	/**
	 * 备份图片
	 * @param directoryName
	 * @return
	 * @throws IOException
	 */
	public String readPhoto_copy(String directoryName) throws IOException {
		
		String realpath = UrlAndPathComm.comm+"Ltmcp_img" + File.separator + directoryName;
		
		if (image != null) {
			File savefile = new File(new File(realpath), imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(image, savefile);
			return realpath + savefile.getName();
		}
		return "";
	}
	
	
	public String readYouPhoto_copy(String directoryName) throws IOException {
		
		String realpath = UrlAndPathComm.comm+"Ltmcp_img" + File.separator + directoryName;
		if (youimage != null) {
			File savefile = new File(new File(realpath), youimageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(youimage, savefile);
			return realpath + savefile.getName();
		}
		return "";
	}

	public void empow(){
		String pow = MD5.md5crypt(powerCode);
		try {
			Empower em =wayBillMobileBiz.findPowerCode(pow);
			if(em == null){
				super.getPringWriter().print(-234);
			} else{
				super.getPringWriter().print(-235);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
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

	public WayBillMobileBiz getWayBillMobileBiz() {
		return wayBillMobileBiz;
	}

	public void setWayBillMobileBiz(WayBillMobileBiz wayBillMobileBiz) {
		this.wayBillMobileBiz = wayBillMobileBiz;
	}

	public File getImage() {
		return image;
	}

	public Integer getExcIds() {
		return excIds;
	}

	public void setExcIds(Integer excIds) {
		this.excIds = excIds;
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getPowerCode() {
		return powerCode;
	}

	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}

	public Empower getEm1() {
		return em1;
	}

	public void setEm1(Empower em1) {
		this.em1 = em1;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getPowerTips() {
		return powerTips;
	}

	public void setPowerTips(Integer powerTips) {
		this.powerTips = powerTips;
	}

	public String getWayNumber() {
		return wayNumber;
	}

	public void setWayNumber(String wayNumber) {
		this.wayNumber = wayNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getYoupin() {
		return youpin;
	}

	public void setYoupin(String youpin) {
		this.youpin = youpin;
	}

	public File getYouimage() {
		return youimage;
	}

	public void setYouimage(File youimage) {
		this.youimage = youimage;
	}

	public String getYouimageFileName() {
		return youimageFileName;
	}

	public void setYouimageFileName(String youimageFileName) {
		this.youimageFileName = youimageFileName;
	}

	public String getYouimageContentType() {
		return youimageContentType;
	}

	public void setYouimageContentType(String youimageContentType) {
		this.youimageContentType = youimageContentType;
	}
}