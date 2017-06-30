package com.ltmcp.mobile.dao.impl;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Inventor_BoxCode;
import com.ltmcp.mobile.dao.caseCodeBindBizDao;
public class caseCodeBindBizDaoImpl extends BaseDaoHibImpl implements caseCodeBindBizDao{
	/**
	 * ����app�˴�������Ӷ�ά�����жϸ����Ӷ�ά���Ƿ��Ѿ�����
	 * @param ���Ӷ�ά��
	 */
	@Override
	public boolean checkCaseCodeExist(String caseCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Inventor_BoxCode where box_code=? ");
		Integer count=super.queryRowCount(sb.toString(),caseCode);
		if(count>0){
			return true;//��ʾ�����Ѿ����ڸ����Ӷ�ά��
		}
		return false;//��ʾ����û�и����Ӷ�ά�룬����ִ�в������ݿ����
	}

	@Override
	public void saveCaseCode(Inventor_BoxCode ib) {
		super.saveOrUpdate(ib);
	}
    
}
