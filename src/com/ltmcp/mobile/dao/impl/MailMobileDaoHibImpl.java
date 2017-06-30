package com.ltmcp.mobile.dao.impl;

import java.util.List;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.MailCondition;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.Petrol;
import com.ltmcp.entity.Position;
import com.ltmcp.mobile.dao.MailMobileDao;

public class MailMobileDaoHibImpl extends BaseDaoHibImpl implements MailMobileDao{

	@Override
	public List<Car> queryCar(MailCondition condition) {
		StringBuilder sb=new StringBuilder("from Car car where 1=1");
		if(condition.getComId() != null){
			sb.append(" and car.company.comId=" + condition.getComId() );
		}
		return super.find(sb.toString(),null);
	}

	@Override
	public List<Position> queryPosi(MailCondition condition) {
		StringBuilder sb = new StringBuilder("from Position where posStatus=? ");
        
        if(condition.getComId() != null){
            sb.append(" and company.comId= " + condition.getComId());
        }
        return super.find(sb.toString(), Comm.POSITION_NORMAL);
	}

	@Override
	public Petrol queryPetrol(String carFlag, int posid,MailCondition condition) {
		StringBuilder sb = new StringBuilder("from Petrol where carFlaper =?");
		sb.append(" and (time between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
		return (Petrol) super.query(sb.toString(), carFlag);
	}

	@Override
	public Integer queryWayAllNum(int carid, MailCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where 1=1 ");
		sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
		sb.append(" and s.company.comId="+condition.getComId());
		sb.append(" and s.position.posId = "+condition.getBigPosid());
		sb.append(" and s.car.carId = " + carid);		
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Integer queryWayNoAllNum(int carid, int posid,MailCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where 1=1 ");
		sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
		sb.append(" and s.company.comId="+condition.getComId());
		sb.append(" and s.position.posId = "+posid);
		sb.append(" and s.car.carId = " + carid);
		return super.queryRowCount(sb.toString());
	}

}
