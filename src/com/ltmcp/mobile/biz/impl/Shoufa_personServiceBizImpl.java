package com.ltmcp.mobile.biz.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.biz.PersonMobileBiz;
import com.ltmcp.mobile.biz.Shoufa_personServiceBiz;
import com.ltmcp.mobile.dao.PersonMobileDao;
import com.ltmcp.mobile.dao.Shoufa_personServiceDao;
import com.ltmcp.util.CharTools;
import com.ltmcp.util.MD5;

public class Shoufa_personServiceBizImpl implements Shoufa_personServiceBiz {
	private Shoufa_personServiceDao shoufa_personServiceDao;
	@Override
	public void saveShoufa_personInfoToSession(shoufa_person shoufa_person) {
		// TODO Auto-generated method stub
		shoufa_personServiceDao.saveShoufa_personInfoToSession(shoufa_person);
	}
	

	public Shoufa_personServiceDao getShoufa_personServiceDao() {
		return shoufa_personServiceDao;
	}


	public void setShoufa_personServiceDao(
			Shoufa_personServiceDao shoufa_personServiceDao) {
		this.shoufa_personServiceDao = shoufa_personServiceDao;
	}


	

	
}
