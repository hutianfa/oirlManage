package com.ltmcp.mobile.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.CarMobileDao;

public class CarMobileDaoImpl extends BaseDaoHibImpl implements CarMobileDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findCars(Integer comId) {
		StringBuilder sb=new StringBuilder(" from Car car where car.company.comId=? and car.carStatus=? ");
		return super.findList(sb.toString(),comId,Comm.CAR_NORMAL);
	}


	@Override
	public boolean carIsExistByComId(String carFlaper, Integer comId) {
		StringBuilder sb=new StringBuilder(" select count(car.carId) from Car car ");
		sb.append(" where car.carFlapper like '%"+carFlaper+"%'");
		sb.append(" and car.company.comId= "+comId);
		
		Integer count=super.queryRowCount(sb.toString(),null);
		if(count>0){
			return true;
		}
		return false;
	}


	@Override
	public Integer queryCarIdByFlapper(String flapper) {
		StringBuilder sb=new StringBuilder(" select carId from Car car");
		sb.append(" where car.carFlapper like '%"+flapper+"%'");
		sb.append("and car.carStatus= "+ Comm.CAR_NORMAL);
		Integer carId=(Integer) super.queryUniqueObject(sb.toString(),null);
		return carId;
	}


	@Override
	public Car queryCarById(Integer id) {
		return (Car) super.query(Car.class, id);
	}


	@Override
	public List<Sealed> queryCarByQRCode(String QRCode) {
		//System.out.println("carMobileDaolmpl类中打印解封二维码:"+QRCode);
//		if(true){
//			StringBuilder sb=new StringBuilder(" from Sealed ");
//			sb.append(" where dimensionalBarCode.unfreeze_content=?");
//			return super.findList(sb.toString(), QRCode);//返回空null
//		}else if(true){
//			StringBuilder sb=new StringBuilder(" from Sealed ");
//			sb.append(" where dimensionalBarCode.unfreeze_content=? and dimensionalBarCode.unfreeze_status=? ");
//			return super.findList(sb.toString(), QRCode,Comm.TWO_CODE_LOSS);//返回不为空
//		}
//		else{}
		StringBuilder sb=new StringBuilder(" from Sealed ");
		sb.append(" where dimensionalBarCode.unfreeze_content=? and dimensionalBarCode.unfreeze_status=? ");
		return super.findList(sb.toString(), QRCode,Comm.TWO_CODE_CENTER);	
	}
	@Override
	public Integer checkUnfreezeCode(String QRCode){
		
		StringBuilder sb=new StringBuilder(" from DimensionalBarCode ");
 	    sb.append(" where unfreeze_content=? ");
 	    //System.out.println("下面执行hql语句只查询配对表中的解封码是否存在！！");
 	    List aa = super.findList(sb.toString(), QRCode);
 	   //System.out.println("执行之后查询配对表中解封码返回的布尔值："+aa.isEmpty()+"列表aa的长度是："+aa.size());
 	    if(aa.isEmpty())//
 	    {
 	    	//System.out.println("返回-470");
 	    	 return 10;  	
 	    }else
 	    {
 	    	//System.out.println("进入-469");
 	    	StringBuilder sb1=new StringBuilder(" from Sealed ");
 	    	
 	    	sb1.append(" where dimensionalBarCode.unfreeze_content=? and dimensionalBarCode.unfreeze_status=? ");
		    List bb = super.findList(sb1.toString(), QRCode,2); 
 	    	
	        if(!bb.isEmpty())//isEmpty()，当字符串长度为0是，返回true
		    {
	        	//System.out.println(11+"已经返回11");
		    	return 11;
		    }
		 }
			 return 0;
	}

	@Override
	public Integer queryCarFredNum(Integer flapper) {
		SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取当日开始时间
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    //当前时间
	    Date end = c.getTime();
	    String endTime=matter.format(end);
	    
	    c.add(Calendar.MINUTE, -60);
	    //开始时间
	    Date begin = c.getTime();
	    String beginTime=matter.format(begin);

		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s ");
		sb.append(" where s.car.carId="+flapper);
		sb.append(" and ( s.seaStatus = "+Comm.WAYBILL_COMPLETED +" or s.seaStatus = "+Comm.WAYBILL_EXCEPTION+")");		
		sb.append(" and (s.freeze.freTime between '"+beginTime+"' and  '"+endTime+"' )");
		
		return super.queryRowCount(sb.toString());
	}
	
}
