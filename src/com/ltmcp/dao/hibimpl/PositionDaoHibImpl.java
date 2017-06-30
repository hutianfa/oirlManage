package com.ltmcp.dao.hibimpl;

import java.sql.Timestamp;
import java.util.List;
import com.ltmcp.comm.AdminComm;
import com.ltmcp.comm.Comm;
import com.ltmcp.condition.PositionCondition;
import com.ltmcp.dao.PositionDao;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.BasDict;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Province;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;

public class PositionDaoHibImpl extends BaseDaoHibImpl implements PositionDao {

    @Override
    public void updatePosition(Position position) {
        super.update(position);
    }

    @Override
    public void deletePosition(Position position) {
        position.setPosStatus(Comm.POSITION_LOSS);
        super.update(position);
    }

    @Override
    public Position queryPosition(Position position) {
        StringBuilder sb = new StringBuilder(" from Position p");
        sb.append(" left join fetch p.company ");
        sb.append(" left join fetch p.basDict ");
        sb.append(" left join fetch p.freezes ");
        sb.append(" left join fetch p.persons ");
        sb.append(" left join fetch p.sealeds ");
        sb.append(" where p.posId = " + position.getPosId());
        
        if(AdminComm.getAdminPower() != 1){
            sb.append(" and p.company.comId=" + AdminComm.getComIdByAdmin());
        }
        return (Position) super.query(sb.toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Position> findPositions(Integer currentPage, Integer pageSize,PositionCondition condition) {
        StringBuilder sb = new StringBuilder("from Position p where  p.posStatus = " + Comm.POSITION_NORMAL);
        queryMethod(condition, sb);
        sb.append(" order by p.posDate desc ");
        return super.find(sb.toString(), currentPage, pageSize);
    }

    private void queryMethod(PositionCondition condition, StringBuilder sb) {
        if (null != condition) {
        	if(AdminComm.getAdminPower() != 1){
	        	  if (null != condition.getComId()) {
	                  sb.append("and p.company.comId=" + condition.getComId());
	              } else {
	                  sb.append("and p.company.comId= " + AdminComm.getComIdByAdmin());
	              }
        	} else if(AdminComm.getAdminPower() == 1 && null != condition.getComId() && !"".equals(condition.getComId())){
        		sb.append("and p.company.comId=" + condition.getComId());
        	}        	
            if (null != condition.getPosType()) {
                sb.append(" and p.basDict.dictId = " + condition.getPosType());
            }
            if (null != condition.getPosid() ) {
                sb.append(" and p.posId =" + condition.getPosid());
            }
        } else {
        	if(AdminComm.getAdminPower() != 1){
        		sb.append(" and p.company.comId= " + AdminComm.getComIdByAdmin());
        	}
        }
    }

    @Override
    public Integer queryPositionCountByCondition(PositionCondition condition) {
        StringBuilder sb = new StringBuilder("select count(posId) from Position p where  p.posStatus = "+ Comm.POSITION_NORMAL);

        if(AdminComm.getAdminPower() != 1){
        	sb.append(" and p.company.comId= " + AdminComm.getComIdByAdmin());
        }
        this.queryMethod(condition, sb);
        return super.queryRowCount(sb.toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Position> findPositions() {
        StringBuilder sb = new StringBuilder("from Position where posStatus=? ");
        
        if(AdminComm.getAdminPower() != 1){
            sb.append(" and company.comId= " + AdminComm.getComIdByAdmin());
        }
        return super.find(sb.toString(), Comm.POSITION_NORMAL);
    }

    @Override
    public void delPositionById(Integer id) {
        StringBuilder sb = new StringBuilder("update Position p set p.posStatus=? where p.posId=? ");
        if(AdminComm.getAdminPower() != 1){
            sb.append(" and p.company.comId= " + AdminComm.getComIdByAdmin());
        }
        super.updateByHql(sb.toString(), Comm.POSITION_LOSS, id);
    }

    @Override
    public boolean isPositionexist(Integer id) {
        StringBuilder sb = new StringBuilder("select count(p.posId) from Position p ");
        sb.append(" where p.posId=?  and p.posStatus=? ");
        if(AdminComm.getAdminPower() != 1){
            sb.append(" and p.company.comId= " + AdminComm.getComIdByAdmin());
        }
        Integer count = super.queryRowCount(sb.toString(), id, Comm.POSITION_NORMAL);
        if (count > 0) {
            return true;
        }
        return false;
    }  
    
    public List<Position> findPositionsByCondition() {
        StringBuilder sb = new StringBuilder("from Position p ");
        sb.append(" where p.posStatus = "+Comm.POSITION_NORMAL);
        if(AdminComm.getAdminPower() !=1){
        	sb.append(" and p.company.comId="+AdminComm.getComIdByAdmin());
        }
        return super.findList(sb.toString());
    }

    /**
     * 把审核通过的插入到position表
     */
    @Override
    public void addPos(PositionExamine posExam) {
        Position position = new Position();
        basDict.setDictId(1);
        position.setPosId(posExam.getId());
        position.setPosName(posExam.getName());
        position.setPosLongitude(posExam.getLongitude());
        position.setPosLatitude(posExam.getLatitude());
        position.setPosStatus(posExam.getState());
        position.setPosDate(posExam.getTime());
        position.setComName(posExam.getCompany().getComName());
        position.setPosCardNumber(posExam.getCardNumber());
        position.setCompany(posExam.getCompany());
        position.setBasDict(basDict);
        super.insert(position);
    }


    private BasDict basDict = new BasDict();

    public BasDict getBasDict() {
        return basDict;
    }

    public void setBasDict(BasDict basDict) {
        this.basDict = basDict;
    }

    /**
     * 把芯片号添加到数据库
     */
    @Override
    public Integer insertCardNumber(Integer dictId, String posName,String cardNumber,Integer areaid,String phoneMac) {
        Position position = new Position();
        basDict.setDictId(dictId); 
        Company comy = new Company();
        comy.setComId(AdminComm.getComIdByAdmin());
        position.setPosId(null);
        position.setPosName(posName);
        position.setPosLongitude(null);
        position.setPosLatitude(null);
        position.setPosStatus(0);
        position.setPosDate(new Timestamp(System.currentTimeMillis()));
        position.setComName(null);
        position.setPosCardNumber(","+cardNumber+",");
        position.setCompany(comy);
        position.setBasDict(basDict);
        position.setAreaid(areaid);
        if(!"".equals(phoneMac)){
        	position.setPhoneMac(","+phoneMac+",");
        }
        super.insert(position);
        return position.getAreaid();
    }

	@Override
	public Area findArea(String areaName) {
		StringBuffer sb = new StringBuffer("from Area a where a.area_name = '"+ areaName+"'");
		return (Area) super.query(sb.toString(), null);
	}
    
	@Override
	public Integer insertArea(Area area) {
		 super.insert(area);
		return area.getCom_id();
	}

	@Override
	public Integer insertCom_a(Province com_a) {
		super.insert(com_a);
		return com_a.getId();
	}
    
	@Override
	public List<Company> queryCom_aById() {
		StringBuffer sb = new StringBuffer("from Company c where 1=1");
		if(AdminComm.getAdminPower() != 1){
			sb.append("and c.comId = "+AdminComm.getComIdByAdmin());
		}
		return super.find(sb.toString(), null);
	}

	@Override
	public List<Area> queryAreaById(Integer id) {
		StringBuffer sb = new StringBuffer("from Area a where 1=1" );
		if( id != null){
			sb.append("and a.com_id = "+id);
		}
		return super.find(sb.toString(), null);
	}

	@Override
	public List<Position> queryPosiById(Integer id) {
		StringBuffer sb = new StringBuffer("from Position p where 1 = 1");
		if( id != null){
			sb.append("and p.areaid = " + id);
		}
		sb.append("and p.company.comId = " +AdminComm.getComIdByAdmin());
		return super.find(sb.toString(), null);
	}
    
	@Override
	public List<Person> queryPersonidById(Integer id) {
		StringBuffer sb = new StringBuffer("from Person p where p.perStatus = "+Comm.PERSON_NORMAL);
		sb.append("and p.perEmail like '%0%'");	
		if( id != null){
			sb.append("and p.position.posId = " + id);
		}
		sb.append("and p.position.company.comId = " +AdminComm.getComIdByAdmin());		
		return super.find(sb.toString(), null);
	}
	
    @Override
    public void updateNewPosition(Position position) {
        super.update(position);

    }

    /**
     * 将excel表中的数据导入到数据库
     */
    @Override
    public void addDimensCode(DimensionalBarCode dimensionalBarCode) {
        super.insert(dimensionalBarCode);
    }

	@Override
	public Integer insertComa(Company coma) {
		super.insert(coma);
		return coma.getComId();
	}

	@Override
	public Company findComa(String comaName) {
		StringBuffer sb = new StringBuffer("from Company c where c.comName = "+comaName);
		return (Company) super.query(sb.toString(), null);
	}
}
