package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;

public interface DWMReportDao {
	
	//��ȡվ��
	public List<Position> getPosi();
	//��ȡ��
	public List getPeop();
	//��ȡ��
	public List getCar();
	
	//��ȡ�ձ���
	public List<Sealed> d(String types);
	//��ȡ�ܱ���
	public List<Sealed> w(String types);
	//��ȡ�±���
	public List<Sealed> m(String types);
	
	
}
