package com.ltmcp.mobile.biz;

import com.ltmcp.entity.Sealed;

public interface OutsourcingMobileBiz {
	//根据code 获取二维码的记录id
	public Integer findCodeId(String code);
	//根据code 的 id 获取 对应的运单
	public Sealed findSeaByCodeId(Integer id);
}
