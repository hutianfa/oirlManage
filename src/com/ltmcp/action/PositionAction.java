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

    private PositionExamine positionExamine;// ��Ҫ��ӵĶ���

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
	 * ������תҳ��
	 * 
	 */
    private DimensionalBarCode dimensionalBarCode;

    private File filedName;


    private String uploadContentType; // �ļ�����������

    private String uploadFileName; // �ϴ��ļ���

    /**
     * ��ȡλ����ϸ����
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
     * ����������ѯλ����Ϣ�б�
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
     * ������תҳ��
     * 
     */
    public String addCardNumber() {
        return SUCCESS;
    }

    /**
     * ����µ�վ��
     */
    public void addNewPosition() {
        try {
        	//����Ա������������ӹ�˾
        	if(AdminComm.getAdminPower() == 1){//����ǹ���Ա
        		//���жϹ�˾�Ƿ����
        		Integer comaid = positionService.queryCom_a(coma.getComName());
        		
    			if(comaid == -1){//�������򱣴���󣬽�id����
    				//���湫˾
    				Company cc = new Company();
    				cc.setComName(coma.getComName());
    				Integer cid = positionService.addCom_a(cc);
    				
    				//���ж�areaid�Ƿ����
                	Integer areaid = positionService.queryArea(area.getArea_name());
                	  
                	if(areaid == -1){//������ ����
                		Area a = new Area();
                    	a.setArea_name(area.getArea_name());
                    	a.setCom_id(cid);
                    	
                    	Integer aid =  positionService.addArea(a);
                    	
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
                	}else{//���� ��ȡid
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
                	}
    			}else{//���ھͱ��淵�ص�id
    				//���ж�areaid�Ƿ����
                	Integer areaid = positionService.queryArea(area.getArea_name());
                	  
                	if(areaid == -1){//������ ����
                		Area a = new Area();
                    	a.setArea_name(area.getArea_name());
                    	a.setCom_id(comaid);
                    	
                    	Integer aid =  positionService.addArea(a);
                    	
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
                	}else{//���� ��ȡid
                    	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
                	}
    			}
    				        		
        	}else{//������ǹ���Ա
        		//���ж�areaid�Ƿ����
            	Integer areaid = positionService.queryArea(area.getArea_name());
            	  
            	if(areaid == -1){//������ ����
            		Area a = new Area();
                	a.setArea_name(area.getArea_name());
                	a.setCom_id(AdminComm.getComIdByAdmin());
                	
                	Integer aid =  positionService.addArea(a);
                	
                	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,aid,phoneMac);
            	}else{//���� ��ȡid
                	Integer posid =  positionService.addPosCardNumber(dictId, posName, cardNumber,areaid,phoneMac);
            	}
        	}
   
            super.getPringWriter().print(0);

        } catch (Exception e) {
            super.getPringWriter().print(1);
            e.printStackTrace();
        }
    }

    //id��ѯ �ֹ�˾
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
    //id��ѯ��
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
    //id��ѯվ��
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
     * �޸�վ����Ϣ
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
     * ��excel���е����ݵ���mysql���ݿ�
     */
    @SuppressWarnings("deprecation")
    public void importCodeByMysql() {
        try {
            InputStream is = new FileInputStream(filedName);
            String name = UUID.randomUUID().toString();
            // �����ϴ����ļ���������
            this.SaveFileFromInputStream(is, ServletActionContext.getRequest().getRealPath(File.separator + "excel") + File.separator,name + ".xls");
            // �ϴ�֮����ļ�·���������ļ�����
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

    // ��MultipartFile ת��ΪFile
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
     * ��֤��Ϣ
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
