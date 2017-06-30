package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.first_order;

public interface firstOrderService {

	/**
	 * 获取车辆信息列表
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	//PageBean searchCars(CarCondition condition, PageBean pageBean);//分页要用的pageBean

	/**
	 * 获取所有订单信息（地区不一样，展示订单也应该不一样）
	 */
	public List<first_order> queryFirst_order(int comid);
	/**
	 * 通过ID获取车辆信息
	 * @param id
	 * @return
	 */
	//Car getCar(Integer id);

	/**
	 * 添加web下单信息
	 * @throws Exception 
	 */
	void addFirst_order(first_order first_order) throws Exception;
	/**
	 * 修改订单（数量），web端点击保存之后传递数据后台此方法修改订单
	 * @param
	 */
    void orderModify(Integer id, Integer num);
    
    /**
     * 检测订单状态
     * @param id 订单编号（订单号）
     * @param status 订单状态
     * @return
     */
    boolean checkOrderStatus(Integer id,Integer status);
	//Integer findCarFlapperCount(String flapper);
	
	//PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * 删除车辆信息
	 * @param id
	 */
	//void delByCarId(Integer id) throws Exception;

	

}
