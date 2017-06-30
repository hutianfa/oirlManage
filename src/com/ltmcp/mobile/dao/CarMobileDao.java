package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

public interface CarMobileDao {
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<Car> findCars(Integer comId);
	
	public boolean carIsExistByComId(String carFlaper,Integer comId);
	
	public Integer queryCarIdByFlapper(String flapper);
	
	public Car queryCarById(Integer id);

	/**
	 * ���ݶ�ά���ȡ������Ϣ
	 * @param qRCode
	 * @return
	 */
	public List<Sealed> queryCarByQRCode(String qRCode);
	
	
	//��ȡ�������ѽ��ڣ�����0�㵽��ǰʱ�䣩
	public Integer queryCarFredNum(Integer flapper);
	//��������Ƿ����ע���470����������Ѿ�����469
	public Integer checkUnfreezeCode(String QRCode); 
}
