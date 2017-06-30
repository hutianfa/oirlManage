package com.ltmcp.comm;

public class  Comm {
	
	public static int SYSTEM_PERSONSIZE=13;
	
	
	public static int SYSTEM_PAGESIZE=10;//20161227
	
	/**
	 * 高级管理员
	 */
	public static int SENIOR_MANAGER=1;
	
	/**
	 * 普通管理员
	 */
	public static int GENERAL_MANAGER=2;
	
	/**
	 * 系统管理员
	 */
	public static int SYSTEM_MANAGER=3;
	
	public static int ADMIN_NORMAL=0;
	
	public static int ADMIN_LOSS=1;
	
	/**
	 * 权限显示
	 */
	public static int POWER_SHOW=0;
	
	/**
	 * 权限隐藏
	 */
	public static int POWER_HIDE=1;

	/**
	 * 当前用户web端
	 */
	public static String CURRENT_ADMIN="CURRENT_ADMIN";
	/**
	 * 当前用户app端
	 */
	public static String CURRENT_SHOUFA_PERSON="CURRENT_SHOUFA_PERSON";
	/**
	 * 当前web用户ID
	 */
	public static String CURRENT_ADMIN_ID="CURRENT_ADMIN_ID";
	/**
	 * 当前app用户ID
	 */
	public static String CURRENT_SHOUFA_PERSON_ID="CURRENT_SHOUFA_PERSON_ID";
	
	/**
	 * 当前用户菜单
	 */
	public static String CURRENT_ADMIN_MENU_NAME="ADMIN_MENU";
	
	/**
	 * 当前用户所有权限
	 */
	public static String CURRENT_ADMIN_ALL_POWER="ADMIN_POWER";
	
	/**
	 * 解封地类型
	 */
	public static Integer FREEZE_POSITION=1;
	
	/**
	 * 加封地类型
	 */
	public static Integer SEALED_POSITION=2;
	
	/**
	 * 员工正常
	 */
	public static Integer PERSON_NORMAL=0;
	
	/**
	 * 员工流失
	 */
	public static Integer PERSON_LOSS=1;
	
	/**
	 * 位置信息（使用中）
	 */
	public static Integer POSITION_NORMAL=0;
	
	/**
	 * 位置信息（未使用）
	 */
	public static Integer POSITION_LOSS=1;
	
	/**
	 * 字典中异常的类型
	 */
	public static Integer BASDICT_EXCEPTION=2;
	
	/**
	 * 字典中位置的类型
	 */
	public static Integer BASDICT_POSITION=1;
	
	/**
	 * 字典中油品的类型
	 */
	public static Integer BASDICT_OILPIN=4;
	
	/**
	 * 固定封签操作异常
	 */
	public static Integer BASDICT_FIX=5;
	
	/**
	 * 车辆来源类型
	 */
	public static Integer BASDICT_TAG=6;
	
	/**
	 * 授权字典
	 */
	public static Integer BASDICT_EM=7;
	
	/**
	 * 状态为正常的汽车(使用中)
	 */
	public static Integer CAR_NORMAL=0;
	
	/**
	 * 状态为删除的汽车（未使用）
	 */
	public static Integer CAR_LOSS=1;
	
	/**
	 * 已经处理的异常标示(已处理)
	 */
	public static Integer EXCEPTION_TREATED=0;
	
	/**
	 * 未处理的异常标示(未处理)
	 */
	public static Integer EXCEPTION_UNTREATED=1;
	
	/**
	 * 已经完成的运单的状态(已完成)
	 */
	public static Integer WAYBILL_COMPLETED=0;
	
	/**
	 * 未完成的运单的状态(未完成)
	 */
	public static Integer WAYBILL_UNFINISHED=1;
	
	/**
	 * 运单出现异常的运单(出现异常)
	 */
	public static Integer WAYBILL_EXCEPTION=2;
	
	/**
	 * 施封人员
	 */
	public static Integer PERSON_LOCK=6;
	
	/**
	 * 解封人员
	 */
	public static Integer PERSON_UNLOCK=7;
	
	/**
	 * 施解封人员
	 */
	public static Integer PERSON_UNLOCK_LOCK=67;
	/**
	 * 固定封签施解封人员
	 */
	public static Integer PERSON_FIX_UNLOCK_LOCK=21;
	/**
	 * 管理员操作
	 */
	public static Integer PERSON_ADMIN=22;
	
	/**
	 * 未使用的二维码
	 */
	public static Integer TWO_CODE_NORMAL=0;
	
	/**
	 * 正在使用中的二维码
	 */
	public static Integer TWO_CODE_CENTER=1;
	
	/**
	 * 已经使用过的二维码
	 */
	public static Integer TWO_CODE_LOSS=2;
	
	
	
	/**
	 * 未使用的FIX二维码
	 */
	public static Integer TWO_CODE_FIX_NORMAL=0;
	
	/**
	 * 正在使用中的FIX二维码
	 */
	public static Integer TWO_CODE_FIX_CENTER=11;
	
	/**
	 * 已经使用过的FIX二维码
	 */
	public static Integer TWO_CODE_FIX_LOSS=12;
	
	
	/**
	 * 已经完成的运单的状态(已完成)
	 */
	public static Integer WAYBILL_FIX_COMPLETED=10;
	
	/**
	 * 未完成的运单的状态(未完成)
	 */
	public static Integer WAYBILL_FIX_UNFINISHED=11;
	
	/**
	 * 运单出现异常的运单(出现异常)
	 */
	public static Integer WAYBILL_FIX_EXCEPTION=12;
	
}
