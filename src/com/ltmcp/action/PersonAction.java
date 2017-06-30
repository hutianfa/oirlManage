package com.ltmcp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PersonCondition;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.PersonService;
import com.ltmcp.service.SealedService;
import com.ltmcp.util.RegexTool;

public class PersonAction extends BaseAction{
	
	private Integer id; //Ա��ID
	
	private String pwd;
	
	private PersonCondition condition; //��ѯ�б�ʱ������
	
	private Person person;//�ö����������ϸҳ��
	
	private Person addPerson; //�ö���Ϊ��Ӷ���
	
	private PageBean pageBean; //ҳ����ƶ���
	
	private String phoneNum;//�ֻ���
	
	private PersonService personService;
	
	private SealedService sealedService;
	
	private List<Person> list=new ArrayList<Person>();

	private List<Position> positions=new ArrayList<Position>();
	
	private List<Sealed> sealeds=new ArrayList<Sealed>();
	/**
	 * �����Ա
	 * @return
	 */
	public void add(){
		
		try {
			if(null != addPerson){
				if(this.personValidation(addPerson) == false){
					
					super.getPringWriter().print(1);
					
				}else{
					
					if(personService.findPeronExtises(addPerson) == false){
						
						personService.addPerson(addPerson);
						super.getPringWriter().print(0);
						
					}else{
						
						super.getPringWriter().print(-1);
						
					}					
				}
			}else{
				super.getPringWriter().print(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(1);
		}
	}
	
	/**
	 * ��֤�����Ϣ
	 * @param person
	 * @return
	 */
	public boolean personValidation(Person person){
		//��֤�û���(����)
		if("".equals(person.getPerName())||null==person.getPerName()){
			return false;
		}else{
			if(RegexTool.regexString("(^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$)",person.getPerName())==false){return false;}
		}
		//��֤����(����)
		if("".equals(person.getPerPassword())||null==person.getPerPassword()){
			return false;
		}else{
			if(RegexTool.regexString("(^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$)",person.getPerPassword())==false){return false;}
		}
		//��֤��ʵ����(����)
		if(null==person.getPerTrueName()||"".equals(person.getPerTrueName())){
			return false;
		}
		//��֤Ա�����(����)
		if(null==person.getPerCode()||"".equals(person.getPerCode())){return false;}
		
		//��֤Ա��λ�ã����
		if(null==person.getPosition().getPosId()){return false;}
		
		if(null!=person.getPerPhone()&&!"".equals(person.getPerPhone())){
			if(RegexTool.regexString("(^1\\d{10}$)",person.getPerPhone())==false){return false;}
		}
		
//		if(null!=person.getPerEmail()&&!"".equals(person.getPerEmail())){
//			if(RegexTool.regexString("(^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$)",person.getPerEmail())==false){return false;}
//		}

		return true;
	}
	
	
	
	
	/**
	 * ��ȡ��Ա��ϸ��Ϣ
	 * @return
	 */
	public String detailed(){
		if(null==id){
			return "error";
		}
		person=personService.getPerson(id);
		if(null==person){return "error";}
		pageBean=sealedService.searchByPersonId(pageBean,id);
		sealeds=pageBean.getList();
		return super.returnToViewDetailed(person);
	}
	
	/**
	 * ��ȡ��Ա�б�
	 * @return
	 */
	public String list(){
		pageBean=personService.searchPersons(condition,pageBean);
		list=pageBean.getList();
		positions=personService.searchPositions();
		return super.returnToViewList(pageBean);
	}
	
	public void del(){
		try {
			if(null!=id){
				personService.delPerson(id);
				super.getResponse().getWriter().print(0);
			}else{
				super.getResponse().getWriter().print(1);
			}
		} catch (Exception e) {
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	
	public void update() throws Exception{
		if(null!=id){

			personService.modify(id, phoneNum,pwd);
			super.getResponse().getWriter().print(0);
		}else{
			super.getResponse().getWriter().print(1);
		}
		
	}
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonCondition getCondition() {
		return condition;
	}

	public void setCondition(PersonCondition condition) {
		this.condition = condition;
	}

	public Person getPerson() {
		return person;
	}

	public SealedService getSealedService() {
		return sealedService;
	}

	public void setSealedService(SealedService sealedService) {
		this.sealedService = sealedService;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(Person addPerson) {
		this.addPerson = addPerson;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<Person> getList() {
		return list;
	}

	public void setList(List<Person> list) {
		this.list = list;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Sealed> getSealeds() {
		return sealeds;
	}

	public void setSealeds(List<Sealed> sealeds) {
		this.sealeds = sealeds;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}