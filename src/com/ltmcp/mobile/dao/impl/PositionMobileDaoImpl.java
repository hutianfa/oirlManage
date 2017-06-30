package com.ltmcp.mobile.dao.impl;

import java.sql.Timestamp;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.DW;
import com.ltmcp.entity.Position;
import com.ltmcp.mobile.dao.PositionMobileDao;

public class PositionMobileDaoImpl extends BaseDaoHibImpl implements PositionMobileDao {
    
    @Override
    public Integer queryPositionId(String name, String password,String cardNumber,String phoneMac) {
    	//System.out.println("ÐÕÃû£º"+name+" "+password+" "+cardNumber+" "+phoneMac);
        StringBuilder sb=new StringBuilder("select po.posId from Position po ");
        sb.append(" right join  po.persons  pe");
        sb.append(" where po.posCardNumber like ?");
        sb.append(" and pe.perName=? and pe.perPassword=? and pe.perStatus=? ");
        //Ìí¼Ómac²éÑ¯×Ö¶Î
//        if(!"".equals(phoneMac)){
//            sb.append(" and po.phoneMac like ',"+phoneMac+",'");
//        }
        Integer id=(Integer) super.queryUniqueObject(sb.toString(),"%,"+cardNumber+",%",name,password,Comm.PERSON_NORMAL);
        return id;
    }

	@Override
	public String queryPosiName(Integer posId) {
		StringBuilder sb=new StringBuilder("select po.posName from Position po ");
		sb.append(" where po.posId =" + posId);
		return (String) super.query(sb.toString(), null);
	}


	@Override
	public Position queryPosi(Integer posId) {
		StringBuilder sb=new StringBuilder("from Position po ");
		sb.append(" where po.posId =" + posId);
		return (Position) super.query(sb.toString(), null);
	}
	
	
	@Override
	public void insertDW(DW dw) {
		super.saveOrUpdate(dw);
	}

	@Override
	public void reInsertDW(DW dw) {
		
		StringBuilder sb = new StringBuilder(" update DW set appNum=? , time=?");
		sb.append(" where imei =?  ");
		super.updateByHql(sb.toString(), dw.getAppNum(),dw.getTime() , dw.getImei());
	}
	
	@Override
	public DW queryDWByImei(String phoneMac) {
		StringBuilder sb = new StringBuilder("from DW where imei = ?");
		return (DW) super.query(sb.toString(), phoneMac);
	}

}
