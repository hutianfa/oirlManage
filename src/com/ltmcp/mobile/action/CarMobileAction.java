package com.ltmcp.mobile.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.biz.CarMobileBiz;
import com.ltmcp.mobile.biz.PersonMobileBiz;
import com.ltmcp.util.CharTools;

public class CarMobileAction extends BaseAction{
	private CarMobileBiz carMobileBiz;
	private String name;
	private String password;
	private String carFlag;
	private List<Sealed> sealed = null;
	private PersonMobileBiz personMobileBiz;
	/**
	 * 安卓端获取list集合
	 */
	public void list(){
		try {
			List<Car> list=carMobileBiz.searchCars(name, password);
			
			String[] ignoreAttribute=new String[]{"admin","company","adminName","comName","freezes","sealeds","carCreatetime","carStatus","carFlag"};
			
			super.outPrintJsonByArray(list, ignoreAttribute);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private String QRCode;//二维码
	/**
	 * 通过正在使用中的二维码获取车牌
	 */
	public void getCarByQRCode(){
		//System.out.println("进入判断解封");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		
     String bs = QRCode.substring(0, 1);
     	//System.out.println("已经进入扫描解封码调用的方法getCarByQRCode()");
		if(!"1".equals(bs)){ // 施封扫了解封的二维码
			super.getPringWriter().print(-201);
			return ;
		}
		
		try {
			
			sealed = carMobileBiz.getCarByQRCode(name, password, QRCode);//参数是账号，密码，解封二维码的验证
			Limt limt = personMobileBiz.Limitime();
			long timer = (Integer.parseInt(limt.getFrelimit()))*60*1000;//时间可调
			Long nowTime = new Date().getTime();//当前时间
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			ArrayList<Object> arrList = new ArrayList<Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			
			int status = carMobileBiz.checkUnfreezeCode(QRCode);
			
			if(status == 10){
				
				map.put("error", "-470");
				map.put("carFlapper","");
				map.put("tips","");//不需要授权则为空
				map.put("sea_doperson", "");//施封人姓名
				map.put("sea_dotime", "");//施封时间
				map.put("sea_doposition","");//施封位置
				map.put("sea_oilpin", "null");//油品信息
				
				
				//System.out.println("已经返回-470");
				arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			    jsonObj.put("obj", arrList);
			    super.getPringWriter().print(arrList.toString());
				return;
			}else if(status == 11){
				
				map.put("error", "-469");
				map.put("carFlapper","");
				map.put("tips","");//不需要授权则为空
				map.put("sea_doperson", "");//施封人姓名
				map.put("sea_dotime", "");//施封时间
				map.put("sea_doposition","");//施封位置
				map.put("sea_oilpin", "null");//油品信息
				
				System.out.println("已经返回-469");
				arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			    jsonObj.put("obj", arrList);
			    super.getPringWriter().print(arrList.toString());
				return;
				//下面这里的else到68行为增加判断的内容
			}else if(null != sealed && sealed.size() >0){//如果有对应的未完成运单
				/**
				 * carFlapper 车牌
				 * tips  是否授权提示标识
				 * error 没有对应运单车牌信息
				 * sea_oilpin 油品信息
				 */
				//System.out.println("101011");
				Long seaTime = sealed.get(0).getSeaTime().getTime();
				Long Time = nowTime - seaTime;//解封与施封的时间差
				map.put("carFlapper", sealed.get(0).getCar().getCarFlapper());
				map.put("sea_oilpin", sealed.get(0).getYouPinName());//油品信息
				/*
				 * 新加属性
				 */
				
				Date date = new Date(sealed.get(0).getSeaTime().getTime());
				map.put("sea_doperson", sealed.get(0).getPerson().getPerName());//施封人姓名
				map.put("sea_dotime", sdf.format(date));//施封时间
				map.put("sea_doposition", sealed.get(0).getPosition().getPosName());//施封位置
				map.put("sea_waynum", sealed.get(0).getSeaNumber());//施封位置
				
				
				
				if(Time <= timer){//判断时间，如果在5分钟之内，就需要授权：意思就是施封与解封时间间隔很短，施封与解封在同一个地点进行（具体则为没有进行施封操作）
					map.put("tips", "-456");//需要授权提示
				}else{
					map.put("tips","");//不需要授权则为空
				}
				map.put("error", "");//该车牌没有对应的运单								
			} else if(null == sealed || sealed.size()< 1){
				//System.out.println("123");
				map.put("error", "-471");//该车牌没有对应的运单·······（sealed.size()< 1表示：
				                         //数据库中没有找到解封码状态为1并且解封码正确的情况，则前面已经判断施封扫解封情况，这里只有解封状态为2的情况，）即为"该车牌没有对应的运单"
				map.put("sea_oilpin", "null");//油品信息
				map.put("tips","");//不需要授权则为空
				map.put("carFlapper","");
				map.put("sea_doperson", "");//施封人姓名
				map.put("sea_dotime", "");//施封时间
				map.put("sea_doposition","");//施封位置
				map.put("sea_waynum","");//施封位置
			}

			arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			jsonObj.put("obj", arrList);
			super.getPringWriter().print(arrList.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *按车牌号获取当前车辆已解封的口的数量
	 */
	public void getSeaOrFreNum(){
		List<Car> list=carMobileBiz.searchCars(name, password);
		if(list == null){
			super.getPringWriter().print("null");
		}else{
			ArrayList<Map> carList = new ArrayList<Map>();
			for(int i = 0 ;i<list.size();i++){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("car", list.get(i).getCarFlapper());
				map.put("carid", list.get(i).getCarId());
				map.put("total", list.get(i).getCarUnFixFlag());
				carList.add(map);
			}
			
			try {
				String ss = new String(Base64.decodeBase64(CharTools.allToUTF8(carFlag)),"UTF-8");
				List<Map> li = carMobileBiz.getCarSeaFreNum(carList,ss); 
				super.outPrintJsonByArray(li);
			} catch (Exception e) {
				super.getPringWriter().print("error"); 
			}
		}
		
 	}
	
	
	public String getQRCode() {
		return QRCode;
	}
	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}
	public CarMobileBiz getCarMobileBiz() {
		return carMobileBiz;
	}
	public void setCarMobileBiz(CarMobileBiz carMobileBiz) {
		this.carMobileBiz = carMobileBiz;
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




	public PersonMobileBiz getPersonMobileBiz() {
		return personMobileBiz;
	}




	public void setPersonMobileBiz(PersonMobileBiz personMobileBiz) {
		this.personMobileBiz = personMobileBiz;
	}

	public String getCarFlag() {
		return carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}

	public List<Sealed> getSealed() {
		return sealed;
	}

	public void setSealed(List<Sealed> sealed) {
		this.sealed = sealed;
	}	
}
