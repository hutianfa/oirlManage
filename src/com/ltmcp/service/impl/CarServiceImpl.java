package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.CarService;

public class CarServiceImpl extends BaseServiceImpl implements CarService {
	private CarDao carDao;
	private SealedDao sealedDao;
	
	
	@Override
	public PageBean searchCars(CarCondition condition, PageBean pageBean) {
		if(null==pageBean){
			pageBean=new PageBean();
		}
		List<Car> list= carDao.findCars(pageBean.getCurentPage(), pageBean.getPageSize(), condition);
		Integer totalCount=carDao.queryCarCountByCondition(condition);
		return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());

	}


	@Override
	public List<Car> queryCar() {
		return carDao.findCar();
	}

	public void setCondition(CarCondition condition){
		if("".equals(condition.getCarFlag())){
			condition.setCarFlag(null);
		}
	}



	@Override
	public Car getCar(Integer id) {
		if(id!=null){
			Car car=new Car();
			car.setCarId(id);
			return carDao.queryCarByCarId(car);
		}
		return null;
	}

	@Override
	public void addCar(Car car) throws Exception {
		if(car!=null){
			
			HttpSession session=ServletActionContext.getRequest().getSession();
			Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
			
			car.setCarCreatetime(new Timestamp(System.currentTimeMillis()));
			car.setAdmin(admin);
			car.setCarStatus(Comm.CAR_NORMAL);
			car.setCompany(admin.getCompany());
			car.setAdminName(admin.getAdmName());
			carDao.insertCar(car);
		}
	}

	@Override
	public Integer findCarFlapperCount(String flapper) {				
		return carDao.queryCarFlapperCount(flapper,Comm.CAR_NORMAL);
	}


	@Override
	public PageBean getSealedsByCarId(Integer id, PageBean pageBean) {
		if(null==id){
			return new PageBean();
		}
		if(null==pageBean){
			pageBean=new PageBean();
		}
		List<Sealed> list=sealedDao.findSealedsByCarId(pageBean.getCurentPage(), pageBean.getPageSize(),id);
		Integer totalCount=sealedDao.querySealedCountByCarId(id);
		return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}
	

	@Override
	public void delByCarId(Integer id) throws Exception{
		if(null!=id&&0<id){
			boolean isexit=carDao.queryCarIdExit(id);
			if(isexit==false){
				throw new Exception();
			}
			carDao.delByCarId(id);
		}
	}

	public CarDao getCarDao() {
		return carDao;
	}
	
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}
	
	
	
	public SealedDao getSealedDao() {
		return sealedDao;
	}
	
	
	
	public void setSealedDao(SealedDao sealedDao) {
		this.sealedDao = sealedDao;
	}
	
}
