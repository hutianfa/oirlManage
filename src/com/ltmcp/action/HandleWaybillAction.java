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
 * 运单处理的Action
 * 
 * @author Administrator
 * 
 */
public class HandleWaybillAction extends BaseAction {

	private SealedService sealedService;
	private SealedCondition condition; // 查询条件
	private PageBean pageBean; // 页码控制
	private Integer id; // 根据运单ID查询
	private Sealed sealed;// 根据ID查询返回的结果
	private FreezeService freezeService;
	private Freeze freeze;
	private ExcRecord excRecord;
	private ExcRecordService excRecordService;
	List<Sealed> list = new ArrayList<Sealed>();

	
	/**
	 * 获取运单信息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		pageBean = sealedService.searchWaybillByCondition(condition, pageBean);
		//将pageBean对象封装在list中
		list = pageBean.getList();
		
		return super.returnToViewList(pageBean);
	}

	/**
	 * 将当前内容导出
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws Exception
	 */
	public void toExcel(List<Sealed> list) throws WriteException, IOException, ParseException{
		pageBean = sealedService.searchWaybillByCondition(condition, pageBean);
		//将pageBean对象封装在list中
		list = pageBean.getList();
		
		listToExcel(list);
	}
	/*
	 * 获取全部运单信息
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
	 * 将list信息导出到excel中
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
		Label  car		=   new Label(0, 0, "车牌号",headerFormat);
		Label sjnm 		=   new Label(1, 0, "司机姓名",headerFormat);
		Label signum    = 	new Label(2, 0, "签封口数",headerFormat);
		Label phonenum  = 	new Label(3, 0, "电话号码",headerFormat);
		Label company   = 	new Label(4, 0, "所属公司",headerFormat);
		Label area      = 	new Label(5, 0, "所属区",headerFormat);
		Label sfperson  = 	new Label(6, 0, "施封人",headerFormat);
		Label sfaddress = 	new Label(7, 0, "施封点",headerFormat);
		Label sftime    = 	new Label(8, 0, "施封时间",headerFormat);
		Label sfcode    = 	new Label(9, 0, "施封内码",headerFormat);
		Label sfimg     = 	new Label(10, 0, "施封图片",headerFormat);
		Label jfperson  = 	new Label(11,0, "解封人",headerFormat);
		Label jfaddress = 	new Label(12,0, "解封点",headerFormat);		
		Label jftime    = 	new Label(13,0, "解封时间",headerFormat);
		Label jfcode    = 	new Label(14,0, "解封外码",headerFormat);
		Label jfimg     = 	new Label(15,0, "解封图片",headerFormat);
		Label transtime = 	new Label(16,0, "运输时长",headerFormat);
		Label status    = 	new Label(17,0, "状态",headerFormat);
		Label power     = 	new Label(18,0, "授权人",headerFormat);
		Label powtips   = 	new Label(19,0, "授权原因",headerFormat);
		Label type      = 	new Label(20,0, "客户类型",headerFormat);
		Label youType   = 	new Label(21,0, "油品类型",headerFormat);
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
		 * 从list中取值  并存入excel中
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
	        String time = df.format((sdf.parse(end).getTime() - sdf.parse(start).getTime())/(1000*60*60.0))+"小时";			
			ws.addCell(new Label(16,i + 1,time ,headerFormat));
			
			String str = "";
			if(list.get(i).getSeaStatus() == 0){
				str = "完成";
			}else if(list.get(i).getSeaStatus() == 1){
				str = "运输中";
			}else if(list.get(i).getSeaStatus() == 2){
				str = "异常";
			}
			ws.addCell(new Label(17,i + 1,str,headerFormat));
			
			ws.addCell(new Label(18,i + 1,list.get(i).getFreeze().getPowCodName() ,headerFormat));
			
			String tips = "";
			if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 72){
				tips = "误操作";
			}else if(list.get(i).getFreeze().getPowerTips() != null && list.get(i).getFreeze().getPowerTips() == 73){
				tips = "操作不合格";
			}
			ws.addCell(new Label(19,i + 1,tips ,headerFormat));
			
			String tp = "";
			if(list.get(i).getTag() != null  && list.get(i).getTag() ==70 ){
				tp = "自有加油站";
			}else if(list.get(i).getTag() != null  && list.get(i).getTag() ==71){
				tp = "外购客户";
			}
			ws.addCell(new Label(20,i + 1,tp ,headerFormat));
			
			String youpinName ;
			if(list.get(i).getYouPinName() != null ){
				youpinName = list.get(i).getYouPinName();
			}else{
				youpinName = "无油品信息";
			}
			ws.addCell(new Label(21,i + 1,youpinName ,headerFormat));
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

	/**
	 * 获取单个运单信息
	 * 
	 * @return
	 */
	public String detailed() {
		try {
			sealed = sealedService.getWaybillById(id); // 未完成
			System.out.println(id);
			if (null != sealed) {
				/**
				 * 非必须
				 */
				freeze = freezeService.getFreezeBySealedId(sealed.getSeaId());
				excRecord = excRecordService.getExcRecordBySeaId(sealed.getSeaId());//异常信息
			} else {
				System.out.println("跳转错误");
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置停留5秒在执行return super.returnToViewDetailed(sealed);
		try {
			Thread.sleep(40000);
			System.out.println("停留4秒");
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
			super.outPrintJsonByObject(pageBean, ignoreAttribute);// 输出json
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public String timestampToString(Timestamp now) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式
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
			super.getPringWriter().print("转换失败!" + e.getMessage());
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
