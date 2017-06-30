package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;

public interface firstOrderDao {
	
	/**
	 * ��ѯһ��������Ϣ
	 * @param car
	 * @return
	 */
	//public Car queryCarByCarId(Car car);
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param currentPage ��ǰҳ
	 * @param pageSize ҳ��С
	 * @param condition ��ѯ����
	 * @return ��ѯ���
	 */
	//public List<Car> findCars(Integer currentPage,Integer pageSize,CarCondition condition);
	
	/**
	 * ��ȡ���ж�����Ϣ
	 */
	public List<first_order> findFirst_order(int comid);
	/**
	 * ���һ��������Ϣ
	 * @param first_order
	 */
	public void insertFirst_order(first_order first_order);
	/**
	 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
	 * @param
	 */
	public void orderModify(Integer id, Integer num);
	
	 /**
     * ��ⶩ��״̬
     * @param id ������ţ������ţ�
     * @param status ����״̬
     * @return
     */
	public boolean checkOrderStatus(Integer id, Integer status);
	/**
	 * ���³�����Ϣ
	 * @param car
	 */
	//public void updateCar(Car car);

	/**
	 * ��ȡ��ѯ������ȡ����������
	 * @param condition
	 * @return
	 */
	//public Integer queryCarCountByCondition(CarCondition condition);

	//public void delByCarId(Integer carId);
	
	/**
	 * ����ID��ȡ����ID
	 * @param carId
	 */
	//public boolean queryCarIdExit(Integer carId);

	//public int queryCarFlapperCount(String carFlapper, Integer cAR_NORMAL);
	
}
