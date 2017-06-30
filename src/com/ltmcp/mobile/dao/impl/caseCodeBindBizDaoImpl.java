package com.ltmcp.mobile.dao.impl;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Inventor_BoxCode;
import com.ltmcp.mobile.dao.caseCodeBindBizDao;
public class caseCodeBindBizDaoImpl extends BaseDaoHibImpl implements caseCodeBindBizDao{
	/**
	 * 根据app端传入的箱子二维码来判断该箱子二维码是否已经存在
	 * @param 箱子二维码
	 */
	@Override
	public boolean checkCaseCodeExist(String caseCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Inventor_BoxCode where box_code=? ");
		Integer count=super.queryRowCount(sb.toString(),caseCode);
		if(count>0){
			return true;//表示表中已经存在该箱子二维码
		}
		return false;//表示表中没有该箱子二维码，可以执行插入数据库操作
	}

	@Override
	public void saveCaseCode(Inventor_BoxCode ib) {
		super.saveOrUpdate(ib);
	}
    
}
