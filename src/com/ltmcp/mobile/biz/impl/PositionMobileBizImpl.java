package com.ltmcp.mobile.biz.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.dao.AppNumDao;
import com.ltmcp.entity.DW;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.biz.PositionMobileBiz;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.mobile.dao.PositionMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;
import com.ltmcp.util.UrlAndPathComm;

public class PositionMobileBizImpl implements PositionMobileBiz {

	private PersonMobileDao personMobileDao;

	private PositionMobileDao positionMobileDao;
	
//	private AppNumDao appNumDao;

	@Override
	public Integer validationPositionCardNumber(String name, String password,String cardNumber,String phoneMac,String appNum) {
		//System.out.println(name+";"+password+";"+cardNumber+";"+phoneMac+";"+appNum);
		Person person=new Person();
		if(null==name||password==null){
			return 1;
		}
		if("".equals(name)||"".equals(password)){
			return 1;
		}
		try {
			name=new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//解密
		try {
			password=new String(Base64.decodeBase64(CharTools.allToUTF8(password)),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//解密
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		boolean b=personMobileDao.queryPersonExist(person);
		//System.out.println(b);
		if(!b){
			return -1;
		}
		Integer id=positionMobileDao.queryPositionId(person.getPerName(), person.getPerPassword(), cardNumber,phoneMac);
		//System.out.println(id);
		if(null != id){
			String posiName = positionMobileDao.queryPosiName(id);
			try {//采集手机的imei
				saveInforTxt("",phoneMac,id,cardNumber,appNum);
				
				Integer comId = positionMobileDao.queryPosi(id).getCompany().getComId();
				
				
				insertDW("dd", phoneMac, id,posiName, cardNumber, appNum,comId);//定位信息采集
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return id;
		}else{
			return -1;
		}
	}
	private void insertDW(String success,String phoneMac,Integer id,String posiName,String cardNumber,String appNum,Integer comId) {
		//先判断是否存在该记录
		DW ddww = positionMobileDao.queryDWByImei(phoneMac);
			//如果存在 则进行 版本号  状态 时间 信息修改
		if(ddww != null){
			ddww.setAppNum(appNum);
			ddww.setTime(new Timestamp(System.currentTimeMillis()));
			
			try {
				positionMobileDao.reInsertDW(ddww);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(ddww == null){//不存在 则进行新纪录插入
			
			DW dw = new DW();
			
			dw.setAppNum(appNum);
			dw.setImei(phoneMac);
			dw.setNfc(cardNumber);
			dw.setPid(id);
			dw.setSuccess(success);
			dw.setPname(posiName);
			dw.setComid(comId);
			dw.setTime(new Timestamp(System.currentTimeMillis()));
			
			positionMobileDao.insertDW(dw);
		}
	
	}
	/**
	 * 将定位信息保存到txt中
	 */
	public void saveInforTxt(String str,String phoneMac,Integer id,String cardNumber,String appNum){
		
		String posiName = positionMobileDao.queryPosiName(id);
		
		String path = UrlAndPathComm.comm+"phomeMac"+File.separator;
		
		
		try {
				//创建目录
				File file = new File(path);
				
		        if (!file.exists()) {
		            file.mkdir();
		        }
		        Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String time = sdf.format(date);
				
		        String xx = path+"phoneMac.txt";
		        PrintWriter pw = new PrintWriter(new FileWriter(xx,true),true);
	            pw.println("(time)"+time+"-----定位成功与否("+str+")-----(作业点id)"+id+"------------(定位卡序列号)"+cardNumber+"-----------(APP版本号)"+appNum+"-----------(IMEI)"+phoneMac);
	            pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	
	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

	public PositionMobileDao getPositionMobileDao() {
		return positionMobileDao;
	}

	public void setPositionMobileDao(PositionMobileDao positionMobileDao) {
		this.positionMobileDao = positionMobileDao;
	}

}
