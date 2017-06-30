package com.ltmcp.dao;

import java.util.List;
import java.util.Map;

import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.shoufa_person;

public interface InventoryDao {
	/**
	 * 
	 * @param comid �����ֹ�˾id
	 * @return ���ظö����ֹ�˾������Ƭ��
	 */
	public List<Area> findAreaIdByComId(int comid);
	/**
     * 
     * @param areaid Ƭ��id
     * @return ���ظ�Ƭ������name
     */
	public List<shoufa_person> searchAreaNameByAreaid(int areaid);
	/**
     * 
     * @param areaid Ƭ��id���������Ƭ��idȥ���Ƭ���¸���վ��ĵõ�Ƭ���ַ���������
     * @return ����Ƭ������վ��������Ƭ���·�����վ���������������һ��list��
     */
	public List<Map<String, Object>> serachTotalPositionByArea(int areaid);
	/**
     * 
     * @param areaid Ƭ��id���������Ƭ��idȥ����������վ����δʩ�⣩
     * @return ���ص���վ����δʩ�⼯����һ��list��
     */
	public List<Map<String, Object>> unlockNotsealed(int areaid);
	/**
     * 
     * @param areaid Ƭ��id���������Ƭ��idȥ����������վ��ʩ����δע�ᣩ
     * @return ���ص���վ��ʩ����δע�Ἧ����һ��list��
     */
	public List<Map<String, Object>> sealNotRegistered(int areaid);
	/**
     * 
     * @param �����ֹ�˾comid
     * @return �����ֹ�˾�����б�
     */
	public List<first_order> companyContByFirstOrder(int comid);
	 /**
     * 
     * @param areaid Ƭ��id���������Ƭ��id��һ��ȥ����������վ�㣩
     * @return ���ص���վ���������ķ�ǩ����Ŀ
     */
	public List<Map<String, Object>> periodNormallyEndsNumber(int areaid);
	
	/**
	 * ��ȡ�����ֹ�˾��Ƭ������վ���Ϳ�Ŀ��
	 */
	void query_The_Inventory();
	
	/**
	 * 
	 * @param comid �����ֹ�˾id
	 * @return һ��shoufa_person
	 */
	public List<shoufa_person> findCompanyPersonNameByComid(Integer comid);
	
	/**
	 * 
	 * @param comid ������˾id
	 * @return Ƭ��
	 */
	public List<Area> serachAreaAddress(Integer comid);
	
	/**
	 * 
	 * @param area_name Ƭ������
	 * @return Ƭ����������������Ƕ���Ӫ���ƴ���������Ƭ������Ŀ��
	 */
	public int countByArea_name(String area_name);
	
	/**
	 * 
	 * @param area_id Ƭ��id
	 * @return ���ص���Ƭ�������·����Թ����վ���ǩ����
	 */
	public int countByArea_id(Integer area_id);
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
	//public List<first_order> findFirst_order();
	/**
	 * ���һ��������Ϣ
	 * @param first_order
	 */
	//public void insertFirst_order(first_order first_order);
	/**
	 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
	 * @param
	 */
	//public void orderModify(Integer id, Integer num);
	
	 /**
     * ��ⶩ��״̬
     * @param id ������ţ������ţ�
     * @param status ����״̬
     * @return
     */
	//public boolean checkOrderStatus(Integer id, Integer status);
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
