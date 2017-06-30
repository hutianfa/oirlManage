package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;

public interface DimensionalBarCodeMobileDao {
	
	/**
	 * 检测二维码是否存在
	 * @param code
	 * @return
	 */
	public boolean queryCodeExists(String code);//施封二维码
	
	public boolean queryCodeExist(String code);//解封二维码
	
	public boolean cheackSeales(String code);//检测重复二维码
	public boolean checkSealesabandoned(String code);//检测施封码废弃
	
	
	/**
	 * 根据二维码查询ID
	 * @param code
	 * @return
	 */
	public Integer queryIdByCodeStatus(String code,Integer Status);//根据施封二维码查询ID
	public Integer queryIdByCodeUnstatus(String code,Integer Status);//根据解封二维码查询ID
	
	public void updateStatusById(Integer id,Integer status);//根据id更改施封状态
	public void updateUnstatusById(Integer id,Integer status);//根据id更改解封状态
	
	/**
	 * 根据二维码获取二维码的状态
	 * @param Status
	 * @return
	 */
	public Integer queryCodeByStatus(String code) throws Exception;//施封二维码的状态
	
	public boolean ExitsCodeEqualsPhoto(String code);//根据二维码获取上传图片信息
	
	/**
	 * 根据二维码Id更新状态    施封方
	 * @param code
	 */
	public void updateStatusByCode(Integer codeId,String seaImgs);
	
	public void updateFreStatusByCodeId(Integer codeId,String freImgs);
	
	
	/**
	 * 更新施封状态
	 * @param codeId
	 */
	
	public void updateSeaStatustwo(Integer codeId);
	public void updateSealedByCodeIdAndCarId(Integer codeId, Integer carId,Integer wAYBILL_UNFINISHED,Integer comId) ;
	/**
	 * 注册二维码
	 */
	public void saveCode(DimensionalBarCode code);
	/**
	 * 检测袋子二维码是否已经被使用
	 */
	public boolean checkBagCodeInDBC(String code);
	/**
	 * 袋子二维码插入表
	 */
	public void saveCodeBag(Dbc_BagCodeBind bacb);
	/**
	 * 袋子绑定二维码
	 */
	public void saveBagCode(String bagcode,String name);
	/**
	 * 根据二维码查找二维码是否应经注册（一部对两个码进行操作）
	 */
	public Integer queryCodeByCode(String code);//根据二维码查二维码
	public Integer queryCodeByUncode(String code);//根据二维码查二维码
	/**
	 * 箱子绑定袋子
	 * @param caseCode
	 * @param name
	 */
	public void addCaseCode(String caseCode, String name);
}
