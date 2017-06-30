package com.ltmcp.mobile.dao.impl;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.mobile.dao.DimensionalBarCodeMobileDao;

public class DimensionalBarCodeMobileDaoImpl extends BaseDaoHibImpl implements
		DimensionalBarCodeMobileDao {
	/***
	 * ���Ӱ󶨴���
	 * @param 
	 */
	public void addCaseCode(String caseCode, String name){
		StringBuilder sb = new StringBuilder(" update dbc_bagcodebind set box_code=? where regist_name=? order by regist_time desc limit 10");
		try {
			super.updateByHqlBindBagCode(sb.toString(),caseCode,name);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	@Override
	public boolean checkBagCodeInDBC(String code){
		StringBuilder sb=new StringBuilder("select count(id) from DimensionalBarCode");
		sb.append(" where bag_code=?");
		Integer count=super.queryRowCount(sb.toString(),code);
		if(count>0){
			return true;
		}
	    return false;
	}
	//����󶨴��ӵĶ�ά��
	@Override
	public void saveBagCode(String bagcode,String name){
		//System.out.println(bagcode+";"+name);
		//StringBuilder sb = new StringBuilder(" update Sealed s set s.seaStatus=?");
		//sb.append(" where s.dimensionalBarCode.id=? ");
		//super.updateByHql(sb.toString(),Comm.WAYBILL_COMPLETED);�Ȱ󶨳ɹ�֮���ٰѸô��Ӷ�ά��д������һ�ű�
		//StringBuilder sb1 = new StringBuilder("update DimensionalBarCode db set db.bag_code=? where db.id in(select aa.id from(select*from DimensionalBarCode dbc where dbc.regist_name=? order by dbc.registime desc limit 0, 3) as aa)");
		StringBuilder sb = new StringBuilder(" update dimensional_bar_code set bag_code=? where regist_name=? order by registime desc limit 53");
		sb.append(" ");
		try {
			super.updateByHqlBindBagCode(sb.toString(),bagcode,name);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	@Override
	public boolean queryCodeExists(String code) {
		//System.out.println("qweqweqeq:��������ʩ�����ж��Ƿ��Ѿ�״̬Ϊ1");
		StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode ");
		sb.append(" where freeze_content=? and freeze_status =? ");
		//��ά����֤��ѯ�ı�DimensionalBarCode�������ǣ����ݿ��е�ʩ���������ַ�����ʩ�����״̬Ҳ��û��ʹ�õ�״̬Ϊ0
		//������Ľ����1���������0�������ݷ��ص���true��Ҳ���ǳɹ������Ա��е�����ԵĶ�ά����ڲ���״̬Ϊ0��
		Integer count = super.queryRowCount(sb.toString(), code,Comm.TWO_CODE_NORMAL);		
		if (count > 0) {
			return true;
		}
		return false;
	}
	//ʩ��ɹ�֮����ȥɨʩ��ɹ��������ά�룬����ʾ���Ǵ˶�ά���Ѿ�ʩ��ɹ�����ѯʩ���
	public boolean cheackSeales(String code){
		StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode ");
		sb.append(" where freeze_content=? and freeze_status =? ");
		Integer count = super.queryRowCount(sb.toString(), code,Comm.TWO_CODE_CENTER);
		if (count > 0) {//����Ѿ��鵽��ʩ���ά����ڲ����Ѿ�״̬Ϊ1��ʹ�����򷵻�false
			return false;
		}
		return true;
	}
	//���ʩ�������
	public boolean checkSealesabandoned(String code){
		StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode ");
		sb.append(" where freeze_content=? and freeze_status =? ");
		Integer count = super.queryRowCount(sb.toString(), code,Comm.TWO_CODE_LOSS);
		if (count > 0) {//����Ѿ��鵽��ʩ���ά����ڲ����Ѿ�״̬Ϊ2�ڱ������򷵻�false
			return false;
		}
		return true;
	}
	@Override
	public boolean queryCodeExist(String code) {

			StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode ");
			sb.append(" where unfreeze_content=? and unfreeze_status =? ");
			
			Integer count = super.queryRowCount(sb.toString(), code,Comm.TWO_CODE_NORMAL);
			if (count > 0) {
				return true;
			}
		return false;
	}


	@Override
	public void updateStatusById(Integer id, Integer status) {
		StringBuilder sb = new StringBuilder(" update DimensionalBarCode set freeze_status=? , unfreeze_status=?");
		sb.append(" where id=?  ");
		super.updateByHql(sb.toString(), status,status , id);

	}
	
	public void updateUnstatusById(Integer id, Integer status) {
		StringBuilder sb = new StringBuilder(" update DimensionalBarCode set Unfreeze_status=? , freeze_status=? ");
		sb.append(" where id=?  ");
		super.updateByHql(sb.toString(), status,status, id);

	}

	@Override
	public Integer queryIdByCodeStatus(String code, Integer Status) {
		StringBuilder sb = new StringBuilder("select id from DimensionalBarCode ");
		sb.append(" where freeze_content=? and freeze_status =? ");
		return (Integer) super.queryUniqueObject(sb.toString(), code, Status);
	}
	@Override
	public Integer queryIdByCodeUnstatus(String code, Integer Status) {
		StringBuilder sb = new StringBuilder("select id from DimensionalBarCode ");
		sb.append(" where unfreeze_content=? and unfreeze_status =? ");
		return (Integer) super.queryUniqueObject(sb.toString(), code, Status);
	}

	/**
	 * ���ݴ������Ķ�ά�뷵�ض�ά���״̬ 0 1 2
	 */
	@Override
	public Integer queryCodeByStatus(String code) throws Exception {
		Integer str = null;
		String bs = code.substring(0, 1);
		if("0".equals(bs)){
			StringBuilder sb = new StringBuilder("select db.freeze_status from DimensionalBarCode db where db.freeze_content=?");
			str = (Integer) super.queryUniqueObject(sb.toString(), code);
			return str;
		} else if("1".equals(bs)){
			StringBuilder sb = new StringBuilder("select db.unfreeze_status from DimensionalBarCode db where db.unfreeze_content=?");
			str = (Integer) super.queryUniqueObject(sb.toString(), code);
			return str;
		}
		return str;
	}
	
	@Override
	public boolean ExitsCodeEqualsPhoto(String code) {
		String bs = code.substring(0, 1);
		if("0".equals(bs)){
			StringBuilder sb = new StringBuilder("select s.seaPhoto,s.seaImg from Sealed s where s.dimensionalBarCode.freeze_content=?");
			Integer str = super.queryRowCount(sb.toString(), code);
			if(str > 0){
				return true;
			}
		} else if("1".equals(bs)){
			StringBuilder sb = new StringBuilder("select s.seaPhoto,s.seaImg from Sealed s where s.dimensionalBarCode.unfreeze_content=?");
			Integer str = super.queryRowCount(sb.toString(), code);
			if(str > 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * ���ݶ�ά�����״̬
	 */
	@Override
	public void updateStatusByCode(Integer codeId,String seaImgs) {
		try{
			StringBuilder sb = new StringBuilder("update Sealed s  set s.seaImg=? where s.dimensionalBarCode.id=?");
		super.updateByHql(sb.toString(), seaImgs,codeId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * ��ⷽ
	 */
	@Override
	public void updateFreStatusByCodeId(Integer codeId, String freImgs) {
		try{
			StringBuilder sb = new StringBuilder("update Freeze f  set f.freImg=? where f.dimensionalBarCode.id=?");
			super.updateByHql(sb.toString(), freImgs,codeId);
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}

	/**
	 * ����ʩ���״̬
	 */
	@Override
	public void updateSeaStatustwo(Integer codeId) {
		StringBuilder sb = new StringBuilder(" update Sealed s set s.seaStatus=?");
		sb.append(" where s.dimensionalBarCode.id=? ");
		super.updateByHql(sb.toString(),Comm.WAYBILL_COMPLETED, codeId);
	}
	
	public void updateSealedByCodeIdAndCarId(Integer codeId, Integer carId,Integer wAYBILL_UNFINISHED,Integer comId) {
		StringBuilder sb=new StringBuilder("update Sealed sea seaStatus=? ");
		sb.append(" where dimensionalBarCode.id=? ");
		sb.append(" and car.carId=? ");
		sb.append(" and company.comId=? ");
		super.query(sb.toString(),Comm.WAYBILL_UNFINISHED,codeId,carId,wAYBILL_UNFINISHED,comId);
	}

	@Override
	public void saveCode(DimensionalBarCode code) {
		super.saveOrUpdate(code);
	}
	@Override
	public void saveCodeBag(Dbc_BagCodeBind bacb){
		super.saveOrUpdate(bacb);
	}
	@Override
	public Integer queryCodeByCode(String code) {
		StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode cod where cod.freeze_content = ?");
		return super.queryRowCount(sb.toString(), code);
	}
	public Integer queryCodeByUncode(String code) {
		StringBuilder sb = new StringBuilder("select count(id) from DimensionalBarCode cod where cod.unfreeze_content = ?");
		return super.queryRowCount(sb.toString(), code);
	}
	
}
