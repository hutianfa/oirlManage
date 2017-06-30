package com.ltmcp.mobile.dao.impl;

import java.sql.Timestamp;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.mobile.dao.Dbc_BagCodeBindDao;

public class Dbc_BagCodeBindDaoImpl extends BaseDaoHibImpl implements Dbc_BagCodeBindDao{
    /**
     * ȥ���ݿ��е�dbc_bagcodebind���ѯ�Ƿ��Ѿ����ڸô��Ӷ�ά��
     * */
	
	@Override
	public boolean checkBagcodeExist(String bagCode) {
		StringBuilder sb=new StringBuilder("select count(id) from Dbc_BagCodeBind where bag_code=? ");
		Integer count=super.queryRowCount(sb.toString(),bagCode);
		if(count>0){
			return true;//��ʾ�����Ѿ����ڸô��Ӷ�ά��
		}
		return false;//��ʾ����û�иô��Ӷ�ά�룬����ִ�в������ݿ����
	}
	/**
	 * ����Dbc_BagCodeBind��Ӧ���е�״̬��ɨ�������Ӷ�ά�룬����20�����Ӷ�ͨ���˷�������״̬������20��֮��ɨ�����Ӷ�ά�룩
	 * @param
	 */
	public void changeBagCodeStatusInRegister(String code, String name,Timestamp ts){
		StringBuilder sb = new StringBuilder("update Dbc_BagCodeBind set status=1 , regist_name=? , regist_time=? where bag_code=?");
		super.updateByHql(sb.toString(),name,ts,code);
	}
	/***
	 * �����Ӷ�ά���Ƿ����ӹ�����Ҳ�ǵ�һ���жϵ����ݣ�
	 * @param
	 */
	public boolean checkBagcodeStaAndBagCode(String code){
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code is not null and status=1 and bag_code=?");
		Integer count=super.queryRowCount(sb.toString(),code);
		if(count>0){
			return true;//�ô��Ӷ�ά���Ѿ������ӹ���
		}
		return false;//�ô��Ӷ�ά��û�б����ӹ���
	}
	@Override
	public boolean checkCasecodeExist(String caseCode) {
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code=?");
		Integer count=super.queryRowCount(sb.toString(),caseCode);
		if(count>0){
			return true;//�����Ӷ�ά���Ѿ���dbc_bagcodebind��
		}
		return false;//�����Ӷ�ά�벻��dbc_bagcodebind��
	}
	@Override
	public boolean multipleScan(String bagCode) {
		StringBuilder sb = new StringBuilder("select count(id) from Dbc_BagCodeBind where box_code is null and status=1 and bag_code=?");
		Integer count=super.queryRowCount(sb.toString(),bagCode);
		if(count>0){
			return true;//�ô��Ӷ�ά���Ѿ��ı�״̬Ϊ1������Ҫ�������Ӷ�ά�룬Ҳ�����ظ�ɨ�˴��ӵĶ�ά��
		}
		return false;//�ô��Ӷ�ά��û�б����ӹ���
		
	}
}
