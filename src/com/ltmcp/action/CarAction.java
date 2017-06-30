package com.ltmcp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.CarService;
import com.ltmcp.util.RegexTool;

public class CarAction extends BaseAction{
	
	private CarCondition condition; //车辆查询条件
	private PageBean pageBean;
	private CarService carService;
	private Integer id;
	private Car car;
	private Car addCar;
	private List<Car> list=new ArrayList<Car>();
	private List<Sealed> sealeds=new ArrayList<Sealed>();
	
	
	/**
	 * 获取车辆列表
	 * @return
	 */
	public String list(){
		pageBean=carService.searchCars(condition,pageBean);
		list=pageBean.getList();
		return super.returnToViewList(pageBean);
	}
	
	/**
	 * 车辆模糊查询
	 */
	public void getCarList(){
		List<Car> car = carService.queryCar();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		for(Car c : car){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carFlpper", c.getCarFlapper());
			map.put("carid", c.getCarId());
			list.add(map);
		}		
		super.outPrintJsonByArray(list);
	}
	
	
	
	/**
	 * 获取某一个车辆信息
	 * @return
	 */
	public String detailed(){
		car=carService.getCar(id);
		if(null==car){return "error";}
		pageBean=carService.getSealedsByCarId(id,pageBean);
		sealeds=pageBean.getList();
		return super.returnToViewDetailed(car);
	}
	
	/**
	 * 添加车辆信息
	 * @return
	 */
	public void add(){
		try {
			if(carAddValidate(addCar)==true){
				if(carService.findCarFlapperCount(addCar.getCarFlapper()) >0){
					super.getResponse().getWriter().print(-1);
				}else{
					carService.addCar(addCar);
					super.getResponse().getWriter().print(0);
				}				
				
			}else{
				super.getResponse().getWriter().print(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 验证添加信息
	 * @param car
	 * @return
	 */
	private boolean carAddValidate(Car car){
		if(null!=car){
			if(null!=car.getCarFlapper()&&!"".equals(car.getCarFlapper())){
				if(RegexTool.regexString("(^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$)",car.getCarFlapper())==false){
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	
	public void del(){
		try {
			if(null!=id){
				carService.delByCarId(id);
				super.getResponse().getWriter().print(0);
			}else{
				super.getResponse().getWriter().print(1);
			}
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void update(){
		
	}
	
	

	public Car getAddCar() {
		return addCar;
	}

	public void setAddCar(Car addCar) {
		this.addCar = addCar;
	}

	public Car getCar() {
		return car;
	}


	public CarCondition getCondition() {
		return condition;
	}

	public void setCondition(CarCondition condition) {
		this.condition = condition;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Car> getList() {
		return list;
	}

	public void setList(List<Car> list) {
		this.list = list;
	}

	public List<Sealed> getSealeds() {
		return sealeds;
	}

	public void setSealeds(List<Sealed> sealeds) {
		this.sealeds = sealeds;
	}
	
	
}
