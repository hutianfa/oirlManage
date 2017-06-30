package com.ltmcp.dao.hibimpl;

import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.PersonCondition;
import com.ltmcp.dao.PersonDao;
import com.ltmcp.entity.Person;

public class PersonDaoHibImpl extends BaseDaoHibImpl implements PersonDao{

	@Override
	public Person queryPersonById(Person person) {
		StringBuilder sb=new StringBuilder("from Person p");
		sb.append(" where p.perId=?");
		return (Person) super.query(sb.toString(),person.getPerId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findPersons(Integer currentPage, Integer pageSize,PersonCondition condition) {
		StringBuilder sb=new StringBuilder("from Person p where 1=1");		
		queryMethod(condition, sb);
		sb.append(" order by p.perCreateDate desc ");
		return super.find(sb.toString(), currentPage, pageSize,null);
	}

	/**
	 * 根据条件查询的方法
	 * @param condition
	 * @param sb
	 */
	private void queryMethod(PersonCondition condition, StringBuilder sb) {
		
		if(condition!=null){
			
			if(AdminComm.getAdminPower() != 1){
				
				sb.append(" and p.company.comId = "+AdminComm.getComIdByAdmin());
				
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() !=null){
				
				sb.append(" and p.company.comId = "+condition.getComId());
				
			}
			//如果区域不为空
			if(condition.getAreaId() != null &&  !"".equals(condition.getAreaId())){
				sb.append(" and p.position.areaid = "+condition.getAreaId());
			}			
			if(condition.getPosId() != null && !"".equals(condition.getPosId()) ){
				sb.append(" and p.position.posId = "+condition.getPosId());
			}			
//			if(null != condition.getPerId()){
//				sb.append(" and p.perId = "+condition.getPerId());
//			}
			if(null != condition.getPerTrueName()){
				sb.append(" and p.perTrueName like '%"+condition.getPerTrueName()+"%'");
			}
			if(null != condition.getPerUserName() && !"".equals(condition.getPerUserName())){
				sb.append(" and p.perName like '%"+condition.getPerUserName()+"%'");
			}
			if(null != condition.getPerSex()){
				sb.append(" and p.perSex = "+condition.getPerSex());
			}			
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and p.company.comId = "+AdminComm.getComIdByAdmin());
			}
		}
		sb.append("and p.perEmail like '%0%'");	
		sb.append(" and p.perStatus = "+Comm.PERSON_NORMAL);
	}

	

	@Override
	public Integer queryPersonCountByCondition(PersonCondition condition) {
		StringBuilder sb=new StringBuilder("select count(perId) from Person p where 1=1 ");
		this.queryMethod(condition, sb);
		return super.queryRowCount(sb.toString());
	}
	
	@Override
	public void updatePerson(Person person) {
		super.saveOrUpdate(person);
	}

	@Override
	public void deletePerson(Person person) {
		StringBuilder sb = new StringBuilder("update  Person p set p.perStatus=?  ");
		sb.append(" where p.perId=? ");
		try {
			super.updateByHql(sb.toString(), Comm.PERSON_LOSS,person.getPerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertPerson(Person addPerson){
		super.insert(addPerson);
	}

	/**
	 * 检测操作员是否存在
	 * @param person
	 * @return
	 */
	
	@Override
	public boolean queryPersonExits(Person person) {
		StringBuilder sb=new StringBuilder("select count(p.perId) from Person p");
		sb.append(" where  p.perName=? and p.perStatus=?  ");
		Integer count=super.queryRowCount(sb.toString(),person.getPerName(),Comm.PERSON_NORMAL);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	
//	public boolean personIsExit(Person person){
//		StringBuilder sb=new StringBuilder("select count(p.perId) from Person p");
//		sb.append(" where  p.perName=? and p.perStatus=?  ");
//		Integer count=super.queryRowCount(sb.toString(),person.getPerName(),Comm.PERSON_NORMAL);
//		if(count>0){
//			return true;
//		}else{
//			return false;
//		}
//	}
	
	@Override
	public boolean personIdIsExit(Integer personId) {
		StringBuilder sb=new StringBuilder("select count(p.perId) from Person p ");
		sb.append(" where p.perStatus=? and p.perId=? ");
		Integer count=super.queryRowCount(sb.toString(),Comm.PERSON_NORMAL,personId);
		if(count>0){
			return true;
		}
		return false;
	}




	
	
}