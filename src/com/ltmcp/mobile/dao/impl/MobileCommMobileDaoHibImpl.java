package com.ltmcp.mobile.dao.impl;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.PetrolName;
import com.ltmcp.entity.Picture;
import com.ltmcp.entity.Sealed;
import com.ltmcp.mobile.dao.MobileCommMobileDao;

public class MobileCommMobileDaoHibImpl extends BaseDaoHibImpl implements MobileCommMobileDao{

	@Override
	public String findYouPinBySealCode(String code) {
		
		StringBuilder sb=new StringBuilder(" from Sealed s");
		sb.append(" where s.dimensionalBarCode.freeze_content=? and s.dimensionalBarCode.freeze_status=? ");
		Sealed sealed = (Sealed) super.query(sb.toString(), code,Comm.TWO_CODE_CENTER);
		String str = "";
		if(sealed != null){
			str =sealed.getYouPinName();
		}
		return str;
	}

	@Override
	public PetrolName findYouPinByNfc(String nfc,int comid) {
		
		StringBuilder sb=new StringBuilder(" from PetrolName p");
		sb.append(" where p.you_nfc_id like '%,"+nfc+",%'");
		sb.append(" and p.com_id = "+ comid);
		return (PetrolName) super.query(sb.toString());
		
	}

	@Override
	public int findComidByUsername(String username) {
		StringBuilder sb=new StringBuilder(" from Person p");
		sb.append(" where p.perName = ? ");
		
		Person pp = (Person) super.query(sb.toString(), username);
		
		if(pp != null){
			return pp.getCompany().getComId();
		}
		return 0;
	}

	@Override
	public void changeSeaByYunDan(String yundan, String twoCode, String filePath) {
		String str = twoCode.substring(0,1);
		Integer id = 0;
		
		
		
		if("0".equals(str)){
			
			StringBuilder ss = new StringBuilder("select id from DimensionalBarCode ");
			ss.append(" where freeze_content= ?");
			id =  (Integer) super.queryUniqueObject(ss.toString(),twoCode);
			
			System.out.println("0y:"+yundan);
			
			System.out.println("0t:"+twoCode);
			
			System.out.println("0f:"+filePath);
			
			System.out.println("0i:"+id);
			
			if(id == null){
				return ;
			}
			
			StringBuilder sb1 = new StringBuilder(" update Sealed s set s.seaImg= ?");
			sb1.append(" where s.seaNumber = ?  ");
			sb1.append(" and s.dimensionalBarCode.id= ? ");
			super.updateByHql(sb1.toString(), filePath,yundan,id);
			
		}else if("1".equals(str)){
			
			StringBuilder ss = new StringBuilder("select id from DimensionalBarCode ");
			ss.append(" where unfreeze_content= ? ");
			id =  (Integer) super.queryUniqueObject(ss.toString(),twoCode);
			
			System.out.println("1:"+yundan);
			
			System.out.println("1:"+twoCode);
			
			System.out.println("1:"+filePath);
			
			System.out.println("1:"+id);
			
			if(id == null){
				return ;
			}
			
			StringBuilder sb2 = new StringBuilder(" update Freeze f set f.freImg= ?");
			sb2.append(" where f.freNumber = ?  ");
			sb2.append(" and f.dimensionalBarCode.id= ?");
			super.updateByHql(sb2.toString(), filePath,yundan,id);
			
		}
		
	}

	@Override
	public void changeSeaPicByYunDan(String type, String bianhao, String picPath) {
//		if("sf".equals(type)){
//			
//			StringBuilder sb1 = new StringBuilder(" update Sealed  set seaLockCode= ?");
//			sb1.append(" where seaNumber = ?  ");
//			super.updateByHql(sb1.toString(),picPath, bianhao);
//			
//		}else if("jf".equals(type)){
//			
//			StringBuilder sb2 = new StringBuilder(" update Freeze  set freLockCode= ?");
//			sb2.append(" where freNumber = ?  ");
//			super.updateByHql(sb2.toString(),picPath, bianhao);
//		}
		
		
		Picture pic = new Picture();
		
		pic.setPicPath(picPath);
		pic.setWayNum(bianhao);
		
		
		super.saveOrUpdate(pic);
	
	}

	@Override
	public List<Picture> findPicByWayNum(String wayNum) {
		StringBuffer sb = new StringBuffer("from Picture where 1=1");
		sb.append("and wayNum = ?");
		return super.findList(sb.toString(), wayNum);
	}

}
