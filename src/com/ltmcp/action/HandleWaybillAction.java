package com.ltmcp.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.SealedCondition;
import com.ltmcp.entity.ExcRecord;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.FreezeService;
import com.ltmcp.service.SealedService;
import com.ltmcp.util.UrlAndPathComm;

/**
 * �˵������Action
 * 
 * @author Administrator
 * 
 */
public class HandleWaybillAction extends BaseAction {

	private SealedService sealedService;
	private SealedCondition condition; // ��ѯ����
	private PageBean pageBean; // ҳ�����
	private Integer id; // �����˵�ID��ѯ
	private Sealed sealed;// ����ID��ѯ���صĽ��
	private FreezeService freezeService;
	private Freeze freeze;
	private ExcRecord excRecord;
	private ExcRecordService excRecordService;
	List<Sealed> list = new ArrayList<Sealed>();

	
	/**
	 * ��ȡ�˵���Ϣ�б�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		pageBean = sealedService.searchWaybillByCondition(condition, pageBean);
		//��pageBean�����װ��list��
		list = pageBean.getList();
		
		return super.returnToViewList(pageBean);
	}

	/**
	 * ����ǰ���ݵ���
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws Exception
	 */
	public void toExcel(List<Sealed> list) throws WriteException, IOException, ParseException{
		pageBean = sealedService.searchWaybillByCondition(condition, pageBean);
		//��pageBean�����װ��list��
		list = pageBean.getList();
		
		listToExcel(list);
	}
	/*
	 * ��ȡȫ���˵���Ϣ
	 */
	public void allList() throws Exception {
		try {
			list = sealedService.findAllWaybillByCondition(condition);
			if(list != null){
				listToExcel(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��list��Ϣ������excel��
	 * @throws WriteException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public void listToExcel(List<Sealed> list) throws WriteException, IOException, ParseException{
		
		String path = UrlAndPathComm.comm+"excel"+File.separator;
		
		long FileName = System.currentTimeMillis();
		
		String fileName = path+FileName+".xls";
		
		File file = new File(path);
		// ����fileName�����ڣ��򴴽�
		if (!file.exists()) {
			file.mkdir();
		}
		
		WritableWorkbook wwb = null;
		// ������д���Excel������
		File file1 = new File(fileName);
		// ����fileName�����ڣ��򴴽�
		if (!file1.exists()) {
			file1.createNewFile();
		}
		
		WritableCellFormat headerFormat= null;
		WritableSheet ws = null;
		try {
			headerFormat = new WritableCellFormat();
	        //ˮƽ���ж���
	        headerFormat.setAlignment(Alignment.CENTRE);
			// ��fileNameΪ�ļ���������һ��Workbook
			wwb = Workbook.createWorkbook(file1);
			// ����������
			ws = wwb.createSheet("page", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
		Label  car		=   new Label(0, 0, "���ƺ�",headerFormat);
		Label sjnm 		=   new Label(1, 0, "˾������",headerFormat);
		Label signum    = 	new Label(2, 0, "ǩ�����",headerFormat);
		Label phonenum  = 	new Label(3, 0, "�绰����",headerFormat);
		Label company   = 	new Label(4, 0, "������˾",headerFormat);
		Label area      = 	new Label(5, 0, "������",headerFormat);
		Label sfperson  = 	new Label(6, 0, "ʩ����",headerFormat);
		Label sfaddress = 	new Label(7, 0, "ʩ���",headerFormat);
		Label sftime    = 	new Label(8, 0, "ʩ��ʱ��",headerFormat);
		Label sfcode    = 	new Label(9, 0, "ʩ������",headerFormat);
		Label sfimg     = 	new Label(10, 0, "ʩ��ͼƬ",headerFormat);
		Label jfperson  = 	new Label(11,0, "�����",headerFormat);
		Label jfaddress = 	new Label(12,0, "����",headerFormat);		
		Label jftime    = 	new Label(13,0, "���ʱ��",headerFormat);
		Label jfcode    = 	new Label(14,0, "�������",headerFormat);
		Label jfimg     = 	new Label(15,0, "���ͼƬ",headerFormat);
		Label transtime = 	new Label(16,0, "����ʱ��",headerFormat);
		Label status    = 	new Label(17,0, "״̬",headerFormat);
		Label power     = 	new Label(18,0, "��Ȩ��",headerFormat);
		Label powtips   = 	new Label(19,0, "��Ȩԭ��",headerFormat);
		Label type      = 	new Label(20,0, "�ͻ�����",headerFormat);
		Label youType   = 	new Label(21,0, "��Ʒ����",headerFormat);
		/**
		 * ���õ�Ԫ���С
		 */
//		ws.setColumnView(0, "��ά��".length() + 20);
//		ws.setColumnView(1, "���к�".length() + 11);
		/**
		 * ��ӵ�Ԫ��
		 */
		ws.addCell(car);
		ws.addCell(sjnm);
		ws.addCell(signum);
		ws.addCell(phonenum);
		ws.addCell(company);
		ws.addCell(area);
		ws.addCell(sfperson);
		ws.addCell(sfaddress);
		ws.addCell(sftime);
		ws.addCell(sfcode);
		ws.addCell(sfimg);
		ws.addCell(jfperson);
		ws.addCell(jfaddress);
		ws.addCell(jftime);
		ws.addCell(jfcode);
		ws.addCell(jfimg);
		ws.addCell(transtime);
		ws.addCell(status);
		ws.addCell(power);
		ws.addCell(powtips);
		ws.addCell(type);
		ws.addCell(youType);
		/**
		 * ��list��ȡֵ  ������excel��
		 */
		for (int i = 0; i < list.size(); i++) {	
			ws.addCell(new Label(0, i + 1,list.get(i).getCar().getCarFlapper(),headerFormat));
			ws.addCell(new Label(1, i + 1,"",headerFormat));
			ws.addCell(new Label(2, i + 1,list.get(i).getCar().getCarFixFlag().toString() ,headerFormat));
			ws.addCell(new Label(3, i + 1,"" ,headerFormat));
			ws.addCell(new Label(4, i + 1,list.get(i).getCompany().getComName() ,headerFormat));
			ws.addCell(new Label(5, i + 1,"" ,headerFormat));
			ws.addCell(new Label(6, i + 1,list.get(i).getPerson().getPerName() ,headerFormat));
			ws.addCell(new Label(7, i + 1,list.get(i).getPosition().getPosName() ,headerFormat));
			ws.addCell(new Label(8, i + 1,list.get(i).getSeaTime().toString() ,headerFormat));
			ws.addCell(new Label(9, i + 1,list.get(i).getDimensionalBarCode().getFreeze_content() ,headerFormat));
			ws.addCell(new Label(10, i + 1,list.get(i).getSeaImg() ,headerFormat));
			ws.addCell(new Label(11,i + 1,list.get(i).getFreeze().getPerson().getPerName() ,headerFormat));
			ws.addCell(new Label(12,i + 1,list.get(i).getFreeze().getPosition().getPosName() ,headerFormat));
			ws.addCell(new Label(13,i + 1,list.get(i).getFreeze().getFreTime().toString() ,headerFormat));
			ws.addCell(new Label(14,i + 1,list.get(i).getFreeze().getDimensionalBarCode().getUnfreeze_content() ,headerFormat));
			ws.addCell(new Label(15,i + 1,list.get(i).getFreeze().getFreImg() ,headerFormat));
			
			
			DecimalFormat df = new DecimalFormat("0.00");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        String start = list.get(i).getSeaTime().toString() ;
	        String end = list.get(i).getFreeze().getFreTime().toString();	        
	        String time = df.format((sdf.parse(end).getTime() - sdf.parse(start).getTime())/(1000*60*60.0))+"Сʱ";			
			ws.addCell(new Label(16,i + 1,time ,headerFormat));
			
			String str = "";
			if(list.get(i).getSeaStatus() == 0){
				str = "���";
			}else if(list.get(i).getSeaStatus() == 1){
				str = "������";
			}else if(list.get(i).getSeaStatus() == 2){
				str = "�쳣";
			}
			ws.addCell(new Label(17,i + 1,str,headerFormat));
			
			ws.addCell(new Label(18,i + 1,list.get(i).getFreeze().getPowCodName() ,headerFormat));
			
			String tips = "";
			if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 72){
				tips = "�����";
			}else if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 73){
				tips = "�������ϸ�";
			}
			ws.addCell(new Label(19,i + 1,tips ,headerFormat));
			
			String tp = "";
			if(list.get(i).getTag() != null  && list.get(i).getTag() ==70 ){
				tp = "���м���վ";
			}else if(list.get(i).getTag() != null  && list.get(i).getTag() ==71){
				tp = "�⹺�ͻ�";
			}
			ws.addCell(new Label(20,i + 1,tp ,headerFormat));
			
			String youpinName ;
			if(list.get(i).getYouPinName() != null ){
				youpinName = list.get(i).getYouPinName();
			}else{
				youpinName = "����Ʒ��Ϣ";
			}
			ws.addCell(new Label(21,i + 1,youpinName ,headerFormat));
		}		
		// д���ĵ�
		wwb.write();
		//excel�ĵ�������
		// �ر�Excel����������
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

	/**
	 * ��ȡ�����˵���Ϣ
	 * 
	 * @return
	 */
	public String detailed() {
		try {
			sealed = sealedService.getWaybillById(id); // δ���
			System.out.println(id);
			if (null != sealed) {
				/**
				 * �Ǳ���
				 */
				freeze = freezeService.getFreezeBySealedId(sealed.getSeaId());
				excRecord = excRecordService.getExcRecordBySeaId(sealed.getSeaId());//�쳣��Ϣ
			} else {
				System.out.println("��ת����");
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//����ͣ��5����ִ��return super.returnToViewDetailed(sealed);
		try {
			Thread.sleep(40000);
			System.out.println("ͣ��4��");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sealed);
		return super.returnToViewDetailed(sealed);
	}

	public String tonowlist() {
		return "tonowlist";
	}

	public void list_json() {
		pageBean = sealedService.searchWaybillByCondition(condition, pageBean);
		try {
			String[] ignoreAttribute = new String[] { "car", "freeze",
					"person", "position", "company", "excRecords", "freezes",
					"sealed" };
			super.outPrintJsonByObject(pageBean, ignoreAttribute);// ���json
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public String timestampToString(Timestamp now) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �����ʽ
		String str = df.format(now);
		return str;
	}

	public void seaJson() {
		List<Sealed> list = sealedService.findSeaAndFre(condition);
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		
		try {
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				
					map.put("seaId", list.get(i).getSeaId());
					map.put("seaStatus", list.get(i).getSeaStatus());
					map.put("comName", list.get(i).getCompany().getComName());
					map.put("seaTime",this.timestampToString(list.get(i).getSeaTime()));
					map.put("dimensionalBarCode", list.get(i).getDimensionalBarCode().getFreeze_content());
					map.put("dimensionalBarCodeun", list.get(i).getDimensionalBarCode().getUnfreeze_content());
					map.put("perTrueName", list.get(i).getPerson().getPerTrueName());
					map.put("perName", list.get(i).getPerson().getPerName());
					map.put("carFlapper", list.get(i).getCar().getCarFlapper());
				
			
					if (list.get(i).getFreeze() != null) {
						map.put("freId", list.get(i).getFreeze().getFreId());
						map.put("freStatus", list.get(i).getFreeze().getFreStatus());
						map.put("frePerName",list.get(i).getFreeze().getPerson().getPerName());
						map.put("freTrueName",list.get(i).getFreeze().getPerson().getPerTrueName());
						map.put("freTime",this.timestampToString(list.get(i).getFreeze().getFreTime()));
					}
			
				
				if (list.get(i).getPosition() != null) {
					map.put("posName", list.get(i).getPosition().getPosName());
					if (list.get(i).getFreeze() != null) {
					  map.put("frePosName", list.get(i).getFreeze().getPosition().getPosName());
					}
				}
				jsonList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			super.outPrintJsonByArray(jsonList, null);
		} catch (IOException e) {
			super.getPringWriter().print("ת��ʧ��!" + e.getMessage());
		}
	}

	public SealedService getSealedService() {
		return sealedService;
	}

	public void setSealedService(SealedService sealedService) {
		this.sealedService = sealedService;
	}

	public SealedCondition getCondition() {
		return condition;
	}

	public void setCondition(SealedCondition condition) {
		this.condition = condition;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sealed getSealed() {
		return sealed;
	}

	public void setSealed(Sealed sealed) {
		this.sealed = sealed;
	}

	public List<Sealed> getList() {
		return list;
	}

	public void setList(List<Sealed> list) {
		this.list = list;
	}

	public FreezeService getFreezeService() {
		return freezeService;
	}

	public void setFreezeService(FreezeService freezeService) {
		this.freezeService = freezeService;
	}

	public Freeze getFreeze() {
		return freeze;
	}

	public void setFreeze(Freeze freeze) {
		this.freeze = freeze;
	}

	public ExcRecord getExcRecord() {
		return excRecord;
	}

	public void setExcRecord(ExcRecord excRecord) {
		this.excRecord = excRecord;
	}

	public ExcRecordService getExcRecordService() {
		return excRecordService;
	}

	public void setExcRecordService(ExcRecordService excRecordService) {
		this.excRecordService = excRecordService;
	}

}
