package com.ltmcp.mobile.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.comm.Shoufa_personComm;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.dao.ExcRecordDao;
import com.ltmcp.dao.hibimpl.BaseDaoHibImpl;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.mobile.dao.ExcRecordServiceDao;

public class ExcRecordServiceDaoImpl extends BaseDaoHibImpl implements ExcRecordServiceDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<position_inventory> findfindExcIllegalityDisPosition_inventory(Integer currentPage, Integer pageSize, ExcRecordCondition erc) 
	{
		//System.out.println(erc.getPerName()+",还有一个发货人的id（片区的id）："+erc.getAreaid()+","+erc.getStaName());
		StringBuilder sb = new StringBuilder("from position_inventory pi where pi.pianqu_id= "+"'"+erc.getAreaid()+"'"+" and pi.position_name="+"'"+erc.getStaName()+"'");
		sb.append(" order by time desc ");
		sb.append("");//test时间
		List<position_inventory> pilistr= null;
		try
		{
			pilistr = super.find(sb.toString(), currentPage, pageSize);//镇压警告
		} catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return pilistr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Second_order> findfindExcIllegalityDisSecond_order(Integer currentPage, Integer pageSize, ExcRecordCondition erc) 
	{
		// TODO Auto-generated method stub
		//根据id找到发货人
		//System.out.println(erc.getPerName());
		
		//StringBuilder sb = new StringBuilder("from Second_order so where so.second_fh_person= "+"'"+erc.getPerName()+"'"+"and so.receive_sh_address="+"'"+erc.getStaName()+"'");//条件
		StringBuilder sb = new StringBuilder("from Second_order so where so.receive_sh_address="+"'"+erc.getStaName()+"'");
		// 新添的
		//condition(erc, sb);	//暂时省略这里的时间条件	
		sb.append(" order by fhtime desc ");
		sb.append("");//test时间
		List<Second_order> solisy = null;
		try 
		{
			solisy = super.find(sb.toString(), currentPage, pageSize);//镇压警告
		} catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return solisy;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ExcRecord> findExcRecords(Integer currentPage,Integer pageSize, ExcRecordCondition erc) {
		StringBuilder sb = new StringBuilder("from ExcRecord er where er.excStatus= "+ Comm.EXCEPTION_UNTREATED);
		// 新添的
		condition(erc, sb);		
		sb.append(" order by excDate desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}

	/*
	 * 查询非法封签(non-Javadoc)
	 * @see com.ltmcp.dao.ExcRecordDao#findExcIllegality(java.lang.Integer, java.lang.Integer, com.ltmcp.condition.ExcRecordCondition)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewErrors> findExcIllegality(Integer currentPage,Integer pageSize, ExcRecordCondition erc) {
		StringBuilder sb = new StringBuilder("from NewErrors ne where ne.status= "+ Comm.EXCEPTION_UNTREATED);
		try {
			NewCondition(erc, sb);
		} catch (Exception e) {
			//e.printStackTrace();admin的空指针异常
		}
		
		sb.append(" order by time desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}
	
	/**
	 * 根据ID查询一个站点
	 * @param erc
	 * @param sb
	 */
	@Override
	public List<Position> QueryPositionName(Integer posid) {
		NewErrors ne = new NewErrors();
		String hql = "from Position p where p.posId ="+ne.getPosid()+" ";
		List poss = new ArrayList();
		poss.add(posid);
		List<Position> plist = null;
		try {
			plist = (List<Position>) this.queryHQL(hql, poss);
			//这儿只返回一条数据 不是一个list集合 还有可能返回null  null不能强制转换的 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plist;
	}
	private void NewCondition(ExcRecordCondition erc, StringBuilder sb){
		if(null !=erc){
			//System.out.println(erc);
			int  comID = 88;
			try
			{
				comID = erc.getComId();
				//System.out.println("comID:"+comID);
			}catch(Exception e) 
			{
				//不处理
			}
				sb.append(" and ne.comid =" + comID);
				//System.out.println("打印公司id："+comID);
			}
		if(null !=erc)
			//System.out.println("进入判断站点id："+erc.getSite());
		{
			//System.out.println(erc);
			int  posID = 0;
			try {
				if(0 == erc.getSite())
				{
					//sb.append(" and ne.posid =" + posID);
				}else
				{
					//System.out.println("有站点哦，站点id是："+erc.getSite());
					posID = erc.getSite();
					sb.append(" and ne.posid =" + erc.getSite());
				}
			} catch (Exception e)
			{
				
			}
	    }
			
			//异常类型
			if(erc.getExcType() != null )
			{
				if(erc.getExcType() == 1)
				{
					//sb.append(" and ne.re = " + "'jf-(非法封签)'" );
					sb.append(" and ne.re = " + "'jf-(二维码未注册)'" );
				}else if(erc.getExcType() == 0)
				{
					//sb.append(" and ne.re = " + "'sf-(非法封签)'" );
					sb.append(" and ne.re = " + "'sf-(二维码未注册)'" );
				}else if(erc.getExcType() == 2)
				{
					sb.append(" and ne.re = " + "'jf-(封签未施封)'" );
				}else
				{
					sb.append("");
				}
			
			//作业点的级联			
			if(erc.getPosid() != null && !"".equals(erc.getPosid())){
				sb.append(" and ne.posid = "+ erc.getPosid());
			}			
			if ( !"".equals(erc.getBeginTime()) && erc.getBeginTime() != null &&  "".equals(erc.getEndTime()) || erc.getEndTime() == null) {
				sb.append(" and ne.time > " + "'" + erc.getBeginTime() + "'");
			}
			if (!"".equals(erc.getEndTime()) && erc.getEndTime() != null && "".equals(erc.getBeginTime()) || erc.getBeginTime() == null) {
				sb.append(" and ne.time < " + "'" + erc.getEndTime() + "'");
			}			
			if (!"".equals(erc.getBeginTime()) && erc.getBeginTime() != null && !"".equals(erc.getEndTime()) && erc.getEndTime() !=null) {
				sb.append(" and (ne.time between " + "'"+ erc.getBeginTime() + "'" + "and " + "'"+ erc.getEndTime() + "'" + ")");
				//System.out.println(erc.getBeginTime()+"; and :"+erc.getEndTime());
			}					
			
		}else{
//			if(AdminComm.getAdminPower() != 1){
//				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin());
//			}
		}
	}
	
	private void condition(ExcRecordCondition erc, StringBuilder sb) {
		
		if (null != erc) {
			
			if(AdminComm.getAdminPower() == 1 && erc.getComId() != null){				
				sb.append(" and er.company.comId=" + erc.getComId() );				
			} else if(AdminComm.getAdminPower() != 1 ){				
				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin());
			}
			//异常类型
			if(erc.getExcType() != null ){
				sb.append(" and er.basDict.dictId = " + erc.getExcType() );
			}
			if(erc.getCarFlapper() != null && !"".equals(erc.getCarFlapper())){
				sb.append(" and er.freeze.car.carFlag like '%" + erc.getCarFlapper() +"%'");
			}
			//异常产生操作人
//			if(erc.getPerName() != null && !"".equals(erc.getPerName())){
//				sb.append(" and er.freeze.person.perName like '%"+erc.getPerName()+"%'");
//			}
			if(erc.getPerId() != null && !"".equals(erc.getPerId())){
				sb.append(" and er.freeze.person.perId ="+erc.getPerId());
			}			
			//作业点的级联
			if(!"".equals(erc.getAreaid()) && erc.getAreaid() != null ){
				sb.append(" and er.freeze.position.areaid = "+ erc.getAreaid());
			}
			if(erc.getPosid() != null && !"".equals(erc.getPosid())){
				sb.append(" and er.freeze.position.posId = "+ erc.getPosid());
			}			
			if ( !"".equals(erc.getBeginTime()) && erc.getBeginTime() != null &&  "".equals(erc.getEndTime()) || erc.getEndTime() == null) {
				sb.append(" and er.excDate > " + "'" + erc.getBeginTime() + "'");
			}
			if (!"".equals(erc.getEndTime()) && erc.getEndTime() != null && "".equals(erc.getBeginTime()) || erc.getBeginTime() == null) {
				sb.append(" and er.excDate < " + "'" + erc.getEndTime() + "'");
			}			
			if (!"".equals(erc.getBeginTime()) && erc.getBeginTime() != null && !"".equals(erc.getEndTime()) && erc.getEndTime() !=null) {
				sb.append(" and (er.excDate between " + "'"+ erc.getBeginTime() + "'" + "and " + "'"+ erc.getEndTime() + "'" + ")");
			}						
		} else {
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin());
			}
		}
	}

	@Override
	public ExcRecord queryExcRecordByExcId(ExcRecord er) {
		StringBuilder sb = new StringBuilder("from ExcRecord er ");
		sb.append(" where er.excId =? ");
		ExcRecord exc = new ExcRecord();
		try {
			exc = (ExcRecord) super.query(sb.toString(), er.getExcId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exc;
	}

	@Override
	public Integer queryExcRecordByType(Integer type) {
		StringBuilder sb = new StringBuilder("select count(excId) from ExcRecord where basDict.dictId = ?");
		return super.queryRowCount(sb.toString(), type);
	}
	


	/**
	 * 根据非法异常查询异常条数
	 */
	@Override
	public Integer queryNewErrorsByStatus_t(ExcRecordCondition erc) {//这里的ExcRecordCondition erc为空？？？
		
		StringBuilder sb = new StringBuilder("select count(ne.id) from NewErrors ne where 1=1");
		if(erc != null){
			int  comID = 88;
			try {
				comID = erc.getComId();
			} catch (Exception e) {
			
			}
			sb.append(" and ne.comid=" + comID);
			//这里要添加shoufa_person 的信息     AdminComm.getAdminPower() = 1 这里等于1是高级管理员
//			if("".endsWith(AdminComm.getAdminName()) || null == AdminComm.getAdminName())
//			{
//				sb.append(" and ne.comid=" + 4);
//			}else
//			{
//				if(AdminComm.getAdminPower() != 1)
//				{
//					sb.append(" and ne.comid=" + AdminComm.getComIdByAdmin());//在admin中根据admin
//					System.out.println("得到公司id："+AdminComm.getComIdByAdmin()+";得到登录名："+AdminComm.getAdminName());
//				}else if(AdminComm.getAdminPower() == 1 && erc.getComId() != null){
//					sb.append(" and ne.comid=" + erc.getComId() );
//					
//				}
//			}
//			else{//否则都改权限为2
//				sb.append(" and ne.comid=" + 4);
//			}
			//异常类型
			if(null !=erc)
				//System.out.println("进入判断站点id："+erc.getSite());
			{
				//System.out.println(erc);
				int  posID = 0;
				try {
					if(0 == erc.getSite())
					{
						//sb.append(" and ne.posid =" + posID);
					}else
					{
						//System.out.println("有站点哦，站点id是："+erc.getSite());
						posID = erc.getSite();
						sb.append(" and ne.posid =" + erc.getSite());
					}
				} catch (Exception e)
				{
					
				}
		    }
			if(erc.getExcType() != null ){
				if(erc.getExcType() == 1){
					
					sb.append(" and ne.re = " + "'jf-(二维码未注册)'" );
				}else if(erc.getExcType() == 0){
					
					sb.append(" and ne.re = " + "'sf-(二维码未注册)'" );
				}else if(erc.getExcType() == 2){
					sb.append(" and ne.re = " + "'jf-(封签未施封)'" );
				}
			}
			//判断开始和结束查询的时间是否是""与null：开始时间为空查全部，结束时间空位，二者都为空，二者都不空
			if (!"".equals(erc.getBeginTime())  && erc.getBeginTime() != null &&  "".equals(erc.getEndTime()) || erc.getEndTime() == null) {
				
				sb.append(" and ne.time > " + "'" + erc.getBeginTime() + "'");
				
			}

			if ("".equals(erc.getBeginTime()) || erc.getBeginTime() == null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null ) {
				
				sb.append(" and ne.time < " + "'" + erc.getEndTime() + "'");
				
			}
			//常用的走这里进入if
			if (!"".equals(erc.getBeginTime()) && erc.getBeginTime() != null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null) {
				
				sb.append(" and (ne.time between " + "'"+ erc.getBeginTime() + "'" + "and " + "'"+ erc.getEndTime() + "'" + ")");
			}
		}else{

//			if(AdminComm.getAdminPower() != 1){
//			
//				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin() );
//			}
		}
		//返回integer类型
		return super.queryRowCount(sb.toString());
	}
	//片区分发
	@Override
	public Integer queryExcRecordByStatus_s(ExcRecordCondition condition) {
		//StringBuilder sb = new StringBuilder("select count(er.id) from Second_order er where er.second_fh_person= " +"'"+condition.getPerName()+"'"+" and er.receive_sh_address="+"'"+condition.getStaName()+"'");
		StringBuilder sb = new StringBuilder("select count(er.id) from Second_order er where er.receive_sh_address="+"'"+condition.getStaName()+"'");
		int sta1 = 0;
		try 
		{
			sta1 = super.queryRowCount(sb.toString());
		} catch (Exception e) 
		{
			//System.out.println(e);
			//e.printStackTrace();
		}
		return sta1;
	}
	//二级分发
	@Override
	public Integer queryExcRecordByStatus_y(ExcRecordCondition condition) {
		StringBuilder sb = new StringBuilder("select count(er.id) from position_inventory er where er.pianqu_id= " +"'"+condition.getAreaid()+"'"+" and er.position_name="+"'"+condition.getStaName()+"'");
		return super.queryRowCount(sb.toString());
	}
	

	@Override
	public Integer queryExcRecordByStatus_t(ExcRecordCondition erc) {
		StringBuilder sb = new StringBuilder("select count(er.excId) from ExcRecord er where er.excStatus= "+ Comm.EXCEPTION_UNTREATED);
		
		if(erc != null){
			
			if(AdminComm.getAdminPower() != 1){
				
				sb.append(" and er.company.comId= "+ AdminComm.getComIdByAdmin());
				
			}else if(AdminComm.getAdminPower() == 1 && erc.getComId() != null){
				
				sb.append(" and er.company.comId=" + erc.getComId() );
				
			}
			
			if (!"".equals(erc.getBeginTime())  && erc.getBeginTime() != null &&  "".equals(erc.getEndTime()) || erc.getEndTime() == null) {
				
				sb.append(" and er.excDate > " + "'" + erc.getBeginTime() + "'");
				
			}

			if ("".equals(erc.getBeginTime()) || erc.getBeginTime() == null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null ) {
				
				sb.append(" and er.excDate < " + "'" + erc.getEndTime() + "'");
				
			}
			
			if (!"".equals(erc.getBeginTime()) && erc.getBeginTime() != null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null) {
				
				sb.append(" and (er.excDate between " + "'"+ erc.getBeginTime() + "'" + "and " + "'"+ erc.getEndTime() + "'" + ")");
			}
			
		}else{
				if(AdminComm.getAdminPower() != 1){
				
				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin() );
				
			}
		}
		
		return super.queryRowCount(sb.toString());
	}
	
	
	@Override
	public Integer queryExcRecordByStatus(Integer status) {
		StringBuilder sb = new StringBuilder("select count(excId) from ExcRecord where excStatus=? ");

		if(AdminComm.getAdminPower() != 1){
			sb.append(" and company.comId= "+ AdminComm.getComIdByAdmin());
		}
		
		return super.queryRowCount(sb.toString(), status);
	}
	
	
	
	/**
	 * 查询已处理了的
	 */
	@Override
	public List<ExcRecord> findHasHandle(Integer currentPage,Integer pageSize, ExcRecordCondition erc) {
//		StringBuilder sb = new StringBuilder("from ExcRecord er where 1=1 ");
//		// 新添的
//		sb.append(" and er.excStatus=?");
//		condition(erc, sb);
//		sb.append(" order by excDate desc ");
//		return super.find(sb.toString(), currentPage, pageSize,Comm.EXCEPTION_TREATED);

		StringBuilder sb = new StringBuilder("from ExcRecord er where er.excStatus= "+ Comm.EXCEPTION_TREATED);
		// 新添的
		condition(erc, sb);		
		sb.append(" order by excDate desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}
	/**
	 * 查询已处理了的
	 */
	@Override
	public List<NewErrors> newfindHasHandle(Integer currentPage,Integer pageSize, ExcRecordCondition erc) {

		StringBuilder sb = new StringBuilder("from NewErrors ne where ne.status= "+ Comm.EXCEPTION_TREATED);
		// 新添的
		NewCondition(erc, sb);		
		sb.append(" order by time desc ");
		return super.find(sb.toString(), currentPage, pageSize);
	}
	
	
	@Override
	public Integer queryExcRecordByStatus_t(Integer status,ExcRecordCondition erc) {
		StringBuilder sb = new StringBuilder("select count(er.excId) from ExcRecord er where er.excStatus= "+ status);
		

		// 新添的
		condition(erc, sb);		
		sb.append(" order by excDate desc ");
		return super.queryRowCount(sb.toString());
		
		
		
		
//			if(erc != null){
//			
//			if(AdminComm.getAdminPower() == 1 && erc.getComId() != null){
//				
//				sb.append(" and er.company.comId=" + erc.getComId() );
//				
//			} else if(AdminComm.getAdminPower() != 1){
//				
//				sb.append(" and er.company.comId= "+ AdminComm.getComIdByAdmin());
//				
//			}
//			
//			if ( !"".equals(erc.getBeginTime())  && erc.getBeginTime() != null &&  "".equals(erc.getEndTime()) || erc.getEndTime() == null) {
//				
//				sb.append(" and er.excDate > " + "'" + erc.getBeginTime() + "'");
//				
//			}
//
//			if ("".equals(erc.getBeginTime()) || erc.getBeginTime() == null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null) {
//				
//				sb.append(" and er.excDate < " + "'" + erc.getEndTime() + "'");
//				
//			}
//			
//			if (!"".equals(erc.getBeginTime()) && erc.getBeginTime() != null && !"".equals(erc.getEndTime()) && erc.getEndTime() != null) {
//				
//				sb.append(" and (er.excDate between " + "'"+ erc.getBeginTime() + "'" + "and " + "'"+ erc.getEndTime() + "'" + ")");
//			}
//			
//		}else{
//				if(AdminComm.getAdminPower() != 1){
//				
//				sb.append(" and er.company.comId=" + AdminComm.getComIdByAdmin() );
//				
//			}
//		}
		
	}
	

	@Override
	public Integer queryExcRecordCountByCondition(ExcRecordCondition condition) {
		StringBuilder sb = new StringBuilder("select count(excId) from ExcRecord er where 1=1 ");
		condition(condition, sb);
		return super.queryRowCount(sb.toString());
	}

	@Override
	public ExcRecord queryexcRecordBySeaId(Integer seaId) {
		StringBuffer sb = new StringBuffer("from ExcRecord e ");
		sb.append(" where e.sealed.seaId=? ");
		
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and  e.company.comId="+ AdminComm.getComIdByAdmin());
		} 
		
		return (ExcRecord) super.query(sb.toString(), seaId);
	}

	@Override
	public void updateRecordStatus(Integer id) {
		StringBuffer sb = new StringBuffer("update ExcRecord e set e.excStatus=? ");
		sb.append("  where  e.excId=? ");
		
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and e.company.comId= "+AdminComm.getComIdByAdmin());
		}
		
		super.updateByHql(sb.toString(), Comm.EXCEPTION_TREATED, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExcRecord> selectAllRecords(ExcRecordCondition condition) {
		StringBuilder sb = new StringBuilder("from ExcRecord er where 1=1 ");
		condition(condition, sb);
		sb.append(" order by excDate desc ");
		return super.find(sb.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> queryTimeList(ExcRecordCondition condition) {
		StringBuilder sb = new StringBuilder(" from Sealed s where 1=1");
		if (condition != null) {
			if (condition.getDictName() != null && !"".equals(condition.getDictName())) {
				sb.append(" and s.position.posName like '%"+ condition.getDictName() + "%'");
			}
			if(condition.getStaName() != null && !"".equals(condition.getStaName())){
				sb.append(" and s.freeze.position.posName like '%" + condition.getStaName() + "%'");
			}
						
			if(AdminComm.getAdminPower() != 1 && null ==condition.getComId()){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			} else if(AdminComm.getAdminPower() == 1 && condition.getComId() != null ){
				sb.append(" and s.company.comId="+condition.getComId()+"");
			}
			
			
		} else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			}
		}

		sb.append(" and s.seaStatus=?");
		sb.append(" and s.freeze.freStatus=?");
		sb.append(" order by s.seaTime desc ");
		return super.findList(sb.toString(), Comm.WAYBILL_COMPLETED,Comm.WAYBILL_COMPLETED);
	}

	/**
	 * 超时异常：超过48小时
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sealed> queryTimeOutList(Integer currentPage, Integer pageSize,ExcRecordCondition condition) {
		StringBuilder sb = new StringBuilder(" from Sealed s where 1=1");
		if (condition != null) {
			if (condition.getPerName() != null && !"".equals(condition.getPerName())) {
				sb.append(" and s.person.perName like '"+ condition.getPerName() + "%'");
			}
			if(AdminComm.getAdminPower() != 1){
				if(null!=condition.getComId()){
					sb.append(" and s.company.comId="+condition.getComId()+"");
				}else{
					sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
				}
			} else if(AdminComm.getAdminPower() == 1 && null!=condition.getComId()){
				sb.append(" and s.company.comId="+condition.getComId()+"");
			}
		}else{
			if(AdminComm.getAdminPower() != 1){
				sb.append(" and s.company.comId="+AdminComm.getComIdByAdmin());
			}
		}
		sb.append(" and s.seaStatus=?");
		sb.append(" order by s.seaTime desc");
		return super.find(sb.toString(), currentPage, pageSize,Comm.WAYBILL_UNFINISHED);
	}

	/**
	 * 将异常处理意见保存到excrecord表
	 */

	@Override
	public void updateHasHandle(String excText, Integer id) {
		StringBuilder sb = new StringBuilder("update ExcRecord e set e.excHandleMethod=?,e.excStatus=?,e.comName=?");
		sb.append("  where  e.excId=? ");
		
		if(AdminComm.getAdminPower() != 1){
			sb.append(" and e.company.comId= "+ AdminComm.getComIdByAdmin());
		} 
		
		super.updateByHql(sb.toString(), excText, Comm.EXCEPTION_TREATED,"".equals(AdminComm.getAdminTrueName()) ? AdminComm.getAdminName() : AdminComm.getAdminTrueName(),id);
	}
	/**
	 * 将异常处理意见保存到NewErrors表
	 */

	@Override
	public void newupdateHasHandle(String newexcText, Integer newid) {

	 NewErrors errors = (NewErrors) super.query(NewErrors.class, newid);
	 
	 if(!newexcText.equals("") && newid != null){
		 errors.setOpinion(newexcText);
		 errors.setDetialName(AdminComm.getAdminName());
		 errors.setStatus("0");
	 }
	 
	 super.saveOrUpdate(errors);
	 
	
	}

	
}
