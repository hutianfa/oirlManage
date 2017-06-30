package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.CarCondition;
import com.ltmcp.dao.CarDao;
import com.ltmcp.entity.Car;

public class CarDaoHibImpl extends BaseDaoHibImpl implements CarDao {

	@Override
	public Car queryCarByCarId(Car car) {
		StringBuilder sb=new StringBuilder("from Car car");
		sb.append(" left join fetch  car.admin ");
		sb.append(" left join fetch  car.company ");
		sb.append(" left join fetch  car.freezes ");
		sb.append(" left join fetch  car.sealeds ");
		sb.append(" where car.carId=? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and car.company.comId=" +AdminComm.getComIdByAdmin() );
		}
		return (Car) super.query(sb.toString(),car.getCarId());
	}

	@Override
	public List<Car> findCar() {
		StringBuilder sb=new StringBuilder("from Car car where 1=1");
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and car.company.comId=" +AdminComm.getComIdByAdmin() );
		}
		return super.find(sb.toString(),null);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findCars(Integer currentPage, Integer pageSize,CarCondition condition) {
		StringBuilder hql=new StringBuilder("from Car car where 1=1  ");
		if(null!=condition){
			if(null != condition.getCarFlag() && !"".equals(condition.getCarFlag())){
				hql.append(" and car.carFlag like '%"+condition.getCarFlag()+"%' ");
			}
			if(null!=condition.getCarFlapper() && !"".equals(condition.getCarFlapper())){
				hql.append(" and car.carFlapper like '%"+condition.getCarFlapper()+"%' ");
			}
			if(AdminComm.getAdminPower() != 1){
				if(null!=condition.getComId()){
					hql.append(" and car.company.comId = "+condition.getComId());
				}else{
					hql.append(" and car.company.comId="+AdminComm.getComIdByAdmin());
				}
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() != null){
				hql.append(" and car.company.comId = "+condition.getComId());
			}

		}else{
			if(AdminComm.getAdminPower() != 1){
				hql.append(" and car.company.comId= "+AdminComm.getComIdByAdmin());
			}
		}
		hql.append(" and car.carStatus = " + Comm.CAR_NORMAL);
		hql.append(" order by car.carCreatetime desc ");
		return super.find(hql.toString(), currentPage, pageSize);
	}


	@Override
	public Integer queryCarCountByCondition(CarCondition condition) {
		StringBuilder hql=new StringBuilder("select count(carId) from Car car where 1=1  ");
		if(null!=condition){
			if(null != condition.getCarFlag() && !"".equals(condition.getCarFlag())){
				hql.append(" and car.carFlag like '%"+condition.getCarFlag()+"%' ");
			}
			if(null != condition.getCarFlapper() && !"".equals(condition.getCarFlapper())){
				hql.append(" and car.carFlapper like '%"+condition.getCarFlapper()+"%' ");
			}
			if(AdminComm.getAdminPower() != 1){
				if(null!=condition.getComId()){
					hql.append(" and car.company.comId = "+condition.getComId());
				}else{
					hql.append(" and car.company.comId="+AdminComm.getComIdByAdmin());
				}
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() != null){
				hql.append(" and car.company.comId = "+condition.getComId());
			}		
		}else{
			if(AdminComm.getAdminPower() != 1){
				hql.append(" and car.company.comId= "+AdminComm.getComIdByAdmin());
			}
		}
		hql.append(" and car.carStatus = " + Comm.CAR_NORMAL);
		return super.queryRowCount(hql.toString());
	}

	@Override
	public void insertCar(Car car) {
		super.insert(car);
	}
	
	@Override
	public void updateCar(Car car) {
		super.update(car);
	}

	@Override
	public void delByCarId(Integer carId) {
		StringBuilder sb=new StringBuilder("update Car c set c.carStatus=? ");
		sb.append(" where  c.carId=? ");
		sb.append("and c.company.comId=? ");
		sb.append("and c.admin.admId=? ");
		super.updateByHql(sb.toString(),Comm.CAR_LOSS,carId,AdminComm.getComIdByAdmin(),AdminComm.getAdminId());
	}

	@Override
	public boolean queryCarIdExit(Integer carId) {
		StringBuilder sb=new StringBuilder("select count(c.carId) from Car c ");
		sb.append(" where c.company.comId=? ");
		sb.append("and c.carId=? and c.carStatus=? and c.admin.admId=?");
		Integer count=super.queryRowCount(sb.toString(),AdminComm.getComIdByAdmin(),carId,Comm.CAR_NORMAL,AdminComm.getAdminId());
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int queryCarFlapperCount(String carFlapper, Integer cAR_NORMAL) {
		StringBuilder sb=new StringBuilder("select count(c.carId) from Car c ");
		sb.append(" where c.carFlapper=? and c.carStatus=? ");
		return super.queryRowCount(sb.toString(),carFlapper,cAR_NORMAL);
	}
	
}
