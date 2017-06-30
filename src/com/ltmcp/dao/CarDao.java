package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;

public interface CarDao {
	
	/**
	 * 查询一条车辆信息
	 * @param car
	 * @return
	 */
	public Car queryCarByCarId(Car car);
	
	/**
	 * 分页查询车辆信息
	 * @param currentPage 当前页
	 * @param pageSize 页大小
	 * @param condition 查询条件
	 * @return 查询结果
	 */
	public List<Car> findCars(Integer currentPage,Integer pageSize,CarCondition condition);
	
	/**
	 * 获取所有车辆信息
	 */
	public List<Car> findCar();
	/**
	 * 添加一条车辆信息 
	 * @param car
	 */
	public void insertCar(Car car);
	
	/**
	 * 更新车辆信息
	 * @param car
	 */
	public void updateCar(Car car);

	/**
	 * 获取查询条件获取车辆的条数
	 * @param condition
	 * @return
	 */
	public Integer queryCarCountByCondition(CarCondition condition);

	public void delByCarId(Integer carId);
	
	/**
	 * 根据ID获取汽车ID
	 * @param carId
	 */
	public boolean queryCarIdExit(Integer carId);

	public int queryCarFlapperCount(String carFlapper, Integer cAR_NORMAL);
	
}
