package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Sealed;

public interface SealedMobileDao {
	
	/**
	 * 保存施封信息
	 * @param sealed
	 */
	public void saveLockInfo(Sealed sealed) throws Exception;

	public Sealed querySealedByCodeIdAndCarId(Integer codeId, Integer carId,
			Integer wAYBILL_UNFINISHED, Integer comId);
	
//	public Sealed querySealedCodeByCode(String  code);

	public void updateStatus(Integer seaId, Integer status);

	public void updateFreezeId(Integer seaId, Integer freId);
	
}
