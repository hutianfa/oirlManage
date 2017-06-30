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
	 * 判断施解封上传图片
	 */
//	private HashMap<String, String> hm = new HashMap<String, String>();
//	private HashMap<String, String> hmfre = new HashMap<String, String>();

	/**
	 * 施封信息
	 */
	@Override
	public Integer addLockInfo(String name, String password, String code,
			String plateNumber, String filePath,String youpin,String youfilePath, String latitude,
			String longitude, Integer positionId, String path,Integer tag,String wayNumber,String time)
			throws Exception
			{
		//System.out.println("进入判断施封信息：点击提交施封之后");
  //基本判断：施封人账号或者密码为空或者有去掉账号密码两边的空白或者空格之后有""，也将返回-3（时其他原因施封失败）
		 
				if (null == name || null == password || "".equals(name.trim()) || "".equals(password.trim())) 
				{
					return -3;
				}
		//用户名解密（因为从前端app传过来的数据经过基本判断之后才能进行解密）,前端app加密传账号，在此后台解密
		name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");// 解密
		//密码（因为从前端app传过来的数据经过基本判断之后才能进行解密，前端app加密传密码，在此后台解密，解密之后再加密成数据库中的格式）
		password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");// 解密
		
		Person person = new Person();//Person类中有相关的账号密码信息
		person.setPerName(name);//把app端传过来的name账号数据set到person对象中
		person.setPerPassword(MD5.md5crypt(password));//把app端传过来的密码数据set到person对象中，并且把password（xxw）加密为数据库中对应的密码格式：beca3389582d0f65ce6e536c053619ff
		
		// 第一步：检测该用户是否存在，调用personMobileDao类中的queryPersonExist方法,person参数传入，再点开queryPersonExist（按住ctrl+鼠标左键）
		//进入PersonMobileDaolmpl.java中，在这个PersonMobileDaolmpl.java中有一个public boolean queryPersonExist(Person person)方法，
		//返回0:添加成功 返回:-1 二维码未在系统中注册 -2:车辆未在系统中注册 -3:其他原因添加失败
		if (personMobileDao.queryPersonExist(person) == false) 
		{
			return -3;
		}
		//
		Integer comId = personMobileDao.queryPersonCompanyId(person); // 公司ID
		//plateNumber车牌号码
		if (null != plateNumber && null != comId) 
		{
			if ("".equals(plateNumber.trim())) 
			{
				return -2;
			}
			plateNumber = new String(Base64.decodeBase64(CharTools.allToUTF8(plateNumber)), "UTF-8"); // 解密
			// 第二步：检测车牌是否存在
			
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
		// 第三步:检测二维码是否在系统中注册或者已经被施封
		if (null != code) {
			if ("".equals(code.trim())) {
				return -1;
			}
			//二维码解密
			//System.out.println("进入判断1");
			code = new String(Base64.decodeBase64(CharTools.allToUTF8(code)),"UTF-8");// 解密
			
			if (dimensionalBarCodeMobileDao.queryCodeExists(code) == false) {
				return -1;
			}
		} else {
			//System.out.println("进入判断3");
			return -3;
		}
		
		Integer carId = carMobileDao.queryCarIdByFlapper(plateNumber); // 汽车ID
		Integer codeId = dimensionalBarCodeMobileDao.queryIdByCodeStatus(code,Comm.TWO_CODE_NORMAL); // 二维码ID
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
		sealed.setCar(car); // 设置汽车
		sealed.setCompany(company); // 设置公司
		sealed.setDimensionalBarCode(dimensionalBarCode); // 设置二维码id(配对的二维码表的id)
		sealed.setPerson(per);
		sealed.setTag(tag);
		//如果youfilePath字段不为空，如果youfilePath字段也不为"",则根据传递的数据设置相关数据
		if(!"".equals(youfilePath)){
			sealed.setYouPinPath(youfilePath);
		}
		if(!"null".equals(youpin) ){
			sealed.setYouPinName(youpin);
		}else {
			sealed.setYouPinName("");
		}
		//外够客户运单自动完成
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
		sealed.setSeaPhoto(filePath);// 设置图片
		
		//sealed.setSeaImg(path);// 第二张图片
		sealed.setSeaNumber(wayNumber);
		sealed.setSeaLatitude(latitude.toString());//纬度
		sealed.setSeaLongitude(longitude.toString());//经度
		Position position = new Position();
		//根据positionId 查找当前位置所属的区域
		position.setPosId(positionId);
		sealed.setPosition(position);
		sealed.setPhoto_check_status(0);
		try {

			// 此处更改二维码状态
//			String number = (String) hm.get(code);
//			if (number == null || number.equals("")) {
//				hm.put(code, "1");
				// 保存施封数据到施封表
				sealedMobileDao.saveLockInfo(sealed);
//			} else if (number.equals("1")) {
//
				//dimensionalBarCodeMobileDao.updateStatusByCode(codeId, filePath);//根据coodid更改图片
				if(tag == 71){
					dimensionalBarCodeMobileDao.updateStatusById(codeId,Comm.TWO_CODE_LOSS);
				}else{
					dimensionalBarCodeMobileDao.updateStatusById(codeId,Comm.TWO_CODE_CENTER);
				}
//
//				hm.remove(code);
//			}

			return 0; // 添加成功!

		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}

	}

	/**
	 * 返回二维码状态接口 -1：二维码未在系统中注册 0：未使用 1：正在使用 2：使用完毕
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public Integer returnCodeStatus(String codeContent) throws Exception {
		// 二维码没注册，返回-1
		//System.out.println("进入判断施封二维码的状态");
		Integer codeStatus;
		if (null != codeContent) {
			if ("".equals(codeContent.trim())) {
				//测试System.out.println("进入判断施封二维码的状态-1，施封码不存在");
				return -1;
			}
			if(dimensionalBarCodeMobileDao.cheackSeales(codeContent) == false){
				//测试System.out.println("进入判断施封二维码的状态-2，不能重复施封");
				return 2;
			}
			if(dimensionalBarCodeMobileDao.checkSealesabandoned(codeContent) == false){
				//测试System.out.println("进入判断施封二维码的状态1，报废");
				return 1;
			}
			if (dimensionalBarCodeMobileDao.queryCodeExists(codeContent) == false) {
				//System.out.println("进入判断施封二维码的状态-1（此封签未注册）");
				return -1;//未注册
			}
			codeStatus = dimensionalBarCodeMobileDao.queryCodeByStatus(codeContent);
		} else {
			//测试System.out.println("进入判断施封二维码的状态-3");
			return -3;
		}

		return codeStatus;
	}

	/**
	 * 返回0:解封成功 -1:二维码未在系统中注册 -2:车辆未在系统中注册 -3:异常添加错误 -4:运单不存在 -5:其他错误
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
		name = new String(Base64.decodeBase64(CharTools.allToUTF8(name)),"UTF-8");// 解密
		password = new String(Base64.decodeBase64(CharTools.allToUTF8(password)), "UTF-8");// 解密
		Person person = personMobileDao.queryPerson(name,MD5.md5crypt(password));
		if (null == person) { // 判断是否存在该操作人员
			return -5;
		}

		if ((person.getBasDict().getDictId() + 0) == (Comm.PERSON_UNLOCK + 0)) { // 判断是否是解封人员
			
		} else if((person.getBasDict().getDictId() + 0) == (Comm.PERSON_UNLOCK_LOCK + 0)){
			
		} else{
			return -5;
		}
		Integer result = this.firstCase(code, plateNumber, person,exceptionList, filePath, latitude, longitude, positionId,powerCode,powerTips,wayNumber,time);

		return result;
	}

	/**
	 * 
	 * @param code 二维码
	 * @param plateNumber 车牌号
	 * @param filePath
	 * @param longitude
	 * @param latitude
	 * @return 返回0:解封成功 -1:二维码未在系统中注册 -2:车辆未在系统中注册 -3:异常添加错误 -4:运单不存在 -5:其他错误
	 * @throws Exception
	 */
	
	public Integer firstCase(String code, String plateNumber, Person person,
			Integer exceptionList, String filePath, String latitude,
			String longitude, Integer positionId ,String powerCode,
			Integer powerTips,String wayNumber,String time) throws Exception {

		if ("".equals(code.trim()) || "".equals(plateNumber.trim())) {
			return -5; // 其他错误
		}

		Integer codeId = dimensionalBarCodeMobileDao.queryIdByCodeUnstatus(code,Comm.TWO_CODE_CENTER); // 二维码ID

		if (null == codeId) {
			return -1; // 二维码未在系统中注册
		}
		plateNumber = new String(Base64.decodeBase64(CharTools.allToUTF8(plateNumber)), "UTF-8"); // 解密
		
		Integer carId = carMobileDao.queryCarIdByFlapper(plateNumber);// 汽车ID
		
		if (null == carId) {
			return -2;// 车辆未在系统中注册
		} 
		Limt limt = personMobileDao.queryLimitime();
		Sealed sealed = sealedMobileDao.querySealedByCodeIdAndCarId(codeId,carId, Comm.WAYBILL_UNFINISHED, person.getCompany().getComId());
		
		long timer = (Integer.parseInt(limt.getFrelimit()))*60*1000;//时间可调

		Long nowTime = new Date().getTime();//当前时间
		Long seaTime = sealed.getSeaTime().getTime();//施封时间
		Long Time = nowTime - seaTime;//解封与施封的时间差
		
		Empower em = null;
		
		if(null != sealed && Time <= timer){//判断时间，如果在5分钟之内，就需要授权	
			if("".equals(powerCode)){
				return -555;
			} else if(!"".equals(powerCode)){
				//查询当前powerCode的人员信息，并写入施封表
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
		
		if (null != exceptionList) {//有异常			
			freeze.setFreStatus(Comm.WAYBILL_EXCEPTION);
		}else{
			freeze.setFreStatus(Comm.WAYBILL_COMPLETED);
		}
		
		freeze.setSealed(sealed);
		freeze.setFrePhoto(filePath);// 设置图片
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
				Integer freId = freezeMobileDao.saveFreeze(freeze);// 保存解封信息
				freeze.setFreId(freId);
				sealedMobileDao.updateFreezeId(sealed.getSeaId(),freeze.getFreId());// 把解封Id写在施封表
				//dimensionalBarCodeMobileDao.updateSealedByCodeIdAndCarId(codeId, carId, Comm.WAYBILL_UNFINISHED, carId);
			//} //else if (number.equals("1")) {

				//dimensionalBarCodeMobileDao.updateFreStatusByCodeId(codeId,filePath);// 根据二维码id更新freimg
				dimensionalBarCodeMobileDao.updateSeaStatustwo(codeId);

				dimensionalBarCodeMobileDao.updateUnstatusById(sealed.getDimensionalBarCode().getId(), Comm.TWO_CODE_LOSS);// 更新二维码状态

//				hmfre.put(code, "2");

//			} else if (number.equals("2")) {
//
//			}

		} catch (Exception e) {
			e.printStackTrace();
			return -5;
		}
		// 2.判断是否添加异常
		
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
			sealedMobileDao.updateStatus(sealed.getSeaId(),Comm.WAYBILL_COMPLETED);// 3.更新施封信息状态
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
