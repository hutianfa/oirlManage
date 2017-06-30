package com.ltmcp.service;

import java.util.List;
import java.util.Map;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.first_order;
import com.ltmcp.entity.shoufa_person;

public interface InventoryService 
{
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
	 * @param area_id Ƭ��id
	 * @return ���ص���Ƭ�������·����Թ����վ���ǩ����
	 */
	public int countByArea_id(Integer area_id);
	/**
	 * 
	 * @param area_name Ƭ������
	 * @return Ƭ����������������Ƕ���Ӫ���ƴ���������Ƭ������Ŀ��
	 */
	public int countByArea_name(String area_name);
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
	//public List<first_order> queryFirst_order();
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
	//void addFirst_order(first_order first_order) throws Exception;
	/**
	 * �޸Ķ�������������web�˵������֮�󴫵����ݺ�̨�˷����޸Ķ���
	 * @param
	 */
   // void orderModify(Integer id, Integer num);
    
    /**
     * ��ⶩ��״̬
     * @param id ������ţ������ţ�
     * @param status ����״̬
     * @return
     */
  //  boolean checkOrderStatus(Integer id,Integer status);
	//Integer findCarFlapperCount(String flapper);
	
	//PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * ɾ��������Ϣ
	 * @param id
	 */
	//void delByCarId(Integer id) throws Exception;

	

}
