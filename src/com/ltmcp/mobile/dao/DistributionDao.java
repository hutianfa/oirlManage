package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.Area;
import com.ltmcp.entity.Area_manager_inventory;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;

public interface DistributionDao {

	/***
	 * �������Ӷ�ά��ȥ�ж��Ƿ��ظ������ݿ�ı���Position_inventory��secondorder
	 * @param code ���Ӷ�ά��
	 * @return true����false
	 * 
	 */
    public boolean checkBoxCodeBySecondOrderAndPosition_inventory(String code);
	/***
	 * ���ݴ��Ӷ�ά��ȥ�ж��Ƿ��ظ�ɨ������Ѿ��ַ�
	 * @param code ���Ӷ�ά��
	 * @return true����false
	 */
	public boolean checkBagCodeBySecondOrderAndPosition_inventory(String code);
	/***
	 * ����վ��ȥ��ѯposition_inventory����վ�����������ʱ�����ƣ�
	 * @param zhanDianName վ������
	 * @return ��վ�������Ѿ��ַ�����������ʹ��׷�ݿ�ʼ����;ûʹ�ã�ֹͣʹ��׷�ݣ���������ڿ��Ƿ�Χ��
	 */
	public Integer findCountByZhandian_name(String zhanDianName);
	/***
	 * ����Ƭ������ȥ��ѯsecoudorder�����������ӣ���������ʱû��ʱ�䣬ͳ��ȫ��
	 * @param name Ƭ������
	 * @return �����ֹ�˾������Ƭ��������
	 */
	public Integer findCountByArea_name(String name);
	
	/***
	 *  ���ݷַ�������**����
	 * @param name �ַ���name
	 * @return list(shoufa_person)
	 */
	public List<shoufa_person> findPianquIdByName(String name);
	
	/***
	 * �ַ���¼��֤
	 * @param username ��¼�˺�
	 * @param password ��¼����
	 * @return
	 */
	public boolean checkDisLogin(String username, String password);
	
	/***
	 * �����˺ź���������˺ŵĲ�ѯ���ַ��˺͸÷ַ��˵ĵص�
	 * @param username ��¼�˺�
	 * @param password ��¼����
	 * @return ��ʱ��Ʒ���һ���˺ŵ�list��������ʵ�ʷ���һ�����󼴿ɣ�
	 */
	public List<shoufa_person> serachPersonAddressorName(String username, String password);
	
	/***
	 * ����ֵ�Ǹ������id���������Ƭ��
	 * @param com_id �ַ��˱�fenfa_person���е�com_id)
	 * @return ����ֵ��Area��list�б�
	 */
	public List<Area> serachAreaAddressor(int com_id);
	
	/***
	 * ����Position�е�Ƭ��id������վ��
	 * @param areaid Ƭ��id
	 * @return Position��list�б�
	 */
	public List<Position> serachPostionAddressor(int areaid);
	
	/***
	 * �����Ӷ�ά��
	 * @param bagCode ���Ӷ�ά��
	 * @return ����true��������false��״̬ҲҪ�ж�����
	 */
	public boolean checkBagCode(String bagCode);
	
	/***
	 * ������Ӷ�ά��
	 * @param boxCode
	 * @return ����true��������false��״̬ҲҪ�ж�����
	 */
	public boolean checkBoxCode(String boxCode);
	
	/**
	 * ��������code
	 * @param so ���������������ֹ�˾�����淢��
	 */
	public void saveSecond_order(Second_order so);
	
	/***
	 * ����Ƭ������id�ҵ�Ƭ�������ַ
	 * @param pianquId Ƭ������id
	 * @return Ƭ�������ַ
	 */
	public List<shoufa_person> findAddressByPianquId(int pianquId);
	
	/***
	 * ����վ��id�ҵ�վ������
	 * @param zhandianId վ��id
	 * @return �ҵ���վ������
	 */
	public List<Position> findAddrressByZhandianId(int zhandianId);
	
	/***
	 * ��ami���浽�����ֹ�˾��Area_manager_inventory
	 * @param ami ����
	 */
	public void saveArea_manager_inventory(Area_manager_inventory ami);
	
	/***
	 * ��pi���浽վ��position_inventory
	 * @param pi վ����Ϣ����pi
	 */
	public void savePosition_inventory(position_inventory pi);
	
	/***
	 * ����code��ѯ���÷�ǩ�����Ĵ���
	 * @param code ��ǩ����
	 */
	public List<DimensionalBarCode> findBagCodeByCode(String code);
	
	/***
	 * ����ǩ������Ĵ������ҵ����Ӷ�ά��
	 * @param findbagCode ��ǩ�����Ĵ��Ӷ�ά��
	 * @return list
	 */
	public List<Dbc_BagCodeBind> findBoxCodeByFindbagCode(String findbagCode);
	
	/***
	 * ���ݷ�ǩ�����жϸö�ά���Ƿ�ϵͳ����
	 * @param code ��������Ƿ��Ǵ���
	 * @return false����true
	 */
	public boolean checkCodeNeiMa(String code);
	
	/***
	 * ���ݷ�ǩ�����жϸö�ά���Ƿ�ϵͳ����
	 * @param code ��������Ƿ��Ǵ���
	 * @return false����true
	 */
	public boolean checkCodeWaiMa(String code);
	/***
	 * ���ݴ��Ӷ�ά����ȥ��վ����������
	 * @param findbagCode
	 * @return һ������position_inventory
	 */
	public List<position_inventory> findPositionBybagCode(String findbagCode);
	
	/***
	 * �������Ӷ�ά���ٴ�ȥposition_inventory��������Ӷ�ά���Ƿ���ڣ�ǰ���ǣ�վ������Ҳ������Ӷ�ά�룬�ϼ��������䷢����վ��
	 * @param findboxCode ���Ӷ�ά��
	 * @return һ������position_inventory
	 */
	public List<position_inventory> findPositionByboxCode(String findboxCode);
	
	/***
	 * ���ݴ��Ӷ�ά��ȥSecond_order�ҵ�վ��
	 * @param findbagCode ���Ӷ�ά��
	 * @return һ������Second_order
	 */
	public List<Second_order> findSecondre_sh_addressInStationBybagCode(String findbagCode);
	
	/***
	 * �������Ӷ�ά��ȥSecond_order�ҵ�վ��
	 * @param findboxCode ���Ӷ�ά��
	 * @return һ������ Second_order
	 */
	public List<Second_order> findSecondre_sh_addressInStationByboxCode(String findboxCode);
	
	/***
	 * ����code��ѯ���÷�ǩ�����Ĵ���
	 * @param code ��ǩ����
	 */
	public List<DimensionalBarCode> findBagCodeByWaimaCode(String code);
	
	/***
	 * ��ѯArea����id����Ӧ��comid
	 * @param pianquId Ƭ��id
	 * @return Area��һ������
	 */
	public List<Area> findComIdByPianquId(int pianquId);
	
	/***
	 * ����shoufa_person���е�comid��ѯshoufa_person���������ж����ֹ�˾��ַ��
	 * @param comId �����ֹ�˾id
	 * @return shoufa_person����
	 */
	public List<shoufa_person> findcompanyAddressBycomId(int comId);
	
	/***
	 * ������Ա��id�ҵ�ʩ����Ϣ��ǰ����������Ա���statusΪ1�������У������� 2����ǩ���ϣ���
	 * @param dimensionalBarCode_id ��Ա��id
	 * @return һ���������
	 */
	public List<Sealed> findSealedInformationBydimensionalBarCode_id(int dimensionalBarCode_id);
	
	/***
	 * ����ʩ����id��ѯ�������Ϣ����
	 * @param sealedId ʩ����id
	 * @return ����һ���������
	 */
	public List<Freeze> findFreezeBysealedId(int sealedId);
	
	/***
	 * ���ݵ�ַ���ض���Position
	 * @param address ������˾ֱ�ӷ���վ������Ϳ�ĵ�ַ
	 * @return Position����
	 */
	public List<Position> findPianQuIdByReceive_sh_address(String address);
	
	/***
	 * �ҵ�Ƭ��id
	 * @param address shoufa_person�еĵ�ַ
	 * @return һ������
	 */
	public List<shoufa_person> findPianQuIdByshoufa_personAddress(String address);
	
}
