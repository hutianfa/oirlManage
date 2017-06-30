package com.ltmcp.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionCondition;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.dao.PositionDao;
import com.ltmcp.dao.SealedDao;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Province;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.entity.Sealed;
import com.ltmcp.service.PositionService;

public class PositionServiceImpl extends BaseServiceImpl implements
        PositionService {

    private PositionDao positionDao;

    private SealedDao sealedDao;

    @Override
    public Position getPosition(Integer id) {
        if (null != id) {
            Position position = new Position();
            position.setPosId(id);
            return positionDao.queryPosition(position);
        }
        return null;
    }

    @Override
    public PageBean searchPosition(PositionCondition condition,PageBean pageBean) {
        if (null == pageBean) {
            pageBean = new PageBean();
        }
        List<Position> list = positionDao.findPositions(pageBean.getCurentPage(), pageBean.getPageSize(), condition);
        Integer totalCount = positionDao.queryPositionCountByCondition(condition);
        return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),pageBean.getCurentPage());
    }

    @Override
    public PageBean searchSealedByPositionId(Integer id, PageBean pageBean) {
        if (null == id) {
            return new PageBean();
        }
        if (null == pageBean) {
            pageBean = new PageBean();
        }
        List<Sealed> list = sealedDao.findSealedsByPositionId(
                pageBean.getCurentPage(), pageBean.getPageSize(), id);
        Integer totalCount = sealedDao.querySealedCountByPositionId(id);

        return PageBean.getPageBean(list, totalCount, pageBean.getPageSize(),
                pageBean.getCurentPage());
    }

    @Override
    public void delPositionById(Integer id) throws Exception {
        if (null == id) {
            throw new Exception();
        }
        if (positionDao.isPositionexist(id) == false) {
            throw new Exception();
        }
        positionDao.delPositionById(id);
    }

    @Override
    public List<Position> searchPositionByCondition(PositionCondition condition) {
        List<Position> list = positionDao.findPositionsByCondition();
        return list;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public SealedDao getSealedDao() {
        return sealedDao;
    }

    public void setSealedDao(SealedDao sealedDao) {
        this.sealedDao = sealedDao;
    }

    @Override
    public void savePos(PositionExamine posExam) {
        positionDao.addPos(posExam);
    }

    /**
     * 添加新的位置
     */
    @Override
    public Integer addPosCardNumber(Integer dictId, String posName,String cardNumber,Integer areaid,String phoneMac) {

        return positionDao.insertCardNumber(dictId, posName, cardNumber,areaid ,phoneMac);
    }


	@Override
	public List<Area> findAreaById(Integer id) {
		return positionDao.queryAreaById(id);
	}

	@Override
	public List<Company> findCom_aById() {		
		return positionDao.queryCom_aById();
	}

	@Override
	public List<Position> findPositionById(Integer id) {
		return positionDao.queryPosiById(id);
	}

	@Override
	public Integer addArea(Area area) {
		return positionDao.insertArea(area);
	}

	@Override
	public List<Person> findpesonByposId(Integer posid) {
		return positionDao.queryPersonidById(posid);
	}
	@Override
	public Integer queryArea(String areaName) {
		Area a = positionDao.findArea(areaName);
		if(a != null){
			return a.getId();
		}else{
			return -1;
		}
	}
	
	@Override
	public Integer addCom(Province com_a) {
		return positionDao.insertCom_a(com_a);
	}
    /**
     * 更新站点名称
     */
    @Override
    public void updateNewPosition(Position position) {
        try {
            if (position.getPosId() != null) {
                positionDao.updateNewPosition(position);
                if (position.getPosCardNumber() != null) {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void importDimensionalCode(DimensionalBarCode dimensonalBarCode) {
        positionDao.addDimensCode(dimensonalBarCode);
    }

    /**
     * 查询excel表中的数据
     */
    @Override
    public List<DimensionalBarCode> getAllByExcel(String file) {
        List<DimensionalBarCode> list = new ArrayList<DimensionalBarCode>();
        try {
            String code = null;
            String id = null;
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet(0);
            // 得到所有的列
            int cols = rs.getColumns();
            // 得到所有的行
            int rows = rs.getRows();
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 第一个是列数，第二个是行数
                    if (cols == 1) {
                        id = rs.getCell(j, i).getContents(); // 默认最左边编号也算一列
                        code = rs.getCell(j, i).getContents();
                    } else {
                        id = rs.getCell(j++, i).getContents(); // 默认最左边编号也算一列
                        code = rs.getCell(j++, i).getContents();
                    }
                    DimensionalBarCode barCode = new DimensionalBarCode(code, 0,code, 0);
                    list.add(barCode);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public Integer queryCom_a(String comaName) {
		Company coma = positionDao.findComa(comaName);
		if(coma != null){
			return coma.getComId();
		}else{
			return -1;
		}		
	}

	@Override
	public Integer addCom_a(Company coma) {
		return positionDao.insertComa(coma);
	}
}
