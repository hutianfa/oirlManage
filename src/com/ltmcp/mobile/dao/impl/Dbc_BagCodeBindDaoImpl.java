package com.ltmcp.mobile.dao.impl;

import java.sql.Timestamp;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.mobile.dao.Dbc_BagCodeBindDao;

public class Dbc_BagCodeBindDaoImpl extends BaseDaoHibImpl implements Dbc_BagCodeBindDao{
    /**
     * 去数据库中的dbc_bagcodebind表查询是否已经存在该袋子二维码
     * */
	
	@Override
	public boolean checkBagcodeExist(String bagCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Dbc_BagCodeBind where bag_code=? ");
		Integer count=super.queryRowCount(sb.toString(),bagCode);
		if(count>0){
			return true;//表示表中已经存在该袋子二维码
		}
		return false;//表示表中没有该袋子二维码，可以执行插入数据库操作
	}
	/**
	 * 更新Dbc_BagCodeBind对应表中的状态（扫描多个袋子二维码，比如20个袋子都通过此方法更新状态，到达20个之后，扫描箱子二维码）
	 * @param
	 */
	public void changeBagCodeStatusInRegister(String code, String name,Timestamp ts){
		StringBuilder sb = new StringBuilder("update Dbc_BagCodeBind set status=1 , regist_name=? , regist_time=? where bag_code=?");
		super.updateByHql(sb.toString(),name,ts,code);
	}
	/***
	 * 检测袋子二维码是否被箱子关联（也是第一步判断的内容）
	 * @param
	 */
	public boolean checkBagcodeStaAndBagCode(String code){
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code is not null and status=1 and bag_code=?");
		Integer count=super.queryRowCount(sb.toString(),code);
		if(count>0){
			return true;//该袋子二维码已经被箱子关联
		}
		return false;//该袋子二维码没有被箱子关联
	}
	@Override
	public boolean checkCasecodeExist(String caseCode) {
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code=?");
		Integer count=super.queryRowCount(sb.toString(),caseCode);
		if(count>0){
			return true;//该箱子二维码已经在dbc_bagcodebind中
		}
		return false;//该箱子二维码不在dbc_bagcodebind中
	}
	@Override
	public boolean multipleScan(String bagCode) {
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code is null and status=1 and bag_code=?");
		Integer count=super.queryRowCount(sb.toString(),bagCode);
		if(count>0){
			return true;//该袋子二维码已经改变状态为1，即将要关联箱子二维码，也就是重复扫了袋子的二维码
		}
		return false;//该袋子二维码没有被箱子关联
		
	}
}
