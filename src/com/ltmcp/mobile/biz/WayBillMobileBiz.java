package com.ltmcp.mobile.biz;

import java.util.List;
import com.ltmcp.entity.Empower;

public interface WayBillMobileBiz {

	/**
	 * 添加施封信息
	 * 
	 * @param name  账号名称
	 * @param password 密码
	 * @param code 二维码
	 * @param plateNumber 车牌号码
	 * @param filePath    
	 * @param longitude
	 * @param latitude
	 * @return 返回0:添加成功 返回:-1 二维码未在系统中注册 -2:车辆未在系统中注册 -3:其他原因添加失败
	 */
	public Integer addLockInfo(String name, String password, String code,
			String plateNumber, String filePath,String youpin,String youfilePath, String latitude,
			String longitude, Integer positionId, String path,Integer tag,String wayNumber,String time)
			throws Exception;

	/**
	 * 添加解封信息
	 * 
	 * @param name
	 *            账号名称
	 * @param password
	 *            密码
	 * @param code
	 *            二维码
	 * @param plateNumber
	 *            车牌
	 * @param exceptionList
	 *            异常列表
	 * @param filePath
	 * @param longitude
	 * @param latitude
	 * @return 返回0:解封成功 -1:二维码未在系统中注册 -2:车辆未在系统中注册 -3:异常添加错误 -4:运单找不到 -5:其他异常
	 * @throws Exception
	 */
	
	public Integer addUnlockInfo(String name, String password, String code,
			String plateNumber, Integer exceptionList, String filePath,
			String latitude, String longitude, Integer positionId,String powerCode,Integer powerTips,String wayNumber,String time)
			throws Exception;

	/**
	 * 返回二维码状态
	 * 
	 * @param codeContent
	 * @return
	 * @throws Exception
	 */
	public Integer returnCodeStatus(String codeContent) throws Exception;
	
	
	/**
	 * 查询授权信息
	 */
	public Empower findPowerCode(String powerCode);
}
