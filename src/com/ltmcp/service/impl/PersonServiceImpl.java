package com.ltmcp.service.impl;

import java.sql.Timestamp;
import java.util.List;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PersonCondition;
import com.ltmcp.dao.PersonDao;
import com.ltmcp.dao.PositionDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;
import com.ltmcp.service.PersonService;
import com.ltmcp.util.MD5;

public class PersonServiceImpl  extends BaseServiceImpl  implements PersonService {

	private PersonDao personDao;
	
	private PositionDao positionDao;
	
	@Override
	public void addPerson(Person addPerson){
		if(null!=addPerson){
			Admin admin=(Admin) super.getSessionObject(Comm.CURRENT_ADMIN);
			addPerson.setAdmin(admin);
			addPerson.setPerPassword(MD5.md5crypt(addPerson.getPerPassword()));
			addPerson.setAdminCreateName(admin.getAdmName());
			addPerson.setCompany(admin.getCompany());
			addPerson.setPerCreateDate(new Timestamp(System.currentTimeMillis()));
			addPerson.setPerStatus(Comm.PERSON_NORMAL);
			personDao.insertPerson(addPerson);
		}
	}


	@Override
	public boolean findPeronExtises(Person addPerson) {
		return personDao.queryPersonExits(addPerson);
	}
	

	@Override
	public Person getPerson(Integer id) {
		if(null!=id){
			Person person=new Person();
			person.setPerId(id);
			return personDao.queryPersonById(person);
		}
		return null;
	}

	@Override
	public PageBean searchPersons(PersonCondition condition, PageBean pageBean) {
			if(null==pageBean){
				pageBean=new PageBean();
			}
			List<Person> list= null;
			Integer totalCount= null;
			try {
				list=personDao.findPersons(pageBean.getCurentPage(),pageBean.getPageSize(), condition);
				totalCount=personDao.queryPersonCountByCondition(condition);
			} catch (Exception e) {
				e.printStackTrace();
				}
			return PageBean.getPageBean(list, totalCount,pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}



	public PositionDao getPositionDao() {
		return positionDao;
	}



	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}



	@Override
	public List<Position> searchPositions() {
		return positionDao.findPositions();
	}




	@Override
	public void delPerson(Integer id) throws Exception{
		if(null==id){
			return;
		}
		if(personDao.personIdIsExit(id)==false){
			throw new Exception();
		}
		Person person=new Person();
		person.setPerId(id);
		personDao.deletePerson(person);
	}




	@Override
	public void modify(Integer id,String phoneNum,String pwd) throws Exception {
		if(null==id){
			return;
		}
		if(personDao.personIdIsExit(id)==false){
			throw new Exception();
		}
		Person p=new Person();
		p.setPerId(id);
		
		Person person = personDao.queryPersonById(p);
		
		person.setPerPhone(phoneNum);
		person.setPerPassword(MD5.md5crypt(pwd));
		personDao.updatePerson(person);
	}




	
}