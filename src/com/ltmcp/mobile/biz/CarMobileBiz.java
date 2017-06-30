package com.ltmcp.mobile.biz;

import java.util.List;
import java.util.Map;

import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

/**
 * 安卓端手机逻辑层
 * @author Administrator
 *
 */
public interface CarMobileBiz {
	
	/**
	 * 获取车辆列表
	 * @return
	 */
	public List<Car> searchCars(String name,String password);
	/**
	 * 获取车牌
	 * @param name
	 * @param password
	 * @param QRCode
	 * @return
	 */
	
	public List<Sealed> getCarByQRCode(String name, String password, String QRCode);
	//检测解封码是否存在注册表470，检测解封码已经报废469
	public Integer checkUnfreezeCode(String QRCode);
	//获取车辆为施封的口
	public List<Map> getCarSeaFreNum(List<Map> carFlapper,String str);
	
}
