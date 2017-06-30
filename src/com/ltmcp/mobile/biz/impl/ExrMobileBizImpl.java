package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.ExrMobileBiz;
import com.ltmcp.mobile.dao.ExrMobileDao;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class ExrMobileBizImpl implements ExrMobileBiz {

	private ExrMobileDao exrMobileDao;
	private PersonMobileDao personMobileDao;
	
	
	
	@Override
	public List<Sealed> queryPow(String name,String password,int currPage) {
		ArrayList newList = new ArrayList<Sealed>();
		
		if(!"".equals(name)&&!"".equals(password)){
			Person person=new  Person();
			try {
				name=new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}//解密
			try {
				password=new String(Base64.decodeBase64(CharTools.allToUTF8(password)),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}//解密
			
			person.setPerName(name);
			person.setPerPassword(MD5.md5crypt(password));
			Integer comId=personMobileDao.queryPersonCompanyId(person);
						
			if(null==comId){
				return new ArrayList<Sealed>();
			}
			List<Sealed> list = exrMobileDao.exrListTime(comId,currPage);
			
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					
					String nn = list.get(i).getFreeze().getPowCodName();
					
					if(!"".equals(nn) || nn != null){
						
						newList.add(list.get(i));
					}
				}
			}else{
				return null;
			}
			
		}
		return newList;
	}
	
	
	
	
	
	
	@Override
	public List<ExcRecord> queryFreExrLi(String name,String password,int currPage) {
		
		ArrayList newList = new ArrayList<ExcRecord>();
		
		if(!"".equals(name)&&!"".equals(password)){
			Person person=new  Person();
			try {
				name=new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}//解密
			try {
				password=new String(Base64.decodeBase64(CharTools.allToUTF8(password)),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}//解密
			person.setPerName(name);
			person.setPerPassword(MD5.md5crypt(password));
			Integer comId=personMobileDao.queryPersonCompanyId(person);
			if(null==comId){
				return new ArrayList<ExcRecord>();
			}
			return exrMobileDao.exrListFre(comId,currPage);
		}
		return new ArrayList<ExcRecord>(); 
		
}

	@Override
	public ExcRecord queryDeatil(ExcRecord exr) {
		return exrMobileDao.detailed(exr);
	}


	@Override
	public void moditfyEXrSta(ExcRecord exr) {
		
		exrMobileDao.updateExr(exr);
		
	}
	
	@Override
	public List<Sealed> queryTimeOutList(String name,String password,int currPage) {
		double sealedTime = 0.0;
		double hours = 0.0;
		double systemTime = 0.0;
		double diff = 0.0;
		
		ArrayList newList = new ArrayList<Sealed>();
		
		if(!"".equals(name)&&!"".equals(password)){
			Person person=new  Person();
			try {
				name=new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}//解密
			try {
				password=new String(Base64.decodeBase64(CharTools.allToUTF8(password)),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}//解密
			person.setPerName(name);
			person.setPerPassword(MD5.md5crypt(password));
			Integer comId=personMobileDao.queryPersonCompanyId(person);
			
			if(null==comId){
				return new ArrayList<Sealed>();
			}			
			
			List<Sealed> list = exrMobileDao.exrListTimeout(comId,currPage);
			
			systemTime = System.currentTimeMillis();

			for (Sealed sea : list) {
				// 将获取的时间转换为小时
				sealedTime = sea.getSeaTime().getTime();
				diff = systemTime - sealedTime;
				// 将获取的时间毫秒转换成小时
				hours = diff / 1000 / 60 / 60;
				if (hours > 48) {
					// 将毫秒数转换为小时
					newList.add(sea);
				}
			}	
		}
		return newList;
	}
	

	@Override
	public void moditfyEmpo(Integer id) {
		exrMobileDao.gaibianEmpo(id);		
	}	
	
	public ExrMobileDao getExrMobileDao() {
		return exrMobileDao;
	}

	public void setExrMobileDao(ExrMobileDao exrMobileDao) {
		this.exrMobileDao = exrMobileDao;
	}

	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

}
