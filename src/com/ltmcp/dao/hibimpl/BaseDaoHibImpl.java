package com.ltmcp.dao.hibimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ltmcp.dao.BaseDao;
import com.ltmcp.entity.Position;

public class BaseDaoHibImpl extends HibernateDaoSupport implements BaseDao{

	@Override
	public List find(String hql, Object... param) {
		return super.getHibernateTemplate().find(hql,param);
	}

	@Override
	public List find(String hql,Integer currentPage,Integer pageSize,Object...param) {
		//可以使用查询缓存：Hibernate中Session 是Hibernate中的缓存对象 用此session操作数据库后会缓存返回的结果在session里面 当你再次操作数据
		//库的时候 如果session缓存里面有相应的值 则不用去与数据库交互直接返回结果.
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		this.setParam(query, param);
		return query.list();
	}

	public List findList(String hql,Object...param){
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		this.setParam(query, param);
		return query.list();
	}
	
	@Override
	public Object query(Class clazz, Serializable id) {
		return super.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Object query(String hql,Object...param) {
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		if(null!=param){
			for(int i=0;i<param.length;i++){
				 query.setParameter(i, param[i]);
			}
		}
		List list= query.list();
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * z设置参数
	 * @param query
	 * @param param
	 * @throws Exception
	 */
	private void setParameter(Query query,List param) throws Exception{
		if(param !=null && param.size()>0){
			try {
				for(int i=0;i<param.size(); i++){
					query.setParameter(i, param.get(i));
				}
			} catch (Exception e) {
				throw e;
			}
		}
	}
	/**
	 * 查询
	 * @param hql
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Object queryHQL(String hql,Object...param) {
		List list = null;
		Session session=this.openSession();
		try{
		Query query=session.createQuery(hql);
				 this.setParameter(query,null);
		list= query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Serializable insert(Object obj) {
		return super.getHibernateTemplate().save(obj);
	}

	public void insertcode(Object obj) {
		super.getSession().saveOrUpdate(obj);
	}
	
	@Override
	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(Object obj) {
		super.getHibernateTemplate().delete(obj);
	}

	@Override
	public int queryRowCount(String hql, Object... param) {
		Session session=this.openSession();//打开session
		Query query=session.createQuery(hql);//执行hql语句
		this.setParam(query, param);
		Long longCount=(Long) query.uniqueResult();
		return longCount.intValue();
	}
	
	public Object queryUniqueObject(String hql,Object...param){
		Session session=this.openSession();
	    Query query=session.createQuery(hql);
	    this.setParam(query, param);
	    return query.uniqueResult();
	}
	
	public int queryCountBySql(String sql){
		Session session=this.openSession();
		Query query= session.createSQLQuery(sql);
		Long longCount=(Long) query.uniqueResult();
		//System.out.println(longCount.intValue());
		return longCount.intValue();
		
	}
	//20170105
	public int queryCountBySql_co(String sql) {
		Session session=this.openSession();
		Query query= session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map map = null;
		List list = query.list();
		int count1 = 0;
		int count2 = 0;
		for(int i = 0; i<list.size(); i++)
		{
			 map = (Map) list.get(i);
		     Object ob1 = map.get("count1");
		     if(ob1 == null)
		     {
		    	 count1 = 0;
		     }else
		     {
		    	 count1 = Integer.parseInt(ob1.toString()); 
		     }
		     
		     Object ob2 = map.get("count2");
		     if(ob2 == null)
		     {
		    	 count2 = 0;
		     }else
		     {
		    	 count2 = Integer.parseInt(ob2.toString()); 
		     }
		     
			 //System.out.println(count1);
			// System.out.println(count2);
			
		}
		//测试总数
		int count_div = count1+count2;
		//System.out.println(count_div+":总数");
		return count_div;
		
	}
	/**
	 * 设置参数
	 * @param query
	 * @param param
	 */
	private void setParam(Query query,Object...param){
		if(null!=query&&param!=null){
			for(int i=0;i<param.length;i++){
				query.setParameter(i,param[i]);
			}
		}
	}

	@Override
	public Session openSession() {
		return super.getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
	
	public void updateBySql(String sql){
		Session session=this.openSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	public void updateByHql(String hql,Object...param){
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		this.setParam(query, param);
		query.executeUpdate();
	}
	public void updateByHqlBindBagCode(String hql,Object...param){
		Session session=this.openSession();
		Query query1=session.createSQLQuery(hql);
//		Query query=session.createQuery(hql);
//		query.setFirstResult(0);
//		query.setMaxResults(3);
		this.setParam(query1, param);
		query1.executeUpdate();
		//session.close();
	}
	
	
	public Integer updatePhoByHqlReInt(String hql,Object...param){
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		this.setParam(query, param);
		return query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(Object obj) {
		super.getHibernateTemplate().saveOrUpdate(obj);//没有数据才会执行新增，主键重复update
	}

	@Override
	public Integer returnByUpdate(String hql,Object...param) {
		Session session=this.openSession();
		Query query=session.createQuery(hql);
		this.setParam(query, param);
		return query.executeUpdate();
	}

	public Integer ceshiBieming(String string) {
		// TODO Auto-generated method stub
		Session session=this.openSession();
		Query query=session.createQuery(string);
		List<Map> list = query.list();
		for(Map map : list){
            System.out.println(map.get("count1")+"\t"+map.get("count1"));
        }
		return 1;
	}

	
	
	

}
