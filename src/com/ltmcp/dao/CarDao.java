package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;

public interface CarDao {
	
	/**
	 * ��ѯһ��������Ϣ
	 * @param car
	 * @return
	 */
	public Car queryCarByCarId(Car car);
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param currentPage ��ǰҳ
	 * @param pageSize ҳ��С
	 * @param condition ��ѯ����
	 * @return ��ѯ���
	 */
	public List<Car> findCars(Integer currentPage,Integer pageSize,CarCondition condition);
	
	/**
	 * ��ȡ���г�����Ϣ
	 */
	public List<Car> findCar();
	/**
	 * ���һ��������Ϣ 
	 * @param car
	 */
	public void insertCar(Car car);
	
	/**
	 * ���³�����Ϣ
	 * @param car
	 */
	public void updateCar(Car car);

	/**
	 * ��ȡ��ѯ������ȡ����������
	 * @param condition
	 * @return
	 */
	public Integer queryCarCountByCondition(CarCondition condition);

	public void delByCarId(Integer carId);
	
	/**
	 * ����ID��ȡ����ID
	 * @param carId
	 */
	public boolean queryCarIdExit(Integer carId);

	public int queryCarFlapperCount(String carFlapper, Integer cAR_NORMAL);
	
}
