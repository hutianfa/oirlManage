package com.ltmcp.service;

import java.util.List;

import com.ltmcp.entity.Position;

public interface BaseService {
	/**
	 * 获取Session中的对象
	 * @param name
	 * @return
	 */
	public Object getSessionObject(String name);

}
