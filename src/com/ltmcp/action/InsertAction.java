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
	
	
	
	
	
	//¼����Ϣ
	public void inputPet(){
		try {
			if("0�Ų���".equals(petrol.getSea_oilpin())){
				petrol.setPetrol_sea_id(0);
			}else if("97������".equals(petrol.getSea_oilpin())){
				petrol.setPetrol_sea_id(1);
			}else if("93������".equals(petrol.getSea_oilpin())){
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
	//¼����Ϣ�б�
	public String list(){
		try {
			pageBean = inputPetServcie.queryPetrol(pageBean,condition);
			//��pageBean�����װ��list��
			li = pageBean.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.returnToViewList(pageBean);
	}
	//������Ϣ excel ����
	
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
			
			//����������
			FileInputStream fis = new FileInputStream(excel);
			//���������
			FileOutputStream fos = new FileOutputStream(w);
			
			//�ֽ�����
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
	 * ����excel
	 * @param path
	 */
	public void readExcel(String path){
        Sheet sheet;
        Workbook book = null;
        Cell petrol_total,petrol_loss,carFlaper,time,sea_oilpin,tiyou,shouyou;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        int comid = AdminComm.getComIdByAdmin();
        try { 
            //t.xlsΪҪ��ȡ��excel�ļ���
            book= Workbook.getWorkbook(new File(path)); 
            
            //��õ�һ�����������(ecxel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
            sheet=book.getSheet(0); 
            
            int row = sheet.getRows();
            int col = sheet.getColumns();
            
            System.out.println("row:"+row);
            
            for(int i = 1;i<row;i++){
            	//��ȡÿһ�еĵ�Ԫ�� 
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
            	
            	if("0�� ���ò���(��)".equals(oilpin)){
    				pp.setPetrol_sea_id(0);
    			}else if("97�� ��������(��)".equals(oilpin)){
    				pp.setPetrol_sea_id(1);
    			}else if("93�� ��������(��)".equals(oilpin)){
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
