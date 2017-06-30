package com.ltmcp.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.ltmcp.condition.ThingCondition;
import com.ltmcp.entity.AreaThings;
import com.ltmcp.entity.AreaThingsTotal;
import com.ltmcp.entity.Things;
import com.ltmcp.service.ThingService;

public class ThingsAction extends BaseAction{

	private Things th;
	private Integer comId;
	private ThingCondition condition;
	private ThingService thingService;
	private String sortT;//排序标识
	private Integer currentPage;
	private AreaThings at;
	private AreaThingsTotal att;
	private Integer areaid;
	
	//封签库存情况
	public void listing(){
		List<Map<String,Object>> list = thingService.queryAreaThingsService(condition);
		super.outPrintJsonByArray(list);
	}


	/**
	 * 查询所有签封领用
	 * @return
	 */
	public void queryAreaThings(){
		List<Map<String, Object>> li = thingService.RetuenList(condition,currentPage); 
		if("1".equals(sortT)){//降序
			Collections.sort(li, new MapComparatorJ());
		} else if("0".equals(sortT)){//升序
			Collections.sort(li, new MapComparatorS());
		}else{
			Collections.sort(li, new MapComparatorJ());
		}
		
		if(li.size() > 13){
			super.outPrintJsonByArray(li.subList(0, 13));
		}else{
			super.outPrintJsonByArray(li);
		}
	}
	
	public void excel(){
		
	}
	
	
	
	/**
	 * 将list信息导出到excel中
	 * @throws WriteException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public void listToExcel(List<Map<String, Object>> list) throws WriteException, IOException, ParseException{
		
		String path = "D://excel//";
//		String path = File.separator+"home" + File.separator+"ltmcp"+ File.separator+"excel"+ File.separator;
		long FileName = System.currentTimeMillis();
		
		String fileName = path+FileName+".xls";
		
		File file = new File(path);
		// 假如fileName不存在，则创建
		if (!file.exists()) {
			file.mkdir();
		}
		
		WritableWorkbook wwb = null;
		// 创建可写入的Excel工作簿
		File file1 = new File(fileName);
		// 假如fileName不存在，则创建
		if (!file1.exists()) {
			file1.createNewFile();
		}
		WritableCellFormat headerFormat= null;
		WritableSheet ws = null;
		try {
			headerFormat = new WritableCellFormat();
	        //水平居中对齐
	        headerFormat.setAlignment(Alignment.CENTRE);
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file1);
			// 创建工作表
			ws = wwb.createSheet("page", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 要插入到的Excel表格的行号，默认从0开始
		Label  car		=   new Label(0, 0, "站点",headerFormat);
		Label sjnm 		=   new Label(1, 0, "签封总数",headerFormat);
		Label signum    = 	new Label(2, 0, "签封使用数",headerFormat);
		Label phonenum  = 	new Label(3, 0, "签封剩余数",headerFormat);
		/**
		 * 设置单元格大小
		 */
//		ws.setColumnView(0, "二维码".length() + 20);
//		ws.setColumnView(1, "序列号".length() + 11);
		/**
		 * 添加单元格
		 */
		ws.addCell(car);
		ws.addCell(sjnm);
		ws.addCell(signum);
		ws.addCell(phonenum);
		
		/**
		 * 从list中取值  并存入excel中
		 */
		for (int i = 0; i < list.size(); i++) {			
			ws.addCell(new Label(0, i + 1,"",headerFormat));
			ws.addCell(new Label(1, i + 1,"",headerFormat));
			ws.addCell(new Label(2, i + 1,"",headerFormat));
			ws.addCell(new Label(3, i + 1,"",headerFormat));			
			
			
//			DecimalFormat df = new DecimalFormat("0.00");
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//	        String start = list.get(i).getSeaTime().toString() ;
//	        String end = list.get(i).getFreeze().getFreTime().toString();	        
//	        String time = df.format((sdf.parse(end).getTime() - sdf.parse(start).getTime())/(1000*60*60.0))+"小时";			
//			ws.addCell(new Label(15,i + 1,time ,headerFormat));
			
		}		
		// 写进文档
		wwb.write();
		//excel文档已生成
		// 关闭Excel工作簿对象
		wwb.close();
		list.removeAll(list);
		
		 HttpServletResponse response = super.getResponse();
	        File file0 = new File(fileName);
	        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file0));
	        response.reset();
	        response.addHeader("Content-Disposition", "attachment;filename="+FileName+".xls");
	         
	        byte[] by = new byte[2048];
	        int len = 0;
	         
	        while((len=(inputStream.read(by)))>0){
	            response.getOutputStream().write(by,0,len);
	        }
	        inputStream.close();   
	        if(file1.isFile() && file1.exists()){
	            file1.delete();
	        }
	}
	
	//显示详情
	public void thingList(){
		List<Map<String, Object>> li = thingService.returnThingService(condition,currentPage);
				
		super.outPrintJsonByArray(li);
	}
	
	public void queryAreaTHingsTotal(){
		List<Map<String , Object>> list = thingService.queryAreaThingsTotal();
		super.outPrintJsonByArray(list);
	}
		

	/**
	 * 查詢所有站點
	 */
	public void queryArea(){
		List<Map<String,Object>> list = null;
		
		try {
			list = thingService.quertArea(areaid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.outPrintJsonByArray(list);
	}
	public Integer getAreaid() {
		return areaid;
	}


	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}


	public String Allthing(){
		return "Allthing";
	}
	
	//增加
	public void thingAdd(){
		try {
			thingService.insertAreaThingsService(at);
			
			thingService.insertAreaThingsTotal(at);
			
			super.getPringWriter().print(1);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(2);
		}
	}
	/**
	 * 增加新的签封
	 */
	public void insertAreaThings(){
		try {
			thingService.thingAddService(th);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			e.printStackTrace();
			super.getPringWriter().print(2);
		}
	}
	//修改
	public void thingUpdate(){
		try {
			thingService.thingUpdateService(th);
			super.getPringWriter().print(1);
		} catch (Exception e) {
			super.getPringWriter().print(2);
		}
	}	

	public static class MapComparatorS implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
	          
			  Integer s1 = (Integer) o1.get("used");  
			  Integer s2 = (Integer) o2.get("used");
			  

			  if(s1 > s2) {  
	             return -1;  
	                }
			  if(s1 < s2){  
	              return 1;  
	                }
			  if(s1 == s2){
				  return 0;
	                }
			  return 0;
		  		}
		}
	
	public static class MapComparatorJ implements Comparator<Map<String, Object>> {
		  @Override
		  public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			  
			  Integer s1 = (Integer) o1.get("used");  
			  Integer s2 = (Integer) o2.get("used");
			  
	             if(s1 > s2) {  
	                return 1;  
	                	}
	             if(s1 < s2){  
	                return -1;  
	                	}
	             if(s1 == s2){  
	            	 return 0;
	   	             }  
	             return 0;
		  		}
		}
	
	public ThingCondition getCondition() {
		return condition;
	}
	public void setCondition(ThingCondition condition) {
		this.condition = condition;
	}
	public ThingService getThingService() {
		return thingService;
	}
	public void setThingService(ThingService thingService) {
		this.thingService = thingService;
	}
	public Things getTh() {
		return th;
	}
	public void setTh(Things th) {
		this.th = th;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getSortT() {
		return sortT;
	}

	public void setSortT(String sortT) {
		this.sortT = sortT;
	}

	public void setThingCondition(ThingCondition condition) {
		this.condition = condition;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public AreaThings getAt() {
		return at;
	}

	public void setAt(AreaThings at) {
		this.at = at;
	}


	public AreaThingsTotal getAtt() {
		return att;
	}


	public void setAtt(AreaThingsTotal att) {
		this.att = att;
	}

}
