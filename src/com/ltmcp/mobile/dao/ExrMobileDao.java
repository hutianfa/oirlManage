package com.ltmcp.mobile.dao;

import java.util.List;

import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;

public interface ExrMobileDao {

	//��ѯ��ʱ�쳣�б�
	public List<Sealed> exrListTime(Integer comId,int currPage);
	//��ѯ��ʱ�쳣
	public List<Sealed> exrListTimeout(Integer comId,int currPage);
	//��ѯ����쳣
	public List<ExcRecord> exrListFre(Integer comId,int currPage);
	//��ȡ�쳣��ϸ����
	public ExcRecord detailed(ExcRecord exr); 
	//�����쳣
	public void updateExr(ExcRecord exr);
	
	//������Ȩ�˵��Ķ�ȡ״̬
	public void gaibianEmpo(Integer id);
}
