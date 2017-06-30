package com.ltmcp.mobile.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.util.comparator.InvertibleComparator;

import com.ltmcp.action.BaseAction;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Inventor_BoxCode;
import com.ltmcp.mobile.biz.impl.Dbc_BagCodeBindBizImpl;
import com.ltmcp.mobile.biz.impl.SaveCodeBizImpl;
import com.ltmcp.mobile.biz.impl.caseCodeBindBizImpl;

import com.ltmcp.mobile.dao.impl.Dbc_BagCodeBindDaoImpl;
import com.ltmcp.mobile.dao.impl.DimensionalBarCodeMobileDaoImpl;
import com.ltmcp.mobile.dao.impl.caseCodeBindBizDaoImpl;


public class SaveCodeAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private SaveCodeBizImpl saveCode;
	private DimensionalBarCodeMobileDaoImpl dao;
	private Dbc_BagCodeBindBizImpl dbcbcheck;
	private Dbc_BagCodeBindDaoImpl dbcheckdao;
	private caseCodeBindBizImpl caseCodeBindBiz;
	private caseCodeBindBizDaoImpl caseCodeBindDao;

	

	private String code1;
	private String code2;
	private String name;
	private String bagCode1;//袋子二维码获取
	private String bagCode2;//袋子二维码获取
	private String boxCode1;//箱子二维码获取
	
	private String cd;
	private String freeze;
	private String unfreeze;
	private String bagCode;
	private String bagCodde2;
	
	
	//箱子绑定袋子
	public void bindCaseInbagCode(){
		 Inventor_BoxCode ibo = new Inventor_BoxCode();
		 System.out.println("打印箱子二维码信息：：boxCode1"+boxCode1+";name"+name);
		 String boxCodee1 = boxCode1.substring(0, 1);
		 Inventor_BoxCode ib = new Inventor_BoxCode();
		 if("x".equals(boxCodee1)){
			 ib.setBox_code(boxCode1);
			 ib.setStatus(0);
			 boolean sta1 = caseCodeBindBiz.checkCaseCodeExist(boxCode1);//inventor_boxcode检测的是这一张表
			 boolean sta2 = dbcbcheck.checkCasecodeExist(boxCode1);
			 //一但增加倒序查找状态为1的情况：那么将会增加sql后台速度，以及没有状态为1（比如说20个袋子，反查询之后立即出现一个或者多个袋子的状态不为1）
		
			 /*检测dbc_bagcodebind表中status是否为1,为1才能绑定箱子，前端设置之能扫描20个袋子之后才能必须扫描箱子二维码,
			  * 这样之后并且扫描箱子的二维码也成功,若真出现箱子二维码的问题：只有2种情况:
			  * 一种是不是x开头的，还有一种是：在库存表中的找到这个箱子的二维码,返回值中若-2001或者-205，会在前端有这样一个操作
			  * ：重新扫描箱子二维码，或者更换箱子上的二维码（），之后扫描成功在，则会提示进入扫描袋子二维码的状态，并且考虑到不能用2台以及以上的
			  * 手持机的登录相同的账号进行操作，账号的登录唯一性确认的设计估计还没设计完成（最麻烦的情况估计会在这里发生，到时候时间和账号都会相同）。
			  * 
			  * */
			 if(sta1 || sta2){
				 super.getPringWriter().print(-2001);//箱子二维码已经被关联
     			 return;
			 }else{
				 
				 
				 //写入到inventor_boxcode表中（本质是厂家的库存表）。inventor库存的意思
				 
//				 Timestamp ts=new Timestamp(System.currentTimeMillis());
//				 String tsStr = "";   
//			     DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//Timestamp转string   
				 //关联时间是否需要（Inventor_BoxCode表中，估计发货时间可能需要，但是也会在产家发货的的订单表中进行时间的如何确认？？、）
			     ibo.setStatus(0);
			     ibo.setBox_code(boxCode1);
			     saveCode.addCaseCode(boxCode1, name);//箱子关联袋子上的二维码（操作的表依旧是：dbc_bagcodebind）
				 caseCodeBindBiz.saveCaseCode(ibo);//插入箱子二维码到inventor_boxcode表
				 super.getPringWriter().print(-2066);//箱子关联袋子成功
     			 return;
			 }
		 }else{
			 super.getPringWriter().print(-205);//关联失败（不是x开头的二维码）
			 return;
		 }
	}
	//改变袋子表中状态来准备关联箱子二维码
	public void changeBagCodeStatus(){
		
		Dbc_BagCodeBind dbcb = new Dbc_BagCodeBind();
		String bagCodee2 = bagCode2.substring(0, 1);
		if("b".equals(bagCodee2)){
			bagCodde2 = bagCode2;
			Timestamp ts=new Timestamp(System.currentTimeMillis());//最关键的3步骤：改变时间和状态，这个2个（时间和状态）加上name字段值来绑定箱子二维码
//			String tsStr = "";   
//	        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//Timestamp转string   
	        try {
				//System.out.println("箱子二维码:"+bagCode2+",姓名："+name+",时间："+ts);
	        	//tsStr = sdf.format(ts);
	        	boolean sta1 = dbcbcheck.checkBagcodeExist(bagCode2);//在袋子注册表中查看是否已经存在袋子二维码.sta1为true表示已经存在，false表示不存在
	        	boolean sta2 = dbcbcheck.checkBagcodeStaAndBagCode(bagCode2);//已经被箱子关联成功的
	        	boolean sta3 = dbcbcheck.multipleScan(bagCode2);//没有关联箱子二维码情况下，重复扫描袋子二维码
	        	
	        	//System.out.println(sta2+": sta2已经被箱子二维码成功关联的状态");
	        	if(sta1){
	        		if(sta2){
	        			super.getPringWriter().print(-2067);//sta2已经被箱子二维码成功关联的状态
	        			return;
	        		}else if(sta3){
	        			super.getPringWriter().print(-1999);//sta3判断重复扫描袋子二维码，前提是该袋子二维码没有被箱子关联
	        			return;
	        			}
	        		  else{
	        			  dbcheckdao.changeBagCodeStatusInRegister(bagCode2, name, ts);//袋子二维码存在并且修改状态为1
	        			  super.getPringWriter().print(-2066);
	        		}
				}else{
					super.getPringWriter().print(-206);//该袋子二维码没有关联袋中封签，请重新扫码其他袋子
					//System.out.println("该袋子二维码没有关联袋中封签");
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
		}else{
			super.getPringWriter().print(-205);//不是b开头的袋子二维码
			return;
		}
	}
	
	//扫描封签外码的方法
	public void ScanningWaima()
	{
		System.out.println("打印外码:"+code2);
	}
	/**
	 * 袋子绑定袋子中已经注册成功的二维码
	 */
	 public void boxCodeBind(){
		 System.out.println("bagCode1"+bagCode1+";name"+name+"进入袋子绑定二维码封签");
		 Dbc_BagCodeBind dbcb = new Dbc_BagCodeBind();
		 String bagCodee1 = bagCode1.substring(0, 1);
		 
		 if("b".equals(bagCodee1)){
			 bagCode=bagCode1;
			 
			try {
				boolean sta = saveCode.checkBagCodeInDBC(bagCode1);//配对中判断是否使用过该袋子二维码
				boolean sta1 = dbcbcheck.checkBagcodeExist(bagCode1);//在袋子注册表中查看是否已经存在袋子二维码
				System.out.println(sta+"sta状态"+";sta1的状态"+sta1);
				if(sta || sta1)//只要其中一个判断为true，则提示该袋子二维码已经在使用
				{
					System.out.println("该袋子二维码已经被注册使用，请更换！！！");
					 super.getPringWriter().print(-208);//关联失败（袋子二维码已经被注册，请更换袋子重新扫描）
					return;
				}else{
						 dbcb.setBag_code(bagCode);
						 dbcb.setStatus(0);
						 
						 try {
							 saveCode.addBagCode(bagCode1,name);//进行袋子二维码数据的保存关联操作
							 saveCode.saveCodeBag(dbcb);//把袋子二维码注册到表中
							 
							
							 super.getPringWriter().print(-2066);//保存并关联ok
						} catch (Exception e) {
							super.getPringWriter().print(-206);//关联异常
						}
				     }
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		 }else{
				super.getPringWriter().print(-205);//关联失败（不是b开头的二维码）
				return;
			}
		 
	 }
	/**
	 * 注册二维码
	 */
	public void registcode(){
		/**
		 * 将注册的对象保存，且不分先后
		 * @return -205 类型一样
		 * @return -206 注册失败
		 */
		DimensionalBarCode code = new DimensionalBarCode();
		
		String cod1 = code1.substring(0, 1);
		String cod2 = code2.substring(0, 1);
		String namee = name;
		System.out.println(name+"打印name");
		if("0".equals(cod1) && "1".equals(cod2)){
			freeze = code1;
			unfreeze = code2;
		} else if("1".equals(cod1) && "0".equals(cod2)){//并且
			freeze = code2;
			unfreeze = code1;
		} else{
			super.getPringWriter().print(-205);
			
			return;
		}
		code.setFreeze_content(freeze);
		code.setFreeze_status(0);
		code.setUnfreeze_content(unfreeze);
		code.setUnfreeze_status(0);
		code.setRegistime(new Timestamp(System.currentTimeMillis()));
		code.setRegist_name(namee);
		
		try {
			saveCode.addCode(code);//保存操作
			super.getPringWriter().print(-2066);//保存ok
		} catch (Exception e) {
			
			super.getPringWriter().print(-206);//保存异常
		}
	}
	/**
	 * 检查二维码是否已经被注册，
	 * @return 非1 没有注册
	 */
	public void queryCode(){
		
		String cod = cd.substring(0, 1);
		if("0".equals(cod)){
			super.getPringWriter().print(dao.queryCodeByCode(cd));
		} else if("1".equals(cod)){
			super.getPringWriter().print(dao.queryCodeByUncode(cd));
		}
	}
	
	
	public DimensionalBarCodeMobileDaoImpl getDao() {
		return dao;
	}
	public void setDao(DimensionalBarCodeMobileDaoImpl dao) {
		this.dao = dao;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public SaveCodeBizImpl getSaveCode() {
		return saveCode;
	}
	public void setSaveCode(SaveCodeBizImpl saveCode) {
		this.saveCode = saveCode;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBagCode1() {
		return bagCode1;
	}
	public void setBagCode1(String bagCode1) {
		this.bagCode1 = bagCode1;
	}
	public Dbc_BagCodeBindBizImpl getDbcbcheck() {
		return dbcbcheck;
	}
	public void setDbcbcheck(Dbc_BagCodeBindBizImpl dbcbcheck) {
		this.dbcbcheck = dbcbcheck;
	}
	public Dbc_BagCodeBindDaoImpl getDbcheckdao() {
		return dbcheckdao;
	}
	public void setDbcheckdao(Dbc_BagCodeBindDaoImpl dbcheckdao) {
		this.dbcheckdao = dbcheckdao;
	}

	public String getBoxCode1() {
		return boxCode1;
	}
	public void setBoxCode1(String boxCode1) {
		this.boxCode1 = boxCode1;
	}
	public String getBagCode2() {
		return bagCode2;
	}
	public void setBagCode2(String bagCode2) {
		this.bagCode2 = bagCode2;
	}
	
	public caseCodeBindBizImpl getCaseCodeBindBiz() {
		return caseCodeBindBiz;
	}
	public void setCaseCodeBindBiz(caseCodeBindBizImpl caseCodeBindBiz) {
		this.caseCodeBindBiz = caseCodeBindBiz;
	}
	public caseCodeBindBizDaoImpl getCaseCodeBindDao() {
		return caseCodeBindDao;
	}
	public void setCaseCodeBindDao(caseCodeBindBizDaoImpl caseCodeBindDao) {
		this.caseCodeBindDao = caseCodeBindDao;
	}
}
