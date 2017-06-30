package com.ltmcp.dao.hibimpl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Sealed;

public class SealedDaoHibImpl extends BaseDaoHibImpl implements SealedDao {

	@Override
	public Sealed querySealed(Sealed sealed) {
		StringBuilder sb=new StringBuilder("from Sealed s ");
		sb.append(" left join fetch s.person ");
		sb.append(" left join fetch s.position ");
		sb.append(" left join fetch s.company ");
		sb.append(" left join fetch s.freeze ");
		sb.append(" left join fetch s.person ");
		sb.append(" where s.seaId=?  ");
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and s.company.comId= "+ AdminComm.getComIdByAdmin());
		}
		return (Sealed) super.query(sb.toString(),sealed.getSeaId());
	}

	@Override
	public List<Sealed> findSealeds(Integer limt,Integer currentPage, Integer pageSize,SealedCondition condition) {
		StringBuilder sb=new StringBuilder("from Sealed s where 1=1 ");
		condition(limt,condition, sb);
		sb.append(" order by s.seaTime desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}

	private void condition(Integer limt,SealedCondition condition, StringBuilder sb) {
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c = Calendar.getInstance();
  	   //获取当前时间的前i个月
  	     c.add(Calendar.DAY_OF_MONTH,-limt);     		      
	      //截止时间
	      Date begin = c.getTime();
	      String beginTime = matter.format(begin);
		
		if(condition!=null){
			
			if(AdminComm.getAdminPower() == 1 && null != condition.getComId()){
				sb.append(" and s.company.comId="+condition.getComId());
			}else if(AdminComm.getAdminPower() != 1){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			}
			if("true".equals(condition.getEmpowerType())){
				sb.append(" and (s.freeze.powerTips = 73 or s.freeze.powerTips = 72)");
			}
			//如果区域不为空
			if(condition.getAreaid() != null &&  !"".equals(condition.getAreaid())){
				sb.append(" and s.position.areaid = "+condition.getAreaid());
			}			
			if(condition.getPosId() != null && !"".equals(condition.getPosId()) ){
				sb.append(" and s.position.posId = "+condition.getPosId());
			}			
			if(null != condition.getCarFlapper() && !"".equals(condition.getCarFlapper())){
				sb.append(" and s.car.carFlapper like  '%"+condition.getCarFlapper()+"%'");
			}			
			if(null != condition.getTag()){
				sb.append(" and s.tag ="+condition.getTag());
			}
			if(null!=condition.getPerId() && !"".equals(condition.getPerId())){
				sb.append(" and s.person.perId ="+condition.getPerId());
			}
			if(null != condition.getStatus()){
				sb.append(" and s.seaStatus= "+condition.getStatus());
				
				if( condition.getStatus()  == 1){					
					sb.append(" and s.seaTime > '"+beginTime+"'");
				}else if(condition.getStatus()  == 0){
					sb.append(" and s.freeze.freId <> null");
				}
			}
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null &&  "".equals(condition.getEndTime()) || condition.getEndTime() == null){

				sb.append(" and s.seaTime > "+ "'"+condition.getBeginTime()+"'");
			}		
			if( !"".equals(condition.getEndTime()) && condition.getEndTime() != null && "".equals(condition.getBeginTime()) || condition.getBeginTime() == null){

				sb.append(" and s.seaTime < "+ "'"+condition.getEndTime()+"'");
			}			
			if(!"".equals(condition.getBeginTime()) && condition.getBeginTime() != null && !"".equals(condition.getEndTime())  && condition.getEndTime() != null){
			
				sb.append(" and (s.seaTime between "+"'"+condition.getBeginTime()+"'"+"and "+ "'"+condition.getEndTime()+"'"+")");
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			}
		}
		
	}

	@Override
	public Integer querySealedCountByStatus(Integer limt,Integer status) {
		
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c = Calendar.getInstance();
  	   //获取当前时间的前i个月
  	     c.add(Calendar.DAY_OF_MONTH,-limt);	     		      
	      //截止时间
	     Date begin = c.getTime();
	     String beginTime = matter.format(begin);
	     StringBuilder sb = null;
    	 sb=new StringBuilder("select count(seaStatus) from Sealed s where s.seaStatus = "+status);
 		
    	if(AdminComm.getAdminPower() != 1){
 			sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
 		}
 		if(status == 1){
 			sb.append(" and s.seaTime > '"+beginTime+"'");
 		}

		return super.queryRowCount(sb.toString(),null);
	}
	

	@Override
	public Limt queryLimitime() {
		String hql="from Limt l where 1=1";
		return (Limt) super.query(hql, null);
	}

	@Override
	public Integer queryTotalCountByCondition(Integer limt,SealedCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where 1=1 ");
		condition(limt,condition, sb);
		return super.queryRowCount(sb.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> findSealeds(Integer curentPage, Integer pageSize,Integer id) {
		StringBuilder sb=new StringBuilder("from Sealed s left join fetch s.freeze f ");
		sb.append(" where s.person.perId="+id+" or f.person.perId="+id);
		if(AdminComm.getAdminPower() != 1){
			sb.append("and  s.company.comId ="+AdminComm.getComIdByAdmin());
		}
		return super.find(sb.toString());
		
	}

	@Override
	public Integer queryTotalCountByPerId(Integer id) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed  s where  s.person.perId= ? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and  s.company.comId ="+AdminComm.getComIdByAdmin());
		}
		Integer seaCount=super.queryRowCount(sb.toString(),id);
		
		StringBuilder sb1=new StringBuilder("select count(s.freId) from Freeze  s where  s.person.perId= ? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and  s.company.comId ="+AdminComm.getComIdByAdmin());
		}
		Integer freCount=super.queryRowCount(sb1.toString(),id);
		return seaCount+freCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> findSealedsByCarId(Integer currentPage, Integer pageSize, Integer carId) {
		StringBuilder sb=new StringBuilder("from Sealed s where s.car.carId=? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and s.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.find(sb.toString(), currentPage, pageSize,carId);
	}

	@Override
	public Integer querySealedCountByCarId(Integer id) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.car.carId=? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and s.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.queryRowCount(sb.toString(),id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> findSealedsByPositionId(Integer currentPage,Integer pageSize, Integer id) {
		StringBuilder sb=new StringBuilder("from Sealed s where s.position.posId=? ");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and s.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.find(sb.toString(), currentPage, pageSize,id);
	}

	@Override
	public Integer querySealedCountByPositionId(Integer id) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.position.posId=?");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and s.company.comId=" + AdminComm.getComIdByAdmin());
		}
		return super.queryRowCount(sb.toString(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> selectSealeds(Integer limt,SealedCondition condition) {
		StringBuilder sb=new StringBuilder("from Sealed s where 1=1 ");
		condition(limt,condition, sb);
		sb.append(" order by s.seaTime desc ");
		return super.find(sb.toString());	
	}

	@SuppressWarnings("unchecked")
	@Override															
	public List<Sealed> selectSeaAndFre(SealedCondition condition) {
		
		StringBuilder sb=new StringBuilder("from Sealed s ");
		
		if(AdminComm.getAdminPower() != 1){
			sb.append(" where   s.company.comId="+AdminComm.getComIdByAdmin());
		} else if(AdminComm.getAdminPower() == 1){
			if(condition.getComId() != null & !"".equals(condition.getComId())){
				sb.append(" where   s.company.comId="+condition.getComId());
			}				
		}
		sb.append(" order by  s.seaTime  desc");
		return super.find(sb.toString(),0,10);

	}

//	@Override
//	public List<Sealed> querySealed_test() {
//		StringBuilder sb=new StringBuilder("from Sealed s where 1=1 ");
//		sb.append(" and (s.seaTime between '2016-06-01 00:32:19' and '2016-07-01 00:32:19')");
//		sb.append(" and s.company.comId= 4");
//		sb.append(" and s.freeze.freId is null");
//		return super.find(sb.toString());
//	}
	
}
