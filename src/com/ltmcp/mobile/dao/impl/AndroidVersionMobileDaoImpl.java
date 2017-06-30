package com.ltmcp.mobile.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.AndroidVersion;
import com.ltmcp.mobile.dao.AndroidVersionMobileDao;

public class AndroidVersionMobileDaoImpl extends BaseDaoHibImpl implements AndroidVersionMobileDao{

	@Override
	public AndroidVersion queryAndroidVersion(String versionType) {
		Session session=super.openSession();
		Query query=session.createQuery("from AndroidVersion av where av.vrsionType=? order by av.id desc ");
		query.setMaxResults(1);
		query.setFirstResult(0);
		query.setString(0, versionType);
		AndroidVersion av=(AndroidVersion) query.uniqueResult();
		return  av;
	}

	@Override
	public AndroidVersion queryTimeForVedio() {
		Session session=super.openSession();
		Query query=session.createQuery("from AndroidVersion");
		AndroidVersion av=(AndroidVersion) query.uniqueResult();
		return  av;
	}
}
