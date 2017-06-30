package com.ltmcp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.ReportCondition;
import com.ltmcp.dao.EfficiencyDao;
import com.ltmcp.entity.Car;
import com.ltmcp.service.EfficiencyService;

public class EfficiencyServiceImpl extends BaseServiceImpl implements EfficiencyService{

	private EfficiencyDao efficiencyDao;
	@Override
	public List<Map<String, Object>> infor3(ReportCondition condition) {
		
		List<Car> car = efficiencyDao.carInfor(condition);//��ȡ�����е�car
		
		List<Map<String, Object>> li = new ArrayList<Map<String,Object>>();
		
		for(int i = 0 ;i<car.size();i++){
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("car", car.get(i).getCarFlapper());
			map.put("sjName", "");
			map.put("tel", "");
			map.put("com", car.get(i).getCompany().getComName());
			
			//���ݳ��ƻ�ȡ�˵����
			List<Object> l = efficiencyDao.carWayNum(car.get(i).getCarId(), condition);//����carId��ȡ�˵����
		
			List<Object> v = new ArrayList<Object>();//����˵���Ӧ������ʱ��
			
				if(l.size() > 0){
					
					for(int x = 0 ; x<l.size() ; x++){
						
						if(l.get(x) != null){
						
						//�����˵���Ż�ȡ�����˵��ģ�����ʱ��&&�������	
			
						try {
							Object obj = efficiencyDao.queryWay((String)l.get(x));//�����˵���Ż�ȡ�����˵��ģ�ƽ������ʱ��

							v.add(obj);
							
						} catch (Exception e) {
							e.printStackTrace();
						}	
							
		
						}else if(l.get(x) == null){
							map.put("way", v);
						}

						map.put("way", v);
					}
					
				} else{
					map.put("way", v);
				}
				
			li.add(map);
						
		}
		
		return returnList(li);
	}
	
	
	public List<Map<String, Object>> returnList(List<Map<String, Object>> list){
		
		List<Map<String, Object>> lii = new ArrayList<Map<String,Object>>();
		
		//����list
		for(int i=0;i<list.size();i++){
						
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("car", list.get(i).get("car"));
			map.put("sjName", list.get(i).get("sjName"));
			map.put("tel", list.get(i).get("tel"));
			map.put("com",list.get(i).get("com") );
			
			List o = (List)list.get(i).get("way");

				if(o.size() > 0){
					int y = o.size();
					double sum = 0.0;
					map.put("totalWayNum",y);										
					
						for(int j=0 ; j < y ; j++){
							int dd =shiftTimeToSs((Double)o.get(j));
							sum += dd;
							
						}
											
					map.put("totalWayTime",sum/(60*60));
					
					map.put("tl", ((sum)/y)/(60*60));
					
				}else if(o.size() < 1){
					try {
						
					map.put("totalWayNum",0);
					map.put("totalWayTime",0.000001);
					map.put("tl",0.000001);
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
			
			lii.add(map);
		}
		
		
		
		return lii;
	}
	
	/**
	 * ת�����ݿⷵ�ص���ֵ
	 * @return
	 */
	public int shiftTimeToSs(Double obj){
		
		int ss=0;
		int mm=0;
		int hh=0;
		int day=0;
		String aS = String.valueOf(obj);
		aS = aS.substring(0, aS.indexOf("."));
			
		
		if(aS.length()==1){
			ss = Integer.parseInt(aS);
		}else if(aS.length()==2){//������λ
			ss = Integer.parseInt(aS);
		}else if(aS.length()==3){
			mm = Integer.parseInt(aS.substring(0, 1));
			ss = Integer.parseInt(aS.substring(1, 3));
		}else if(aS.length()==4){//��������λ�͵�������λ
			mm = Integer.parseInt(aS.substring(0, 2));
			ss = Integer.parseInt(aS.substring(2, 4));
		}else if(aS.length()==5){
			hh = Integer.parseInt(aS.substring(0, 1));
			mm = Integer.parseInt(aS.substring(1, 3));
			ss = Integer.parseInt(aS.substring(3, 5));
		}else if(aS.length()==6){//��������λ�͵�������λ
			hh = Integer.parseInt(aS.substring(0, 2));
			mm = Integer.parseInt(aS.substring(2, 4));
			ss = Integer.parseInt(aS.substring(4, 6));
		}else if(aS.length()>6){//����ʣ��
			day = Integer.parseInt(aS.substring(0,aS.length()-6));
			hh = Integer.parseInt(aS.substring(aS.length()-6, aS.length()-4));
			mm = Integer.parseInt(aS.substring(aS.length()-4, aS.length()-2));
			ss = Integer.parseInt(aS.substring(aS.length()-2, aS.length()));			
		}

		return ss+mm*60+hh*3600+day*24*3600;

	}	
	
	
	
	
	
	public EfficiencyDao getEfficiencyDao() {
		return efficiencyDao;
	}
	public void setEfficiencyDao(EfficiencyDao efficiencyDao) {
		this.efficiencyDao = efficiencyDao;
	}
}
