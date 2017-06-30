package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.ltmcp.entity.Car;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.CarMobileBiz;
import com.ltmcp.mobile.dao.CarMobileDao;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class CarMobileBizImpl implements CarMobileBiz{
	private CarMobileDao carMobileDao;

	private PersonMobileDao personMobileDao;

	public Integer checkUnfreezeCode(String QRCode){
		   return carMobileDao.checkUnfreezeCode(QRCode);
	}


	@Override
	public List<Car> searchCars(String name,String password) {
		if(null!=name&&password!=null){

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
					return new ArrayList<Car>();
				}
				return carMobileDao.findCars(comId);
			}

		}
		return new ArrayList<Car>();

	}


	@Override
	public List<Map> getCarSeaFreNum(List<Map> carFlapper,String str) {
		ArrayList<Map> ca = new ArrayList<Map>();
		for(int i = 0 ;i< carFlapper.size();i++){						
			if(str.equals(carFlapper.get(i).get("car"))){
				Map<String,Object> map = new HashMap<String, Object>();
				Integer s = (Integer) carFlapper.get(i).get("carid");
				Integer num = carMobileDao.queryCarFredNum(s);				
				map.put("carFlapper", str);
				map.put("totalNum",carFlapper.get(i).get("total"));
				map.put("fre", num);
				ca.add(map);
			}			
		}
		return ca;
	}

	public CarMobileDao getCarMobileDao() {
		return carMobileDao;
	}


	public void setCarMobileDao(CarMobileDao carMobileDao) {
		this.carMobileDao = carMobileDao;
	}


	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}


	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}


	@Override
	public List<Sealed> getCarByQRCode(String name, String password, String QRCode) {
		if(null!=name&&password!=null){

			if(!"".equals(name)&&!"".equals(password)){
				Person person=new  Person();
				try {
					name=new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				//解密
				try {
					password=new String(Base64.decodeBase64(CharTools.allToUTF8(password)),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}//解密
				person.setPerName(name);
				person.setPerPassword(MD5.md5crypt(password));
//				person.setPerPassword(password);
				Integer comId=personMobileDao.queryPersonCompanyId(person);
				if(null==comId){
					return new ArrayList<Sealed>();
				}
				return carMobileDao.queryCarByQRCode(QRCode);
			}
		}


		return null;
	}




	




}
