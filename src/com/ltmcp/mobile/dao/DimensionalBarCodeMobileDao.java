package com.ltmcp.mobile.dao;

import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;

public interface DimensionalBarCodeMobileDao {
	
	/**
	 * ����ά���Ƿ����
	 * @param code
	 * @return
	 */
	public boolean queryCodeExists(String code);//ʩ���ά��
	
	public boolean queryCodeExist(String code);//����ά��
	
	public boolean cheackSeales(String code);//����ظ���ά��
	public boolean checkSealesabandoned(String code);//���ʩ�������
	
	
	/**
	 * ���ݶ�ά���ѯID
	 * @param code
	 * @return
	 */
	public Integer queryIdByCodeStatus(String code,Integer Status);//����ʩ���ά���ѯID
	public Integer queryIdByCodeUnstatus(String code,Integer Status);//���ݽ���ά���ѯID
	
	public void updateStatusById(Integer id,Integer status);//����id����ʩ��״̬
	public void updateUnstatusById(Integer id,Integer status);//����id���Ľ��״̬
	
	/**
	 * ���ݶ�ά���ȡ��ά���״̬
	 * @param Status
	 * @return
	 */
	public Integer queryCodeByStatus(String code) throws Exception;//ʩ���ά���״̬
	
	public boolean ExitsCodeEqualsPhoto(String code);//���ݶ�ά���ȡ�ϴ�ͼƬ��Ϣ
	
	/**
	 * ���ݶ�ά��Id����״̬    ʩ�ⷽ
	 * @param code
	 */
	public void updateStatusByCode(Integer codeId,String seaImgs);
	
	public void updateFreStatusByCodeId(Integer codeId,String freImgs);
	
	
	/**
	 * ����ʩ��״̬
	 * @param codeId
	 */
	
	public void updateSeaStatustwo(Integer codeId);
	public void updateSealedByCodeIdAndCarId(Integer codeId, Integer carId,Integer wAYBILL_UNFINISHED,Integer comId) ;
	/**
	 * ע���ά��
	 */
	public void saveCode(DimensionalBarCode code);
	/**
	 * �����Ӷ�ά���Ƿ��Ѿ���ʹ��
	 */
	public boolean checkBagCodeInDBC(String code);
	/**
	 * ���Ӷ�ά������
	 */
	public void saveCodeBag(Dbc_BagCodeBind bacb);
	/**
	 * ���Ӱ󶨶�ά��
	 */
	public void saveBagCode(String bagcode,String name);
	/**
	 * ���ݶ�ά����Ҷ�ά���Ƿ�Ӧ��ע�ᣨһ������������в�����
	 */
	public Integer queryCodeByCode(String code);//���ݶ�ά����ά��
	public Integer queryCodeByUncode(String code);//���ݶ�ά����ά��
	/**
	 * ���Ӱ󶨴���
	 * @param caseCode
	 * @param name
	 */
	public void addCaseCode(String caseCode, String name);
}
