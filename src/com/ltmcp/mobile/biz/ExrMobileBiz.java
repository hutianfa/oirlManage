package com.ltmcp.mobile.biz;

import java.util.List;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;

public interface ExrMobileBiz {

	//��ʾ�쳣�б�(��ʱ�쳣)
	public List<Sealed> queryPow(String name,String password,int currPage);
	//��ʾ�쳣�б�(��ʱ�쳣)
	public List<Sealed> queryTimeOutList(String name,String password,int currPage);
	//��ʾ�쳣�б�(����쳣)
	public List<ExcRecord> queryFreExrLi(String name,String password,int currPage);
	//��ʾ��ϸ����
	public ExcRecord queryDeatil(ExcRecord exr);
	//�����쳣
	public void moditfyEXrSta(ExcRecord exr);
	//������Ȩ�б�
	public void moditfyEmpo(Integer id);
}
