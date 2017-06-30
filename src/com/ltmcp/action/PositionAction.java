package com.ltmcp.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.entity.Province;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.PositionService;

public class PositionAction extends BaseAction {
    private PageBean pageBean;

    private Integer id;

    private Position position;

    private PositionCondition condition;

    private PositionService positionService;

    private PositionExamine positionExamine;// 需要添加的对象

    private HttpServletRequest request;

    private List<Position> list = new ArrayList<Position>();

    private List<Sealed> sealeds = new ArrayList<Sealed>();

    private Integer dictId;

    private String posName;

    private String cardNumber;
    
    private String phoneMac;

    private Area area;
    
    private Province com_a;
    
    private Company coma;

	/**
	 * 配置跳转页面
	 * 
	 */
    private DimensionalBarCode dimensionalBarCode;

    private File filedName;


    private String uploadContentType; // 文件的内容类型

    private String uploadFileName; // 上传文件名

    /**
     * 获取位置详细内容
     * 
     * @return
     */
    public String detailed() {
        position = positionService.getPosition(id);
        if (null == position) {
            return "error";
        }
        pageBean = positionService.searchSealedByPositionId(id, pageBean);
        sealeds = pageBean.getList();
        return super.returnToViewDetailed(position);
    }

    /**
     * 根据条件查询位置信息列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        pageBean = positionService.searchPosition(condition, pageBean);
        list = pageBean.getList();
        return super.returnToViewList(pageBean);
    }

    /**
     * 配置跳转页面
     * 
     */
    public String addCardNumber() {
        return SUCCESS;
    }

    /**
     * 添加新的站点
     */
    public void addNewPosition() {
        try {
        	//管理员操作，可以添加公司
        	if(AdminComm.getAdminPower() == 1){//如果是管理员
        		//先判断公司是否存在
        		Integer comaid = positionService.queryCom_a(coma.getComName());
        		
    			if(comaid == -1){//不存在则保存对象，将id返回
    				//保存公司
    				Company cc = new Company();
    				cc.setComName(coma.getComName());
    				Integer cid = positionService.addCom_a(cc);
    				
    				//先判断areaid是否存在
                	Integer areaid = positionService.queryArea(area.getArea_name());
                	  
                	if(areaid == -1){//不存在 保存
                		Area a = new Area();
                    	a.setArea_name(area.getArea_name());
                    	a.setCom_id(cid);
                    	
                    	Integer aid =  positionService.addArea(a);
                    	
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
                	}else{//存在 就取id
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
                	}
    			}else{//存在就保存返回的id
    				//先判断areaid是否存在
                	Integer areaid = positionService.queryArea(area.getArea_name());
                	  
                	if(areaid == -1){//不存在 保存
                		Area a = new Area();
                    	a.setArea_name(area.getArea_name());
                    	a.setCom_id(comaid);
                    	
                    	Integer aid =  positionService.addArea(a);
                    	
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
                	}else{//存在 就取id
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
                	}
    			}
    				        		
        	}else{//如果不是管理员
        		//先判断areaid是否存在
            	Integer areaid = positionService.queryArea(area.getArea_name());
            	  
            	if(areaid == -1){//不存在 保存
            		Area a = new Area();
                	a.setArea_name(area.getArea_name());
                	a.setCom_id(AdminComm.getComIdByAdmin());
                	
                	Integer aid =  positionService.addArea(a);
                	
                	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
            	}else{//存在 就取id
                	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
            	}
        	}
   
            super.getPringWriter().print(0);

        } catch (Exception e) {
            super.getPringWriter().print(1);
            e.printStackTrace();
        }
    }

    //id查询 分公司
    public void comaById(){
    	try {
    		List<Company> list = positionService.findCom_aById();
    		
    		List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
    		for(Company c :list){
    			Map<String,Object> map= new HashMap<String,Object>();
    			map.put("comName", c.getComName());
    			map.put("comid", c.getComId());
    			li.add(map);
    		}
    		super.outPrintJsonByArray(li);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    //id查询区
    public void areaById(){
    	
    	List<Area> list = positionService.findAreaById(id);
    	List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		for(Area a :list){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("areaName",a.getArea_name() );
			map.put("areaid",a.getId());
			li.add(map);
		}
		super.outPrintJsonByArray(li);
    }
    //id查询站点
    public void posiById(){
	
    	List<Position> list = positionService.findPositionById(id);
    	
    	List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		for(Position p :list){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("posName",p.getPosName());
			map.put("posid",p.getPosId());
			li.add(map);
		}
		super.outPrintJsonByArray(li);
    }
    
    
    public void personByPosid(){
    	List<Person> list = positionService.findpesonByposId(id);
    	List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		for(Person p :list){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("perName",p.getPerTrueName() );
			map.put("perid",p.getPerId());
			li.add(map);
		}
		super.outPrintJsonByArray(li);
    }
    /**
     * 修改站点信息
     */
    public void updateNewPos() {
        try {
            position = positionService.getPosition(id);
            
            if (position != null) {
            	
                position.setPosName(posName);
                position.setPosCardNumber(cardNumber);
                
                if(!"".equals(phoneMac)){
                	position.setPhoneMac(phoneMac+",");	
                }
                positionService.updateNewPosition(position);
                
                super.getPringWriter().print(0);
            }

        } catch (Exception e) {
            super.getPringWriter().print(1);
            e.printStackTrace();
        }
    }

    /**
     * 将excel表中的数据导入mysql数据库
     */
    @SuppressWarnings("deprecation")
    public void importCodeByMysql() {
        try {
            InputStream is = new FileInputStream(filedName);
            String name = UUID.randomUUID().toString();
            // 保存上传的文件到服务器
            this.SaveFileFromInputStream(is, ServletActionContext.getRequest().getRealPath(File.separator + "excel") + File.separator,name + ".xls");
            // 上传之后的文件路径（包括文件名）
            String realPath = ServletActionContext.getRequest().getRealPath(File.separator + "excel")+ File.separator + name + ".xls";

            List<DimensionalBarCode> list = positionService.getAllByExcel(realPath);
            for (int i = 0; i < list.size(); i++) {
                dimensionalBarCode = list.get(i);
                positionService.importDimensionalCode(dimensionalBarCode);
            }
            super.getPringWriter().print(0);
        } catch (Exception e) {
            super.getPringWriter().print(1);
            e.printStackTrace();
        }
    }   

    // 将MultipartFile 转换为File
    public void SaveFileFromInputStream(InputStream stream, String path,String savefile) throws IOException {

        FileOutputStream fs = new FileOutputStream(path + File.separator+ savefile);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    /**
     * 验证消息
     * 
     * @param person
     * @return
     */

    public void getListByName() {
        List<Position> list = positionService.searchPositionByCondition(condition);
        String[] ignoreAttribute = new String[] { "comName", "company",
                "basDict", "posLongitude", "posLatitude", "posDate",
                "posStatus", "freezes", "persons", "sealeds" };
        try {
            super.outPrintJsonByArray(list, ignoreAttribute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void del() {
        try {
            if (null != id) {
            	
                positionService.delPositionById(id);
                super.getResponse().getWriter().print(0);
                
            } else {
                super.getResponse().getWriter().print(1);
            }
        } catch (Exception e) {
            try {
                super.getResponse().getWriter().print(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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

    public PositionService getPositionService() {
        return positionService;
    }

    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public PositionCondition getCondition() {
        return condition;
    }

    public void setCondition(PositionCondition condition) {
        this.condition = condition;
    }

    public List<Position> getList() {
        return list;
    }

    public void setList(List<Position> list) {
        this.list = list;
    }

    public List<Sealed> getSealeds() {
        return sealeds;
    }

    public void setSealeds(List<Sealed> sealeds) {
        this.sealeds = sealeds;
    }

    public PositionExamine getPositionExamine() {
        return positionExamine;
    }

    public void setPositionExamine(PositionExamine positionExamine) {
        this.positionExamine = positionExamine;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public DimensionalBarCode getDimensionalBarCode() {
        return dimensionalBarCode;
    }

    public void setDimensionalBarCode(DimensionalBarCode dimensionalBarCode) {
        this.dimensionalBarCode = dimensionalBarCode;
    }

    public File getFiledName() {
        return filedName;
    }

    public void setFiledName(File filedName) {
        this.filedName = filedName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Province getCom_a() {
		return com_a;
	}

	public void setCom_a(Province com_a) {
		this.com_a = com_a;
	}

	public String getPhoneMac() {
		return phoneMac;
	}

	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}

	public Company getComa() {
		return coma;
	}

	public void setComa(Company coma) {
		this.coma = coma;
	}
}
