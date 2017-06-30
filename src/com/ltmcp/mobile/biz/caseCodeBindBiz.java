package com.ltmcp.mobile.biz;
import com.ltmcp.entity.Inventor_BoxCode;
public interface caseCodeBindBiz {
/**
 * 根据app端传入的箱子二维码来判断该箱子二维码是否已经存在
 *@param 箱子二维码
 */
	public boolean checkCaseCodeExist(String caseCose);
	/**
	 * 箱子二维码绑定袋子之后写入库存表中
	 * @param 箱子表类为对象
	 */
	public void saveCaseCode(Inventor_BoxCode ib);
}
