package com.ltmcp.mobile.dao.impl;

import java.util.List;

import com.ltmcp.comm.Comm;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.Person;
import com.ltmcp.mobile.dao.PersonMobileDao;

public class PersonMobileDaoImpl extends BaseDaoHibImpl implements
		PersonMobileDao {

	@Override
	public boolean queryPersonExist(Person person) {
		//select count(p.perId) from Person p where p.perName=? and p.perPassword=? and p.perStatus=?
		//在Integer count = super.queryRowCount()中会把person.getPerName(),person.getPerPassword(), Comm.PERSON_NORMAL
		//具体的值放进select中的参数三个？中。
		StringBuilder sb = new StringBuilder("select count(p.perId) from Person p where p.perName=? and p.perPassword=? and p.perStatus=?");
		Integer count = super.queryRowCount(sb.toString(), person.getPerName(),person.getPerPassword(), Comm.PERSON_NORMAL);
		//执行上面的代码具体可以在sql中如下的转化：
		//select count(per_id) from Person where per_name='xxw' and per_password='beca3389582d0f65ce6e536c053619ff' and per_status=0
		//存在则此sql语句返回1
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Person queryPersonByPerson(Person person) {
		StringBuilder sb = new StringBuilder("from Person p where p.perName=? and p.perPassword=? and p.perStatus=?");
		return (Person) super.query(sb.toString(),person.getPerName(), person.getPerPassword(),Comm.PERSON_NORMAL);
	}
	
	@Override
	public Integer queryPersonCompanyId(Person person) {
		StringBuilder sb = new StringBuilder("select p.company.comId from Person p where p.perName=? and p.perPassword=? and p.perStatus=?");
		Integer comId = (Integer) super.queryUniqueObject(sb.toString(),person.getPerName(), person.getPerPassword(),Comm.PERSON_NORMAL);
		return comId;
	}

	@Override
	public void updatePerson(Person person, String newPassword)throws Exception {
		StringBuilder sb = new StringBuilder("update Person p set p.perPassword=? where p.perName=? and  p.perStatus=?");
		super.updateByHql(sb.toString(), newPassword, person.getPerName(),Comm.PERSON_NORMAL);
	}

	@Override
	public Integer queryPersonType(Person person) {
		StringBuilder sb = new StringBuilder("select p.basDict.dictId from Person p ");
		sb.append("where p.perName=? and p.perPassword=? and p.perStatus=?");
		return (Integer) super.queryUniqueObject(sb.toString(),person.getPerName(), person.getPerPassword(),Comm.PERSON_NORMAL);
	}

	@Override
	public Integer queryPersonId(Person person) {
		StringBuilder sb = new StringBuilder(" select p.perId from Person p");
		sb.append(" where p.perName=? and p.perPassword=? and p.perStatus=?");
		return (Integer) super.queryUniqueObject(sb.toString(),person.getPerName(), person.getPerPassword(),Comm.PERSON_NORMAL);
	}

	@Override
	public Person queryPerson(String name, String password) {
		StringBuilder sb = new StringBuilder(" from Person p");
		sb.append(" where p.perName=? and p.perPassword=? and p.perStatus=? ");
		return (Person) super.query(sb.toString(), name, password,Comm.PERSON_NORMAL);
	}

	@Override
	public void updatePersonPasswordById(Person p) {
		StringBuilder sb = new StringBuilder(" update Person set perPassword=? where perId=? and perStatus=?");
		super.updateByHql(sb.toString(), p.getPerPassword(), p.getPerId(),Comm.PERSON_NORMAL);
	}

	@Override
	public List<Person> queryPersonByPos(String perName) {
		StringBuilder sb = new StringBuilder("from Person p");
		sb.append(" where p.perName=?");
		return super.find(sb.toString(), perName);
	}

	@Override
	public Limt queryLimitime() {
		String hql="from Limt l where 1=1";
		return (Limt) super.query(hql, null);
	}
}
