package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

public interface CarService {

	/**
	 * ��ȡ������Ϣ�б�
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	PageBean searchCars(CarCondition condition, PageBean pageBean);

	/**
	 * ��ȡ���г�����Ϣ
	 */
	public List<Car> queryCar();
	/**
	 * ͨ��ID��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	Car getCar(Integer id);

	/**
	 * ��ӳ�����Ϣ
	 * @throws Exception 
	 */
	void addCar(Car car) throws Exception;

	Integer findCarFlapperCount(String flapper);
	
	PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * ɾ��������Ϣ
	 * @param id
	 */
	void delByCarId(Integer id) throws Exception;

	

}
