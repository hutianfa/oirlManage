package com.ltmcp.mobile.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.ExcRecordCondition;
import com.ltmcp.dao.BasDictDao;
import com.ltmcp.dao.ExcRecordDao;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Limt;
import com.ltmcp.entity.NewErrors;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.mobile.biz.ExcRecordServiceBiz;
import com.ltmcp.mobile.dao.ExcRecordServiceDao;
import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.impl.BaseServiceImpl;

public class ExcRecordServiceBizImpl extends BaseServiceImpl implements ExcRecordServiceBiz {

	private ExcRecordServiceDao excRecordServiceDao;

	private BasDictDao basDictDao;

	private SealedDao sealedDao;

	private Integer integer;

	@Override
	public Integer getTreatedCount() {
		Integer count = excRecordServiceDao.queryExcRecordByStatus(Comm.EXCEPTION_TREATED);
		if (null == count) {
			return 0;
		} else {
			return count;
		}
	}

	@Override
	public Integer getUnTreatedCount() {
		Integer count = excRecordServiceDao.queryExcRecordByStatus(Comm.EXCEPTION_UNTREATED);
		if (null == count) {
			return 0;
		} else {
			return count;
		}
	}

	@Override
	public PageBean searchExcRecords(ExcRecordCondition condition,PageBean pageBean) {
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		//ExcRecord���б������Ƿ�Ҫȥ�������쳣�ļ�¼
		List<ExcRecord> list = excRecordServiceDao.findExcRecords(pageBean.getCurentPage(), pageBean.getPageSize(), condition);
		Integer totalCount =  excRecordServiceDao.queryExcRecordByStatus_t(condition);

		return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	@Override
	public PageBean searchIllegalityDis_pianqu(ExcRecordCondition condition, PageBean pageBean) {
		if (null == pageBean)
		{
			pageBean = new PageBean();
		}
		List<position_inventory> list = null;
		list = excRecordServiceDao.findfindExcIllegalityDisPosition_inventory(pageBean.getCurentPage(),  pageBean.getPageSize(), condition);
		Integer totalCount =  excRecordServiceDao.queryExcRecordByStatus_y(condition);
		return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
		
	}
	@Override
	public PageBean searchIllegalityDis(ExcRecordCondition condition, PageBean pageBean) {
		if (null == pageBean)
		{
			pageBean = new PageBean();
		}
		//�ж�
		//1.����ǹ�˾
		List<Second_order> list = null;
		String sub = null;
		try 
		{
			sub = condition.getStaName().substring(2, 4);
		} catch (Exception e) 
		{
			
		}
		
		if("Ƭ��".endsWith(sub))//("xxxx�Ƴ�".equals(condition.getPerName()) || "���Ƴ�".equals(condition.getPerName()) || "�Ƴ�".equals(condition.getPerName()))//getname
		
		{
			list = excRecordServiceDao.findfindExcIllegalityDisSecond_order(pageBean.getCurentPage(),  pageBean.getPageSize(), condition);
			Integer totalCount =  excRecordServiceDao.queryExcRecordByStatus_s(condition);
			return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
			
		}else
		{
			//δ�ꡤ������Ƭ����
			return  null;
		}
		
		//2.������Ƭ��
		
	}

	/*
	 * (non-Javadoc)���쳣������Ϣ
	 * @see com.ltmcp.service.ExcRecordService#searchIllegality(com.ltmcp.condition.ExcRecordCondition, com.ltmcp.comm.PageBean)
	 */
	@Override
	public PageBean searchIllegality(ExcRecordCondition condition,PageBean pageBean) {
		
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		//��ѯ����δ�������쳣������
		List<NewErrors> list = excRecordServiceDao.findExcIllegality(pageBean.getCurentPage(),  pageBean.getPageSize(), condition);
		try {
			integer = excRecordServiceDao.queryNewErrorsByStatus_t(condition);//�õ��쳣����
		} catch (Exception e) {
			//e.printStackTrace();admin��ָ���쳣
		}
		return PageBean.getPageBean(list, integer, pageBean.getPageSize(),pageBean.getCurentPage());
	}
	/**
	 * ����ID��ѯվ����Ϣ
	 */
	@Override
	public List<Position> QueryPositionNameS(Integer posid) {
		List<Position> lpos = this.excRecordServiceDao.QueryPositionName(posid); 
		return lpos;
	}
	

	/**
	 * ��ѯ�Ѵ������˵���Ϣ
	 */
	@Override
	public PageBean selectExcRecords(ExcRecordCondition condition,PageBean pageBean) {
		
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		List<ExcRecord> list = excRecordServiceDao.findHasHandle(pageBean.getCurentPage(),pageBean.getPageSize(), condition);		
		Integer totalCount = excRecordServiceDao.queryExcRecordByStatus_t(Comm.EXCEPTION_TREATED,condition);
		
		return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	/**
	 * ��ѯ�Ѵ������˵���Ϣ
	 */
	@Override
	public PageBean newselectExcRecords(ExcRecordCondition condition,PageBean pageBean) {
		
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		List<NewErrors> list = excRecordServiceDao.newfindHasHandle(pageBean.getCurentPage(),pageBean.getPageSize(), condition);		
		Integer totalCount = excRecordServiceDao.queryExcRecordByStatus_t(Comm.EXCEPTION_TREATED,condition);
		
		return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
	}
	
	@Override
	public ExcRecord getExcRecord(Integer id) {
		if (null != id) {
			ExcRecord excRecord = new ExcRecord();
			excRecord.setExcId(id);
			return excRecordServiceDao.queryExcRecordByExcId(excRecord);
		}
		return null;

	}
	public ExcRecordServiceDao getExcRecordServiceDao() {
		return excRecordServiceDao;
	}

	public void setExcRecordServiceDao(ExcRecordServiceDao excRecordServiceDao) {
		this.excRecordServiceDao = excRecordServiceDao;
	}
	
	@Override
	public ExcRecord getExcRecordBySeaId(Integer seaId) {
		if (null == seaId) {
			return new ExcRecord();
		}
		ExcRecord excRecord = excRecordServiceDao.queryexcRecordBySeaId(seaId);
		return excRecord;
	}

	@Override
	public void changeExcRecordStatus(Integer id) {
		try {
			excRecordServiceDao.updateRecordStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BasDict> searchExceptionTypes() {
		return basDictDao.queryBasDictByType(Comm.BASDICT_EXCEPTION);
	}

	public BasDictDao getBasDictDao() {
		return basDictDao;
	}

	public void setBasDictDao(BasDictDao basDictDao) {
		this.basDictDao = basDictDao;
	}

	@Override
	public List<ExcRecord> findAllRecords(ExcRecordCondition condition) {
		return excRecordServiceDao.selectAllRecords(condition);
	}

	public SealedDao getSealedDao() {
		return sealedDao;
	}

	public void setSealedDao(SealedDao sealedDao) {
		this.sealedDao = sealedDao;
	}

	@Override
	public Map findTimeList(ExcRecordCondition condition) {
		double timeDiff = 0.0;// ʱ���
		double sum = 0.0;// ʱ�����ʱ��
		double parcent = 0.0;// ������ʱ���
		double avg = 0.0;// ƽ��ʱ��
		double parcent2 = 0.0;// �ٷֱ�
		double actualTime = 0.0;// ʵ��ʱ��
		List<Sealed> list = excRecordServiceDao.queryTimeList(condition);
		for (int i = 1; i < list.size(); i++) {
			timeDiff = (list.get(i).getFreeze().getFreTime().getTime() - list.get(i).getSeaTime().getTime()) / 1000;// �������˵���ʱ����ת������
			sum += timeDiff;// �ۼ�ʱ�����ܺ�
		}
		avg = sum / list.size();// ƽ��ʱ��
		Map map = new HashMap();
		ArrayList newList = new ArrayList<Sealed>();
		for (Sealed s : list) {
			actualTime = (s.getFreeze().getFreTime().getTime() - s.getSeaTime().getTime()) / 1000;
			if (actualTime > avg) {
				parcent = actualTime - avg;
				parcent2 = parcent / avg;
				// ʵ��ʱ��
				s.setActualTime(new java.text.DecimalFormat("0.00").format(actualTime / 3600));
				// ƽ��ʱ��
				s.setAvgTime(new java.text.DecimalFormat("0.00").format(avg / 3600));
				// �ٷֱ�
				s.setParcentTime(new java.text.DecimalFormat("0.00").format(parcent2));
				newList.add(s);
			}
		}
		map.put("newList", newList);
		return map;
	}

	/**
	 * ��ʱ�쳣 ����48Сʱ��ɵ��˵����쳣
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageBean findTimeOutList(ExcRecordCondition condition,PageBean pageBean) {
		double sealedTime = 0.0;
		double hours = 0.0;
		double systemTime = 0.0;
		double diff = 0.0;
		// ��ȡ��ⷽ��ʱ����Ϣ
		if (null == pageBean) {
			pageBean = new PageBean();
		}
		List<Sealed> list = excRecordServiceDao.queryTimeOutList(pageBean.getCurentPage(), pageBean.getPageSize(), condition);
		List arrList = new ArrayList();
		systemTime = System.currentTimeMillis();
		for (Sealed sea : list) {
			// ����ȡ��ʱ��ת��ΪСʱ
			sealedTime = sea.getSeaTime().getTime();
			diff = systemTime - sealedTime;
			// ����ȡ��ʱ�����ת����Сʱ
			hours = diff / 1000 / 60 / 60;
			if (hours > 48) {
				// ��������ת��ΪСʱ
				arrList.add(sea);
				sea.setTimeOut(new java.text.DecimalFormat("0.00").format(hours));
			}
		}
		Limt limt = sealedDao.queryLimitime();
		Integer timer = Integer.parseInt(limt.getStat());//ʱ��ɵ�
		
		Integer totalCount =sealedDao.querySealedCountByStatus(timer,Comm.WAYBILL_UNFINISHED);
		return PageBean.getPageBean(arrList, totalCount,pageBean.getPageSize(), pageBean.getCurentPage());
	}

	/**
	 * ���������д��excrecord ����ȥ
	 */

	@Override
	public void updateHandleMethod(String excText, Integer id) {
		excRecordServiceDao.updateHasHandle(excText, id);

	}
	/**
	 * ���������д��NewErrors ����ȥ
	 */

	@Override
	public void newupdateHandleMethod(String newexcText, Integer newid) {
		excRecordServiceDao.newupdateHasHandle(newexcText, newid);

	}

	

	
}