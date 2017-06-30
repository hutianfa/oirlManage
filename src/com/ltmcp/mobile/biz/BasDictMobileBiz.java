package com.ltmcp.mobile.biz;

import java.util.List;

import com.ltmcp.entity.BasDict;

public interface BasDictMobileBiz {
	public List<BasDict> searchExceptionList(String name,String password);
	public List<BasDict> searchOilPinList(String name,String password);
	public List<BasDict> searchFixList(String name,String password);
	public List<BasDict> searchTagList(String name, String password);
	public List<BasDict> searchEmList(String name, String password);
}
