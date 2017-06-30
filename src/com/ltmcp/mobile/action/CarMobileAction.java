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
	 * ��׿�˻�ȡlist����
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
	
	
	private String QRCode;//��ά��
	/**
	 * ͨ������ʹ���еĶ�ά���ȡ����
	 */
	public void getCarByQRCode(){
		//System.out.println("�����жϽ��");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");
		
     String bs = QRCode.substring(0, 1);
     	//System.out.println("�Ѿ�����ɨ��������õķ���getCarByQRCode()");
		if(!"1".equals(bs)){ // ʩ��ɨ�˽��Ķ�ά��
			super.getPringWriter().print(-201);
			return ;
		}
		
		try {
			
			sealed = carMobileBiz.getCarByQRCode(name, password, QRCode);//�������˺ţ����룬����ά�����֤
			Limt limt = personMobileBiz.Limitime();
			long timer = (Integer.parseInt(limt.getFrelimit()))*60*1000;//ʱ��ɵ�
			Long nowTime = new Date().getTime();//��ǰʱ��
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			ArrayList<Object> arrList = new ArrayList<Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			
			int status = carMobileBiz.checkUnfreezeCode(QRCode);
			
			if(status == 10){
				
				map.put("error", "-470");
				map.put("carFlapper","");
				map.put("tips","");//����Ҫ��Ȩ��Ϊ��
				map.put("sea_doperson", "");//ʩ��������
				map.put("sea_dotime", "");//ʩ��ʱ��
				map.put("sea_doposition","");//ʩ��λ��
				map.put("sea_oilpin", "null");//��Ʒ��Ϣ
				
				
				//System.out.println("�Ѿ�����-470");
				arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			    jsonObj.put("obj", arrList);
			    super.getPringWriter().print(arrList.toString());
				return;
			}else if(status == 11){
				
				map.put("error", "-469");
				map.put("carFlapper","");
				map.put("tips","");//����Ҫ��Ȩ��Ϊ��
				map.put("sea_doperson", "");//ʩ��������
				map.put("sea_dotime", "");//ʩ��ʱ��
				map.put("sea_doposition","");//ʩ��λ��
				map.put("sea_oilpin", "null");//��Ʒ��Ϣ
				
				System.out.println("�Ѿ�����-469");
				arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			    jsonObj.put("obj", arrList);
			    super.getPringWriter().print(arrList.toString());
				return;
				//���������else��68��Ϊ�����жϵ�����
			}else if(null != sealed && sealed.size() >0){//����ж�Ӧ��δ����˵�
				/**
				 * carFlapper ����
				 * tips  �Ƿ���Ȩ��ʾ��ʶ
				 * error û�ж�Ӧ�˵�������Ϣ
				 * sea_oilpin ��Ʒ��Ϣ
				 */
				//System.out.println("101011");
				Long seaTime = sealed.get(0).getSeaTime().getTime();
				Long Time = nowTime - seaTime;//�����ʩ���ʱ���
				map.put("carFlapper", sealed.get(0).getCar().getCarFlapper());
				map.put("sea_oilpin", sealed.get(0).getYouPinName());//��Ʒ��Ϣ
				/*
				 * �¼�����
				 */
				
				Date date = new Date(sealed.get(0).getSeaTime().getTime());
				map.put("sea_doperson", sealed.get(0).getPerson().getPerName());//ʩ��������
				map.put("sea_dotime", sdf.format(date));//ʩ��ʱ��
				map.put("sea_doposition", sealed.get(0).getPosition().getPosName());//ʩ��λ��
				map.put("sea_waynum", sealed.get(0).getSeaNumber());//ʩ��λ��
				
				
				
				if(Time <= timer){//�ж�ʱ�䣬�����5����֮�ڣ�����Ҫ��Ȩ����˼����ʩ������ʱ�����̣ܶ�ʩ��������ͬһ���ص���У�������Ϊû�н���ʩ�������
					map.put("tips", "-456");//��Ҫ��Ȩ��ʾ
				}else{
					map.put("tips","");//����Ҫ��Ȩ��Ϊ��
				}
				map.put("error", "");//�ó���û�ж�Ӧ���˵�								
			} else if(null == sealed || sealed.size()< 1){
				//System.out.println("123");
				map.put("error", "-471");//�ó���û�ж�Ӧ���˵�����������������sealed.size()< 1��ʾ��
				                         //���ݿ���û���ҵ������״̬Ϊ1���ҽ������ȷ���������ǰ���Ѿ��ж�ʩ��ɨ������������ֻ�н��״̬Ϊ2�����������Ϊ"�ó���û�ж�Ӧ���˵�"
				map.put("sea_oilpin", "null");//��Ʒ��Ϣ
				map.put("tips","");//����Ҫ��Ȩ��Ϊ��
				map.put("carFlapper","");
				map.put("sea_doperson", "");//ʩ��������
				map.put("sea_dotime", "");//ʩ��ʱ��
				map.put("sea_doposition","");//ʩ��λ��
				map.put("sea_waynum","");//ʩ��λ��
			}

			arrList.add(net.sf.json.JSONObject.fromObject(map).toString());
			jsonObj.put("obj", arrList);
			super.getPringWriter().print(arrList.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *�����ƺŻ�ȡ��ǰ�����ѽ��Ŀڵ�����
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
