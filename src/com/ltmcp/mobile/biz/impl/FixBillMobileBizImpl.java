package com.ltmcp.mobile.biz.impl;

import java.sql.Timestamp;


import org.apache.commons.codec.binary.Base64;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.FixedSeal;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.biz.FixBillMobileBiz;
import com.ltmcp.mobile.dao.DimensionalBarCodeMobileDao;
import com.ltmcp.mobile.dao.FixSeaFreMobileDao;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class FixBillMobileBizImpl implements FixBillMobileBiz{
	private FixSeaFreMobileDao fixSeaFreMobileDao;
	private PersonMobileDao personMobileDao;
	private DimensionalBarCodeMobileDao dimensionalBarCodeMobileDao;

	@Override
	public Integer addLockInfo(String name, String password, String code,String plateNumber, String filePath, Integer positionId,String tips)throws Exception {
		if (null == name || null == password || "".equals(name.trim()) || "".equals(password.trim())) {
			return -3;
		}
		//�û���
		name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");// ����
		//����5
		password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");// ����
		
		Person person = new Person();
		person.setPerName(name);
		person.setPerPassword(MD5.md5crypt(password));
		// ��һ���������û��Ƿ����
		if (personMobileDao.queryPersonExist(person) == false) {
			return -3;
		}
		Person per = personMobileDao.queryPersonByPerson(person);
		
		// �ڶ���:����ά���Ƿ���ϵͳ��ע��
		if (null != code) {
			if ("".equals(code.trim())) {
				return -1;
			}
			//��ά�����
			code = new String(Base64.decodeBase64(CharTools.allToUTF8(code)),"UTF-8");// ����
			
			if (dimensionalBarCodeMobileDao.queryCodeExists(code) == false) {
				return -1;
			}
		} else {
			return -3;
		}
		Integer codeId = dimensionalBarCodeMobileDao.queryIdByCodeStatus(code,Comm.TWO_CODE_NORMAL); // ��ά��ID
		String plate = new String(Base64.decodeBase64(CharTools.allToUTF8(plateNumber)), "UTF-8");
		//��������
		Integer comId = per.getCompany().getComId();
		FixedSeal fix = new FixedSeal();
		fix.setCompany(comId);
		fix.setSeaCode(code);
		fix.setSeaSign(filePath);
		fix.setSeaTime(new Timestamp(System.currentTimeMillis()));
		fix.setSeaTip(tips);
		fix.setFreName(name);
		fix.setPlateNumber(plate);
		fix.setFixStatus(Comm.WAYBILL_FIX_UNFINISHED);
		fix.setAreaid(per.getPosition().getAreaid());
		fix.setSeaPosi(per.getPosition().getPosId());
		//��������
		try {
			fixSeaFreMobileDao.saveLockFix(fix);
			fixSeaFreMobileDao.updateFreCodeSta(codeId);
			return 0; // ��ӳɹ�!
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	@Override
	public Integer addUnlockInfo(String name, String password,String code,Integer positionId, String filePath,String tips) throws Exception {
		
		if (null == name || null == password) {
			return -5;
		}
		if ("".equals(name.trim()) || "".equals(password.trim())) {
			return -5;
		}
		name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");// ����
		password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");// ����
		Person person = personMobileDao.queryPerson(name,MD5.md5crypt(password));
		
		if (null == person) { // �ж��Ƿ���ڸò�����Ա
			return -5;
		}

		if ((person.getBasDict().getDictId() + 0) == (Comm.PERSON_UNLOCK + 0)) { // �ж��Ƿ��ǽ����Ա
			
		} else if((person.getBasDict().getDictId() + 0) == (Comm.PERSON_UNLOCK_LOCK + 0)){
			
		}else if((person.getBasDict().getDictId() + 0) == (Comm.PERSON_FIX_UNLOCK_LOCK +0)){ 
			
		}else{
			return -5;
		}
		Integer result = this.firstCase(code,filePath,person.getPosition().getPosId(),tips,name);
		return result;
		
	}
	
	
	public Integer firstCase(String code,String filePath,Integer positionId,String tips,String name) throws Exception {

		if ("".equals(code.trim())) {
			return -5; // ��������
		}
		String cc =fixSeaFreMobileDao.queryCodeByQRCode(code);
		Integer ids = fixSeaFreMobileDao.queryFixId(cc);
		if(ids == null){
			return -4;//������
		}
//		if (null == positionId) {
//			return -5;
//		}
		Integer codeId =fixSeaFreMobileDao.queryIdByFixCodeUnstatus(code, Comm.TWO_CODE_FIX_CENTER);
		//��������
		
		FixedSeal fre = fixSeaFreMobileDao.queryFixedSeal(ids);
		fre.setFreCode(code);
		fre.setFreSign(filePath);
		fre.setFreTime(new Timestamp(System.currentTimeMillis()));
		fre.setFreTip(tips);
		fre.setFrePosi(positionId);
		fre.setFreName(name);
		fre.setFixStatus(Comm.WAYBILL_FIX_COMPLETED);
		
		try {
			fixSeaFreMobileDao.saveUnLockFix(fre);
			fixSeaFreMobileDao.updateUnFreCodeSta(codeId);
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}
	}
	//���ݶ�ά���ȡ���ƺ�
	public String getCarByQRCode(String name, String password, String code) {
		return fixSeaFreMobileDao.queryCarByQRCode(code);
	}
	

	@Override
	public String getCodeByQRCode(String code) {
		return fixSeaFreMobileDao.queryCodeByQRCode(code);
	}

	public FixSeaFreMobileDao getFixSeaFredao() {
		return fixSeaFreMobileDao;
	}

	public void setFixSeaFredao(FixSeaFreMobileDao fixSeaFreMobileDao) {
		this.fixSeaFreMobileDao = fixSeaFreMobileDao;
	}

	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

	public DimensionalBarCodeMobileDao getDimensionalBarCodeMobileDao() {
		return dimensionalBarCodeMobileDao;
	}

	public void setDimensionalBarCodeMobileDao(
			DimensionalBarCodeMobileDao dimensionalBarCodeMobileDao) {
		this.dimensionalBarCodeMobileDao = dimensionalBarCodeMobileDao;
	}

	public FixSeaFreMobileDao getFixSeaFreMobileDao() {
		return fixSeaFreMobileDao;
	}

	public void setFixSeaFreMobileDao(FixSeaFreMobileDao fixSeaFreMobileDao) {
		this.fixSeaFreMobileDao = fixSeaFreMobileDao;
	}

	
}
