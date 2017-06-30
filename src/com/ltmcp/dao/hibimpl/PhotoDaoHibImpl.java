package com.ltmcp.dao.hibimpl;

import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.PhotoCondition;
import com.ltmcp.dao.PhotoDao;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Sealed;

public class PhotoDaoHibImpl extends BaseDaoHibImpl implements PhotoDao{

	@Override
	public List<Sealed> selectSeaAndFreImg(PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("from Sealed s ");
		sb.append(" where   s.company.comId="+AdminComm.getComIdByAdmin());
		sb.append(" and  photo_check_status=0");
		sb.append(" order by  s.seaTime  asc");
		return super.find(sb.toString(),0,Comm.SYSTEM_PAGESIZE);
	}

	public Integer queryTotalCountByCondition(PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s where s.photo_check_status=0 and (s.seaStatus = 0 or s.seaStatus = 2 ) ");
		return super.queryRowCount(sb.toString());
	}
	
	@Override
	public Integer updateSeaAndFreImg(PhotoCondition condition) {
		StringBuilder s=new StringBuilder("update Sealed s set s.photo_check_tip=? , s.photo_check_status=? where s.seaId=?");
		Integer n = super.updatePhoByHqlReInt(s.toString(), condition.getSeaTip(),condition.getSeaStatus(),condition.getSeaId());
		StringBuilder sb=new StringBuilder("update Freeze f set f.photo_check_tip=? , f.photo_check_status=? where f.freId=?");
		Integer m = super.updatePhoByHqlReInt(sb.toString(),condition.getFreTip(), condition.getFreStatus(),condition.getFreId());
		return n+m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findPersons(PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("from Person p where 1=1");
		quMethod(condition, sb);
		if(condition!= null && condition.getCurrentPage() != null ){
			return super.find(sb.toString(), condition.getCurrentPage(), Comm.SYSTEM_PERSONSIZE);
		} else{
			return super.find(sb.toString(), 1, Comm.SYSTEM_PERSONSIZE);
		}
	}
	

	@Override
	public Integer findPerTotal(PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(p.perId) from Person p where 1=1");
		quMethod(condition, sb);
		return super.queryRowCount(sb.toString());
	}
	
	
	/**
	 * 根据条件查询的方法
	 * @param condition
	 * @param sb
	 */
	private void quMethod(PhotoCondition condition, StringBuilder sb) {
		if(condition!=null){
			//不是超级管理员
			if(AdminComm.getAdminPower() != 1){
				if(null!=condition.getComId()){
					sb.append(" and p.company.comId = "+condition.getComId());
				}else{
					sb.append(" and p.company.comId = "+AdminComm.getComIdByAdmin());
				}
				//是超级管理员
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() !=null){
				sb.append(" and p.company.comId = "+condition.getComId());
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and p.company.comId = "+AdminComm.getComIdByAdmin());
			}
		}
		sb.append(" and p.perStatus = "+ Comm.PERSON_NORMAL);
	}

	@Override
	public Integer queryAllBadTotalSeaPhoto(Person person,PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s ");
		sb.append("where s.person.perId = "+ person.getPerId());
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		sb.append("and s.photo_check_status=2");
		
		if(condition!=null){
			
			if(!"".equals(condition.getStartDate()) && condition.getStartDate() != null){
				
				sb.append(" and s.seaTime > "+"'"+condition.getStartDate()+"'");
				
			}
			if(!"".equals(condition.getEndDate()) && condition.getEndDate() != null){
				
				sb.append(" and s.seaTime <" +"'"+condition.getEndDate()+"'");
				
			}
		}
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Integer queryAllTotalSeaPhoto(Person person,PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s ");
		sb.append("where s.person.perId = "+ person.getPerId());
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		
		if(condition!=null){
			if(!"".equals(condition.getStartDate()) && condition.getStartDate() != null){
				sb.append(" and s.seaTime > "+"'"+condition.getStartDate()+"'");
			}
			if(!"".equals(condition.getEndDate()) && condition.getEndDate() != null){
				sb.append(" and s.seaTime <" +"'"+condition.getEndDate()+"'");
			}
		}
		return super.queryRowCount(sb.toString());
	}
	
	
	@Override
	public Integer queryAllBadTotalFrePhoto(Person person,PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(f.freId) from Freeze f ");
		sb.append("where f.person.perId = "+ person.getPerId());
		sb.append("and (f.freStatus = 0 OR f.freStatus = 2 )");
		sb.append("and f.photo_check_status=2");
		if(condition!=null){
			if(!"".equals(condition.getStartDate()) && condition.getStartDate() != null){
				sb.append(" and f.freTime > "+"'"+condition.getStartDate()+"'");
			}
			if(!"".equals(condition.getEndDate()) && condition.getEndDate() != null){
				sb.append(" and f.freTime <" +"'"+condition.getEndDate()+"'");
			}
		}
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Integer queryAllTotalFrePhoto(Person person,PhotoCondition condition) {
		StringBuilder sb=new StringBuilder("select count(f.freId) from Freeze f ");
		sb.append("where f.person.perId = "+ person.getPerId());
		sb.append("and (f.freStatus = 0 OR f.freStatus = 2 )");
		if(condition!=null){
			if(!"".equals(condition.getStartDate()) && condition.getStartDate() != null){
				sb.append(" and f.freTime > "+"'"+condition.getStartDate()+"'");
			}
			if(!"".equals(condition.getEndDate()) && condition.getEndDate() != null){
				sb.append(" and f.freTime <" +"'"+condition.getEndDate()+"'");
			}
		}
		return super.queryRowCount(sb.toString());
	}

	@Override
	public Integer queryAllTotalFre(Person person, String beginTime, String endTime) {
		StringBuilder sb=new StringBuilder("select count(f.freId) from Freeze f ");
		sb.append("where f.person.perId = "+ person.getPerId());
		sb.append("and (f.freStatus = 0 OR f.freStatus = 2 )");
		
		sb.append(" and f.freTime > date_format('"+beginTime+"','%Y-%m-%d')");
		sb.append(" and f.freTime < date_format('"+endTime+"','%Y-%m-%d')");
		
		return super.queryRowCount(sb.toString());
	}


	@Override
	public Integer queryAllBadTotalFre(Person person, String beginTime,String endTime) {
		StringBuilder sb=new StringBuilder("select count(f.freId) from Freeze f ");
		sb.append("where f.person.perId = "+ person.getPerId());
		sb.append("and (f.freStatus = 0 OR f.freStatus = 2 )");
		sb.append("and f.photo_check_status=2");
		
		sb.append(" and f.freTime > date_format('"+beginTime+"','%Y-%m-%d')");
		sb.append(" and f.freTime < date_format('"+endTime+"','%Y-%m-%d')");
			
		return super.queryRowCount(sb.toString());
	}
	
	
	@Override
	public Integer queryAllTotalSea(Person person, String beginTime, String endTime) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s ");
		sb.append("where s.person.perId = "+ person.getPerId());
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		
		sb.append(" and s.seaTime > date_format('"+beginTime+"','%Y-%m-%d')");
		sb.append(" and s.seaTime < date_format('"+endTime+"','%Y-%m-%d')");
		
		return super.queryRowCount(sb.toString());
	}


	@Override
	public Integer queryAllBadTotalSea(Person person, String beginTime,String endTime) {
		StringBuilder sb=new StringBuilder("select count(s.seaId) from Sealed s ");
		sb.append("where s.person.perId = "+ person.getPerId());
		sb.append("and (s.seaStatus = 0 or s.seaStatus = 2 )");
		sb.append("and s.photo_check_status=2");
		
		sb.append(" and s.seaTime > date_format('"+beginTime+"','%Y-%m-%d')");
		sb.append(" and s.seaTime < date_format('"+endTime+"','%Y-%m-%d')");
		
		return super.queryRowCount(sb.toString());
	}

	

}
