package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Area;
import com.ltmcp.entity.Area_manager_inventory;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;

public interface DistributionDao {

	/***
	 * 根据箱子二维码去判断是否重复在数据库的表中Position_inventory和secondorder
	 * @param code 箱子二维码
	 * @return true或者false
	 * 
	 */
    public boolean checkBoxCodeBySecondOrderAndPosition_inventory(String code);
	/***
	 * 根据袋子二维码去判断是否重复扫描或者已经分发
	 * @param code 袋子二维码
	 * @return true或者false
	 */
	public boolean checkBagCodeBySecondOrderAndPosition_inventory(String code);
	/***
	 * 根据站点去查询position_inventory表中站点的总数（无时间限制）
	 * @param zhanDianName 站点名称
	 * @return 该站点所有已经分发的总数（从使用追溯开始，中途没使用，停止使用追溯，等情况不在考虑范围）
	 */
	public Integer findCountByZhandian_name(String zhanDianName);
	/***
	 * 根据片区名称去查询secoudorder表（袋子与箱子）总数，暂时没用时间，统计全部
	 * @param name 片区名称
	 * @return 二级分公司发到该片区的总数
	 */
	public Integer findCountByArea_name(String name);
	
	/***
	 *  根据分发姓名（**经理）
	 * @param name 分发人name
	 * @return list(shoufa_person)
	 */
	public List<shoufa_person> findPianquIdByName(String name);
	
	/***
	 * 分发登录验证
	 * @param username 登录账号
	 * @param password 登录密码
	 * @return
	 */
	public boolean checkDisLogin(String username, String password);
	
	/***
	 * 根据账号和密码进行账号的查询：分发人和该分发人的地点
	 * @param username 登录账号
	 * @param password 登录密码
	 * @return 暂时设计返回一个账号的list，（可以实际返回一个对象即可）
	 */
	public List<shoufa_person> serachPersonAddressorName(String username, String password);
	
	/***
	 * 返回值是根据这个id查出的所有片区
	 * @param com_id 分发人表（fenfa_person表中的com_id)
	 * @return 返回值是Area的list列表
	 */
	public List<Area> serachAreaAddressor(int com_id);
	
	/***
	 * 返回Position中的片区id关联的站点
	 * @param areaid 片区id
	 * @return Position的list列表
	 */
	public List<Position> serachPostionAddressor(int areaid);
	
	/***
	 * 检测袋子二维码
	 * @param bagCode 袋子二维码
	 * @return 存在true，不存在false（状态也要判定？）
	 */
	public boolean checkBagCode(String bagCode);
	
	/***
	 * 检测箱子二维码
	 * @param boxCode
	 * @return 存在true，不存在false（状态也要判定？）
	 */
	public boolean checkBoxCode(String boxCode);
	
	/**
	 * 保存箱子code
	 * @param so 二级订单（二级分公司往下面发）
	 */
	public void saveSecond_order(Second_order so);
	
	/***
	 * 根据片区经理id找到片区经理地址
	 * @param pianquId 片区经理id
	 * @return 片区经理地址
	 */
	public List<shoufa_person> findAddressByPianquId(int pianquId);
	
	/***
	 * 根据站点id找到站点名称
	 * @param zhandianId 站点id
	 * @return 找到的站点名称
	 */
	public List<Position> findAddrressByZhandianId(int zhandianId);
	
	/***
	 * 将ami保存到二级分公司表Area_manager_inventory
	 * @param ami 对象
	 */
	public void saveArea_manager_inventory(Area_manager_inventory ami);
	
	/***
	 * 将pi保存到站点position_inventory
	 * @param pi 站点信息对象pi
	 */
	public void savePosition_inventory(position_inventory pi);
	
	/***
	 * 根据code查询出该封签关联的袋子
	 * @param code 封签内码
	 */
	public List<DimensionalBarCode> findBagCodeByCode(String code);
	
	/***
	 * 根据签封关联的袋子码找到箱子二维码
	 * @param findbagCode 封签关联的袋子二维码
	 * @return list
	 */
	public List<Dbc_BagCodeBind> findBoxCodeByFindbagCode(String findbagCode);
	
	/***
	 * 根据封签内码判断该二维码是否系统生成
	 * @param code 检测内码是否是存在
	 * @return false或者true
	 */
	public boolean checkCodeNeiMa(String code);
	
	/***
	 * 根据封签外码判断该二维码是否系统生成
	 * @param code 检测外码是否是存在
	 * @return false或者true
	 */
	public boolean checkCodeWaiMa(String code);
	/***
	 * 根据袋子二维码先去找站点所属对象
	 * @param findbagCode
	 * @return 一个对象position_inventory
	 */
	public List<position_inventory> findPositionBybagCode(String findbagCode);
	
	/***
	 * 根据箱子二维码再次去position_inventory表查找箱子二维码是否存在，前提是：站点表中找不到袋子二维码，上级可能整箱发给该站点
	 * @param findboxCode 箱子二维码
	 * @return 一个对象position_inventory
	 */
	public List<position_inventory> findPositionByboxCode(String findboxCode);
	
	/***
	 * 根据袋子二维码去Second_order找到站点
	 * @param findbagCode 袋子二维码
	 * @return 一个对象Second_order
	 */
	public List<Second_order> findSecondre_sh_addressInStationBybagCode(String findbagCode);
	
	/***
	 * 根据箱子二维码去Second_order找到站点
	 * @param findboxCode 箱子二维码
	 * @return 一个对象 Second_order
	 */
	public List<Second_order> findSecondre_sh_addressInStationByboxCode(String findboxCode);
	
	/***
	 * 根据code查询出该封签关联的袋子
	 * @param code 封签外码
	 */
	public List<DimensionalBarCode> findBagCodeByWaimaCode(String code);
	
	/***
	 * 查询Area表中id所对应的comid
	 * @param pianquId 片区id
	 * @return Area的一个对象
	 */
	public List<Area> findComIdByPianquId(int pianquId);
	
	/***
	 * 根据shoufa_person表中的comid查询shoufa_person对象（里面有二级分公司地址）
	 * @param comId 二级分公司id
	 * @return shoufa_person对象
	 */
	public List<shoufa_person> findcompanyAddressBycomId(int comId);
	
	/***
	 * 根据配对表的id找到施封信息（前提条件：配对表中status为1（运输中），或者 2（封签作废））
	 * @param dimensionalBarCode_id 配对表的id
	 * @return 一个解封表对象
	 */
	public List<Sealed> findSealedInformationBydimensionalBarCode_id(int dimensionalBarCode_id);
	
	/***
	 * 根据施封表的id查询出解封信息对象
	 * @param sealedId 施封表的id
	 * @return 返回一个解封表对象
	 */
	public List<Freeze> findFreezeBysealedId(int sealedId);
	
	/***
	 * 根据地址返回对象Position
	 * @param address 二级公司直接发给站点或者油库的地址
	 * @return Position对象
	 */
	public List<Position> findPianQuIdByReceive_sh_address(String address);
	
	/***
	 * 找到片区id
	 * @param address shoufa_person中的地址
	 * @return 一个对象
	 */
	public List<shoufa_person> findPianQuIdByshoufa_personAddress(String address);
	
}
