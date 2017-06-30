package com.ltmcp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.dao.PhotoDao;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.PhotoService;

public class PhotoServiceImpl implements PhotoService{

	private PhotoDao photoDao;
	@Override
	public List<Sealed> findSeaAndFreImg(PhotoCondition condition) {
		
		return photoDao.selectSeaAndFreImg(condition);
	}	
	
	@Override
	public Integer saveSeaAndFreImg(PhotoCondition condition) {

		return photoDao.updateSeaAndFreImg(condition);
	}
	
	@Override
	public Integer findPhotoTotal(PhotoCondition condition) {
		
		return photoDao.queryTotalCountByCondition(condition);
	}

	@Override
	public List<Map<String, Object>> findScale(Person person, PhotoCondition condition) {
		int num = 0 ;
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Person> li = photoDao.findPersons(condition);
		
		int perTotal = photoDao.findPerTotal(condition);
		
		int n = perTotal/Comm.SYSTEM_PERSONSIZE;
		int Page = perTotal%Comm.SYSTEM_PERSONSIZE;
		 if(Page > 0){
			 num = n+1;
		 }
			for(int i=0;i<li.size();i++){

				if(li.get(i).getBasDict().getDictId() == 6){//施封
					double t1 = photoDao.queryAllBadTotalSeaPhoto(li.get(i), condition);
					double t2 = photoDao.queryAllTotalSeaPhoto(li.get(i), condition);

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("pageNum", num);
					map.put("id", li.get(i).getPerId());
					map.put("personType", 6);
					map.put("perTrueName", li.get(i).getPerTrueName());
					map.put("phoneNum", li.get(i).getPerPhone());
					map.put("company", li.get(i).getCompany().getComName());
					map.put("position", li.get(i).getPosition().getPosName());
					if(t2 == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", (t1/t2));
					}
					map.put("allTotal", (int)t2);
					map.put("allBadTotal", (int)t1);
					list.add(map);
				} else if(li.get(i).getBasDict().getDictId() == 7){//解封					
					double t1 = photoDao.queryAllBadTotalFrePhoto(li.get(i), condition);
					double t2 = photoDao.queryAllTotalFrePhoto(li.get(i), condition);
					
					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("currentPage", condition.getCurrentPage());
					map.put("pageNum", num);
					map.put("id", li.get(i).getPerId());
					map.put("personType", 7);
					map.put("perTrueName", li.get(i).getPerTrueName());
					map.put("phoneNum", li.get(i).getPerPhone());
					map.put("company", li.get(i).getCompany().getComName());
					map.put("position", li.get(i).getPosition().getPosName());
					if(t2 == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", (t1/t2));
					}
					map.put("allBadTotal", (int)t1);
					map.put("allTotal", (int)t2);
					
					list.add(map);
					
				}else if(li.get(i).getBasDict().getDictId() == 67){//施解封					
					double tt1 = photoDao.queryAllBadTotalFrePhoto(li.get(i), condition);					
					double t1 = photoDao.queryAllBadTotalSeaPhoto(li.get(i), condition);
					
					double tt2 = photoDao.queryAllTotalFrePhoto(li.get(i), condition);
					double t2 = photoDao.queryAllTotalSeaPhoto(li.get(i), condition);
					
					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("currentPage", condition.getCurrentPage());
					map.put("pageNum", num);
					map.put("id", li.get(i).getPerId());
					map.put("personType", 67);
					map.put("perTrueName", li.get(i).getPerTrueName());
					map.put("phoneNum", li.get(i).getPerPhone());
					map.put("company", li.get(i).getCompany().getComName());
					map.put("position", li.get(i).getPosition().getPosName());
					if((t2 + tt2) == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", ((tt1+t1)/(tt2+t2)));
					}
					map.put("allTotal", (int)(tt2+t2));
					map.put("allBadTotal", (int)(tt1+t1));
					list.add(map);
				}
			}
			

//			 Collections.sort(list, new Comparator<Map<String, Object>>() {
//				   public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//				    // o1，o2是list中的Map，可以在其内取得值，按其排序，此例为降序，scale和scale是价格的绝对值
//				    double t1 = (Double) o1.get("scale");
//				    double t2 = (Double) o2.get("scale");
//				    if(t1==t2){return 0;}
//	                if(t1>t2){return 1;}
//	                return 0;
//				   }
//			});
			
			
			
//			for (int i = 0; i < list.size(); i++) {
//	            for (int j = i; j < list.size(); j++) {
//	 
//	                if ((Double)list.get(i).get("scale") >= (Double)list.get(j).get("scale")) {
//	                	Map<String, Object> o1 = list.get(i);
//	                	list.set(i, list.get(j));
//	                	list.set(j, list.get(i));
//	                }
//	            }
//	        }	
		return list;
	}
	
	public PhotoDao getPhotoDao() {
		return photoDao;
	}
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	@Override
	public List<Map<String, Object>> findPersonScale(Person person) {		
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	      
	      //获取当前时间的前4个月的个人信息
	      for(int i =0 ;i<6;i++){
	    	  SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		      Calendar c = Calendar.getInstance();
	    	//获取当前时间的前i个月
	    	  c.add(Calendar.MONTH,-i);
		     		      
		      c.set(Calendar.DAY_OF_MONTH, 1);
		      //截止时间
		      Date begin = c.getTime();
		      String beginTime=matter.format(begin);
		      
		      c.add(Calendar.MONTH,+1);
		      c.set(Calendar.DAY_OF_MONTH, -0);
		      //起始时间
		      Date end = c.getTime();
		      String endTime=matter.format(end);
		      
		      SimpleDateFormat adf=new SimpleDateFormat("yyyy-MM");
	    	  String mon=adf.format(begin);
		      if( person.getBasDict().getDictId()== 6){//施封
		    	  
					double t1 = photoDao.queryAllBadTotalSea(person, beginTime, endTime);
					double t2 = photoDao.queryAllTotalSea(person, beginTime, endTime);

					Map<String, Object> map = new HashMap<String, Object>();
					
		    	    map.put("monthNum",mon);
					map.put("allTotal", (int)t2);
					map.put("allBadTotal", (int)t1);
					if(t2 == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", (t1/t2));
					}
					list.add(map);
				} else if(person.getBasDict().getDictId() == 7){//解封
					
					double t1 = photoDao.queryAllBadTotalFre(person, beginTime, endTime);
					double t2 = photoDao.queryAllTotalFre(person, beginTime, endTime);
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("monthNum",mon);
					map.put("allBadTotal", (int)t1);
					map.put("allTotal", (int)t2);
					if(t2 == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", (t1/t2));
					}
					list.add(map);
					
				}else if(person.getBasDict().getDictId() == 67){//施解封
					double t1 = photoDao.queryAllBadTotalSea(person, beginTime, endTime);
					double tt1 = photoDao.queryAllBadTotalFre(person, beginTime, endTime);

					double t2 = photoDao.queryAllTotalSea(person, beginTime, endTime);					
					double tt2 = photoDao.queryAllTotalFre(person, beginTime, endTime);
					
					Map<String, Object> map = new HashMap<String, Object>();
										
					map.put("monthNum",mon);
					map.put("allTotal", (int)(tt2+t2));
					map.put("allBadTotal", (int)(tt1+t1));
					if((t2 + tt2) == 0){
						map.put("scale", 0);	
					}else {
						map.put("scale", ((tt1+t1)/(tt2+t2)));
					}					
					list.add(map);
				}
	      }
	      Collections.reverse(list);
		return list;
	}

}
