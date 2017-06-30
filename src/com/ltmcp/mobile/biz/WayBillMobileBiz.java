package com.ltmcp.mobile.biz;

import java.util.List;
import com.ltmcp.entity.Empower;

public interface WayBillMobileBiz {

	/**
	 * ���ʩ����Ϣ
	 * 
	 * @param name  �˺�����
	 * @param password ����
	 * @param code ��ά��
	 * @param plateNumber ���ƺ���
	 * @param filePath    
	 * @param longitude
	 * @param latitude
	 * @return ����0:��ӳɹ� ����:-1 ��ά��δ��ϵͳ��ע�� -2:����δ��ϵͳ��ע�� -3:����ԭ�����ʧ��
	 */
	public Integer addLockInfo(String name, String password, String code,
			String plateNumber, String filePath,String youpin,String youfilePath, String latitude,
			String longitude, Integer positionId, String path,Integer tag,String wayNumber,String time)
			throws Exception;

	/**
	 * ��ӽ����Ϣ
	 * 
	 * @param name
	 *            �˺�����
	 * @param password
	 *            ����
	 * @param code
	 *            ��ά��
	 * @param plateNumber
	 *            ����
	 * @param exceptionList
	 *            �쳣�б�
	 * @param filePath
	 * @param longitude
	 * @param latitude
	 * @return ����0:���ɹ� -1:��ά��δ��ϵͳ��ע�� -2:����δ��ϵͳ��ע�� -3:�쳣��Ӵ��� -4:�˵��Ҳ��� -5:�����쳣
	 * @throws Exception
	 */
	
	public Integer addUnlockInfo(String name, String password, String code,
			String plateNumber, Integer exceptionList, String filePath,
			String latitude, String longitude, Integer positionId,String powerCode,Integer powerTips,String wayNumber,String time)
			throws Exception;

	/**
	 * ���ض�ά��״̬
	 * 
	 * @param codeContent
	 * @return
	 * @throws Exception
	 */
	public Integer returnCodeStatus(String codeContent) throws Exception;
	
	
	/**
	 * ��ѯ��Ȩ��Ϣ
	 */
	public Empower findPowerCode(String powerCode);
}
