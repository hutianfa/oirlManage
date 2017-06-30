package com.ltmcp.mobile.biz;

import java.util.List;
import java.util.Map;

import com.ltmcp.entity.Car;
import com.ltmcp.entity.Sealed;

/**
 * ��׿���ֻ��߼���
 * @author Administrator
 *
 */
public interface CarMobileBiz {
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<Car> searchCars(String name,String password);
	/**
	 * ��ȡ����
	 * @param name
	 * @param password
	 * @param QRCode
	 * @return
	 */
	
	public List<Sealed> getCarByQRCode(String name, String password, String QRCode);
	//��������Ƿ����ע���470����������Ѿ�����469
	public Integer checkUnfreezeCode(String QRCode);
	//��ȡ����Ϊʩ��Ŀ�
	public List<Map> getCarSeaFreNum(List<Map> carFlapper,String str);
	
}
