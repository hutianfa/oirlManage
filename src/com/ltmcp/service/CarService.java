package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

public interface CarService {

	/**
	 * 获取车辆信息列表
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchCars(CarCondition condition, PageBean pageBean);

	/**
	 * 获取所有车辆信息
	 */
	public List<Car> queryCar();
	/**
	 * 通过ID获取车辆信息
	 * @param id
	 * @return
	 */
	Car getCar(Integer id);

	/**
	 * 添加车辆信息
	 * @throws Exception 
	 */
	void addCar(Car car) throws Exception;

	Integer findCarFlapperCount(String flapper);
	
	PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * 删除车辆信息
	 * @param id
	 */
	void delByCarId(Integer id) throws Exception;

	

}
