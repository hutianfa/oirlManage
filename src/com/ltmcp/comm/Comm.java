package com.ltmcp.comm;

public class  Comm {
	
	public static int SYSTEM_PERSONSIZE=13;
	
	
	public static int SYSTEM_PAGESIZE=10;//20161227
	
	/**
	 * �߼�����Ա
	 */
	public static int SENIOR_MANAGER=1;
	
	/**
	 * ��ͨ����Ա
	 */
	public static int GENERAL_MANAGER=2;
	
	/**
	 * ϵͳ����Ա
	 */
	public static int SYSTEM_MANAGER=3;
	
	public static int ADMIN_NORMAL=0;
	
	public static int ADMIN_LOSS=1;
	
	/**
	 * Ȩ����ʾ
	 */
	public static int POWER_SHOW=0;
	
	/**
	 * Ȩ������
	 */
	public static int POWER_HIDE=1;

	/**
	 * ��ǰ�û�web��
	 */
	public static String CURRENT_ADMIN="CURRENT_ADMIN";
	/**
	 * ��ǰ�û�app��
	 */
	public static String CURRENT_SHOUFA_PERSON="CURRENT_SHOUFA_PERSON";
	/**
	 * ��ǰweb�û�ID
	 */
	public static String CURRENT_ADMIN_ID="CURRENT_ADMIN_ID";
	/**
	 * ��ǰapp�û�ID
	 */
	public static String CURRENT_SHOUFA_PERSON_ID="CURRENT_SHOUFA_PERSON_ID";
	
	/**
	 * ��ǰ�û��˵�
	 */
	public static String CURRENT_ADMIN_MENU_NAME="ADMIN_MENU";
	
	/**
	 * ��ǰ�û�����Ȩ��
	 */
	public static String CURRENT_ADMIN_ALL_POWER="ADMIN_POWER";
	
	/**
	 * ��������
	 */
	public static Integer FREEZE_POSITION=1;
	
	/**
	 * �ӷ������
	 */
	public static Integer SEALED_POSITION=2;
	
	/**
	 * Ա������
	 */
	public static Integer PERSON_NORMAL=0;
	
	/**
	 * Ա����ʧ
	 */
	public static Integer PERSON_LOSS=1;
	
	/**
	 * λ����Ϣ��ʹ���У�
	 */
	public static Integer POSITION_NORMAL=0;
	
	/**
	 * λ����Ϣ��δʹ�ã�
	 */
	public static Integer POSITION_LOSS=1;
	
	/**
	 * �ֵ����쳣������
	 */
	public static Integer BASDICT_EXCEPTION=2;
	
	/**
	 * �ֵ���λ�õ�����
	 */
	public static Integer BASDICT_POSITION=1;
	
	/**
	 * �ֵ�����Ʒ������
	 */
	public static Integer BASDICT_OILPIN=4;
	
	/**
	 * �̶���ǩ�����쳣
	 */
	public static Integer BASDICT_FIX=5;
	
	/**
	 * ������Դ����
	 */
	public static Integer BASDICT_TAG=6;
	
	/**
	 * ��Ȩ�ֵ�
	 */
	public static Integer BASDICT_EM=7;
	
	/**
	 * ״̬Ϊ����������(ʹ����)
	 */
	public static Integer CAR_NORMAL=0;
	
	/**
	 * ״̬Ϊɾ����������δʹ�ã�
	 */
	public static Integer CAR_LOSS=1;
	
	/**
	 * �Ѿ�������쳣��ʾ(�Ѵ���)
	 */
	public static Integer EXCEPTION_TREATED=0;
	
	/**
	 * δ������쳣��ʾ(δ����)
	 */
	public static Integer EXCEPTION_UNTREATED=1;
	
	/**
	 * �Ѿ���ɵ��˵���״̬(�����)
	 */
	public static Integer WAYBILL_COMPLETED=0;
	
	/**
	 * δ��ɵ��˵���״̬(δ���)
	 */
	public static Integer WAYBILL_UNFINISHED=1;
	
	/**
	 * �˵������쳣���˵�(�����쳣)
	 */
	public static Integer WAYBILL_EXCEPTION=2;
	
	/**
	 * ʩ����Ա
	 */
	public static Integer PERSON_LOCK=6;
	
	/**
	 * �����Ա
	 */
	public static Integer PERSON_UNLOCK=7;
	
	/**
	 * ʩ�����Ա
	 */
	public static Integer PERSON_UNLOCK_LOCK=67;
	/**
	 * �̶���ǩʩ�����Ա
	 */
	public static Integer PERSON_FIX_UNLOCK_LOCK=21;
	/**
	 * ����Ա����
	 */
	public static Integer PERSON_ADMIN=22;
	
	/**
	 * δʹ�õĶ�ά��
	 */
	public static Integer TWO_CODE_NORMAL=0;
	
	/**
	 * ����ʹ���еĶ�ά��
	 */
	public static Integer TWO_CODE_CENTER=1;
	
	/**
	 * �Ѿ�ʹ�ù��Ķ�ά��
	 */
	public static Integer TWO_CODE_LOSS=2;
	
	
	
	/**
	 * δʹ�õ�FIX��ά��
	 */
	public static Integer TWO_CODE_FIX_NORMAL=0;
	
	/**
	 * ����ʹ���е�FIX��ά��
	 */
	public static Integer TWO_CODE_FIX_CENTER=11;
	
	/**
	 * �Ѿ�ʹ�ù���FIX��ά��
	 */
	public static Integer TWO_CODE_FIX_LOSS=12;
	
	
	/**
	 * �Ѿ���ɵ��˵���״̬(�����)
	 */
	public static Integer WAYBILL_FIX_COMPLETED=10;
	
	/**
	 * δ��ɵ��˵���״̬(δ���)
	 */
	public static Integer WAYBILL_FIX_UNFINISHED=11;
	
	/**
	 * �˵������쳣���˵�(�����쳣)
	 */
	public static Integer WAYBILL_FIX_EXCEPTION=12;
	
}
