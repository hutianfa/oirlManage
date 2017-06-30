package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.dao.firstOrderDao;
import com.ltmcp.entity.Car;
import com.ltmcp.entity.first_order;

public class firstOrderDaoHibImpl extends BaseDaoHibImpl implements firstOrderDao {

	@Override
	public List<first_order> findFirst_order(int comid) {
		StringBuilder sb=new StringBuilder("from first_order fo");
		sb.append(" where fo.comid=?");
		return super.find(sb.toString(), comid);
	}

	@Override
	public void insertFirst_order(first_order first_order) {
		super.insert(first_order);
		
	}

	@Override
	public void orderModify(Integer id, Integer num) {
		StringBuilder sb = new StringBuilder("update  first_order p set p.fahuo_number=?");
		sb.append(" where p.id=? ");
		try {
			super.updateByHql(sb.toString(), num,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkOrderStatus(Integer id, Integer status) {
		//这个hql可能错误
		StringBuilder sb=new StringBuilder("select count(id) from first_order where id=? and status=?");
		Integer count=super.queryRowCount(sb.toString(), id, 0);
		if(count>0){
			return true;//不能更新
		}
		return false;//可以更新
	}

	
	
}
