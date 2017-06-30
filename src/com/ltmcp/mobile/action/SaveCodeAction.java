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
	private String bagCode1;//���Ӷ�ά���ȡ
	private String bagCode2;//���Ӷ�ά���ȡ
	private String boxCode1;//���Ӷ�ά���ȡ
	
	private String cd;
	private String freeze;
	private String unfreeze;
	private String bagCode;
	private String bagCodde2;
	
	
	//���Ӱ󶨴���
	public void bindCaseInbagCode(){
		 Inventor_BoxCode ibo = new Inventor_BoxCode();
		 System.out.println("��ӡ���Ӷ�ά����Ϣ����boxCode1"+boxCode1+";name"+name);
		 String boxCodee1 = boxCode1.substring(0, 1);
		 Inventor_BoxCode ib = new Inventor_BoxCode();
		 if("x".equals(boxCodee1)){
			 ib.setBox_code(boxCode1);
			 ib.setStatus(0);
			 boolean sta1 = caseCodeBindBiz.checkCaseCodeExist(boxCode1);//inventor_boxcode��������һ�ű�
			 boolean sta2 = dbcbcheck.checkCasecodeExist(boxCode1);
			 //һ�����ӵ������״̬Ϊ1���������ô��������sql��̨�ٶȣ��Լ�û��״̬Ϊ1������˵20�����ӣ�����ѯ֮����������һ�����߶�����ӵ�״̬��Ϊ1��
		
			 /*���dbc_bagcodebind����status�Ƿ�Ϊ1,Ϊ1���ܰ����ӣ�ǰ������֮��ɨ��20������֮����ܱ���ɨ�����Ӷ�ά��,
			  * ����֮����ɨ�����ӵĶ�ά��Ҳ�ɹ�,����������Ӷ�ά������⣺ֻ��2�����:
			  * һ���ǲ���x��ͷ�ģ�����һ���ǣ��ڿ����е��ҵ�������ӵĶ�ά��,����ֵ����-2001����-205������ǰ��������һ������
			  * ������ɨ�����Ӷ�ά�룬���߸��������ϵĶ�ά�루����֮��ɨ��ɹ��ڣ������ʾ����ɨ����Ӷ�ά���״̬�����ҿ��ǵ�������2̨�Լ����ϵ�
			  * �ֳֻ��ĵ�¼��ͬ���˺Ž��в������˺ŵĵ�¼Ψһ��ȷ�ϵ���ƹ��ƻ�û�����ɣ����鷳��������ƻ������﷢������ʱ��ʱ����˺Ŷ�����ͬ����
			  * 
			  * */
			 if(sta1 || sta2){
				 super.getPringWriter().print(-2001);//���Ӷ�ά���Ѿ�������
     			 return;
			 }else{
				 
				 
				 //д�뵽inventor_boxcode���У������ǳ��ҵĿ�����inventor������˼
				 
//				 Timestamp ts=new Timestamp(System.currentTimeMillis());
//				 String tsStr = "";   
//			     DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//Timestampתstring   
				 //����ʱ���Ƿ���Ҫ��Inventor_BoxCode���У����Ʒ���ʱ�������Ҫ������Ҳ���ڲ��ҷ����ĵĶ������н���ʱ������ȷ�ϣ�������
			     ibo.setStatus(0);
			     ibo.setBox_code(boxCode1);
			     saveCode.addCaseCode(boxCode1, name);//���ӹ��������ϵĶ�ά�루�����ı������ǣ�dbc_bagcodebind��
				 caseCodeBindBiz.saveCaseCode(ibo);//�������Ӷ�ά�뵽inventor_boxcode��
				 super.getPringWriter().print(-2066);//���ӹ������ӳɹ�
     			 return;
			 }
		 }else{
			 super.getPringWriter().print(-205);//����ʧ�ܣ�����x��ͷ�Ķ�ά�룩
			 return;
		 }
	}
	//�ı���ӱ���״̬��׼���������Ӷ�ά��
	public void changeBagCodeStatus(){
		
		Dbc_BagCodeBind dbcb = new Dbc_BagCodeBind();
		String bagCodee2 = bagCode2.substring(0, 1);
		if("b".equals(bagCodee2)){
			bagCodde2 = bagCode2;
			Timestamp ts=new Timestamp(System.currentTimeMillis());//��ؼ���3���裺�ı�ʱ���״̬�����2����ʱ���״̬������name�ֶ�ֵ�������Ӷ�ά��
//			String tsStr = "";   
//	        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//Timestampתstring   
	        try {
				//System.out.println("���Ӷ�ά��:"+bagCode2+",������"+name+",ʱ�䣺"+ts);
	        	//tsStr = sdf.format(ts);
	        	boolean sta1 = dbcbcheck.checkBagcodeExist(bagCode2);//�ڴ���ע����в鿴�Ƿ��Ѿ����ڴ��Ӷ�ά��.sta1Ϊtrue��ʾ�Ѿ����ڣ�false��ʾ������
	        	boolean sta2 = dbcbcheck.checkBagcodeStaAndBagCode(bagCode2);//�Ѿ������ӹ����ɹ���
	        	boolean sta3 = dbcbcheck.multipleScan(bagCode2);//û�й������Ӷ�ά������£��ظ�ɨ����Ӷ�ά��
	        	
	        	//System.out.println(sta2+": sta2�Ѿ������Ӷ�ά��ɹ�������״̬");
	        	if(sta1){
	        		if(sta2){
	        			super.getPringWriter().print(-2067);//sta2�Ѿ������Ӷ�ά��ɹ�������״̬
	        			return;
	        		}else if(sta3){
	        			super.getPringWriter().print(-1999);//sta3�ж��ظ�ɨ����Ӷ�ά�룬ǰ���Ǹô��Ӷ�ά��û�б����ӹ���
	        			return;
	        			}
	        		  else{
	        			  dbcheckdao.changeBagCodeStatusInRegister(bagCode2, name, ts);//���Ӷ�ά����ڲ����޸�״̬Ϊ1
	        			  super.getPringWriter().print(-2066);
	        		}
				}else{
					super.getPringWriter().print(-206);//�ô��Ӷ�ά��û�й������з�ǩ��������ɨ����������
					//System.out.println("�ô��Ӷ�ά��û�й������з�ǩ");
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
		}else{
			super.getPringWriter().print(-205);//����b��ͷ�Ĵ��Ӷ�ά��
			return;
		}
	}
	
	//ɨ���ǩ����ķ���
	public void ScanningWaima()
	{
		System.out.println("��ӡ����:"+code2);
	}
	/**
	 * ���Ӱ󶨴������Ѿ�ע��ɹ��Ķ�ά��
	 */
	 public void boxCodeBind(){
		 System.out.println("bagCode1"+bagCode1+";name"+name+"������Ӱ󶨶�ά���ǩ");
		 Dbc_BagCodeBind dbcb = new Dbc_BagCodeBind();
		 String bagCodee1 = bagCode1.substring(0, 1);
		 
		 if("b".equals(bagCodee1)){
			 bagCode=bagCode1;
			 
			try {
				boolean sta = saveCode.checkBagCodeInDBC(bagCode1);//������ж��Ƿ�ʹ�ù��ô��Ӷ�ά��
				boolean sta1 = dbcbcheck.checkBagcodeExist(bagCode1);//�ڴ���ע����в鿴�Ƿ��Ѿ����ڴ��Ӷ�ά��
				System.out.println(sta+"sta״̬"+";sta1��״̬"+sta1);
				if(sta || sta1)//ֻҪ����һ���ж�Ϊtrue������ʾ�ô��Ӷ�ά���Ѿ���ʹ��
				{
					System.out.println("�ô��Ӷ�ά���Ѿ���ע��ʹ�ã������������");
					 super.getPringWriter().print(-208);//����ʧ�ܣ����Ӷ�ά���Ѿ���ע�ᣬ�������������ɨ�裩
					return;
				}else{
						 dbcb.setBag_code(bagCode);
						 dbcb.setStatus(0);
						 
						 try {
							 saveCode.addBagCode(bagCode1,name);//���д��Ӷ�ά�����ݵı����������
							 saveCode.saveCodeBag(dbcb);//�Ѵ��Ӷ�ά��ע�ᵽ����
							 
							
							 super.getPringWriter().print(-2066);//���沢����ok
						} catch (Exception e) {
							super.getPringWriter().print(-206);//�����쳣
						}
				     }
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		 }else{
				super.getPringWriter().print(-205);//����ʧ�ܣ�����b��ͷ�Ķ�ά�룩
				return;
			}
		 
	 }
	/**
	 * ע���ά��
	 */
	public void registcode(){
		/**
		 * ��ע��Ķ��󱣴棬�Ҳ����Ⱥ�
		 * @return -205 ����һ��
		 * @return -206 ע��ʧ��
		 */
		DimensionalBarCode code = new DimensionalBarCode();
		
		String cod1 = code1.substring(0, 1);
		String cod2 = code2.substring(0, 1);
		String namee = name;
		System.out.println(name+"��ӡname");
		if("0".equals(cod1) && "1".equals(cod2)){
			freeze = code1;
			unfreeze = code2;
		} else if("1".equals(cod1) && "0".equals(cod2)){//����
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
			saveCode.addCode(code);//�������
			super.getPringWriter().print(-2066);//����ok
		} catch (Exception e) {
			
			super.getPringWriter().print(-206);//�����쳣
		}
	}
	/**
	 * ����ά���Ƿ��Ѿ���ע�ᣬ
	 * @return ��1 û��ע��
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
