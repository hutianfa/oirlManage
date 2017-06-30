package com.ltmcp.mobile.biz;
import com.ltmcp.entity.Dbc_BagCodeBind;
public interface Dbc_BagCodeBindBiz {
/**
 * 检测袋子二维码是否已经存在
 */
	public boolean checkBagcodeExist(String bagCode);
	/***
	 * 箱子二维码检测在dbc_bagcodebind表中是否存在
	 * @param caseCode 箱子二维码
	 * @return true or fasle
	 */
	public boolean checkCasecodeExist(String caseCode);
	/***
	 * 当重复扫描袋子二维码，并且还没有关联到箱子二维码的时候，判断重复扫袋子二维码
	 * @param bagCode 袋子二维码
	 * @return
	 */
	public boolean multipleScan(String bagCode);
}
