package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;

public interface firstOrderDao {
	
	/**
	 * 查询一条车辆信息
	 * @param car
	 * @return
	 */
	//public Car queryCarByCarId(Car car);
	
	/**
	 * 分页查询车辆信息
	 * @param currentPage 当前页
	 * @param pageSize 页大小
	 * @param condition 查询条件
	 * @return 查询结果
	 */
	//public List<Car> findCars(Integer currentPage,Integer pageSize,CarCondition condition);
	
	/**
	 * 获取所有订单信息
	 */
	public List<first_order> findFirst_order(int comid);
	/**
	 * 添加一条订单信息
	 * @param first_order
	 */
	public void insertFirst_order(first_order first_order);
	/**
	 * 修改订单（数量），web端点击保存之后传递数据后台此方法修改订单
	 * @param
	 */
	public void orderModify(Integer id, Integer num);
	
	 /**
     * 检测订单状态
     * @param id 订单编号（订单号）
     * @param status 订单状态
     * @return
     */
	public boolean checkOrderStatus(Integer id, Integer status);
	/**
	 * 更新车辆信息
	 * @param car
	 */
	//public void updateCar(Car car);

	/**
	 * 获取查询条件获取车辆的条数
	 * @param condition
	 * @return
	 */
	//public Integer queryCarCountByCondition(CarCondition condition);

	//public void delByCarId(Integer carId);
	
	/**
	 * 根据ID获取汽车ID
	 * @param carId
	 */
	//public boolean queryCarIdExit(Integer carId);

	//public int queryCarFlapperCount(String carFlapper, Integer cAR_NORMAL);
	
}
