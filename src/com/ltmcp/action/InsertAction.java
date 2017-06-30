package com.ltmcp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PetroCondition;
import com.ltmcp.entity.Petrol;
import com.ltmcp.service.InputPetServcie;
import com.ltmcp.util.UrlAndPathComm;

public class InsertAction extends BaseAction{

	private Petrol petrol;
	private InputPetServcie inputPetServcie;
	private PageBean pageBean;
	private PetroCondition condition;
	private String time;
	List<Petrol> li = new ArrayList<Petrol>();
	
	
	private File excel;

    private String excelFileName;

    private String excelContentType;
	
	
	
	
	
	//录入信息
	public void inputPet(){
		try {
			if("0号柴油".equals(petrol.getSea_oilpin())){
				petrol.setPetrol_sea_id(0);
			}else if("97号汽油".equals(petrol.getSea_oilpin())){
				petrol.setPetrol_sea_id(1);
			}else if("93号汽油".equals(petrol.getSea_oilpin())){
				petrol.setPetrol_sea_id(2);
			}
			petrol.setTime(new Timestamp(System.currentTimeMillis()));
			petrol.setComName(inputPetServcie.queryComNmByComid(AdminComm.getComIdByAdmin()));
			petrol.setCom(AdminComm.getComIdByAdmin());
			
			inputPetServcie.save(petrol);
			
			super.getPringWriter().print(0);
			
		} catch (Exception e) {
			super.getPringWriter().print(1);
			e.printStackTrace();
		}
	}
	//录入信息列表
	public String list(){
		try {
			pageBean = inputPetServcie.queryPetrol(pageBean,condition);
			//将pageBean对象封装在list中
			li = pageBean.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.returnToViewList(pageBean);
	}
	//油损信息 excel 导入
	
	public  void fileToBD() {
		
		String dirs = UrlAndPathComm.comm+"you_excel"+File.separator;
		
		String path = dirs+"ltmcp_you_excel.xls";
		
		
		if(excel == null)
        	return ;
		
		
		File dir = new File(dirs);
		
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		try {
			File w = new File(path);
			
			//穿件输入流
			FileInputStream fis = new FileInputStream(excel);
			//创建输出流
			FileOutputStream fos = new FileOutputStream(w);
			
			//字节数组
			byte[] b = new byte[1024];
			int len = 0;
			
			while((len = fis.read(b)) != -1){
				fos.write(b, 0, len);
			}
			fis.close();
	        fos.close();
	        
	        readExcel(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * 解析excel
	 * @param path
	 */
	public void readExcel(String path){
        Sheet sheet;
        Workbook book = null;
        Cell petrol_total,petrol_loss,carFlaper,time,sea_oilpin,tiyou,shouyou;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        int comid = AdminComm.getComIdByAdmin();
        try { 
            //t.xls为要读取的excel文件名
            book= Workbook.getWorkbook(new File(path)); 
            
            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(0); 
            
            int row = sheet.getRows();
            int col = sheet.getColumns();
            
            System.out.println("row:"+row);
            
            for(int i = 1;i<row;i++){
            	//获取每一行的单元格 
            	time = sheet.getCell(0,i);
            	tiyou = sheet.getCell(3,i);
            	shouyou = sheet.getCell(5,i);
            	sea_oilpin = sheet.getCell(8,i);
            	carFlaper = sheet.getCell(17,i);
            	petrol_total = sheet.getCell(24,i);
            	petrol_loss = sheet.getCell(27,i);
            	
            	String oilpin = sea_oilpin.getContents();
            	
            	Petrol pp = new Petrol();
            	pp.setCarFlaper(carFlaper.getContents());
            	pp.setCom(comid);
            	pp.setComName(shouyou.getContents().substring(0, 10));
            	pp.setPetrol_loss(Double.parseDouble(petrol_total.getContents()) - Double.parseDouble(petrol_loss.getContents()));
            	pp.setPetrol_total(Double.parseDouble(petrol_total.getContents()));
            	pp.setShengyu(Double.parseDouble(petrol_loss.getContents()));
            	pp.setSea_oilpin(oilpin);
            	pp.setTiyou(tiyou.getContents());
            	pp.setShouyou(shouyou.getContents());
            	
            	pp.setTime(new Timestamp(sdf.parse(time.getContents()).getTime()));
            	
            	if("0号 车用柴油(Ⅳ)".equals(oilpin)){
    				pp.setPetrol_sea_id(0);
    			}else if("97号 车用汽油(Ⅳ)".equals(oilpin)){
    				pp.setPetrol_sea_id(1);
    			}else if("93号 车用汽油(Ⅳ)".equals(oilpin)){
    				pp.setPetrol_sea_id(2);
    			}
            	
            	inputPetServcie.save(pp);
            }
            
            File delFile = new File(path);
            if(delFile.exists()){
            	delFile.delete();
            }
            
        }
        catch(Exception e)  { 
        	e.printStackTrace();
        }finally{
        	book.close(); 
        }
	}
	
	
	
	public Petrol getPetrol() {
		return petrol;
	}
	public void setPetrol(Petrol petrol) {
		this.petrol = petrol;
	}
	public InputPetServcie getInputPetServcie() {
		return inputPetServcie;
	}
	public void setInputPetServcie(InputPetServcie inputPetServcie) {
		this.inputPetServcie = inputPetServcie;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public PetroCondition getCondition() {
		return condition;
	}
	public void setCondition(PetroCondition condition) {
		this.condition = condition;
	}
	public List<Petrol> getLi() {
		return li;
	}
	public void setLi(List<Petrol> li) {
		this.li = li;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getExcelContentType() {
		return excelContentType;
	}
	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}
}
