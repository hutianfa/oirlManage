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
	 * @param comid 二级分公司id
	 * @return 返回该二级分公司下所属片区
	 */
	public List<Area> findAreaIdByComId(int comid);
	/**
     * 
     * @param areaid 片区id
     * @return 返回该片区名称name
     */
	public List<shoufa_person> searchAreaNameByAreaid(int areaid);
	/**
     * 
     * @param areaid 片区id（利用这个片区id去查该片区下各个站点的得到片区分发的数量）
     * @return 返回片区下属站点总数（片区下发各个站点的数量）集合在一个list中
     */
	public List<Map<String, Object>> serachTotalPositionByArea(int areaid);
	/**
     * 
     * @param areaid 片区id（利用这个片区id去查下属所有站点解封未施封）
     * @return 返回单个站点解封未施封集合在一个list中
     */
	public List<Map<String, Object>> unlockNotsealed(int areaid);
	/**
     * 
     * @param areaid 片区id（利用这个片区id去查下属所有站点施封码未注册）
     * @return 返回单个站点施封码未注册集合在一个list中
     */
	public List<Map<String, Object>> sealNotRegistered(int areaid);
	/**
     * 
     * @param 二级分公司comid
     * @return 二级分公司订单列表
     */
	public List<first_order> companyContByFirstOrder(int comid);
	
    /**
     * 
     * @param areaid 片区id（利用这个片区id第一步去查下属所有站点）
     * @return 返回单个站点正常消耗封签的数目
     */
	public List<Map<String, Object>> periodNormallyEndsNumber(int areaid);
	/**
	 * 获取二级分公司，片区，油站和油库的库存
	 */
	void query_The_Inventory();
	
	/**
	 * 
	 * @param comid 二级分公司id
	 * @return 一个shoufa_person
	 */
	public List<shoufa_person> findCompanyPersonNameByComid(Integer comid);
	
	/**
	 * 
	 * @param comid 二级公司id
	 * @return 片区
	 */
	public List<Area> serachAreaAddress(Integer comid);
   
	/**
	 * 
	 * @param area_id 片区id
	 * @return 返回的是片区经理下发各自管理的站点封签总数
	 */
	public int countByArea_id(Integer area_id);
	/**
	 * 
	 * @param area_name 片区名称
	 * @return 片区总数（这个总数是二级营销科处发出到各片区的数目）
	 */
	public int countByArea_name(String area_name);
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
	//public List<first_order> queryFirst_order();
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
	//void addFirst_order(first_order first_order) throws Exception;
	/**
	 * 修改订单（数量），web端点击保存之后传递数据后台此方法修改订单
	 * @param
	 */
   // void orderModify(Integer id, Integer num);
    
    /**
     * 检测订单状态
     * @param id 订单编号（订单号）
     * @param status 订单状态
     * @return
     */
  //  boolean checkOrderStatus(Integer id,Integer status);
	//Integer findCarFlapperCount(String flapper);
	
	//PageBean getSealedsByCarId(Integer id, PageBean pageBean);

	/**
	 * 删除车辆信息
	 * @param id
	 */
	//void delByCarId(Integer id) throws Exception;

	

}
