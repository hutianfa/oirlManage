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
	 * ��ȡ������Ϣ�б�
	 * @param condition
	 * @param pageBean
	 * @return
	 */
	//PageBean searchCars(CarCondition condition, PageBean pageBean);//��ҳҪ�õ�pageBean

	/**
	 * ��ȡ���ж�����Ϣ��������һ����չʾ����ҲӦ�ò�һ����
	 */
	public List<first_order> queryFirst_order(int comid);
	/**
	 * ͨ��ID��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	//Car getCar(Integer id);

	/**
	 * ���web�µ���Ϣ
	 * @throws Exception 
	 */
	void addFirst_order(first_order first_order) throws Exception;
	/**
	 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
	 * @param
	 */
    void orderModify(Integer id, Integer num);
    
    /**
     * ��ⶩ��״̬
     * @param id ������ţ������ţ�
     * @param status ����״̬
     * @return
     */
    boolean checkOrderStatus(Integer id,Integer status);
	//Integer findCarFlapperCount(String flapper);
	
	//PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * ɾ��������Ϣ
	 * @param id
	 */
	//void delByCarId(Integer id) throws Exception;

	

}
