package com.ltmcp.mobile.dao;

import com.ltmcp.entity.FixedSeal;

public interface FixSeaFreMobileDao {

	//固定封签的施封
	public void saveLockFix(FixedSeal sea) throws Exception;
	//施封成功，更改二维码状态
	public void updateFreCodeSta(Integer codeId);
	//根据施封码查找解封code
	public String queryFixSeaCode(String code);
	//更具解封code 查询Fix的id
	public Integer queryFixId(String code);
	//固定封签的解封
	public void saveUnLockFix(FixedSeal sea) throws Exception;	
	//解封成功，更改二维码状态
	public void updateUnFreCodeSta(Integer codeId);
	
	
	//根据正在使用的二维码获取车牌号
	public String queryCarByQRCode(String qRCode);
	//根据当前二维码获取关联的施封二维码
	public String queryCodeByQRCode(String qRCode);
	//根据当前二维码查询codeid
	public Integer queryIdByFixCodeUnstatus(String code, Integer Status);
	//根据id查询FixedSeal
	public FixedSeal queryFixedSeal(Integer id);
}
