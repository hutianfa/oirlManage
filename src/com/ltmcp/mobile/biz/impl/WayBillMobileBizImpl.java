package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Empower;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.WayBillMobileBiz;
import com.ltmcp.mobile.dao.CarMobileDao;
import com.ltmcp.mobile.dao.DimensionalBarCodeMobileDao;
import com.ltmcp.mobile.dao.ExcRecordMobileDao;
import com.ltmcp.mobile.dao.FreezeMobileDao;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.mobile.dao.SealedMobileDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;


public class WayBillMobileBizImpl implements WayBillMobileBiz
 {
	

	private PersonMobileDao personMobileDao;
	private CarMobileDao carMobileDao;
	private DimensionalBarCodeMobileDao dimensionalBarCodeMobileDao;
	private SealedMobileDao sealedMobileDao;
	private FreezeMobileDao freezeMobileDao;
	private ExcRecordMobileDao excRecordMobileDao;
	/**
	 * �ж�ʩ����ϴ�ͼƬ
	 */
//	private HashMap<String, String> hm = new HashMap<String, String>();
//	private HashMap<String, String> hmfre = new HashMap<String, String>();

	/**
	 * ʩ����Ϣ
	 */
	@Override
	public Integer addLockInfo(String name, String password, String code,
			String plateNumber, String filePath,String youpin,String youfilePath, String latitude,
			String longitude, Integer positionId, String path,Integer tag,String wayNumber,String time)
			throws Exception
			{
		//System.out.println("�����ж�ʩ����Ϣ������ύʩ��֮��");
  //�����жϣ�ʩ�����˺Ż�������Ϊ�ջ�����ȥ���˺��������ߵĿհ׻��߿ո�֮����""��Ҳ������-3��ʱ����ԭ��ʩ��ʧ�ܣ�
		 
				if (null == name || null == password || "".equals(name.trim()) || "".equals(password.trim())) 
				{
					return -3;
				}
		//�û������ܣ���Ϊ��ǰ��app�����������ݾ��������ж�֮����ܽ��н��ܣ�,ǰ��app���ܴ��˺ţ��ڴ˺�̨����
		name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");// ����
		//���루��Ϊ��ǰ��app�����������ݾ��������ж�֮����ܽ��н��ܣ�ǰ��app���ܴ����룬�ڴ˺�̨���ܣ�����֮���ټ��ܳ����ݿ��еĸ�ʽ��
		password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");// ����
		
		Person person = new Person();//Person��������ص��˺�������Ϣ
		person.setPerName(name);//��app�˴�������name�˺�����set��person������
		person.setPerPassword(MD5.md5crypt(password));//��app�˴���������������set��person�����У����Ұ�password��xxw������Ϊ���ݿ��ж�Ӧ�������ʽ��beca3389582d0f65ce6e536c053619ff
		
		// ��һ���������û��Ƿ���ڣ�����personMobileDao���е�queryPersonExist����,person�������룬�ٵ㿪queryPersonExist����סctrl+��������
		//����PersonMobileDaolmpl.java�У������PersonMobileDaolmpl.java����һ��public boolean queryPersonExist(Person person)������
		//����0:��ӳɹ� ����:-1 ��ά��δ��ϵͳ��ע�� -2:����δ��ϵͳ��ע�� -3:����ԭ�����ʧ��
		if (personMobileDao.queryPersonExist(person) == false) 
		{
			return -3;
		}
		//
		Integer comId = personMobileDao.queryPersonCompanyId(person); // ��˾ID
		//plateNumber���ƺ���
		if (null != plateNumber && null != comId) 
		{
			if ("".equals(plateNumber.trim())) 
			{
				return -2;
			}
			plateNumber = new String(Base64.decodeBase64(CharTools.allToUTF8(plateNumber)), "UTF-8"); // ����
			// �ڶ�������⳵���Ƿ����
			
			if (carMobileDao.carIsExistByComId(plateNumber, comId) == false) 
			{
				return -2;
			}
		} else 
		{
			return -3;
		}
		if (null == positionId)
		{
			return -3;
		}
		// ������:����ά���Ƿ���ϵͳ��ע������Ѿ���ʩ��
		if (null != code) {
			if ("".equals(code.trim())) {
				return -1;
			}
			//��ά�����
			//System.out.println("�����ж�1");
			code = new String(Base64.decodeBase64(CharTools.allToUTF8(code)),"UTF-8");// ����
			
			if (dimensionalBarCodeMobileDao.queryCodeExists(code) == false) {
				return -1;
			}
		} else {
			//System.out.println("�����ж�3");
			return -3;
		}
		
		Integer carId = carMobileDao.queryCarIdByFlapper(plateNumber); // ����ID
		Integer codeId = dimensionalBarCodeMobileDao.queryIdByCodeStatus(code,Comm.TWO_CODE_NORMAL); // ��ά��ID
		Integer perId = personMobileDao.queryPersonId(person);//person ID
				
		if (null == carId || null == codeId || null == perId || null == comId) {
			return -3;
		}
		
		Sealed sealed = new Sealed();

		Car car = new Car();
		car.setCarId(carId);

		Company company = new Company();
		company.setComId(comId);

		DimensionalBarCode dimensionalBarCode = new DimensionalBarCode();
		dimensionalBarCode.setId(codeId);

		Person per = new Person();
		per.setPerId(perId);
		sealed.setCar(car); // ��������
		sealed.setCompany(company); // ���ù�˾
		sealed.setDimensionalBarCode(dimensionalBarCode); // ���ö�ά��id(��ԵĶ�ά����id)
		sealed.setPerson(per);
		sealed.setTag(tag);
		//���youfilePath�ֶβ�Ϊ�գ����youfilePath�ֶ�Ҳ��Ϊ"",����ݴ��ݵ����������������
		if(!"".equals(youfilePath)){
			sealed.setYouPinPath(youfilePath);
		}
		if(!"null".equals(youpin) ){
			sealed.setYouPinName(youpin);
		}else {
			sealed.setYouPinName("");
		}
		//�⹻�ͻ��˵��Զ����
		if(tag == 71){
			sealed.setSeaStatus(Comm.WAYBILL_COMPLETED);
		}else{
			sealed.setSeaStatus(Comm.WAYBILL_UNFINISHED);
		}
		if(!"".equals(time) && time != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(time);
			long tt = date.getTime();
			sealed.setSeaTime(new Timestamp(tt));
		}else{
			sealed.setSeaTime(new Timestamp(System.currentTimeMillis()));
		}
		sealed.setSeaPhoto(filePath);// ����ͼƬ
		
		//sealed.setSeaImg(path);// �ڶ���ͼƬ
		sealed.setSeaNumber(wayNumber);
		sealed.setSeaLatitude(latitude.toString());//γ��
		sealed.setSeaLongitude(longitude.toString());//����
		Position position = new Position();
		//����positionId ���ҵ�ǰλ������������
		position.setPosId(positionId);
		sealed.setPosition(position);
		sealed.setPhoto_check_status(0);
		try {

			// �˴����Ķ�ά��״̬
//			String number = (String) hm.get(code);
//			if (number == null || number.equals("")) {
//				hm.put(code, "1");
				// ����ʩ�����ݵ�ʩ���
				sealedMobileDao.saveLockInfo(sealed);
//			} else if (number.equals("1")) {
//
				//dimensionalBarCodeMobileDao.updateStatusByCode(codeId, filePath);//����coodid����ͼƬ
				if(tag == 71){
					dimensionalBarCodeMobileDao.updateStatusById(codeId,Comm.TWO_CODE_LOSS);
				}else{
					dimensionalBarCodeMobileDao.updateStatusById(codeId,Comm.TWO_CODE_CENTER);
				}
//
//				hm.remove(code);
//			}

			return 0; // ��ӳɹ�!

		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}

	}

	/**
	 * ���ض�ά��״̬�ӿ� -1����ά��δ��ϵͳ��ע�� 0��δʹ�� 1������ʹ�� 2��ʹ�����
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public Integer returnCodeStatus(String codeContent) throws Exception {
		// ��ά��ûע�ᣬ����-1
		//System.out.println("�����ж�ʩ���ά���״̬");
		Integer codeStatus;
		if (null != codeContent) {
			if ("".equals(codeContent.trim())) {
				//����System.out.println("�����ж�ʩ���ά���״̬-1��ʩ���벻����");
				return -1;
			}
			if(dimensionalBarCodeMobileDao.cheackSeales(codeContent) == false){
				//����System.out.println("�����ж�ʩ���ά���״̬-2�������ظ�ʩ��");
				return 2;
			}
			if(dimensionalBarCodeMobileDao.checkSealesabandoned(codeContent) == false){
				//����System.out.println("�����ж�ʩ���ά���״̬1������");
				return 1;
			}
			if (dimensionalBarCodeMobileDao.queryCodeExists(codeContent) == false) {
				//System.out.println("�����ж�ʩ���ά���״̬-1���˷�ǩδע�ᣩ");
				return -1;//δע��
			}
			codeStatus = dimensionalBarCodeMobileDao.queryCodeByStatus(codeContent);
		} else {
			//����System.out.println("�����ж�ʩ���ά���״̬-3");
			return -3;
		}

		return codeStatus;
	}

	/**
	 * ����0:���ɹ� -1:��ά��δ��ϵͳ��ע�� -2:����δ��ϵͳ��ע�� -3:�쳣��Ӵ��� -4:�˵������� -5:��������
	 */
	@Override
	public Integer addUnlockInfo(String name, String password, String code,
			String plateNumber, Integer exceptionList, String filePath,
			String latitude, String longitude, Integer positionId,String powerCode,
			Integer powerTips,String wayNumber,String time)
			throws Exception{

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
			
		} else{
			return -5;
		}
		Integer result = this.firstCase(code, plateNumber, person,exceptionList, filePath, latitude, longitude, positionId,powerCode,powerTips,wayNumber,time);

		return result;
	}

	/**
	 * 
	 * @param code ��ά��
	 * @param plateNumber ���ƺ�
	 * @param filePath
	 * @param longitude
	 * @param latitude
	 * @return ����0:���ɹ� -1:��ά��δ��ϵͳ��ע�� -2:����δ��ϵͳ��ע�� -3:�쳣��Ӵ��� -4:�˵������� -5:��������
	 * @throws Exception
	 */
	
	public Integer firstCase(String code, String plateNumber, Person person,
			Integer exceptionList, String filePath, String latitude,
			String longitude, Integer positionId ,String powerCode,
			Integer powerTips,String wayNumber,String time) throws Exception {

		if ("".equals(code.trim()) || "".equals(plateNumber.trim())) {
			return -5; // ��������
		}

		Integer codeId = dimensionalBarCodeMobileDao.queryIdByCodeUnstatus(code,Comm.TWO_CODE_CENTER); // ��ά��ID

		if (null == codeId) {
			return -1; // ��ά��δ��ϵͳ��ע��
		}
		plateNumber = new String(Base64.decodeBase64(CharTools.allToUTF8(plateNumber)), "UTF-8"); // ����
		
		Integer carId = carMobileDao.queryCarIdByFlapper(plateNumber);// ����ID
		
		if (null == carId) {
			return -2;// ����δ��ϵͳ��ע��
		} 
		Limt limt = personMobileDao.queryLimitime();
		Sealed sealed = sealedMobileDao.querySealedByCodeIdAndCarId(codeId,carId, Comm.WAYBILL_UNFINISHED, person.getCompany().getComId());
		
		long timer = (Integer.parseInt(limt.getFrelimit()))*60*1000;//ʱ��ɵ�

		Long nowTime = new Date().getTime();//��ǰʱ��
		Long seaTime = sealed.getSeaTime().getTime();//ʩ��ʱ��
		Long Time = nowTime - seaTime;//�����ʩ���ʱ���
		
		Empower em = null;
		
		if(null != sealed && Time <= timer){//�ж�ʱ�䣬�����5����֮�ڣ�����Ҫ��Ȩ	
			if("".equals(powerCode)){
				return -555;
			} else if(!"".equals(powerCode)){
				//��ѯ��ǰpowerCode����Ա��Ϣ����д��ʩ���
				em = freezeMobileDao.queryPowerCode(MD5.md5crypt(powerCode));
			}
		}else if(null == sealed){
			return -4;
		}
		
		if (null == positionId) {
			return -5;
		}
		Freeze freeze = new Freeze();
		
		if(em != null ){
			freeze.setPowCodName(em.getName());
			freeze.setPowerTips(powerTips);
		}
		freeze.setCar(sealed.getCar());
		freeze.setCompany(sealed.getCompany());
		freeze.setDimensionalBarCode(sealed.getDimensionalBarCode());
		
		if(!"".equals(time) && time != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(time);
			long tt = date.getTime();
			freeze.setFreTime(new Timestamp(tt));
		}else{
			freeze.setFreTime(new Timestamp(System.currentTimeMillis()));
		}

		freeze.setPerson(person);
		
		if (null != exceptionList) {//���쳣			
			freeze.setFreStatus(Comm.WAYBILL_EXCEPTION);
		}else{
			freeze.setFreStatus(Comm.WAYBILL_COMPLETED);
		}
		
		freeze.setSealed(sealed);
		freeze.setFrePhoto(filePath);// ����ͼƬ
		freeze.setFreImg("");
		freeze.setFreNumber(wayNumber);
		freeze.setFreLatitud(latitude.toString());
		freeze.setFreLongitude(longitude.toString());
		freeze.setPhoto_check_status(0);
		Position p = new Position();
		p.setPosId(positionId);
		freeze.setPosition(p);
		freeze.setFreLockCode("1");
		try {
//			String number = (String) hmfre.get(code);
//			if (number == null || number.equals("")) {
//				hmfre.put(code, "1");
				Integer freId = freezeMobileDao.saveFreeze(freeze);// ��������Ϣ
				freeze.setFreId(freId);
				sealedMobileDao.updateFreezeId(sealed.getSeaId(),freeze.getFreId());// �ѽ��Idд��ʩ���
				//dimensionalBarCodeMobileDao.updateSealedByCodeIdAndCarId(codeId, carId, Comm.WAYBILL_UNFINISHED, carId);
			//} //else if (number.equals("1")) {

				//dimensionalBarCodeMobileDao.updateFreStatusByCodeId(codeId,filePath);// ���ݶ�ά��id����freimg
				dimensionalBarCodeMobileDao.updateSeaStatustwo(codeId);

				dimensionalBarCodeMobileDao.updateUnstatusById(sealed.getDimensionalBarCode().getId(), Comm.TWO_CODE_LOSS);// ���¶�ά��״̬

//				hmfre.put(code, "2");

//			} else if (number.equals("2")) {
//
//			}

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}
		// 2.�ж��Ƿ�����쳣
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (null != exceptionList) {
			
			ExcRecord excRecord = new ExcRecord();
			excRecord.setExcDate(timestamp);
			excRecord.setCompany(person.getCompany());
			
			excRecord.setFreeze(freeze);
			excRecord.setExcStatus(Comm.EXCEPTION_UNTREATED);
			excRecord.setSealed(sealed);
			
			BasDict bd = new BasDict();
			bd.setDictId(exceptionList);
			excRecord.setBasDict(bd);
			excRecordMobileDao.saveException(excRecord);
			sealedMobileDao.updateStatus(sealed.getSeaId(),Comm.WAYBILL_EXCEPTION);
		}else{
			sealedMobileDao.updateStatus(sealed.getSeaId(),Comm.WAYBILL_COMPLETED);// 3.����ʩ����Ϣ״̬
		}
		return 0;
	}

	@Override
	public Empower findPowerCode(String powerCode) {
		return freezeMobileDao.queryPowerCode(powerCode);
	}
	

	public PersonMobileDao getPersonMobileDao() {
		return personMobileDao;
	}

	public void setPersonMobileDao(PersonMobileDao personMobileDao) {
		this.personMobileDao = personMobileDao;
	}

	public CarMobileDao getCarMobileDao() {
		return carMobileDao;
	}

	public void setCarMobileDao(CarMobileDao carMobileDao) {
		this.carMobileDao = carMobileDao;
	}

	public DimensionalBarCodeMobileDao getDimensionalBarCodeMobileDao() {
		return dimensionalBarCodeMobileDao;
	}

	public void setDimensionalBarCodeMobileDao(
			DimensionalBarCodeMobileDao dimensionalBarCodeMobileDao) {
		this.dimensionalBarCodeMobileDao = dimensionalBarCodeMobileDao;
	}

	public SealedMobileDao getSealedMobileDao() {
		return sealedMobileDao;
	}

	public void setSealedMobileDao(SealedMobileDao sealedMobileDao) {
		this.sealedMobileDao = sealedMobileDao;
	}

	public FreezeMobileDao getFreezeMobileDao() {
		return freezeMobileDao;
	}

	public void setFreezeMobileDao(FreezeMobileDao freezeMobileDao) {
		this.freezeMobileDao = freezeMobileDao;
	}

	public ExcRecordMobileDao getExcRecordMobileDao() {
		return excRecordMobileDao;
	}

	public void setExcRecordMobileDao(ExcRecordMobileDao excRecordMobileDao) {
		this.excRecordMobileDao = excRecordMobileDao;
	}
}
