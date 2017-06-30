package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

public interface CarMobileDao {
	
	/**
	 * 获取车辆列表
	 * @return
	 */
	public List<Car> findCars(Integer comId);
	
	public boolean carIsExistByComId(String carFlaper,Integer comId);
	
	public Integer queryCarIdByFlapper(String flapper);
	
	public Car queryCarById(Integer id);

	/**
	 * 根据二维码获取车辆信息
	 * @param qRCode
	 * @return
	 */
	public List<Sealed> queryCarByQRCode(String qRCode);
	
	
	//获取车辆的已解封口（当日0点到当前时间）
	public Integer queryCarFredNum(Integer flapper);
	//检测解封码是否存在注册表470，检测解封码已经报废469
	public Integer checkUnfreezeCode(String QRCode); 
}
