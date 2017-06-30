package com.ltmcp.mobile.biz;

import java.util.List;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Sealed;

public interface ExrMobileBiz {

	//显示异常列表(近时异常)
	public List<Sealed> queryPow(String name,String password,int currPage);
	//显示异常列表(超时异常)
	public List<Sealed> queryTimeOutList(String name,String password,int currPage);
	//显示异常列表(解封异常)
	public List<ExcRecord> queryFreExrLi(String name,String password,int currPage);
	//显示详细内容
	public ExcRecord queryDeatil(ExcRecord exr);
	//处理异常
	public void moditfyEXrSta(ExcRecord exr);
	//过滤授权列表
	public void moditfyEmpo(Integer id);
}
