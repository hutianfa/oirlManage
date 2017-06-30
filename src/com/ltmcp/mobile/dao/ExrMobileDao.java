package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;

public interface ExrMobileDao {

	//查询近时异常列表
	public List<Sealed> exrListTime(Integer comId,int currPage);
	//查询超时异常
	public List<Sealed> exrListTimeout(Integer comId,int currPage);
	//查询解封异常
	public List<ExcRecord> exrListFre(Integer comId,int currPage);
	//获取异常详细内容
	public ExcRecord detailed(ExcRecord exr); 
	//处理异常
	public void updateExr(ExcRecord exr);
	
	//跟新授权运单的读取状态
	public void gaibianEmpo(Integer id);
}
